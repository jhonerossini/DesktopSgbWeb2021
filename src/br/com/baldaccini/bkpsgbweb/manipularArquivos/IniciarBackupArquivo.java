/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.manipularArquivos;

import br.com.baldaccini.bkpsgbweb.log.GravarArquivoLog;
import br.com.baldaccini.bkpsgbweb.modelo.BackupArquivo;
import br.com.baldaccini.bkpsgbweb.swing.ConfigBkp;

/**
 *
 * @author JHONE
 */
public class IniciarBackupArquivo implements Runnable {

    private boolean startStop = true;
    private boolean pause = false;
    private BackupArquivo backupArquivo;
    private ConsultaBackupArquivo consulta;

    public IniciarBackupArquivo(BackupArquivo backupArquivo) {
        this.backupArquivo = backupArquivo;
    }

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        consulta = new ConsultaBackupArquivo(getBackupArquivo());
        while (isStartStop()) {
            try {
                if (isPause()) {
                    synchronized (this) {
                        wait();
                    }
                }
                consulta.iniciarBackupArquivo(false);
                synchronized (this) {
                    wait((59 * 1000));
                }
            } catch (InterruptedException ex) {
                GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
            }
        }
        GravarArquivoLog.gravarLogInformation("Vereficação de backup parada", ConfigBkp.getInstance());
    }

    public void imediato() {
        consulta.iniciarBackupArquivo(true);
    }

    public boolean isStartStop() {
        return startStop;
    }

    /**
     * @return the backupArquivo
     */
    public BackupArquivo getBackupArquivo() {
        return backupArquivo;
    }

    /**
     * @return the pause
     */
    public boolean isPause() {
        return pause;
    }

    /**
     */
    public void pause() {
        this.pause = true;
    }

    public void continuar() {
        this.pause = false;
        synchronized (this) {
            notify();
        }
    }

    /**
     */
    public void iniciar() {
        this.startStop = true;
    }

    /**
     */
    public void parar() {
        this.startStop = false;
        synchronized (this) {
            notify();
        }
    }
}
