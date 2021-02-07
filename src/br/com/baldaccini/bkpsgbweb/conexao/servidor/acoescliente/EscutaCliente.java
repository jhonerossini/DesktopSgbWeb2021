/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.conexao.servidor.acoescliente;

import br.com.baldaccini.bkpsgbweb.log.GravarServidorLog;
import br.com.baldaccini.bkpsgbweb.swing.ConfigBkp;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author jhone
 */
public class EscutaCliente implements Runnable {

    private Socket socket;
    private ServerSocket serverSocket;
    private boolean flagConectou = false;
    private RequisicaoRespostaCliente requisicaoRespostaCliente;
    private Thread th;
    private GravarServidorLog gravarServidorLog;

    public EscutaCliente(int porta) {
        gravarServidorLog = new GravarServidorLog();
        try {
            serverSocket = new ServerSocket(porta);
            flagConectou = true;
        } catch (IOException ex) {
            flagConectou = false;
            gravarServidorLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
        }
    }

    @Override
    public void run() {
        if (flagConectou) {
            while (flagConectou) {
                try {
                    socket = serverSocket.accept();
                    requisicaoRespostaCliente = new RequisicaoRespostaCliente(socket);
                    th = new Thread(requisicaoRespostaCliente);
                    th.setName(RequisicaoRespostaCliente.class.getSimpleName());
                    th.start();
                } catch (IOException ex) {
                    flagConectou = false;
                    gravarServidorLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
                }
            }
        }
    }

    /**
     * true rodando e false parado
     *
     * @return boolean
     */
    public boolean isServidorAtivoInativo() {
        return flagConectou;
    }

    public boolean parar() {
        try {
            flagConectou = false;
            serverSocket.close();
            socket.close();
        } catch (NullPointerException | IOException ex) {
            gravarServidorLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
        }
        return flagConectou;
    }

    public RequisicaoRespostaCliente getRequisicaoRespostaCliente() {
        return this.requisicaoRespostaCliente;
    }
}
