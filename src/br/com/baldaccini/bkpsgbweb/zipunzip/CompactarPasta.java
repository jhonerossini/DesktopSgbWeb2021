/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.zipunzip;

import br.com.baldaccini.bkpsgbweb.data.DataReturn;
import br.com.baldaccini.bkpsgbweb.log.GravarArquivoLog;
import br.com.baldaccini.bkpsgbweb.relatorio.RelatorioArquivo;
import br.com.baldaccini.bkpsgbweb.swing.ConfigBkp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author Rosemary
 */
public class CompactarPasta {

    private final String nomeBackupArquivo;
    private static final int TAM_BUFFER = 4096;
    private DataReturn dtr;
    private RelatorioArquivo ra;

    public CompactarPasta(String nomeBackupArquivo) {
        this.nomeBackupArquivo = nomeBackupArquivo;
        dtr = new DataReturn();
        ra = new RelatorioArquivo();
    }

    /*
     * Metodos para teste
     */
    /**
     * Zipa o arquivo ou diretório
     *
     * @param endEntrada endereço do arquivo ou diretório a ser zipado
     * @param endSaida endereço de saída do arquivo zip gerado
     * @return true se zipou certou ou false se deu erro
     */
    public boolean zipar(String endEntrada, String endSaida) {

        String dirInterno = "";
        boolean retorno = true;

        try {
            File file = new File(endEntrada);
            //Verifica se o arquivo ou diretório existe  
            if (!file.exists()) {
                GravarArquivoLog.gravarLogError("msg.erro.zip-001", ConfigBkp.getInstance());
                return false;
            }
            //se é um arquivo a ser zipado
            //zipa e retorna

            try (ZipOutputStream zipDestino = new ZipOutputStream(new FileOutputStream(endSaida))) {
                ziparFile(file, dirInterno, zipDestino);
                //se é um arquivo a ser zipado
                //zipa e retorna
                /*if (file.isFile()) {
                 ziparFile(file, dirInterno, zipDestino);
                 } //senão lista o que tem no diretório e zipa
                 else {
                 dirInterno = file.getName();
                 //Verifica se é diretório ou
                 File[] files = file.listFiles();
                 for (File file1 : files) {
                 ziparFile(file1, dirInterno, zipDestino);
                 }
                 }*/
                zipDestino.finish();
                zipDestino.close();
            }
        } catch (IOException ex) {
            GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
            GravarArquivoLog.gravarLogError("msg.erro.zip-002", ConfigBkp.getInstance());
            retorno = false;
        }
        return retorno;
    }

    /**
     * Zipa o arquivo ou diretório passado Verifica se é diretório executa
     * recursão para adicionar os arquivos contidos dentro do mesmo no zip senão
     * somente adiciona o arquivo no zip criado
     *
     * @param file arquivo ou diretório a ser adicionado no zip
     * @param dirInterno diretório interno do zip
     * @param zipDestino zip em que está sendo adicionado os arquivos e
     * diretórios
     * @throws java.io.IOException exeção que pode ser gerada na adição de
     * arquivos no zip
     */
    private void ziparFile(File file, String dirInterno, ZipOutputStream zipDestino) throws IOException {

        byte data[] = new byte[TAM_BUFFER];
        //Verifica se a file é um diretório, então faz a recursão  
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                ziparFile(file1, dirInterno + File.separator + file.getName(), zipDestino);
            }
            return;
        }

        try (FileInputStream fi = new FileInputStream(file.getAbsolutePath())) {
            zipDestino.putNextEntry(new ZipEntry(dirInterno + File.separator + file.getName()));
            ra.gravarRelatorio(this.nomeBackupArquivo, file.getName(), file.getParent(), dirInterno + File.separator + file.getName(), file.length(), dtr.sysDataPath() + " " + dtr.horaMinSeg());
            int count;
            while ((count = fi.read(data)) > 0) {
                //zipDestino.write(data, 0, count);
                zipDestino.write(data);
            }
            zipDestino.flush();
            zipDestino.closeEntry();
        }
    }
}
