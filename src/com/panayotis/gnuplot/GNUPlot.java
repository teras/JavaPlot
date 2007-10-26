/*
 * GNUPlot.java
 *
 * Created on 12 Οκτώβριος 2007, 3:07 μμ
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.panayotis.gnuplot;

import com.panayotis.debug.Debug;
import com.panayotis.gnuplot.plot.Plot;
import com.panayotis.gnuplot.terminal.DefaultTerminal;
import com.panayotis.gnuplot.terminal.GNUPlotTerminal;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This is the main class of JavaPlot. It is the cental point to create a gnuplot.
 * Typically the user needs to create a new instance of this object and add the
 * desired plots.<p>
 * It also provides some convinient methods in order to set various parameters.
 * @author teras
 * @see #addPlot(Plot)
 */
public class GNUPlot {
    
    protected GNUPlotParameters par;
    
    protected transient GNUPlotTerminal term;
    protected transient GNUPlotExec exec;
    
    private static transient Debug dbg = new Debug();
    
    /**
     * Create a new instance of gnuplot, using the default parameters
     * @throws com.panayotis.gnuplot.GNUPlotException If the gnuplot executable is not found, this exception is thrown. Typically at
     * this case there is need to use a constructor which defines the gnuplot path.
     * @see #GNUPlot(String)
     * @see #GNUPlot(GNUPlotParameters,String,GNUPlotTerminal)
     */
    public GNUPlot() throws GNUPlotException {
        this(null, null, null);
    }
    
    /**
     * Create a new instance of gnuplot, with a given set of parameters.
     * @param par GNUPlot parameters to use. These parameters encapsulate the whole gnuplot variables, including data sets.
     * @throws com.panayotis.gnuplot.GNUPlotException If the gnuplot executable is not found, this exception is thrown. Typically at
     * this case there is need to use a constructor which defines the gnuplot path.
     * @see #GNUPlot(String)
     * @see #GNUPlot(GNUPlotParameters,String,GNUPlotTerminal)
     */
    public GNUPlot(GNUPlotParameters par) throws GNUPlotException {
        this(par, null, null);
    }
    /**
     * Create a new instance of gnuplot, with a given path for gnuplot. This constructor
     * is useful if the automatic path search for gnuplot is not fruitful, or the user
     * wants to point to a specific gnuplot executable.
     * @param gnuplotpath The pathname of the gnuplot executable. If this parameter is set to null, use the default path.
     * @throws com.panayotis.gnuplot.GNUPlotException If the gnuplot executable is not found, this exception is thrown. It means that the
     * provided path for gnuplot is not valid.
     */
    public GNUPlot(String gnuplotpath) throws GNUPlotException {
        this(null, gnuplotpath, null);
    }
    /**
     * Create a new instance of gnuplot, with given parameters and given path for gnuplot.
     * <p>
     * This constructor is useful if the user wants to fine tune eny aspect of GNUPlot
     * object, and especially if there is need to define a priori the output terminal.
     * <p>
     * Any parameters set to null, produce the default parameters.
     * @param par GNUPlot parameters to use. These parameters encapsulate the whole gnuplot variables, including data sets.
     * @param gnuplotpath The pathname of the gnuplot executable. If this parameter is set to null, use the default path.
     * @param term The gnuplot terminal to use
     * @throws com.panayotis.gnuplot.GNUPlotException If the gnuplot executable is not found, this exception is thrown. It means that the
     * provided path for gnuplot is not valid.
     */
    public GNUPlot(GNUPlotParameters par, String gnuplotpath, GNUPlotTerminal term) throws GNUPlotException {
        if (par==null) par = new GNUPlotParameters();
        this.par = par;
        
        if (term==null) term = new DefaultTerminal();
        this.term = term;
        
        try {
            exec = new GNUPlotExec(gnuplotpath);
        } catch (IOException e) {
            String msg = e.getMessage();
            if (gnuplotpath==null) msg += " Please provide gnuplot path to the constructor of GNUPlot.";
            throw new GNUPlotException(msg);
        }
        
    }
    
    /**
     * Set various GNUPlot parameters. All parameters added here will be used
     * in the form of "set key value"
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        par.set(key, value);
    }
    
    /**
     * Use this method to get a reference to the plot axis, in order to set various
     * parameters.
     * @param axisname The name of the axis. This typically is "x", "y", "z".
     * @return The requested Axis, or null if axis is not found
     */
    public Axis getAxis(String axisname) {
        return par.getAxis(axisname);
    }
    
    public void addPlot(Plot plot) {
        if (plot==null) return;
        par.addPlot(plot);
    }
    public ArrayList<Plot> getPlots() {
        return par.getPlots();
    }
    
    public void plot() throws GNUPlotException {
        exec.plot(par, term);
    }
//    public void splot() throws GNUPlotException {
//        exec.splot(par, term);
//    }
    
    
    public void setGNUPlotPath(String path) throws IOException {
        exec.setGNUPlotPath(path);
    }
    
    public String getGNUPlotPath(){
        return exec.getGNUPlotPath();
    }
    
    public void setParameters(GNUPlotParameters parameters) {
        if (par==null) return;
        par = parameters;
    }
    
    public GNUPlotParameters getParameters() {
        return par;
    }
    
    
    public void setTerminal(GNUPlotTerminal term) {
        if (term==null) return;
        this.term = term;
    }
    
    public GNUPlotTerminal getTerminal() {
        return term;
    }
    
    public static Debug getDebugger() {
        return dbg;
    }
    
    public ArrayList<String> getPreInit() {
        return par.getPreInit();
    }
    public ArrayList<String> getPostInit() {
        return par.getPostInit();
    }
    
}
