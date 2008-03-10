/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panayotis.gnuplot.plot;

import com.panayotis.gnuplot.layout.GraphLayout;
import com.panayotis.gnuplot.layout.GridGraphLayout;
import com.panayotis.gnuplot.layout.LayoutMetrics;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The data representation of a whole graph page
 * @author teras
 */
public class Page extends ArrayList<Graph> {
    protected static final String NL = System.getProperty("line.separator");

    private String pagetitle;
    private GraphLayout layout;

    /**
     * COntruct a new blank page with one graph inside
     */
    public Page() {
        add(new Graph());
        pagetitle = "";
        layout = new GridGraphLayout();
    }

    /**
     * Get a reference for this page layout
     * @return the layout used in this page
     */
    public GraphLayout getLayout() {
        return layout;
    }

    /**
     * Get the title of this page
     * @return page title
     */
    public Object getTitle() {
        return pagetitle;
    }

    /**
     * Set the title of this page
     * @param title the new page title
     */
    public void setTitle(String title) {
        if (title == null)
            title = "";
        pagetitle = title;
    }

    /**
     * Append the GNUPlot program which will construct this page, to a buffer.
     * @param bf Buffer to store the gnuplot program
     */
    public void getGNUPlotPage(StringBuffer bf) {
        if (size() > 1) {
            /* This is a multiplot */

            /* First lay out the position of all elements */
            layout.updateMetrics(this);
            
            bf.append("set multiplot");
            if (!pagetitle.equals("")) {
                bf.append(" title \"").append(pagetitle).append('"');
            }
            bf.append(NL);

            LayoutMetrics metrics;
            for (Graph gr : this) {
                metrics = gr.getMetrics();
                bf.append("set origin ").append(metrics.getX()).append(',').append(metrics.getY()).append(NL);
                bf.append("set size ").append(metrics.getWidth()).append(',').append(metrics.getHeight()).append(NL);
                gr.retrieveData(bf);
            }

            bf.append("unset multiplot").append(NL);
        } else {
            get(0).retrieveData(bf);
        }

    }

}
