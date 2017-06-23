package roadgraph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import geography.GeographicPoint;

/**A unit tester class to test methods in MapGraph()
 * @author Dong Pei
 * 
 * @created June 2017
 * @last modified June 2017
 */




public class MapGraphTester {
	
	MapGraph firstMap;
	GeographicPoint point;
	GeographicPoint point2;
	GeographicPoint point3;
	GeographicPoint point6;
	
	
	@Before
	public void setUp() throws Exception 
	{
		
		//initiate a simple map.
		firstMap = new MapGraph();
		point = new GeographicPoint(2.22, 6.55);
		point2 = new GeographicPoint(2.23, 6.45);
		point3 = new GeographicPoint(2.24, 6.45);
		point6 = new GeographicPoint(2.2, 6.45);
		firstMap.addVertex(point);
		firstMap.addVertex(point2);
		firstMap.addVertex(point3);
		firstMap.addEdge(point, point2, "test name",
				"residential", 2);
		
		
	}
	
	
	
	@Test
	public void testAddVertex()
	{
		GeographicPoint point4 = null;
		GeographicPoint point5 = new GeographicPoint(2.22, 6.55);
		assertEquals("add null geographic point", false, firstMap.addVertex(point4));
		assertEquals("add repeat point", false, firstMap.addVertex(point5));
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
		assertTrue("test if edge is connected after add one edge", 
				firstMap.getNeighbourX(point).contains(2.23));
	}
	
	
	@Test
	public void testGetVertices(){
		assertEquals("test the total number of insections", 3, firstMap.getVertices().size());
		assertTrue("test the total number of insections", firstMap.getVertices().contains(point));
		assertTrue("test the total number of insections", firstMap.getVertices().contains(point3));
		assertTrue("test the total number of insections", firstMap.getVertices().contains(point2));
	}
	
	@Test
	public void testFindPath(){
		HashMap<GeographicPoint, GeographicPoint> test = new HashMap<GeographicPoint, GeographicPoint>();
		test.put(point, point3);
		test.put(point3, point2);
		test.put(point2, point6);
		firstMap.findPath(point, point2, test);
		//System.out.println(firstMap.findPath(point, point, test));
	}
	
	
	
}
