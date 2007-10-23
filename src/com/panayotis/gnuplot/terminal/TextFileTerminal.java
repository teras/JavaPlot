/*
 * TextFileTerminal.java
 *
 * Created on October 23, 2007, 10:46 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.panayotis.gnuplot.terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author teras
 */
public class TextFileTerminal extends FileTerminal {
    private StringBuffer output;
    
    /** Creates a new instance of TextFileTerminal */
    public TextFileTerminal(String type) {
        this(type, "");
    }
    /**
     * Creates a new instance of TextFileTerminal and output to a specific file
     * @param type
     * @param filename
     */
    public TextFileTerminal(String type, String filename) {
        super(type, filename);
    }
    
    public String processOutput(InputStream stdout) {
        output = new StringBuffer();
        BufferedReader in = new BufferedReader(new InputStreamReader(stdout));
        String line;
        try {
            while ((line=in.readLine())!=null)
                output.append(line);
            in.close();
        } catch (IOException ex) {
            return "I/O error while processing gnuplot output: "+ex.getMessage();
        }
        return null;
    }
    
    public String getTextOutput() {
        if (output==null) return null;
        return output.toString();
    }
}
