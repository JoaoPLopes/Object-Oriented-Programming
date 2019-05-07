package simulator;

import java.util.Collection;

/**
 * Interface of a graph path. 
 * Represents a group of nodes known as path.
 * A path stores a set of graph nodes and their weights. A path also stores the current node.
 * 
 * 
 * @author Joao Lopes, Gonçalo Carvalho, Alessio Vacca
 *
 */

public interface TraverserPath {

	/**
	 * This method gives a collection of visited nodes by the traverser
	 * @return a collection containing all the visited nodes by the traverser
	 */
	public Collection<?> getVisited();
	
	/**
	 * This method gives the Traverser current node
	 * @return the current node where the traverser is
	 */
	public int getCurrentNode();
	
	/**
	 * This method gives a Collection with all the weights of the visited nodes
	 * @return a Collection of integers containing the weights of all the visited nodes
	 */
	public Collection<?> getPathWeights();
	
	/**
	 * This method adds a node to the set of visited nodes in the path
	 * @param n the node to add to the path
	 */
	public void addVisitedNode(int n);
	
	/**
	 * This method adds a weight to the set of weights that corresponds to the nodes in the path
	 * @param weight the edge weight to add to the path
	 */
	public void addPathWeight(int weight);
	
	/**
	 * This method set as current node a give node
	 * @param n the node to set as current
	 */
	public void setCurrentNode(int n);

}
