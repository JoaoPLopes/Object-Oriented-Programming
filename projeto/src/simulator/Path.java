package simulator;

import java.util.LinkedList;
import java.util.List;


public class Path {
	
	private List<Integer> visitedNodes = new LinkedList<>();
	private List<Integer> path_weight= new LinkedList<>();
	private int currentNode;
	
	Path (int target_node){
		
		currentNode=target_node;
		visitedNodes.add(target_node);
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
	
	
	
	
}
