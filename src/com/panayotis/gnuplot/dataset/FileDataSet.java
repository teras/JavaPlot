/*
 * FileDataSet.java
 *
 * Created on October 24, 2007, 2:00 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.panayotis.gnuplot.dataset;

import com.panayotis.gnuplot.GNUPlotException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 *
 * @author teras
 */
public class FileDataSet extends PointDataSet<Double> {
    
    /** Creates a new instance of FileDataSet
     * @param datafile
     * @throws java.io.IOException 
     * @throws com.panayotis.gnuplot.GNUPlotException
     * @throws java.lang.NumberFormatException 
     */
    public FileDataSet(File datafile) throws IOException, GNUPlotException, NumberFormatException {
        super();
        
        Double [] dat = null;
        int pos;
        BufferedReader in = new BufferedReader(new FileReader(datafile));
        String line;
        while ( (line=in.readLine())!=null && (!line.equals("")) ) {
            line = line.trim();
            if (!line.startsWith("#")) {
                StringTokenizer tk = new StringTokenizer(line);
                if (dat==null) {    // first run
                    dat = new Double[tk.countTokens()];
                } else {
                    if (dat.length!=tk.countTokens())
                        throw new GNUPlotException("Error with number of columns for file \'"
                                +datafile.getPath()+"\'. Was "+dat.length+", wanted "+tk.countTokens());
                }
                pos = 0;
                while (tk.hasMoreTokens()) {
                    dat[pos++] = Double.valueOf(tk.nextToken());
                }
                add(new Point<Double>(dat));
            }
        }
    }
    
    
}
