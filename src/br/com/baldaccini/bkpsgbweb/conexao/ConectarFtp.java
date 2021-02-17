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
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * since 2011
 * Data: 2021
 * @author baldaccini
 */
public class ConectarFtp {

    private FTPClient ftp = null;

    public ConectarFtp() {
        this.ftp = new FTPClient();
    }

    public FTPClient conectar(String ip, int porta, String usuario, String pass, String diretorio, boolean modoPassivo) {
        try {
            ftp.connect(ip, porta);
            GravarArquivoLog.gravarTodosLog("Conexão estabelecida com sucesso!");
            if (ftp.login(usuario, pass)) {
                GravarArquivoLog.gravarTodosLog("Login realizado com sucesso!");
                ftp.setSendBufferSize(8192);
                ftp.setBufferSize(8192);
                GravarArquivoLog.gravarLogInformation("Taxa de envio 8Mb", ConfigBkp.getInstance());
                if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                    if(ftp.setFileType(FTP.BINARY_FILE_TYPE)){
                        GravarArquivoLog.gravarTodosLog("Tipo da transferencia alterada para binario.");
                    }else{
                        GravarArquivoLog.gravarTodosLog("Não foi possivel alterar o tipo da transferencia para binario.");
                    }
                    
                    if (modoPassivo) {
                        ftp.enterLocalPassiveMode();
                        GravarArquivoLog.gravarTodosLog("Alterou para o modo passivo.");
                    } else {
                        ftp.enterLocalActiveMode();
                        GravarArquivoLog.gravarTodosLog("Alterou para o modo ativo.");
                    }
                }
            }
        } catch (IOException ex) {
            GravarArquivoLog.gravarTodosLog("IOException: " + ex.getMessage());
        }
        return ftp;
    }
    private String destino;
    private DirectoryStream<Path> entradas;
    boolean destinoRaiz=true;
    public boolean enviarArquivo(Path local, String destino) throws IOException{
        try {
            if(destinoRaiz){
                this.destino = destino;
                destinoRaiz = false;
            }
            if (Files.isDirectory(local)) {
                // listamos todas as entradas do diretório
                entradas = Files.newDirectoryStream(local);
            }
            String dir = "";
            String dirLinux = "";
            for (Path entrada : entradas) {
                if(Files.isDirectory(entrada, LinkOption.NOFOLLOW_LINKS)){
                    dir = (entrada.getParent()+File.separator+entrada.getFileName());
                    System.out.println("Diretorio: " + dir);
                    dirLinux = dir.substring(dir.lastIndexOf(this.destino), dir.length());
                    System.out.println("Diretorio linux: " + dirLinux);
                    dirLinux = entrada.getFileName().toString();
                    if(!ftp.changeWorkingDirectory(dirLinux)){
                        if(ftp.makeDirectory(dirLinux)){
                            if(ftp.changeWorkingDirectory(dirLinux)){
                                GravarArquivoLog.gravarLogInformation("Diretorio alterado: " + dirLinux, ConfigBkp.getInstance());
                            }
                        }
                    }
                
                    enviarArquivo(entrada, dirLinux);
                }else{
                    dir = entrada.getParent()+File.separator+entrada.getFileName();
                    System.out.println("Arquivo: " + dir);
                    dirLinux = dir.substring(dir.lastIndexOf(this.destino), dir.length());
                    System.out.println("Arquivo Linux: " + dirLinux);
                    File file = new File(dir);
                    InputStream is = new FileInputStream(file);
                    GravarArquivoLog.gravarLogInformation("Enviando arquivo...", ConfigBkp.getInstance());
                    ftp.storeFile(file.getName(), is);
                    GravarArquivoLog.gravarLogInformation("Envio concluido com sucesso!", ConfigBkp.getInstance());
                }
            }
            ftp.changeWorkingDirectory("../");
//            FTPFile file = ftp.mlistFile("wafiapps.net_Adobe_Flash_CS3_Professional.zip");
//            if(file != null){
//                if("xstream.zip".equals(file.getName().replace("/",""))){
//                    lblResposta.setText("Arquivo ja existe no diretorio FTP!!");
//                    return;
//                }
//            }
        }catch(IOException ex){
            GravarArquivoLog.gravarLogInformation("Falha ao enviar arquivo: " + ex.getMessage(), ConfigBkp.getInstance());
            return false;
        }
        return true;
    }
}
