/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.manipularArquivos;

import br.com.baldaccini.bkpsgbweb.data.DataReturn;
import br.com.baldaccini.bkpsgbweb.log.GravarArquivoLog;
import br.com.baldaccini.bkpsgbweb.relatorio.RelatorioArquivo;
import br.com.baldaccini.bkpsgbweb.swing.ConfigBkp;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author jhone
 */
public class ManipularArquivos {

    private Path local;
    private Path destino;
    private DirectoryStream<Path> entradas;
    private final String nomeBackup;
    private final RelatorioArquivo ra;
    private final DataReturn dr;

    public ManipularArquivos(String nomeBackup) {
        this.nomeBackup = nomeBackup;
        dr = new DataReturn();
        ra = new RelatorioArquivo();
    }

    public synchronized void copiarArquivosNovos(Path local, Path destino) {
        // se é um diretório, tentamos criar. se já existir, não tem problema.
        if (Files.isDirectory(local)) {
            try {
                Files.createDirectories(destino);
                // listamos todas as entradas do diretório
                entradas = Files.newDirectoryStream(local);
            } catch (IOException ex) {
                GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
            }
            for (Path entrada : entradas) {
                // para cada entrada, achamos o arquivo equivalente dentro de cada arvore
                this.local = local.resolve(entrada.getFileName());
                this.destino = destino.resolve(entrada.getFileName());
                // invoca o metodo de maneira recursiva
                copiarArquivosNovos(this.local, this.destino);
            }
        } else {
            try {
                if (!Files.exists(destino, LinkOption.NOFOLLOW_LINKS)) {
                    Files.copy(local, destino);
                    ra.gravarRelatorio(nomeBackup, local.toFile().getName(), local.toFile().getParent(), this.destino.toFile().getParent(), local.toFile().length(), dr.sysDataPath() + " " + dr.horaMinSeg());
                }
            } catch (IOException ex) {
                GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
            }
        }
    }

    public synchronized void copiarArquivosAlteradosNovos(Path local, Path destino) {
        // se é um diretório, tentamos criar. se já existir, não tem problema.
        if (Files.isDirectory(local)) {
            try {
                Files.createDirectories(destino);
                // listamos todas as entradas do diretório
                entradas = Files.newDirectoryStream(local);
            } catch (IOException ex) {
                GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
            }
            for (Path entrada : entradas) {
                // para cada entrada, achamos o arquivo equivalente dentro de cada arvore
                this.local = local.resolve(entrada.getFileName());
                this.destino = destino.resolve(entrada.getFileName());
                if (!Files.exists(this.destino, LinkOption.NOFOLLOW_LINKS)) {
                    // invoca o metodo de maneira recursiva
                    copiarArquivosNovos(this.local, this.destino);
                } else {
                    try {
                        if (!String.valueOf(Files.size(this.local)).equals(String.valueOf(Files.size(this.destino)))) {
                            //this.frame.atualizaLog("Copia de arquivo alterado!");
                            Files.copy(this.local, this.destino, StandardCopyOption.REPLACE_EXISTING);
                            ra.gravarRelatorio(nomeBackup, entrada.toFile().getName(), entrada.toFile().getParent(), this.destino.toFile().getParent(), entrada.toFile().length(), dr.sysDataPath() + " " + dr.horaMinSeg());
                        }
                    } catch (IOException ex) {
                        GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
                    }
                }
            }
        }
    }
}