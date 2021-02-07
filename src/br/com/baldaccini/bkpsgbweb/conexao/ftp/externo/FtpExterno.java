/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.conexao.ftp.externo;

import br.com.baldaccini.bkpsgbweb.interfaces.IDestinoFtp;
import br.com.baldaccini.bkpsgbweb.log.GravarArquivoLog;
import br.com.baldaccini.bkpsgbweb.swing.ConfigBkp;
import br.com.baldaccini.bkpsgbweb.swing.DestinoFtp;
import br.com.baldaccini.bkpsgbweb.util.ConvExprTamArq;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 *
 * @author jhone
 */
public class FtpExterno implements IDestinoFtp {

    private InputStream is;
    private FTPClient ftp;
    private DefaultMutableTreeNode raiz;
    private final DefaultTreeModel model;
    private long total = 0l;
    private final ConvExprTamArq conv;
    private DestinoFtp destinoFtp;
    private ConfigBkp configBkp;

    public FtpExterno(ConfigBkp configBkp, DestinoFtp destinoFtp) {
        this.configBkp = configBkp;
        this.destinoFtp = destinoFtp;
        this.raiz = new DefaultMutableTreeNode("raiz");
        this.model = new DefaultTreeModel(raiz);
        this.conv = new ConvExprTamArq();
        this.destinoFtp.getJTree().setModel(model);
    }

    /**
     * Returns the index of a child of a given node, provided its string value.
     *
     * @param node The node to search its children
     * @param childValue The value of the child to compare with
     * @return The index
     */
    private int childIndex(final DefaultMutableTreeNode node, final String childValue) {
        Enumeration<DefaultMutableTreeNode> children = node.children();
        DefaultMutableTreeNode child = null;
        int index = -1;

        while (children.hasMoreElements() && index < 0) {
            child = children.nextElement();

            if (child.getUserObject() != null && childValue.equals(child.getUserObject())) {
                index = node.getIndex(child);
            }
        }

        return index;
    }

    /**
     * Builds a tree from a given forward slash delimited string.
     *
     * @param model The tree model
     * @param str The string to build the tree from
     */
    protected void buildTreeFromString(final DefaultTreeModel model, final String str) {
        // Fetch the root node
        raiz = (DefaultMutableTreeNode) model.getRoot();

        // Split the string around the delimiter
        String[] strings = str.split("/");

        // Create a node object to use for traversing down the tree as it 
        // is being created
        DefaultMutableTreeNode node = raiz;

        // Iterate of the string array
        for (String s : strings) {
            // Look for the index of a node at the current level that
            // has a value equal to the current string
            int index = childIndex(node, s);

            // Index less than 0, this is a new node not currently present on the tree
            if (index < 0) {
                // Add the new node
                DefaultMutableTreeNode newChild = new DefaultMutableTreeNode(s);
                node.insert(newChild, node.getChildCount());
                node = newChild;
            } // Else, existing node, skip to the next string
            else {
                node = (DefaultMutableTreeNode) node.getChildAt(index);
            }
        }
    }

    private synchronized void pathLocalServidorFTP(File file) {
        File[] files = file.listFiles();

        for (File f : files) {
            if (f.isDirectory()) {
                try {
                    if (!ftp.changeWorkingDirectory(f.getName())) {
                        if (!ftp.makeDirectory(f.getName())) {
                            destinoFtp.atualizarLog("Não foi possível criar o diretorio " + f.getName());
                        } else {
                            destinoFtp.atualizarLog("Diretorio " + f.getName() + " criado com sucesso!");
                            destinoFtp.atualizarLog("Acessando diretorio " + f.getName());
                            if (ftp.changeWorkingDirectory(f.getName())) {
                                destinoFtp.atualizarLog("Sucesso ao acessar o diretorio " + f.getName());
                            } else {
                                destinoFtp.atualizarLog("Erro ao acessar o diretorio " + f.getName());
                            }
                        }
                    } else {
                        destinoFtp.atualizarLog("Sucesso ao acessar o diretorio " + f.getName());
                    }
                } catch (IOException ex) {
                    destinoFtp.atualizarLog(ex.getMessage());
                }
                pathLocalServidorFTP(f);
            } else {
                try {
                    FTPFile[] listftpF = ftp.listFiles();
                    boolean flagExit = false;
                    for (FTPFile ftpF : listftpF) {
                        if (ftpF.getName().equals(f.getName())) {
                            flagExit = true;
                            if (f.length() > ftpF.getSize()) {
                                destinoFtp.atualizarLog("Arquivo " + f.getName() + " alterado!");
                                if (ftp.deleteFile(f.getName())) {
                                    destinoFtp.atualizarLog("Arquivo: " + f.getName() + " deletado com sucesso!");
                                    is = new FileInputStream(f);//prepara o arquivo para ser enviado
                                    if (ftp.appendFile(f.getName(), is)) {
                                        destinoFtp.atualizarLog("Arquivo: " + f.getName() + " enviado com sucesso!");
                                        buildTreeFromString(model, ftp.printWorkingDirectory() + "/" + f.getName());
                                        tamanhoBytesCopiados(f.length());
                                    } else {
                                        destinoFtp.atualizarLog("Não foi possível enviar o arquivo: " + f.getName());
                                    }
                                } else {
                                    destinoFtp.atualizarLog("Não foi possível deletar o arquivo: " + f.getName());
                                }
                            }
                        }
                    }
                    if (!flagExit) {
                        is = new FileInputStream(f);
                        if (ftp.appendFile(f.getName(), is)) {
                            destinoFtp.atualizarLog("Arquivo: " + f.getName() + " enviado com sucesso!");
                            buildTreeFromString(model, ftp.printWorkingDirectory() + "/" + f.getName());
                            tamanhoBytesCopiados(f.length());
                        } else {
                            destinoFtp.atualizarLog("Não foi possível enviar o arquivo: " + f.getName());
                        }
                    }
                } catch (IOException ex) {
                    destinoFtp.atualizarLog(ex.getMessage());
                }
            }
        }
        /*try {
            if (!lblArqRaizFlag.getText().equals(ftp.printWorkingDirectory())) {
                ftp.changeWorkingDirectory("../");
            }
        } catch (IOException ex) {
            destinoFtp.atualizarLog(ex.getMessage());
        }*/
    }

    private void listarDiretorioFtp() {
        try {
            FTPFile[] fTpFile = ftp.listFiles();
            for (FTPFile f : fTpFile) {
                if (f.isDirectory()) {
                    if (ftp.changeWorkingDirectory(f.getName())) {
                        listarDiretorioFtp();
                    }
                } else {
                    if ("/".equals(ftp.printWorkingDirectory())) {
                        buildTreeFromString((DefaultTreeModel) model, ftp.printWorkingDirectory());
                    }
                    String nome = ftp.printWorkingDirectory().substring(1, ftp.printWorkingDirectory().length());
                    buildTreeFromString((DefaultTreeModel) model, nome + "/" + f.getName());
                    tamanhoBytesCopiados(f.getSize());
                }
            }
            ftp.changeWorkingDirectory("../");
        } catch (IOException ex) {
            GravarArquivoLog.gravarTodosLog(ex.getMessage());
            destinoFtp.atualizarLog(ex.getMessage());
        }
    }

    @Override
    public boolean conectar(String ip, int porta, String usuario, String pass, String diretorio, boolean modoPassivo) {
        ftp = new FTPClient();
        try {
            ftp.connect(ip, porta);
            if (ftp.login(usuario, pass)) {
                if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                    destinoFtp.atualizarLog("Conectado!");
                    if (ftp.setFileType(FTP.BINARY_FILE_TYPE)) {
                        destinoFtp.atualizarLog("Tipo da transferencia alterada para binario");
                    } else {
                        destinoFtp.atualizarLog("Não foi possivel alterar o tipo da transferencia para binario");
                    }
                    if (modoPassivo) {
                        ftp.enterLocalPassiveMode();
                        //lblModoFlag.setText("Passivo");
                    } else {
                        ftp.enterLocalActiveMode();
                        //lblModoFlag.setText("Ativo");
                    }
                }

                destinoFtp.atualizarLog("" + ftp.getReplyString());
                destinoFtp.atualizarLog("Porta " + ftp.getDefaultPort());

                if (!"".equals(diretorio)) {
                    ftp.changeWorkingDirectory(diretorio);
                    destinoFtp.atualizarLog(diretorio);
                } else {
                    destinoFtp.atualizarLog(diretorio);
                }
                //lblArqRaizFlag.setText(ftp.printWorkingDirectory());
                Runnable rn = new Runnable() {

                    @Override
                    public void run() {
                        listarDiretorioFtp();
                        //trePastasServer.setModel(model);
                        //jScrollPane1.repaint();
                        //lblTotalBytesFlag.setText(conv.convertToStringRepresentation(total));
                        //atualizarLog("Lista finalizada!");
                        //btnPronto.setEnabled(true);
                    }
                };
                new Thread(rn).start();
                return true;
            } else {
                return false;
            }
        } catch (IOException ex) {
            GravarArquivoLog.gravarTodosLog(ex.getMessage());
            destinoFtp.atualizarLog(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean desconectar() {
        if (ftp != null) {
            if (ftp.isConnected()) {
                try {
                    ftp.logout();
                    ftp.disconnect();
                    //this.setVisible(false);
                    return true;
                } catch (IOException ex) {
                    //atualizarLog(ex.getMessage());
                    //this.setVisible(false);
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    @Override
    public void fecharJanela() {
        //super.dispose();
    }

    private void tamanhoBytesCopiados(long tam) {
        total += tam;
        //destinoFtp.lblTotalBytesFlag.setText(String.valueOf(total));
    }
}
