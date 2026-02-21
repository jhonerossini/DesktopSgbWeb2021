package br.com.baldaccini.bkpsgbweb.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jhone
 */
public class UtilsJson {

    public static void criarJson(List<?> lista, String arquivo) throws IOException {
        // Cria o ObjectMapper para serializar objetos Java para JSON
        ObjectMapper objectMapper = new ObjectMapper();
        // Cria o objeto File a partir do caminho fornecido
        File file = new File(arquivo);
        // Se o arquivo não existir, cria um novo arquivo
        if (!file.exists()) {
            file.createNewFile();
        }
        // Serializa a lista para JSON e grava no arquivo
        objectMapper.writeValue(file, lista);
    }

    public static <T> ArrayList<T> backupArquivo(String arquivo, Class<T> clazz) throws IOException {
        File f = new File(arquivo);
        if (f.exists()) {
            // Cria o ObjectMapper para ler JSON e mapear para objetos Java
            ObjectMapper objectMapper = new ObjectMapper();
            // Lê o arquivo JSON e converte para uma lista de objetos da classe fornecida
            ArrayList<T> listabackup = objectMapper.readValue(f, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz));
            return listabackup;
        }
        return new ArrayList<>();
    }
}
