/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.manipularArquivos;

import br.com.baldaccini.bkpsgbweb.conexao.ConectarFtp;
import br.com.baldaccini.bkpsgbweb.data.DataReturn;
import br.com.baldaccini.bkpsgbweb.log.GravarArquivoLog;
import br.com.baldaccini.bkpsgbweb.modelo.Acoes;
import br.com.baldaccini.bkpsgbweb.modelo.BackupArquivo;
import br.com.baldaccini.bkpsgbweb.swing.ConfigBkp;
import br.com.baldaccini.bkpsgbweb.zipunzip.CompactarPasta;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Rosemary
 */
public class ConsultaBackupArquivo {

    private final BackupArquivo backup;
    private final ManipularArquivos ma;
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
            ConfigBkp.getInstance().atualizarStatusBkpArquivo();
            if (backup.getBkpIncremental()) {
                ma.copiarArquivosAlteradosNovos(new File(backup.getLocal()).toPath(), new File(backup.getDestino() + backup.getNome() + BKP + backup.getDataBackupAgendado() + "_" + backup.getHoraMin().replace(":", "-")).toPath());
                //verifica se é backup normal
            } else if (backup.getNormal() || backup.getCompactar()) {
                if (!"".equals(backup.getHost())) {
                    String destinoZip = "";
                    if (backup.getCompactar()) {
                        destinoZip = compactar(true);
                        backupNormalComFtp(destinoZip);
                    }
                    if (backup.getNormal()) {
                        backupNormalComFtp(Acoes.SEM_COMPACTAR);
                    }
                } else {
                    if (backup.getCompactar()) {
                        compactar(false);
                    }
                    if (backup.getNormal()) {
                        ma.copiarArquivosNovos(new File(backup.getLocal()).toPath(), new File(backup.getDestino() + backup.getNome() + BKP + backup.getDataBackupAgendado() + "_" + backup.getHoraMin().replace(":", "-")).toPath());
                    }
                }
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
                        } else if (backup.getNormal() || backup.getCompactar()) {
                            if (!"".equals(backup.getHost())) {
                                String destinoZip = "";
                                if (backup.getCompactar()) {
                                    destinoZip = compactar(true);
                                    backupNormalComFtp(destinoZip);
                                }
                                if (backup.getNormal()) {
                                    backupNormalComFtp(Acoes.SEM_COMPACTAR);
                                }
                            } else {
                                if (backup.getCompactar()) {
                                    compactar(false);
                                }
                                if (backup.getNormal()) {
                                    ma.copiarArquivosNovos(new File(backup.getLocal()).toPath(), new File(backup.getDestino() + backup.getNome() + BKP + backup.getDataBackupAgendado() + "_" + backup.getHoraMin().replace(":", "-")).toPath());
                                }
                            }
                        }
                    }
                } else if (backup.getFlagSemana()) {
                    if (dr.compararDataAntes(backup.getDataBackupAgendado())) {
                        if (isDiaSemana()) {
                            if (backup.getHoraMin().equals(dr.horaMin())) {
                                if (backup.getBkpIncremental()) {
                                    ma.copiarArquivosAlteradosNovos(new File(backup.getLocal()).toPath(), new File(backup.getDestino() + backup.getNome() + BKP + backup.getDataBackupAgendado() + "_" + backup.getHoraMin().replace(":", "-")).toPath());
                                    //verifica se é backup normal
                                } else if (backup.getNormal() || backup.getCompactar()) {
                                    if (!"".equals(backup.getHost())) {
                                        String destinoZip = "";
                                        if (backup.getCompactar()) {
                                            destinoZip = compactar(true);
                                            backupNormalComFtp(destinoZip);
                                        }
                                        if (backup.getNormal()) {
                                            backupNormalComFtp(Acoes.SEM_COMPACTAR);
                                        }
                                    } else {
                                        if (backup.getCompactar()) {
                                            compactar(false);
                                        }
                                        if (backup.getNormal()) {
                                            ma.copiarArquivosNovos(new File(backup.getLocal()).toPath(), new File(backup.getDestino() + backup.getNome() + BKP + backup.getDataBackupAgendado() + "_" + backup.getHoraMin().replace(":", "-")).toPath());
                                        }
                                    }
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
                        } else if (backup.getNormal() || backup.getCompactar()) {
                            if (!"".equals(backup.getHost())) {
                                String destinoZip = "";
                                if (backup.getCompactar()) {
                                    destinoZip = compactar(true);
                                    backupNormalComFtp(destinoZip);
                                }
                                if (backup.getNormal()) {
                                    backupNormalComFtp(Acoes.SEM_COMPACTAR);
                                }
                            } else {
                                if (backup.getCompactar()) {
                                    compactar(false);
                                }
                                if (backup.getNormal()) {
                                    ma.copiarArquivosNovos(new File(backup.getLocal()).toPath(), new File(backup.getDestino() + backup.getNome() + BKP + backup.getDataBackupAgendado() + "_" + backup.getHoraMin().replace(":", "-")).toPath());
                                }
                            }
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

    /**
     * Para bkp externo escolha true
     *
     * @param localExterno
     * @return
     */
    private String compactar(boolean localExterno) {
        String destinoZip;
        //verifica se é para compactar
        String destinoZipLocal = backup.getDestino() + backup.getNome() + BKP + backup.getDataBackupAgendado() + "_" + backup.getHoraMin().replace(":", "-") + ".zip";
        if (localExterno) {
            destinoZip = System.getProperty("os.name").contains("Windows") ? "C:\\temp" + destinoZipLocal : "/tmp" + destinoZipLocal;
        } else {
            destinoZip = destinoZipLocal;
        }
        if (!new File(destinoZip).exists()) {
            if (!cp.zipar(backup.getLocal(), destinoZip)) {
                destinoZip = "";
            }
        }
        return destinoZip;
    }

    private void backupNormalComFtp(String destinoZip) {
        String ip = backup.getHost();
        String porta = backup.getPorta();
        String usuario = backup.getUsuario();
        String senha = "123456780";//backup.getSenha();
        String modoPassivo = backup.getModoConexao();
        ConectarFtp conexaoFtp = new ConectarFtp();
        if(null != conexaoFtp.conectar(ip, porta != null && !"".equals(porta) ? Integer.parseInt(porta) : 21, usuario, senha, backup.getDestino(), (modoPassivo != null && !"".equals(modoPassivo) ? "true".equals(modoPassivo) : false))){
            try {
                if ("".equals(destinoZip)) {
                    conexaoFtp.enviarArquivo(new File(backup.getLocal()).toPath(), backup.getDestino());
                } else {
                    conexaoFtp.enviarArquivo(new File(destinoZip).toPath(), backup.getDestino());
                }
            } catch (IOException ex) {
                GravarArquivoLog.gravarLogInformation(ex.getMessage(), ConfigBkp.getInstance());
                System.out.println(ex.getMessage());
            }
        }else{
            GravarArquivoLog.gravarLogInformation("Não foi possivel conectar com o servidor FTP.", ConfigBkp.getInstance());
        }
    }
}
