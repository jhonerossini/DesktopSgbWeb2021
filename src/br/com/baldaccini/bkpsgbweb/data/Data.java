/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.data;

import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * Classe que utiliza-se de GregorianCalendar para fornecer uma data em formato facil de usar
 * @author Pedro Rawan Meireles Limeira - prmlimeira@gmail.com
 *
 */
public class Data {
	
	private int dia;
	private Mes mes;
	private int ano;
	private GregorianCalendar calendario;
	
	public enum Mes {
		JANEIRO("janeiro",1, 31), 
		FEVEREIRO("fevereiro",2, 28, 29),
		MARCO("marco",3, 31),
		ABRIL("abril",4, 30),
		MAIO("maio",5, 31),
		JUNHO("junho",6, 30),
		JULHO("julho",7, 31),
		AGOSTO("agosto",8, 31),
		SETEMBRO("setembro",9, 30),
		OUTUBRO("outubro",10, 31),
		NOVEMBRO("novembro",11, 30),
		DEZEMBRO("dezembro",12, 31);
		
		private int diasMes;
		private int numero;
		private int diasMesAnoBissexto;
		private String extenso;
		private Mes(String extenso, int numero, int diasMes){
			this.extenso = extenso;
			this.numero = numero;
			this.diasMes = diasMes;
		}
		private Mes(String extenso, int numero, int mes, int mesAnoBissexto){
			this.extenso = extenso;
			this.numero = numero;
			this.diasMes = mes;
			this.diasMesAnoBissexto = mesAnoBissexto;
		};//Fim do enum Mes
		
		/**
		 * 
		 * @return int - quantidade de dias que o mes correspondente possui
		 */
		public int getMes(){
			return diasMes;
		}
		/**
		 * 
		 * @return int - retorna a quantidade de dias quando esta num ano bissexto, somente aplicavel ao mes de fevereiro
		 */
		public int getDiasMesAnoBissexto(){
			return diasMesAnoBissexto;
		}
		
		/**
		 * 
		 * @return int - retorna o numero correspondente ao mes
		 */
		public int getNumero(){
			return this.numero;
		}
		/**
		 * 
		 * @return String - retorna o nome do mes por extenso
		 */
		public String getExtenso(){
			return this.extenso;
		}
	};
	
	/**
	 * Construtor sem parametro - seta os valores para a data atual
	 */
	public Data(){
		//Seta para o dia atual
		calendario = new GregorianCalendar();
		dia= calendario.get(Calendar.DAY_OF_MONTH);  
		//Seta para o mes atual
		mes = Mes.values()[calendario.get(Calendar.MONTH)];
		//Seta ano para o atual
		ano = calendario.get(Calendar.YEAR);
		//Construtor sem parametros setar data para data atual
	}
	/**
	 * Constroi com os valores que voce passar como parametro
	 * @param dia - int
	 * @param int - mes
	 * @param int - ano
	 */
	public Data(int dia, int mes, int ano) throws Exception{
		
		calendario = new GregorianCalendar();
		
		if(dia >= 1 && dia <= 31){
			if(mes > 1 && mes <= 12){
				if(ano > 1900){
					calendario.set(Calendar.DAY_OF_MONTH, dia);
					calendario.set(Calendar.MONTH, mes - 1);
					calendario.set(Calendar.YEAR, ano);
					
					this.dia = calendario.get(Calendar.DAY_OF_MONTH);
					this.mes = Mes.values()[calendario.get(Calendar.MONTH)];
					
					this.ano = calendario.get(Calendar.YEAR);
				}else{
					throw new Exception("parametro ano invalido");
				}
			}else{
				throw new Exception("parametro mes invalido");
			}
		}else{
			throw new Exception("parametro dia invalido");
		}
	}
	/**
	 * Do mesmo modo que o anterior so que mes pode ser o enum publico Mes
	 * @param int - dia
	 * @param Mes - mes
	 * @param int - ano
	 */
	public Data(int dia, Mes mes, int ano) throws Exception{
		this(dia, mes.getNumero(), ano);
	}
	
	/**
	 * 
	 * @return int - dia
	 */
	public int getDia() {
		return dia;
	}
	/**
	 * Faz o calculo para verificar se um ano eh bissexto
	 * @param int - ano
	 * @return boolean - caso o ano seja bissexto retorna true caso nao false
	 */
	public boolean anoEhBissexto(int ano){
		if(ano > 0){
			return (ano%4 == 0 && (ano%400 ==0 || ano%100 != 0));
		}
		else{
			return false;
		}
	}
	/**
	 * Pega o ano em que esta setada a data para fazer o calculo se eh bissexto ou nao
	 * @return boolean
	 */
	public boolean anoEhBissexto(){
		return this.anoEhBissexto(this.ano);
	}
	/**
	 * Configura o dia do calendario
	 * @param int - dia
	 */
	public void setDia(int dia) throws Exception{
		if(dia > 0 && dia <= mes.getMes()){
			calendario.set(Calendar.DAY_OF_MONTH, dia);
			this.dia = calendario.get(Calendar.DAY_OF_MONTH);
		}
		
		else{
			throw new Exception("dia invalido");
		}
	}
	/**
	 * Retorna o enum correspondente ao mes setado
	 * @return Mes 
	 */
	public Mes getMes() {
		return mes;
	}
	
	/**
	 * 
	 * @return int - ano
	 */
	public int getAno() {
		return ano;
	}
	
	/**
	 * Retorna uma string no formato dd/mm/aaaa
	 * @return String 
	 */
	public String getData(){
		return dia+"/"+mes.getNumero()+"/"+ano;
	}
	/**
	 * Retorna uma string no formato <dia> de <mes por extenso> de <ano>
	 * @return String 
	 */
	public String getDataPorExtenso(){
		return dia+" de "+mes.getExtenso()+" de "+ano;
	}
	/**
	 * toString - devolve uma string no formato dd/mm/aaaa
	 */
	public String toString(){
		return this.getData();
	}
	/**
	 * Compara pelo ano, mes e dia se duas datas sao iguais
	 */
	public boolean equals(Object data){
		if(data instanceof Data){
			return ((Data) data).getAno() == this.ano && ((Data) data).getMes().equals(this.mes) && ((Data) data).getDia() == this.dia;
		}else{
			return false;
		}
	}//Fim do equals
	
	
}