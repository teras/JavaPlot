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
 * This is the generic interface which every data set object should provide. By
 * implementing this interface the author can create his own data objects which 
 * can be used in JavaPlot
 * @author teras
 */
public interface DataSet {
    
    /**
     * Retrieve how many points this data set has.
     * @return the number of points
     */
    public int size();
    /** 
     * Retrieve how many dimensions this dataset refers to.
     * Typically, for every point, this method informs JavaPlot how many "columns"
     * of data this point has. Make sure that every point has at least as many
     * dimensions as what is reported here .
     * @return the number of dimensions
     */
    public int getDimensions();
    
    /**
     * Retrieve data information from a point.
     * To retrieve information for each point, a continious call to this method will be
     * executed, keeping the item number constant and increasing the dimension.
     * @param point The point number
     * @param dimension The point dimension (or "column") to request data from
     * @return the point data for this dimension
     */
    public String getPointValue(int point, int dimension);
   
}
