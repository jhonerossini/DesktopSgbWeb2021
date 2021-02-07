/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.conexao;

import java.sql.*;

/**
 *
 * @author Rosemary
 */
public class ConSQLServer {
    String saae = "SQLCMD -S .\\SQLEXPRESS -U PRONIM -P PRO98NIM -i \"C:\\CETIL\\BKP\\IMEDIATO.SQL\"";
    private String servidor="\\morpheus";
    private String porta="";
    private String usuario="";
    private String senha="PRO98NIM";
    private String banco="PRONIM";
    private String url = "jdbc:microsoft:sqlserver:" + servidor;// + "\\" + banco;
    private Connection conexao;

    public ConSQLServer() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conectou...");
        } catch (ClassNotFoundException ex) {
            throw new Exception("Não foi possivel encontrar o arquivo: " + ex.getMessage());
        } catch (SQLException ex) {
            throw new Exception("Erro na Conexão: " + ex.getMessage());
        }
    }

    public Connection getConexao() {
        return conexao;
    }
}
