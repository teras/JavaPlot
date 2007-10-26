/*
 * PlotColor.java
 *
 * Created on 26 Οκτώβριος 2007, 4:32 μμ
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.panayotis.gnuplot.style;

/**
 *
 * @author teras
 */
public enum PlotColor {
    RED, GREEN, BLUE;
    
    public String toString() {
        return "rgb '"+name().toLowerCase()+"'";
    }
   
}
