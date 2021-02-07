/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.util;

import br.com.baldaccini.bkpsgbweb.data.DataReturn;
import java.util.ArrayList;
import java.util.List;

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
}
