/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panayotis.gnuplot.plot;

import java.util.ArrayList;
import java.util.HashMap;

import static com.panayotis.gnuplot.GNUPlotParameters.*;

/**
 *
 * @author teras
 */
public class Graph extends ArrayList<Plot> {

    protected static final String NL = System.getProperty("line.separator");
    private HashMap<String, Axis> axis;

    public Graph() {
        axis = new HashMap<String, Axis>();
        axis.put("x", new Axis("x"));
        axis.put("y", new Axis("y"));
        axis.put("z", new Axis("z"));
    }

    /**
     *  Get one of the available Axis, in orde to set some parameters on it.
     * @param axisname The name of the Axis. It is usually "x", "y", "z"
     * @return The desired Axis
     */
    public Axis getAxis(String axisname) {
        if (axisname == null) {
            return null;
        }
        return axis.get(axisname.toLowerCase());
    }

    /**
     * Add a new plot to this plotgroup.
     * At least one plot is needed to produce visual results.
     * @param plot The given plot.
     */
    public void addPlot(Plot plot) {
        if (plot != null) {
            add(plot);
        }
    }

    public void retrieveData(StringBuffer bf) {
        /* Set various axis parameters */
        for (Axis ax : axis.values()) {
            ax.appendProperties(bf);
        }

        /* Create data plots */
        bf.append("_gnuplot_error = 1").append(NL);
        bf.append("plot");  // Set error parameter
        /* Add plot definitions */
        for (Plot p : this) {
            bf.append(' ');
            p.retrieveDefinition(bf);
            bf.append(',');
        }
        bf.deleteCharAt(bf.length() - 1);
        bf.append(" ; _gnuplot_error=0").append(NL);    // Reset error parameter. if everything OK
        /* Add plot data (if any) */
        for (Plot p : this) {
            p.retrieveData(bf);
        }

        /* Print error check */
        bf.append("if (_gnuplot_error == 1) print '").append(ERRORTAG).append('\'').append(NL);
        bf.append("if (_gnuplot_error == 0) print '").append(SUCCESSTAG).append('\'').append(NL);
    }
}
