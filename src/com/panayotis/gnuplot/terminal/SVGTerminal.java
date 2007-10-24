/*
 * SVGTerminal.java
 *
 * Created on October 24, 2007, 7:47 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.panayotis.gnuplot.terminal;

import java.io.InputStream;

/**
 *
 * @author teras
 */
public class SVGTerminal extends TextFileTerminal {
    
    /** Creates a new instance of SVGTerminal */
    public SVGTerminal() {
        this("");
    }
    
    public SVGTerminal(String filename) {
        super("svg", filename);
    }
    
    public String processOutput(InputStream stdout) {
        String out = super.processOutput(stdout);
        if (out!=null) {
            out = out.replace("currentColor", "black");
        }
        return out;
    }
}
