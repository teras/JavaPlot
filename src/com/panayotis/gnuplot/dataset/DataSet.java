/*
 * DataSet.java
 *
 * Created on 15 Οκτώβριος 2007, 12:14 μμ
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.panayotis.gnuplot.dataset;

/**
 *
 * @author teras
 */
public interface DataSet {
    
    public int size();
    public int getDimensions();
    
    public double getPointValue(int item, int dimension);
   
}
