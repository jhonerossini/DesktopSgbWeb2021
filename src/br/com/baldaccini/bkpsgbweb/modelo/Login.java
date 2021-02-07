/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.modelo;

/**
 *
 * @author home
 */
public class Login {

    private String usuario;
    private String pass;
    private String email;
    private String empresa;
    private String finalidadeBackup;

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the senha to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the empresa
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the finalidadeBackup
     */
    public String getFinalidadeBackup() {
        return finalidadeBackup;
    }

    /**
     * @param finalidadeBackup the finalidadeBackup to set
     */
    public void setFinalidadeBackup(String finalidadeBackup) {
        this.finalidadeBackup = finalidadeBackup;
    }
}
