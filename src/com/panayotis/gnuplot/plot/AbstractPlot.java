/*
 * AbstractPlot.java
 *
 * Created on 12 Οκτώβριος 2007, 4:07 μμ
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.panayotis.gnuplot.plot;

import com.panayotis.gnuplot.PropertiesHolder;

/**
 *
 * @author teras
 */
public abstract class AbstractPlot extends PropertiesHolder implements Plot {
    
    private static int last_id = 0;
    private String definition = "";
    
    public AbstractPlot() {
        super(" ", "");
        set("title", "'Datafile " + (++last_id)+"'");
    }
    
    protected void setDefinition(String definition) {
        this.definition = definition;
    }
    public String getPlotDefinition() {
        StringBuffer buf = new StringBuffer();
        buf.append(definition);
        getProperties(buf);
        return buf.toString();
    }
}
