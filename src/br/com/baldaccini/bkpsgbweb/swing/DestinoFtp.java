/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.swing;

import br.com.baldaccini.bkpsgbweb.conexao.ftp.externo.FtpExterno;
import javax.swing.JOptionPane;
import javax.swing.JTree;

/**
 *
 * @author baldaccini
 */
public class DestinoFtp extends javax.swing.JFrame {

    private final ConfigBkp configBkp;
    private final FtpExterno ftpExterno;

    /**
     * Creates new form DestinoFtp
     *
     * @param configBkp
     */
    public DestinoFtp(ConfigBkp configBkp) {
        this.configBkp = configBkp;
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(new Icone().Icone());
        ftpExterno = new FtpExterno(configBkp, this);
    }

    public void atualizarLog(String log) {
        txaLog.append(log);
        txaLog.append("\n");
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
        trePastasServer = new javax.swing.JTree();
        lblDestino = new javax.swing.JLabel();
        btnPronto = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaLog = new javax.swing.JTextArea();
        lblTotalBytes = new javax.swing.JLabel();
        lblTotalBytesFlag = new javax.swing.JLabel();
        lblArqRaiz = new javax.swing.JLabel();
        lblArqRaizFlag = new javax.swing.JLabel();
        lblDestinoFlag = new javax.swing.JLabel();
        lblModo = new javax.swing.JLabel();
        lblModoFlag = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Destino FTP");

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        trePastasServer.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        trePastasServer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                trePastasServerMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trePastasServerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(trePastasServer);

        lblDestino.setText("Destino:");

        btnPronto.setBackground(new java.awt.Color(0, 51, 153));
        btnPronto.setForeground(new java.awt.Color(255, 255, 255));
        btnPronto.setText("Pronto");
        btnPronto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProntoActionPerformed(evt);
            }
        });

        txaLog.setEditable(false);
        txaLog.setColumns(20);
        txaLog.setRows(5);
        jScrollPane2.setViewportView(txaLog);

        lblTotalBytes.setText("Total bytes:");

        lblArqRaiz.setText("Raiz:");

        lblModo.setText("Modo:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDestino)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDestinoFlag)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnPronto))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblArqRaiz)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblArqRaizFlag))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTotalBytes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTotalBytesFlag, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblModo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblModoFlag)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblArqRaiz)
                    .addComponent(lblArqRaizFlag))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPronto)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDestino)
                        .addComponent(lblDestinoFlag)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModo)
                    .addComponent(lblModoFlag))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTotalBytes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTotalBytesFlag, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProntoActionPerformed
        if ("".equals(lblDestinoFlag.getText())) {
            JOptionPane.showMessageDialog(this, "Escolha um destino destino!");
        } else {
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnProntoActionPerformed

    private void trePastasServerMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trePastasServerMouseReleased
        String st = String.valueOf(trePastasServer.getSelectionPath());
        st = st.replace("[", "");
        st = st.replace("]", "");
        st = st.replace(",", "/");
        st = st.replace("raiz", "");
        st = st.trim();
        st = st.replace(" ", "");
        st = st.replace("//", "/");
        st = st.replace("//", "/");
        this.lblDestinoFlag.setText(st);
        this.configBkp.setDestino(st);
        //Esta pegando o caminho e agora falta salvar um arquivo com os dados de login do ftp
    }//GEN-LAST:event_trePastasServerMouseReleased

    private void trePastasServerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trePastasServerMouseClicked
        if ("".equals(lblDestinoFlag.getText())) {

        }
    }//GEN-LAST:event_trePastasServerMouseClicked

    public JTree getJTree() {
        return this.trePastasServer;
    }

    public FtpExterno getFtpExterno() {
        return this.ftpExterno;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton btnPronto;
    protected javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblArqRaiz;
    protected javax.swing.JLabel lblArqRaizFlag;
    private javax.swing.JLabel lblDestino;
    protected javax.swing.JLabel lblDestinoFlag;
    private javax.swing.JLabel lblModo;
    protected javax.swing.JLabel lblModoFlag;
    private javax.swing.JLabel lblTotalBytes;
    protected static javax.swing.JLabel lblTotalBytesFlag;
    private javax.swing.JTree trePastasServer;
    private javax.swing.JTextArea txaLog;
    // End of variables declaration//GEN-END:variables
    //TESTE DE ENVIO
    /*try {
         if (ftp.changeWorkingDirectory(lblArqRaizFlag.getText())) {
         txaLog.append("Alterado para o diretorio " + lblArqRaizFlag.getText());
         txaLog.append("\n");
         if (ftp.changeWorkingDirectory(local.getName())) {
         txaLog.append("Alterado para o diretorio " + local.getName());
         txaLog.append("\n");
         } else {
         if (ftp.makeDirectory(local.getName())) {
         txaLog.append("Diretorio " + local.getName() + " criado com sucesso!");
         txaLog.append("\n");
         txaLog.append("Acessando diretorio " + local.getName());
         if (ftp.changeWorkingDirectory(local.getName())) {
         txaLog.append("Alterado para o diretorio " + lblArqRaizFlag.getText());
         txaLog.append("\n");
         } else {
         txaLog.append("Não foi possível alterar para o diretorio " + local.getName());
         txaLog.append("\n");
         return;
         }
         } else {
         txaLog.append("Não foi possível alterar para o diretorio " + local.getName());
         txaLog.append("\n");
         return;
         }
         }
         } else {
         txaLog.append("Não foi possível alterar para o diretorio " + lblArqRaizFlag.getText());
         txaLog.append("\n");
         return;
         }
         } catch (IOException ex) {
         txaLog.append(ex.getMessage());
         txaLog.append("\n");
         }
         Runnable rn = new Runnable() {

         @Override
         public void run() {
         jButton1.setEnabled(false);
         txaLog.append("Transferindo...");
         txaLog.append("\n");
         pathLocalServidorFTP(local);
         txaLog.append("Transferencia realizada com sucesso!");
         txaLog.append("\n");
         trePastasServer.setModel(model);
         jScrollPane1.repaint();
         lblTotalBytesFlag.setText(conv.convertToStringRepresentation(total));
         txaLog.append("Lista finalizada!");
         txaLog.append("\n");
         jButton1.setEnabled(true);
         }
         };
         new Thread(rn).start();*/
}