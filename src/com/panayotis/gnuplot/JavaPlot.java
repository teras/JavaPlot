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
 *  * <p>
 * This object is not serializable, use GNUPlotParameters instead.
 * @author teras
 */
public class JavaPlot extends GNUPlot {
    
    
    /**
     * Create a new instance of JavaPlot, with the default parameters
     * @throws com.panayotis.gnuplot.GNUPlotException If the gnuplot executable is not found, this exception is thrown. Typically at
     * this case there is need to use a constructor which defines the gnuplot path.
     * @see GNUPlot#GNUPlot()
     */
    public JavaPlot() throws GNUPlotException {
        super();
    }
    /**
     * Create a new JavaPlot object with a given gnuplot path
     * @param gnuplotpath
     * @throws com.panayotis.gnuplot.GNUPlotException If the gnuplot executable is not found, this exception is thrown.  It means that the
     * provided path for gnuplot is not valid.
     * @see GNUPlot#GNUPlot(String)
     */
    public JavaPlot(String gnuplotpath) throws GNUPlotException {
        super(gnuplotpath);
    }
    /**
     *  Create a new JavaPlot object with given parameters
     * @param par
     * @throws com.panayotis.gnuplot.GNUPlotException
     * @see GNUPlot#GNUPlot(GNUPlotParameters)
     */
    public JavaPlot(GNUPlotParameters par) throws GNUPlotException {
        super(par);
    }
    /**
     * Create a new JavaPlot object with given parameters, gnuplot path and terminal
     * @param par
     * @param gnuplotpath
     * @param term
     * @throws com.panayotis.gnuplot.GNUPlotException
     * @see GNUPlot#GNUPlot(GNUPlotParameters,String,GNUPlotTerminal)
     */
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
     * 
     */
    public static enum Key {OFF, TOP_RIGHT, BOTTOM_RIGHT, TOP_LEFT, BOTTOM_LEFT, BELOW, OUTSIDE};
    
    
    /**
     * 
     * @param position
     */
    public void setKey(Key position) {
        if (position==null)
            set("key", null);
        else
            set("key", position.name().replace('_',' ').toLowerCase());
    }
    
    
    
    /**
     * 
     * @param points
     */
    public void addPlot(double[][] points) { addPlot(new DataSetPlot(points)); }
    /**
     * 
     * @param points
     */
    public void addPlot(float[][] points) { addPlot(new DataSetPlot(points)); }
    /**
     * 
     * @param points
     */
    public void addPlot(int[][] points) { addPlot(new DataSetPlot(points)); }
    /**
     * 
     * @param points
     */
    public void addPlot(long[][] points) { addPlot(new DataSetPlot(points)); }
    /**
     * 
     * @param function
     */
    public void addPlot(String function) { addPlot(new FunctionPlot(function)); }
    /**
     * 
     * @param set
     */
    public void addPlot(DataSet set) { addPlot(new DataSetPlot(set)); }
}
