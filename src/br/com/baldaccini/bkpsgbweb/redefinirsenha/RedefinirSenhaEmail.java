/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.redefinirsenha;

import br.com.baldaccini.bkpsgbweb.log.GravarArquivoLog;
import br.com.baldaccini.bkpsgbweb.modelo.Email;
import br.com.baldaccini.bkpsgbweb.modelo.Login;
import br.com.baldaccini.bkpsgbweb.swing.ConfigBkp;
import br.com.baldaccini.bkpsgbweb.util.Criptografia;
import br.com.baldaccini.bkpsgbweb.util.Util;
import br.com.baldaccini.bkpsgbweb.xml.LoginXML;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import org.jdom2.JDOMException;

/**
 *
 * @author Rosemary
 */
public class RedefinirSenhaEmail {

    public void dadosEmail() throws JDOMException, IOException {
        LoginXML loginXML = new LoginXML();
        Login login = loginXML.lerLogin();
        Properties props = System.getProperties();
        String novaSenha="";
        
        List<String> lista = Util.lerArquivoTextoPorLinha("dadosEmail.txt");
        for(String s : lista){
            props.put(s.substring(0, s.indexOf("=")), s.substring(s.lastIndexOf("=")+1, s.length()));
        }
        
        if (!login.getEmail().equals("")) {
            Email enviarEmail = new Email();
            enviarEmail.setEmailDest(login.getEmail());
            enviarEmail.setEmailRemet("jhone.rossini@gmail.com");
            enviarEmail.setNomeDest(login.getUsuario());
            enviarEmail.setNomeRemet("Equipe da SGB");
            enviarEmail.setAssunto("Redefinição de senha SG-Backup.");
            novaSenha = new Util().gerarTextoRandom(login.getUsuario());
            enviarEmail.setCorpo("Esta é a nova senha: " + novaSenha);
            try {
                enviarEmailGmail(enviarEmail, props);
                loginXML.alterar(Criptografia.encrypt(novaSenha, login.getUsuario()));
            } catch (AddressException ex) {
                GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
                JOptionPane.showMessageDialog(null, "Endereço de e-mail digitado incorretamente!");
            }
        } else {
            GravarArquivoLog.gravarLogInformation("Email não cadastrado.", ConfigBkp.getInstance());
            JOptionPane.showMessageDialog(null, "Email não cadastrado.");
        }
    }

    private void enviarEmailGmail(Email email, Properties props) throws AddressException, UnknownHostException {
        MimeMessage message = new MimeMessage(Session.getInstance(props, new Autenticacao(props.getProperty("email"),props.getProperty("senha"))));
        try {
            try {
                message.setFrom(new InternetAddress(email.getEmailRemet(), email.getNomeRemet()));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getEmailDest(), email.getNomeDest()));
                message.setSubject(email.getAssunto());
                message.setContent(email.getCorpo(), "text/plain");
            } catch (UnsupportedEncodingException ex) {
                GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
                JOptionPane.showMessageDialog(null, "Erro na configuração do e-mail, tente novamente!");
            }
            Transport.send(message);
            JOptionPane.showMessageDialog(null, "E-mail enviado com sucesso!\nCaso não encontre o e-mail, verifique na caixa de span.");
        } catch (MessagingException ex) {
            GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
            JOptionPane.showMessageDialog(null, "Não foi possível enviar o e-mail, verifique sua conexão com a internet!");
        }
    }
}
