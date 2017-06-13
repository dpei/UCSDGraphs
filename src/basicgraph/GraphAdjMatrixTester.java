package basicgraph;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import util.GraphLoader;

/**
 * @author Dong Pei
 * @created on June 2017
 *
 */


public class GraphAdjMatrixTester {
	GraphAdjMatrix graphFromFile3;
	GraphAdjList graphFromFile;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		
		// initiate GraphAdjMatrix class object
		graphFromFile3 = new GraphAdjMatrix();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", graphFromFile3);;
		
		
		// initiate GraphAdjList class object
		graphFromFile = new GraphAdjList();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", graphFromFile);
	}
	
	
	@Test
	public void testMultiply()
	{
		ArrayList listAdj = (ArrayList) graphFromFile.getDistance2(3);
		ArrayList listMatrix = (ArrayList) graphFromFile3.getDistance2(3);
		assertThat(listAdj, is(listMatrix));
	}
	
	
	
}
