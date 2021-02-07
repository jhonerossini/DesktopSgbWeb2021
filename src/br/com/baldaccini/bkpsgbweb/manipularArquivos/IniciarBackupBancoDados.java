/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.manipularArquivos;

import br.com.baldaccini.bkpsgbweb.log.GravarBackupBancoLog;
import br.com.baldaccini.bkpsgbweb.modelo.BackupBancoDados;
import br.com.baldaccini.bkpsgbweb.swing.ConfigBkp;

/**
 *
 * @author JHONE
 */
public class IniciarBackupBancoDados implements Runnable {

    private boolean startStop = true;
    private boolean pause = false;
    private final BackupBancoDados bkp;
    private ConsultaBackupBancoDados consultaBackupBancoDados;

    public IniciarBackupBancoDados(BackupBancoDados backup) {
        this.bkp = backup;
    }

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        consultaBackupBancoDados = new ConsultaBackupBancoDados(getBkp());
        while (isStartStop()) {
            try {
                if (isPause()) {
                    synchronized (this) {
                        wait();
                    }
                }
                consultaBackupBancoDados.iniciarBackupArquivo();
                synchronized (this) {
                    wait((59 * 1000));
                }
            } catch (InterruptedException ex) {
                GravarBackupBancoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
            }
        }
        GravarBackupBancoLog.gravarLogInformation("Vereficação de backup parada", ConfigBkp.getInstance());
    }

    public boolean isStartStop() {
        return startStop;
    }

    /**
     * @return the bkp
     */
    public BackupBancoDados getBkp() {
        return bkp;
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
