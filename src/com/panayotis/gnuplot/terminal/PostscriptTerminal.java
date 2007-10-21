/*
 * PostscriptTerminal.java
 *
 * Created on October 16, 2007, 1:34 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.panayotis.gnuplot.terminal;

/**
 *
 * @author teras
 */
public class PostscriptTerminal extends FileTerminal {
    
    public PostscriptTerminal(String filename) {
        super("postscript", filename);
    }
       
}
