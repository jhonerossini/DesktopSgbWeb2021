/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.redefinirsenha;

import javax.mail.*;

/**
 *
 * @author Rosemary
 */
public class Autenticacao extends Authenticator {
    
    private String email;
    private String pass;
    
    public Autenticacao(String email, String pass){
        this.email = email;
        this.pass = pass;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        //return new PasswordAuthentication("jhone.rossini@outlook.com", "Avaj1991@1");
        return new PasswordAuthentication(email, pass);
    }
}