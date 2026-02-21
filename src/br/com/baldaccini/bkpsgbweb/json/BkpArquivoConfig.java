package br.com.baldaccini.bkpsgbweb.json;

import br.com.baldaccini.bkpsgbweb.log.GravarArquivoLog;
import br.com.baldaccini.bkpsgbweb.modelo.BackupArquivo;
import br.com.baldaccini.bkpsgbweb.swing.ConfigBkp;
import br.com.baldaccini.bkpsgbweb.util.UtilsJson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jhone
 */
public class BkpArquivoConfig {
    
    public void criarBackupArquivoJson(List<BackupArquivo> backup) {
        try {
            UtilsJson.criarJson(backup, "backupArquivo.json");
        } catch (IOException ex) {
            GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
        }
    }

    public ArrayList<BackupArquivo> backupArquivo() {
        try {
            return UtilsJson.backupArquivo("backupArquivo.json", BackupArquivo.class);
        } catch (IOException ex) {
            GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
        }
        return new ArrayList<>();
    }
}
