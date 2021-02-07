/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.modelo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author baldaccini
 */
public class TipoArqPasta extends HashMap {

    private String tipo;
    private Map<String, String> listaDirCorrido = new LinkedHashMap<>();
    private String name;

    public TipoArqPasta(TipoArqPasta tipoArqPasta, String tipo) {
        
    }

    public TipoArqPasta() {

    }

    /**
     * @return the tipo
     */
    public boolean isFile() {
        return "arq".equals(tipo);
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the listaDirCorrido
     */
    public Map<String, String> getListaDirCorrido() {
        return listaDirCorrido;
    }

    /**
     * @param listaDirCorrido the listaDirCorrido to set
     */
    public void setListaDirCorrido(Map<String, String> listaDirCorrido) {
        this.listaDirCorrido = listaDirCorrido;
    }

    public String[] list() {
        String[] lista = new String[listaDirCorrido.size()];
        int cont = 0;
        for (Map.Entry<String, String> m : listaDirCorrido.entrySet()) {
            lista[cont] = m.getKey();
            cont++;
        }
        return lista;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
