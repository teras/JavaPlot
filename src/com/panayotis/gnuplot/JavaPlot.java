/*
 * JavaPlot.java
 *
 * Created on October 19, 2007, 1:11 AM
 *
 */
package com.panayotis.gnuplot;

import com.panayotis.gnuplot.dataset.DataSet;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.plot.FunctionPlot;
import com.panayotis.gnuplot.terminal.GNUPlotTerminal;

/**
 * A friendly wrapper of GNUPlot, able to set common plot parameters. If easy of use
 * is required, it is recommended to use this class instead of GNUPlot.
 * @author teras
 */
public class JavaPlot extends GNUPlot {
    
    
    public JavaPlot() throws GNUPlotException {
        super();
    }
    public JavaPlot(String gnuplotpath) throws GNUPlotException {
        super(gnuplotpath);
    }
    public JavaPlot(GNUPlotParameters par) throws GNUPlotException {
        super(par);
    }
    public JavaPlot(GNUPlotParameters par, String gnuplotpath, GNUPlotTerminal term) throws GNUPlotException {
        super(par, gnuplotpath, term);
    }
    /**
     * Set the graph Title
     * @param title Title of the graph
     */
    public void setTitle(String title) {
        set("title", "'"+title+"'");
    }
    
    /**
     * Set the size of the drawing points
     * @param size Size of the point
     */
    public void setPointSize(double size) {
        set("pointsize", String.valueOf(size));
    }
    
    public void setKey(Key position) {
        set("key", position.name().replace('_',' ').toLowerCase());
    }
    
    public static enum Key {OFF, TOP_RIGHT, BOTTOM_RIGHT, TOP_LEFT, BOTTOM_LEFT, BELOW, OUTSIDE};
    
    
    public void addPlot(double[][] points) { addPlot(new DataSetPlot(points)); }
    public void addPlot(float[][] points) { addPlot(new DataSetPlot(points)); }
    public void addPlot(int[][] points) { addPlot(new DataSetPlot(points)); }
    public void addPlot(long[][] points) { addPlot(new DataSetPlot(points)); }
    public void addPlot(String function) { addPlot(new FunctionPlot(function)); }
    public void addPlot(DataSet set) { addPlot(new DataSetPlot(set)); }
}
