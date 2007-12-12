/*
 * GNUPlotParameters.java
 *
 * Created on October 13, 2007, 4:02 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.panayotis.gnuplot;

import com.panayotis.gnuplot.plot.Plot;
import com.panayotis.gnuplot.terminal.GNUPlotTerminal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is a placeholder for the parameters used to create the actual plot.
 * 
 * @author teras
 */
public class GNUPlotParameters extends PropertiesHolder implements Serializable {
    
    final static String ERRORTAG = "_ERROR_";
    final static String SUCCESSTAG = "_SUCCESS_";
    
    private ArrayList<Plot> plots;
    private HashMap<String,Axis> axis;
    private ArrayList<String> preinit;
    private ArrayList<String> postinit;
    
    
    /**
     *  Create a new plot with the default parameters
     */
    public GNUPlotParameters() {
        plots = new ArrayList<Plot>();
        
        axis = new HashMap<String,Axis>();
        axis.put("x", new Axis("x"));
        axis.put("y", new Axis("y"));
        axis.put("z", new Axis("z"));
        
        preinit = new ArrayList<String>();
        postinit = new ArrayList<String>();
    }
    
    
    
    /**
     *  Get one of the available Axis, in orde to set some parameters on it.
     * @param axisname The name of the Axis. It is usually "x", "y", "z"
     * @return The desired Axis
     */
    public Axis getAxis(String axisname) {
        if (axisname==null) return null;
        return axis.get(axisname.toLowerCase());
    }
    
    /**
     * This list is used to add special commands to gnuplot, before the automatically
     * generated from this library. It is a convenient method to send unsupported commands
     * to gnuplot at the beginning of the program.
     * @return The list of the initialization commands
     */
    public ArrayList<String> getPreInit() {
        return preinit;
    }
    /**
     * This list is used to add special commands to gnuplot, after the automatically
     * generated from this library. It is a convenient method to send unsupported commands
     * to gnuplot at the end of the program, just before the final plot command.
     * @return he list of the post initialization commands
     */
    public ArrayList<String> getPostInit() {
        return postinit;
    }
    
    
    /**
     * Add a new plot to the specified GNUPlot object.
     * At least one plot is needed to produce visual results.
     * @param plot The given plot.
     */
    public void addPlot(Plot plot) {
        if (plot!=null)
            plots.add(plot);
    }
    
    
    
    /**
     * Get the actual GNUPlot commands. This method is used to construct the gnuplot program
     * @param term The terminal to use
     * @return The GNUPlot program
     */
    String getPlotCommands(GNUPlotTerminal term) {
        StringBuffer bf = new StringBuffer();
        
        /* First execute pre-init commands */
        for (String com:preinit) {
            bf.append(com).append(NL);
        }
        
        /* Set various axis parameters */
        for (Axis ax : axis.values()) {
            ax.appendProperties(bf);
        }
        
        /* Gather various "set" parameters */
        appendProperties(bf);
        
        /* Set Terminal (and it's parameters) */
        if (!term.getType().equals(""))
            bf.append("set term ").append(term.getType()).append(NL);
        if (!term.getOutputFile().equals(""))
            bf.append("set output \'").append(term.getOutputFile()).append("\'").append(NL);
        
        
        /* We are almost ready. Before executing the actual plot command, issue the post-init commands */
        for (String com:postinit) {
            bf.append(com).append(NL);
        }

        
        /* Create data plots */
        bf.append("_gnuplot_error = 1").append(NL);
        bf.append("plot");  // Set error parameter
        /* Add plot definitions */
        for (Plot p:plots) {
            bf.append(' ');
            p.retrieveDefinition(bf);
            bf.append(',');
        }
        bf.deleteCharAt(bf.length()-1);
        bf.append(" ; _gnuplot_error=0").append(NL);    // Reset error parameter. if everything OK
        /* Add plot data (if any) */
        for(Plot p:plots)
            p.retrieveData(bf);
        
        /* Print error check */
        bf.append("if (_gnuplot_error == 1) print '").append(ERRORTAG).append('\'').append(NL);
        bf.append("if (_gnuplot_error == 0) print '").append(SUCCESSTAG).append('\'').append(NL);
        
        /* Finish! */
        bf.append("quit").append(NL);
        
        return bf.toString();
    }

    /**
     * Get the list of the stored plots
     * @return List of Plot objects
     */
    ArrayList<Plot> getPlots() {
        return plots;
    }

}
