/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baldaccini.bkpsgbweb.util;

import static br.com.baldaccini.bkpsgbweb.log.GravarArquivoLog.gravarLogError;
import br.com.baldaccini.bkpsgbweb.swing.ConfigBkp;
import java.text.DecimalFormat;

/**
 *
 * @author baldaccini
 */
public class ConvExprTamArq {

    private final long K = 1024;
    private final long M = K * K;
    private final long G = M * K;
    private final long T = G * K;

    public String convertToStringRepresentation(final long value) {
        final long[] dividers = new long[]{T, G, M, K, 1};
        final String[] units = new String[]{"TB", "GB", "MB", "KB", "Bytes"};
        if (value < 1) {
            gravarLogError("Invalid file size: " + value, ConfigBkp.getInstance());
        }
        String result = null;
        for (int i = 0; i < dividers.length; i++) {
            final long divider = dividers[i];
            if (value >= divider) {
                result = format(value, divider, units[i]);
                break;
            }
        }
        return result;
    }

    private String format(final long value, final long divider, final String unit) {
        final double result = divider > 1 ? (double) value / (double) divider : (double) value;
        return new DecimalFormat("#,##0.#").format(result) + " " + unit;
    }
}
