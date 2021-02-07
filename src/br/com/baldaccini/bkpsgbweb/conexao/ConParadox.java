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
public class ConParadox {

    private String servidor;
    private String usuario;
    private String senha;
    private String banco;
    private String url = "jdbc:Paradox:///" + servidor + "/" + banco;
    private Connection conexao;

    public ConParadox() throws SQLException, Exception {
        try {
            Class.forName("com.hxtt.sql.paradox.ParadoxDriver");
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conectou...");
        } catch (ClassNotFoundException ex) {
            throw new Exception("Não foi possivel encontrar o arquivo: " + ex.getMessage());
        }
    }

    public Connection getConexao() {
        return conexao;
    }
}
