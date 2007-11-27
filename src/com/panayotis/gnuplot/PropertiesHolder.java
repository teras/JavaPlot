/*
 * PropertiesHolder.java
 *
 * Created on October 19, 2007, 2:20 AM
 *
 */
package com.panayotis.gnuplot;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author teras
 */
public class PropertiesHolder extends HashMap<String,String> {
    
    /**
     * 
     */
    protected static final String NL = System.getProperty("line.separator");
    
    private String prefix;
    private String suffix;
    
    /** Creates a new instance of PropertiesHolder */
    public PropertiesHolder() {
        this("set ", NL);
    }
    /**
     * 
     * @param prefix
     * @param suffix
     */
    public PropertiesHolder(String prefix, String suffix) {
        super();
        this.prefix = prefix;
        this.suffix = suffix;
    }
    
    /**
     *
     * @param key
     * @param value The value of the specified parameter.<p>
     * If value is null, then this key will be removed.
     */
    public void set(String key, String value) {
        if (key!=null) {
            if (value==null)
                unset(key);
            else
                put(key, value);
        }
    }
    /**
     * 
     * @param value
     */
    public void set(String value) {
        set(value, "");
    }
    
    /**
     * 
     * @param key
     */
    public void unset(String key) {
        remove(key);
    }
    
    /**
     * 
     * @param bf
     */
    public void getProperties(StringBuffer bf) {
        Object val;
        for(Entry e:entrySet()) {
            bf.append(prefix).append(e.getKey());
            val = e.getValue();
            if (val!=null && (!val.equals("")) ) {
                bf.append(' ').append(e.getValue());
            }
            bf.append(suffix);
        }
    }
}
