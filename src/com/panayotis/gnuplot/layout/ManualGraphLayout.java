/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panayotis.gnuplot.layout;

import com.panayotis.gnuplot.plot.Page;
import java.io.Serializable;

/**
 * Position graphs in absolute coordinates. This is actually a dummy layout.
 * All information is stored inside the Graph object
 * @author teras
 */
public class ManualGraphLayout implements GraphLayout, Serializable {

    /** 
     * This layout manager does nothing with the actual graph layout
     * @param page The page containing the various graph objects, to be lay out.
     */
    public void updateMetrics(Page page) {
    }

}
