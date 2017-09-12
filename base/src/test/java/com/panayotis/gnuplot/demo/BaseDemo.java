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
import com.panayotis.gnuplot.GNUPlotParameters;
import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.dataset.FileDataSet;
import com.panayotis.gnuplot.dataset.GenericDataSet;
import com.panayotis.gnuplot.dataset.parser.DataParser;
import com.panayotis.gnuplot.layout.StripeLayout;
import com.panayotis.gnuplot.plot.AbstractPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.NamedPlotColor;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;
import com.panayotis.gnuplot.swing.JPlot;
import com.panayotis.gnuplot.terminal.PostscriptTerminal;
import com.panayotis.gnuplot.utils.Debug;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/**
 * This Object is used to demonstrate JavaPlot library
 *
 * @author teras
 */
public class BaseDemo {

    /**
     * @param args the command line arguments. First argument is the path of
     * gnuplot application
     */
    public static void main(String[] args) {
        String path = null;
        if (args.length > 0)
            path = args[0];

        //simple();
        //simple3D();
//        defaultTerminal(path);
//        EPSTerminal(path);
//        SVGTerminal(path);
        //JPlotTerminal(path);
        //serialization(defaultTerminal(path));
        //file();
        //interactive();
        labels();
    }

    /* This is a very simple plot to demonstrate JavaPlot graphs */
    private static void simple() {
        JavaPlot p = new JavaPlot();
        p.addPlot("sin(x)");
        p.plot();
    }

    /* This is a very simple plot to demonstrate JavaPlot 3d graphs */
    private static void simple3D() {
        JavaPlot p = new JavaPlot(true);
        p.addPlot("sin(x)*y");
        p.plot();
    }

    /* This demo code uses default terminal. Use it as reference for other javaplot arguments  */
    private static JavaPlot defaultTerminal(String gnuplotpath) {
        JavaPlot p = new JavaPlot(gnuplotpath);
        JavaPlot.getDebugger().setLevel(Debug.VERBOSE);

        p.setTitle("Default \"Terminal Title\"");
        p.getAxis("x").setLabel("X axis", "Arial", 20);
        p.getAxis("y").setLabel("Y axis");

        p.getAxis("x").setBoundaries(-30, 20);
        p.setKey(JavaPlot.Key.TOP_RIGHT);

        double[][] plot = {{1, 1.1}, {2, 2.2}, {3, 3.3}, {4, 4.3}};
        DataSetPlot s = new DataSetPlot(plot);
        p.addPlot(s);
        p.addPlot("besj0(x)*0.12e1");
        PlotStyle stl = ((AbstractPlot) p.getPlots().get(1)).getPlotStyle();
        stl.setStyle(Style.POINTS);
        stl.setLineType(NamedPlotColor.GOLDENROD);
        stl.setPointType(5);
        stl.setPointSize(8);
        p.addPlot("sin(x)");

        p.newGraph();
        p.addPlot("sin(x)");

        p.newGraph3D();
        double[][] plot3d = {{1, 1.1, 3}, {2, 2.2, 3}, {3, 3.3, 3.4}, {4, 4.3, 5}};
        p.addPlot(plot3d);

        p.newGraph3D();
        p.addPlot("sin(x)*sin(y)");

        p.setMultiTitle("Global test title");
        StripeLayout lo = new StripeLayout();
        lo.setColumns(9999);
        p.getPage().setLayout(lo);
        p.plot();

        return p;
    }

    /* This demo code creates a EPS file on home directory */
    private static JavaPlot EPSTerminal(String gnuplotpath) {
        JavaPlot p = new JavaPlot();

        PostscriptTerminal epsf = new PostscriptTerminal(System.getProperty("user.home")
                + System.getProperty("file.separator") + "output.eps");
        epsf.setColor(true);
        p.setTerminal(epsf);

        p.setTitle("Postscript Terminal Title");
        p.addPlot("sin (x)");
        p.addPlot("sin(x)*cos(x)");
        p.newGraph();
        p.addPlot("cos(x)");
        p.setTitle("Trigonometric functions -1");
        p.setMultiTitle("Trigonometric functions");
        p.plot();
        return p;
    }

    /* This demo code displays plot on screen using image terminal */
    private static JavaPlot JPlotTerminal(String gnuplotpath) {
        JPlot plot = new JPlot();
        plot.getJavaPlot().addPlot("sqrt(x)/x");
        plot.getJavaPlot().addPlot("x*sin(x)");
        plot.plot();

        JFrame f = new JFrame();
        f.getContentPane().add(plot);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        return plot.getJavaPlot();
    }

    private static void serialization(JavaPlot p) {
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("koko.lala"));
            out.writeObject(p.getParameters());

            in = new ObjectInputStream(new FileInputStream("koko.lala"));
            JavaPlot q = new JavaPlot((GNUPlotParameters) in.readObject());
            q.plot();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    /* This is a simple plot to demonstrate file datasets */
    private static void file() {
        try {
            JavaPlot p = new JavaPlot();
            p.addPlot(new FileDataSet(new File("lala")));
            p.plot();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void interactive() {
        JavaPlot p = new JavaPlot(true);
        p.addPlot("sin(x)*y");
        p.setInteractive(true);
        p.plot();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        p.close();
    }

    /* This is a simple plot to demonstrate plotting labels beside points with different colors */
    private static void labels(){
        GenericDataSet dataSet = new GenericDataSet(new DataParser() {
            public boolean isValid(String s, int i) {
                return true;
            }
        });

        //fill dataset
        for (int i = 0; i < 100; i++){
            double x = Math.random() * 1000;
            double y = Math.random() * 1000;
            double z = Math.random() * 1000;
            String label = Character.toString((char)(int)(Math.random() * 26 + 65));
            int rgbColor = (int)(Math.random() * 0x1000000);

            List<String> items = new ArrayList<String>(5);
            items.add(String.valueOf(x));
            items.add(String.valueOf(y));
            items.add(String.valueOf(z));
            items.add(label);
            items.add(String.format("0x%x", rgbColor));
            dataSet.add(items);
        }

        JavaPlot p = new JavaPlot(true);
        p.addPlot(dataSet);

        AbstractPlot plot = (AbstractPlot)p.getPlots().get(0);
        PlotStyle style = plot.getPlotStyle();
        style.setStyle(Style.LABELS);
        style.setLabelPointType(7);
        style.setLabelOffset(0, 1);
        style.setLineType(NamedPlotColor.VARIABLE);

        p.plot();
    }
}
