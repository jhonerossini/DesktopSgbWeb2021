/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.relatorio;

import static br.com.baldaccini.bkpsgbweb.log.GravarArquivoLog.gravarLogError;
import br.com.baldaccini.bkpsgbweb.util.ConvExprTamArq;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author jhone
 */
public class RelatorioArquivo {

    private final File dir;
    private File file;
    private FileWriter arq;
    private PrintWriter pw;
    private FileReader fr;
    private BufferedReader br;
    private final ConvExprTamArq convExprTamArq;
    private final StringBuilder texto;
    
    public RelatorioArquivo (){
        dir = new File("relatorio");
        if(!dir.exists()){
            dir.mkdir();
        }
        convExprTamArq = new ConvExprTamArq();
        texto = new StringBuilder();
    }

    public StringBuilder carregarRelatorioArquivo(String nomeBackup) {
        Long totalBytesCopiados = 0l;
        texto.delete(0, texto.length());
        try {
            fr = new FileReader("relatorio/" + nomeBackup);
            br = new BufferedReader(fr);
            try {
                String linha;
                Long valor;
                while (br.ready()) {
                    linha = br.readLine();
                    if (linha.contains("Tamanho do Arquivo: ")) {
                        valor = Long.parseLong(linha.substring("Tamanho do Arquivo: ".length()));
                        totalBytesCopiados += valor;
                        linha = "Tamanho do Arquivo: " + convExprTamArq.convertToStringRepresentation(valor);
                    }
                    texto.append(linha);
                    texto.append("\n");
                }
                texto.append("|------------------------------------------------------------------------------------|");
                texto.append("\n");
                texto.append("Total: ").append(convExprTamArq.convertToStringRepresentation(totalBytesCopiados));
                fr.reset();
                fr.close();
                br.reset();
                br.close();
            } catch (IOException ex) {
                gravarLogError(ex.getMessage(), null);
            }
        } catch (FileNotFoundException ex) {
            gravarLogError(ex.getMessage(), null);
        }
        return texto;
    }

    public synchronized void gravarRelatorio(String nomeBackup, String nomeArquivo, String localArquivo, String destinoArquivo, Long tamanhoArquivo, String dataHoraCopia) {
        try {
            file = new File(dir, nomeBackup.replace(":","_") + ".txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            arq = new FileWriter("relatorio/" + nomeBackup.replace(":","_") + ".txt", true);
            pw = new PrintWriter(arq);

            pw.append("|------------------------------------------------------------------------------------|");
            pw.println();
            pw.append("Nome Arquivo: " + nomeArquivo);
            pw.println();
            pw.append("Local do Arquivo: " + localArquivo);
            pw.println();
            pw.append("Destino do Arquivo: " + destinoArquivo);
            pw.println();
            pw.append("Tamanho do Arquivo: " + String.valueOf(tamanhoArquivo));
            pw.println();
            pw.append("Data e hora da criação: " + dataHoraCopia);
            pw.println();
            pw.flush();
            arq.flush();
        } catch (IOException ex) {
            gravarLogError(ex.getMessage(), null);
        } finally {
            try {
                pw.close();
                arq.close();
            } catch (IOException ex) {
                gravarLogError(ex.getMessage(), null);
            } catch (Exception ex) {
                gravarLogError(ex.getMessage(), null);
            }
        }
    }
}
