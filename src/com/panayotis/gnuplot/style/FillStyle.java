/*
 * FillStyle.java
 *
 * Created on 26 Οκτώβριος 2007, 4:35 μμ
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.panayotis.gnuplot.style;

import com.panayotis.gnuplot.PropertiesHolder;

/**
 *
 * @author teras
 */
public class FillStyle extends PropertiesHolder {
    public enum Fill {EMPTY, SOLID, PATTERN};
    
    private Fill style;
    private String params;
    
    public FillStyle() {
        this(null);
    }
    public FillStyle(Fill style) {
        super(" ", "");
        if (style==null) style = Fill.EMPTY;
        this.style = style;
    }
    
    public void setBorder(int type) {
        unset("noborder");
        set("border", String.valueOf(type));
    }
    public void removeBorder() {
        unset("border");
        set("noborder");
    }
    
    public void setDensity(float density) {
        setStyle(Fill.SOLID);
        params = String.valueOf(density);
    }
    
    public void setPattern(int pattern) {
        setStyle(Fill.PATTERN);
        params = String.valueOf(pattern);
    }
    public void setStyle(Fill style) {
        this.style = style;
        if (style==Fill.EMPTY)
            params="";
    }
    
    public void getProperties(StringBuffer buf) {
        buf.append(" fill ");
        buf.append(style.name().toLowerCase());
        if (!params.equals(""))
            buf.append(' ').append(params);
        
        super.getProperties(buf);
        
    }
}
