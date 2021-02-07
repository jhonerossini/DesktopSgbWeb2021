/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.modelo;

/**
 *
 * @author Rosemary
 */
public class Email {

    private String emailDest;
    private String nomeDest;
    private String emailRemet;
    private String nomeRemet;
    private String assunto;
    private String corpo;

    /**
     * @return the emailDest
     */
    public String getEmailDest() {
        return emailDest;
    }

    /**
     * @param emailDest the emailDest to set
     */
    public void setEmailDest(String emailDest) {
        this.emailDest = emailDest;
    }

    /**
     * @return the nomeDest
     */
    public String getNomeDest() {
        return nomeDest;
    }

    /**
     * @param nomeDest the nomeDest to set
     */
    public void setNomeDest(String nomeDest) {
        this.nomeDest = nomeDest;
    }

    /**
     * @return the emailRemet
     */
    public String getEmailRemet() {
        return emailRemet;
    }

    /**
     * @param emailRemet the emailRemet to set
     */
    public void setEmailRemet(String emailRemet) {
        this.emailRemet = emailRemet;
    }

    /**
     * @return the nomeRemet
     */
    public String getNomeRemet() {
        return nomeRemet;
    }

    /**
     * @param nomeRemet the nomeRemet to set
     */
    public void setNomeRemet(String nomeRemet) {
        this.nomeRemet = nomeRemet;
    }

    /**
     * @return the assunto
     */
    public String getAssunto() {
        return assunto;
    }

    /**
     * @param assunto the assunto to set
     */
    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    /**
     * @return the corpo
     */
    public String getCorpo() {
        return corpo;
    }

    /**
     * @param corpo the corpo to set
     */
    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }
}
