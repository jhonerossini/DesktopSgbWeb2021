/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.interfaces;

import br.com.baldaccini.bkpsgbweb.modelo.BackupArquivo;

/**
 *
 * @author jhone
 */
public interface INotificacoesArquivo {
    
    public void excluirThreadArquivo(int linha, boolean flagRemove);
    public void detalheArquivo(BackupArquivo backupArquivo);
    public void addLinhaTabelaArquivo();
    
    public void atualizarLogArquivo(String texto);
    public void atualizarLogServidor(String texto);
    
    public void situacaoBkpArquivo(int linha, String situacao);
    public void iniciarTabelaArquivo(String[] linhaTabela);
    public void alertaArquivo(String texto);

    public void atualizarLblQtdBkp(int i, String simpleName);

    public void iniciarThreadArquivo(int linhaTbl);

    public void pausarThreadArquivo(int linhaTbl);

    public void pararThreadArquivo(int linhaTbl);

    public void excluirThreadArquivo(int linhaTbl);
    
    public void setServidorBytesEnviados(String texto);
    public void setServidorBytesRecebido(String texto);
    public void setServidorParado(String data);
    public void setServidorTempoOnline(String data);
    public void setServidorIniciado(String data);
    public void setServidorFalhas(String texto);
    public void setServidorUsuariosConectados(String texto);
    public void setServidorOperando(String texto);
    public void setLblAvisoServidor(String texto);
}
