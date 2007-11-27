/*
 * DataSetPlot.java
 *
 * Created on 12 Οκτώβριος 2007, 4:07 μμ
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.panayotis.gnuplot.plot;

import com.panayotis.gnuplot.dataset.ArrayDataSet;
import com.panayotis.gnuplot.dataset.DataSet;
import com.panayotis.gnuplot.dataset.PointDataSet;


/**
 *
 * @author teras
 */
public class DataSetPlot extends AbstractPlot {
    
    private DataSet dataset;
    
    /**
     * 
     */
    public DataSetPlot() {
        this(new PointDataSet());
    }
    /**
     * 
     * @param dataset
     */
    public DataSetPlot(double[][] dataset) {
        this(new ArrayDataSet(dataset));
    }
    /**
     * 
     * @param dataset
     */
    public DataSetPlot(float[][] dataset) {
        this(new ArrayDataSet(dataset));
    }
    /**
     * 
     * @param dataset
     */
    public DataSetPlot(int[][] dataset) {
        this(new ArrayDataSet(dataset));
    }
    /**
     * 
     * @param dataset
     */
    public DataSetPlot(long[][] dataset) {
        this(new ArrayDataSet(dataset));
    }
    
    /**
     * 
     * @param dataset
     */
    public DataSetPlot(DataSet dataset) {
        setDataSet(dataset);
        setDefinition("'-'");
    }
    
    
    /**
     * 
     * @return
     */
    public String getData() {
        StringBuffer bf = new StringBuffer();
        int i, j;
        int isize, jsize;
        
        if (dataset!=null) {
            isize = dataset.size();
            jsize = dataset.getDimensions();
            for ( i = 0 ; i < isize ; i++) {
                for( j = 0 ; j < jsize ; j++) {
                    bf.append(dataset.getPointValue(i, j)).append(' ');
                }
                bf.append(NL);
            }
        }
        bf.append("e").append(NL);
        return bf.toString();
    }
    
    /**
     * 
     * @param set
     */
    public void setDataSet(DataSet set) {
        dataset = set;
    }
    /**
     * 
     * @return
     */
    public DataSet getDataSet() {
        return dataset;
    }
    
    
}
