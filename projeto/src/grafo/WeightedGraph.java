package grafo;

import java.util.LinkedList;

import exceptions.NonPositive;

/**
 * 
 * Interface of a Weighted Graph. A weighted graph is a graph where its nodes are connected
 * through edges with a certain weight.
 * 
 * @author Joao Lopes
 *
 */

public interface WeightedGraph {
	
	
	/**
	 * Has as input two nodes and establish a edge between them with a given weight
	 * @param source is the source node
	 * @param target is the target node
	 * @param weight is the weight of the connection
	 * @throws NonPositive throws exception when the weigh in negative
	 */
	public void addEdge(int source, int target, int weight); 
	
	/**
	 * Return the number of the nodes in the graph
	 * @return the number of nodes
	 */
	public int getNNodes();
	
	/**
	 * Has as input two nodes and removes the edge between them 
	 * @param the first node
	 * @param the second node
	 */
	public void removeEdge (int p, int f);
	
	/**
	 * Has as input a node and returns his neighbors
	 * @param f the node
	 * @return the list of nodes with a connection to f
	 */
	
	public LinkedList<Edge> adjacentEdges(int f);
	
	public Edge getEdge(int s, int t);
	
	
}
