package simulator;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import exceptions.NonPositive;

/**
 * 
 * Class: Path.java
 * This class implements the TraverserPath interface.
 * A Path contains a two list of integers. One containing all the visited nodes an the other the correspondent weights.
 * A Path also stores an integer representing the current node 
 * 
 * @author Joao Lopes, Gonçalo Carvalho, Alessio Vacca
 *
 */
public class Path implements TraverserPath {
	
	private List<Integer> visitedNodes = new LinkedList<>();
	private List<Integer> path_weight= new LinkedList<>();
	private int currentNode;
	 
	
	/**
	 * Constructs an object Path
	 * @param node is the node where the path will begin
	 */
	Path (int node){
		
		currentNode = node;
		visitedNodes.add(node);
	}
	
	/**
	 * This method gives a list of visited nodes by the traverser
	 * @return a collection list all the visited nodes by the traverser
	 */
	public List<Integer> getVisited(){
		return visitedNodes;
	}
	
	/**
	 * This method gives the Traverser current node
	 * @return the current node where the traverser is
	 */
	public int getCurrentNode() {
		return currentNode;
	}
	
	/**
	 * This method gives a list with all the weights of the visited nodes
	 * @return a list of integers containing the weights of all the visited nodes
	 */
	@Override
	public List<Integer> getPathWeights() {
		return path_weight;
	}

	/**
	 * This method gives the total path weight
	 * @return the total weight of the path
	 * @throws NonPositive if the weight has a negative value
	 */
	public int getTotalPathWeight() throws NonPositive{
		int sum = 0;
		for(Integer path_temp : path_weight) {
			sum+= path_temp;
		}
		if(sum < 0)
			throw new NonPositive("A path weight is Negative, something is wrong"); 
		else
			return sum;
	}
	
	/**
	 * This method adds a node to the set of visited nodes in the path
	 * @param n the node to add to the path
	 */
	public void addVisitedNode(int n) {
		visitedNodes.add(n);
	}
	
	
	/**
	 * This method adds a weight to the set of weights that corresponds to the nodes in the path
	 * @param weight the edge weight to add to the path
	 */
	public void addPathWeight(int weight) {
		path_weight.add(weight);
	}
	
	/**
	 * This method set as current node a give node
	 * @param n the node to set as current
	 */
	public void setCurrentNode(int n) {
		currentNode = n;
	}
	
	/**
	 * This method is responsible to check if there is an hamiltonian cycle inside the path
	 * @return true if there is an hamiltonian cycle inside the path
	 */
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
	
	 /**
	  * Method that checks if there are repeated nodes inside the path visited nodes
	  * @return true is the path contains repeated nodes 
	  */
	public boolean hasDuplicate() {
		  Set<Integer> appeared = new HashSet<>();
		  for (int item : getVisited()) {
		    if (!appeared.add(item)) {
		      return true;
		    }
		  }
		  return false;
		}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Path [visitedNodes=" + visitedNodes + ", path_weight=" + path_weight + ", currentNode=" + currentNode
				+  "]";
	}

	
	
}
