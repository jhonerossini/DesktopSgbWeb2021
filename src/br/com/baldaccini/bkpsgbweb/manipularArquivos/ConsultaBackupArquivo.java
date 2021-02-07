/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.manipularArquivos;

import br.com.baldaccini.bkpsgbweb.data.DataReturn;
import br.com.baldaccini.bkpsgbweb.modelo.BackupArquivo;
import br.com.baldaccini.bkpsgbweb.zipunzip.CompactarPasta;
import java.io.File;

/**
 *
 * @author Rosemary
 */
public class ConsultaBackupArquivo {

    private BackupArquivo backup;
    private ManipularArquivos ma;
    private final CompactarPasta cp;
    private final DataReturn dr;

    // 2015458828656 - 
    // 1056 - fixo 2015458845862 - 2015858853157
    // 08008882373 - setor de cobrança da TIM 2015458899685
    private final String[] diasSemanaBackup;
    private static final String BKP = "_BKP_";

    public ConsultaBackupArquivo(BackupArquivo backup) {
        this.backup = backup;
        dr = new DataReturn();
        ma = new ManipularArquivos(backup.getNome() + BKP + backup.getDataBackupAgendado() + "_" + backup.getHoraMin());
        cp = new CompactarPasta(backup.getNome() + BKP + backup.getDataBackupAgendado() + "_" + backup.getHoraMin());
        diasSemanaBackup = new String[]{backup.getSeg(), backup.getTer(),
            backup.getQua(), backup.getQui(), backup.getSex(), backup.getSab(),
            backup.getDom()};
    }

    public synchronized void iniciarBackupArquivo(boolean imediato) {
        if (imediato) {
            if (backup.getBkpIncremental()) {
                ma.copiarArquivosAlteradosNovos(new File(backup.getLocal()).toPath(), new File(backup.getDestino() + backup.getNome() + BKP + backup.getDataBackupAgendado() + "_" + backup.getHoraMin().replace(":", "-")).toPath());
                //verifica se é backup normal
            } else if (backup.getNormal()) {
                ma.copiarArquivosNovos(new File(backup.getLocal()).toPath(), new File(backup.getDestino() + backup.getNome() + BKP + backup.getDataBackupAgendado() + "_" + backup.getHoraMin().replace(":", "-")).toPath());
            }
            //verifica se é para compactar
            if (backup.getCompactar()) {
                cp.zipar(backup.getLocal(), backup.getDestino() + backup.getNome() + BKP + backup.getDataBackupAgendado() + "_" + backup.getHoraMin().replace(":", "-") + ".zip");
            }
            return;
        }
        if (backup != null) {
            if (backup.getFlagData()) {
                if (backup.getDataBackupAgendado().equals(dr.sysDataPath())) {
                    if (backup.getHoraMin().equals(dr.horaMin())) {
                        if (backup.getBkpIncremental()) {
                            ma.copiarArquivosAlteradosNovos(new File(backup.getLocal()).toPath(), new File(backup.getDestino() + backup.getNome() + BKP + backup.getDataBackupAgendado() + "_" + backup.getHoraMin().replace(":", "-")).toPath());
                            //verifica se é backup normal
                        } else if (backup.getNormal()) {
                            ma.copiarArquivosNovos(new File(backup.getLocal()).toPath(), new File(backup.getDestino() + backup.getNome() + BKP + backup.getDataBackupAgendado() + "_" + backup.getHoraMin().replace(":", "-")).toPath());
                        }
                        //verifica se é para compactar
                        if (backup.getCompactar()) {
                            cp.zipar(backup.getLocal(), backup.getDestino() + backup.getNome() + BKP + backup.getDataBackupAgendado() + "_" + backup.getHoraMin().replace(":", "-") + ".zip");
                        }
                    }
                } else if (backup.getFlagSemana()) {
                    if (dr.compararDataAntes(backup.getDataBackupAgendado())) {
                        if (isDiaSemana()) {
                            if (backup.getHoraMin().equals(dr.horaMin())) {
                                if (backup.getBkpIncremental()) {
                                    ma.copiarArquivosAlteradosNovos(new File(backup.getLocal()).toPath(), new File(backup.getDestino() + backup.getNome() + BKP + backup.getDataBackupAgendado() + "_" + backup.getHoraMin().replace(":", "-")).toPath());
                                    //verifica se é backup normal
                                } else if (backup.getNormal()) {
                                    ma.copiarArquivosNovos(new File(backup.getLocal()).toPath(), new File(backup.getDestino() + backup.getNome() + BKP + backup.getDataBackupAgendado() + "_" + backup.getHoraMin().replace(":", "-")).toPath());
                                }
                                //verifica se é para compactar
                                if (backup.getCompactar()) {
                                    cp.zipar(backup.getLocal(), backup.getDestino() + backup.getNome() + BKP + backup.getDataBackupAgendado() + "_" + backup.getHoraMin().replace(":", "-") + ".zip");
                                }
                            }
                        }
                    }
                }
            } else if (backup.getFlagSemana()) {
                //verifica o dia da semana
                if (isDiaSemana()) {
                    //verifica a hora e minuto
                    if (dr.horaMin().equals(backup.getHoraMin())) {
                        //vefifica se o backup vai ser incremental
                        if (backup.getBkpIncremental()) {
                            ma.copiarArquivosAlteradosNovos(new File(backup.getLocal()).toPath(), new File(backup.getDestino() + backup.getNome() + BKP + backup.getDataBackupAgendado() + "_" + backup.getHoraMin().replace(":", "-")).toPath());
                            //verifica se é backup normal
                        } else if (backup.getNormal()) {
                            ma.copiarArquivosNovos(new File(backup.getLocal()).toPath(), new File(backup.getDestino() + backup.getNome() + BKP + backup.getDataBackupAgendado() + "_" + backup.getHoraMin().replace(":", "-")).toPath());
                        }
                        //verifica se é para compactar
                        if (backup.getCompactar()) {
                            cp.zipar(backup.getLocal(), backup.getDestino() + backup.getNome() + BKP + backup.getDataBackupAgendado() + "_" + backup.getHoraMin().replace(":", "-") + ".zip");
                        }
                    }
                }
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
        for (String s : diasSemanaBackup) {
            if (s.equals(dr.weekDay())) {
                return true;
            }
        }
        return false;
    }
}
