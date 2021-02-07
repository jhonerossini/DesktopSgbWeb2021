/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.data;

import br.com.baldaccini.bkpsgbweb.log.GravarArquivoLog;
import br.com.baldaccini.bkpsgbweb.swing.ConfigBkp;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Rosemary
 */
public class DataReturn {

    private SimpleDateFormat sdf;
    private Calendar cal;

    public String sysHora() {
        return _24Hora();
    }

    public String sysMin() {
        cal = Calendar.getInstance();
        int min = cal.get(Calendar.MINUTE);
        return String.valueOf(min < 10 ? "0" + min : min);
    }

    public String sysSec() {
        cal = Calendar.getInstance();
        int segundo = cal.get(Calendar.SECOND);
        return String.valueOf(segundo < 10 ? "0" + segundo : segundo);
    }

    /**
     *
     * @return String
     */
    private String _24Hora() {
        cal = Calendar.getInstance();
        int hora = cal.get(Calendar.HOUR);
        String AM_PM = cal.get(Calendar.AM_PM) == 0 ? "AM" : "PM";
        if (AM_PM.equals("PM")) {
            if (hora == 1) {
                hora = 13;
            } else if (hora == 2) {
                hora = 14;
            } else if (hora == 3) {
                hora = 15;
            } else if (hora == 4) {
                hora = 16;
            } else if (hora == 5) {
                hora = 17;
            } else if (hora == 6) {
                hora = 18;
            } else if (hora == 7) {
                hora = 19;
            } else if (hora == 8) {
                hora = 20;
            } else if (hora == 9) {
                hora = 21;
            } else if (hora == 10) {
                hora = 22;
            } else if (hora == 11) {
                hora = 23;
            } else if (hora == 0) {
                hora = 12;
            }
        }
        return String.valueOf(hora < 10 ? "0" + hora : hora);
    }

    /**
     *
     * @return String HH:mm:ss
     */
    public String horaMinSeg() {
        cal = Calendar.getInstance();
        int min = cal.get(Calendar.MINUTE);
        int segundo = cal.get(Calendar.SECOND);
        return _24Hora() + ":"
                + String.valueOf(min < 10 ? "0" + min : min) + ":"
                + String.valueOf(segundo < 10 ? "0" + segundo : segundo);
    }

    /**
     *
     * @return String
     */
    public String horaMin() {
        cal = Calendar.getInstance();
        int min = cal.get(Calendar.MINUTE);
        return _24Hora() + ":"
                + String.valueOf(min < 10 ? "0" + min : min);
    }

    public String amPm() {
        cal = Calendar.getInstance();
        return cal.get(Calendar.AM_PM) == 0 ? "AM" : "PM";
    }

    public String sysDia() {
        cal = Calendar.getInstance();
        return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
    }

    public String sysMes() {
        cal = Calendar.getInstance();
        return String.valueOf(cal.get(Calendar.MONTH));
    }

    public String sysAno() {
        cal = Calendar.getInstance();
        return String.valueOf(cal.get(Calendar.YEAR));
    }

    public String weekDay() {
        cal = Calendar.getInstance();
        return new DateFormatSymbols().getWeekdays()[cal.get(Calendar.DAY_OF_WEEK)];
    }

    /*
     * @sysData - dd/MM-yyyy
     */
    public String sysData() {
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(new Date());
    }

    /*
     * @sysDataPath - dd-MM-yyyy
     */
    public String sysDataPath() {
        sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(new Date());
    }

    public String mesPorExtenso() {
        cal = Calendar.getInstance();
        return new DateFormatSymbols().getMonths()[cal.get(Calendar.MONTH)];
    }

    /**
     *
     * @param data
     * @return true se a data do S.O é menor do que a informada senão retorna
     * false
     */
    public boolean compararDataDepois(String data) {
        sdf = new SimpleDateFormat("dd-MM-yyyy");
        boolean retorno = false;
        try {
            retorno = sdf.parse(data).after(new Date());
        } catch (ParseException ex) {
            GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
        }
        return retorno;
    }
    
    /**
     *
     * @param data
     * @return true se a data do S.O é maior do que a informada senão retorna
     * false
     */
    public boolean compararDataAntes(String data) {
        sdf = new SimpleDateFormat("dd-MM-yyyy");
        boolean retorno = false;
        try {
            retorno = sdf.parse(data).before(new Date());
        } catch (ParseException ex) {
            GravarArquivoLog.gravarLogError(ex.getMessage(), ConfigBkp.getInstance());
        }
        return retorno;
    }

    public int ultimoDiaMes() {
        cal = Calendar.getInstance();
        if (cal.get(Calendar.MONTH) == 1) {
            return 31;
        } else if (cal.get(Calendar.MONTH) == 2) {
            if (anoEhBissexto()) {
                return 29;
            } else {
                return 28;
            }
        } else if (cal.get(Calendar.MONTH) == 3) {
            return 31;
        } else if (cal.get(Calendar.MONTH) == 4) {
            return 30;
        } else if (cal.get(Calendar.MONTH) == 5) {
            return 31;
        } else if (cal.get(Calendar.MONTH) == 6) {
            return 30;
        } else if (cal.get(Calendar.MONTH) == 7) {
            return 31;
        } else if (cal.get(Calendar.MONTH) == 8) {
            return 31;
        } else if (cal.get(Calendar.MONTH) == 9) {
            return 30;
        } else if (cal.get(Calendar.MONTH) == 10) {
            return 31;
        } else if (cal.get(Calendar.MONTH) == 11) {
            return 30;
        } else if (cal.get(Calendar.MONTH) == 12) {
            return 31;
        }
        return -1;
    }

    private boolean anoEhBissexto() {
        if (cal.get(Calendar.YEAR) > 0) {
            return (cal.get(Calendar.YEAR) % 4 == 0 && (cal.get(Calendar.YEAR) % 400 == 0 || cal.get(Calendar.YEAR) % 100 != 0));
        } else {
            return false;
        }
    }
}
