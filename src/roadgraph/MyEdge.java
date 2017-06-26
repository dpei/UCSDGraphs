package roadgraph;



import roadgraph.MyVertice;
/** Not Used in MapGraph class yet
 * 
 * @created: June 2017
 * @author: Dong Pei
 * 
 * 
 * 
 */


public class MyEdge {
	
	private MyVertice start;
	private MyVertice end;
	private String name;
	
	
	public MyEdge(){
		start = new MyVertice(1,1);
		end = new MyVertice(2,2);
		name = "zhonghua";
	}	
	
	
	
	public String getName(){
		return name;
	}
	
}
