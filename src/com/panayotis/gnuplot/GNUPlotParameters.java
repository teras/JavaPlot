/*
 * GNUPlotParameters.java
 *
 * Created on October 13, 2007, 4:02 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.panayotis.gnuplot;

import com.panayotis.gnuplot.PropertiesHolder;
import com.panayotis.gnuplot.plot.Plot;
import com.panayotis.gnuplot.terminal.GNUPlotTerminal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
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
    
    
    public GNUPlotParameters() {
        plots = new ArrayList<Plot>();
        
        axis = new HashMap<String,Axis>();
        axis.put("x", new Axis("x"));
        axis.put("y", new Axis("y"));
        axis.put("z", new Axis("z"));
        
        preinit = new ArrayList<String>();
        postinit = new ArrayList<String>();
    }
    
    
    
    public Axis getAxis(String axisname) {
        if (axisname==null) return null;
        return axis.get(axisname.toLowerCase());
    }
    
    public ArrayList<String> getPreInit() {
        return preinit;
    }
    public ArrayList<String> getPostInit() {
        return postinit;
    }
    
    
    /**
     * Add a new plot to the specified GNUPlot.
     * At least one plot is needed to produce visual results.
     * @param plot The given plot.
     */
    public void addPlot(Plot plot) {
        if (plot!=null)
            plots.add(plot);
    }
    
    
    
    String getPlotCommands(GNUPlotTerminal term) {
        StringBuffer bf = new StringBuffer();
        
        /* First execute pre-init commands */
        for (String com:preinit) {
            bf.append(com).append(NL);
        }
        
        /* Set various axis parameters */
        for (Axis ax : axis.values()) {
            ax.getProperties(bf);
        }
        
        /* Gather various "set" parameters */
        getProperties(bf);
        
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
            bf.append(' ').append(p.getPlotDefinition()).append(',');
        }
        bf.deleteCharAt(bf.length()-1);
        bf.append(" ; _gnuplot_error=0").append(NL);    // Reset error parameter. if everything OK
        /* Add plot data (if any) */
        for(Plot p:plots)
            bf.append(p.getData());
        
        /* Print error check */
        bf.append("if (_gnuplot_error == 1) print '").append(ERRORTAG).append('\'').append(NL);
        bf.append("if (_gnuplot_error == 0) print '").append(SUCCESSTAG).append('\'').append(NL);
        
        /* Finish! */
        bf.append("quit").append(NL);
        
        return bf.toString();
    }
    
}
