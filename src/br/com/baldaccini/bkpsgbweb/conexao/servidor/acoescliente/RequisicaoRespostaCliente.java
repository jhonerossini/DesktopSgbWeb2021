/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.conexao.servidor.acoescliente;

import br.com.baldaccini.bkpsgbweb.interfaces.INotificacoesArquivo;
import br.com.baldaccini.bkpsgbweb.log.GravarServidorLog;
import br.com.baldaccini.bkpsgbweb.manipularArquivos.IniciarBackupArquivo;
import br.com.baldaccini.bkpsgbweb.modelo.Acoes;
import br.com.baldaccini.bkpsgbweb.modelo.BackupArquivo;
import br.com.baldaccini.bkpsgbweb.modelo.Login;
import br.com.baldaccini.bkpsgbweb.swing.ConfigBkp;
import br.com.baldaccini.bkpsgbweb.swing.backuparquivo.SwingBackupArquivo;
import br.com.baldaccini.bkpsgbweb.util.Criptografia;
import br.com.baldaccini.bkpsgbweb.util.Util;
import br.com.baldaccini.bkpsgbweb.xml.LoginXML;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

/**
 *
 * @author jhone
 */
public class RequisicaoRespostaCliente implements Runnable {

    private final Socket socket;
    private DataOutputStream out;
    private DataInputStream inp;
    private final GravarServidorLog gravarServidorLog;

    private INotificacoesArquivo configBkp;

    public RequisicaoRespostaCliente(Socket socket) {
        this.socket = socket;
        gravarServidorLog = new GravarServidorLog();
        try {
            out = new DataOutputStream(this.socket.getOutputStream());
            inp = new DataInputStream(this.socket.getInputStream());
            configBkp = ConfigBkp.getInstance();
        } catch (IOException ex) {
            gravarServidorLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
        }
    }

    @Override
    public synchronized void run() {
        try {
            Integer acao;
            Integer valor;
            while (true) {
                acao = inp.readInt();
                configBkp.setServidorBytesRecebido(String.valueOf(acao.byteValue()));
                switch (acao) {
                    case 2:
                        valor = inp.readInt();
                        configBkp.iniciarThreadArquivo(valor);
                        configBkp.setServidorBytesRecebido(String.valueOf(valor.byteValue()));
                        break;
                    case 3:
                        valor = inp.readInt();
                        configBkp.pausarThreadArquivo(valor);
                        configBkp.setServidorBytesRecebido(String.valueOf(valor.byteValue()));
                        break;
                    case 4:
                        valor = inp.readInt();
                        configBkp.pararThreadArquivo(valor);
                        configBkp.setServidorBytesRecebido(String.valueOf(valor.byteValue()));
                        break;
                    case 1:
                        enviarListaBackupArquivo();
                        break;
                    case 5:
                        valor = inp.readInt();
                        SwingBackupArquivo.excluirThreadArquivo(valor);
                        configBkp.setServidorBytesRecebido(String.valueOf(valor.byteValue()));
                        break;
                    case 0:
                        String nome = inp.readUTF();
                        configBkp.setServidorBytesRecebido(String.valueOf(nome.getBytes()));
                        String senha = inp.readUTF();
                        configBkp.setServidorBytesRecebido(String.valueOf(senha.getBytes()));
                        login(nome, senha, acao);
                        break;
                }
            }
        } catch (IOException ex) {
            gravarServidorLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
        }
    }

    public void acaoBkpWeb(int acao, int linha, BackupArquivo ba, String estadoBkp) {
        try {
            if (linha == -1) {
                configBkp.setServidorBytesEnviados(Util.getBytesValorVariaveis(Acoes.BKP_ARQUIVO_FLAG));
                out.writeInt(Acoes.BKP_ARQUIVO_FLAG);
                configBkp.setServidorBytesEnviados(Util.getBytesValorVariaveis(acao));
                out.writeInt(acao);
                enviarListaBackupArquivo(ba, estadoBkp);
            } else {
                configBkp.setServidorBytesEnviados(Util.getBytesValorVariaveis(Acoes.BKP_ARQUIVO_FLAG));
                out.writeInt(Acoes.BKP_ARQUIVO_FLAG);
                configBkp.setServidorBytesEnviados(Util.getBytesValorVariaveis(acao));
                out.writeInt(acao);
                configBkp.setServidorBytesEnviados(Util.getBytesValorVariaveis(linha));
                out.writeInt(linha);
            }
        } catch (IOException ex) {
            gravarServidorLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
        }
    }

    public void enviarListaBackupArquivo(BackupArquivo ba, String estadoBkp) {
        try {
            out.writeUTF(ba.getDataBackupAgendado());//diaBackup

            out.writeUTF(Arrays.toString(new String[]{(!"false".equals(ba.getDom()) ? ba.getDom() : ""),
                (!"false".equals(ba.getSeg()) ? ba.getSeg() : ""),
                (!"false".equals(ba.getTer()) ? ba.getTer() : ""),
                (!"false".equals(ba.getQua()) ? ba.getQua() : ""),
                (!"false".equals(ba.getQui()) ? ba.getQui() : ""),
                (!"false".equals(ba.getSex()) ? ba.getSex() : ""),
                (!"false".equals(ba.getSab()) ? ba.getSab() : "")}));

            out.writeUTF(estadoBkp);

            out.writeUTF(ba.getNome());

            out.writeUTF(Arrays.toString(new String[]{
                (!"false".equals(ba.getBkpIncremental()) ? Acoes.INCREMENTAL : ""),
                (!"false".equals(ba.getNormal()) ? Acoes.NORMAL : ""),
                (!"false".equals(ba.getCompactar()) ? Acoes.COMPACTAR : "")}));

            out.flush();
        } catch (IOException ex) {
            gravarServidorLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
        }
    }

    private void enviarListaBackupArquivo() {
        int qtdlinha;
        String diaBkpAg;
        String semanaBkp;
        String estadoBkp;
        String nome;
        String tipoBkp;
        try {
            out.writeInt(Acoes.BKP_ARQUIVO_FLAG);
            configBkp.setServidorBytesEnviados(String.valueOf(Acoes.BKP_ARQUIVO_FLAG));
            
            out.writeInt(Acoes.POPULAR_TABELA);//codigo
            configBkp.setServidorBytesEnviados(String.valueOf(Acoes.POPULAR_TABELA));
            
            qtdlinha = SwingBackupArquivo.getListaThreadArquivo().size();
            out.writeInt(qtdlinha);//qtdLinha
            configBkp.setServidorBytesEnviados(String.valueOf(qtdlinha));

            for (IniciarBackupArquivo iba : SwingBackupArquivo.getListaThreadArquivo()) {

                diaBkpAg = iba.getBackupArquivo().getDataBackupAgendado();
                out.writeUTF(diaBkpAg);//diaBackup
                configBkp.setServidorBytesEnviados(diaBkpAg);

                semanaBkp = Arrays.toString(new String[]{(!"false".equals(iba.getBackupArquivo().getDom()) ? iba.getBackupArquivo().getDom() : ""),
                    (!"false".equals(iba.getBackupArquivo().getSeg()) ? iba.getBackupArquivo().getSeg() : ""),
                    (!"false".equals(iba.getBackupArquivo().getTer()) ? iba.getBackupArquivo().getTer() : ""),
                    (!"false".equals(iba.getBackupArquivo().getQua()) ? iba.getBackupArquivo().getQua() : ""),
                    (!"false".equals(iba.getBackupArquivo().getQui()) ? iba.getBackupArquivo().getQui() : ""),
                    (!"false".equals(iba.getBackupArquivo().getSex()) ? iba.getBackupArquivo().getSex() : ""),
                    (!"false".equals(iba.getBackupArquivo().getSab()) ? iba.getBackupArquivo().getSab() : "")});
                out.writeUTF(semanaBkp);
                configBkp.setServidorBytesEnviados(semanaBkp);

                estadoBkp = (iba.isPause() ? Acoes.PAUSADO : iba.isStartStop() ? Acoes.INICIADO : Acoes.PARADO);
                out.writeUTF(estadoBkp);
                configBkp.setServidorBytesEnviados(estadoBkp);

                nome = iba.getBackupArquivo().getNome();
                out.writeUTF(nome);
                configBkp.setServidorBytesEnviados(nome);

                tipoBkp = Arrays.toString(new String[]{
                    (!"false".equals(iba.getBackupArquivo().getBkpIncremental()) ? Acoes.INCREMENTAL : ""),
                    (!"false".equals(iba.getBackupArquivo().getNormal()) ? Acoes.NORMAL : ""),
                    (!"false".equals(iba.getBackupArquivo().getCompactar()) ? Acoes.COMPACTAR : "")});
                out.writeUTF(tipoBkp);
                configBkp.setServidorBytesEnviados(tipoBkp);

                out.flush();
            }
        } catch (IOException ex) {
            gravarServidorLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
        }
    }

    private void login(String usuario, String senha, int codAcao) {
        Login login = new LoginXML().lerLogin();
        if (usuario.equals(login.getUsuario()) && Criptografia.decrypt(senha).equals(Criptografia.decrypt(login.getPass()))) {
            try {
                out.write(codAcao);

                out.writeInt(Acoes.LOGIN_OK);

                out.writeUTF(usuario);

                if ("".equals(login.getEmail()) || login.getEmail() == null) {
                    out.writeUTF("");
                } else {
                    out.writeUTF(login.getEmail());
                }

                if ("".equals(login.getEmpresa()) || login.getEmpresa() == null) {
                    out.writeUTF("");
                } else {
                    out.writeUTF(login.getEmpresa());
                }

                if ("".equals(login.getFinalidadeBackup()) || login.getFinalidadeBackup() == null) {
                    out.writeUTF("");
                } else {
                    out.writeUTF(login.getFinalidadeBackup());
                }
            } catch (IOException ex) {
                gravarServidorLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
            }
        } else {
            try {
                out.write(codAcao);

                out.writeInt(Acoes.LOGIN_FAIL);

            } catch (IOException ex) {
                gravarServidorLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
            }
        }
    }

    public DataOutputStream getDataOutputStream() {
        return this.out;
    }
}
