/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.log;

import br.com.baldaccini.bkpsgbweb.data.DataReturn;
import br.com.baldaccini.bkpsgbweb.swing.ConfigBkp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author jhone
 */
public class GravarServidorLog {
    private File f;
    private FileReader fr;
    private BufferedReader br;
    private DataReturn dr;
    private FileWriter arq;
    private String info;
    private PrintWriter pw;
    
    public GravarServidorLog(){
        dr = new DataReturn();
    }

    public StringBuilder carregarArquivoTextoLog() {
        f = new File("servidorLog.txt");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException ex) {
                GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
            }
        }
        StringBuilder texto = null;
        try {
            fr = new FileReader("servidorLog.txt");

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

    public void gravarLogWarning(String log, ConfigBkp configBkp) {
        try {
            arq = new FileWriter("servidorLog.txt", true);
            pw = new PrintWriter(arq);

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
            arq.flush();
            pw.close();
            arq.close();
            if (configBkp != null) {
                configBkp.atualizarLogServidor(info + log);
            }
        } catch (IOException ex) {
            gravarLogError(ex.getMessage(), null);
        }
    }

    public void gravarLogError(String log, ConfigBkp configBkp) {

        try {
            arq = new FileWriter("servidorLog.txt", true);
            pw = new PrintWriter(arq);

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
                configBkp.atualizarLogServidor(info + log);
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

    public void gravarLogInformation(String log, ConfigBkp configBkp) {
        try {
            arq = new FileWriter("servidorLog.txt", true);
            pw = new PrintWriter(arq);

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
            arq.flush();
            pw.close();
            arq.close();
            if (configBkp != null) {
                configBkp.atualizarLogServidor(info + log);
            }
        } catch (IOException ex) {
            gravarLogError(ex.getMessage(), null);
        }
    }
}
