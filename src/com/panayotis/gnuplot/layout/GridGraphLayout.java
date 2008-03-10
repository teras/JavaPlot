/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panayotis.gnuplot.layout;

import com.panayotis.gnuplot.GNUPlotException;
import com.panayotis.gnuplot.plot.Page;
import java.io.Serializable;

/**
 * Align graphs evenly on the page, in a grid layout
 * @author teras
 */
public class GridGraphLayout implements GraphLayout, Serializable {

    /**
     * Where the first graph will be put
     */
    public enum Start {

        UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT;
    }
    /**
     * Orientation of the graph layout
     */
    public static final boolean HORIZONTAL = true,  VERTICAL = false;
    private Start start;
    private boolean orientation;

    /**
     * Create a new grid layout with specific width and height
     * @param width width of the layout
     * @param height height of the layout
     */
    public GridGraphLayout() {
        start = Start.UPLEFT;
        orientation = HORIZONTAL;
    }

    /**
     * Set where the first graph will be put
     * @param start Position of the first graph
     */
    public void setStartPosition(Start start) {
        this.start = start;
    }

    /**
     * Sey the orientation of the graphs, as being put
     * @param orientation Selected orientation
     */
    public void setOrientation(boolean orientation) {
        this.orientation = orientation;
    }

    /**
     * Update the capacity of this layout. This manager tries to create  grid, 
     * as much square as possible
     * @param size The number of the graphs which will be requested to insert in this grid
     */
    public void updateMetrics(Page page) {

        int size = page.countGraphs();

        if (size <= 0)
            return;

        int width, height;
        height = (int) (Math.floor(Math.sqrt(size)));
        width = (int) Math.ceil((double) size / height);

        float dx = 1f / width;
        float dy = 1f / height;
        int col, lin;
        for (int index = 0; index < size; index++) {
            if (orientation) {
                col = index % width;
                lin = index / width;
            } else {
                lin = index % height;
                col = index / height;
            }

            if (start == Start.UPRIGHT || start == Start.DOWNRIGHT)
                col = width - col - 1;
            if (start == Start.UPLEFT || start == Start.UPRIGHT) // Positioning (0,0) in GNUPlot is in lower left corner
                lin = height - lin - 1;

            page.getGraph(index).setMetrics(dx * col, dy * lin, dx, dy);
        }
    }
}
