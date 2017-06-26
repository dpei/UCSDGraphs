package roadgraph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import geography.GeographicPoint;
import util.GraphLoader;

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
	
	//MapGraph simpleTestMap;
	
	
	@Before
	public void setUp() throws Exception 
	{
		
		//initiate a very simple map.
		firstMap = new MapGraph();
		point = new GeographicPoint(2.22, 6.55);
		point2 = new GeographicPoint(2.23, 6.45);
		point3 = new GeographicPoint(2.24, 6.45);
		point6 = new GeographicPoint(2.2, 6.45);
		firstMap.addVertex(point);
		firstMap.addVertex(point2);
		firstMap.addVertex(point3);
		firstMap.addVertex(point6);
		
		firstMap.addEdge(point, point2, "test name",
				"residential", 2);
		firstMap.addEdge(point2, point3, "test name",
				"residential", 2);
		firstMap.addEdge(point3, point, "test name",
				"residential", 2);
		firstMap.addEdge(point2, point6, "test name",
				"residential", 2);
		
		
		
		// initiate a test map prepared by course
		
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
		assertEquals("test edge number after add one edge", 4, firstMap.getNumEdges());
		assertTrue("test if edge is connected after add one edge", 
				firstMap.getNeighbourX(point).contains(2.23));
	}
	
	
	@Test
	public void testGetVertices(){
		assertEquals("test the total number of insections", 4, firstMap.getVertices().size());
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
		// Pass a regular case
		ArrayList<GeographicPoint> result1 = new ArrayList<GeographicPoint>();
		result1.add(point2);
		result1.add(point3);
		result1.add(point);
		assertEquals("a regular case", result1, firstMap.findPath(point2, point, test));
		
		// Pass a case where no path found.
		// No such case generated because the bfs() function can handle this situation
		
		// Pass a case where only one element
		ArrayList<GeographicPoint> result2 = new ArrayList<GeographicPoint>();
		result2.add(point2);
		assertEquals("a regular case", result2, firstMap.findPath(point2, point2, test));
		
		
	}
	
	
	
	@Test
	public void testBfs(){
		
		ArrayList<GeographicPoint> result1 = new ArrayList<GeographicPoint>();
		result1.add(point2);
		result1.add(point3);
		result1.add(point);
		assertEquals("A simple example", result1, firstMap.bfs(point2, point));
		assertEquals("return no path found", null, firstMap.bfs(point6, point2));
		
			
		ArrayList<GeographicPoint> result2 = new ArrayList<GeographicPoint>();
		result2.add(point2);
		assertEquals("return one step", result2, firstMap.bfs(point2, point2));
		
		
		
		//point2 = new GeographicPoint(2.23, 6.45);
		//point = new GeographicPoint(2.22, 6.55);
		
		GeographicPoint testStart = new GeographicPoint(2.23, 6.45);
		GeographicPoint testEnd = new GeographicPoint(2.22, 6.55);
		
		System.out.println("new point: "+ firstMap.bfs(testStart,testEnd));
		System.out.println("old point: "+ firstMap.bfs(point2, point));
		
		// System.out.println(firstMap.bfs(point6, point2));
		
		/*
		MapGraph simpleTestMap = new MapGraph();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
		
		System.out.println(simpleTestMap);
		GeographicPoint testStart = new GeographicPoint(4.0, 1.0);
		GeographicPoint testEnd = new GeographicPoint(5.0, 1.0);
		List<GeographicPoint> testroute = simpleTestMap.bfs(testStart,testEnd);
		System.out.println(testroute);
		*/
		//System.out.println(firstMap.getNeighbourX(point2));
		//System.out.println(simpleTestMap.getNeighbourX(testEnd));
		
	}
	
}
