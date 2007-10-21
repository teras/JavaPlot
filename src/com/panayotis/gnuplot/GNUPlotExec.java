/*
 * GNUPlotExec.java
 *
 * Created on October 14, 2007, 1:42 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.panayotis.gnuplot;

import com.panayotis.debug.Debug;
import com.panayotis.gnuplot.terminal.GNUPlotTerminal;
import com.panayotis.io.FileUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 * @author teras
 */
class GNUPlotExec {
    
    private static final transient String DEFAULT_PATH = FileUtils.findPathExec("gnuplot");
    private transient File gnuplotexec;
    
    /** Creates a new instance of GNUPlotExec */
    GNUPlotExec() throws IOException {
        this(null);
    }
    GNUPlotExec(String path) throws IOException {
        if (path==null) path = DEFAULT_PATH;
        setGNUPlotPath(path);
    }
    
    
    void setGNUPlotPath(String path) throws IOException {
        File file = FileUtils.getExec(path);
        if (file!=null)
            gnuplotexec = file;
        else
            throw new IOException("GnuPlot executable \""+path+"\" not found.");
    }
    String getGNUPlotPath() {
        return gnuplotexec.getPath();
    }
    
    void plot(GNUPlotParameters par, GNUPlotTerminal term) throws GNUPlotException {
        try {
            String comms = par.getPlotCommands(term);
            GNUPlot.getDebugger().msg("** Start of plot commands **", Debug.INFO);
            GNUPlot.getDebugger().msg(comms, Debug.INFO);
            GNUPlot.getDebugger().msg("** End of plot commands **", Debug.INFO);
            
            /* It's time now to start the actual gnuplot application */
            String [] command = { getGNUPlotPath(), "-" };
            Process proc = Runtime.getRuntime().exec(command);
            OutputStreamWriter out = new OutputStreamWriter(proc.getOutputStream());
            out.write(comms);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            
            StringBuffer er = new StringBuffer();
            StringBuffer ou = new StringBuffer();
            
            String line;
            while ( (line=in.readLine())!=null ) {
                if (line.indexOf("gnuplot>")>=0)
                    line="";
                line = line.replace("input data ('e' ends) >","").trim();
                if (!line.equals("")) {
                    if (line.indexOf(GNUPlotParameters.ERRORTAG)>=0) {
                        GNUPlot.getDebugger().msg("** Start of error stream **", Debug.INFO);
                        GNUPlot.getDebugger().msg(er.toString(), Debug.ERROR);
                        GNUPlot.getDebugger().msg("** End of error stream **", Debug.INFO);
                        throw new GNUPlotException("Error while parsing \'plot\' arguments.");
                    } else {
                        er.append(line).append('\n');
                    }
                }
            }
            in.close();
            term.processOutput(proc.getInputStream());
            
            out.close();
            in.close();
            
        } catch (IOException ex) {
            throw new GNUPlotException("IOException while executing \""+getGNUPlotPath()+"\":"+ex.getLocalizedMessage());
        }
        
    }
    
}