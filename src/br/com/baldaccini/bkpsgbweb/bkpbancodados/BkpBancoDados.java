/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.bkpbancodados;

import br.com.baldaccini.bkpsgbweb.log.GravarBackupBancoLog;
import br.com.baldaccini.bkpsgbweb.swing.ConfigBkp;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author JHONE
 */
public class BkpBancoDados {

    private Process p = null;
    private static ResultSet res;
    private static Connection con;
    private Statement st;
    private static final int BUFFER = 1024;

    public void backupMysql(String banco, String usuario, String senha, String servidor, String porta, String destino) {
        try {
            p = Runtime.getRuntime().exec(getMysqlBinPath(usuario, senha, banco, porta, servidor) + "mysqldump --host=" + servidor + " --port=" + porta + " --user=" + usuario + " --password=" + senha + "  " + "--skip-comments --skip-triggers " + banco);
            StringBuilder temp;
            try (InputStream in = p.getInputStream(); BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                temp = new StringBuilder();
                int count;
                char[] cbuf = new char[BUFFER];
                while ((count = br.read(cbuf, 0, BUFFER)) != -1) {
                    temp.append(cbuf, 0, count);
                }
            }
            byte[] data = temp.toString().getBytes();
            File filedst = new File(destino + banco + ".zip");
            try (FileOutputStream dest = new FileOutputStream(filedst); ZipOutputStream zip = new ZipOutputStream(
                    new BufferedOutputStream(dest))) {
                zip.setMethod(ZipOutputStream.DEFLATED);
                zip.setLevel(Deflater.BEST_COMPRESSION);
                zip.putNextEntry(new ZipEntry(banco + ".sql"));
                zip.write(data);
                zip.flush();
                zip.close();
                GravarBackupBancoLog.gravarLogInformation("Backup realizado com sucesso!", ConfigBkp.getInstance());
            }
        } catch (IOException e) {
            GravarBackupBancoLog.gravarLogError(e.getMessage(), ConfigBkp.getInstance());
            GravarBackupBancoLog.gravarLogInformation("Não foi possivel criar o Backup!", ConfigBkp.getInstance());
        }
    }

    public String restaurarBackupMysql(String banco, String senha, String arquivoSql) {
        String[] restoreCmd = new String[]{"mysql ", "--user=" + banco, "--password=" + senha, "-e", "source " + arquivoSql};
        try {

            p = Runtime.getRuntime().exec(restoreCmd);
            int processComplete = p.waitFor();

            if (processComplete == 0) {
                GravarBackupBancoLog.gravarLogInformation("Backup restaurado com sucesso!", ConfigBkp.getInstance());
                return "Backup restaurado com sucesso!";
            } else {
                GravarBackupBancoLog.gravarLogInformation("Não foi possível restaurar o Backup!", ConfigBkp.getInstance());
                return "Não foi possível restaurar o Backup!";
            }
        } catch (IOException | InterruptedException ex) {
            GravarBackupBancoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
        }
        return "Não foi possível restaurar o Backup!";
    }

    private String getMysqlBinPath(String user, String password, String db, String porta, String servidor) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            GravarBackupBancoLog.gravarLogError(e.getMessage(), ConfigBkp.getInstance());
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + servidor + ":" + porta + "/" + db, user, password);
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (Exception e) {
            GravarBackupBancoLog.gravarLogError(e.getMessage(), ConfigBkp.getInstance());
        }
        String a = "";
        try {
            res = st.executeQuery("select @@basedir");
            while (res.next()) {
                a = res.getString(1);
            }
        } catch (Exception eee) {
            GravarBackupBancoLog.gravarLogError(eee.getMessage(), ConfigBkp.getInstance());
        }
        a = a + "bin\\";
        return a;
    }
}
