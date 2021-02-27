/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.login;

import br.com.baldaccini.bkpsgbweb.log.GravarArquivoLog;
import br.com.baldaccini.bkpsgbweb.modelo.Login;
import br.com.baldaccini.bkpsgbweb.util.Criptografia;
import br.com.baldaccini.bkpsgbweb.xml.LoginXML;

/**
 *
 * @author JHONE
 */
public class LoginDev {

    private final Login login;

    public LoginDev() {
        login = new LoginXML().lerLogin();
    }

    public boolean realizarLogin(String nome, String senha) {
        if (nome != null && senha != null) {
            try {
                if (nome.equals(login.getUsuario()) && Criptografia.encrypt(senha, login.getUsuario()).equals(login.getPass())) {
                    return true;
                }
            } catch (Exception ex) {
                GravarArquivoLog.gravarTodosLog(ex.getMessage());
                return false;
            }
        }
        return false;
    }
}
