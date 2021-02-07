/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.interfaces;

import br.com.baldaccini.bkpsgbweb.swing.DestinoFtp;

/**
 *
 * @author jhone
 */
public interface INotificacoesBancoDados {
    
    public void excluirThreadBancoDados(int linha, boolean flagRemove);
    
    public void detalheBancoDados();
    
    public void atualizarLogBancoDados(String texto);
    
    public void situacaoTabelaBancoDados(int linha, String situacao);
    
    public void iniciarTabelaBancoDados(String[] linhaTabela);
    
    public void alertaBancoDados(String texto);
}
