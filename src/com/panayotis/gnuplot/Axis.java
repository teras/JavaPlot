/*
 * Axis.java
 *
 * Created on October 14, 2007, 10:59 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.panayotis.gnuplot;

import com.panayotis.gnuplot.PropertiesHolder;
import java.util.HashMap;

/**
 *
 * @author teras
 */
public class Axis extends PropertiesHolder {
    
    private String name;
    
    /** Creates a new instance of Axis */
    Axis(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setLogScale(boolean log) {
        if (log) 
            set("logscale", getName());
        else
            unset("logscale");
    }
    
   
    public void setLabel(String label) {
        setLabel(label, null, -1);
    }
    
    /**
     * Set the label and the font of the current axis
     * @param label The label of this axis
     * @param font Font name
     * @param size Font size
     */
    public void setLabel(String label, String font, int size) {
        String fontname="";
        if (font!=null) {
            fontname=" font '"+font+( (size>1)?","+size:"" )+"'";
        }
        set(getName()+"label", "'"+label+"'"+fontname);
    }
    
}
