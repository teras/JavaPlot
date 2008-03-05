/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panayotis.gnuplot.layout;

import com.panayotis.gnuplot.GNUPlotException;

/**
 * Align graphs evenly on the page, in a grid layout
 * @author teras
 */
public class GridGraphLayout implements GraphLayout {

    /**
     * Where the first graph will be put
     */
    public enum LayoutStart {
        /**
         * Position of the first graph
         */
        UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT;
    }
    
    /**
     * Orientation of the graph layout
     */
    public static final boolean HORIZONTAL = true, VERTICAL = false;
    
    private int width,  height;
    private float dx,  dy;
    private LayoutStart start;
    private boolean orientation;

    /**
     * Create a new grid layout with specific width and height
     * @param width width of the layout
     * @param height height of the layout
     */
    public GridGraphLayout(int width, int height) {
        setLayout(width, height);
        start = LayoutStart.UPLEFT;
        orientation = HORIZONTAL;
    }

     /**
     * Get metrics of graph with a specified index.
     * @param index The index of the graph 
     * @return Metrics of the positioning of the graph
     */
    public LayoutMetrics getMetrics(int index) {

        if (index >= (width * height)) {
            throw new GNUPlotException("Index greater than grid capacity");
        }

        int col, lin;
        if (orientation) {
            col = index % width;
            lin = index / width;
        } else {
            lin = index % height;
            col = index / height;
        }

        if (start == LayoutStart.UPRIGHT || start == LayoutStart.DOWNRIGHT) {
            col = width - col - 1;
        }
        if (start == LayoutStart.UPLEFT || start == LayoutStart.UPRIGHT) { // Positioning (0,0) in GNUPlot is in lower left corner
            lin = height - lin - 1;
        }

        LayoutMetrics ret = new LayoutMetrics();
        ret.x = dx * col;
        ret.y = dy * lin;
        ret.width = dx;
        ret.height = dy;
        return ret;
    }

    /**
     * Set the dimensions of this grid
     * @param width Width of the grid
     * @param height Height of the grif
     */
    public void setLayout(int width, int height) {
        if (width <= 0) {
            throw new GNUPlotException("Width should be a number greater than zero.");
        }
        if (height <= 0) {
            throw new GNUPlotException("Height should be a number greater than zero.");
        }
        this.width = width;
        this.height = height;
        dx = 1f / width;
        dy = 1f / height;
    }

    /**
     * Set where the first graph will be put
     * @param start Position of the first graph
     */
    public void setStartPosition(LayoutStart start) {
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
    public void updateCapacity(int size) {
        if (size <= 0) {
            throw new GNUPlotException("Capacity should be a number greater than zero.");
        }

        int x, y;
        y = (int) (Math.floor(Math.sqrt(size)));
        x = (int) Math.ceil((double) size / y);
        setLayout(x, y);
    }
}
