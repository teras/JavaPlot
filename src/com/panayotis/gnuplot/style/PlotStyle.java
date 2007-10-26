/*
 * PlotStyle.java
 *
 * Created on 26 Οκτώβριος 2007, 2:58 μμ
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.panayotis.gnuplot.style;

import com.panayotis.gnuplot.*;

/**
 *
 * @author teras
 */
public class PlotStyle extends PropertiesHolder {
    
    private Style type;
    private FillStyle fill;
    
    /**
     * Creates a new instance of PlotStyle
     */
    public PlotStyle() {
        this(null);
    }
    public PlotStyle(Style type) {
        super(" ", "");
        this.type = type;
        fill = null;
    }
    
    public void setStyle(Style style) {
        this.type = style;
    }
    
    public void getProperties(StringBuffer buf) {
        if (type!=null) {
            buf.append(" with ").append(type.name().toLowerCase());
            super.getProperties(buf);
            
            if (fill!=null && type.filled) fill.getProperties(buf);
        }
    }
    
    public void setLineWidth(int width) {
        if (width<0)
            unset("linewidth");
        else
            set("linewidth", String.valueOf(width));
    }
    
    public void setPointSize(int width) {
        if (width<0)
            unset("pointsize");
        else
            set("pointsize", String.valueOf(width));
    }
    
    public void setLineType(int type) {
        if (type<-1)
            unset("linetype");
        else
        set("linetype", String.valueOf(type));
    }
    public void setLineType(PlotColor col) {
        if (col==null) 
            unset("linetype");
        else
            set("linetype", col.toString());
    }

    public void setPointType(int type) {
        if (type<-1)
            unset("pointtype");
        else
        set("pointtype", String.valueOf(type));
    }
    
    public void setFill(FillStyle fillstyle) {
        this.fill = fillstyle;
    }
    
    
}
