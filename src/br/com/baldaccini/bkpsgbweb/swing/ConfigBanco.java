/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.swing;

import br.com.baldaccini.bkpsgbweb.bkpbancodados.BkpBancoDados;

/**
 *
 * @author Rosemary
 */
public class ConfigBanco extends javax.swing.JFrame {

    private static final String NUMEROS = "0123456789";

    /**
     * Creates new form ConfigBanco
     */
    public ConfigBanco() {
        initComponents();
        this.setIconImage(new Icone().Icone());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Backup do banco de dados");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRealizarBackup = new javax.swing.JPanel();
        lbl_banco = new javax.swing.JLabel();
        cboBanco = new javax.swing.JComboBox();
        chkEditarPorta = new javax.swing.JCheckBox();
        txtEditarPorta = new javax.swing.JTextField();
        lblServidor = new javax.swing.JLabel();
        txtServidor = new javax.swing.JTextField();
        lblNomeBanco = new javax.swing.JLabel();
        txtNomeBanco = new javax.swing.JTextField();
        lblSenhaBanco = new javax.swing.JLabel();
        btnIniciarBackupBanco = new javax.swing.JButton();
        pswSenhaBanco = new javax.swing.JPasswordField();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblNotificacao = new javax.swing.JLabel();
        pnlRestaurarBackup = new javax.swing.JPanel();
        lblRbBanco = new javax.swing.JLabel();
        cboRbBanco = new javax.swing.JComboBox();
        chkRbEditarPorta = new javax.swing.JCheckBox();
        txtRbEditarPorta = new javax.swing.JTextField();
        lblRbServidor = new javax.swing.JLabel();
        txtRbServidor = new javax.swing.JTextField();
        lblRbNomeBanco = new javax.swing.JLabel();
        txtRbNomeBanco = new javax.swing.JTextField();
        lblRbSenhaBanco = new javax.swing.JLabel();
        pswRbSenhaBanco = new javax.swing.JPasswordField();
        lblRbArquivo = new javax.swing.JLabel();
        txtRbArquivo = new javax.swing.JTextField();
        btnRbAbrir = new javax.swing.JButton();
        btnRbIniciarRestauracao = new javax.swing.JButton();
        lblRbUsuario = new javax.swing.JLabel();
        txtRbUsuario = new javax.swing.JTextField();
        lblRbNotificacao = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlRealizarBackup.setBorder(javax.swing.BorderFactory.createTitledBorder("Realizar Backup"));

        lbl_banco.setText("Banco");

        cboBanco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MySql", "PostgreSql", "ParadoxJDBC" }));

        chkEditarPorta.setText("Editar porta");
        chkEditarPorta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEditarPortaActionPerformed(evt);
            }
        });

        txtEditarPorta.setEnabled(false);
        txtEditarPorta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEditarPortaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEditarPortaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEditarPortaKeyTyped(evt);
            }
        });

        lblServidor.setText("Sevidor:");

        lblNomeBanco.setText("Nome do banco:");

        lblSenhaBanco.setText("Senha Banco:");

        btnIniciarBackupBanco.setBackground(new java.awt.Color(0, 51, 153));
        btnIniciarBackupBanco.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciarBackupBanco.setText("Iniciar Backup");
        btnIniciarBackupBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarBackupBancoActionPerformed(evt);
            }
        });

        lblUsuario.setText("Usuario:");

        javax.swing.GroupLayout pnlRealizarBackupLayout = new javax.swing.GroupLayout(pnlRealizarBackup);
        pnlRealizarBackup.setLayout(pnlRealizarBackupLayout);
        pnlRealizarBackupLayout.setHorizontalGroup(
            pnlRealizarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRealizarBackupLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRealizarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pswSenhaBanco)
                    .addGroup(pnlRealizarBackupLayout.createSequentialGroup()
                        .addGroup(pnlRealizarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblServidor)
                            .addComponent(lblNomeBanco)
                            .addComponent(lblSenhaBanco)
                            .addComponent(lblUsuario))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRealizarBackupLayout.createSequentialGroup()
                        .addGap(0, 8, Short.MAX_VALUE)
                        .addComponent(lblNotificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnIniciarBackupBanco))
                    .addComponent(txtUsuario)
                    .addComponent(txtNomeBanco)
                    .addComponent(txtServidor)
                    .addGroup(pnlRealizarBackupLayout.createSequentialGroup()
                        .addComponent(lbl_banco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkEditarPorta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEditarPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlRealizarBackupLayout.setVerticalGroup(
            pnlRealizarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRealizarBackupLayout.createSequentialGroup()
                .addGroup(pnlRealizarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_banco)
                    .addComponent(cboBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkEditarPorta)
                    .addComponent(txtEditarPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblServidor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNomeBanco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomeBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSenhaBanco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pswSenhaBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(pnlRealizarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnIniciarBackupBanco)
                    .addComponent(lblNotificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73))
        );

        pnlRestaurarBackup.setBorder(javax.swing.BorderFactory.createTitledBorder("Restaurar Backup"));

        lblRbBanco.setText("Banco:");

        cboRbBanco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MySql", "PostgreSql", "ParadoxJDBC" }));

        chkRbEditarPorta.setText("Editar porta");
        chkRbEditarPorta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRbEditarPortaActionPerformed(evt);
            }
        });

        txtRbEditarPorta.setEnabled(false);

        lblRbServidor.setText("Sevidor:");

        lblRbNomeBanco.setText("Nome do banco:");

        lblRbSenhaBanco.setText("Senha Banco:");

        lblRbArquivo.setText("Arquivo:");

        txtRbArquivo.setEnabled(false);

        btnRbAbrir.setBackground(new java.awt.Color(0, 51, 153));
        btnRbAbrir.setForeground(new java.awt.Color(255, 255, 255));
        btnRbAbrir.setText("Abrir");
        btnRbAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRbAbrirActionPerformed(evt);
            }
        });

        btnRbIniciarRestauracao.setBackground(new java.awt.Color(0, 51, 153));
        btnRbIniciarRestauracao.setForeground(new java.awt.Color(255, 255, 255));
        btnRbIniciarRestauracao.setText("Iniciar Restauração");
        btnRbIniciarRestauracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRbIniciarRestauracaoActionPerformed(evt);
            }
        });

        lblRbUsuario.setText("Usuario:");

        javax.swing.GroupLayout pnlRestaurarBackupLayout = new javax.swing.GroupLayout(pnlRestaurarBackup);
        pnlRestaurarBackup.setLayout(pnlRestaurarBackupLayout);
        pnlRestaurarBackupLayout.setHorizontalGroup(
            pnlRestaurarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRestaurarBackupLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRestaurarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRbNomeBanco)
                    .addComponent(txtRbServidor)
                    .addGroup(pnlRestaurarBackupLayout.createSequentialGroup()
                        .addGroup(pnlRestaurarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlRestaurarBackupLayout.createSequentialGroup()
                                .addComponent(lblRbBanco)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboRbBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                                .addComponent(chkRbEditarPorta))
                            .addComponent(lblRbServidor, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRbNomeBanco, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtRbEditarPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRestaurarBackupLayout.createSequentialGroup()
                        .addComponent(txtRbArquivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRbAbrir))
                    .addComponent(pswRbSenhaBanco)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRestaurarBackupLayout.createSequentialGroup()
                        .addComponent(lblRbNotificacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRbIniciarRestauracao))
                    .addComponent(txtRbUsuario)
                    .addGroup(pnlRestaurarBackupLayout.createSequentialGroup()
                        .addGroup(pnlRestaurarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRbSenhaBanco)
                            .addComponent(lblRbArquivo)
                            .addComponent(lblRbUsuario))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlRestaurarBackupLayout.setVerticalGroup(
            pnlRestaurarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRestaurarBackupLayout.createSequentialGroup()
                .addGroup(pnlRestaurarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRbBanco)
                    .addComponent(cboRbBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkRbEditarPorta)
                    .addComponent(txtRbEditarPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblRbServidor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRbServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRbNomeBanco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRbNomeBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRbUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRbSenhaBanco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pswRbSenhaBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRbArquivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRestaurarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRbArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRbAbrir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlRestaurarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRbIniciarRestauracao)
                    .addComponent(lblRbNotificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlRealizarBackup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRestaurarBackup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlRestaurarBackup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlRealizarBackup, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkEditarPortaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEditarPortaActionPerformed
        if (chkEditarPorta.isSelected()) {
            txtEditarPorta.setEnabled(true);
        } else {
            txtEditarPorta.setEnabled(false);
            txtEditarPorta.setText("");
        }
    }//GEN-LAST:event_chkEditarPortaActionPerformed

    private void btnIniciarBackupBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarBackupBancoActionPerformed
        String servidor = (txtServidor.getText().equals("") ? "localhost" : txtServidor.getText());
        String porta = "0";
        String usuario = "root";

        if (chkEditarPorta.isSelected()) {
            porta = txtEditarPorta.getText();
        } else {
            if (cboBanco.getSelectedIndex() == 0) {
                porta = "3306";
            } else if (cboBanco.getSelectedIndex() == 1) {
                porta = "3306";
            } else if (cboBanco.getSelectedIndex() == 2) {
                porta = "3306";
            }
        }
        if (!txtUsuario.getText().equals("")) {
            usuario = txtUsuario.getText();
        }
        if (!txtNomeBanco.getText().equals("")) {
            //lblNotificacao.setText(new BkpBancoDados().backupMysql(txtNomeBanco.getText(), usuario, String.valueOf(pswSenhaBanco.getPassword()), servidor, porta));
        } else {
            lblNotificacao.setText("Nome do Banco não pode ficar vazio!");
        }
    }//GEN-LAST:event_btnIniciarBackupBancoActionPerformed

    private void chkRbEditarPortaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRbEditarPortaActionPerformed
        if (chkRbEditarPorta.isSelected()) {
            txtRbEditarPorta.setEnabled(true);
        } else {
            txtRbEditarPorta.setEnabled(false);
            txtRbEditarPorta.setText("");
        }
    }//GEN-LAST:event_chkRbEditarPortaActionPerformed

    private void txtEditarPortaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEditarPortaKeyReleased
        if (!txtEditarPorta.getText().equals("")) {
            if (Integer.parseInt(txtEditarPorta.getText()) > 65536) {
                txtEditarPorta.setText("65536");
            }
        }
    }//GEN-LAST:event_txtEditarPortaKeyReleased

    private void txtEditarPortaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEditarPortaKeyTyped
        if (!NUMEROS.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
        if (txtEditarPorta.getText().length() >= 5) {
            evt.consume();
        }
    }//GEN-LAST:event_txtEditarPortaKeyTyped

    private void txtEditarPortaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEditarPortaKeyPressed
        
    }//GEN-LAST:event_txtEditarPortaKeyPressed

    private void btnRbAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRbAbrirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRbAbrirActionPerformed

    private void btnRbIniciarRestauracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRbIniciarRestauracaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRbIniciarRestauracaoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarBackupBanco;
    private javax.swing.JButton btnRbAbrir;
    private javax.swing.JButton btnRbIniciarRestauracao;
    private javax.swing.JComboBox cboBanco;
    private javax.swing.JComboBox cboRbBanco;
    private javax.swing.JCheckBox chkEditarPorta;
    private javax.swing.JCheckBox chkRbEditarPorta;
    private javax.swing.JLabel lblNomeBanco;
    private javax.swing.JLabel lblNotificacao;
    private javax.swing.JLabel lblRbArquivo;
    private javax.swing.JLabel lblRbBanco;
    private javax.swing.JLabel lblRbNomeBanco;
    private javax.swing.JLabel lblRbNotificacao;
    private javax.swing.JLabel lblRbSenhaBanco;
    private javax.swing.JLabel lblRbServidor;
    private javax.swing.JLabel lblRbUsuario;
    private javax.swing.JLabel lblSenhaBanco;
    private javax.swing.JLabel lblServidor;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lbl_banco;
    private javax.swing.JPanel pnlRealizarBackup;
    private javax.swing.JPanel pnlRestaurarBackup;
    private javax.swing.JPasswordField pswRbSenhaBanco;
    private javax.swing.JPasswordField pswSenhaBanco;
    private javax.swing.JTextField txtEditarPorta;
    private javax.swing.JTextField txtNomeBanco;
    private javax.swing.JTextField txtRbArquivo;
    private javax.swing.JTextField txtRbEditarPorta;
    private javax.swing.JTextField txtRbNomeBanco;
    private javax.swing.JTextField txtRbServidor;
    private javax.swing.JTextField txtRbUsuario;
    private javax.swing.JTextField txtServidor;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
