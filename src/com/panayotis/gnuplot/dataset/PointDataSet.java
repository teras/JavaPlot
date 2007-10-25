/*
 * PointDataSet.java
 *
 * Created on October 15, 2007, 2:10 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.panayotis.gnuplot.dataset;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author teras
 */
public class PointDataSet<N extends Number> extends ArrayList<Point<N>>  implements DataSet, Serializable {
    
    public PointDataSet() {
        super();
    }
    public PointDataSet(int initial) {
        super(initial);
    }
    public PointDataSet(Collection<? extends Point<N>> pts) throws NumberFormatException {
        super(pts);
        int length = size();
        int old_dim = getDimensions();
        for(int i = 0 ; i <length ; i++)
            old_dim = checkDimension(get(i), old_dim);
    }
    
    
    private int checkDimension(Point<N> point, int old_dim) throws NumberFormatException {
        int new_dim = point.getDimensions();
        if (old_dim<0) old_dim = new_dim;   // if the array is still empty, any size is good size
        if (old_dim!=new_dim)
            throw new NumberFormatException("Point inserted differs in dimension: found "+new_dim+", requested "+old_dim);
        return old_dim;
    }
    
    public boolean add(Point<N> point) throws NumberFormatException {
        checkDimension(point, getDimensions());
        return super.add(point);
    }
    
    public void add(int index, Point<N> point) throws NumberFormatException {
        checkDimension(point, getDimensions());
        super.add(index, point);
    }
    
    public boolean addAll(Collection<? extends Point<N>> pts) throws NumberFormatException {
        int old_dim = getDimensions();
        for (Point<N> p : pts)
            old_dim = checkDimension(p, old_dim);
        return super.addAll(pts);
    }
    
    public boolean addAll(int index, Collection<? extends Point<N>> pts) throws NumberFormatException {
        int old_dim = getDimensions();
        for (Point<N> p : pts)
            old_dim = checkDimension(p, old_dim);
        return super.addAll(index, pts);
    }
    public Point<N> set(int index, Point<N> point) throws NumberFormatException {
        checkDimension(point, getDimensions());
        return super.set(index, point);
    }
    
    public void addPoint(N... coords) {
        add(new Point<N>(coords));
    }
    
    public int getDimensions() {
        if (size()==0) return -1;
        return get(0).getDimensions();
    }
    
    public double getPointValue(int index, int dimension) {
        return get(index).get(dimension).doubleValue();
    }
    
    
    @SuppressWarnings("unchecked")
    public static final  <N extends Number> PointDataSet<N> constructDataSet(Class <N> objclass, Object array) throws ArrayStoreException {
        int length, dim, cdim;
        int i, j;
        Object row, value;
        
        if (!array.getClass().isArray())
            throw new ArrayStoreException("The second argument of constructDataSet should be a two dimensional array.");
        
        length = Array.getLength(array);
        dim = -1;
        PointDataSet<N> points = new PointDataSet<N>(length);
        N[] buffer = null;
        
        for (i = 0 ; i<length ; i++) {
            row = Array.get(array, i);
            if (!row.getClass().isArray())
                throw new ArrayStoreException("The second argument of constructDataSet is a one dimensional, instead of two dimensional, array.");
            cdim = Array.getLength(row);
            if (dim<0) {
                dim = cdim;
                buffer = (N[])Array.newInstance(Number.class, dim);
            }
            if (dim!=cdim)
                throw new ArrayStoreException("Array has not consistent size, was "+dim+", found "+cdim);
            for(j = 0 ; j < dim ; j++) {
                value = Array.get(row, j);
                if (!value.getClass().equals(objclass))
                    throw new ArrayStoreException("Array item "+value+" is " + value.getClass().getName()+" and not "+objclass.getName());
                buffer[j]=(N)value;
            }
            points.quickadd(new Point<N>(buffer));
        }
        return points;
    }
    
    private void quickadd(Point<N> point) {
        super.add(point);
    }
    
    
    
}
