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
import com.panayotis.gnuplot.style.PlotStyle;

/**
 *
 * @author teras
 */
public abstract class AbstractPlot extends PropertiesHolder implements Plot {
    
    private static int last_id = 0;
  
    private String definition = "";
    private PlotStyle style;
    
    public AbstractPlot() {
        super(" ", "");
        set("title", "'Datafile " + (++last_id)+"'");
    }
    
    protected void setDefinition(String definition) {
        this.definition = definition;
    }
    public String getDefinition() {
        StringBuffer buf = new StringBuffer();
        buf.append(definition);
        getProperties(buf);
        if (style!=null) style.getProperties(buf);
        return buf.toString();
    }
    
    public void setPlotStyle(PlotStyle style) {
        this.style= style;
    }
    public PlotStyle getPlotStyle() {
        if (style==null)
            style = new PlotStyle();
        return style;
    }
}
