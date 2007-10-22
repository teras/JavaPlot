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
    
    
    String message = null;
    String error = "";
    public void setM(String g) {
        if (message==null) message = g;
        else message +='\n'+g;
    }
    public void setE(String e) {
        error = e;
    }
    synchronized void plot(GNUPlotParameters par, GNUPlotTerminal terminal) throws GNUPlotException {
        try {
            final GNUPlotTerminal term = terminal;  // Use this thread-aware variable instead of "terminal"
            final String comms = par.getPlotCommands(term);
            
            GNUPlot.getDebugger().msg("** Start of plot commands **", Debug.INFO);
            GNUPlot.getDebugger().msg(comms, Debug.INFO);
            GNUPlot.getDebugger().msg("** End of plot commands **", Debug.INFO);
            
            /* It's time now to start the actual gnuplot application */
            String [] command = { getGNUPlotPath() };
            final Process proc = Runtime.getRuntime().exec(command);
            
            
            
            final StringBuffer er = new StringBuffer();
            final StringBuffer ou = new StringBuffer();
            
            /* Windows buffers DEMAND asynchronus read & write */
            Thread err_thread = new Thread() {
                public void run() {
                    BufferedReader err = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
                    String line;
                    try {
                        while ( (line=err.readLine())!=null ) {
                            if (line.indexOf("gnuplot>")>=0)
                                line="";
                            line = line.replace("input data ('e' ends) >","").trim();
                            if (!line.equals("")) {
                                er.append(line).append('\n');
                                if (line.indexOf(GNUPlotParameters.ERRORTAG)>=0) {
                                    setE(er.toString());
                                    setM("Error while parsing \'plot\' arguments.");
                                    break;
                                }
                                if (line.indexOf(GNUPlotParameters.SUCCESSTAG)>=0)
                                    break;
                            }
                        }
                        err.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            };
            err_thread.start();
            Thread out_thread = new Thread() {
                public void run() {
                    setM(term.processOutput(proc.getInputStream()));
                }
            };
            out_thread.start();
            /* We utilize the current thread */
            OutputStreamWriter out = new OutputStreamWriter(proc.getOutputStream());
            out.write(comms);
            out.flush();
            out.close();
            
            
            try {
                proc.waitFor();
                err_thread.join();
                out_thread.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            
            int level = Debug.VERBOSE;
            if (message!=null && message.equals("")) message = null;
            if (message!=null) level = Debug.ERROR;
            GNUPlot.getDebugger().msg("** Start of error stream **", level);
            GNUPlot.getDebugger().msg(error, level);
            GNUPlot.getDebugger().msg("** End of error stream **", level);
            
            if (message!=null) throw new GNUPlotException(message);
            
        } catch (IOException ex) {
            throw new GNUPlotException("IOException while executing \""+getGNUPlotPath()+"\":"+ex.getLocalizedMessage());
        }
        
    }
    
}