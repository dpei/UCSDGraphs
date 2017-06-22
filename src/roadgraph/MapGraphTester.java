package roadgraph;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import geography.GeographicPoint;

/**
 * @author Dong Pei
 * 
 * @created June 2017
 * A unit tester class to test methods in MapGraph()  
 */




public class MapGraphTester {
	
	MapGraph firstMap;
	GeographicPoint point;
	GeographicPoint point2;
	
	@Before
	public void setUp() throws Exception 
	{
		
		//initiate a simple map.
		firstMap = new MapGraph();
		point = new GeographicPoint(2.22, 6.55);
		point2 = new GeographicPoint(2.23, 6.45);
		firstMap.addVertex(point);
		firstMap.addVertex(point2);
		firstMap.addEdge(point, point2, "test name",
				"residential", 2);
		
		
	}
	
	
	
	@Test
	public void testAddVertex()
	{
		GeographicPoint point3 = null;
		GeographicPoint point4 = new GeographicPoint(2.22, 6.55);
		assertEquals("add null geographic point", false, firstMap.addVertex(point3));
		assertEquals("add repeat point", false, firstMap.addVertex(point4));
		//System.out.println(firstMap);
		//firstMap.printVertex();
		
	}
	
	
	
	
	// Test exceptions when distance is -2 or when something is null
		@Test(expected = IllegalArgumentException.class)
		public void testAddEdgesExp() {
			firstMap.addEdge(point, point2, "test name",
					"residential", -2);
		}
		@Test(expected = IllegalArgumentException.class)
		public void testAddEdgesExp3() {
			firstMap.addEdge(null, point2, "test name",
					"residential", 2);
		}
	
	@Test
	public void testAddEdges()
	{
		
		assertEquals("test edge number after add one edge", 1, firstMap.getNumEdges());
		assertEquals("test if edge is connected after add one edge", point, firstMap.
	}
	
	
	
	
}
