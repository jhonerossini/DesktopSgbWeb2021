/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.zipunzip;

import br.com.baldaccini.bkpsgbweb.data.DataReturn;
import br.com.baldaccini.bkpsgbweb.log.GravarArquivoLog;
import br.com.baldaccini.bkpsgbweb.relatorio.RelatorioArquivo;
import br.com.baldaccini.bkpsgbweb.swing.ConfigBkp;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public boolean adicionarArquivoZip(String origem, String caminhoZip) {
        Path dirOrigem = Paths.get(origem);
        Path zipPath = Paths.get(caminhoZip);
        String dirBaseZip = dirOrigem.getFileName().toString();

        if (!Files.exists(dirOrigem) || !Files.isDirectory(dirOrigem)) {
            GravarArquivoLog.gravarLogError("Diretório de origem não existe: " + origem, ConfigBkp.getInstance());
            return false;
        }
        if(!Files.exists(zipPath.getParent()))
            try {
                Files.createDirectory(zipPath.getParent());
        } catch (IOException ex) {
            GravarArquivoLog.gravarLogError("Não foi possível criar o diretório temporário: " + zipPath.getParent(), ConfigBkp.getInstance());
        }

        try (ZipOutputStream zipDestino = new ZipOutputStream(Files.newOutputStream(zipPath))) {
            Files.walk(dirOrigem).forEach(path -> {
                if (!Files.isDirectory(path)) {
                    try (InputStream is = Files.newInputStream(path)) {
                        // Caminho relativo correto
                        Path relativo = dirOrigem.relativize(path);
                        // ZIP SEMPRE usa "/"
                        String entradaZip = dirBaseZip + "/" + relativo.toString().replace("\\", "/");
                        zipDestino.putNextEntry(new ZipEntry(entradaZip));
                        is.transferTo(zipDestino);
                        zipDestino.closeEntry();
                        ra.gravarRelatorio(this.nomeBackupArquivo, path.getFileName().toString(), path.getParent().toString(), zipPath.getParent()+"\\"+relativo.toString(), path.toFile().length(), dtr.sysDataPath() + " " + dtr.horaMinSeg());
                        GravarArquivoLog.gravarLogInformation(entradaZip, ConfigBkp.getInstance());
                    } catch (IOException e) {
                        GravarArquivoLog.gravarLogError(e.getMessage(), ConfigBkp.getInstance());
                    }
                }
            });
        } catch (Exception ex) {
            GravarArquivoLog.gravarLogError("CompactarPasta -> zipar: " + ex.getMessage(), ConfigBkp.getInstance());
            GravarArquivoLog.gravarLogError("msg.erro.zip-002", ConfigBkp.getInstance());
            return false;
        }
        return true;
    }
}
