package com.panayotis.gnuplot;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for com.panayotis.gnuplot");
		//$JUnit-BEGIN$
		suite.addTestSuite(DataSetPlotTest.class);
		suite.addTestSuite(FunctionPlotTest.class);
		//$JUnit-END$
		return suite;
	}

	
	

	
    public static void main(String[] args) {
    	junit.textui.TestRunner.run(suite());
    }

}
