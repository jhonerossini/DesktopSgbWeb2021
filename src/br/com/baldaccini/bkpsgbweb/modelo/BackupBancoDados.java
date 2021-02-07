/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.modelo;

/**
 *
 * @author JHONE
 */
public class BackupBancoDados {

    private String nomeArquivo;
    private String pathArquivo;
    private String destino;
    private String descricao;
    private String porta;
    private String servidor;
    private String banco;
    private String usuario;
    private String senha;
    private String dataUltimoBackup;
    private String dataBackupAgendado;
    private String dataCriacaoBackup;
    private String seg;
    private String ter;
    private String qua;
    private String qui;
    private String sex;
    private String sab;
    private String dom;
    private String horaMinuto;
    private String flagData;
    private String flagSemana;

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
     * @return the servidor
     */
    public String getServidor() {
        return servidor;
    }

    /**
     * @param servidor the servidor to set
     */
    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    /**
     * @return the nomeBanco
     */
    public String getBanco() {
        return banco;
    }

    /**
     * @param nomeBanco the nomeBanco to set
     */
    public void setBanco(String banco) {
        this.banco = banco;
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
     * @return the dataUltimoBackup
     */
    public String getDataUltimoBackup() {
        return dataUltimoBackup;
    }

    /**
     * @param dataUltimoBackup the dataUltimoBackup to set
     */
    public void setDataUltimoBackup(String dataUltimoBackup) {
        this.dataUltimoBackup = dataUltimoBackup;
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
     * @return the nomeArquivo
     */
    public String getNomeArquivo() {
        return nomeArquivo;
    }

    /**
     * @param nomeArquivo the nomeArquivo to set
     */
    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
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
     * @return the flagData
     */
    public String getFlagData() {
        return flagData;
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
    public String getFlagSemana() {
        return flagSemana;
    }

    /**
     * @param flagSemana the flagSemana to set
     */
    public void setFlagSemana(String flagSemana) {
        this.flagSemana = flagSemana;
    }

    /**
     * @return the dataCriacaoBackup
     */
    public String getDataCriacaoBackup() {
        return dataCriacaoBackup;
    }

    /**
     * @param dataCriacaoBackup the dataCriacaoBackup to set
     */
    public void setDataCriacaoBackup(String dataCriacaoBackup) {
        this.dataCriacaoBackup = dataCriacaoBackup;
    }

    /**
     * @return the pathArquivo
     */
    public String getPathArquivo() {
        return pathArquivo;
    }

    /**
     * @param pathArquivo the pathArquivo to set
     */
    public void setPathArquivo(String pathArquivo) {
        this.pathArquivo = pathArquivo;
    }

    /**
     * @return the horaMinuto
     */
    public String getHoraMinuto() {
        return horaMinuto;
    }

    /**
     * @param horaMinuto the horaMinuto to set
     */
    public void setHoraMinuto(String horaMinuto) {
        this.horaMinuto = horaMinuto;
    }
}
