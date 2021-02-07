/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.manipularArquivos;

import java.io.File;

/**
 *
 * @author lab01-pc00
 */
public class BkpIncremental {

    private String[][] antigo;
    private String[][] atual;
    private int atualizarListaDestino = 0;
    private int atualizarListaLocal = 0;
    private int contListDest = 0;
    private int contListLocal = 0;
    private static File localFisico;
    private static File destinoFisico;
    private boolean exist = false;
    private static String[][] novoArq;

    public BkpIncremental(File local, File destino) {
        localFisico = local;
        destinoFisico = destino;
        getSizelistLocal(local);
        getSizelistDestino(destino);
        antigo = new String[contListDest][2];
        atual = new String[contListLocal][2];
        atualizarListaLocal(local);
        atualizarListaDestino(destino);
        novoArq = new String[atual.length][2];
        //arqNovo();
        //arqAlterado();
    }

    //Verificar se existe arquivo alterado e substituir
    public synchronized void arqAlterado() {
        for (int at = 0; at < atual.length; at++) {
            for (String[] antigo1 : antigo) {
                if (antigo1[0].equals(destinoFisico + atual[at][0].substring(localFisico.getPath().length()))) {
                    if (!antigo1[1].equals(atual[at][1])) {
                        new CopiarColar().fileWriter(new File(atual[at][0]), new File(antigo[at][0]));
                    }
                }
            }
        }
    }

    //verifica se há arquivo novo e copia para o destino
    private synchronized void arqNovo() {
        if (atual.length > 0 && atual.length > antigo.length) {
            for (String[] atual1 : atual) {
                if (antigo.length > 0) {
                    for (int an = 0; an < antigo.length; an++) {
                        if (antigo[an][0].equals(destinoFisico + atual1[0].substring(localFisico.getPath().length()))) {
                            novoArq = new String[atual.length][2];
                            exist = false;
                        } else {
                            exist = true;
                            novoArq[an][0] = atual1[0];
                            novoArq[an][1] = destinoFisico + atual1[0].substring(localFisico.getPath().length());
                        }
                    }
                    if (exist) {
                        for (String[] novoArq1 : novoArq) {
                            if (novoArq1[0] != null) {
                                new CopiarColar().copiarArquivos(new File(novoArq1[0]).toPath(), new File(novoArq1[1]).toPath());
                            }
                        }
                        exist = true;
                    }
                } else {
                    for (String[] atual2 : atual) {
                        if (atual2[0] != null) {
                            new CopiarColar().copiarArquivos(new File(atual2[0]).toPath(), new File(destinoFisico + atual2[0].substring(localFisico.getPath().length())).toPath());
                        }
                    }
                }
            }
        } else if (antigo.length > atual.length) {
            for (int an = 0; an < antigo.length; an++) {
                if (atual.length > 0) {
                    for (String[] atual1 : atual) {
                        if (atual1[0].equals(localFisico + antigo[an][0].substring(destinoFisico.getPath().length()))) {
                            novoArq = new String[antigo.length][2];
                            exist = false;
                        } else {
                            exist = true;
                            novoArq[an][0] = antigo[an][0];
                            novoArq[an][1] = localFisico + antigo[an][0].substring(destinoFisico.getPath().length());
                        }
                    }
                    if (exist) {
                        for (String[] novoArq1 : novoArq) {
                            if (novoArq1[0] != null) {
                                new CopiarColar().copiarArquivos(new File(novoArq1[0]).toPath(), new File(novoArq1[1]).toPath());
                            }
                        }
                        exist = true;
                    }
                } else {
                    for (String[] antigo1 : antigo) {
                        if (antigo1[0] != null) {
                            new CopiarColar().copiarArquivos(new File(antigo1[0]).toPath(), new File(localFisico + antigo1[0].substring(destinoFisico.getPath().length())).toPath());
                        }
                    }
                }
            }
        }
    }

    //verifica se há arquivo novo e copia para o destino
    public synchronized void ArquivoNovo() {
        getSizelistLocal(localFisico);
        getSizelistDestino(destinoFisico);
        antigo = new String[contListDest][2];
        atual = new String[contListLocal][2];
        atualizarListaLocal(localFisico);
        atualizarListaDestino(destinoFisico);
        novoArq = new String[atual.length][2];
        arqNovo();
        if (atual.length > 0) {
            for (String[] atual1 : atual) {
                if (antigo.length > 0) {
                    for (int an = 0; an < antigo.length; an++) {
                        if (antigo[an][0].equals(destinoFisico + atual1[0].substring(localFisico.getPath().length()))) {
                            novoArq = new String[atual.length][2];
                            exist = false;
                        } else {
                            exist = true;
                            novoArq[an][0] = atual1[0];
                            novoArq[an][1] = destinoFisico + atual1[0].substring(localFisico.getPath().length());
                        }
                    }
                    if (exist) {
                        for (String[] novoArq1 : novoArq) {
                            if (novoArq1[0] != null) {
                                new CopiarColar().copiarArquivos(new File(novoArq1[0]).toPath(), new File(novoArq1[1]).toPath());
                            }
                        }
                        exist = true;
                    }
                } else {
                    for (String[] atual2 : atual) {
                        if (atual2[0] != null) {
                            new CopiarColar().copiarArquivos(new File(atual2[0]).toPath(), new File(destinoFisico + atual2[0].substring(localFisico.getPath().length())).toPath());
                        }
                    }
                }
            }
        }
    }

    //Atualizar lista do destino
    private synchronized void atualizarListaDestino(File destino) {
        if (destino.isDirectory()) {
            File[] lista = destino.listFiles();
            for (File lista1 : lista) {
                atualizarListaDestino(lista1);
            }
        } else {
            antigo[atualizarListaDestino][0] = destino.getAbsolutePath();
            antigo[atualizarListaDestino][1] = String.valueOf(destino.length());
            atualizarListaDestino++;
        }
    }

    //Atualizar lista do local
    private synchronized void atualizarListaLocal(File local) {
        if (local.isDirectory()) {
            File[] lista = local.listFiles();
            for (File lista1 : lista) {
                atualizarListaLocal(lista1);
            }
        } else {
            atual[atualizarListaLocal][0] = local.getAbsolutePath();
            atual[atualizarListaLocal][1] = String.valueOf(local.length());
            atualizarListaLocal++;
        }
    }

    //Retorna um numero inteiro com a quantidade de arquivos do destino
    private synchronized void getSizelistDestino(File destino) {
        if (destino.isDirectory()) {
            File[] lista = destino.listFiles();
            for (File lista1 : lista) {
                getSizelistDestino(lista1);
            }
        } else {
            contListDest++;
        }
    }

    //Retorna um numero inteiro com a quantidade de arquivos do local
    private synchronized void getSizelistLocal(File local) {
        if (local.isDirectory()) {
            File[] lista = local.listFiles();
            for (File lista1 : lista) {
                getSizelistLocal(lista1);
            }
        } else {
            contListLocal++;
        }
    }
    /*
     * Acho melhor fazer uma listagem do local e comparar com o destino que ja tem uma lista salva na memoria
     * e ir comparando se o nome antigo ja existe na lista nova, caso nao exista quarda o nome para no final
     * do metodo copiar os arquivos faltando.
     */
}
