/*
 * GNUPlotException.java
 *
 * Created on 12 Οκτώβριος 2007, 4:12 μμ
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.panayotis.gnuplot;

/**
 *
 * @author teras
 */
public class GNUPlotException extends RuntimeException {
    
    /**
     * Creates a new instance of GNUPlotException
     * @param reason 
     */
    public GNUPlotException(String reason) {
        super(reason);
    }
    
}
