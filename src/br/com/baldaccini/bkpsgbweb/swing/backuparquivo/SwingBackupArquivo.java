/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.swing.backuparquivo;

import br.com.baldaccini.bkpsgbweb.conexao.servidor.Configuracao;
import br.com.baldaccini.bkpsgbweb.interfaces.INotificacoesArquivo;
import br.com.baldaccini.bkpsgbweb.log.GravarArquivoLog;
import br.com.baldaccini.bkpsgbweb.log.GravarBackupBancoLog;
import br.com.baldaccini.bkpsgbweb.manipularArquivos.IniciarBackupArquivo;
import br.com.baldaccini.bkpsgbweb.modelo.Acoes;
import br.com.baldaccini.bkpsgbweb.modelo.BackupArquivo;
import br.com.baldaccini.bkpsgbweb.modelo.NomeAbreviacao;
import br.com.baldaccini.bkpsgbweb.swing.ConfigBkp;
import br.com.baldaccini.bkpsgbweb.xml.BkpArquivoXML;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jhone
 */
public class SwingBackupArquivo {

    private static List<IniciarBackupArquivo> LISTA_THREAD;
    private Runnable rn;
    private Thread th;
    private IniciarBackupArquivo iniciarBackup;
    private boolean flagVerifThreadsAtivasInat = false;
    private static INotificacoesArquivo CONFIG_BKP;
    private static BkpArquivoXML BKP_ARQUIVO_XML;
    private static Configuracao CONFIGURACAO;

    public SwingBackupArquivo(ConfigBkp configBkp) {
        CONFIGURACAO = new Configuracao(configBkp);
        BKP_ARQUIVO_XML = new BkpArquivoXML();
        LISTA_THREAD = new ArrayList<>();
        SwingBackupArquivo.CONFIG_BKP = configBkp;
        qtdBackupArquivo();
    }

    public static List<IniciarBackupArquivo> getListaThreadArquivo() {
        return LISTA_THREAD;
    }

    private void qtdBackupArquivo() {
        rn = new Runnable() {

            @Override
            public synchronized void run() {
                while (true) {
                    CONFIG_BKP.atualizarLblQtdBkp((LISTA_THREAD != null ? LISTA_THREAD.size() : 0), SwingBackupArquivo.class.getSimpleName());
                    try {
                        wait(500);
                    } catch (InterruptedException ex) {
                        GravarBackupBancoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
                    }
                }
            }
        };
        th = new Thread(rn);
        th.setName("QtdBackupArquivo");
        th.start();
    }

    private void inicarThreadBackupArquivo(ArrayList<BackupArquivo> lista) {
        try {
            if (lista != null && lista.size() > 0) {

                for (BackupArquivo backup : lista) {
                    iniciarBackup = new IniciarBackupArquivo(backup);
                    th = new Thread(iniciarBackup);
                    th.setName(backup.getNome());
                    th.start();
                    LISTA_THREAD.add(iniciarBackup);
                    CONFIG_BKP.iniciarTabelaArquivo(new String[]{backup.getNome(), backup.getDataCriacao().replace("-", "/"), backup.getHoraMin(), (NomeAbreviacao.SEGUNDA_FEIRA.equals(backup.getSeg()) ? Acoes.SIM : Acoes.NAO), (NomeAbreviacao.TERCA_FEIRA.equals(backup.getTer()) ? Acoes.SIM : Acoes.NAO), (NomeAbreviacao.QUARTA_FEIRA.equals(backup.getQua()) ? Acoes.SIM : Acoes.NAO),
                        (NomeAbreviacao.QUINTA_FEIRA.equals(backup.getQui()) ? Acoes.SIM : Acoes.NAO), (NomeAbreviacao.SEXTA_FEIRA.equals(backup.getSex()) ? Acoes.SIM : Acoes.NAO), (NomeAbreviacao.SABADO.equals(backup.getSab()) ? Acoes.SIM : Acoes.NAO), (NomeAbreviacao.DOMINGO.equals(backup.getDom()) ? Acoes.SIM : Acoes.NAO)});
                }
            }
        } catch (Exception ex) {
            GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
            if (LISTA_THREAD != null) {
                int cont = 0;
                for (IniciarBackupArquivo kv : LISTA_THREAD) {
                    if (!kv.isStartStop()) {
                        CONFIG_BKP.situacaoBkpArquivo(cont, Acoes.PARADO);
                    } else {
                        CONFIG_BKP.situacaoBkpArquivo(cont, Acoes.INICIADO);
                    }
                    cont++;
                }
            }
        }
    }

    private void verifThreadsAtivasInat() {
        flagVerifThreadsAtivasInat = true;
        rn = new Runnable() {

            int cont = 0;

            @Override
            public synchronized void run() {
                while (true) {
                    try {
                        for (IniciarBackupArquivo kv : LISTA_THREAD) {
                            try {
                                if (kv.isPause()) {
                                    CONFIG_BKP.situacaoBkpArquivo(cont, Acoes.PAUSADO);
                                } else if (!kv.isStartStop()) {
                                    CONFIG_BKP.situacaoBkpArquivo(cont, Acoes.PARADO);
                                } else {
                                    CONFIG_BKP.situacaoBkpArquivo(cont, Acoes.INICIADO);
                                }
                                cont++;
                            } catch (Exception ex) {
                                flagVerifThreadsAtivasInat = false;
                                GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
                            }
                        }
                        cont = 0;
                        wait(200);
                    } catch (Exception ex) {
                        flagVerifThreadsAtivasInat = false;
                        GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
                    }
                }
            }
        };
        th = new Thread(rn);
        th.setName("VerifThAtivaInativa");
        th.start();
    }

    public void iniciarThreadAtivaInativa() {
        rn = new Runnable() {

            @Override
            public void run() {
                ArrayList<BackupArquivo> listaBackup = new BkpArquivoXML().backupArquivo();
                if (listaBackup.size() >= 0) {
                    inicarThreadBackupArquivo(listaBackup);
                    verifThreadsAtivasInat();
                    //persistirThread();
                }
            }
        };
        th = new Thread(rn);
        th.setName("ArqIniThAtivaInativa");
        th.start();
    }

    @Deprecated
    private void persistirThread() {
        rn = new Runnable() {
            @Override
            public synchronized void run() {
                while (true) {
                    if (!flagVerifThreadsAtivasInat) {
                        verifThreadsAtivasInat();
                    }
                    try {
                        wait(1000);
                    } catch (InterruptedException ex) {
                        GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
                    }
                }
            }
        };
        th = new Thread(rn);
        th.setName("ArqPersThAtivaInativa");
        th.start();
    }

    @SuppressWarnings("empty-statement")
    private long gerarIdentificador() {
        Long id = new Random().nextLong();
        while (compararIdentificadorXML(id));
        return id;
    }

    private boolean compararIdentificadorXML(Long id) {
        try {
            for (BackupArquivo backup : BKP_ARQUIVO_XML.backupArquivo()) {
                if (id.compareTo(Long.parseLong((backup.getIdentificador() == null ? "-2" : backup.getIdentificador()))) == 0) {
                    return true;
                }
            }
        } catch (NumberFormatException ex) {
            GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
        }
        return false;
    }

    public void addNovaThread(BackupArquivo backupArquivo) {
        if (backupArquivo != null) {
            long id = gerarIdentificador();
            List<BackupArquivo> listaBackupNovo;
            listaBackupNovo = new ArrayList<>();
            listaBackupNovo.addAll(BKP_ARQUIVO_XML.backupArquivo());
            for (BackupArquivo bkp : listaBackupNovo) {
                if (bkp.getNome().equals(backupArquivo.getNome())) {
                    CONFIG_BKP.alertaArquivo(Acoes.O_NOME_DO_BACKUP_JA_EXISTE);
                    listaBackupNovo.clear();
                    return;
                }
            }
            backupArquivo.setIdentificador(String.valueOf(id));
            listaBackupNovo.add(backupArquivo);
            iniciarBackup = new IniciarBackupArquivo(backupArquivo);
            th = new Thread(iniciarBackup);
            th.setName(backupArquivo.getNome());
            th.start();
            LISTA_THREAD.add(iniciarBackup);
            BKP_ARQUIVO_XML.criarBackupArquivo(listaBackupNovo);
            CONFIG_BKP.addLinhaTabelaArquivo();
            listaBackupNovo.clear();
            try {
                if (SwingBackupArquivo.CONFIGURACAO.getEscutaCliente().isServidorAtivoInativo()) {
                    SwingBackupArquivo.CONFIGURACAO.getEscutaCliente().getRequisicaoRespostaCliente().acaoBkpWeb(Acoes.ADD_ITEM_TABELA, -1, backupArquivo, iniciarBackup.isPause() ? Acoes.PAUSADO : iniciarBackup.isStartStop() ? Acoes.INICIADO : Acoes.PARADO);
                }
            } catch (Exception ex) {
                GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
            }
        }
    }

    //2015453966510 - tentativa 1 a atendente pediu pra eu ligar para 1056, só que eu tinha ligado para o 1056
    //2015453976420 - tentativa 2 sistema fora do ar, o atendente pediu para retornar dentro de  2 horas
    //2015454024181 - tentativa 3 sistema fora do ar, o atendente pediu para retornar dentro de  2 horas
    public boolean iniciarThreadArquivo(int linha) {
        int cont = 0;
        try {
            for (IniciarBackupArquivo kv : LISTA_THREAD) {
                if (cont == linha) {
                    if (kv.isPause()) {
                        kv.continuar();
                        CONFIG_BKP.situacaoBkpArquivo(linha, Acoes.INICIADO);
                        try {
                            if (SwingBackupArquivo.CONFIGURACAO.getEscutaCliente().isServidorAtivoInativo()) {
                                SwingBackupArquivo.CONFIGURACAO.getEscutaCliente().getRequisicaoRespostaCliente().acaoBkpWeb(Acoes.INICIAR, linha, null, null);
                            }
                            return true;
                        } catch (NullPointerException ex) {
                            GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
                        }
                    } else if (!kv.isStartStop()) {
                        kv.iniciar();
                        th = new Thread(kv);
                        th.setName(kv.getBackupArquivo().getNome());
                        th.start();
                        CONFIG_BKP.situacaoBkpArquivo(linha, Acoes.INICIADO);
                        GravarArquivoLog.gravarLogInformation(kv.getBackupArquivo().getNome() + " iniciado com sucesso!", ConfigBkp.getInstance());
                        try {
                            if (SwingBackupArquivo.CONFIGURACAO.getEscutaCliente().isServidorAtivoInativo()) {
                                SwingBackupArquivo.CONFIGURACAO.getEscutaCliente().getRequisicaoRespostaCliente().acaoBkpWeb(Acoes.INICIAR, linha, null, null);
                            }
                            return true;
                        } catch (NullPointerException ex) {
                            GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
                        }
                    }
                }
                cont++;
            }
        } catch (Exception ex) {
            GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
        }
        return false;
    }

    public void pausarThreadArquivo(final int linha) {
        rn = new Runnable() {

            int cont = 0;

            @Override
            public void run() {
                for (IniciarBackupArquivo kv : LISTA_THREAD) {
                    if (linha == cont) {
                        if (!kv.isStartStop()) {
                            kv.iniciar();
                            th = new Thread(kv);
                            th.setName(kv.getBackupArquivo().getNome());
                            th.start();
                        }
                        kv.pause();
                        CONFIG_BKP.situacaoBkpArquivo(linha, Acoes.PAUSADO);
                        GravarArquivoLog.gravarLogInformation(kv.getBackupArquivo().getNome() + " pausado com sucesso!", ConfigBkp.getInstance());
                        try {
                            if (SwingBackupArquivo.CONFIGURACAO.getEscutaCliente().isServidorAtivoInativo()) {
                                SwingBackupArquivo.CONFIGURACAO.getEscutaCliente().getRequisicaoRespostaCliente().acaoBkpWeb(Acoes.PAUSAR, linha, null, null);
                            }
                        } catch (NullPointerException ex) {
                            GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
                        }
                    }
                    cont++;
                }
            }
        };
        th = new Thread(rn);
        th.start();
    }

    public void imediato(final int linha) {
        rn = new Runnable() {
            int cont = 0;

            @Override
            public void run() {
                for (IniciarBackupArquivo kv : LISTA_THREAD) {
                    if (linha == cont) {
                        kv.imediato();
                    }
                    cont++;
                }
            }
        };
        th = new Thread(rn);
        th.start();
    }

    public void pararThreadArquivo(final int linha) {
        rn = new Runnable() {

            int cont = 0;

            @Override
            public void run() {
                for (IniciarBackupArquivo kv : LISTA_THREAD) {
                    if (linha == cont) {
                        if (kv.isPause()) {
                            kv.continuar();
                        }
                        kv.parar();
                        CONFIG_BKP.situacaoBkpArquivo(linha, Acoes.PARADO);
                        GravarArquivoLog.gravarLogInformation(kv.getBackupArquivo().getNome() + " parado com sucesso!", ConfigBkp.getInstance());
                        try {
                            if (SwingBackupArquivo.CONFIGURACAO.getEscutaCliente().isServidorAtivoInativo()) {
                                SwingBackupArquivo.CONFIGURACAO.getEscutaCliente().getRequisicaoRespostaCliente().acaoBkpWeb(Acoes.PARAR, linha, null, null);
                            }
                        } catch (NullPointerException ex) {
                            GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
                        }
                    }
                    cont++;
                }
            }
        };
        th = new Thread(rn);
        th.start();
    }

    public void detalharThreadArquivo(int linha) {
        List<BackupArquivo> lista = BKP_ARQUIVO_XML.backupArquivo();
        CONFIG_BKP.detalheArquivo(lista.get(linha));
    }

    public static void excluirThreadArquivo(int linha) {
        if (linha >= 0) {
            ArrayList<BackupArquivo> lista = BKP_ARQUIVO_XML.backupArquivo();
            GravarArquivoLog.gravarLogInformation("tamanho da lista: " + lista.size(), ConfigBkp.getInstance());
            if (lista.size() > 0) {
                GravarArquivoLog.gravarLogInformation("lista é maior que zero!", ConfigBkp.getInstance());
                if (!LISTA_THREAD.isEmpty()) {
                    GravarArquivoLog.gravarLogInformation("LISTA_THREAD.isEmpty(): " + LISTA_THREAD.isEmpty(), ConfigBkp.getInstance());
                    for (IniciarBackupArquivo kv : LISTA_THREAD) {
                        if (kv.getBackupArquivo().getIdentificador().equals(lista.get(linha).getIdentificador())) {
                            kv.parar();
                            GravarArquivoLog.gravarLogInformation("thread parada com sucesso!", ConfigBkp.getInstance());
                            LISTA_THREAD.remove(kv);
                            GravarArquivoLog.gravarLogInformation("thread removida com sucesso!", ConfigBkp.getInstance());
                            lista.remove(linha);
                            GravarArquivoLog.gravarLogInformation("linha removida com sucesso!", ConfigBkp.getInstance());
                            BKP_ARQUIVO_XML.criarBackupArquivo(lista);
                            GravarArquivoLog.gravarLogInformation("xml atualizado com sucesso!", ConfigBkp.getInstance());
                            CONFIG_BKP.excluirThreadArquivo(linha, true);
                            try {
                                if (SwingBackupArquivo.CONFIGURACAO.getEscutaCliente().isServidorAtivoInativo()) {
                                    SwingBackupArquivo.CONFIGURACAO.getEscutaCliente().getRequisicaoRespostaCliente().acaoBkpWeb(Acoes.EXCLUIR_LINHA, linha, null, null);
                                }
                            } catch (NullPointerException ex) {
                                GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    public Configuracao getConfiguracoes() {
        return CONFIGURACAO;
    }

    //retornar os dias da semana em que foi agendado o backup
    public String diasSemanaFormatado(BackupArquivo backup) {
        String diasSemana = "";
        int qtdMaiuscula = 0;
        String retorno = "";

        if (backup.getSeg().equals("true")) {
            diasSemana = Acoes.SEGUNDA;
        }
        if (backup.getTer().equals("true")) {
            diasSemana += Acoes.TERCA;
        }
        if (backup.getQua().equals("true")) {
            diasSemana += Acoes.QUARTA;
        }
        if (backup.getQui().equals("true")) {
            diasSemana += Acoes.QUINTA;
        }
        if (backup.getSex().equals("true")) {
            diasSemana += Acoes.SEXTA;
        }
        if (backup.getSab().equals("true")) {
            diasSemana += Acoes.SABADO;
        }
        if (backup.getDom().equals("true")) {
            diasSemana += Acoes.DOMINGO;
        }
        for (int i = 0; i < diasSemana.length(); i++) {
            if (diasSemana.substring(i, i + 1).equals(diasSemana.substring(i, i + 1).toUpperCase())) {
                qtdMaiuscula++;
            }
        }
        switch (qtdMaiuscula) {
            case 1:
                retorno = diasSemana;
                break;
            case 2:
                for (int i = 0; i < diasSemana.length(); i++) {
                    if (i > 0) {
                        if (diasSemana.substring(i, i + 1).equals(diasSemana.substring(i, i + 1).toUpperCase())) {
                            retorno = diasSemana.substring(0, i) + " e " + diasSemana.substring(i, diasSemana.length());
                        }
                    }
                }
                break;
            case 3: {
                int parada = 0;
                for (int i = 0; i < diasSemana.length(); i++) {
                    if (i >= 0) {
                        if (String.valueOf(diasSemana.charAt(i)).equals(String.valueOf(diasSemana.charAt(i)).toUpperCase())) {
                            if (parada == 1) {
                                retorno += ", ";
                            } else if (parada == 2) {
                                retorno += " e ";
                            }
                            parada++;
                        }
                    }
                    retorno += diasSemana.charAt(i);
                }
                break;
            }
            case 4: {
                int parada = 0;
                for (int i = 0; i < diasSemana.length(); i++) {
                    if (i >= 0) {
                        if (String.valueOf(diasSemana.charAt(i)).equals(String.valueOf(diasSemana.charAt(i)).toUpperCase())) {
                            switch (parada) {
                                case 1:
                                    retorno += ", ";
                                    break;
                                case 2:
                                    retorno += ", ";
                                    break;
                                case 3:
                                    retorno += " e ";
                                    break;
                                default:
                                    break;
                            }
                            parada++;
                        }
                    }
                    retorno += diasSemana.charAt(i);
                }
                break;
            }
            case 5: {
                int parada = 0;
                for (int i = 0; i < diasSemana.length(); i++) {
                    if (i >= 0) {
                        if (String.valueOf(diasSemana.charAt(i)).equals(String.valueOf(diasSemana.charAt(i)).toUpperCase())) {
                            switch (parada) {
                                case 1:
                                    retorno += ", ";
                                    break;
                                case 2:
                                    retorno += ", ";
                                    break;
                                case 3:
                                    retorno += ", ";
                                    break;
                                case 4:
                                    retorno += " e ";
                                    break;
                                default:
                                    break;
                            }
                            parada++;
                        }
                    }
                    retorno += diasSemana.charAt(i);
                }
                break;
            }
            case 6: {
                int parada = 0;
                for (int i = 0; i < diasSemana.length(); i++) {
                    if (i >= 0) {
                        if (String.valueOf(diasSemana.charAt(i)).equals(String.valueOf(diasSemana.charAt(i)).toUpperCase())) {
                            switch (parada) {
                                case 1:
                                    retorno += ", ";
                                    break;
                                case 2:
                                    retorno += ", ";
                                    break;
                                case 3:
                                    retorno += ", ";
                                    break;
                                case 4:
                                    retorno += ", ";
                                    break;
                                case 5:
                                    retorno += " e ";
                                    break;
                                default:
                                    break;
                            }
                            parada++;
                        }
                    }
                    retorno += diasSemana.charAt(i);
                }
                break;
            }
            case 7: {
                int parada = 0;
                for (int i = 0; i < diasSemana.length(); i++) {
                    if (i >= 0) {
                        if (String.valueOf(diasSemana.charAt(i)).equals(String.valueOf(diasSemana.charAt(i)).toUpperCase())) {
                            switch (parada) {
                                case 1:
                                    retorno += ", ";
                                    break;
                                case 2:
                                    retorno += ", ";
                                    break;
                                case 3:
                                    retorno += ", ";
                                    break;
                                case 4:
                                    retorno += ", ";
                                    break;
                                case 5:
                                    retorno += ", ";
                                    break;
                                case 6:
                                    retorno += " e ";
                                    break;
                                default:
                                    break;
                            }
                            parada++;
                        }
                    }
                    retorno += diasSemana.charAt(i);
                }
                break;
            }
            default:
                break;
        }
        return retorno;
    }
}
