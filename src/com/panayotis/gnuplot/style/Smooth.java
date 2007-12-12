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
 * Define how this plot should be smoothed. Please refer to the documentation of
 * gnuplot for specific explanation of each method
 * @author teras
 */
public enum Smooth {
    UNIQUE ,
    FREQUENCY ,
    CSPLINES ,
    ACSPLINES ,
    BEZIER ,
    SBEZIER;
 
    /**
     * Retrieve the gnuplot argument for this smoothing mechanism
     * @return the gnuplot argument
     */
    public String toString() {
        return " smooth "+name().toLowerCase();
    }
}
