/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.conexao.servidor;

import br.com.baldaccini.bkpsgbweb.conexao.servidor.acoescliente.EscutaCliente;
import br.com.baldaccini.bkpsgbweb.data.DataReturn;
import br.com.baldaccini.bkpsgbweb.log.GravarServidorLog;
import br.com.baldaccini.bkpsgbweb.modelo.Acoes;
import br.com.baldaccini.bkpsgbweb.swing.ConfigBkp;

/**
 *
 * @author baldaccini
 */
public class Configuracao {

    private int ano = 0, mes = 0, dia = 0, hora = 0, min = 0, seg = 0;
    private boolean estadoServidor = false;
    private EscutaCliente escutaCliente;
    private final GravarServidorLog gravarServidorLog;
    private final ConfigBkp configBkp;
    private final DataReturn dr;
    private Thread th;

    public Configuracao(ConfigBkp configBkp) {
        this.configBkp = configBkp;
        gravarServidorLog = new GravarServidorLog();
        dr = new DataReturn();
    }

    public void iniciar() {
        if (!estadoServidor) {
            String porta = configBkp.gettxtConfigServPorta();
            if (!porta.isEmpty()) {
                escutaCliente = new EscutaCliente(Integer.parseInt(porta));
                th = new Thread(escutaCliente);
                th.setName(EscutaCliente.class.getSimpleName());
                th.start();
                configBkp.setLblAvisoServidor("");
                estadoServidor = true;
                gravarServidorLog.gravarLogInformation(Acoes.SERVIDOR_INICIADO_COM_SUCESSO, configBkp);
                setarConfigInicial();
            } else {
                configBkp.setLblAvisoServidor(Acoes.O_CAMPO_PORTA_NAO_PODE_FICAR_VAZIO);
            }
        } else {
            gravarServidorLog.gravarLogInformation(Acoes.VERIFICANDO_SERVIDOR_INICIADO, configBkp);
            if (escutaCliente.isServidorAtivoInativo()) {
                escutaCliente.parar();
                gravarServidorLog.gravarLogInformation(Acoes.REINICIANDO_SERVIDOR, configBkp);
                iniciar();
            }
        }
    }

    public void parar() {
        if (estadoServidor) {
            estadoServidor = escutaCliente.parar();
            configBkp.setServidorOperando(Acoes.PARADO);
            configBkp.setServidorParado(dr.horaMinSeg());
            estadoServidor = false;
            gravarServidorLog.gravarLogInformation(Acoes.SERVIDOR_PARADO_COM_SUCESSO, configBkp);
        }
    }

    private void setarConfigInicial() {
        configBkp.setServidorOperando(Acoes.NORMAL);
        configBkp.setServidorUsuariosConectados("0");
        configBkp.setServidorFalhas("0");
        configBkp.setServidorIniciado(dr.horaMinSeg());
        Runnable rn = new Runnable() {

            @Override
            public synchronized void run() {
                while (estadoServidor) {
                    try {
                        wait(1000);
                    } catch (InterruptedException ex) {
                        gravarServidorLog.gravarLogError(ex.getMessage(), configBkp);
                    }

                    configBkp.setServidorTempoOnline(contador());
                }
            }
        };
        th = new Thread(rn);
        th.setName("tempoServOnline");
        th.start();
        configBkp.setServidorParado("");
        configBkp.setServidorBytesEnviados("");
        configBkp.setServidorBytesRecebido("");
    }

    private String contador() {
        String retorno;
        if (seg == 59) {
            seg = 0;
            if (min == 59) {
                min = 0;
                if (hora == 23) {
                    hora = 0;
                    if (dia == dr.ultimoDiaMes()) {
                        dia = 1;
                        if (mes == 12) {
                            mes = 1;
                            ano++;
                        } else {
                            mes++;
                        }
                    } else {
                        dia++;
                    }
                } else {
                    hora++;
                }
            } else {
                min++;
            }
        } else {
            seg++;
        }
        if (ano == 0 && mes == 0 && dia == 0 && hora == 0 & min == 0) {
            retorno = seg < 10 ? "0" + seg + " segundo" : seg + " segundo";
        } else if (ano == 0 && mes == 0 && dia == 0 && hora == 0) {
            retorno = (min < 10 ? "0" + min : min) + ":" + (seg < 10 ? "0" + seg : seg);
        } else if (ano == 0 && mes == 0 && dia == 0) {
            retorno = (hora < 10 ? "0" + hora : hora) + ":" + (min < 10 ? "0" + min : min) + ":" + (seg < 10 ? "0" + seg : seg);
        } else if (ano == 0 && mes == 0) {
            retorno = dia + " dia e " + (hora < 10 ? "0" + hora : hora) + ":" + (min < 10 ? "0" + min : min) + ":" + (seg < 10 ? "0" + seg : seg);
        } else if (ano == 0) {
            retorno = mes + " mes e " + (hora < 10 ? "0" + hora : hora) + ":" + (min < 10 ? "0" + min : min) + ":" + (seg < 10 ? "0" + seg : seg);
        } else {
            retorno = ano + " ano e " + (hora < 10 ? "0" + hora : hora) + ":" + (min < 10 ? "0" + min : min) + ":" + (seg < 10 ? "0" + seg : seg);
        }
        return retorno;
    }

    public boolean getEstadoServidor() {
        return estadoServidor;
    }
    
    public EscutaCliente getEscutaCliente(){
        if(this.escutaCliente == null){
            iniciar();
        }
        return this.escutaCliente;
    }
}
