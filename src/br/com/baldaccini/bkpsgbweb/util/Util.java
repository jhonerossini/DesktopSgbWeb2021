/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.util;

import br.com.baldaccini.bkpsgbweb.data.DataReturn;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author jhone
 */
public class Util {

    public static List<String> anos() {
        int anoAtual = Integer.parseInt(new DataReturn().sysAno());
        List<String> listaAnos = new ArrayList<>();
        for (int i = 0; i <= 25; i++) {
            listaAnos.add(String.valueOf(anoAtual++));
        }
        return listaAnos;
    }

    public static String getBytesValorVariaveis(short value) {
        return String.valueOf(("" + value).replace("-", "").replace(".", "").replace(",", "").length());
    }

    public static String getBytesValorVariaveis(int value) {
        return String.valueOf(("" + value).replace("-", "").length());
    }

    public static String getBytesValorVariaveis(double value) {
        return String.valueOf(("" + value).replace("-", "").replace(".", "").replace(",", "").length());
    }

    public static String getBytesValorVariaveis(float value) {
        return String.valueOf(("" + value).replace("-", "").replace(".", "").replace(",", "").length());
    }

    public static String getBytesValorVariaveis(long value) {
        return String.valueOf(("" + value).replace("-", "").length());
    }

    public static String getBytesValorVariaveis(String value) {
        return String.valueOf(value.length());
    }

    public static String getBytesValorVariaveis(Integer value) {
        return String.valueOf(("" + value).replace("-", "").length());
    }
    
    public static List<String> lerArquivoTextoPorLinha(String arquivo) throws FileNotFoundException{
        File file = new File(arquivo);
        
        if(!file.exists()){
            JOptionPane.showMessageDialog(null, "Arquivo não existe!");
            return null;
        }
        List<String> retorno = new ArrayList<>();
        Scanner in = new Scanner(new FileReader(file));
        while (in.hasNextLine()) {
            String line = in.nextLine();
            retorno.add(line);
        }
        return retorno;
    }
    
    public String gerarTextoRandom(String base){
        Random r = new Random();
        String retorno = "";
        try {
        r.setSeed(base.getBytes().length + this.hashCode());
            retorno = new String(String.valueOf(r.nextInt() * this.hashCode()).getBytes(this.getClass().toGenericString()));
        } catch (UnsupportedEncodingException ex) {
            retorno = ex.getMessage().getBytes().toString();
            
        }
        return retorno;
    }
}
