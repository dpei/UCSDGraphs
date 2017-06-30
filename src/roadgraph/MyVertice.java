package roadgraph;


/**A Vertice class used for MapGraph. This class is used for 
 * 
 * 
 * 
 * @created: June 2017
 * @author: Dong Pei
 *
 */

import geography.GeographicPoint;



import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;


public class MyVertice extends GeographicPoint implements Comparable<MyVertice>{
	
	// this variable is used to store the real distance between 
	private double distanceToStart;
	// this variable is used to store the heuristic estimated cost distance 
	// (i.e. the conserved estimation, or say the straight line distance between two point)
	private double distanceSum;
	
	public MyVertice(double X, double Y){
		super(X, Y);
	}
	
	public void setDistanceToStart(double distance){
		distanceToStart = distance;
	}
	
	
	
	public double getDistanceToStart(){
		return distanceToStart;
	}

	
	public void setDistanceSum(double distance){
		distanceSum = distance;
	}
	public double getDistanceSum(){
		return distanceSum;
	}
	
	
	@Override
	public int compareTo(MyVertice y){
		double dist = this.getDistanceSum() - y.getDistanceSum();
		if (dist>0){
			return 1;
		} else if(dist==0) {
			return 0;
		} else {
			return -1;
		}
	}
	
	
	public String toString()
    {
    	return "Lat: " + getX() + ", Lon: " + getY() + ", The distance to start: "+ distanceToStart;
    }
	
	
	// test use only
	public static void main(String[] args)
	{
		
		MyVertice test1 = new MyVertice(1.1, 2.2);
		test1.setDistanceToStart(0.1);
		MyVertice test2 = new MyVertice(1.2, 2.2);
		test2.setDistanceToStart(0.2);
		MyVertice test3 = new MyVertice(1.3, 2.2);
		test3.setDistanceToStart(0.3);
		MyVertice test4 = new MyVertice(1.4, 2.2);
		test4.setDistanceToStart(0.4);
		MyVertice test5 = new MyVertice(1.5, 2.2);
		test5.setDistanceToStart(0.5);
		
		PriorityQueue<MyVertice> al=new PriorityQueue<MyVertice>();  
		al.add(test1);
		al.add(test4);
		al.add(test3);
		al.add(test2);
		al.add(test5);
		int number = al.size();
		for (int i=0; i<number; i++){
			System.out.println(al.poll());
		}
		
		/*
		System.out.println("After sorting: ");
		Collections.sort(al);
		for (int i=0; i<al.size(); i++){
			System.out.println(al.get(i).getX());
		}
		*/
	}


	
}