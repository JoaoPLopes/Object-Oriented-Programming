package simulator;

import java.util.LinkedList;
import java.util.List;


public class Path {
	
	private List<Integer> visitedNodes = new LinkedList<>();
	private List<Integer> path_weight= new LinkedList<>();
	private int currentNode;
	private int goingnext;
	private int associatedweight;
	private boolean a;
	 
	Path (int target_node){
		
		currentNode=target_node;
		visitedNodes.add(target_node);
		goingnext = currentNode;
	}
	
	
	public void addVisitedNode(int n) {
		visitedNodes.add(n);
	}
	
	public void setCurrentNode(int n) {
		currentNode = n;
	}
	
	
	public int getCurrentNode() {
		return currentNode;
	}
	
	public void addPathWeight(int weight) {
		path_weight.add(weight);
	}
	
	public List<Integer> getPathWeight() {
		return path_weight;
	}
	
	public List<Integer> getVisited(){
		return visitedNodes;
	}
	public int getGoingNext() {
		return goingnext;
	}
	public void setGoingNext(int _goingnext) {
		goingnext=_goingnext;
	}
	
	public int getAssociatedWeight() {
		return associatedweight;
	}
	
	public void setAssociatedWeight(int weight) {
		associatedweight= weight;
	}
	
	public boolean getBoolean() {
		return a;
	}
	public void setboolean(boolean _a ) {
		a=_a;
		
	}
	
	/**
	 * If there is a closed cycle inside the list containing the visited nodes this method removes
	 * all the nodes inside that cycle.
	 */
	public void removeCycle() {
		int current = getCurrentNode();
		int l = getVisited().size();
		getVisited().remove(l-1);
		getPathWeight().remove(l-2);
		l -=1;
		while (getVisited().get(l-1)!=current) {
			getVisited().remove(l-1);
			getPathWeight().remove(l-2);
			l-=1;
		}
	}


	@Override
	public String toString() {
		return "Path [visitedNodes=" + visitedNodes + ", path_weight=" + path_weight + ", currentNode=" + currentNode
				+ ", goingnext=" + goingnext + ", associatedweight=" + associatedweight + ", a=" + a + "]";
	}
	
	
	
	
}
