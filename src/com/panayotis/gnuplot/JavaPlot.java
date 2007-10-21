/*
 * JavaPlot.java
 *
 * Created on October 19, 2007, 1:11 AM
 *
 */
package com.panayotis.gnuplot;

import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.plot.FunctionPlot;

/**
 * A friendly wrapper of GNUPlot, able to set common plot parameters. If easy of use
 * is required, it is recommended to use this class instead of GNUPlot.
 * @author teras
 */
public class JavaPlot extends GNUPlot {
    
    
    
    /**
     * Set the graph Title
     * @param title Title of the graph
     */
    public void setTitle(String title) {
        set("title", "'"+title+"'");
    }
    
    /**
     * Define the area to plot.
     * <br>
     * Note that if we have choosed log scale, then the X axis will be guaranteed to be
     * larger than zero. If the X axis is in log scale, do not set a value less than zero
     * or else a plot error will occure.
     * @param from The minimum X value
     * @param to The maximum X value
     */
    public void setBoundaries(double from, double to) {
        if (from==Double.POSITIVE_INFINITY || from==Double.NEGATIVE_INFINITY || from==Double.MAX_VALUE || from==Double.MIN_VALUE) return;
        if (to==Double.POSITIVE_INFINITY || to==Double.NEGATIVE_INFINITY || to==Double.MAX_VALUE || to==Double.MIN_VALUE) return;
        if (to<from) {
            double swap = to;
            to = from;
            from = swap;
        }
        set("xran", "["+from+":"+to+"]");
    }
    
    /**
     * Set the size of the drawing points
     * @param size Size of the point
     */
    public void setPointSize(double size) {
        set("pointsize", String.valueOf(size));
    }
    
    public void setKey (Key position) {
        set("key", position.name().replace('_',' ').toLowerCase());
    }
    
    public static enum Key {OFF, TOP_RIGHT, BOTTOM_RIGHT, TOP_LEFT, BOTTOM_LEFT, BELOW, OUTSIDE};
    
    
    public void addPlot(double[][] points) { addPlot(new DataSetPlot(points)); }
    public void addPlot(float[][] points) { addPlot(new DataSetPlot(points)); }
    public void addPlot(int[][] points) { addPlot(new DataSetPlot(points)); }
    public void addPlot(long[][] points) { addPlot(new DataSetPlot(points)); }
    public void addPlot(String function) { addPlot(new FunctionPlot(function)); }
    
}
