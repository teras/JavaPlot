/*
 * ExpandableTerminal.java
 *
 * Created on October 21, 2007, 6:58 PM
 *
 */
package com.panayotis.gnuplot.terminal;

import com.panayotis.gnuplot.PropertiesHolder;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

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
    
    /**
     * This method only consumes gnuplot stdout input stream. It is performed to 
     * prevent a possible thread lockup.
     * @param stdout The output of GNUPlot. It will be consumed.
     */
    public String processOutput(InputStream stdout) {
        byte[] buffer = new byte[1000];
        BufferedInputStream in = new BufferedInputStream(stdout);
        try {
            while ( in.read(buffer)>=0 );   // consume stream
            in.close();
        } catch (IOException ex) {
            return "I/O error while processing gnuplot output: "+ex.getMessage();
        }
        return null;
    }
    
}
