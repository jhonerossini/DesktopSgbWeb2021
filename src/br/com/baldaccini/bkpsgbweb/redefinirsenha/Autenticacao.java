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

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        //return new PasswordAuthentication("jhone.rossini@outlook.com", "Avaj1991@1");
        return new PasswordAuthentication("jhone.rossini@gmail.com", "avaj1608");
    }
}