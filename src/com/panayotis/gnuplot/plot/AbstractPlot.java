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
import com.panayotis.gnuplot.style.Smooth;

/**
 *
 * @author teras
 */
public abstract class AbstractPlot extends PropertiesHolder implements Plot {
    
    private static int last_id = 0;
  
    private String definition = "";
    private PlotStyle style = null;
    private Smooth smooth = null;
    
    /**
     * 
     */
    public AbstractPlot() {
        super(" ", "");
        set("title", "'Datafile " + (++last_id)+"'");
    }
    
    /**
     * 
     * @param definition
     */
    protected void setDefinition(String definition) {
        this.definition = definition;
    }
    /**
     * 
     * @return
     */
    public String getDefinition() {
        StringBuffer buf = new StringBuffer();
        buf.append(definition);
        getProperties(buf);
        if (smooth!=null) buf.append(smooth.toString());
        if (style!=null) style.getProperties(buf);
        return buf.toString();
    }
    
    /**
     * 
     * @param style
     */
    public void setPlotStyle(PlotStyle style) {
        this.style= style;
    }
    /**
     * 
     * @return
     */
    public PlotStyle getPlotStyle() {
        if (style==null)
            style = new PlotStyle();
        return style;
    }
    /**
     * 
     * @param smooth
     */
    public void setSmooth(Smooth smooth) {
        this.smooth = smooth;
    }
}
