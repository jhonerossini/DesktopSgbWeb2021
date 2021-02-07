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
public class ConMySql {

    private String servidor;
    private String usuario;
    private String senha;
    private String banco;
    private String porta;
    private String url = "jdbc:mysql://" + servidor + porta + banco;
    private Connection conexao;

    public ConMySql() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
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
