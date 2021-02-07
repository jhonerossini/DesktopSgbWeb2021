/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.manipularArquivos;

import br.com.baldaccini.bkpsgbweb.bkpbancodados.BkpBancoDados;
import br.com.baldaccini.bkpsgbweb.data.DataReturn;
import br.com.baldaccini.bkpsgbweb.modelo.BackupBancoDados;
import br.com.baldaccini.bkpsgbweb.zipunzip.CompactarPasta;

/**
 *
 * @author jhone
 */
public class ConsultaBackupBancoDados {

    private final BackupBancoDados backupBancoDados;

    private String horaMin;
    private String dataSistema;
    private String diaDaSemana;
    private final String[] diasSemanaBackup;
    private final DataReturn dr;
    private static final String _BKP_ = "_BKP_";

    public ConsultaBackupBancoDados(BackupBancoDados backupBancoDados) {
        this.backupBancoDados = backupBancoDados;
        dr = new DataReturn();
        diasSemanaBackup = new String[]{backupBancoDados.getSeg(), backupBancoDados.getTer(),
            backupBancoDados.getQua(), backupBancoDados.getQui(), backupBancoDados.getSex(), backupBancoDados.getSab(),
            backupBancoDados.getDom()};
    }

    public synchronized void iniciarBackupArquivo() {
        horaMin = dr.horaMin();
        dataSistema = dr.sysDataPath();
        diaDaSemana = dr.weekDay();
        if (backupBancoDados != null) {
            //verifica se é pra fazer backup semanal
            if (backupBancoDados.getFlagSemana().equals("true") && backupBancoDados.getFlagData().equals("true")
                    && dr.compararDataDepois(backupBancoDados.getDataBackupAgendado()) && isDiaSemana()
                    && horaMin.equals(backupBancoDados.getHoraMinuto())) {
                new BkpBancoDados().backupMysql(backupBancoDados.getNomeArquivo(), backupBancoDados.getUsuario(), String.valueOf(backupBancoDados.getSenha()), backupBancoDados.getServidor(), backupBancoDados.getPorta(), backupBancoDados.getDestino());
            } else if (backupBancoDados.getFlagData().equals("true") && dataSistema.equals(backupBancoDados.getDataBackupAgendado())
                    && horaMin.equals(backupBancoDados.getHoraMinuto())) {
                new BkpBancoDados().backupMysql(backupBancoDados.getNomeArquivo(), backupBancoDados.getUsuario(), String.valueOf(backupBancoDados.getSenha()), backupBancoDados.getServidor(), backupBancoDados.getPorta(), backupBancoDados.getDestino());
            } else if (backupBancoDados.getFlagSemana().equals("true") && horaMin.equals(backupBancoDados.getHoraMinuto())) {
                new BkpBancoDados().backupMysql(backupBancoDados.getNomeArquivo(), backupBancoDados.getUsuario(), String.valueOf(backupBancoDados.getSenha()), backupBancoDados.getServidor(), backupBancoDados.getPorta(), backupBancoDados.getDestino());
            }
        }
    }

    /**
     * Retorna true se o dia da semana for igual o dia da semana agendado, caso
     * contrario retorna false
     *
     * @return boolean
     */
    private boolean isDiaSemana() {
        boolean retorno = false;
        for (String s : diasSemanaBackup) {
            if (s.equals(diaDaSemana)) {
                retorno = true;
            }
        }
        return retorno;
    }
}
