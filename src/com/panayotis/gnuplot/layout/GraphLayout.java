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
     * Use this method to inform the layout that metrics might need update.
     * <p>
     * This method is called automatically, just before the actual gnuplot program will
     * be produced. It is not called while adding or substructing elements from a page.
     * Thus, layout metrics are only updated just before they are needed.
     * <p>
     * If you manually set metrics and use an automatic layout, these metrics will
     * be lost
     * @param page The Page which it's graphs should be lay out.
     */
    public abstract void updateMetrics(Page page);
}
