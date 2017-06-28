package roadgraph;



import geography.GeographicPoint;
import roadgraph.MyVertice;
/** Used in MapGraph class yet
 * 
 * @created: June 2017
 * @author: Dong Pei
 * 
 * 
 * 
 */


public class MyEdge {
	
	private GeographicPoint start;
	private GeographicPoint end;
	private String name;
	private double length;
	
	
	public MyEdge(GeographicPoint S, GeographicPoint E, String N, double L){
		start = S;
		end = E;
		name = N;
		length = L;
	}	
	

	public double getLength(){
		return length;
	}
	
	public GeographicPoint getStart(){
		return start;
	}
	
	public GeographicPoint getEnd(){
		return end;
	}
}
