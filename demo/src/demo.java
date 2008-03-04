/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.iodebug.Debug;
import com.panayotis.gnuplot.plot.AbstractPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.NamedPlotColor;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;
import com.panayotis.gnuplot.terminal.ImageTerminal;
import com.panayotis.gnuplot.terminal.PostscriptTerminal;
import com.panayotis.gnuplot.terminal.SVGTerminal;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This Object is used to demonstrate JavaPlot library
 * @author teras
 */
public class demo {

    /**
     * @param args the command line arguments. First argument is the path of gnuplot application
     */
    public static void main(String[] args) {
        String path = null;
        if (args.length>0) path=args[0];
        
        //defaultTerminal(path);
        //EPSTerminal(path);
        //SVGTerminal(path);
        ImageTerminal(path);
    }

    /* This demo code uses default terminal. Use it as reference for other javaplot arguments  */
    private static void defaultTerminal(String gnuplotpath) {
        JavaPlot p = new JavaPlot(gnuplotpath);
        JavaPlot.getDebugger().setLevel(Debug.VERBOSE);

        p.setTitle("Default Terminal Title");
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
        p.plot();
    }

    /* This demo code creates a EPS file on home directory */
    private static void EPSTerminal(String gnuplotpath) {
        JavaPlot p = new JavaPlot();

        PostscriptTerminal epsf = new PostscriptTerminal(System.getProperty("user.home") +
                System.getProperty("file.separator") + "output.eps");
        epsf.setColor(true);
        p.setTerminal(epsf);

        p.setTitle("Postscript Terminal Title");
        p.addPlot("sin (x)");
        p.addPlot("sin(x)*cos(x)");
        p.plot();
    }

    /* This demo code displays plot on screen using image terminal */
    private static void ImageTerminal(String gnuplotpath) {
        JavaPlot p = new JavaPlot();
        final ImageTerminal img = new ImageTerminal();
        p.setTerminal(img);

        p.setTitle("Image Terminal Title");
        p.addPlot("sqrt(x)/x");
        p.addPlot("x*sin(x)");
        p.plot();

        JFrame f = new JFrame();
        JPanel panel = new JPanel() {

            Dimension dim = new Dimension(img.getImage().getWidth(), img.getImage().getHeight());

            public void paint(Graphics g) {
                g.drawImage(img.getImage(), 0, 0, null);
            }

            public Dimension getMinimumSize() {
                return dim;
            }

            public Dimension getPreferredSize() {
                return dim;
            }
        };
        f.getContentPane().add(panel);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }

    /* This demo code displays plot on screen using SVG commands (only b&w) */
    private static void SVGTerminal(String gnuplotpath) {
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
    }
}
