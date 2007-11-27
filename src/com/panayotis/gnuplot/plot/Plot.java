/*
 * Plot.java
 *
 * Created on October 14, 2007, 1:59 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.panayotis.gnuplot.plot;

/**
 *
 * @author teras
 */
public interface Plot {
    
    /**
     * 
     * @return
     */
    public abstract String getDefinition();
    /**
     * 
     * @return
     */
    public abstract String getData();
}
