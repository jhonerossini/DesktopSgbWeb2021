/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Rosemary
 */
public class ConFirebird {

    private String servidor;
    private String usuario;
    private String senha;
    private String banco;
    private String porta;
    private String url = "jdbc:firebirdsql://";
    private Connection conexao;

    public ConFirebird() throws Exception {
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            conexao = DriverManager.getConnection(getUrl() , usuario, senha);
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

    /**
     * @return the url
     */
    private String getUrl() {
        return url + servidor + porta + banco;
    }
}
