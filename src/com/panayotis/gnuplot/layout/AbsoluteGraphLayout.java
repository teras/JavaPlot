/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panayotis.gnuplot.layout;

/**
 * Position graphs in absolute coordinates. Note: this layout is not supported yet.
 * @author teras
 */
public class AbsoluteGraphLayout implements GraphLayout {

    /**
     * Get metrics of graph with a specified index.
     * @param index The index of the graph 
     * @return Metrics of the positioning of the graph
     */
    public LayoutMetrics getMetrics(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Inform layout manager how many objects are present in this page
     * @param size The number of graph objects 
     */
    public void updateCapacity(int size) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
