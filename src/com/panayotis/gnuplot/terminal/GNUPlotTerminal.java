/*
 * GNUPlotTerminal.java
 *
 * Created on October 14, 2007, 2:36 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.panayotis.gnuplot.terminal;

import java.io.InputStream;

/**
 * This interface represents any GNUPlot terminal. "Terminal" by the definition of
 * GNUPlot is any device which will present the drawn plots.
 * @author teras
 */
public interface GNUPlotTerminal {
    
    /**
     * Get the terminal type / terminal name. A list of available terminal names can be found
     * through gnuplot if you issue the command "set term".
     * @return the terminal type
     */
    public String getType();
    /**
     * Get the output filename. Use "" if not output file is desired or needed.
     * @return The output filename
     */
    public String getOutputFile();
    /**
     * This method is executed bu GNUPlot, when the plot has been performed. It actually 
     * transforms the GNUPlot output to this method, if parsing is required. If no output
     * filename is given, then by default the plot will be dumped to output.
     * @param stdout The output stream of GNUPlot. Note that since it is required to read from this stream, 
     * it is given as InputStream.
     */
    public void processOutput(InputStream stdout) ;
}
