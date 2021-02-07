/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.swing.backupbancodados;

import br.com.baldaccini.bkpsgbweb.log.GravarBackupBancoLog;
import br.com.baldaccini.bkpsgbweb.manipularArquivos.IniciarBackupBancoDados;
import br.com.baldaccini.bkpsgbweb.modelo.BackupBancoDados;
import br.com.baldaccini.bkpsgbweb.swing.ConfigBkp;
import br.com.baldaccini.bkpsgbweb.xml.BkpBancoDadosXML;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jhone
 */
public class SwingBackupBancoDados {

    private final List<IniciarBackupBancoDados> listaIniciarBackup;
    private Runnable rn;
    private Thread th;
    private IniciarBackupBancoDados iniciarBackupBancoDados;
    private final ConfigBkp configBkp;
    private BkpBancoDadosXML bkpBancoDadosXML;

    /**
     *
     * @param configBkp
     */
    public SwingBackupBancoDados(ConfigBkp configBkp) {
        bkpBancoDadosXML = new BkpBancoDadosXML();
        this.configBkp = configBkp;
        listaIniciarBackup = new ArrayList<>();
        qtdBackupBancodados();
    }

    private void qtdBackupBancodados() {
        rn = new Runnable() {

            @Override
            public synchronized void run() {
                while (true) {
                    configBkp.atualizarLblQtdBkp((listaIniciarBackup != null ? listaIniciarBackup.size() : 0), SwingBackupBancoDados.class.getSimpleName());
                    try {
                        wait(500);
                    } catch (InterruptedException ex) {
                        GravarBackupBancoLog.gravarLogError(ex.getMessage(), configBkp);
                    }
                }
            }
        };
        th = new Thread(rn);
        th.setName("QtdBackupBancodados");
        th.start();
    }

    private void atualizarVerificacaoEstadoTabela() {
        rn = new Runnable() {
            @Override
            public synchronized void run() {
                int cont = 0;
                while (true) {
                    if (listaIniciarBackup.size() >= 0) {
                        for (IniciarBackupBancoDados kv : listaIniciarBackup) {
                            if (!kv.isStartStop()) {
                                configBkp.situacaoTabelaBancoDados(cont, "Parado");
                            } else if (kv.isPause()) {
                                configBkp.situacaoTabelaBancoDados(cont, "Pausado");
                            } else {
                                configBkp.situacaoTabelaBancoDados(cont, "Iniciado");
                            }
                            cont++;
                        }
                    }
                    cont = 0;
                    try {
                        wait(200);
                    } catch (InterruptedException ex) {
                        GravarBackupBancoLog.gravarLogError(ex.getMessage(), configBkp);
                    }
                }
            }
        };
        th = new Thread(rn);
        th.setName("VerifThAtivaInativaBD");
        th.start();
    }

    public void carregarIniciarBackupBancoDados() {
        List<BackupBancoDados> listaBancoDados = bkpBancoDadosXML.backupBancoDados();
        if (listaBancoDados.size() > 0) {
            for (BackupBancoDados bkpBancoDados : listaBancoDados) {
                iniciarBackupBancoDados = new IniciarBackupBancoDados(bkpBancoDados);
                th = new Thread(iniciarBackupBancoDados);
                th.setName(bkpBancoDados.getNomeArquivo());
                th.start();
                listaIniciarBackup.add(iniciarBackupBancoDados);
                configBkp.iniciarTabelaBancoDados(new String[]{
                    bkpBancoDados.getBanco(), bkpBancoDados.getNomeArquivo(), bkpBancoDados.getUsuario(),
                    bkpBancoDados.getDataBackupAgendado().replace("-", "/"),
                    bkpBancoDados.getHoraMinuto(), bkpBancoDados.getServidor(), "Iniciado"
                });
            }
            atualizarVerificacaoEstadoTabela();
        }
    }

    public void adicionarBackupBancodados(BackupBancoDados backupBancoDados) {
        if (backupBancoDados != null) {
            try {
                List<BackupBancoDados> listaBancoDados = new ArrayList<>();

                listaBancoDados.addAll(bkpBancoDadosXML.backupBancoDados());

                listaBancoDados.add(backupBancoDados);

                bkpBancoDadosXML.criarBackupBancoDados(listaBancoDados);

                iniciarBackupBancoDados = new IniciarBackupBancoDados(backupBancoDados);
                th = new Thread(iniciarBackupBancoDados);
                th.setName(iniciarBackupBancoDados.getBkp().getNomeArquivo());
                th.start();
                listaIniciarBackup.add(iniciarBackupBancoDados);
                configBkp.iniciarTabelaBancoDados(new String[]{
                    backupBancoDados.getBanco(), backupBancoDados.getNomeArquivo(), backupBancoDados.getUsuario(),
                    backupBancoDados.getDataBackupAgendado().replace("-", "/"),
                    backupBancoDados.getHoraMinuto(), backupBancoDados.getServidor(), "Iniciado"});
            } catch (NullPointerException ex) {
                GravarBackupBancoLog.gravarLogError(ex.getMessage(), configBkp);
            }
        }
    }

    public void pausarThreadBancoDados(final int linha) {
        if (linha >= 0) {
            rn = new Runnable() {
                int cont = 0;

                @Override
                public void run() {
                    for (IniciarBackupBancoDados kv : listaIniciarBackup) {
                        if (cont == linha) {
                            if (!kv.isStartStop()) {
                                kv.iniciar();
                                th = new Thread(kv);
                                th.start();
                            }
                            kv.pause();
                            configBkp.situacaoTabelaBancoDados(linha, "Pausado");
                        }
                        cont++;
                    }
                }
            };
            th = new Thread(rn);
            th.setName("PausarThreadBD");
            th.start();
        }
    }

    public void iniciarThreadBancoDados(final int linha) {
        int cont = 0;
        if (linha >= 0) {
            try {
                for (IniciarBackupBancoDados kv : listaIniciarBackup) {
                    if (cont == linha) {
                        if (kv.isPause()) {
                            kv.continuar();
                            configBkp.situacaoTabelaBancoDados(linha, "Iniciado");
                        } else if (!kv.isStartStop()) {
                            kv.iniciar();
                            kv.continuar();
                            th = new Thread(kv);
                            th.setName(kv.getBkp().getNomeArquivo());
                            th.start();
                            configBkp.situacaoTabelaBancoDados(linha, "Iniciado");
                        }
                    }
                    cont++;
                }
            } catch (Exception ex) {
                GravarBackupBancoLog.gravarLogError(ex.getMessage(), configBkp);
            }
        }
    }

    public void pararThreadBancoDados(final int linha) {
        if (linha >= 0) {
            rn = new Runnable() {

                int cont = 0;

                @Override
                public void run() {
                    if (linha >= 0) {
                        for (IniciarBackupBancoDados kv : listaIniciarBackup) {
                            if (linha == cont) {
                                if (kv.isPause()) {
                                    kv.continuar();
                                }
                                kv.parar();
                                configBkp.situacaoTabelaBancoDados(linha, "Parado");
                            }
                            cont++;
                        }
                    }
                }
            };
            th = new Thread(rn);
            th.setName("PararThreadBD");
            th.start();
        }
    }

    public void excluirThreadBancoDados(final int linha) {
        if (linha >= 0) {
            int cont = 0;
            ArrayList<BackupBancoDados> lista = bkpBancoDadosXML.backupBancoDados();
            if (lista.size() > 0) {
                if (!listaIniciarBackup.isEmpty()) {
                    for (IniciarBackupBancoDados kv : listaIniciarBackup) {
                        if (cont == linha) {
                            kv.parar();
                            listaIniciarBackup.remove(kv);
                            lista.remove(linha);
                            bkpBancoDadosXML.criarBackupBancoDados(lista);
                            configBkp.excluirThreadBancoDados(linha, true);
                            break;
                        }
                        cont++;
                    }
                }
            }
        }
    }
}
