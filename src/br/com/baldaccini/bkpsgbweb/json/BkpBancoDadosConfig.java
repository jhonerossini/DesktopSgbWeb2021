package br.com.baldaccini.bkpsgbweb.json;

import br.com.baldaccini.bkpsgbweb.log.GravarBackupBancoLog;
import br.com.baldaccini.bkpsgbweb.modelo.BackupBancoDados;
import br.com.baldaccini.bkpsgbweb.swing.ConfigBkp;
import br.com.baldaccini.bkpsgbweb.util.UtilsJson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jhone
 */
public class BkpBancoDadosConfig {
    
    public void criarBackupBancoDados(List<BackupBancoDados> backupBancoDados) {
        try {
            UtilsJson.criarJson(backupBancoDados, "backupBancoDados.json");
        } catch (IOException ex) {
            GravarBackupBancoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
        }
    }

    public ArrayList<BackupBancoDados> backupBancoDados() {
        try {
            return UtilsJson.backupArquivo("backupBancoDados.json", BackupBancoDados.class);
        } catch (IOException ex) {
            GravarBackupBancoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
        }
        return new ArrayList<>();
    }
}
