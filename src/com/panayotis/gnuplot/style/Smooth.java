/*
 * Smooth.java
 *
 * Created on October 31, 2007, 2:29 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.panayotis.gnuplot.style;

/**
 *
 * @author teras
 */
public enum Smooth {
    UNIQUE ,
    FREQUENCY ,
    CSPLINES ,
    ACSPLINES ,
    BEZIER ,
    SBEZIER;
 
    public String toString() {
        return " smooth "+name().toLowerCase();
    }
}
