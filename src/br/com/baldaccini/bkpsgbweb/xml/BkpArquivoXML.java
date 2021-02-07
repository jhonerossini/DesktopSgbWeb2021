/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.xml;

import br.com.baldaccini.bkpsgbweb.log.GravarArquivoLog;
import br.com.baldaccini.bkpsgbweb.modelo.BackupArquivo;
import br.com.baldaccini.bkpsgbweb.swing.ConfigBkp;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
public class BkpArquivoXML {

    public void criarBackupArquivo(List<BackupArquivo> backup) {
        try {
            XStream xStream = new XStream(new DomDriver());
            xStream.alias("backups", List.class);
            xStream.alias("backup", BackupArquivo.class);

            File arquivo = new File("backupArquivo.xml");
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }
            FileOutputStream gravar;
            gravar = new FileOutputStream(arquivo);
            gravar.write(xStream.toXML(backup).getBytes());
            gravar.flush();
            gravar.close();
        } catch (FileNotFoundException ex) {
            GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
        } catch (IOException ex) {
            GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
        }
    }

    public ArrayList<BackupArquivo> backupArquivo() {
        File f = new File("backupArquivo.xml");
        if (f.exists()) {
            try {
                SAXBuilder builder = new SAXBuilder();
                Document doc = builder.build(f);
                Element root = (Element) doc.getRootElement();
                List pessoas = root.getChildren();
                Iterator i = pessoas.iterator();
                ArrayList<BackupArquivo> listabackup = new ArrayList<>();
                BackupArquivo backup;
                while (i.hasNext()) {
                    backup = new BackupArquivo();
                    Element pessoa = (Element) i.next();
                    backup.setDataCriacao(pessoa.getChildText("dataCriacao"));
                    backup.setLocal(pessoa.getChildText("local"));
                    backup.setDestino(pessoa.getChildText("destino"));
                    backup.setSeg(pessoa.getChildText("seg"));
                    backup.setTer(pessoa.getChildText("ter"));
                    backup.setQua(pessoa.getChildText("qua"));
                    backup.setQui(pessoa.getChildText("qui"));
                    backup.setSex(pessoa.getChildText("sex"));
                    backup.setSab(pessoa.getChildText("sab"));
                    backup.setDom(pessoa.getChildText("dom"));
                    backup.setHoraMin(pessoa.getChildText("horaMin"));
                    backup.setCompactar(pessoa.getChildText("compactar"));
                    backup.setExcluir(pessoa.getChildText("excluir"));
                    backup.setBkpIncremental(pessoa.getChildText("bkpIncremental"));
                    backup.setNome(pessoa.getChildText("nome"));
                    backup.setDescricao(pessoa.getChildText("descricao"));
                    backup.setIdentificador(pessoa.getChildText("identificador"));
                    backup.setNormal(pessoa.getChildText("normal"));
                    backup.setDataBackupAgendado(pessoa.getChildText("dataBackupAgendado"));
                    backup.setFlagData(pessoa.getChildText("flagData"));
                    backup.setFlagSemana(pessoa.getChildText("flagSemana"));
                    listabackup.add(backup);
                }
                return listabackup;
            } catch (JDOMException | IOException ex) {
                GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
            }
        }
        return new ArrayList<>();
    }
}
