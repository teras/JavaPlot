/*
 * ExpandableTerminal.java
 *
 * Created on October 21, 2007, 6:58 PM
 *
 */
package com.panayotis.gnuplot.terminal;

import com.panayotis.gnuplot.PropertiesHolder;

/**
 *
 * @author teras
 */
public abstract class ExpandableTerminal extends PropertiesHolder implements GNUPlotTerminal {
   
    private String type;
    
    public ExpandableTerminal(String type) {
        super(" ", "");
        if (type==null) type ="unknown";
        this.type = type;
    }
    
     /**
     *
     * @return The type of this terminal
     */
    public String getType() {
        StringBuffer buf = new StringBuffer();
        buf.append(type);
        getProperties(buf);
        return buf.toString();
    }

}
