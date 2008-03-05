/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panayotis.gnuplot.layout;

import com.panayotis.gnuplot.GNUPlotException;

/**
 *
 * @author teras
 */
public class GridGraphLayout implements GraphLayout {

    public static final boolean HORIZONTAL = true;
    public static final boolean VERTICAL = false;

    private int width, height;
    private float dx, dy;
    private LayoutStart start;
    private boolean orientation;
    
    
    public GridGraphLayout(int width, int height) {
        setLayout(width, height);
        start = LayoutStart.UPLEFT;
        orientation = HORIZONTAL;
    }
    
    public LayoutMetrics getMetrics(int index) {
        
        if (index>=(width*height))
            throw new GNUPlotException("Index greater than grid capacity");
        
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
            lin = height - lin -1;
        }

        LayoutMetrics ret = new LayoutMetrics();
        ret.x = dx*col;
        ret.y = dy*lin;
        ret.width = dx;
        ret.height = dy;
        return ret;
    }

    public void setLayout(int width, int height) {
        if (width<=0)
            throw new GNUPlotException("Width should be a number greater than zero.");
        if (height<=0)
            throw new GNUPlotException("Height should be a number greater than zero.");
        this.width = width;
        this.height = height;
        dx = 1f/width;
        dy = 1f/height;
    }

    public void setStartPosition(LayoutStart start) {
        this.start = start;
    }

    public void setOrientation(boolean orientation) {
        this.orientation = orientation;
    }

    public void updateCapacity(int size) {
        if (size <= 0 )
            throw new GNUPlotException("Capacity should be a number greater than zero.");
        
        int x, y;
        y = (int)(Math.floor(Math.sqrt(size)));
        x = (int)Math.ceil((double)size/y);
        setLayout(x, y);
    }
}
