package com.panayotis.gnuplot.demo;

/* Copyright (c) 2007-2014 by panayotis.com
 *
 * JavaPlot is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, version 2.
 *
 * JavaPlot is free in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with CrossMobile; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.terminal.SVGTerminal;
import com.panayotis.gnuplot.utils.Debug;
import javax.swing.JFrame;

/**
 * This Object is used to demonstrate JavaPlot library
 *
 * @author teras
 */
public class SVGDemo {

    /**
     * @param args the command line arguments. First argument is the path of
     * gnuplot application
     */
    public static void main(String[] args) {
        String path = null;
        if (args.length > 0)
            path = args[0];

        SVGTerminal(path);
    }

    /* This demo code displays plot on screen using SVG commands (only b&w) */
    private static JavaPlot SVGTerminal(String gnuplotpath) {
        JavaPlot p = new JavaPlot();
        JavaPlot.getDebugger().setLevel(Debug.VERBOSE);

        SVGTerminal svg = new SVGTerminal();
        p.setTerminal(svg);

        p.setTitle("SVG Terminal Title");
        p.addPlot("x+3");
        p.plot();

        try {
            JFrame f = new JFrame();
            f.getContentPane().add(svg.getPanel());
            f.pack();
            f.setLocationRelativeTo(null);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setVisible(true);
        } catch (ClassNotFoundException ex) {
            System.err.println("Error: Library SVGSalamander not properly installed?");
        }

        return p;
    }

}
