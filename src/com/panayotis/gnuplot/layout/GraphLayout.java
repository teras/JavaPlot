/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panayotis.gnuplot.layout;

import com.panayotis.gnuplot.plot.Page;

/**
 * This Object is used to define how graphs will be positioned on the whole page
 * @author teras
 */
public interface GraphLayout {

    /**
     * Use this method to inform the layout that metrics might need update
     * @param page The Page which it's graphs should be lay out.
     */
    public abstract void updateMetrics(Page page);
}
