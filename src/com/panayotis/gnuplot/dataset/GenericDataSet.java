/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.panayotis.gnuplot.dataset;

import java.util.ArrayList;

/**
 * Generic data class to store data
 * @author teras
 */
public class GenericDataSet extends ArrayList<ArrayList<String>> implements DataSet {

    public int getDimensions() {
        if (size()<1) return -1;
        return get(0).size();
    }

    public String getPointValue(int point, int dimension) {
        return get(point).get(dimension);
    }

}
