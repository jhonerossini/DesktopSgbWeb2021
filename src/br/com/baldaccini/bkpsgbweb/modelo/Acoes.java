/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.modelo;

/**
 *
 * @author baldaccini
 */
public class Acoes {

    public static int LOGIN_OK = 10;
    public static int LOGIN_FAIL = -10;
    public static int BKP_ARQUIVO_FLAG = 100;
    public static int BKP_BANCO_DADOS_FLAG = 101;
    public static int LOGIN = 0;
    public static int ERRO = 6;
    public static int EXCLUIR_LINHA = 5;
    public static int INICIAR = 2;
    public static int PARAR = 4;
    public static int PAUSAR = 3;
    public static int POPULAR_TABELA = 1;
    public static int ADD_ITEM_TABELA = 7;

    public static String INICIADO = "Iniciado";
    public static String PAUSADO = "Pausado";
    public static String PARADO = "Parado";
    public static String NOME = "Nome";
    public static String IDENTIFICADOR = "Identificador";
    public static String DIA_DO_BACKUP = "Dia do Backup";
    public static String DATA_DA_CRIACAO = "Data da criação";
    public static String HORA_DO_BACKUP = "Hora do backup";
    public static String TIPO_DO_BACKUP = "Tipo do backup";
    public static String INCREMENTAL = "Incremental";
    public static String COMPACTAR = "Compactar";
    public static String REVERSO = "Reverso";
    public static String LOCAL = "Local";
    public static String DESTINO = "Destino";
    public static String SELECIONE_UMA_LINHA_PARA_REMOVELA = "Selecione uma linha para remove-la.";
    public static String SENHA = "Senha";
    public static String VOCE_REALMENTE_DESEJA_SAIR_DO_SISTEMA = "Você realmente deseja sair do sistema?";
    public static String CONFIRMACAO = "Confirmação";
    public static String SENHA_INCORRETA = "Senha incorreta.";
    public static String ATENCAO = "Atenção";
    public static String CONFIGURAR = "Configurar";
    public static String SAIR = "Sair";
    public static String SGB_BACKUP = "SGB-BACKUP";
    public static String SISTEMA_GERENCIADOR_DE_BACKUP = "Sistema Gerenciador de Backup";
    public static String SINTASE_SEGURO_ESTOU_REALIZANDO_BACKUP_DOS_SEUS_DADOS = "Sinta-se seguro(a), estou realizando backup dos seus dados.";
    public static String TRAYICON_NAO_PODE_SER_ADICIONADO_NO_SISTEMA = "TrayIcon não pode ser adicionado no sistema.";
    public static String BANDEJA_DO_SISTEMA_NAO_E_SUPORTADO = "Bandeja do sistema não é suportado.";
    public static String SIM = "Sim";
    public static String NAO = "Não";
    public static String INICIANDO_TODOS_OS_BACKUPS = "Iniciando todos os Backup...";
    public static String TODOS_OS_BACKUP_INICIADO_COM_SUCESSO = "Todos os Backup iniciado com sucesso!";
    public static String NOME_DO_BANCO_NAO_PODE_FICAR_VAZIO = "Nome do Banco não pode ficar vazio!";
    public static String O_DESTINO_NAO_PODE_FICAR_VAZIO = "O destino não pode ficar vazio!";
    public static String LOCALHOST = "localhost";
    public static String ROOT = "root";
    public static String DEFAULT_PORTA_MYSQL = "3306";
    public static String O_CAMPO_LOCAL_NAO_PODE_FICAR_VAZIO = "O campo local não pode ficar vazio.";
    public static String O_NOME_DO_BACKUP_NAO_PODE_FICAR_VAZIO = "O nome do backup não pode ficar vazio.";
    public static String CARACTERES = "Caracteres";
    public static String TEMPO_ONLINE = "Tempo online";
    public static String SERVIDOR_PARADO = "Servidor parado";
    public static String BYTES_ENVIADOS = "Bytes enviados";
    public static String BYTES_RECEBIDOS = "Bytes recebidos";
    public static String SERVIDOR_INICIADO = "Servidor iniciado";
    public static String FALHAS = "Falhas";
    public static String USUARIOS_CONECTADOS = "Usuarios conectados";
    public static String OPERANDO = "Operando";
    public static String SERVIDOR_INICIADO_COM_SUCESSO = "Servidor iniciado com sucesso!";
    public static String O_CAMPO_PORTA_NAO_PODE_FICAR_VAZIO = "O campo porta não pode ficar vazio!";
    public static String VERIFICANDO_SERVIDOR_INICIADO = "Verificando servidor iniciado...";
    public static String REINICIANDO_SERVIDOR = "Reiniciando servidor...";
    public static String SERVIDOR_PARADO_COM_SUCESSO = "Servidor parado com sucesso!";
    public static String NORMAL = "Normal";
    public static String O_NOME_DO_BACKUP_JA_EXISTE = "O nome do backup já existe!";
    public static String SEGUNDA = "Segunda";
    public static String TERCA = "Terça";
    public static String QUARTA = "Quarta";
    public static String QUINTA = "Quinta";
    public static String SEXTA = "Sexta";
    public static String SABADO = "Sábado";
    public static String DOMINGO = "Domingo";
    public static String SEM_COMPACTAR = "";

}
