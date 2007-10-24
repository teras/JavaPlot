/*
 * demo.java
 *
 * Created on October 23, 2007, 4:51 PM
 */

import com.panayotis.debug.Debug;
import com.panayotis.gnuplot.*;
import com.panayotis.gnuplot.dataset.FileDataSet;
import com.panayotis.gnuplot.swing.JPlot;
import com.panayotis.gnuplot.terminal.FileTerminal;
import com.panayotis.gnuplot.terminal.ImageTerminal;
import com.panayotis.gnuplot.terminal.PostscriptTerminal;
import com.panayotis.gnuplot.terminal.SVGTerminal;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author  teras
 */
public class demo extends javax.swing.JFrame {
    
    public static void main(String [] args) {
        
        FileDataSet q = null;
        try {
            q = new FileDataSet(new File(System.getProperty("user.home")+
                    System.getProperty("file.separator")+"ko"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        String gnuplotpath = null;
        if (args.length>0) gnuplotpath = args[0];
        
        ImageTerminal png = new ImageTerminal();
        PostscriptTerminal epsf = new PostscriptTerminal(System.getProperty("user.home")+
                System.getProperty("file.separator")+"output.eps");
        PostscriptTerminal eps =  new PostscriptTerminal();
        FileTerminal svg = new FileTerminal("svg", System.getProperty("user.home")+
                System.getProperty("file.separator")+"output.svg");
        
        JavaPlot p = new JavaPlot(gnuplotpath);
        p.getDebugger().setLevel(Debug.VERBOSE);
        
        p.setTitle("Big Fat Title");
    //    p.getAxis("x").setLogScale(true);
        p.getAxis("x").setLabel("X axis", "Arial", 20);
        p.getAxis("y").setLabel("Y axis");
        
        p.getAxis("x").setBoundaries(-30, 20);
        //   p.getAxis("y").setBoundaries(1, 2);
        p.setKey(JavaPlot.Key.TOP_RIGHT);
        
        double [][] plot = { {1, 1.1}, {2, 2.2}, {3, 3.3}, {4, 4.3} };
        p.addPlot(plot);
        
        p.addPlot(q);
        //   png.set("large");
        
        //   p.plot();
        //   p.setTerminal(png);
        eps.setColor(true);
        eps.setEPS(false);
        //      p.setPointSize(4);
        //  p.getPreInit().add("plot x");
        p.addPlot("besj0(x)*0.12e1");
        // System.out.println(eps.getTextOutput());
        //     System.exit(0);
        

        new demo(p);
    }
    
    
    /**
     * Creates new form test
     */
    public demo(JavaPlot jp) {
        initComponents();
        
        SVGTerminal term = new SVGTerminal();
        jp.setTerminal(term);
        jp.plot();
        getContentPane().add(term.getPanel(1000, 800));
        
        
//        JPlot p = new JPlot(jp);
//        getContentPane().add(p);
//        p.plot();
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}