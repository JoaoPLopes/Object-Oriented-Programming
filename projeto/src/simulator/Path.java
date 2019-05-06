package simulator;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import exceptions.NonPositive;


public class Path implements TraverserPath {
	
	private List<Integer> visitedNodes = new LinkedList<>();
	private List<Integer> path_weight= new LinkedList<>();
	private int currentNode;
	 
	Path (int target_node){
		
		currentNode = target_node;
		visitedNodes.add(target_node);
	}
	
	
	public List<Integer> getVisited(){
		return visitedNodes;
	}
	
	public int getCurrentNode() {
		return currentNode;
	}
	
	@Override
	public List<Integer> getPathWeights() {
		// TODO Auto-generated method stub
		return path_weight;
	}


	@Override
	public int getTotalPathWeight() throws NonPositive{
		// TODO Auto-generated method stub
		int sum = 0;
		for(Integer path_temp : path_weight) {
			sum+= path_temp;
		}
		if(sum < 0)
			throw new NonPositive("A path weight is Negative, something is wrong"); 
		else
			return sum;
	}
	
	public void addVisitedNode(int n) {
		visitedNodes.add(n);
	}
	
	
	
	public void addPathWeight(int weight) {
		path_weight.add(weight);
	}
	
	public void setCurrentNode(int n) {
		currentNode = n;
	}
	
	
	public boolean hasHamiltonCycle() {
		int nbnodes = ColonySimulator.dados.getNbNodes()+1;
		if ((this.getVisited().size()==nbnodes) && this.getVisited().get(0)==this.getVisited().get(nbnodes-1)) {
			return true; 
		}else
			return false;
	}
	
	
	/**
	 * If there is a closed cycle inside the list containing the visited nodes this method removes
	 * all the nodes inside that cycle.
	 */
	public void removeCycle() {
		if(this.hasDuplicate()) {
			int current = getCurrentNode();
			int l = getVisited().size();
			getVisited().remove(l-1);
			getPathWeights().remove(l-2);
			l -=1;
			while (getVisited().get(l-1)!=current) {
				getVisited().remove(l-1);
				getPathWeights().remove(l-2);
				l-=1;
			}
		}
	}
	
	public boolean hasDuplicate() {
		  Set<Integer> appeared = new HashSet<>();
		  for (int item : this.visitedNodes) {
		    if (!appeared.add(item)) {
		      return true;
		    }
		  }
		  return false;
		}


	@Override
	public String toString() {
		return "Path [visitedNodes=" + visitedNodes + ", path_weight=" + path_weight + ", currentNode=" + currentNode
				+  "]";
	}

	
	
}
