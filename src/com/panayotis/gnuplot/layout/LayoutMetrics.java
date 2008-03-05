/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panayotis.gnuplot.layout;

import java.io.Serializable;

/**
 * Container of the metrics for a specific graph
 * @author teras
 */
public class LayoutMetrics implements Serializable {

    /**
     * Metric values of this graph
     */
    public float x,  y,  width,  height;
   
}
