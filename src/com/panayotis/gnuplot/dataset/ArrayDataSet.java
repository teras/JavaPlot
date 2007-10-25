/*
 * ArrayDataSet.java
 *
 * Created on 15 Οκτώβριος 2007, 12:29 μμ
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.panayotis.gnuplot.dataset;

import java.io.Serializable;

/**
 *
 * @author teras
 */
public class ArrayDataSet implements DataSet, Serializable {
    double[][] val; 
    
    /** Creates a new instance of ArrayDataSet */
    public ArrayDataSet(double[][] values) {
        int length = values.length;
        int dimension = values[0].length;
        int i, j;
        
        if (length==0) throw new ArrayStoreException("Array has zero points");
        val = new double[length][dimension];
        for( i = 0 ; i < length ; i++) {
            if (values[i].length!=dimension) 
                throw new ArrayStoreException("Array has not consistent size, was "+dimension+", found "+values[i].length);
            for(j = 0 ; j < dimension ; j++)
                val[i][j] = values[i][j];
        }
    }
    public ArrayDataSet(float[][] values) {
        int length = values.length;
        int dimension = values[0].length;
        int i, j;
        
        if (length==0) throw new ArrayStoreException("Array has zero points");
        val = new double[length][dimension];
        for( i = 0 ; i < length ; i++) {
            if (values[i].length!=dimension) 
                throw new ArrayStoreException("Array has not consistent size, was "+dimension+", found "+values[i].length);
            for(j = 0 ; j < dimension ; j++)
                val[i][j] = values[i][j];
        }
    }
    public ArrayDataSet(int[][] values) {
        int length = values.length;
        int dimension = values[0].length;
        int i, j;
        
        if (length==0) throw new ArrayStoreException("Array has zero points");
        val = new double[length][dimension];
        for( i = 0 ; i < length ; i++) {
            if (values[i].length!=dimension) 
                throw new ArrayStoreException("Array has not consistent size, was "+dimension+", found "+values[i].length);
            for(j = 0 ; j < dimension ; j++)
                val[i][j] = values[i][j];
        }
    }
    public ArrayDataSet(long[][] values) {
        int length = values.length;
        int dimension = values[0].length;
        int i, j;
        
        if (length==0) throw new ArrayStoreException("Array has zero points");
        val = new double[length][dimension];
        for( i = 0 ; i < length ; i++) {
            if (values[i].length!=dimension) 
                throw new ArrayStoreException("Array has not consistent size, was "+dimension+", found "+values[i].length);
            for(j = 0 ; j < dimension ; j++)
                val[i][j] = values[i][j];
        }
    }
    
    public int size() {
        return val.length;
    }

    public int getDimensions() {
        if (val[0]==null) return -1;
        return val[0].length;
    }

    public double getPointValue(int item, int dimension) {
        return val[item][dimension];
    }
    
    
}
