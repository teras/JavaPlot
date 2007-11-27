/*
 * Point.java
 *
 * Created on October 15, 2007, 1:54 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.panayotis.gnuplot.dataset;

import java.io.Serializable;
import java.lang.reflect.Array;

/**
 *
 * @param N 
 * @author teras
 */
public class Point<N extends Number> implements Serializable {
    private N[] coords;
    
    /** Creates a new instance of Point
     * @param coords 
     */
    @SuppressWarnings("unchecked")
    public Point(N... coords) {
        this.coords = (N[])Array.newInstance(Number.class, coords.length);
        System.arraycopy(coords, 0, this.coords, 0, coords.length);
    }
    
    /**
     * 
     * @param dimension
     * @return
     * @throws java.lang.ArrayIndexOutOfBoundsException
     */
    public N get(int dimension) throws ArrayIndexOutOfBoundsException {
        return coords[dimension];
    }
    /**
     * 
     * @return
     */
    public int getDimensions() {
        return coords.length;
    }
}
