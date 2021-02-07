/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.swing;

import br.com.baldaccini.bkpsgbweb.util.ConvExprTamArq;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.text.DefaultCaret;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 *
 * @author baldaccini
 */
public class Teste extends javax.swing.JFrame {

    private final FTPClient ftp;

    /**
     * Creates new form Teste
     *
     * @param fTPClient
     */
    public Teste(FTPClient fTPClient) {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.ftp = fTPClient;
        ((DefaultCaret) txaLog.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        ((DefaultCaret) txaListaServidor.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        carregarListaFtp();
    }

    private void carregarListaFtp() {
        if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
            txaLog.append("....: Conectado");
            txaLog.append("\n");
            ftp.enterLocalPassiveMode();
        }
        txaLog.append("....: " + ftp.getReplyString());
        txaLog.append("\n");
        txaLog.append("....: Porta " + ftp.getDefaultPort());
        txaLog.append("\n");

        try {
            txaLog.append("" + ftp.changeWorkingDirectory("/"));
            txaLog.append("\n");
            Runnable rn = new Runnable() {

                @Override
                public void run() {
                    listarDiretorioFtp();
                    lblTotalBytesBaixados.setText(conv.convertToStringRepresentation(total));
                    
                }
            };
            new Thread(rn).start();

        } catch (IOException ex) {
            txaLog.append(ex.getMessage());
            txaLog.append("\n");
        }
    }
    //FTPFile[] fTpFile;
    Map<String, String> listaDirCorrido = new HashMap<>();

    private void listarDiretorioFtp() {
        try {
            FTPFile[] fTpFile = ftp.listFiles();
            for (FTPFile f : fTpFile) {
                if (f.isDirectory()) {
                    listaDirCorrido.put(ftp.printWorkingDirectory(), "dir");
                    if (ftp.changeWorkingDirectory(f.getName())) {
                        listarDiretorioFtp();
                    }
                } else {
                    listaDirCorrido.put(ftp.printWorkingDirectory() + "/" + f.getName(), "arq");
                    txaListaServidor.append("dir - " + ftp.printWorkingDirectory());
                    txaListaServidor.append("\n");
                    txaListaServidor.append("   arq - " + f.getName());
                    txaListaServidor.append("\n");
                    tamanhoBytesCopiados(f.getSize());
                }
            }
            ftp.changeWorkingDirectory("../");
            txaLog.append(ftp.printWorkingDirectory());
            txaLog.append("\n");
        } catch (IOException ex) {
            txaLog.append(ex.getMessage());
            txaLog.append("\n");
        }
    }
    long total = 0l;
    ConvExprTamArq conv = new ConvExprTamArq();

    private void tamanhoBytesCopiados(long tam) {
        total += tam;
        lblTotalBytesBaixados.setText(String.valueOf(total));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txaListaServidor = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaLog = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        lblTotalBytesBaixados = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txaListaServidor.setColumns(20);
        txaListaServidor.setLineWrap(true);
        txaListaServidor.setRows(5);
        txaListaServidor.setWrapStyleWord(true);
        txaListaServidor.setCaretPosition(txaListaServidor.getDocument().getLength());
        jScrollPane1.setViewportView(txaListaServidor);

        txaLog.setColumns(20);
        txaLog.setRows(5);
        jScrollPane2.setViewportView(txaLog);

        jLabel1.setText("Total bytes:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalBytesBaixados)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblTotalBytesBaixados))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTotalBytesBaixados;
    private javax.swing.JTextArea txaListaServidor;
    private javax.swing.JTextArea txaLog;
    // End of variables declaration//GEN-END:variables
}
