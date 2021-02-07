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
public class GravarBackupBancoLog {

    private static DataReturn dr;
    private static FileWriter arq;
    private static PrintWriter sb;
    private static String info;
    private FileReader fr;
    private BufferedReader br;
    private StringBuilder texto;

    public GravarBackupBancoLog() {
        dr = new DataReturn();
    }

    public synchronized StringBuilder carregarArquivoTextoLog() {
        try {
            fr = new FileReader("bkpBancoLog.txt");
            if (fr.ready()) {
                br = new BufferedReader(fr);
                texto = new StringBuilder();
                try {
                    while (br.ready()) {
                        texto.append(br.readLine());
                        texto.append("\n");
                    }
                } catch (IOException ex) {
                    gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
                }
            }
            br.close();
        } catch (FileNotFoundException ex) {
            gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
        } catch (IOException ex) {
            gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
        } finally {
            fr = null;
        }
        return texto;
    }

    public synchronized static void gravarLogWarning(String log, ConfigBkp configBkp) {
        try {
            arq = new FileWriter("bkpBancoLog.txt", true);
            sb = new PrintWriter(arq);
            info = "Warni - ";
            info += dr.sysDataPath();
            info += " ";
            info += dr.sysHora();
            info += ":";
            info += dr.sysMin();
            info += ":";
            info += dr.sysSec();
            info += " - ";
            sb.append(info);
            sb.append(log);
            sb.println();
            sb.flush();
            arq.flush();
            arq.close();
            if (configBkp != null) {
                configBkp.atualizarLogBancoDados(info + log);
            }
        } catch (IOException ex) {
            gravarLogError(ex.getMessage(), configBkp);
        } finally {
            arq = null;
            sb = null;
            info = null;
        }
    }

    public synchronized static void gravarLogError(String log, ConfigBkp configBkp) {
        try {
            arq = new FileWriter("bkpBancoLog.txt", true);
            sb = new PrintWriter(arq);
            info = "Error - ";
            info += dr.sysDataPath();
            info += " ";
            info += dr.sysHora();
            info += ":";
            info += dr.sysMin();
            info += ":";
            info += dr.sysSec();
            info += " - ";
            sb.append(info);
            sb.append(log);
            sb.println();
            sb.flush();
            arq.flush();
            arq.close();
            if (configBkp != null) {
                configBkp.atualizarLogBancoDados(info + log);
            }
        } catch (IOException ex) {
        } finally {
            arq = null;
            sb = null;
            info = null;
        }
    }

    public synchronized static void gravarLogInformation(String log, ConfigBkp configBkp) {
        try {
            arq = new FileWriter("bkpBancoLog.txt", true);
            sb = new PrintWriter(arq);
            info = "Infor - ";
            info += dr.sysDataPath();
            info += " ";
            info += dr.sysHora();
            info += ":";
            info += dr.sysMin();
            info += ":";
            info += dr.sysSec();
            info += " - ";
            sb.append(info);
            sb.append(log);
            sb.println();
            sb.flush();
            arq.flush();
            arq.close();
            if (configBkp != null) {
                configBkp.atualizarLogBancoDados(info + log);
            }
        } catch (IOException ex) {
            gravarLogError(ex.getMessage(), configBkp);
        } finally {
            arq = null;
            sb = null;
            info = null;
        }
    }
}
