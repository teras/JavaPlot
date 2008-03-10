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
public class Page implements Serializable {
    protected static final String NL = System.getProperty("line.separator");

    private ArrayList<Graph> graphs;
    private String pagetitle;
    private GraphLayout layout;

    /**
     * COntruct a new blank page with one graph inside
     */
    public Page() {
        graphs = new ArrayList<Graph>();
        graphs.add(new Graph());
        pagetitle = "";
        layout = new GridGraphLayout();
    }

    /**
     * Add a new graph into this page
     * @param gr The graph to be added
     */
    public void addGraph(Graph gr) {
        graphs.add(gr);
        layout.updateMetrics(this);
    }

    /** 
     * Get a specific graph from it's ID
     * @param idx the graph inded
     * @return The graph object with the desired id
     */
    public Graph getGraph(int idx) {
        return graphs.get(idx);
    }

    /**
     * Count how many graphs are inside this page
     * @return the number of graphs
     */
    public int countGraphs() {
        return graphs.size();
    }

    /**
     * Get a reference for all graphs inside this page
     * @return Refernece to all graph objects
     */
    public ArrayList<Graph> getGraphs() {
        return graphs;
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
        if (countGraphs() > 1) {
            /* This is a multiplot */
            bf.append("set multiplot");
            if (!pagetitle.equals("")) {
                bf.append(" title \"").append(pagetitle).append('"');
            }
            bf.append(NL);

            LayoutMetrics metrics;
            for (Graph gr : graphs) {
                metrics = gr.getMetrics();
                bf.append("set origin ").append(metrics.getX()).append(',').append(metrics.getY()).append(NL);
                bf.append("set size ").append(metrics.getWidth()).append(',').append(metrics.getHeight()).append(NL);
                gr.retrieveData(bf);
            }

            bf.append("unset multiplot").append(NL);
        } else {
            getGraph(0).retrieveData(bf);
        }

    }

}
