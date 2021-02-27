/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.log;

import br.com.baldaccini.bkpsgbweb.data.DataReturn;
import br.com.baldaccini.bkpsgbweb.swing.ConfigBkp;
import java.io.*;

/**
 *
 * @author JHONE
 */
public class GravarArquivoLog {

    private File f;
    private FileReader fr;
    private BufferedReader br;
    private static DataReturn dr;
    private static FileWriter arq;
    private static StringBuilder info;
    private static PrintWriter pw;

    public GravarArquivoLog() {
        try {
            f = new File("arqLog.txt");
            arq = new FileWriter(f, true);
            pw = new PrintWriter(arq);
            info = new StringBuilder();
        } catch (IOException ex) {
            System.out.println("GravarArquivoLog -> GravarArquivoLog: " + ex.getMessage());
        }
    }

    public StringBuilder carregarArquivoTextoLog() {
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException ex) {
                GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
            }
        }
        StringBuilder texto = null;
        try {
            fr = new FileReader("arqLog.txt");
            br = new BufferedReader(fr);
            texto = new StringBuilder();
            try {
                while (br.ready()) {
                    texto.append(br.readLine());
                    texto.append("\n");
                }
                fr.close();
                br.close();
            } catch (IOException ex) {
                gravarLogError(ex.getMessage(), null);
            }
        } catch (FileNotFoundException ex) {
            gravarLogError(ex.getMessage(), null);
        }
        return texto;
    }

    public synchronized static void gravarLogWarning(String log, ConfigBkp configBkp) {
        dr = new DataReturn();
        try {
            info.append("Warn - ");
            info.append(dr.sysDataPath());
            info.append(" ");
            info.append(dr.sysHora());
            info.append(":");
            info.append(dr.sysMin());
            info.append(":");
            info.append(dr.sysSec());
            info.append(" - ");
            pw.append(info.toString());
            pw.append(log);
            pw.flush();
            arq.flush();
            if (configBkp != null) {
                configBkp.atualizarLogArquivo(info.append(log).toString());
            }
        } catch (IOException ex) {
            gravarLogError(ex.getMessage(), null);
        }finally {
            info.setLength(0);
        }
    }

    public synchronized static void gravarLogError(String log, ConfigBkp configBkp) {
        dr = new DataReturn();
        try {
            info.append("Error - ");
            info.append(dr.sysDataPath());
            info.append(" ");
            info.append(dr.sysHora());
            info.append(":");
            info.append(dr.sysMin());
            info.append(":");
            info.append(dr.sysSec());
            info.append(" - ");
            pw.append(info.toString());
            pw.append(log);
            pw.println();
            pw.flush();
            arq.flush();
            if (configBkp != null) {
                configBkp.atualizarLogArquivo(info + log);
            }
        } catch (IOException ex) {
            System.out.println("GravarArquivoLog -> gravarLogError: "+ex.getMessage());
            ConfigBkp.getInstance().atualizarLogArquivo("GravarArquivoLog -> gravarLogError: " + ex.getMessage());
        } finally {
            info.setLength(0);
        }
    }

    public synchronized static void gravarLogInformation(String log, ConfigBkp configBkp) {
        dr = new DataReturn();
        try {
            info.append("Info - ");
            info.append(dr.sysDataPath());
            info.append(" ");
            info.append(dr.sysHora());
            info.append(":");
            info.append(dr.sysMin());
            info.append(":");
            info.append(dr.sysSec());
            info.append(" - ");
            pw.append(info.toString());
            pw.append(log);
            pw.println();
            pw.flush();
            arq.flush();
            if (configBkp != null) {
                configBkp.atualizarLogArquivo(info.append(log).toString());
            }
        } catch (IOException ex) {
            gravarLogError(ex.getMessage(), null);
        }finally{
            info.setLength(0);
        }
    }

    public synchronized static void gravarTodosLog(String log) {
        dr = new DataReturn();
        try {
            info.append("error - ");
            info.append(dr.sysDataPath());
            info.append(" ");
            info.append(dr.sysHora());
            info.append(":");
            info.append(dr.sysMin());
            info.append(":");
            info.append(dr.sysSec());
            info.append(" - ");
            pw.append(info.toString());
            pw.append(log);
            pw.println();
            pw.flush();
            arq.flush();
            ConfigBkp.getInstance().atualizarLogArquivo(info.append(log).toString());
        } catch (IOException ex) {
            System.out.println("GravarArquivoLog -> gravarTodosLog: " + ex.getMessage());
            ConfigBkp.getInstance().atualizarLogArquivo("GravarArquivoLog -> gravarTodosLog: " + ex.getMessage());
        }finally{
            info.setLength(0);
        }
    }
}
