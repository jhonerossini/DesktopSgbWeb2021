/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.swing;

import br.com.baldaccini.bkpsgbweb.data.DataReturn;
import br.com.baldaccini.bkpsgbweb.interfaces.INotificacoesArquivo;
import br.com.baldaccini.bkpsgbweb.interfaces.INotificacoesBancoDados;
import br.com.baldaccini.bkpsgbweb.log.GravarArquivoLog;
import br.com.baldaccini.bkpsgbweb.log.GravarBackupBancoLog;
import br.com.baldaccini.bkpsgbweb.log.GravarServidorLog;
import br.com.baldaccini.bkpsgbweb.modelo.Acoes;
import br.com.baldaccini.bkpsgbweb.modelo.BackupArquivo;
import br.com.baldaccini.bkpsgbweb.modelo.BackupBancoDados;
import br.com.baldaccini.bkpsgbweb.modelo.Login;
import br.com.baldaccini.bkpsgbweb.modelo.NomeAbreviacao;
import br.com.baldaccini.bkpsgbweb.relatorio.RelatorioFrame;
import br.com.baldaccini.bkpsgbweb.swing.backuparquivo.SwingBackupArquivo;
import br.com.baldaccini.bkpsgbweb.swing.backupbancodados.SwingBackupBancoDados;
import br.com.baldaccini.bkpsgbweb.util.Util;
import br.com.baldaccini.bkpsgbweb.xml.LoginXML;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author Rosemary
 */
public class ConfigBkp extends javax.swing.JFrame implements INotificacoesArquivo, INotificacoesBancoDados {

    /**
     * Creates new form ConfigBkp
     */
    public ConfigBkp() {
        data = new DataReturn();
        initComponents();
        inicializarJFrame();
        CONFIG_BKP = this;
        swingBackupArquivo = new SwingBackupArquivo(this);
        swingBackupBancoDados = new SwingBackupBancoDados(this);
    }

    public static ConfigBkp getInstance() {
        return CONFIG_BKP;
    }

    public void adress(String ip) {
        txaLog.append(ip);
    }

    @Override
    public void setLblAvisoServidor(String texto) {
        lblConfigServerAviso.setText(texto);
    }

    public String gettxtConfigServPorta() {
        return txtConfigServPorta.getText();
    }

    @Override
    public void setServidorOperando(String texto) {
        lblCsOperando.setText(Acoes.OPERANDO + ": " + texto);
    }

    @Override
    public void setServidorUsuariosConectados(String texto) {
        if (!"".equals(texto)) {
            lblCsUsuariosConectados.setText(Acoes.USUARIOS_CONECTADOS + ": " + (Integer.parseInt(texto) + Integer.parseInt("".equals(lblCsUsuariosConectados.getText().replace(" ", "").substring(lblCsUsuariosConectados.getText().replace(" ", "").indexOf(":") + 1)) ? "0" : (lblCsUsuariosConectados.getText().replace(" ", "").substring(lblCsUsuariosConectados.getText().replace(" ", "").indexOf(":") + 1)))));
        } else if ("0".equals(texto)) {
            lblCsUsuariosConectados.setText(Acoes.USUARIOS_CONECTADOS + ": 0");
        }
    }

    @Override
    public void setServidorFalhas(String texto) {
        lblCsFalhas.setText(Acoes.FALHAS + ": " + texto);
    }

    @Override
    public void setServidorIniciado(String data) {
        lblCsServidorIniciado.setText(Acoes.SERVIDOR_INICIADO + ": " + data);
    }

    @Override
    public void setServidorTempoOnline(String data) {
        lblCsTempoOnline.setText(Acoes.TEMPO_ONLINE + ": " + data);
    }

    @Override
    public void setServidorParado(String data) {
        lblCsServidorParado.setText(Acoes.SERVIDOR_PARADO + ": " + data);
    }

    @Override
    public void setServidorBytesEnviados(String texto) {
        lblCsBytesEnviados.setText(Acoes.BYTES_ENVIADOS + ": " + (bytesEnviados += (texto == null ? 0l : texto.length())));
    }

    @Override
    public void setServidorBytesRecebido(String texto) {
        lblCsBytesRecebidos.setText(Acoes.BYTES_RECEBIDOS + ": " + (bytesRecebidos += (texto == null ? 0l : texto.length())));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        radBackupArquivos = new javax.swing.JRadioButton();
        radBackupBancoDados = new javax.swing.JRadioButton();
        radServidorBackup = new javax.swing.JRadioButton();
        pnlBackupArquivo = new javax.swing.JPanel();
        tbConfigBackup = new javax.swing.JTabbedPane();
        pnlAgendarBackup = new javax.swing.JPanel();
        lblLocal = new javax.swing.JLabel();
        lblDestino = new javax.swing.JLabel();
        txtLocal = new javax.swing.JTextField();
        btnLocal = new javax.swing.JButton();
        btnDestino = new javax.swing.JButton();
        txtDestino = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        lblAcaoDoBackup = new javax.swing.JLabel();
        chkIncremental = new javax.swing.JCheckBox();
        chkCompactar = new javax.swing.JCheckBox();
        lblNomeBackup = new javax.swing.JLabel();
        txtNomeBackup = new javax.swing.JTextField();
        lblDescricaoBackup = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaDescricaoBackup = new javax.swing.JTextArea();
        lblCaracteres = new javax.swing.JLabel();
        lblNotificacoes = new javax.swing.JLabel();
        pnlData = new javax.swing.JPanel();
        chkSegunda = new javax.swing.JCheckBox();
        chkTerca = new javax.swing.JCheckBox();
        chkQuarta = new javax.swing.JCheckBox();
        chkQuinta = new javax.swing.JCheckBox();
        chkSexta = new javax.swing.JCheckBox();
        chkSabado = new javax.swing.JCheckBox();
        chkDomingo = new javax.swing.JCheckBox();
        chkData = new javax.swing.JCheckBox();
        chkContinuarBackup = new javax.swing.JCheckBox();
        lblDia = new javax.swing.JLabel();
        cboDia = new javax.swing.JComboBox();
        lblMes = new javax.swing.JLabel();
        cboMes = new javax.swing.JComboBox();
        lblAno = new javax.swing.JLabel();
        cboAno = new javax.swing.JComboBox(new DefaultComboBoxModel(Util.anos().toArray()));
        pnlHora = new javax.swing.JPanel();
        spiMin = new javax.swing.JSpinner();
        lbl_min = new javax.swing.JLabel();
        spiHora = new javax.swing.JSpinner();
        lbl_hora = new javax.swing.JLabel();
        chkNormal = new javax.swing.JCheckBox();
        pnlBackupAgendado = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBkpAgendados = new javax.swing.JTable();
        btn_deletar = new javax.swing.JButton();
        btnDetalhe = new javax.swing.JButton();
        lblInformacao = new javax.swing.JLabel();
        btnIniciarThread = new javax.swing.JButton();
        btnPausar = new javax.swing.JButton();
        btnParar = new javax.swing.JButton();
        btnImediato = new javax.swing.JButton();
        pnlDetalheBackup = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblDbIdentificador = new javax.swing.JLabel();
        lblDbNome = new javax.swing.JLabel();
        lblDbTipoBackup = new javax.swing.JLabel();
        lblDbDiaBackup = new javax.swing.JLabel();
        lblDbHoraBackup = new javax.swing.JLabel();
        lblDbLocalBackp = new javax.swing.JLabel();
        lblDbDestinoBackup = new javax.swing.JLabel();
        lblDbDescricao = new javax.swing.JLabel();
        lblDbDataCriacao = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txaDbDescricao = new javax.swing.JTextArea();
        tpLog = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txaLog = new javax.swing.JTextArea();
        pnlBkpBancoDados = new javax.swing.JPanel();
        tpDbBackupBancoDados = new javax.swing.JTabbedPane();
        pnlBdAgendarBackup = new javax.swing.JPanel();
        lblBdBanco = new javax.swing.JLabel();
        cboBdBanco = new javax.swing.JComboBox();
        lblBdServidor = new javax.swing.JLabel();
        txtBdServidor = new javax.swing.JTextField();
        lblBdNomeBanco = new javax.swing.JLabel();
        txtBdNomeBanco = new javax.swing.JTextField();
        lblBdUsuario = new javax.swing.JLabel();
        txtBdUsuario = new javax.swing.JTextField();
        lblBdSenhaBanco = new javax.swing.JLabel();
        pswBdSenhaBanco = new javax.swing.JPasswordField();
        btnBdAdicionarBackupBanco = new javax.swing.JButton();
        lblBdNotificacao = new javax.swing.JLabel();
        txtBdEditarPorta = new javax.swing.JTextField();
        chkBdEditarPorta = new javax.swing.JCheckBox();
        lblBdDestino = new javax.swing.JLabel();
        txtBdDestino = new javax.swing.JTextField();
        btnBdDestino = new javax.swing.JButton();
        pnlBdData = new javax.swing.JPanel();
        lblBdDia = new javax.swing.JLabel();
        lblBdMes = new javax.swing.JLabel();
        cboBdMes = new javax.swing.JComboBox();
        lblBdAno = new javax.swing.JLabel();
        chkBdDomingo = new javax.swing.JCheckBox();
        chkBdSabado = new javax.swing.JCheckBox();
        chkBdSexta = new javax.swing.JCheckBox();
        chkBdQuinta = new javax.swing.JCheckBox();
        chkBdQuarta = new javax.swing.JCheckBox();
        chkBdTerca = new javax.swing.JCheckBox();
        chkBdSegunda = new javax.swing.JCheckBox();
        cboBdAno = new javax.swing.JComboBox(new DefaultComboBoxModel(Util.anos().toArray()));
        cboBdDia = new javax.swing.JComboBox();
        chkBdContinuarBackup = new javax.swing.JCheckBox();
        chkBdData = new javax.swing.JCheckBox();
        pnlBdHora = new javax.swing.JPanel();
        lblBdHora = new javax.swing.JLabel();
        spiBdHora = new javax.swing.JSpinner();
        lblBdMin = new javax.swing.JLabel();
        spiBdMin = new javax.swing.JSpinner();
        pnlBdBackupAgendado = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblBdBackupAgendado = new javax.swing.JTable();
        btnBdExcluir = new javax.swing.JButton();
        btnBdDetalhe = new javax.swing.JButton();
        btnBdParar = new javax.swing.JButton();
        btnBdPausar = new javax.swing.JButton();
        btnBdIniciar = new javax.swing.JButton();
        pnlRestaurarBackup = new javax.swing.JPanel();
        lblRbBanco = new javax.swing.JLabel();
        cboRbBanco = new javax.swing.JComboBox();
        lblRbServidor = new javax.swing.JLabel();
        txtRbServidor = new javax.swing.JTextField();
        lblRbNomeBanco = new javax.swing.JLabel();
        txtRbNomeBanco = new javax.swing.JTextField();
        lblRbUsuario = new javax.swing.JLabel();
        txtRbUsuario = new javax.swing.JTextField();
        lblRbSenhaBanco = new javax.swing.JLabel();
        pswRbSenhaBanco = new javax.swing.JPasswordField();
        lblRbArquivo = new javax.swing.JLabel();
        txtRbArquivo = new javax.swing.JTextField();
        btnRbIniciarRestauracao = new javax.swing.JButton();
        btnRbAbrir = new javax.swing.JButton();
        txtRbEditarPorta = new javax.swing.JTextField();
        chkRbEditarPorta = new javax.swing.JCheckBox();
        lblRestaurarNotificacao = new javax.swing.JLabel();
        pnlBdDetalheBackup = new javax.swing.JPanel();
        pnlBdLog = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txaBdLog = new javax.swing.JTextArea();
        pnlServidorBackup = new javax.swing.JPanel();
        tpServidorBackup = new javax.swing.JTabbedPane();
        pnlServidorControle = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnCsIniciar = new javax.swing.JButton();
        btnCsParar = new javax.swing.JButton();
        txtConfigServPorta = new javax.swing.JTextField();
        lblConfigServPorta = new javax.swing.JLabel();
        lblConfigServerAviso = new javax.swing.JLabel();
        pnlSsEstadoServidor1 = new javax.swing.JPanel();
        lblCsOperando = new javax.swing.JLabel();
        lblCsUsuariosConectados = new javax.swing.JLabel();
        lblCsFalhas = new javax.swing.JLabel();
        lblCsServidorIniciado = new javax.swing.JLabel();
        lblCsTempoOnline = new javax.swing.JLabel();
        lblCsServidorParado = new javax.swing.JLabel();
        sepSsSepararEstadoServidor1 = new javax.swing.JSeparator();
        lblCsBytesEnviados = new javax.swing.JLabel();
        lblCsBytesRecebidos = new javax.swing.JLabel();
        pnlServidorLog = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        txaServidorLog = new javax.swing.JTextArea();
        tobConfigBasicas = new javax.swing.JToolBar();
        lblQtdBkpAgendado = new javax.swing.JLabel();
        lblQtdBkpFlag = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        lblDataHora = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        mniBancoDados = new javax.swing.JMenuItem();
        mniRelatorios = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        mnuSobre = new javax.swing.JMenu();

        setTitle("Sistema Gerenciador de Backup");
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        buttonGroup1.add(radBackupArquivos);
        radBackupArquivos.setSelected(true);
        radBackupArquivos.setText("Backup de Arquivos");
        radBackupArquivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radBackupArquivosActionPerformed(evt);
            }
        });

        buttonGroup1.add(radBackupBancoDados);
        radBackupBancoDados.setText("Backup Banco de Dados");
        radBackupBancoDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radBackupBancoDadosActionPerformed(evt);
            }
        });

        buttonGroup1.add(radServidorBackup);
        radServidorBackup.setText("Servidor Backup");
        radServidorBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radServidorBackupActionPerformed(evt);
            }
        });

        pnlBackupArquivo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlBackupArquivo.setPreferredSize(new java.awt.Dimension(938, 566));

        tbConfigBackup.setMinimumSize(new java.awt.Dimension(105, 92));
        tbConfigBackup.setPreferredSize(new java.awt.Dimension(931, 546));

        pnlAgendarBackup.setPreferredSize(new java.awt.Dimension(842, 518));

        lblLocal.setText("Local:");

        lblDestino.setText("Destino:");

        txtLocal.setEditable(false);
        txtLocal.setBackground(new java.awt.Color(204, 204, 204));
        txtLocal.setAutoscrolls(false);

        btnLocal.setBackground(new java.awt.Color(0, 51, 153));
        btnLocal.setForeground(new java.awt.Color(255, 255, 255));
        btnLocal.setText("Local");
        btnLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalActionPerformed(evt);
            }
        });

        btnDestino.setBackground(new java.awt.Color(0, 51, 153));
        btnDestino.setForeground(new java.awt.Color(255, 255, 255));
        btnDestino.setText("Destino");
        btnDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDestinoActionPerformed(evt);
            }
        });

        txtDestino.setEditable(false);
        txtDestino.setBackground(new java.awt.Color(204, 204, 204));
        txtDestino.setAutoscrolls(false);

        btnAdicionar.setBackground(new java.awt.Color(0, 51, 153));
        btnAdicionar.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionar.setText("Adicionar");
        btnAdicionar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        lblAcaoDoBackup.setText("Ação do backup:");

        chkIncremental.setText("Incremental");
        chkIncremental.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkIncrementalActionPerformed(evt);
            }
        });

        chkCompactar.setText("Compactar");
        chkCompactar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCompactarActionPerformed(evt);
            }
        });

        lblNomeBackup.setText("Nome do Backup:");

        txtNomeBackup.setBackground(new java.awt.Color(204, 204, 204));

        lblDescricaoBackup.setText("Descrição do Backup:");

        txaDescricaoBackup.setBackground(new java.awt.Color(204, 204, 204));
        txaDescricaoBackup.setColumns(20);
        txaDescricaoBackup.setLineWrap(true);
        txaDescricaoBackup.setRows(3);
        txaDescricaoBackup.setWrapStyleWord(true);
        txaDescricaoBackup.setSelectionEnd(255);
        txaDescricaoBackup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txaDescricaoBackupKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txaDescricaoBackupKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(txaDescricaoBackup);

        lblCaracteres.setText("Caracteres:");

        pnlData.setBorder(javax.swing.BorderFactory.createTitledBorder("Data"));

        chkSegunda.setText("Segunda");
        chkSegunda.setVisible(false);
        chkSegunda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSegundaActionPerformed(evt);
            }
        });

        chkTerca.setText("Terça");
        chkTerca.setVisible(false);
        chkTerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTercaActionPerformed(evt);
            }
        });

        chkQuarta.setText("Quarta");
        chkQuarta.setVisible(false);
        chkQuarta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkQuartaActionPerformed(evt);
            }
        });

        chkQuinta.setText("Quinta");
        chkQuinta.setVisible(false);
        chkQuinta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkQuintaActionPerformed(evt);
            }
        });

        chkSexta.setText("Sexta");
        chkSexta.setVisible(false);
        chkSexta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSextaActionPerformed(evt);
            }
        });

        chkSabado.setText("Sábado");
        chkSabado.setVisible(false);
        chkSabado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSabadoActionPerformed(evt);
            }
        });

        chkDomingo.setText("Domingo");
        chkDomingo.setVisible(false);
        chkDomingo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDomingoActionPerformed(evt);
            }
        });

        chkData.setSelected(true);
        chkData.setText("Data:");
        chkData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDataActionPerformed(evt);
            }
        });

        chkContinuarBackup.setText("Após a data deseja continuar o backup?");
        chkContinuarBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkContinuarBackupActionPerformed(evt);
            }
        });

        lblDia.setText("Dia");

        cboDia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        lblMes.setText("Mês");

        cboMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));

        lblAno.setText("Ano");

        javax.swing.GroupLayout pnlDataLayout = new javax.swing.GroupLayout(pnlData);
        pnlData.setLayout(pnlDataLayout);
        pnlDataLayout.setHorizontalGroup(
            pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDataLayout.createSequentialGroup()
                        .addGap(0, 44, Short.MAX_VALUE)
                        .addComponent(chkSegunda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkTerca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkQuarta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkQuinta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkSexta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkSabado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkDomingo)
                        .addGap(117, 117, 117))
                    .addGroup(pnlDataLayout.createSequentialGroup()
                        .addComponent(chkContinuarBackup)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlDataLayout.createSequentialGroup()
                        .addComponent(chkData)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblAno)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pnlDataLayout.setVerticalGroup(
            pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataLayout.createSequentialGroup()
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkData)
                    .addComponent(lblDia)
                    .addComponent(cboDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMes)
                    .addComponent(cboMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAno)
                    .addComponent(cboAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkContinuarBackup)
                .addGap(18, 18, 18)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkSegunda)
                    .addComponent(chkTerca)
                    .addComponent(chkQuarta)
                    .addComponent(chkQuinta)
                    .addComponent(chkSexta)
                    .addComponent(chkSabado)
                    .addComponent(chkDomingo))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlHora.setBorder(javax.swing.BorderFactory.createTitledBorder("Hora"));

        spiMin.setModel(new javax.swing.SpinnerNumberModel(0, 0, 60, 1));

        lbl_min.setText("Min");

        spiHora.setModel(new javax.swing.SpinnerNumberModel(0, 0, 24, 1));

        lbl_hora.setText("Hora");

        javax.swing.GroupLayout pnlHoraLayout = new javax.swing.GroupLayout(pnlHora);
        pnlHora.setLayout(pnlHoraLayout);
        pnlHoraLayout.setHorizontalGroup(
            pnlHoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHoraLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_hora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spiHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl_min)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spiMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlHoraLayout.setVerticalGroup(
            pnlHoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoraLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(pnlHoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spiHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spiMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_hora)
                    .addComponent(lbl_min))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        chkNormal.setSelected(true);
        chkNormal.setText("Normal");
        chkNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkNormalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAgendarBackupLayout = new javax.swing.GroupLayout(pnlAgendarBackup);
        pnlAgendarBackup.setLayout(pnlAgendarBackupLayout);
        pnlAgendarBackupLayout.setHorizontalGroup(
            pnlAgendarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAgendarBackupLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAgendarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAgendarBackupLayout.createSequentialGroup()
                        .addComponent(txtNomeBackup, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNotificacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAgendarBackupLayout.createSequentialGroup()
                        .addGroup(pnlAgendarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlAgendarBackupLayout.createSequentialGroup()
                                .addComponent(lblLocal)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtLocal)
                            .addComponent(txtDestino))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlAgendarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLocal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDestino, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)))
                    .addComponent(jScrollPane2)
                    .addGroup(pnlAgendarBackupLayout.createSequentialGroup()
                        .addComponent(lblCaracteres)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAgendarBackupLayout.createSequentialGroup()
                        .addGroup(pnlAgendarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAcaoDoBackup)
                            .addComponent(lblNomeBackup)
                            .addGroup(pnlAgendarBackupLayout.createSequentialGroup()
                                .addComponent(chkNormal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkIncremental)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chkCompactar))
                            .addComponent(lblDestino)
                            .addComponent(lblDescricaoBackup)
                            .addGroup(pnlAgendarBackupLayout.createSequentialGroup()
                                .addComponent(pnlData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnlHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 28, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlAgendarBackupLayout.setVerticalGroup(
            pnlAgendarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAgendarBackupLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLocal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAgendarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDestino)
                .addGap(7, 7, 7)
                .addGroup(pnlAgendarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAgendarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAgendarBackupLayout.createSequentialGroup()
                        .addComponent(pnlHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAcaoDoBackup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAgendarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chkNormal)
                    .addGroup(pnlAgendarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkCompactar)
                        .addComponent(chkIncremental)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNomeBackup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAgendarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNomeBackup, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNotificacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricaoBackup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAgendarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCaracteres)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(957, 957, 957))
        );

        tbConfigBackup.addTab("Agendar Backup", pnlAgendarBackup);

        pnlBackupAgendado.setPreferredSize(new java.awt.Dimension(922, 508));

        tblBkpAgendados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblBkpAgendados.setSelectionBackground(new java.awt.Color(0, 102, 255));
        tblBkpAgendados.setSelectionForeground(new java.awt.Color(240, 240, 240));
        tblBkpAgendados.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblBkpAgendados.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblBkpAgendados);
        tblBkpAgendados.setModel(new DefaultTableModel(new String[][]{}, new String[]{"Nome", "Data", "Hora", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab", "Dom", "Estado"}));
        tblBkpAgendados.getColumnModel().getColumn(0).setPreferredWidth(20);//nome
        tblBkpAgendados.getColumnModel().getColumn(1).setPreferredWidth(20);//data
        tblBkpAgendados.getColumnModel().getColumn(2).setPreferredWidth(20);//hora
        tblBkpAgendados.getColumnModel().getColumn(3).setPreferredWidth(5);//seg
        tblBkpAgendados.getColumnModel().getColumn(4).setPreferredWidth(5);//ter
        tblBkpAgendados.getColumnModel().getColumn(5).setPreferredWidth(5);//qua
        tblBkpAgendados.getColumnModel().getColumn(6).setPreferredWidth(5);//qui
        tblBkpAgendados.getColumnModel().getColumn(7).setPreferredWidth(5);//sex
        tblBkpAgendados.getColumnModel().getColumn(8).setPreferredWidth(5);//sab
        tblBkpAgendados.getColumnModel().getColumn(9).setPreferredWidth(5);//dom
        tblBkpAgendados.getColumnModel().getColumn(10).setPreferredWidth(50);//Estado

        btn_deletar.setBackground(new java.awt.Color(0, 51, 153));
        btn_deletar.setForeground(new java.awt.Color(255, 255, 255));
        btn_deletar.setText("Deletar");
        btn_deletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deletarActionPerformed(evt);
            }
        });

        btnDetalhe.setBackground(new java.awt.Color(0, 51, 153));
        btnDetalhe.setForeground(new java.awt.Color(255, 255, 255));
        btnDetalhe.setText("Detalhe");
        btnDetalhe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalheActionPerformed(evt);
            }
        });

        btnIniciarThread.setBackground(new java.awt.Color(0, 51, 153));
        btnIniciarThread.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciarThread.setText("Iniciar");
        btnIniciarThread.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarThreadActionPerformed(evt);
            }
        });

        btnPausar.setBackground(new java.awt.Color(0, 51, 153));
        btnPausar.setForeground(new java.awt.Color(255, 255, 255));
        btnPausar.setText("Pausar");
        btnPausar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPausarActionPerformed(evt);
            }
        });

        btnParar.setBackground(new java.awt.Color(0, 51, 153));
        btnParar.setForeground(new java.awt.Color(255, 255, 255));
        btnParar.setText("Parar");
        btnParar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPararActionPerformed(evt);
            }
        });

        btnImediato.setBackground(new java.awt.Color(0, 51, 153));
        btnImediato.setForeground(new java.awt.Color(255, 255, 255));
        btnImediato.setText("Imediato");
        btnImediato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImediatoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBackupAgendadoLayout = new javax.swing.GroupLayout(pnlBackupAgendado);
        pnlBackupAgendado.setLayout(pnlBackupAgendadoLayout);
        pnlBackupAgendadoLayout.setHorizontalGroup(
            pnlBackupAgendadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackupAgendadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBackupAgendadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1069, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackupAgendadoLayout.createSequentialGroup()
                        .addComponent(lblInformacao)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackupAgendadoLayout.createSequentialGroup()
                        .addComponent(btnImediato)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnIniciarThread)
                        .addGap(18, 18, 18)
                        .addComponent(btnPausar)
                        .addGap(18, 18, 18)
                        .addComponent(btnParar)
                        .addGap(18, 18, 18)
                        .addComponent(btnDetalhe)
                        .addGap(18, 18, 18)
                        .addComponent(btn_deletar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlBackupAgendadoLayout.setVerticalGroup(
            pnlBackupAgendadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackupAgendadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBackupAgendadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_deletar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDetalhe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnParar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPausar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlBackupAgendadoLayout.createSequentialGroup()
                        .addComponent(btnIniciarThread, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnImediato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(lblInformacao)
                .addGap(35, 35, 35))
        );

        tbConfigBackup.addTab("Backup Agendado", pnlBackupAgendado);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Backup"));

        lblDbIdentificador.setText("Identificador:");

        lblDbNome.setText("Nome:");

        lblDbTipoBackup.setText("Tipo do Backup:");

        lblDbDiaBackup.setText("Dia do Backup:");

        lblDbHoraBackup.setText("Hora do Backup:");

        lblDbLocalBackp.setText("Local:");

        lblDbDestinoBackup.setText("Destino:");

        lblDbDescricao.setText("Descrição:");

        lblDbDataCriacao.setText("Data da criação:");

        txaDbDescricao.setEditable(false);
        txaDbDescricao.setColumns(20);
        txaDbDescricao.setLineWrap(true);
        txaDbDescricao.setRows(4);
        txaDbDescricao.setWrapStyleWord(true);
        txaDbDescricao.setFocusable(false);
        jScrollPane4.setViewportView(txaDbDescricao);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDbIdentificador)
                            .addComponent(lblDbNome)
                            .addComponent(lblDbDiaBackup)
                            .addComponent(lblDbHoraBackup)
                            .addComponent(lblDbTipoBackup)
                            .addComponent(lblDbDataCriacao)
                            .addComponent(lblDbDescricao)
                            .addComponent(lblDbLocalBackp)
                            .addComponent(lblDbDestinoBackup))
                        .addGap(0, 753, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDbIdentificador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDbNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDbDiaBackup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDbDataCriacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDbHoraBackup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDbTipoBackup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDbLocalBackp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDbDestinoBackup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDbDescricao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlDetalheBackupLayout = new javax.swing.GroupLayout(pnlDetalheBackup);
        pnlDetalheBackup.setLayout(pnlDetalheBackupLayout);
        pnlDetalheBackupLayout.setHorizontalGroup(
            pnlDetalheBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalheBackupLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDetalheBackupLayout.setVerticalGroup(
            pnlDetalheBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetalheBackupLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(182, Short.MAX_VALUE))
        );

        tbConfigBackup.addTab("Detalhe do Backup", pnlDetalheBackup);

        jScrollPane3.setAutoscrolls(true);

        txaLog.setEditable(false);
        txaLog.setColumns(20);
        txaLog.setLineWrap(true);
        txaLog.setRows(5);
        txaLog.setWrapStyleWord(true);
        txaLog.setCaretPosition(txaLog.getDocument().getLength());
        txaLog.setFocusable(false);
        jScrollPane3.setViewportView(txaLog);

        javax.swing.GroupLayout tpLogLayout = new javax.swing.GroupLayout(tpLog);
        tpLog.setLayout(tpLogLayout);
        tpLogLayout.setHorizontalGroup(
            tpLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE)
        );
        tpLogLayout.setVerticalGroup(
            tpLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
        );

        jScrollPane3.getAccessibleContext().setAccessibleParent(tpLog);

        tbConfigBackup.addTab("Log", tpLog);

        javax.swing.GroupLayout pnlBackupArquivoLayout = new javax.swing.GroupLayout(pnlBackupArquivo);
        pnlBackupArquivo.setLayout(pnlBackupArquivoLayout);
        pnlBackupArquivoLayout.setHorizontalGroup(
            pnlBackupArquivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbConfigBackup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlBackupArquivoLayout.setVerticalGroup(
            pnlBackupArquivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbConfigBackup, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
        );

        pnlBkpBancoDados.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlBkpBancoDados.setPreferredSize(new java.awt.Dimension(938, 566));

        lblBdBanco.setText("Banco:");

        cboBdBanco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MySql", "PostgreSql", "ParadoxJDBC" }));

        lblBdServidor.setText("Sevidor:");

        txtBdServidor.setBackground(new java.awt.Color(204, 204, 204));

        lblBdNomeBanco.setText("Nome do banco:");

        txtBdNomeBanco.setBackground(new java.awt.Color(204, 204, 204));

        lblBdUsuario.setText("Usuario:");

        txtBdUsuario.setBackground(new java.awt.Color(204, 204, 204));

        lblBdSenhaBanco.setText("Senha Banco:");

        pswBdSenhaBanco.setBackground(new java.awt.Color(204, 204, 204));

        btnBdAdicionarBackupBanco.setBackground(new java.awt.Color(0, 51, 153));
        btnBdAdicionarBackupBanco.setForeground(new java.awt.Color(255, 255, 255));
        btnBdAdicionarBackupBanco.setText("Adicionar Backup");
        btnBdAdicionarBackupBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBdAdicionarBackupBancoActionPerformed(evt);
            }
        });

        txtBdEditarPorta.setBackground(new java.awt.Color(204, 204, 204));
        txtBdEditarPorta.setEnabled(false);
        txtBdEditarPorta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBdEditarPortaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBdEditarPortaKeyTyped(evt);
            }
        });

        chkBdEditarPorta.setText("Editar porta");
        chkBdEditarPorta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBdEditarPortaActionPerformed(evt);
            }
        });

        lblBdDestino.setText("Destino:");

        txtBdDestino.setEditable(false);
        txtBdDestino.setBackground(new java.awt.Color(204, 204, 204));

        btnBdDestino.setBackground(new java.awt.Color(0, 51, 153));
        btnBdDestino.setForeground(new java.awt.Color(255, 255, 255));
        btnBdDestino.setText("Destino");
        btnBdDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBdDestinoActionPerformed(evt);
            }
        });

        pnlBdData.setBorder(javax.swing.BorderFactory.createTitledBorder("Data"));

        lblBdDia.setText("Dia:");

        lblBdMes.setText("Mês:");

        cboBdMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));

        lblBdAno.setText("Ano:");

        chkBdDomingo.setText("Domingo");
        chkBdDomingo.setVisible(false);

        chkBdSabado.setText("Sábado");
        chkBdSabado.setVisible(false);

        chkBdSexta.setText("Sexta");
        chkBdSexta.setVisible(false);

        chkBdQuinta.setText("Quinta");
        chkBdQuinta.setVisible(false);

        chkBdQuarta.setText("Quarta");
        chkBdQuarta.setVisible(false);

        chkBdTerca.setText("Terça");
        chkBdTerca.setVisible(false);

        chkBdSegunda.setText("Segunda");
        chkBdSegunda.setVisible(false);

        cboBdDia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        chkBdContinuarBackup.setText("Após a data deseja continuar o backup?");
        chkBdContinuarBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBdContinuarBackupActionPerformed(evt);
            }
        });

        chkBdData.setSelected(true);
        chkBdData.setText("Data:");
        chkBdData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBdDataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBdDataLayout = new javax.swing.GroupLayout(pnlBdData);
        pnlBdData.setLayout(pnlBdDataLayout);
        pnlBdDataLayout.setHorizontalGroup(
            pnlBdDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBdDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBdDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBdDataLayout.createSequentialGroup()
                        .addComponent(chkBdData)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBdDia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(cboBdDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBdMes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboBdMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBdAno)
                        .addGap(11, 11, 11)
                        .addComponent(cboBdAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chkBdContinuarBackup)
                    .addGroup(pnlBdDataLayout.createSequentialGroup()
                        .addComponent(chkBdSegunda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkBdTerca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkBdQuarta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkBdQuinta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkBdSexta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkBdSabado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkBdDomingo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBdDataLayout.setVerticalGroup(
            pnlBdDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBdDataLayout.createSequentialGroup()
                .addGroup(pnlBdDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBdDia)
                    .addComponent(lblBdMes)
                    .addComponent(cboBdMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBdAno)
                    .addComponent(cboBdAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboBdDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkBdData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkBdContinuarBackup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlBdDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkBdSegunda)
                    .addComponent(chkBdTerca)
                    .addComponent(chkBdQuarta)
                    .addComponent(chkBdQuinta)
                    .addComponent(chkBdSexta)
                    .addComponent(chkBdSabado)
                    .addComponent(chkBdDomingo))
                .addContainerGap())
        );

        pnlBdHora.setBorder(javax.swing.BorderFactory.createTitledBorder("Hora"));

        lblBdHora.setText("Hora");

        spiBdHora.setModel(new javax.swing.SpinnerNumberModel(0, 0, 24, 1));

        lblBdMin.setText("Min");

        spiBdMin.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));

        javax.swing.GroupLayout pnlBdHoraLayout = new javax.swing.GroupLayout(pnlBdHora);
        pnlBdHora.setLayout(pnlBdHoraLayout);
        pnlBdHoraLayout.setHorizontalGroup(
            pnlBdHoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBdHoraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBdHora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spiBdHora, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBdMin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spiBdMin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBdHoraLayout.setVerticalGroup(
            pnlBdHoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBdHoraLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(pnlBdHoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spiBdMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBdHora)
                    .addComponent(lblBdMin)
                    .addComponent(spiBdHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlBdAgendarBackupLayout = new javax.swing.GroupLayout(pnlBdAgendarBackup);
        pnlBdAgendarBackup.setLayout(pnlBdAgendarBackupLayout);
        pnlBdAgendarBackupLayout.setHorizontalGroup(
            pnlBdAgendarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBdAgendarBackupLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBdAgendarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBdAgendarBackupLayout.createSequentialGroup()
                        .addComponent(txtBdDestino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBdDestino))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBdAgendarBackupLayout.createSequentialGroup()
                        .addGroup(pnlBdAgendarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBdAgendarBackupLayout.createSequentialGroup()
                                .addComponent(lblBdNotificacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(123, 123, 123))
                            .addGroup(pnlBdAgendarBackupLayout.createSequentialGroup()
                                .addGroup(pnlBdAgendarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblBdNomeBanco)
                                    .addComponent(lblBdUsuario)
                                    .addComponent(lblBdSenhaBanco)
                                    .addComponent(lblBdServidor)
                                    .addGroup(pnlBdAgendarBackupLayout.createSequentialGroup()
                                        .addComponent(lblBdBanco)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboBdBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(chkBdEditarPorta)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtBdEditarPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 416, Short.MAX_VALUE)))
                        .addComponent(btnBdAdicionarBackupBanco))
                    .addComponent(txtBdServidor)
                    .addComponent(txtBdNomeBanco)
                    .addGroup(pnlBdAgendarBackupLayout.createSequentialGroup()
                        .addGroup(pnlBdAgendarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBdAgendarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtBdUsuario, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(pswBdSenhaBanco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblBdDestino)
                            .addGroup(pnlBdAgendarBackupLayout.createSequentialGroup()
                                .addComponent(pnlBdData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlBdHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlBdAgendarBackupLayout.setVerticalGroup(
            pnlBdAgendarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBdAgendarBackupLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBdDestino)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBdAgendarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBdDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBdDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnlBdAgendarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlBdHora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBdData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlBdAgendarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBdBanco)
                    .addComponent(cboBdBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkBdEditarPorta)
                    .addComponent(txtBdEditarPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(lblBdServidor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBdServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBdNomeBanco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBdNomeBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBdUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBdSenhaBanco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pswBdSenhaBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBdAgendarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBdNotificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBdAdicionarBackupBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tpDbBackupBancoDados.addTab("Agendar Backup", pnlBdAgendarBackup);

        tblBdBackupAgendado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Banco", "Nome do Banco", "Usuario", "Data", "Hora", "Servidor", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBdBackupAgendado.setSelectionBackground(new java.awt.Color(51, 102, 255));
        tblBdBackupAgendado.setSelectionForeground(new java.awt.Color(240, 240, 240));
        tblBdBackupAgendado.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblBdBackupAgendado.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(tblBdBackupAgendado);
        tblBdBackupAgendado.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnBdExcluir.setBackground(new java.awt.Color(0, 51, 153));
        btnBdExcluir.setForeground(new java.awt.Color(255, 255, 255));
        btnBdExcluir.setText("Excluir");
        btnBdExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBdExcluirActionPerformed(evt);
            }
        });

        btnBdDetalhe.setBackground(new java.awt.Color(0, 51, 153));
        btnBdDetalhe.setForeground(new java.awt.Color(255, 255, 255));
        btnBdDetalhe.setText("Detalhe");
        btnBdDetalhe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBdDetalheActionPerformed(evt);
            }
        });

        btnBdParar.setBackground(new java.awt.Color(0, 51, 153));
        btnBdParar.setForeground(new java.awt.Color(255, 255, 255));
        btnBdParar.setText("Parar");
        btnBdParar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBdPararActionPerformed(evt);
            }
        });

        btnBdPausar.setBackground(new java.awt.Color(0, 51, 153));
        btnBdPausar.setForeground(new java.awt.Color(255, 255, 255));
        btnBdPausar.setText("Pausar");
        btnBdPausar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBdPausarActionPerformed(evt);
            }
        });

        btnBdIniciar.setBackground(new java.awt.Color(0, 51, 153));
        btnBdIniciar.setForeground(new java.awt.Color(255, 255, 255));
        btnBdIniciar.setText("Iniciar");
        btnBdIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBdIniciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBdBackupAgendadoLayout = new javax.swing.GroupLayout(pnlBdBackupAgendado);
        pnlBdBackupAgendado.setLayout(pnlBdBackupAgendadoLayout);
        pnlBdBackupAgendadoLayout.setHorizontalGroup(
            pnlBdBackupAgendadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBdBackupAgendadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBdBackupAgendadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 902, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBdBackupAgendadoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBdIniciar)
                        .addGap(18, 18, 18)
                        .addComponent(btnBdPausar)
                        .addGap(18, 18, 18)
                        .addComponent(btnBdParar)
                        .addGap(18, 18, 18)
                        .addComponent(btnBdDetalhe)
                        .addGap(18, 18, 18)
                        .addComponent(btnBdExcluir)))
                .addContainerGap())
        );
        pnlBdBackupAgendadoLayout.setVerticalGroup(
            pnlBdBackupAgendadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBdBackupAgendadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBdBackupAgendadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBdDetalhe, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBdExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBdParar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBdPausar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBdIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tpDbBackupBancoDados.addTab("Backup Agendado", pnlBdBackupAgendado);

        lblRbBanco.setText("Banco:");

        cboRbBanco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MySql", "PostgreSql", "ParadoxJDBC" }));

        lblRbServidor.setText("Sevidor:");

        txtRbServidor.setBackground(new java.awt.Color(204, 204, 204));

        lblRbNomeBanco.setText("Nome do banco:");

        txtRbNomeBanco.setBackground(new java.awt.Color(204, 204, 204));

        lblRbUsuario.setText("Usuario:");

        txtRbUsuario.setBackground(new java.awt.Color(204, 204, 204));

        lblRbSenhaBanco.setText("Senha Banco:");

        pswRbSenhaBanco.setBackground(new java.awt.Color(204, 204, 204));

        lblRbArquivo.setText("Arquivo:");

        txtRbArquivo.setBackground(new java.awt.Color(204, 204, 204));
        txtRbArquivo.setEnabled(false);

        btnRbIniciarRestauracao.setBackground(new java.awt.Color(0, 51, 153));
        btnRbIniciarRestauracao.setForeground(new java.awt.Color(255, 255, 255));
        btnRbIniciarRestauracao.setText("Iniciar Restauração");
        btnRbIniciarRestauracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRbIniciarRestauracaoActionPerformed(evt);
            }
        });

        btnRbAbrir.setBackground(new java.awt.Color(0, 51, 153));
        btnRbAbrir.setForeground(new java.awt.Color(255, 255, 255));
        btnRbAbrir.setText("Abrir");

        txtRbEditarPorta.setBackground(new java.awt.Color(204, 204, 204));
        txtRbEditarPorta.setEnabled(false);
        txtRbEditarPorta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRbEditarPortaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRbEditarPortaKeyTyped(evt);
            }
        });

        chkRbEditarPorta.setText("Editar porta");
        chkRbEditarPorta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRbEditarPortaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlRestaurarBackupLayout = new javax.swing.GroupLayout(pnlRestaurarBackup);
        pnlRestaurarBackup.setLayout(pnlRestaurarBackupLayout);
        pnlRestaurarBackupLayout.setHorizontalGroup(
            pnlRestaurarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRestaurarBackupLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRestaurarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRestaurarBackupLayout.createSequentialGroup()
                        .addComponent(lblRestaurarNotificacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRbIniciarRestauracao))
                    .addComponent(txtRbServidor)
                    .addGroup(pnlRestaurarBackupLayout.createSequentialGroup()
                        .addComponent(txtRbArquivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRbAbrir))
                    .addComponent(txtRbNomeBanco)
                    .addGroup(pnlRestaurarBackupLayout.createSequentialGroup()
                        .addGroup(pnlRestaurarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRbServidor)
                            .addComponent(lblRbNomeBanco)
                            .addComponent(lblRbSenhaBanco)
                            .addComponent(lblRbArquivo)
                            .addComponent(lblRbUsuario)
                            .addGroup(pnlRestaurarBackupLayout.createSequentialGroup()
                                .addComponent(lblRbBanco)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboRbBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75)
                                .addComponent(chkRbEditarPorta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtRbEditarPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlRestaurarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(pswRbSenhaBanco, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtRbUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 480, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlRestaurarBackupLayout.setVerticalGroup(
            pnlRestaurarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRestaurarBackupLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRestaurarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRbBanco)
                    .addComponent(cboRbBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkRbEditarPorta)
                    .addComponent(txtRbEditarPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRbServidor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRbServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRbNomeBanco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRbNomeBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRbUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRbSenhaBanco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pswRbSenhaBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRbArquivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRestaurarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRbArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRbAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(132, 132, 132)
                .addGroup(pnlRestaurarBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRbIniciarRestauracao, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRestaurarNotificacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tpDbBackupBancoDados.addTab("Restaurar Backup", pnlRestaurarBackup);

        javax.swing.GroupLayout pnlBdDetalheBackupLayout = new javax.swing.GroupLayout(pnlBdDetalheBackup);
        pnlBdDetalheBackup.setLayout(pnlBdDetalheBackupLayout);
        pnlBdDetalheBackupLayout.setHorizontalGroup(
            pnlBdDetalheBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 926, Short.MAX_VALUE)
        );
        pnlBdDetalheBackupLayout.setVerticalGroup(
            pnlBdDetalheBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 511, Short.MAX_VALUE)
        );

        tpDbBackupBancoDados.addTab("Detalhe do Backup", pnlBdDetalheBackup);

        jScrollPane6.setAutoscrolls(true);

        txaBdLog.setEditable(false);
        txaBdLog.setColumns(20);
        txaBdLog.setLineWrap(true);
        txaBdLog.setRows(5);
        txaBdLog.setWrapStyleWord(true);
        txaBdLog.setCaretPosition(txaBdLog.getDocument().getLength());
        txaBdLog.setFocusable(false);
        jScrollPane6.setViewportView(txaBdLog);

        javax.swing.GroupLayout pnlBdLogLayout = new javax.swing.GroupLayout(pnlBdLog);
        pnlBdLog.setLayout(pnlBdLogLayout);
        pnlBdLogLayout.setHorizontalGroup(
            pnlBdLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE)
        );
        pnlBdLogLayout.setVerticalGroup(
            pnlBdLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBdLogLayout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                .addContainerGap())
        );

        tpDbBackupBancoDados.addTab("Log", pnlBdLog);

        javax.swing.GroupLayout pnlBkpBancoDadosLayout = new javax.swing.GroupLayout(pnlBkpBancoDados);
        pnlBkpBancoDados.setLayout(pnlBkpBancoDadosLayout);
        pnlBkpBancoDadosLayout.setHorizontalGroup(
            pnlBkpBancoDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpDbBackupBancoDados)
        );
        pnlBkpBancoDadosLayout.setVerticalGroup(
            pnlBkpBancoDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBkpBancoDadosLayout.createSequentialGroup()
                .addComponent(tpDbBackupBancoDados)
                .addContainerGap())
        );

        pnlServidorBackup.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tpServidorBackup.setPreferredSize(new java.awt.Dimension(1094, 565));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Servidor FTP Local"));

        btnCsIniciar.setBackground(new java.awt.Color(0, 51, 153));
        btnCsIniciar.setForeground(new java.awt.Color(255, 255, 255));
        btnCsIniciar.setText("Iniciar");
        btnCsIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCsIniciarActionPerformed(evt);
            }
        });

        btnCsParar.setBackground(new java.awt.Color(0, 51, 153));
        btnCsParar.setForeground(new java.awt.Color(255, 255, 255));
        btnCsParar.setText("Parar");
        btnCsParar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCsPararActionPerformed(evt);
            }
        });

        txtConfigServPorta.setText("2222");

        lblConfigServPorta.setText("Porta:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnCsIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCsParar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(147, 147, 147)
                        .addComponent(lblConfigServerAviso))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblConfigServPorta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtConfigServPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConfigServPorta)
                    .addComponent(txtConfigServPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCsParar)
                    .addComponent(btnCsIniciar)
                    .addComponent(lblConfigServerAviso))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlSsEstadoServidor1.setBorder(javax.swing.BorderFactory.createTitledBorder("Estado servidor FTP local"));

        lblCsOperando.setText("Operando:");

        lblCsUsuariosConectados.setText("Usuarios conectados:");

        lblCsFalhas.setText("Falhas:");

        lblCsServidorIniciado.setText("Servidor iniciado:");

        lblCsTempoOnline.setText("Tempo online:");

        lblCsServidorParado.setText("Servidor parado:");

        sepSsSepararEstadoServidor1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblCsBytesEnviados.setText("Bytes enviados:");

        lblCsBytesRecebidos.setText("Bytes recebidos:");

        javax.swing.GroupLayout pnlSsEstadoServidor1Layout = new javax.swing.GroupLayout(pnlSsEstadoServidor1);
        pnlSsEstadoServidor1.setLayout(pnlSsEstadoServidor1Layout);
        pnlSsEstadoServidor1Layout.setHorizontalGroup(
            pnlSsEstadoServidor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSsEstadoServidor1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSsEstadoServidor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCsOperando)
                    .addComponent(lblCsUsuariosConectados)
                    .addComponent(lblCsFalhas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
                .addComponent(sepSsSepararEstadoServidor1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSsEstadoServidor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCsServidorParado)
                    .addGroup(pnlSsEstadoServidor1Layout.createSequentialGroup()
                        .addGroup(pnlSsEstadoServidor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCsTempoOnline)
                            .addComponent(lblCsServidorIniciado))
                        .addGap(188, 188, 188)
                        .addGroup(pnlSsEstadoServidor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCsBytesEnviados)
                            .addComponent(lblCsBytesRecebidos))))
                .addContainerGap(253, Short.MAX_VALUE))
        );
        pnlSsEstadoServidor1Layout.setVerticalGroup(
            pnlSsEstadoServidor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSsEstadoServidor1Layout.createSequentialGroup()
                .addGroup(pnlSsEstadoServidor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sepSsSepararEstadoServidor1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlSsEstadoServidor1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlSsEstadoServidor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSsEstadoServidor1Layout.createSequentialGroup()
                                .addGroup(pnlSsEstadoServidor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlSsEstadoServidor1Layout.createSequentialGroup()
                                        .addComponent(lblCsServidorIniciado)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblCsTempoOnline))
                                    .addGroup(pnlSsEstadoServidor1Layout.createSequentialGroup()
                                        .addComponent(lblCsBytesEnviados)
                                        .addGap(34, 34, 34)
                                        .addComponent(lblCsBytesRecebidos)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCsServidorParado))
                            .addGroup(pnlSsEstadoServidor1Layout.createSequentialGroup()
                                .addComponent(lblCsOperando)
                                .addGap(18, 18, 18)
                                .addComponent(lblCsUsuariosConectados)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblCsFalhas)))))
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout pnlServidorControleLayout = new javax.swing.GroupLayout(pnlServidorControle);
        pnlServidorControle.setLayout(pnlServidorControleLayout);
        pnlServidorControleLayout.setHorizontalGroup(
            pnlServidorControleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlServidorControleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlServidorControleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlSsEstadoServidor1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlServidorControleLayout.setVerticalGroup(
            pnlServidorControleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlServidorControleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSsEstadoServidor1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(266, Short.MAX_VALUE))
        );

        tpServidorBackup.addTab("Controle Servidor", pnlServidorControle);

        txaServidorLog.setEditable(false);
        txaServidorLog.setColumns(20);
        txaServidorLog.setLineWrap(true);
        txaServidorLog.setRows(5);
        txaServidorLog.setWrapStyleWord(true);
        txaServidorLog.setCaretPosition(txaServidorLog.getDocument().getLength());
        txaServidorLog.setFocusable(false);
        jScrollPane8.setViewportView(txaServidorLog);

        javax.swing.GroupLayout pnlServidorLogLayout = new javax.swing.GroupLayout(pnlServidorLog);
        pnlServidorLog.setLayout(pnlServidorLogLayout);
        pnlServidorLogLayout.setHorizontalGroup(
            pnlServidorLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 1074, Short.MAX_VALUE)
        );
        pnlServidorLogLayout.setVerticalGroup(
            pnlServidorLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
        );

        tpServidorBackup.addTab("Log", pnlServidorLog);

        javax.swing.GroupLayout pnlServidorBackupLayout = new javax.swing.GroupLayout(pnlServidorBackup);
        pnlServidorBackup.setLayout(pnlServidorBackupLayout);
        pnlServidorBackupLayout.setHorizontalGroup(
            pnlServidorBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpServidorBackup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlServidorBackupLayout.setVerticalGroup(
            pnlServidorBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpServidorBackup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tpServidorBackup.getAccessibleContext().setAccessibleName("Controle Servidor");

        tobConfigBasicas.setFloatable(false);
        tobConfigBasicas.setRollover(true);

        lblQtdBkpAgendado.setText("Quantidade de backup agendado:");
        tobConfigBasicas.add(lblQtdBkpAgendado);
        tobConfigBasicas.add(lblQtdBkpFlag);
        tobConfigBasicas.add(jSeparator1);

        lblDataHora.setText("               data hora");
        tobConfigBasicas.add(lblDataHora);

        jMenu1.setText("Configuração");

        jMenuItem1.setText("Alterar senha");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        mniBancoDados.setText("Banco de Dados");
        mniBancoDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniBancoDadosActionPerformed(evt);
            }
        });
        jMenu1.add(mniBancoDados);

        mniRelatorios.setText("Relatórios");
        mniRelatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniRelatoriosActionPerformed(evt);
            }
        });
        jMenu1.add(mniRelatorios);

        jMenuItem2.setText("Sair");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        mnuSobre.setText("Sobre");
        jMenuBar1.add(mnuSobre);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radBackupArquivos)
                .addGap(18, 18, 18)
                .addComponent(radBackupBancoDados)
                .addGap(18, 18, 18)
                .addComponent(radServidorBackup)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnlServidorBackup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlBkpBancoDados, javax.swing.GroupLayout.DEFAULT_SIZE, 1094, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlBackupArquivo, javax.swing.GroupLayout.DEFAULT_SIZE, 1094, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tobConfigBasicas, javax.swing.GroupLayout.DEFAULT_SIZE, 1075, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radBackupArquivos)
                    .addComponent(radBackupBancoDados)
                    .addComponent(radServidorBackup))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlServidorBackup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addComponent(pnlBkpBancoDados, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(33, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(34, Short.MAX_VALUE)
                    .addComponent(pnlBackupArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(29, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 596, Short.MAX_VALUE)
                    .addComponent(tobConfigBasicas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pnlBkpBancoDados.setVisible(false);
        pnlServidorBackup.setVisible(false);
        tobConfigBasicas.getAccessibleContext().setAccessibleParent(this);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new Logout(this).setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new RedefinirSenhaLocal().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnIniciarThreadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarThreadActionPerformed
        if (tblBkpAgendados.getSelectedRow() >= 0) {
            swingBackupArquivo.iniciarThreadArquivo(tblBkpAgendados.getSelectedRow());
        }
    }//GEN-LAST:event_btnIniciarThreadActionPerformed

    private void btnDetalheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalheActionPerformed
        if (tblBkpAgendados.getSelectedRow() >= 0) {
            swingBackupArquivo.detalharThreadArquivo(tblBkpAgendados.getSelectedRow());
        }
   }//GEN-LAST:event_btnDetalheActionPerformed

    private void btn_deletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deletarActionPerformed
        if (tblBkpAgendados.getSelectedRow() >= 0) {
            SwingBackupArquivo.excluirThreadArquivo(tblBkpAgendados.getSelectedRow());
        }
    }//GEN-LAST:event_btn_deletarActionPerformed

    private void txaDescricaoBackupKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txaDescricaoBackupKeyReleased
        if (txaDescricaoBackup.getText().length() > 254) {
            txaDescricaoBackup.setText(txaDescricaoBackup.getText().substring(0, 255));
        }
        lblCaracteres.setText(Acoes.CARACTERES + ": " + txaDescricaoBackup.getText().length());
    }//GEN-LAST:event_txaDescricaoBackupKeyReleased

    private void txaDescricaoBackupKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txaDescricaoBackupKeyPressed
        if (txaDescricaoBackup.getText().length() > 254) {
            txaDescricaoBackup.setText(txaDescricaoBackup.getText().substring(0, 255));
        }
        lblCaracteres.setText(Acoes.CARACTERES + ": " + txaDescricaoBackup.getText().length());
    }//GEN-LAST:event_txaDescricaoBackupKeyPressed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        if ("".equals(txtLocal.getText())) {
            lblNotificacoes.setText(Acoes.O_CAMPO_LOCAL_NAO_PODE_FICAR_VAZIO);
            return;
        } else if ("".equals(txtDestino.getText())) {
            lblNotificacoes.setText(Acoes.O_DESTINO_NAO_PODE_FICAR_VAZIO);
            return;
        } else if ("".equals(txtNomeBackup.getText())) {
            lblNotificacoes.setText(Acoes.O_NOME_DO_BACKUP_NAO_PODE_FICAR_VAZIO);
            return;
        } else {
            lblNotificacoes.setText("");
        }
        BackupArquivo backup = new BackupArquivo();
        backup.setDataCriacao(new DataReturn().sysDataPath());
        backup.setDescricao(txaDescricaoBackup.getText());

        backup.setLocal(txtLocal.getText());
        backup.setDestino(txtDestino.getText());
        backup.setNome(txtNomeBackup.getText());
        
        backup.setHost(ftpIp);
        backup.setUsuario(ftpLogin);
        backup.setSenha(ftpSenha);
        backup.setPorta(ftpPorta);
        backup.setModoConexao(ftpModoConexao);

        backup.setFlagSemana(String.valueOf(chkContinuarBackup.isSelected()));
        backup.setDom(chkDomingo.isSelected() && chkContinuarBackup.isSelected() ? NomeAbreviacao.DOMINGO : "false");
        backup.setSeg(chkSegunda.isSelected() && chkContinuarBackup.isSelected() ? NomeAbreviacao.SEGUNDA_FEIRA : "false");
        backup.setTer(chkTerca.isSelected() && chkContinuarBackup.isSelected() ? NomeAbreviacao.TERCA_FEIRA : "false");
        backup.setQua(chkQuarta.isSelected() && chkContinuarBackup.isSelected() ? NomeAbreviacao.QUARTA_FEIRA : "false");
        backup.setQui(chkQuinta.isSelected() && chkContinuarBackup.isSelected() ? NomeAbreviacao.QUINTA_FEIRA : "false");
        backup.setSex(chkSexta.isSelected() && chkContinuarBackup.isSelected() ? NomeAbreviacao.SEXTA_FEIRA : "false");
        backup.setSab(chkSabado.isSelected() && chkContinuarBackup.isSelected() ? NomeAbreviacao.SABADO : "false");

        backup.setFlagData(String.valueOf(chkData.isSelected()));
        if (chkData.isSelected()) {
            backup.setDataBackupAgendado(cboDia.getSelectedItem().toString() + "-" + ((cboMes.getSelectedIndex() + 1) < 10 ? "0" + (cboMes.getSelectedIndex() + 1) : (cboMes.getSelectedIndex() + 1)) + "-" + cboAno.getSelectedItem());
        } else {
            backup.setDataBackupAgendado("N/A");
        }

        backup.setHoraMin(String.valueOf(Integer.parseInt(String.valueOf(spiHora.getValue())) < 10 ? "0" + spiHora.getValue() : spiHora.getValue()) + ":" + String.valueOf(Integer.parseInt(String.valueOf(spiMin.getValue())) < 10 ? "0" + spiMin.getValue() : spiMin.getValue()));

        backup.setNormal(String.valueOf(chkNormal.isSelected()));
        backup.setExcluir("");
        backup.setBkpIncremental(String.valueOf(chkIncremental.isSelected()));
        backup.setCompactar(String.valueOf(chkCompactar.isSelected()));

        swingBackupArquivo.addNovaThread(backup);
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void chkSextaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSextaActionPerformed
        if (chkSegunda.isSelected() == false && chkTerca.isSelected() == false && chkQuarta.isSelected() == false && chkQuinta.isSelected() == false && chkSabado.isSelected() == false && chkDomingo.isSelected() == false) {
            chkSexta.setSelected(true);
        }
    }//GEN-LAST:event_chkSextaActionPerformed

    private void chkQuintaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkQuintaActionPerformed
        if (chkSegunda.isSelected() == false && chkTerca.isSelected() == false && chkQuarta.isSelected() == false && chkSexta.isSelected() == false && chkSabado.isSelected() == false && chkDomingo.isSelected() == false) {
            chkQuinta.setSelected(true);
        }
    }//GEN-LAST:event_chkQuintaActionPerformed

    private void chkDomingoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDomingoActionPerformed
        if (chkSegunda.isSelected() == false && chkTerca.isSelected() == false && chkQuarta.isSelected() == false && chkQuinta.isSelected() == false && chkSexta.isSelected() == false && chkSabado.isSelected() == false) {
            chkDomingo.setSelected(true);
        }
    }//GEN-LAST:event_chkDomingoActionPerformed

    private void chkSabadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSabadoActionPerformed
        if (chkSegunda.isSelected() == false && chkTerca.isSelected() == false && chkQuarta.isSelected() == false && chkQuinta.isSelected() == false && chkSexta.isSelected() == false && chkDomingo.isSelected() == false) {
            chkSabado.setSelected(true);
        }
    }//GEN-LAST:event_chkSabadoActionPerformed

    private void chkSegundaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSegundaActionPerformed
        if (chkTerca.isSelected() == false && chkQuarta.isSelected() == false && chkQuinta.isSelected() == false && chkSexta.isSelected() == false && chkSabado.isSelected() == false && chkDomingo.isSelected() == false) {
            chkSegunda.setSelected(true);
        }
    }//GEN-LAST:event_chkSegundaActionPerformed

    private void chkQuartaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkQuartaActionPerformed
        if (chkSegunda.isSelected() == false && chkTerca.isSelected() == false && chkQuinta.isSelected() == false && chkSexta.isSelected() == false && chkSabado.isSelected() == false && chkDomingo.isSelected() == false) {
            chkQuarta.setSelected(true);
        }
    }//GEN-LAST:event_chkQuartaActionPerformed

    private void chkTercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTercaActionPerformed
        if (chkSegunda.isSelected() == false && chkQuarta.isSelected() == false && chkQuinta.isSelected() == false && chkSexta.isSelected() == false && chkSabado.isSelected() == false && chkDomingo.isSelected() == false) {
            chkTerca.setSelected(true);
        }
    }//GEN-LAST:event_chkTercaActionPerformed

    private void btnDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDestinoActionPerformed
        new LocalSalvar(this).setVisible(true);
    }//GEN-LAST:event_btnDestinoActionPerformed

    private void btnLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalActionPerformed
        fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fc.showOpenDialog(fc) == JFileChooser.APPROVE_OPTION) {
            txtLocal.setText(fc.getSelectedFile().getPath());
        } else {
            txtLocal.setText("");
        }
    }//GEN-LAST:event_btnLocalActionPerformed

    private void btnPausarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPausarActionPerformed
        if (tblBkpAgendados.getSelectedRow() >= 0) {
            swingBackupArquivo.pausarThreadArquivo(tblBkpAgendados.getSelectedRow());
        }
    }//GEN-LAST:event_btnPausarActionPerformed

    private void btnPararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPararActionPerformed
        if (tblBkpAgendados.getSelectedRow() >= 0) {
            swingBackupArquivo.pararThreadArquivo(tblBkpAgendados.getSelectedRow());
        }
    }//GEN-LAST:event_btnPararActionPerformed

    private void mniBancoDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniBancoDadosActionPerformed
        new ConfigBanco().setVisible(true);
    }//GEN-LAST:event_mniBancoDadosActionPerformed

    private void radBackupBancoDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radBackupBancoDadosActionPerformed
        tbConfigBackup.setVisible(false);
        pnlBackupArquivo.setVisible(false);
        pnlServidorBackup.setVisible(false);

        pnlBkpBancoDados.setVisible(true);
        tpDbBackupBancoDados.setVisible(true);
    }//GEN-LAST:event_radBackupBancoDadosActionPerformed

    private void radBackupArquivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radBackupArquivosActionPerformed
        tpDbBackupBancoDados.setVisible(false);
        pnlBkpBancoDados.setVisible(false);
        pnlServidorBackup.setVisible(false);

        pnlBackupArquivo.setVisible(true);
        tbConfigBackup.setVisible(true);
    }//GEN-LAST:event_radBackupArquivosActionPerformed

    private void chkRbEditarPortaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRbEditarPortaActionPerformed
        if (chkRbEditarPorta.isSelected()) {
            txtRbEditarPorta.setEnabled(true);
        } else {
            txtRbEditarPorta.setEnabled(false);
            txtRbEditarPorta.setText("");
        }
    }//GEN-LAST:event_chkRbEditarPortaActionPerformed

    private void txtRbEditarPortaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRbEditarPortaKeyTyped
        if (!NUMEROS.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
        if (txtBdEditarPorta.getText().length() >= 5) {
            evt.consume();
        }
    }//GEN-LAST:event_txtRbEditarPortaKeyTyped

    private void txtRbEditarPortaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRbEditarPortaKeyReleased
        if (!txtBdEditarPorta.getText().equals("")) {
            if (Integer.parseInt(txtBdEditarPorta.getText()) > 65536) {
                txtBdEditarPorta.setText("65536");
            }
        }
    }//GEN-LAST:event_txtRbEditarPortaKeyReleased

    private void btnRbIniciarRestauracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRbIniciarRestauracaoActionPerformed
        int linha = tblBkpAgendados.getSelectedRow();
        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "");
            return;
        }
        swingBackupArquivo.getConfiguracoes().getEscutaCliente().run();
    }//GEN-LAST:event_btnRbIniciarRestauracaoActionPerformed

    private void chkContinuarBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkContinuarBackupActionPerformed
        if (chkData.isSelected()) {
            if (chkContinuarBackup.isSelected()) {
                chkSegunda.setSelected(true);
                chkSegunda.setVisible(true);
                chkTerca.setVisible(true);
                chkQuarta.setVisible(true);
                chkQuinta.setVisible(true);
                chkSexta.setVisible(true);
                chkSabado.setVisible(true);
                chkDomingo.setVisible(true);
            } else {
                chkSegunda.setSelected(false);
                chkSegunda.setVisible(false);
                chkTerca.setVisible(false);
                chkQuarta.setVisible(false);
                chkQuinta.setVisible(false);
                chkSexta.setVisible(false);
                chkSabado.setVisible(false);
                chkDomingo.setVisible(false);
            }
        } else {
            chkContinuarBackup.setSelected(true);
        }
    }//GEN-LAST:event_chkContinuarBackupActionPerformed

    private void chkDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDataActionPerformed
        if (chkContinuarBackup.isSelected()) {
            if (chkData.isSelected()) {
                lblDia.setVisible(true);
                cboDia.setVisible(true);
                lblMes.setVisible(true);
                cboMes.setVisible(true);
                lblAno.setVisible(true);
                cboAno.setVisible(true);
            } else {
                lblDia.setVisible(false);
                cboDia.setVisible(false);
                lblMes.setVisible(false);
                cboMes.setVisible(false);
                lblAno.setVisible(false);
                cboAno.setVisible(false);
            }
        } else {
            chkData.setSelected(true);
        }
    }//GEN-LAST:event_chkDataActionPerformed

    private void chkNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkNormalActionPerformed
        if (chkNormal.isSelected()) {
            if (chkIncremental.isSelected()) {
                chkIncremental.setSelected(false);
            }
        } else if (!chkIncremental.isSelected() && !chkCompactar.isSelected()) {
            chkNormal.setSelected(true);
        }
    }//GEN-LAST:event_chkNormalActionPerformed

    private void chkIncrementalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkIncrementalActionPerformed
        if (chkIncremental.isSelected()) {
            if (chkNormal.isSelected()) {
                chkNormal.setSelected(false);
            }
        } else if (!chkNormal.isSelected() && !chkCompactar.isSelected()) {
            chkIncremental.setSelected(true);
        }
    }//GEN-LAST:event_chkIncrementalActionPerformed

    private void chkBdContinuarBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBdContinuarBackupActionPerformed
        if (chkBdData.isSelected()) {
            if (chkBdContinuarBackup.isSelected()) {
                chkBdSegunda.setVisible(true);
                chkBdTerca.setVisible(true);
                chkBdQuarta.setVisible(true);
                chkBdQuinta.setVisible(true);
                chkBdSexta.setVisible(true);
                chkBdSabado.setVisible(true);
                chkBdDomingo.setVisible(true);
            } else {
                chkBdSegunda.setVisible(false);
                chkBdTerca.setVisible(false);
                chkBdQuarta.setVisible(false);
                chkBdQuinta.setVisible(false);
                chkBdSexta.setVisible(false);
                chkBdSabado.setVisible(false);
                chkBdDomingo.setVisible(false);
            }
        } else {
            chkBdContinuarBackup.setSelected(true);
        }
    }//GEN-LAST:event_chkBdContinuarBackupActionPerformed

    private void btnBdDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBdDestinoActionPerformed
        fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fc.showOpenDialog(fc) == JFileChooser.APPROVE_OPTION) {
            txtBdDestino.setText(fc.getSelectedFile().getPath() + "/");
        } else {
            txtBdDestino.setText("");
        }
    }//GEN-LAST:event_btnBdDestinoActionPerformed

    private void chkBdEditarPortaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBdEditarPortaActionPerformed
        if (chkBdEditarPorta.isSelected()) {
            txtBdEditarPorta.setEnabled(true);
        } else {
            txtBdEditarPorta.setEnabled(false);
            txtBdEditarPorta.setText("");
        }
    }//GEN-LAST:event_chkBdEditarPortaActionPerformed

    private void txtBdEditarPortaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBdEditarPortaKeyTyped
        if (!NUMEROS.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
        if (txtBdEditarPorta.getText().length() >= 5) {
            evt.consume();
        }
    }//GEN-LAST:event_txtBdEditarPortaKeyTyped

    private void txtBdEditarPortaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBdEditarPortaKeyReleased
        if (!txtBdEditarPorta.getText().equals("")) {
            if (Integer.parseInt(txtBdEditarPorta.getText()) > 65536) {
                txtBdEditarPorta.setText("65536");
            }
        }
    }//GEN-LAST:event_txtBdEditarPortaKeyReleased

    private void btnBdAdicionarBackupBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBdAdicionarBackupBancoActionPerformed
        if (!"".equals(txtBdDestino.getText())) {
            if (!"".equals(txtBdNomeBanco.getText())) {
                BackupBancoDados backupBancoDados = new BackupBancoDados();
                backupBancoDados.setBanco(cboBdBanco.getSelectedItem().toString());
                backupBancoDados.setDataCriacaoBackup(data.sysDataPath());
                backupBancoDados.setDataUltimoBackup(null);
                backupBancoDados.setDescricao(null);
                backupBancoDados.setDestino(txtBdDestino.getText());

                backupBancoDados.setFlagSemana(String.valueOf(chkBdContinuarBackup.isSelected()));
                backupBancoDados.setDom(chkBdDomingo.isSelected() && chkBdContinuarBackup.isSelected() ? NomeAbreviacao.DOMINGO : "false");
                backupBancoDados.setSeg(chkBdSegunda.isSelected() && chkBdContinuarBackup.isSelected() ? NomeAbreviacao.SEGUNDA_FEIRA : "false");
                backupBancoDados.setTer(chkBdTerca.isSelected() && chkBdContinuarBackup.isSelected() ? NomeAbreviacao.TERCA_FEIRA : "false");
                backupBancoDados.setQua(chkBdQuarta.isSelected() && chkBdContinuarBackup.isSelected() ? NomeAbreviacao.QUARTA_FEIRA : "false");
                backupBancoDados.setQui(chkBdQuinta.isSelected() && chkBdContinuarBackup.isSelected() ? NomeAbreviacao.QUINTA_FEIRA : "false");
                backupBancoDados.setSex(chkBdSexta.isSelected() && chkBdContinuarBackup.isSelected() ? NomeAbreviacao.SEXTA_FEIRA : "false");
                backupBancoDados.setSab(chkBdSabado.isSelected() && chkBdContinuarBackup.isSelected() ? NomeAbreviacao.SABADO : "false");

                backupBancoDados.setFlagData(String.valueOf(chkBdData.isSelected()));
                if (chkBdData.isSelected()) {
                    backupBancoDados.setDataBackupAgendado(cboBdDia.getSelectedItem() + "-" + ((cboBdMes.getSelectedIndex() + 1) < 10 ? "0" + (cboBdMes.getSelectedIndex() + 1) : (cboBdMes.getSelectedIndex() + 1)) + "-" + cboBdAno.getSelectedItem());
                } else {
                    backupBancoDados.setDataBackupAgendado("N/A");
                }

                backupBancoDados.setHoraMinuto((Integer.parseInt(spiBdHora.getValue().toString()) < 10 ? "0" + spiBdHora.getValue().toString() : spiBdHora.getValue().toString()) + ":" + (Integer.parseInt(spiBdMin.getValue().toString()) < 10 ? "0" + spiBdMin.getValue().toString() : spiBdMin.getValue().toString()));
                backupBancoDados.setNomeArquivo(txtBdNomeBanco.getText());
                backupBancoDados.setPathArquivo(txtBdDestino.getText() + txtBdNomeBanco.getText() + (cboBdBanco.getSelectedIndex() == 0 ? ".sql" : (cboBdBanco.getSelectedIndex() == 1 ? ".psg" : ".prd")));
                backupBancoDados.setPorta("".equals(txtBdEditarPorta.getText()) ? Acoes.DEFAULT_PORTA_MYSQL : txtBdEditarPorta.getText());
                backupBancoDados.setSenha(String.valueOf(pswBdSenhaBanco.getPassword()));
                backupBancoDados.setServidor("".equals(txtBdServidor.getText()) ? Acoes.LOCALHOST : txtBdServidor.getText());
                backupBancoDados.setUsuario("".equals(txtBdUsuario.getText()) ? Acoes.ROOT : txtBdUsuario.getText());
                swingBackupBancoDados.adicionarBackupBancodados(backupBancoDados);
            } else {
                lblBdNotificacao.setText(Acoes.NOME_DO_BANCO_NAO_PODE_FICAR_VAZIO);
            }
        } else {
            lblBdNotificacao.setText(Acoes.O_DESTINO_NAO_PODE_FICAR_VAZIO);
        }
    }//GEN-LAST:event_btnBdAdicionarBackupBancoActionPerformed

    private void chkBdDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBdDataActionPerformed
        if (chkBdContinuarBackup.isSelected()) {
            if (chkBdData.isSelected()) {
                lblBdDia.setVisible(true);
                cboBdDia.setVisible(true);
                lblBdMes.setVisible(true);
                cboBdMes.setVisible(true);
                lblBdAno.setVisible(true);
                cboBdAno.setVisible(true);
            } else {
                lblBdDia.setVisible(false);
                cboBdDia.setVisible(false);
                lblBdMes.setVisible(false);
                cboBdMes.setVisible(false);
                lblBdAno.setVisible(false);
                cboBdAno.setVisible(false);
            }
        } else {
            chkBdData.setSelected(true);
        }
    }//GEN-LAST:event_chkBdDataActionPerformed

    private void btnBdIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBdIniciarActionPerformed
        swingBackupBancoDados.iniciarThreadBancoDados(tblBdBackupAgendado.getSelectedRow());
    }//GEN-LAST:event_btnBdIniciarActionPerformed

    private void btnBdPausarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBdPausarActionPerformed
        swingBackupBancoDados.pausarThreadBancoDados(tblBdBackupAgendado.getSelectedRow());
    }//GEN-LAST:event_btnBdPausarActionPerformed

    private void btnBdPararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBdPararActionPerformed
        swingBackupBancoDados.pararThreadBancoDados(tblBdBackupAgendado.getSelectedRow());
    }//GEN-LAST:event_btnBdPararActionPerformed

    private void btnBdDetalheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBdDetalheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBdDetalheActionPerformed

    private void btnBdExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBdExcluirActionPerformed
        swingBackupBancoDados.excluirThreadBancoDados(tblBdBackupAgendado.getSelectedRow());
    }//GEN-LAST:event_btnBdExcluirActionPerformed

    private void radServidorBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radServidorBackupActionPerformed
        tbConfigBackup.setVisible(false);
        pnlBackupArquivo.setVisible(false);
        pnlBkpBancoDados.setVisible(false);
        tpDbBackupBancoDados.setVisible(false);
        pnlServidorBackup.setVisible(true);
    }//GEN-LAST:event_radServidorBackupActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        lblInformacao.setText(Acoes.INICIANDO_TODOS_OS_BACKUPS);
        swingBackupArquivo.iniciarThreadAtivaInativa();
        lblInformacao.setText(Acoes.TODOS_OS_BACKUP_INICIADO_COM_SUCESSO);
        swingBackupBancoDados.carregarIniciarBackupBancoDados();

        Calendar cal = Calendar.getInstance();
        cboMes.setSelectedIndex(cal.get(Calendar.MONTH));
        cboAno.setSelectedItem(cal.get(Calendar.YEAR));
        cboDia.setSelectedIndex(cal.get(Calendar.DAY_OF_MONTH) - 1);
    }//GEN-LAST:event_formWindowOpened

    private void mniRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniRelatoriosActionPerformed
        new RelatorioFrame().setVisible(true);
    }//GEN-LAST:event_mniRelatoriosActionPerformed

    private void chkCompactarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCompactarActionPerformed
        if (!chkIncremental.isSelected() && !chkNormal.isSelected()) {
            chkCompactar.setSelected(true);
        }
    }//GEN-LAST:event_chkCompactarActionPerformed

    private void btnCsIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCsIniciarActionPerformed
        if (!swingBackupArquivo.getConfiguracoes().getEstadoServidor()) {
            swingBackupArquivo.getConfiguracoes().iniciar();
            txtConfigServPorta.setEditable(false);
        }
    }//GEN-LAST:event_btnCsIniciarActionPerformed

    private void btnCsPararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCsPararActionPerformed
        if (swingBackupArquivo.getConfiguracoes().getEstadoServidor()) {
            swingBackupArquivo.getConfiguracoes().parar();
            txtConfigServPorta.setEditable(true);
        }
    }//GEN-LAST:event_btnCsPararActionPerformed

    private void btnImediatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImediatoActionPerformed
        int linha = tblBkpAgendados.getSelectedRow();
        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma linha!");
            return;
        }
        swingBackupArquivo.imediato(linha);
    }//GEN-LAST:event_btnImediatoActionPerformed

    @Override
    public void excluirThreadArquivo(int linha, boolean flagRemove) {
        // Verificamos se existe realmente alguma linha selecionada  
        if (linha >= 0 && flagRemove) {
            // Obtem o modelo da JTable e remove a linha
            ((DefaultTableModel) tblBkpAgendados.getModel()).removeRow(linha);
            GravarBackupBancoLog.gravarLogInformation("ConfigBkp:excluirThreadArquivo - linha excluida da tabela com sucesso!", ConfigBkp.getInstance());
        } else {
            JOptionPane.showMessageDialog(null, Acoes.SELECIONE_UMA_LINHA_PARA_REMOVELA);
        }
    }

    @Override
    public void detalheArquivo(BackupArquivo backupArquivo) {
        if (backupArquivo != null) {
            lblDbNome.setText(Acoes.NOME + ":            " + backupArquivo.getNome());
            lblDbIdentificador.setText(Acoes.IDENTIFICADOR + ":   " + backupArquivo.getIdentificador());
            lblDbDiaBackup.setText(Acoes.DIA_DO_BACKUP + ":   " + swingBackupArquivo.diasSemanaFormatado(backupArquivo));
            lblDbDataCriacao.setText(Acoes.DATA_DA_CRIACAO + ": " + backupArquivo.getDataCriacao().replace("-", "/"));
            lblDbHoraBackup.setText(Acoes.HORA_DO_BACKUP + ":  " + backupArquivo.getHoraMin());
            if (backupArquivo.getBkpIncremental() && backupArquivo.getCompactar() && backupArquivo.getNormal()) {
                lblDbTipoBackup.setText(Acoes.TIPO_DO_BACKUP + ":  Incremental, Compactar e Reverso");
            } else if (backupArquivo.getBkpIncremental() && backupArquivo.getCompactar() && !backupArquivo.getNormal()) {
                lblDbTipoBackup.setText(Acoes.TIPO_DO_BACKUP + ":  Incremental e Compactar");
            } else if (backupArquivo.getBkpIncremental() && !backupArquivo.getCompactar() && !backupArquivo.getNormal()) {
                lblDbTipoBackup.setText(Acoes.TIPO_DO_BACKUP + ":  Incremental");
            } else if (!backupArquivo.getBkpIncremental() && backupArquivo.getCompactar() && backupArquivo.getNormal()) {
                lblDbTipoBackup.setText(Acoes.TIPO_DO_BACKUP + ":  Compactar e Reverso");
            } else if (!backupArquivo.getBkpIncremental() && !backupArquivo.getCompactar() && backupArquivo.getNormal()) {
                lblDbTipoBackup.setText(Acoes.TIPO_DO_BACKUP + ":  Reverso");
            } else if (!backupArquivo.getBkpIncremental() && backupArquivo.getCompactar() && !backupArquivo.getNormal()) {
                lblDbTipoBackup.setText(Acoes.TIPO_DO_BACKUP + ":  Compactar");
            }
            lblDbLocalBackp.setText(Acoes.LOCAL + ":           " + backupArquivo.getLocal());
            lblDbDestinoBackup.setText(Acoes.DESTINO + ":        " + backupArquivo.getDestino());

            txaDbDescricao.setText(backupArquivo.getDescricao());
            tbConfigBackup.setSelectedIndex(2);
        }
    }

    @Override
    public void atualizarLogArquivo(String texto) {
        txaLog.append(texto);
        txaLog.append("\n");
        limitarLinhasLogArquivo(this.txaLog);
    }

    @Override
    public void atualizarLogServidor(String texto) {
        txaServidorLog.append(texto);
        txaServidorLog.append("\n");
    }

    @Override
    public void situacaoBkpArquivo(int linha, String situacao) {
        tblBkpAgendados.setValueAt(situacao, linha, 10);
    }

    @Override
    public void iniciarTabelaArquivo(String[] linhaTabela) {
        if (linhaTabela != null) {
            ((DefaultTableModel) tblBkpAgendados.getModel()).addRow(linhaTabela);
        }
    }

    @Override
    public void alertaArquivo(String texto) {
        lblInformacao.setText(texto);
    }

    @Override
    public void addLinhaTabelaArquivo() {
        int hora = Integer.parseInt(String.valueOf(spiHora.getValue()));
        int min = Integer.parseInt(String.valueOf(spiMin.getValue()));
        String dataBkpAgendado;
        if (chkData.isSelected()) {
            dataBkpAgendado = cboDia.getSelectedItem() + "/" + ((cboMes.getSelectedIndex() + 1) < 10 ? "0" + (cboMes.getSelectedIndex() + 1) : (cboMes.getSelectedIndex() + 1)) + "/" + cboAno.getSelectedItem();
        } else {
            dataBkpAgendado = "N/A";
        }

        ((DefaultTableModel) tblBkpAgendados.getModel()).addRow(new String[]{txtNomeBackup.getText(), dataBkpAgendado, (hora < 10 ? "0" + hora : hora) + ":" + (min < 10 ? "0" + min : min), (chkSegunda.isSelected() && chkContinuarBackup.isSelected() ? Acoes.SIM : Acoes.NAO), (chkTerca.isSelected() && chkContinuarBackup.isSelected() ? Acoes.SIM : Acoes.NAO),
            (chkQuarta.isSelected() && chkContinuarBackup.isSelected() ? Acoes.SIM : Acoes.NAO), (chkQuinta.isSelected() && chkContinuarBackup.isSelected() ? Acoes.SIM : Acoes.NAO), (chkSexta.isSelected() && chkContinuarBackup.isSelected() ? Acoes.SIM : Acoes.NAO),
            (chkSabado.isSelected() && chkContinuarBackup.isSelected() ? Acoes.SIM : Acoes.NAO), (chkDomingo.isSelected() && chkContinuarBackup.isSelected() ? Acoes.SIM : Acoes.NAO), Acoes.INICIADO});
    }

    @Override
    public void excluirThreadBancoDados(int linha, boolean flagRemove) {
        // Verificamos se existe realmente alguma linha selecionada  
        if (linha >= 0 && flagRemove) {
            // Obtem o modelo da JTable e remove a linha
            ((DefaultTableModel) tblBdBackupAgendado.getModel()).removeRow(linha);
        } else {
            JOptionPane.showMessageDialog(null, Acoes.SELECIONE_UMA_LINHA_PARA_REMOVELA);
        }
    }

    @Override
    public void detalheBancoDados() {

    }

    @Override
    public void situacaoTabelaBancoDados(int linha, String situacao) {
        tblBdBackupAgendado.setValueAt(situacao, linha, 6);
    }

    @Override
    public void iniciarTabelaBancoDados(String[] linhaTabela) {
        ((DefaultTableModel) tblBdBackupAgendado.getModel()).addRow(linhaTabela);
    }

    @Override
    public void alertaBancoDados(String texto) {

    }

    @Override
    public void atualizarLogBancoDados(String texto) {
        txaBdLog.append(texto);
        txaBdLog.append("\n");
    }

    /*
     * Aqui fica os metodos que são criados pelo programador
     */
    private void limitarLinhasLogArquivo(JTextArea txtWin) {
        int numLinesToTrunk = txtWin.getLineCount() - SCROLL_BUFFER_SIZE;
        if (numLinesToTrunk > 0) {
            try {
                int posOfLastLineToTrunk = txtWin.getLineEndOffset(numLinesToTrunk - 1);
                txaLog.replaceRange("", 0, posOfLastLineToTrunk);
            } catch (BadLocationException ex) {
                GravarArquivoLog.gravarLogError(ex.getMessage(), this);
            }
        }
    }
    
    @Override
    public synchronized void atualizarLblQtdBkp(int valor, String classe) {
        if (classe.equals(SwingBackupArquivo.class.getSimpleName())) {
            qtdBkpArquivo = valor;
        } else if (classe.equals(SwingBackupBancoDados.class.getSimpleName())) {
            qtdBkpBancoDados = valor;
        }
        lblQtdBkpFlag.setText(String.valueOf(qtdBkpArquivo + qtdBkpBancoDados));
    }

    public void setDestino(String destino) {
        txtDestino.setText(destino);
    }

    @Override
    public void iniciarThreadArquivo(int linhaTbl) {
        swingBackupArquivo.iniciarThreadArquivo(linhaTbl);
    }

    @Override
    public void pausarThreadArquivo(int linhaTbl) {
        swingBackupArquivo.pausarThreadArquivo(linhaTbl);
    }

    @Override
    public void pararThreadArquivo(int linhaTbl) {
        swingBackupArquivo.pararThreadArquivo(linhaTbl);
    }

    @Override
    public void excluirThreadArquivo(int linhaTbl) {
        SwingBackupArquivo.excluirThreadArquivo(linhaTbl);
    }
    
    public String getFtpModoConexao(){
        return this.ftpModoConexao;
    }
    
    public void setFtpModoConexao(String ftpModoConexao){
        this.ftpModoConexao = ftpModoConexao;
    }
    
    public String getFtpIp(){
        return this.ftpIp;
    }
    
    public void setFtpIp(String ftpIp){
        this.ftpIp = ftpIp;
    }
    
    public String getFtpPorta(){
        return this.ftpPorta;
    }
    
    public void setFtpPorta(String ftpPorta){
        this.ftpPorta = ftpPorta;
    }
    
    public String getFtpLogin(){
        return this.ftpLogin;
    }
    
    public void setFtpLogin(String ftpLogin){
        this.ftpLogin = ftpLogin;
    }
    
    public String getFtpSenha(){
        return this.ftpSenha;
    }
    
    public void setFtpSenha(String ftpSenha){
        this.ftpSenha = ftpSenha;
    }

    //Dados necessário para inicializar o JFrame
    private void inicializarJFrame() {
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent we) {
                if (systray == 0) {
                    minimizeToTray();
                }
                if (!isVisible()) {
                    setVisible(true);
                } else {
                    setVisible(false);
                }
            }
        });
        imageIcon = new Icone().Icone();
        this.setIconImage(imageIcon);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        //spiHora.setModel(new SpinnerNumberModel(0, 0, 24, 1));
        //spiMin.setModel(new SpinnerNumberModel(0, 0, 59, 1));

        Runnable rn = new Runnable() {

            @Override
            public synchronized void run() {
                while (true) {
                    lblDataHora.setText("                                   " + data.weekDay() + ", " + data.sysDia() + " de " + data.mesPorExtenso() + " de " + data.sysAno() + "  " + data.horaMinSeg());
                    try {
                        wait(999);
                    } catch (InterruptedException ex) {
                        GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.this);
                    }
                }
            }
        };
        Thread th = new Thread(rn);
        th.setName("DataHoraRodape");
        th.start();
        //
        txaLog.append(new GravarArquivoLog().carregarArquivoTextoLog().toString());
        ((DefaultCaret) txaLog.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        //
        txaBdLog.append(new GravarBackupBancoLog().carregarArquivoTextoLog().toString());
        ((DefaultCaret) txaBdLog.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        //
        txaServidorLog.append(new GravarServidorLog().carregarArquivoTextoLog().toString());
        ((DefaultCaret) txaServidorLog.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        limitarLinhasLogArquivo(this.txaLog);
    }

    private void minimizeToTray() {
        //Verifica se o S.O. suporta tray icon
        if (SystemTray.isSupported()) {
            // Instancia um novo SystemTray
            SystemTray tray = SystemTray.getSystemTray();
            // Instancia e Define o icone do TrayIcon
            TrayIcon trayIcon = new TrayIcon(imageIcon);
            // Define o auto-ajuste da imagem
            trayIcon.setImageAutoSize(true);
            //ação de executar  
            ActionListener actionListener = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    abrirJFrame();
                }
            };
            //ação de sair  
            ActionListener sairListener = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    String senha = JOptionPane.showInputDialog(Acoes.SENHA + ": ");
                    Login login = new LoginXML().lerLogin();
                    if (login.getPass().equals(senha)) {
                        int opcao = JOptionPane.showConfirmDialog(null, Acoes.VOCE_REALMENTE_DESEJA_SAIR_DO_SISTEMA, Acoes.CONFIRMACAO, JOptionPane.YES_NO_OPTION);
                        if (opcao == 0) {
                            System.exit(0);
                        }
                    } else {
                        JOptionPane.showConfirmDialog(null, Acoes.SENHA_INCORRETA, Acoes.ATENCAO, JOptionPane.CLOSED_OPTION);
                    }
                }
            };
            PopupMenu popup = new PopupMenu();
            //Opção executar  
            MenuItem executarItem = new MenuItem(Acoes.CONFIGURAR);
            executarItem.addActionListener(actionListener);
            popup.add(executarItem);
            //Opção sair  
            MenuItem sairItem = new MenuItem(Acoes.SAIR);
            sairItem.addActionListener(sairListener);
            popup.add(sairItem);
            // Criando o tray icon e colocando o popup para o clique com o botão direito  
            trayIcon = new TrayIcon(imageIcon, Acoes.SGB_BACKUP, popup);
            //Auto-ajuste do tamanho  
            trayIcon.setImageAutoSize(true);
            //Registrando o escutador para evento de clique com o botao esquerdo  
            trayIcon.addActionListener(actionListener);
            try {
                tray.add(trayIcon);
                systray = 1;
                //balão de aviso  
                trayIcon.displayMessage(Acoes.SISTEMA_GERENCIADOR_DE_BACKUP, Acoes.SINTASE_SEGURO_ESTOU_REALIZANDO_BACKUP_DOS_SEUS_DADOS, TrayIcon.MessageType.INFO);
            } catch (AWTException e) {
                GravarArquivoLog.gravarLogError(Acoes.TRAYICON_NAO_PODE_SER_ADICIONADO_NO_SISTEMA, this);
            }
        } else {
            GravarArquivoLog.gravarLogWarning(Acoes.BANDEJA_DO_SISTEMA_NAO_E_SUPORTADO, this);
        }
    }

    private void abrirJFrame() {
        this.setVisible(true);
    }

    //Variaveis locais
    private JFileChooser fc;
    private Image imageIcon;
    private int systray = 0;
    private static final String NUMEROS = "0123456789";
    private static ConfigBkp CONFIG_BKP;
    private final DataReturn data;
    private final SwingBackupArquivo swingBackupArquivo;
    private final SwingBackupBancoDados swingBackupBancoDados;
    private int qtdBkpBancoDados = 0;
    private int qtdBkpArquivo = 0;
    private long bytesEnviados = 0l;
    private long bytesRecebidos = 0l;
    private String ftpLogin = "";
    private String ftpSenha = "";
    private String ftpPorta = "";
    private String ftpIp = "";
    private String ftpModoConexao = "";
    private final int SCROLL_BUFFER_SIZE = 500;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnBdAdicionarBackupBanco;
    private javax.swing.JButton btnBdDestino;
    private javax.swing.JButton btnBdDetalhe;
    private javax.swing.JButton btnBdExcluir;
    private javax.swing.JButton btnBdIniciar;
    private javax.swing.JButton btnBdParar;
    private javax.swing.JButton btnBdPausar;
    private javax.swing.JButton btnCsIniciar;
    private javax.swing.JButton btnCsParar;
    private javax.swing.JButton btnDestino;
    protected javax.swing.JButton btnDetalhe;
    private javax.swing.JButton btnImediato;
    protected javax.swing.JButton btnIniciarThread;
    private javax.swing.JButton btnLocal;
    protected javax.swing.JButton btnParar;
    protected javax.swing.JButton btnPausar;
    private javax.swing.JButton btnRbAbrir;
    private javax.swing.JButton btnRbIniciarRestauracao;
    protected javax.swing.JButton btn_deletar;
    private javax.swing.ButtonGroup buttonGroup1;
    protected javax.swing.JComboBox cboAno;
    protected javax.swing.JComboBox cboBdAno;
    protected javax.swing.JComboBox cboBdBanco;
    protected javax.swing.JComboBox cboBdDia;
    protected javax.swing.JComboBox cboBdMes;
    protected javax.swing.JComboBox cboDia;
    protected javax.swing.JComboBox cboMes;
    protected javax.swing.JComboBox cboRbBanco;
    protected javax.swing.JCheckBox chkBdContinuarBackup;
    protected javax.swing.JCheckBox chkBdData;
    protected javax.swing.JCheckBox chkBdDomingo;
    protected javax.swing.JCheckBox chkBdEditarPorta;
    protected javax.swing.JCheckBox chkBdQuarta;
    protected javax.swing.JCheckBox chkBdQuinta;
    protected javax.swing.JCheckBox chkBdSabado;
    protected javax.swing.JCheckBox chkBdSegunda;
    protected javax.swing.JCheckBox chkBdSexta;
    protected javax.swing.JCheckBox chkBdTerca;
    protected javax.swing.JCheckBox chkCompactar;
    protected javax.swing.JCheckBox chkContinuarBackup;
    protected javax.swing.JCheckBox chkData;
    protected javax.swing.JCheckBox chkDomingo;
    protected javax.swing.JCheckBox chkIncremental;
    protected javax.swing.JCheckBox chkNormal;
    protected javax.swing.JCheckBox chkQuarta;
    protected javax.swing.JCheckBox chkQuinta;
    protected javax.swing.JCheckBox chkRbEditarPorta;
    protected javax.swing.JCheckBox chkSabado;
    protected javax.swing.JCheckBox chkSegunda;
    protected javax.swing.JCheckBox chkSexta;
    protected javax.swing.JCheckBox chkTerca;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    protected javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JLabel lblAcaoDoBackup;
    private javax.swing.JLabel lblAno;
    private javax.swing.JLabel lblBdAno;
    private javax.swing.JLabel lblBdBanco;
    private javax.swing.JLabel lblBdDestino;
    private javax.swing.JLabel lblBdDia;
    private javax.swing.JLabel lblBdHora;
    private javax.swing.JLabel lblBdMes;
    private javax.swing.JLabel lblBdMin;
    private javax.swing.JLabel lblBdNomeBanco;
    protected javax.swing.JLabel lblBdNotificacao;
    private javax.swing.JLabel lblBdSenhaBanco;
    private javax.swing.JLabel lblBdServidor;
    private javax.swing.JLabel lblBdUsuario;
    private javax.swing.JLabel lblCaracteres;
    private javax.swing.JLabel lblConfigServPorta;
    protected javax.swing.JLabel lblConfigServerAviso;
    private javax.swing.JLabel lblCsBytesEnviados;
    private javax.swing.JLabel lblCsBytesRecebidos;
    private javax.swing.JLabel lblCsFalhas;
    private javax.swing.JLabel lblCsOperando;
    private javax.swing.JLabel lblCsServidorIniciado;
    private javax.swing.JLabel lblCsServidorParado;
    private javax.swing.JLabel lblCsTempoOnline;
    private javax.swing.JLabel lblCsUsuariosConectados;
    protected javax.swing.JLabel lblDataHora;
    protected javax.swing.JLabel lblDbDataCriacao;
    protected javax.swing.JLabel lblDbDescricao;
    protected javax.swing.JLabel lblDbDestinoBackup;
    protected javax.swing.JLabel lblDbDiaBackup;
    protected javax.swing.JLabel lblDbHoraBackup;
    protected javax.swing.JLabel lblDbIdentificador;
    protected javax.swing.JLabel lblDbLocalBackp;
    protected javax.swing.JLabel lblDbNome;
    protected javax.swing.JLabel lblDbTipoBackup;
    private javax.swing.JLabel lblDescricaoBackup;
    private javax.swing.JLabel lblDestino;
    private javax.swing.JLabel lblDia;
    protected javax.swing.JLabel lblInformacao;
    private javax.swing.JLabel lblLocal;
    private javax.swing.JLabel lblMes;
    private javax.swing.JLabel lblNomeBackup;
    protected javax.swing.JLabel lblNotificacoes;
    protected javax.swing.JLabel lblQtdBkpAgendado;
    private javax.swing.JLabel lblQtdBkpFlag;
    private javax.swing.JLabel lblRbArquivo;
    private javax.swing.JLabel lblRbBanco;
    private javax.swing.JLabel lblRbNomeBanco;
    private javax.swing.JLabel lblRbSenhaBanco;
    private javax.swing.JLabel lblRbServidor;
    private javax.swing.JLabel lblRbUsuario;
    private javax.swing.JLabel lblRestaurarNotificacao;
    private javax.swing.JLabel lbl_hora;
    private javax.swing.JLabel lbl_min;
    private javax.swing.JMenuItem mniBancoDados;
    private javax.swing.JMenuItem mniRelatorios;
    private javax.swing.JMenu mnuSobre;
    private javax.swing.JPanel pnlAgendarBackup;
    private javax.swing.JPanel pnlBackupAgendado;
    private javax.swing.JPanel pnlBackupArquivo;
    private javax.swing.JPanel pnlBdAgendarBackup;
    private javax.swing.JPanel pnlBdBackupAgendado;
    private javax.swing.JPanel pnlBdData;
    private javax.swing.JPanel pnlBdDetalheBackup;
    private javax.swing.JPanel pnlBdHora;
    private javax.swing.JPanel pnlBdLog;
    private javax.swing.JPanel pnlBkpBancoDados;
    private javax.swing.JPanel pnlData;
    private javax.swing.JPanel pnlDetalheBackup;
    private javax.swing.JPanel pnlHora;
    private javax.swing.JPanel pnlRestaurarBackup;
    private javax.swing.JPanel pnlServidorBackup;
    private javax.swing.JPanel pnlServidorControle;
    private javax.swing.JPanel pnlServidorLog;
    private javax.swing.JPanel pnlSsEstadoServidor1;
    protected javax.swing.JPasswordField pswBdSenhaBanco;
    private javax.swing.JPasswordField pswRbSenhaBanco;
    private javax.swing.JRadioButton radBackupArquivos;
    private javax.swing.JRadioButton radBackupBancoDados;
    private javax.swing.JRadioButton radServidorBackup;
    private javax.swing.JSeparator sepSsSepararEstadoServidor1;
    protected javax.swing.JSpinner spiBdHora;
    protected javax.swing.JSpinner spiBdMin;
    protected javax.swing.JSpinner spiHora;
    protected javax.swing.JSpinner spiMin;
    protected javax.swing.JTabbedPane tbConfigBackup;
    protected javax.swing.JTable tblBdBackupAgendado;
    private javax.swing.JTable tblBkpAgendados;
    private javax.swing.JToolBar tobConfigBasicas;
    private javax.swing.JTabbedPane tpDbBackupBancoDados;
    private javax.swing.JPanel tpLog;
    private javax.swing.JTabbedPane tpServidorBackup;
    protected javax.swing.JTextArea txaBdLog;
    protected javax.swing.JTextArea txaDbDescricao;
    protected javax.swing.JTextArea txaDescricaoBackup;
    private javax.swing.JTextArea txaLog;
    private javax.swing.JTextArea txaServidorLog;
    protected javax.swing.JTextField txtBdDestino;
    protected javax.swing.JTextField txtBdEditarPorta;
    protected javax.swing.JTextField txtBdNomeBanco;
    protected javax.swing.JTextField txtBdServidor;
    protected javax.swing.JTextField txtBdUsuario;
    private javax.swing.JTextField txtConfigServPorta;
    private javax.swing.JTextField txtDestino;
    protected javax.swing.JTextField txtLocal;
    protected javax.swing.JTextField txtNomeBackup;
    private javax.swing.JTextField txtRbArquivo;
    private javax.swing.JTextField txtRbEditarPorta;
    private javax.swing.JTextField txtRbNomeBanco;
    private javax.swing.JTextField txtRbServidor;
    private javax.swing.JTextField txtRbUsuario;
    // End of variables declaration//GEN-END:variables
}
