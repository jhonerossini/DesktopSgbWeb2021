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
    private static String info;
    private static PrintWriter pw;

    public GravarArquivoLog() {
        try {
            f = new File("arqLog.txt");
            arq = new FileWriter("arqLog.txt", true);
            pw = new PrintWriter(arq);
        } catch (IOException ex) {
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

    public static void gravarLogWarning(String log, ConfigBkp configBkp) {
        dr = new DataReturn();
        try {
            info = "Warni - ";
            info += dr.sysDataPath();
            info += " ";
            info += dr.sysHora();
            info += ":";
            info += dr.sysMin();
            info += ":";
            info += dr.sysSec();
            info += " - ";
            pw.append(info);
            pw.append(log);
            pw.flush();
            pw.close();
            arq.flush();
            arq.close();
            if (configBkp != null) {
                configBkp.atualizarLogArquivo(info + log);
            }
        } catch (IOException ex) {
            gravarLogError(ex.getMessage(), null);
        }
    }

    public static void gravarLogError(String log, ConfigBkp configBkp) {
        dr = new DataReturn();
        try {
            info = "Error - ";
            info += dr.sysDataPath();
            info += " ";
            info += dr.sysHora();
            info += ":";
            info += dr.sysMin();
            info += ":";
            info += dr.sysSec();
            info += " - ";
            pw.append(info);
            pw.append(log);
            pw.println();
            pw.flush();
            arq.flush();
            if (configBkp != null) {
                configBkp.atualizarLogArquivo(info + log);
            }
        } catch (IOException ex) {
        } finally {
            try {
                arq.close();
            } catch (IOException ex) {
            }
            pw.close();
        }
    }

    public static void gravarLogInformation(String log, ConfigBkp configBkp) {
        dr = new DataReturn();
        try {
            info = "Infor - ";
            info += dr.sysDataPath();
            info += " ";
            info += dr.sysHora();
            info += ":";
            info += dr.sysMin();
            info += ":";
            info += dr.sysSec();
            info += " - ";
            pw.append(info);
            pw.append(log);
            pw.println();
            pw.flush();
            pw.close();
            arq.flush();
            arq.close();
            if (configBkp != null) {
                configBkp.atualizarLogArquivo(info + log);
            }
        } catch (IOException ex) {
            gravarLogError(ex.getMessage(), null);
        }
    }

    public static void gravarTodosLog(String log) {
        dr = new DataReturn();
        try {
            info = "error - ";
            info += dr.sysDataPath();
            info += " ";
            info += dr.sysHora();
            info += ":";
            info += dr.sysMin();
            info += ":";
            info += dr.sysSec();
            info += " - ";
            pw.append(info);
            pw.append(log);
            pw.println();
            pw.flush();
            pw.close();
            arq.flush();
            arq.close();
        } catch (IOException ex) {
        }
    }
}
