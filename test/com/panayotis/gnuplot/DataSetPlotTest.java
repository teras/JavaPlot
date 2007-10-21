package com.panayotis.gnuplot;

import com.panayotis.gnuplot.plot.DataSetPlot;
import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataSetPlotTest extends TestCase {

	private double [][] plot = { {1,1}, {2,2}, {3,3}, {4,4} };
	private DataSetPlot dp = new DataSetPlot(plot);
	
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		System.out.println("setUpBeforeClass");
//	}
//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//		System.out.println("tearDownAfterClass");
//	}

//	@Before
//	public void setUp() throws Exception {
//		System.out.println("setUp");
//	}
//
//	@After
//	public void tearDown() throws Exception {
//		System.out.println("tearDown");
//	}
//
	@Test
	public void testGetDescription() {
		System.out.println("testGetDescription");
		
		
		String okStr = "1.0 1.0 \n"
		+ "2.0 2.0 \n" 
		+ "3.0 3.0 \n" 
		+ "4.0 4.0 \n" 
		+ "e\n";
		
///		System.out.println(dp.getData());
	
		Assert.assertEquals( okStr ,dp.getData());
	}

	@Test
	public void testGetData() {
		System.out.println("testGetData");
		
		String okStr = "'-'";
		
		//System.out.println(dp.getDescription());
	
		Assert.assertEquals( okStr ,dp.getPlotDefinition());
	}

}
