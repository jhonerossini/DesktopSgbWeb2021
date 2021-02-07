/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.redefinirsenha;

import br.com.baldaccini.bkpsgbweb.log.GravarArquivoLog;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import br.com.baldaccini.bkpsgbweb.modelo.Email;
import br.com.baldaccini.bkpsgbweb.modelo.Login;
import br.com.baldaccini.bkpsgbweb.util.Criptografia;
import br.com.baldaccini.bkpsgbweb.xml.LoginXML;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import org.jdom2.JDOMException;

/**
 *
 * @author Rosemary
 */
public class RedefinirSenhaEmail {

    public void dadosEmail() throws JDOMException, IOException {
        Login login = new LoginXML().lerLogin();
        if (!login.getEmail().equals("")) {
            Email enviarEmail = new Email();
            enviarEmail.setEmailDest(login.getEmail());
            enviarEmail.setEmailRemet("jhone.rossini@gmail.com");
            enviarEmail.setNomeDest(login.getUsuario());
            enviarEmail.setNomeRemet("Equipe da SGB");
            enviarEmail.setAssunto("Redefinição de senha.");
            enviarEmail.setCorpo("Esta é a sua senha atual: " + Criptografia.decrypt(login.getPass()));
            try {
                enviarEmailGmail(enviarEmail);
            } catch (AddressException ex) {
                GravarArquivoLog.gravarTodosLog(ex.getMessage());
                JOptionPane.showMessageDialog(null, "Endereço de e-mail digitado incorretamente!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Email não cadastrado.");
        }
    }

    private void enviarEmail(Email email) throws AddressException {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.live.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        MimeMessage message = new MimeMessage(Session.getInstance(props, new Autenticacao()));
        try {
            try {
                message.setFrom(new InternetAddress(email.getEmailRemet(), email.getNomeRemet()));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getEmailDest(), email.getNomeDest()));
                message.setSubject(email.getAssunto());
                message.setContent(email.getCorpo(), "text/plain");
            } catch (UnsupportedEncodingException ex) {
                GravarArquivoLog.gravarTodosLog(ex.getMessage());
                JOptionPane.showMessageDialog(null, "Erro na configuração do e-mail, tente novamente!");
            }
            Transport.send(message);
            JOptionPane.showMessageDialog(null, "E-mail enviado com sucesso!\nCaso não encontre o e-mail, verifique na caixa de span.");
        } catch (MessagingException ex) {
            GravarArquivoLog.gravarTodosLog(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Não foi possível enviar o e-mail, verifique sua conexão com a internet!");
        }
    }

    private void enviarEmailGmail(Email email) throws AddressException, UnknownHostException {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "imap");
        props.put("mail.imap.host", "imap.gmail.com");
        props.put("mail.imap.starttls.enable", "true");
        props.put("mail.imap.auth", "true");
        
        MimeMessage message = new MimeMessage(Session.getInstance(props, new Autenticacao()));
        try {
            try {
                message.setFrom(new InternetAddress(email.getEmailRemet(), email.getNomeRemet()));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getEmailDest(), email.getNomeDest()));
                message.setSubject(email.getAssunto());
                message.setContent(email.getCorpo(), "text/plain");
            } catch (UnsupportedEncodingException ex) {
                GravarArquivoLog.gravarTodosLog(ex.getMessage());
                JOptionPane.showMessageDialog(null, "Erro na configuração do e-mail, tente novamente!");
            }
            Transport.send(message);
            JOptionPane.showMessageDialog(null, "E-mail enviado com sucesso!\nCaso não encontre o e-mail, verifique na caixa de span.");
        } catch (MessagingException ex) {
            GravarArquivoLog.gravarTodosLog(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Não foi possível enviar o e-mail, verifique sua conexão com a internet!");
        }
    }
}
