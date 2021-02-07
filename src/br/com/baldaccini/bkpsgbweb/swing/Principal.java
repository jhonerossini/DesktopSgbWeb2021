/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.swing;

/**
 *
 * @author Rosemary
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(new Icone().Icone());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_logoSaae = new javax.swing.JLabel();
        mnu_princ = new javax.swing.JMenuBar();
        mnu_config = new javax.swing.JMenu();
        mnu_config_backup = new javax.swing.JMenuItem();
        mniBancoDados = new javax.swing.JMenuItem();
        mnu_redefinirSenha = new javax.swing.JMenuItem();
        mnu_sair = new javax.swing.JMenu();
        mni_sair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema Gerenciador de Backup");

        mnu_config.setText("Configurar");

        mnu_config_backup.setText("Backup");
        mnu_config_backup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_config_backupActionPerformed(evt);
            }
        });
        mnu_config.add(mnu_config_backup);

        mniBancoDados.setText("Banco de Dados");
        mniBancoDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniBancoDadosActionPerformed(evt);
            }
        });
        mnu_config.add(mniBancoDados);

        mnu_redefinirSenha.setText("Redefinir senha");
        mnu_redefinirSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_redefinirSenhaActionPerformed(evt);
            }
        });
        mnu_config.add(mnu_redefinirSenha);

        mnu_princ.add(mnu_config);

        mnu_sair.setText("Sair");

        mni_sair.setText("Sair");
        mni_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_sairActionPerformed(evt);
            }
        });
        mnu_sair.add(mni_sair);

        mnu_princ.add(mnu_sair);

        setJMenuBar(mnu_princ);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(410, Short.MAX_VALUE)
                .addComponent(lbl_logoSaae)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_logoSaae)
                .addContainerGap(304, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mni_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_sairActionPerformed
        this.dispose();
    }//GEN-LAST:event_mni_sairActionPerformed

    private void mnu_config_backupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_config_backupActionPerformed
        new ConfigBkp().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mnu_config_backupActionPerformed

    private void mnu_redefinirSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_redefinirSenhaActionPerformed
        new RedefinirSenhaLocal().setVisible(true);
    }//GEN-LAST:event_mnu_redefinirSenhaActionPerformed

    private void mniBancoDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniBancoDadosActionPerformed
        new ConfigBanco().setVisible(true);
    }//GEN-LAST:event_mniBancoDadosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbl_logoSaae;
    private javax.swing.JMenuItem mniBancoDados;
    private javax.swing.JMenuItem mni_sair;
    private javax.swing.JMenu mnu_config;
    private javax.swing.JMenuItem mnu_config_backup;
    private javax.swing.JMenuBar mnu_princ;
    private javax.swing.JMenuItem mnu_redefinirSenha;
    private javax.swing.JMenu mnu_sair;
    // End of variables declaration//GEN-END:variables
}