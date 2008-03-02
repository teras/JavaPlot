/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.panayotis.gnuplot;

import com.panayotis.iodebug.Debug;
import com.panayotis.gnuplot.plot.AbstractPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.NamedPlotColor;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;
import com.panayotis.gnuplot.terminal.FileTerminal;
import com.panayotis.gnuplot.terminal.ImageTerminal;
import com.panayotis.gnuplot.terminal.PostscriptTerminal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author teras
 */
public class GNUPlotTest {

    public GNUPlotTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of set method, of class GNUPlot.
     */
    @Test
    public void performPlot() {
  //        FileDataSet q = null;
//        try {
//            q = new FileDataSet(new File(System.getProperty("user.home")+
//                    System.getProperty("file.separator")+"ko"));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
        
         
        ImageTerminal png = new ImageTerminal();
        PostscriptTerminal epsf = new PostscriptTerminal(System.getProperty("user.home")+
                System.getProperty("file.separator")+"output.eps");
        PostscriptTerminal eps =  new PostscriptTerminal();
        FileTerminal svg = new FileTerminal("svg", System.getProperty("user.home")+
                System.getProperty("file.separator")+"output.svg");
        
        JavaPlot p = new JavaPlot();
        JavaPlot.getDebugger().setLevel(Debug.VERBOSE);
        
        p.setTitle("Big Fat Title");
    //    p.getAxis("x").setLogScale(true);
        p.getAxis("x").setLabel("X axis", "Arial", 20);
        p.getAxis("y").setLabel("Y axis");
        
        p.getAxis("x").setBoundaries(-30, 20);
        //   p.getAxis("y").setBoundaries(1, 2);
        p.setKey(JavaPlot.Key.TOP_RIGHT);
        
        double [][] plot = { {1, 1.1 }, {2, 2.2}, {3, 3.3}, {4, 4.3} };
        DataSetPlot s = new DataSetPlot(plot);
       // s.setSmooth(Smooth.BEZIER);
        p.addPlot(s);
      //  p.addPlot(q);
        //   png.set("large");
        
        //   p.plot();
        //   p.setTerminal(png);
        eps.setColor(true);
        // eps.setEPS(false);
        //      p.setPointSize(4);
        //  p.getPreInit().add("plot x");
        p.addPlot("besj0(x)*0.12e1");
        // System.out.println(eps.getTextOutput());
        //     System.exit(0);
        
        PlotStyle stl = ((AbstractPlot)p.getPlots().get(1)).getPlotStyle();
        stl.setStyle(Style.POINTS);
        stl.setLineType(NamedPlotColor.GOLDENROD);
        stl.setPointType(5);
        stl.setPointSize(8);
        
        //p.setPersist(false);
        //p.setTerminal(eps);
        p.plot();
        
        
        
//        initComponents();
//        
//        SVGTerminal term = new SVGTerminal();
//        jp.setTerminal(term);
//        jp.plot();
//        try {
//            getContentPane().add(term.getPanel(1000, 800));
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//        
//        
////        JPlot p = new JPlot(jp);
////        getContentPane().add(p);
////        p.plot();
//        
//        pack();
//        setLocationRelativeTo(null);
//        setVisible(true);
        
        
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

   


}