/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.modelo;

import java.io.Serializable;

/**
 *
 * @author Rosemary
 */
public class BackupArquivo implements Serializable {

    private static final long serialVersionUID = 16081991L;

    private String identificador;
    private String nome;
    private String descricao;
    private String dataCriacao;
    private String dataBackupAgendado;
    private String local;
    private String destino;
    private String seg;
    private String ter;
    private String qua;
    private String qui;
    private String sex;
    private String sab;
    private String dom;
    private String horaMin;
    private String compactar;
    private String excluir;
    private String bkpIncremental;
    private String normal;
    private String flagData;
    private String flagSemana;

    private String id;
    private String host;
    private String porta;
    private String usuario;
    private String senha;
    private String modoConexao;
    private String ftpDestino;

    /**
     * @return the dataCriacao
     */
    public String getDataCriacao() {
        return dataCriacao;
    }

    /**
     * @param dataCriacao the dataCriacao to set
     */
    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    /**
     * @return the local
     */
    public String getLocal() {
        return local;
    }

    /**
     * @param local the local to set
     */
    public void setLocal(String local) {
        this.local = local;
    }

    /**
     * @return the destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * @return the seg
     */
    public String getSeg() {
        return seg;
    }

    /**
     * @param seg the seg to set
     */
    public void setSeg(String seg) {
        this.seg = seg;
    }

    /**
     * @return the ter
     */
    public String getTer() {
        return ter;
    }

    /**
     * @param ter the ter to set
     */
    public void setTer(String ter) {
        this.ter = ter;
    }

    /**
     * @return the qua
     */
    public String getQua() {
        return qua;
    }

    /**
     * @param qua the qua to set
     */
    public void setQua(String qua) {
        this.qua = qua;
    }

    /**
     * @return the qui
     */
    public String getQui() {
        return qui;
    }

    /**
     * @param qui the qui to set
     */
    public void setQui(String qui) {
        this.qui = qui;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the sab
     */
    public String getSab() {
        return sab;
    }

    /**
     * @param sab the sab to set
     */
    public void setSab(String sab) {
        this.sab = sab;
    }

    /**
     * @return the dom
     */
    public String getDom() {
        return dom;
    }

    /**
     * @param dom the dom to set
     */
    public void setDom(String dom) {
        this.dom = dom;
    }

    /**
     * @return the compactar
     */
    public boolean getCompactar() {
        return compactar.equals("true");
    }

    /**
     * @param compactar the compactar to set
     */
    public void setCompactar(String compactar) {
        this.compactar = compactar;
    }

    /**
     * @return the excluir
     */
    public String getExcluir() {
        return excluir;
    }

    /**
     * @param excluir the excluir to set
     */
    public void setExcluir(String excluir) {
        this.excluir = excluir;
    }

    /**
     * @return the bkpIncremental
     */
    public boolean getBkpIncremental() {
        return bkpIncremental.equals("true");
    }

    /**
     * @param bkpIncremental the bkpIncremental to set
     */
    public void setBkpIncremental(String bkpIncremental) {
        this.bkpIncremental = bkpIncremental;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the identificador
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    /**
     * @return the flagData
     */
    public boolean getFlagData() {
        return flagData.equals("true");
    }

    /**
     * @param flagData the flagData to set
     */
    public void setFlagData(String flagData) {
        this.flagData = flagData;
    }

    /**
     * @return the flagSemana
     */
    public boolean getFlagSemana() {
        return flagSemana.equals("true");
    }

    /**
     * @param flagSemana the flagSemana to set
     */
    public void setFlagSemana(String flagSemana) {
        this.flagSemana = flagSemana;
    }

    /**
     * @return the dataBackupAgendado
     */
    public String getDataBackupAgendado() {
        return dataBackupAgendado;
    }

    /**
     * @param dataBackupAgendado the dataBackupAgendado to set
     */
    public void setDataBackupAgendado(String dataBackupAgendado) {
        this.dataBackupAgendado = dataBackupAgendado;
    }

    /**
     * @return the horaMin
     */
    public String getHoraMin() {
        return horaMin;
    }

    /**
     * @param horaMin the horaMin to set
     */
    public void setHoraMin(String horaMin) {
        this.horaMin = horaMin;
    }

    /**
     * @return the normal
     */
    public boolean getNormal() {
        return normal.equals("true");
    }

    /**
     * @param normal the normal to set
     */
    public void setNormal(String normal) {
        this.normal = normal;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the porta
     */
    public String getPorta() {
        return porta;
    }

    /**
     * @param porta the porta to set
     */
    public void setPorta(String porta) {
        this.porta = porta;
    }

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
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the modoConexao
     */
    public String getModoConexao() {
        return modoConexao;
    }

    /**
     * @param modoConexao the modoConexao to set
     */
    public void setModoConexao(String modoConexao) {
        this.modoConexao = modoConexao;
    }

    /**
     * @return the ftpDestino
     */
    public String getFtpDestino() {
        return ftpDestino;
    }

    /**
     * @param ftpDestino the ftpDestino to set
     */
    public void setFtpDestino(String ftpDestino) {
        this.ftpDestino = ftpDestino;
    }
}
