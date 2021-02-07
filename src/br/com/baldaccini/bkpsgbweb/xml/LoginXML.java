/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.xml;

import br.com.baldaccini.bkpsgbweb.log.GravarArquivoLog;
import br.com.baldaccini.bkpsgbweb.modelo.Login;
import br.com.baldaccini.bkpsgbweb.swing.ConfigBkp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author baldaccini
 */
public class LoginXML {

    public void criarLogin(Login login) {
        try {
            PrintWriter pw;
            File arquivo = new File("configPass.ini");
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }
            try (FileWriter arq = new FileWriter("configPass.ini", false)) {
                pw = new PrintWriter(arq);
                pw.append("usuario=" + login.getUsuario());
                pw.println();
                pw.append("senha=" + login.getPass());
                pw.println();
                pw.append("email=" + login.getEmail());
                pw.println();
                pw.append("empresa=" + login.getEmpresa());
                pw.println();
                pw.append("finalidade=" + login.getFinalidadeBackup());
                pw.println();
                pw.flush();
                arq.flush();
                pw.close();
                arq.close();
            }
        } catch (FileNotFoundException ex) {
            GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
        } catch (IOException ex) {
            GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
        }
    }

    @Deprecated
    public Login lerLoginXML() {
        File f = new File("configPass.xml");
        if (f.exists()) {
            SAXBuilder builder = new SAXBuilder();
            Document doc;
            try {
                doc = builder.build(f);
                Element root = (Element) doc.getRootElement();
                List pessoas = root.getChildren();
                Iterator i = pessoas.iterator();
                Login login = new Login();
                Element pessoa = (Element) i.next();
                login.setUsuario(pessoa.getChildText("usuario"));
                login.setPass(pessoa.getChildText("pass"));
                login.setEmail(pessoa.getChildText("email"));
                return login;
            } catch (JDOMException | IOException ex) {
                GravarArquivoLog.gravarTodosLog(ex.getMessage());
                return null;
            }
        }
        return new Login();
    }

    public Login lerLogin() {
        FileReader fr = null;
        try {
            File f = new File("configPass.ini");
            if (!f.exists()) {
                if (!f.createNewFile()) {
                    return new Login();
                }
            }
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            Login login = new Login();
            String linha = br.readLine();
            login.setUsuario(linha.substring(linha.indexOf("=") + 1));
            linha = br.readLine();
            login.setPass(linha.substring(linha.indexOf("=") + 1));
            linha = br.readLine();
            login.setEmail(linha.substring(linha.indexOf("=") + 1));
            linha = br.readLine();
            login.setEmpresa(linha.substring(linha.indexOf("=") + 1));
            linha = br.readLine();
            login.setFinalidadeBackup(linha.substring(linha.indexOf("=") + 1));
            return login;
        } catch (FileNotFoundException ex) {
            GravarArquivoLog.gravarTodosLog(ex.getMessage());
        } catch (IOException ex) {
            GravarArquivoLog.gravarTodosLog(ex.getMessage());
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                GravarArquivoLog.gravarTodosLog(ex.getMessage());
            }
        }
        return new Login();
    }

    public boolean alterar(String pass) throws FileNotFoundException, IOException {
        Login login = lerLogin();
        login.setPass(pass);
        criarLogin(login);
        return true;
    }
}
