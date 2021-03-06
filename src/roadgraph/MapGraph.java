/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.function.Consumer;

import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and YOU
 * @modified by Dong Pei
 * @modified on June 2017
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraph {
	//TODO: Add your member variables here in WEEK 3
	private int numVertices;
	private int numEdges;
	private HashMap<GeographicPoint, ArrayList<GeographicPoint>> adjListsMap;
	private HashSet<MyEdge> edges;
	
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		numVertices = 0;
		numEdges = 0;
		adjListsMap = new HashMap<GeographicPoint, ArrayList<GeographicPoint>>();
		edges = new HashSet<MyEdge>();
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		return numVertices;
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		return adjListsMap.keySet();
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		return numEdges;
	}

	
	
	/** Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	//public boolean addVertex(GeographicPoint location)
	public boolean addVertex(GeographicPoint location)
	{		
		if (location == null | adjListsMap.containsKey(location)){
			return false;
		} else {
			adjListsMap.put(location, new ArrayList<>());
			numVertices ++;
			return true;
		}
	}
	
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {
		
		// throw exceptions if input is not good
		if (length < 0 | !adjListsMap.containsKey(from) | !adjListsMap.containsKey(to) |
				from == null | to == null | roadName == null | roadType == null){
			throw new IllegalArgumentException();
		} else {
			adjListsMap.get(from).add(to);
			numEdges ++;
			edges.add(new MyEdge(from, to, roadName, length));
		}
	}
	

	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
        
	}
	
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions 
	 *   for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	//
	//
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal,
			 					    Consumer<GeographicPoint> nodeSearched)
	{
	
		// Hook for visualization.  See writeup.
		
		
		
		// Initiate a list of "visited" nodes 
		// Initiate a list of "queue" that has all current nodes to be visited
		// Initiate a list of "parent" HashMap
		HashSet<GeographicPoint> visited = new HashSet<GeographicPoint>();
		LinkedList<GeographicPoint> queue = new LinkedList<GeographicPoint>();
		HashMap<GeographicPoint, GeographicPoint> parent = 
				new HashMap<GeographicPoint, GeographicPoint>();
		
		
		
		
		// Put start into queue 
		// Put start into visited
		queue.add(start);
		visited.add(start);
		GeographicPoint curr = null;
		
		// While you queue list is not empty perform the following 
		//    Curr = first node (geographic point) in current queue
		//    If curr == G then return parent HashMap else:
		//      For each of curr's neighbors, n, not in visited set:
		//        Add n to visited set
		//        Enqueue n onto the queue
		//        Add curr as n's parent in parent map
		while(!queue.isEmpty()){
			curr = queue.remove(0);
			nodeSearched.accept(curr);
			//System.out.println("Look at curr"+curr);
			
			if (curr.getX() == goal.getX() & curr.getY() == goal.getY()){
				return findPath(start, goal, parent);
				
				//System.out.println("Start is: "+start+" goal is: "+ goal +findPath(start, goal, parent));

			} else {
				for (GeographicPoint neighbour:adjListsMap.get(curr)){
					if (!visited.contains(neighbour)){
						visited.add(neighbour);
						queue.add(neighbour);
						parent.put(neighbour, curr);
					}
				}
			}
		}
		/*
		for (GeographicPoint key:parent.keySet()){
			System.out.println(key+" parent: "+parent.get(key));
			
		}
		*/
		return null;
		//System.out.println("naaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
	}
	
	
	
	/** Given a HashMap contains each node and its parent. This HashMap
	 *  was generated using breath first search. 
	 *  The goal is to find the path from start to goal 
	 *  Input: HashMap
	 *  Outout: a list from start to goal 
	 * 
	 */
	
	private List<GeographicPoint> findPath(GeographicPoint start, GeographicPoint goal, 
										  HashMap<GeographicPoint,GeographicPoint> parent){
		// Initiate variables
		List<GeographicPoint> retList = new ArrayList<GeographicPoint>();
		GeographicPoint curr =  goal;
		retList.add(goal);
		
		// Find a path from goal to start
		while (!curr.equals(start)){
			curr = parent.get(curr);
			retList.add(curr);
		}
		Collections.reverse(retList);
		return retList;
	}
	
	
	
	/** Given a start and end GeographicPoint object, return the distance between the two
	 *  If there is no edge, return null. This method takes advantage of the edge HashSet<MyEdge> 
	 *  object
	 *  Input: start and end GeographicPoint object
	 *  Outout: distance from the "start" object to the "end" object.  
	 *  Change to private after testing
	 */
	private double getEdgeDistance(GeographicPoint start, GeographicPoint goal){
		for (MyEdge edge : edges){
			if (edge.getStart().equals(start) & edge.getEnd().equals(goal)){
				return edge.getLength();
			}
		}
		return -1.0;
	}
	
	

	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstra(start, goal, temp);
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, 
										  Consumer<GeographicPoint> nodeSearched)
	{
		PriorityQueue<MyVertice> queue = new PriorityQueue<MyVertice>();
		HashSet<GeographicPoint> visited = new HashSet<GeographicPoint>();
		HashMap<GeographicPoint, GeographicPoint> parent = 
				new HashMap<GeographicPoint, GeographicPoint>();
		
		// Initiate all vertices so that they all have a positive infinity length to start
		HashSet<MyVertice> vertices = new HashSet<MyVertice>(); 
		for (GeographicPoint point : adjListsMap.keySet()){
			MyVertice vt = new MyVertice(point.getX(), point.getY());
			
			// Actually this should be the distance to start. I use distance to sum
			// because A* method need to compare sum. So all distance to start in this
			// method is not set.
			vt.setDistanceSum(Double.POSITIVE_INFINITY);
			vertices.add(vt);
		}
		
		// add start into queue
		MyVertice myStart = getVertice(start, vertices);
		myStart.setDistanceSum(0);
		queue.add(myStart);
		
		System.out.println("dijkstra visited the following nodes: ");
		//int i=0;
		
		
		
		while(!queue.isEmpty()){
			
			//System.out.println("This is loop: "+i+". current queue element: "+ queue.peek()+" , quene size is"+ queue.size());
			//i++;
			MyVertice myCurr = queue.poll();
			GeographicPoint curr = new GeographicPoint(myCurr.getX(), myCurr.getY());
			
			// count visited nodes
			System.out.println(curr);
			nodeSearched.accept(curr);
			
			
			if (!visited.contains(curr)){
				visited.add(curr);
				if (curr.equals(goal)){
					//System.out.println();
					//System.out.println("Find goal Final parent map: ");
					//for (GeographicPoint p:parent.keySet()){
					//	System.out.println(parent.get(p)+" to "+p);
					//}
					
					return findPath(start, goal, parent);
				} else {
					for (GeographicPoint neighbour:adjListsMap.get(curr)){
						if (!visited.contains(neighbour)){
							double edgeDistance =  getEdgeDistance(curr, neighbour);
							// make sure the distance is larger than 0
							if (edgeDistance > 0){
								double neighbourDistance = myCurr.getDistanceSum() + edgeDistance;
								
								// find the MyVertice node that has the same value as "neighbour"  
								MyVertice curNei = getVertice(neighbour, vertices);
								double curNeiDist = curNei.getDistanceSum();
								
								if(neighbourDistance < curNeiDist){
									parent.put(neighbour, curr);
									curNei.setDistanceSum(neighbourDistance);
									queue.add(curNei);
								}
							}
						}
					}
					/*
					System.out.println();
					System.out.println("After one more loop, The HashMap is: ");
					System.out.println(parent.size());
					for (GeographicPoint p:parent.keySet()){
						System.out.println(parent.get(p)+" to "+p);
					}
					System.out.println("The queue is: ");
					System.out.println(queue.size());
					System.out.println(queue);
					*/
				}
			}
		}
		return null;
	}
		
		
	
	/**
	 * helper method to find the right MyVertice based on GeographicPoint object. This method is used for
	 * @param input is a GeographicPoint 
	 * @param output is a MyVertice object that has same value of x and y as input GeographicPoint
	 * @return the right MyVertice object
	 */
	
	public MyVertice getVertice(GeographicPoint input, HashSet<MyVertice> vertices){
		for (MyVertice vt: vertices){
			if (vt.getX() == input.getX() & vt.getY() == input.getY()){
				return vt;
			}
		}
		return null;
	}
	
	

	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, 
											 Consumer<GeographicPoint> nodeSearched)
	{
		PriorityQueue<MyVertice> queue = new PriorityQueue<MyVertice>();
		HashSet<GeographicPoint> visited = new HashSet<GeographicPoint>();
		HashMap<GeographicPoint, GeographicPoint> parent = 
				new HashMap<GeographicPoint, GeographicPoint>();
		
		HashSet<MyVertice> vertices = new HashSet<MyVertice>(); 
		for (GeographicPoint point : adjListsMap.keySet()){
			MyVertice vt = new MyVertice(point.getX(), point.getY());
			vt.setDistanceToStart(Double.POSITIVE_INFINITY);
			// in A* algorithm, setDistanceSum sets f(n)=g(n)+h(n).			
			vt.setDistanceSum(Double.POSITIVE_INFINITY);
			vertices.add(vt);
		}
		
		MyVertice myStart = getVertice(start, vertices);
		// in A* algorithm. setDistanceToStart() sets f(n)=g(n)+h(n)
		myStart.setDistanceToStart(0);
		myStart.setDistanceSum(start.distance(goal));
		queue.add(myStart);
		
		System.out.println("A* visited the following nodes: ");
		
		
		while(!queue.isEmpty()){
			
			MyVertice myCurr = queue.poll();
			GeographicPoint curr = new GeographicPoint(myCurr.getX(), myCurr.getY());
			
			// count visited nodes
			System.out.println(curr);
			nodeSearched.accept(curr);
			
			
			if (!visited.contains(curr)){
				visited.add(curr);
				if (curr.equals(goal)){
					return findPath(start, goal, parent);
				} else {
					for (GeographicPoint neighbour:adjListsMap.get(curr)){
						if (!visited.contains(neighbour)){
							double neiEdgeDistance = getEdgeDistance(curr, neighbour);
							double neiGoalDistance = neighbour.distance(goal);
							
							if (neiEdgeDistance > 0){
								double neiToStartDistance = myCurr.getDistanceToStart() + neiEdgeDistance;
								double neiSumDistance = neiToStartDistance + neiGoalDistance; 
								MyVertice curNei = getVertice(neighbour, vertices);
								
								if(neiSumDistance < curNei.getDistanceSum()){
									parent.put(neighbour, curr);
									curNei.setDistanceToStart(neiToStartDistance);
									curNei.setDistanceSum(neiSumDistance);
									queue.add(curNei);
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	// a helper method for test only
	public String toString() {
		String s = "\nGraph with " + numVertices + " vertices and " + numEdges + " edges.\n";
		return s;
	}
	
		
	// for test only
	public ArrayList<Double> getNeighbourX(GeographicPoint location){
		ArrayList<Double> number = new ArrayList<Double>();
		for (GeographicPoint lc : adjListsMap.get(location)){
			number.add(lc.getX()); 
		}
		return number;
	}
	
	
	
	public static void main(String[] args)
	{
		//You can use this method for testing.  
		
		
		/* Here are some test cases you should try before you attempt 
		 * the Week 3 End of Week Quiz, EVEN IF you score 100% on the 
		 * programming assignment.
		 */
		/*
		MapGraph simpleTestMap = new MapGraph();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
		
		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
		
		System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
		List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
		List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,testEnd);
		
		
		MapGraph testMap = new MapGraph();
		GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
		
		// A very simple test using real data
		testStart = new GeographicPoint(32.869423, -117.220917);
		testEnd = new GeographicPoint(32.869255, -117.216927);
		System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		
		
		// A slightly more complex test using real data
		testStart = new GeographicPoint(32.8674388, -117.2190213);
		testEnd = new GeographicPoint(32.8697828, -117.2244506);
		System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		*/
		
		
		/* Use this code in Week 3 End of Week Quiz */
		/*MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		
		
		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

		*/
		
	}
	
}
