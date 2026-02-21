package br.com.baldaccini.bkpsgbweb.conexao.ftp.externo;

import br.com.baldaccini.bkpsgbweb.interfaces.IDestinoFtp;
import br.com.baldaccini.bkpsgbweb.log.GravarArquivoLog;
import br.com.baldaccini.bkpsgbweb.swing.ConfigBkp;
import br.com.baldaccini.bkpsgbweb.swing.DestinoFtp;
import org.apache.commons.net.ftp.*;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.IOException;
import java.nio.file.*;
import java.util.Objects;

public class FtpExterno implements IDestinoFtp {

    private FTPClient ftp;
    private final DestinoFtp destinoFtp;
    private final ConfigBkp configBkp;

    private DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("raiz");
    private final DefaultTreeModel model = new DefaultTreeModel(raiz);

    private long totalBytes;

    public FtpExterno(ConfigBkp configBkp, DestinoFtp destinoFtp) {
        this.configBkp = Objects.requireNonNull(configBkp);
        this.destinoFtp = destinoFtp;

        if (destinoFtp != null) {
            destinoFtp.getJTree().setModel(model);
        }
    }

    // =========================
    // CONEXAO
    // =========================
    @Override
    public boolean conectar(String host, int porta, String usuario,
            String senha, String diretorio,
            boolean modoPassivo) {

        ftp = new FTPClient();

        try {
            ftp.connect(host, porta);

            if (!ftp.login(usuario, senha)
                    || !FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                return false;
            }

            ftp.setFileType(FTP.BINARY_FILE_TYPE);

            if (modoPassivo) {
                ftp.enterLocalPassiveMode();
            } else {
                ftp.enterLocalActiveMode();
            }

            if (diretorio != null && !diretorio.isBlank()) {
                ftp.changeWorkingDirectory(diretorio);
            }

            log("Conectado ao servidor FTP.");

            //Virtual Thread
            Thread.startVirtualThread(this::listarDiretorioFtp);

            return true;

        } catch (IOException e) {
            logErro(e);
            return false;
        }
    }

    @Override
    public boolean desconectar() {
        if (ftp == null || !ftp.isConnected()) {
            return true;
        }

        try {
            ftp.logout();
            ftp.disconnect();
            log("Desconectado com sucesso.");
            return true;
        } catch (IOException e) {
            logErro(e);
            return false;
        }
    }

    // =========================
    // UPLOAD RECURSIVO
    // =========================
    public void enviarDiretorio(Path origem) {
        if (!Files.exists(origem)) {
            return;
        }

        Thread.startVirtualThread(() -> uploadRecursivo(origem));
    }

    private void uploadRecursivo(Path origem) {

        try (var paths = Files.walk(origem)) {

            paths.forEach(path -> {
                try {
                    if (Files.isDirectory(path)) {
                        criarDiretorioRemoto(path.getFileName().toString());
                    } else {
                        enviarArquivo(path);
                    }
                } catch (Exception e) {
                    logErro(e);
                }
            });

        } catch (IOException e) {
            logErro(e);
        }
    }

    private void enviarArquivo(Path arquivo) throws IOException {

        try (var input = Files.newInputStream(arquivo)) {

            if (ftp.storeFile(arquivo.getFileName().toString(), input)) {

                totalBytes += Files.size(arquivo);
                log("Arquivo enviado: " + arquivo.getFileName());

            } else {
                log("Falha ao enviar: " + arquivo.getFileName());
            }
        }
    }

    private void criarDiretorioRemoto(String nome) throws IOException {

        if (!ftp.changeWorkingDirectory(nome)) {
            ftp.makeDirectory(nome);
            ftp.changeWorkingDirectory(nome);
        }
    }

    // =========================
    // LISTAGEM
    // =========================
    private void listarDiretorioFtp() {

        try {
            listarRecursivo("/");
            if(totalBytes == 0l)
                adicionarNoTree(ftp.printWorkingDirectory());
        } catch (IOException e) {
            logErro(e);
        }
    }

    private void listarRecursivo(String path) throws IOException {

        ftp.changeWorkingDirectory(path);

        var arquivos = ftp.listFiles();

        if (arquivos == null) {
            return;
        }

        for (var arquivo : arquivos) {

            if (arquivo.isDirectory()) {
                listarRecursivo(path + "/" + arquivo.getName());
            } else {
                adicionarNoTree(path + "/" + arquivo.getName());
                totalBytes += arquivo.getSize();
            }
        }
    }

    // =========================
    // TREE
    // =========================
    private void adicionarNoTree(String caminho) {

        var partes = caminho.split("/");
        var node = raiz;
        if(partes != null && partes.length == 0){
            raiz = new DefaultMutableTreeNode("/");
        }    
        for (var parte : partes) {

            if (parte.isBlank()) {
                continue;
            }

            DefaultMutableTreeNode filho = null;

            for (int i = 0; i < node.getChildCount(); i++) {
                var child = (DefaultMutableTreeNode) node.getChildAt(i);
                if (parte.equals(child.getUserObject())) {
                    filho = child;
                    break;
                }
            }

            if (filho == null) {
                filho = new DefaultMutableTreeNode(parte);
                node.add(filho);
            }

            node = filho;
        }
    }

    // =========================
    // LOG
    // =========================
    private void log(String msg) {
        if (destinoFtp != null) {
            destinoFtp.atualizarLog(msg);
        }
        GravarArquivoLog.gravarLogInformation(msg, configBkp);
    }

    private void logErro(Exception e) {
        if (destinoFtp != null) {
            destinoFtp.atualizarLog(e.getMessage());
        }
        GravarArquivoLog.gravarTodosLog(e.getMessage());
    }

    @Override
    public void fecharJanela() {
        desconectar();
    }
}
