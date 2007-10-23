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
public class PostscriptTerminal extends TextFileTerminal {
    
    public PostscriptTerminal() {
        this("");
    }
    public PostscriptTerminal(String filename) {
        super("postscript", filename);
        setColor(true);
        setEPS(true);
    }
    
    public void setEPS(boolean eps) {
        if (eps)
            set("eps");
        else
            unset("eps");
    }
    
    public void setColor(boolean color) {
        if (color)  {
            set("color");
            unset("monochrome");
        } else {
            set("monochrome");
            unset("color");
        }
    }
    
}
