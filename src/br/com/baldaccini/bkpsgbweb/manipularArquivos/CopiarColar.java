/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.manipularArquivos;

import br.com.baldaccini.bkpsgbweb.log.GravarArquivoLog;
import br.com.baldaccini.bkpsgbweb.swing.ConfigBkp;
import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author ETEC
 */
public class CopiarColar {

    private Path novaOrigem;
    private Path novoDestino;
    private DirectoryStream<Path> entradas;
    private int nLidos = 0;

    public synchronized void copiarArquivos(Path origem, Path destino) {
        // se é um diretório, tentamos criar. se já existir, não tem problema.
        if (Files.isDirectory(origem)) {
            try {
                Files.createDirectories(destino);
                // listamos todas as entradas do diretório
                entradas = Files.newDirectoryStream(origem);
            } catch (IOException ex) {
                GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
            }
            for (Path entrada : entradas) {
                // para cada entrada, achamos o arquivo equivalente dentro de cada arvore
                novaOrigem = origem.resolve(entrada.getFileName());
                novoDestino = destino.resolve(entrada.getFileName());
                // invoca o metodo de maneira recursiva
                copiarArquivos(novaOrigem, novoDestino);
            }
        } else {
            try {
                // copiamos o arquivo
                Files.copy(origem, destino);
            } catch (IOException ex) {
                try {
                    //se não existir o caminho ele cria
                    Files.createDirectories(destino.getParent());
                    Files.copy(origem, destino);
                } catch (IOException ex1) {
                    GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
                }
            }
        }
    }
    
    //Sobrescreve os arquivos
    public synchronized void fileWriter(File local, File destino) {
        try {
            FileInputStream input = new FileInputStream(local);
            try (FileOutputStream output = new FileOutputStream(destino, false)) {
                byte[] buffer = new byte[15360];//media de 5 char Quantidade de bytes a ser carregado na memória
                while ((nLidos = input.read(buffer)) >= 0) {// Um loop para ler a quantidade de bytes por vez
                    output.write(buffer, 0, nLidos);// Aqui grava a imagen no local de destino
                    output.flush();
                }
                output.close();
            }
        } catch (FileNotFoundException ex) {
            GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
        } catch (IOException ex) {
            GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
        }
    }
    /*public void copiarColar(Backup backup) throws IOException {
     if (((backup.getDom().equals("true") && new DataReturn().weekDay().equals("Domingo"))
     || (backup.getSeg().equals("true") && new DataReturn().weekDay().equals("Segunda-feira"))
     || (backup.getTer().equals("true") && new DataReturn().weekDay().equals("Terça-feira"))
     || (backup.getQua().equals("true") && new DataReturn().weekDay().equals("Quarta-feira"))
     || (backup.getQui().equals("true") && new DataReturn().weekDay().equals("Quinta-feira"))
     || (backup.getSex().equals("true") && new DataReturn().weekDay().equals("Sexta-feira"))
     || (backup.getSab().equals("true") && new DataReturn().weekDay().equals("Sábado")))) {
     if (new DataReturn().sysHora().equals("00")) {
     if (new DataReturn().sysMin().equals("00")) {
     if (backup.getHora().equals("0" + new DataReturn().sysHora())
     && backup.getMinuto().equals("0" + new DataReturn().sysMin())) {
     copiarArquivos(new File(backup.getLocal()).toPath(), new File(backup.getDestino()).toPath());
     }
     } else {
     if (backup.getHora().equals("0" + new DataReturn().sysHora())
     && backup.getMinuto().equals(new DataReturn().sysMin())) {
     copiarArquivos(new File(backup.getLocal()).toPath(), new File(backup.getDestino()).toPath());
     }
     }
     } else {
     if (backup.getHora().equals(new DataReturn().sysHora())
     && backup.getMinuto().equals(new DataReturn().sysMin())) {
     copiarArquivos(new File(backup.getLocal()).toPath(), new File(backup.getDestino()).toPath());
     } else if (new DataReturn().sysMin().equals("00")) {
     if (backup.getHora().equals("0" + new DataReturn().sysHora())
     && backup.getMinuto().equals("0" + new DataReturn().sysMin())) {
     copiarArquivos(new File(backup.getLocal()).toPath(), new File(backup.getDestino()).toPath());
     }
     } else {
     if (backup.getHora().equals("0" + new DataReturn().sysHora())
     && backup.getMinuto().equals(new DataReturn().sysMin())) {
     copiarArquivos(new File(backup.getLocal()).toPath(), new File(backup.getDestino()).toPath());
     }
     }
     }
     }
     }
     */
}
