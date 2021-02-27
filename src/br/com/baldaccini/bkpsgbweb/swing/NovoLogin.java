package br.com.baldaccini.bkpsgbweb.swing;

import br.com.baldaccini.bkpsgbweb.log.GravarArquivoLog;
import br.com.baldaccini.bkpsgbweb.modelo.Login;
import br.com.baldaccini.bkpsgbweb.util.Criptografia;
import br.com.baldaccini.bkpsgbweb.xml.LoginXML;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author Rosemary
 */
public class NovoLogin extends javax.swing.JFrame {

    /**
     * Creates new form NovoLogin
     */
    public NovoLogin() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(new Icone().Icone());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_nome = new javax.swing.JLabel();
        lbl_senha = new javax.swing.JLabel();
        txt_nome = new javax.swing.JTextField();
        pass_senha = new javax.swing.JPasswordField();
        lbl_email = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        lbl_confSenha = new javax.swing.JLabel();
        pass_confSenha = new javax.swing.JPasswordField();
        btn_criarNovaConta = new javax.swing.JButton();
        lblEmpresa = new javax.swing.JLabel();
        txtEmpresa = new javax.swing.JTextField();
        lblFinalidadeBackup = new javax.swing.JLabel();
        txtFinalidadeBackup = new javax.swing.JTextField();
        btnSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastrar");

        lbl_nome.setText("Nome:");

        lbl_senha.setText("Nova senha:");

        lbl_email.setText("E-mail:");

        lbl_confSenha.setText("Confirma senha:");

        pass_confSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pass_confSenhaActionPerformed(evt);
            }
        });

        btn_criarNovaConta.setBackground(new java.awt.Color(0, 51, 153));
        btn_criarNovaConta.setForeground(new java.awt.Color(255, 255, 255));
        btn_criarNovaConta.setText("Criar nova conta");
        btn_criarNovaConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_criarNovaContaActionPerformed(evt);
            }
        });

        lblEmpresa.setText("Empresa:");

        txtEmpresa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        lblFinalidadeBackup.setText("Finalidade do Backup:");

        btnSair.setBackground(new java.awt.Color(0, 51, 153));
        btnSair.setForeground(new java.awt.Color(255, 255, 255));
        btnSair.setText("Sair");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pass_confSenha)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFinalidadeBackup)
                            .addComponent(lblEmpresa)
                            .addComponent(lbl_nome)
                            .addComponent(lbl_email)
                            .addComponent(lbl_confSenha)
                            .addComponent(lbl_senha))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSair)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                        .addComponent(btn_criarNovaConta))
                    .addComponent(pass_senha)
                    .addComponent(txt_email)
                    .addComponent(txt_nome)
                    .addComponent(txtFinalidadeBackup)
                    .addComponent(txtEmpresa))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEmpresa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmpresa)
                .addGap(4, 4, 4)
                .addComponent(lblFinalidadeBackup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFinalidadeBackup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_nome)
                .addGap(1, 1, 1)
                .addComponent(txt_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_email)
                .addGap(2, 2, 2)
                .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_senha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pass_senha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_confSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pass_confSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_criarNovaConta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_criarNovaContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_criarNovaContaActionPerformed
        if (txtEmpresa.getText().equals("") || txtFinalidadeBackup.getText().equals("") || txt_nome.getText().equals("") || txt_email.getText().equals("") || String.valueOf(pass_senha.getPassword()).equals("") || String.valueOf(pass_confSenha.getPassword()).equals("")) {
            JOptionPane.showMessageDialog(null, "Não pode haver campos vazio");
        } else {
            if (String.valueOf(pass_senha.getPassword()).equals(String.valueOf(pass_confSenha.getPassword()))) {
                Login login = new Login();
                login.setUsuario(txt_nome.getText());
                login.setEmail(txt_email.getText());
                try {
                    login.setPass(new String(Criptografia.encrypt(String.valueOf(pass_senha.getPassword()), txt_nome.getText())));
                } catch (Exception ex) {
                    GravarArquivoLog.gravarTodosLog(ex.getMessage());
                    return;
                }
                login.setEmpresa(txtEmpresa.getText());
                login.setFinalidadeBackup(txtFinalidadeBackup.getText());
                new LoginXML().criarLogin(login);
                this.dispose();
                new Principal().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Senha e nova senha não correspondem.");
            }
        }
    }//GEN-LAST:event_btn_criarNovaContaActionPerformed
    /**
     * @param args the command line arguments
     */
    private static void verifExistencia() {
        File file = new File("configPass.ini");
        if (file.exists()) {
            new Autenticar().setVisible(true);
        } else {
            new NovoLogin().setVisible(true);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                verifExistencia();
            }
        });
    }
    private void pass_confSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pass_confSenhaActionPerformed
    }//GEN-LAST:event_pass_confSenhaActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btn_criarNovaConta;
    private javax.swing.JLabel lblEmpresa;
    private javax.swing.JLabel lblFinalidadeBackup;
    private javax.swing.JLabel lbl_confSenha;
    private javax.swing.JLabel lbl_email;
    private javax.swing.JLabel lbl_nome;
    private javax.swing.JLabel lbl_senha;
    private javax.swing.JPasswordField pass_confSenha;
    private javax.swing.JPasswordField pass_senha;
    private javax.swing.JTextField txtEmpresa;
    private javax.swing.JTextField txtFinalidadeBackup;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_nome;
    // End of variables declaration//GEN-END:variables
}
