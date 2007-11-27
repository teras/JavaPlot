/*
 * FunctionPlot.java
 *
 * Created on 12 Οκτώβριος 2007, 5:17 μμ
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.panayotis.gnuplot.plot;

/**
 *
 * @author teras
 */
public class FunctionPlot extends AbstractPlot{
    private String function;
    
    
    /**
     * Creates a new instance of FunctionPlot
     * @param function 
     */
    public FunctionPlot(String function) {
        if (function==null) function = "0";
        this.function = function;
        set("title", "'"+function+"'");
        setDefinition(function);
    }

    /**
     * 
     * @return
     */
    public String getData() { return ""; }

}
