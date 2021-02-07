/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.modelo;

import java.io.Serializable;

/**
 *
 * @author jhone
 */
public class DadosClienteServidor implements Serializable {
    
    private static final long serialVersionUID = 16081991L;

    private String nomeLogin;
    private String empresa;
    private String email;
    private String diaBackup;
    private String diaSemanaBackup;
    private String estadoBackup;
    private String nomeBackup;
    private String tipoBackup;

    /**
     * @return the nomeLogin
     */
    public String getNomeLogin() {
        return nomeLogin;
    }

    /**
     * @param nomeLogin the nomeLogin to set
     */
    public void setNomeLogin(String nomeLogin) {
        this.nomeLogin = nomeLogin;
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
     * @return the diaBackup
     */
    public String getDiaBackup() {
        return diaBackup;
    }

    /**
     * @param diaBackup the diaBackup to set
     */
    public void setDiaBackup(String diaBackup) {
        this.diaBackup = diaBackup;
    }

    /**
     * @return the diaSemanaBackup
     */
    public String getDiaSemanaBackup() {
        return diaSemanaBackup;
    }

    /**
     * @param diaSemanaBackup the diaSemanaBackup to set
     */
    public void setDiaSemanaBackup(String diaSemanaBackup) {
        this.diaSemanaBackup = diaSemanaBackup;
    }

    /**
     * @return the estadoBackup
     */
    public String getEstadoBackup() {
        return estadoBackup;
    }

    /**
     * @param estadoBackup the estadoBackup to set
     */
    public void setEstadoBackup(String estadoBackup) {
        this.estadoBackup = estadoBackup;
    }

    /**
     * @return the nomeBackup
     */
    public String getNomeBackup() {
        return nomeBackup;
    }

    /**
     * @param nomeBackup the nomeBackup to set
     */
    public void setNomeBackup(String nomeBackup) {
        this.nomeBackup = nomeBackup;
    }

    /**
     * @return the tipoBackup
     */
    public String getTipoBackup() {
        return tipoBackup;
    }

    /**
     * @param tipoBackup the tipoBackup to set
     */
    public void setTipoBackup(String tipoBackup) {
        this.tipoBackup = tipoBackup;
    }

}
