/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.xml;

import br.com.baldaccini.bkpsgbweb.log.GravarBackupBancoLog;
import br.com.baldaccini.bkpsgbweb.modelo.BackupBancoDados;
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
public class BkpBancoDadosXML {

    public void criarBackupBancoDados(List<BackupBancoDados> backupBancoDados) {
        try {
            XStream xStream = new XStream(new DomDriver());
            xStream.alias("bkpBancoDados", List.class);
            xStream.alias("bkpBancoDado", BackupBancoDados.class);

            File arquivo = new File("backupBancoDados.xml");
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }
            FileOutputStream gravar;
            gravar = new FileOutputStream(arquivo);
            gravar.write(xStream.toXML(backupBancoDados).getBytes());
            gravar.flush();
            gravar.close();
        } catch (FileNotFoundException ex) {
            GravarBackupBancoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
        } catch (IOException ex) {
            GravarBackupBancoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
        }
    }

    public ArrayList<BackupBancoDados> backupBancoDados() {
        File f = new File("backupBancoDados.xml");
        if (f.exists()) {
            try {
                SAXBuilder builder = new SAXBuilder();
                Document doc = builder.build(f);
                Element root = (Element) doc.getRootElement();
                List pessoas = root.getChildren();
                Iterator i = pessoas.iterator();
                ArrayList<BackupBancoDados> listabackup = new ArrayList<>();
                BackupBancoDados backupBancoDados;
                while (i.hasNext()) {
                    backupBancoDados = new BackupBancoDados();
                    Element pessoa = (Element) i.next();
                    backupBancoDados.setDestino(pessoa.getChildText("destino"));
                    backupBancoDados.setSeg(pessoa.getChildText("seg"));
                    backupBancoDados.setTer(pessoa.getChildText("ter"));
                    backupBancoDados.setQua(pessoa.getChildText("qua"));
                    backupBancoDados.setQui(pessoa.getChildText("qui"));
                    backupBancoDados.setSex(pessoa.getChildText("sex"));
                    backupBancoDados.setSab(pessoa.getChildText("sab"));
                    backupBancoDados.setDom(pessoa.getChildText("dom"));
                    backupBancoDados.setHoraMinuto(pessoa.getChildText("horaMinuto"));
                    backupBancoDados.setDescricao(pessoa.getChildText("descricao"));
                    backupBancoDados.setPathArquivo(pessoa.getChildText("pathArquivo"));
                    backupBancoDados.setDataBackupAgendado(pessoa.getChildText("dataBackupAgendado"));
                    backupBancoDados.setDataUltimoBackup(pessoa.getChildText("dataUltimoBackup"));
                    backupBancoDados.setDataCriacaoBackup(pessoa.getChildText("dataCriacaoBackup"));
                    backupBancoDados.setNomeArquivo(pessoa.getChildText("nomeArquivo"));
                    backupBancoDados.setPorta(pessoa.getChildText("porta"));
                    backupBancoDados.setSenha(pessoa.getChildText("senha"));
                    backupBancoDados.setServidor(pessoa.getChildText("servidor"));
                    backupBancoDados.setUsuario(pessoa.getChildText("usuario"));
                    backupBancoDados.setBanco(pessoa.getChildText("banco"));
                    backupBancoDados.setFlagData(pessoa.getChildText("flagData"));
                    backupBancoDados.setFlagSemana(pessoa.getChildText("flagSemana"));
                    listabackup.add(backupBancoDados);
                }
                return listabackup;
            } catch (JDOMException | IOException ex) {
                GravarBackupBancoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
            }
        }
        return new ArrayList<>();
    }
}
