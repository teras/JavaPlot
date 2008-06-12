package com.panayotis.gnuplot.layout;

import com.panayotis.gnuplot.plot.Page;
import java.io.Serializable;

/**
 * Align graphs evenly on the page, in a stripe layout.
 * 
 * The stripes start from the bottom and go up.
 * <p>
 * If you manually set metrics and use this, these metrics will be lost
 * @author dan
 */
// TODO(dan): Add a switch for top-down or bottom-up striping
public class StripeLayout implements GraphLayout, Serializable {
    private static final long serialVersionUID = 84294413536114483L;

    public StripeLayout() {
    }

    /**
     * Lay out graphs.
     *
     * @param page The page with the elements we would like to position
     */
    public void updateMetrics(Page page) {
        int size = page.size();

        if (size <= 0)
            return;

        /*
        int width, height;
        height = size;
        width = 1;
        */

        for (int index = 0; index < size; index++) {
            page.get(index).setMetrics(0, ((float)index) / size, 1, 1f / size);
        }
    }
}
