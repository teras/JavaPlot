/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.panayotis.gnuplot.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 *
 * @author teras
 */
public class FileUtils {

    /**
     * This method browses current path to search for a file. Typically this
     * should be an executable, but it is impossible under Java to check if this
     * file has the execution bit on. Apart from the user defined $PATH
     * variable, common bin-directory places are searched.
     *
     * @return The path of the specified program. If it is not found, "gnuplot"
     * is returned.
     */
    public static String findPathExec() {
        /* Check for default locations */
        for (String path : new String[]{
            "/bin/gnuplot", "/usr/bin/gnuplot", "/usr/local/bin/gnuplot",
            "/sbin/gnuplot", "/usr/sbin/gnuplot", "/usr/local/sbin/gnuplot",
            "/opt/bin/gnuplot", "/opt/local/bin/gnuplot",
            "/opt/sbin/gnuplot", "/opt/local/sbin/gnuplot",
            "/sw/bin/gnuplot",
            "C:\\Program Files (x86)\\gnuplot\\bin\\wgnuplot.exe",
            "C:\\Program Files\\gnuplot\\bin\\wgnuplot.exe",
            "c:\\cygwin\\bin\\gnuplot.exe"})
            if (new File(path).isFile())
                return path;

        /* Check for PATH-based locations */
        StringTokenizer st = new StringTokenizer(System.getenv("PATH"), File.pathSeparator);
        String dir, file;
        while (st.hasMoreTokens()) {
            dir = st.nextToken();
            file = dir + File.separator + "gnuplot";
            if (new File(file).isFile())
                return file;
            file = dir + File.separator + "wgnuplot.exe";
            if (new File(file).isFile())
                return file;
            file = dir + File.separator + "gnuplot.exe";
            if (new File(file).isFile())
                return file;
        }
        return "gnuplot";
    }

    public static String createTempFile(String contents) throws IOException {
        File file = File.createTempFile("gnuplot_", ".dat");
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.append(contents);
        out.close();
        return file.getAbsolutePath();
    }
}
