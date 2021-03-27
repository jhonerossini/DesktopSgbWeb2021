/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.conexao;

import br.com.baldaccini.bkpsgbweb.log.GravarArquivoLog;
import br.com.baldaccini.bkpsgbweb.swing.ConfigBkp;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * since 2011
 * Data: 2021
 * @author baldaccini
 */
public class ConectarFtp {

    private FTPClient ftp = null;
    private DirectoryStream<Path> entradas;
    private boolean destinoRaiz = true;

    public ConectarFtp() {
        this.ftp = new FTPClient();
    }

    public FTPClient conectar(String ip, int porta, String usuario, String pass, String diretorio, boolean modoPassivo) {
        try {
            ftp.connect(ip, porta);
            GravarArquivoLog.gravarLogInformation("Conexão estabelecida com sucesso!", ConfigBkp.getInstance());
            if (ftp.login(usuario, pass)) {
                GravarArquivoLog.gravarLogInformation("Login realizado com sucesso!", ConfigBkp.getInstance());
                ftp.setSendBufferSize(8192);
                ftp.setBufferSize(8192);
                GravarArquivoLog.gravarLogInformation("Taxa de envio 8Mb", ConfigBkp.getInstance());
                if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                    if(ftp.setFileType(FTP.BINARY_FILE_TYPE)){
                        GravarArquivoLog.gravarLogInformation("Tipo da transferencia alterada para binario.", ConfigBkp.getInstance());
                    }else{
                        GravarArquivoLog.gravarLogInformation("Não foi possivel alterar o tipo da transferencia para binario.", ConfigBkp.getInstance());
                    }
                    if (modoPassivo) {
                        ftp.enterLocalPassiveMode();
                        GravarArquivoLog.gravarLogInformation("Alterou para o modo passivo.", ConfigBkp.getInstance());
                    } else {
                        ftp.enterLocalActiveMode();
                        GravarArquivoLog.gravarLogInformation("Alterou para o modo ativo.", ConfigBkp.getInstance());
                    }
                }
            }
        } catch (IOException ex) {
            GravarArquivoLog.gravarTodosLog("IOException: " + ex.getMessage());
        }
        return ftp;
    }
    
    private boolean enviarArquivoCall(Path local){
        try {
            if(destinoRaiz){
                destinoRaiz = false;
            }
            if (Files.isDirectory(local)) {
                // listamos todas as entradas do diretório
                entradas = Files.newDirectoryStream(local);
            }else{
                try (FileInputStream fis = new FileInputStream(local.toFile())) {
                    ftp.storeFile(local.toFile().getName(), fis);
                }
                Files.delete(local);
                return true;
            }
            String dir = "";
            String dirLinux = "";
            for (Path entrada : entradas) {
                if(Files.isDirectory(entrada, LinkOption.NOFOLLOW_LINKS)){
                    dirLinux = entrada.getFileName().toString();
                    if(!ftp.changeWorkingDirectory(dirLinux)){
                        if(ftp.makeDirectory(dirLinux)){
                            if(ftp.changeWorkingDirectory(dirLinux)){
                                GravarArquivoLog.gravarLogInformation("Diretorio alterado: " + dirLinux, ConfigBkp.getInstance());
                            }
                        }
                    }
                
                    enviarArquivoCall(entrada);
                }else{
                    dir = entrada.getParent()+File.separator+entrada.getFileName();
                    File file = new File(dir);
                    InputStream is = new FileInputStream(file);
                    GravarArquivoLog.gravarLogInformation("Enviando arquivo...", ConfigBkp.getInstance());
                    ftp.storeFile(file.getName(), is);
                    GravarArquivoLog.gravarLogInformation("Envio concluido com sucesso: " + file.getName(), ConfigBkp.getInstance());
                }
            }
            ftp.changeWorkingDirectory("../");
        }catch(IOException ex){
            GravarArquivoLog.gravarLogInformation("Falha ao enviar arquivo: " + ex.getMessage(), ConfigBkp.getInstance());
            return false;
        }
        return true;
    }

    public void enviarArquivo(Path local, String destino) throws IOException{
        if(destino != null && !"".equals(destino)){
            ftp.changeWorkingDirectory(destino);
        }
        enviarArquivoCall(local);
    }
}
