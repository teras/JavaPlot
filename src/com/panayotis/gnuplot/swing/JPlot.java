/*
 * JPlot.java
 *
 * Created on October 24, 2007, 7:53 PM
 */

package com.panayotis.gnuplot.swing;

import com.panayotis.gnuplot.GNUPlot;
import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.terminal.ImageTerminal;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author  teras
 */
public class JPlot extends JPanel {
    
    private JavaPlot plot;
    private ImageTerminal term;
    
    /** Creates new form JPlot */
    public JPlot() {
        this(new JavaPlot());
    }
    
    public JPlot(JavaPlot plot) {
        initComponents();
        term = new ImageTerminal();
        setJavaPlot(plot);
    }

    public void setJavaPlot(JavaPlot javaplot) {
        plot = javaplot;
        plot.setTerminal(term);
    }
    public JavaPlot getJavaPlot() {
        return plot;
    }
    
    public void plot() {
        if (plot==null) return;
        plot.plot();
        BufferedImage img = term.getImage();
        setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
    }
    
    public void paint(Graphics g) {
        BufferedImage img = term.getImage();
        if (img==null) return;
        g.drawImage(img, 0, 0, null);
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}