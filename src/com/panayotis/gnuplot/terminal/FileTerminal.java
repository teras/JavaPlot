/*
 * FileTerminal.java
 *
 * Created on October 17, 2007, 2:51 AM
 *
 */
package com.panayotis.gnuplot.terminal;

import java.io.InputStream;

/**
 *
 * @author teras
 */
public class FileTerminal extends ExpandableTerminal {
    
    protected String filename;
    /**
     * Creates a new instance of FileTerminal and output to STDOUT
     * @param type
     */
    public FileTerminal(String type) {
        this(type, "");
    }
    /**
     * Creates a new instance of FileTerminal and output to a specific file
     * @param type
     * @param filename
     */
    public FileTerminal(String type, String filename) {
        super(type);
        if (filename==null) filename = "";
        this.filename = filename;
    }
    
    
    
    /**
     *
     * @return The output filename
     */
    public String getOutputFile() {
        return filename;
    }
    
    /**
     *
     * @param stdout
     */
    public String processOutput(InputStream stdout) { return null; }
    
}
