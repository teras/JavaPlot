/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panayotis.gnuplot.layout;

/**
 * This Object is used to define how graphs will be positioned on the whole page
 * @author teras
 */
public interface GraphLayout {

    /**
     * Get metrics of graph with a specified index. The object is supposed to know
     * how to layout graphs
     * @param index The index of the graph 
     * @return Metrics of the positioning of the graph
     */
    public abstract LayoutMetrics getMetrics(int index);
    
    /**
     * Inform layout manager how many objects are present in this page
     * @param size The number of graph objects 
     */
    public abstract void updateCapacity(int size);
}
