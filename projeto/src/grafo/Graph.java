package grafo;

import java.util.ArrayList;
import java.util.LinkedList;

import exceptions.NonPositive;
import exceptions.WrongXLMvalue;

/**
 * Class: Graph.java
 * 
 * Graph implements the interface WeightedGraph
 * 
 * This Class contains two fields. An integer describing the size of the graph (i.e number of nodes)
 * and an ArrayList of LinkedLists that stores the edges connecting nodes.
 * The indexes of the ArrayList represents the nodes of the graph. In the LinkedLists the edges
 * are stored.
 * 
 * @author Joao Lopes
 *
 */

public class Graph implements WeightedGraph {
	private int size;
	private ArrayList<LinkedList<Edge>> edges = new ArrayList<LinkedList<Edge>>();
	
	
	/**
	 * Constructs a graph by initializing all the s LinkedList, being s the number of nodes
	 * @param s is the number of the nodes in the graph
	 */
	public Graph(int s){
		size = s;
		for(int i=0; i<s; i++) {
			edges.add(new LinkedList<Edge>());
		}
	}
	
	/**
	 * This method adds an edge connecting two nodes of the graph. Sets the connection between the
	 * nodes with weight w
	 * @param s is one of the nodes to be connected
	 * @param t is the other node to be connected
	 * @param w is the weight of the connection
	 */
	public void addEdge(int s, int t, int w) {
	
		edges.get(s-1).add(new Edge(t, w,0));
		edges.get(t-1).add(new Edge(s, w,0));
		
		
		
	}
	
	/**
	 * returns the number of nodes in the graph
	 */
	public int getNNodes() {
		return size;
	} 
	
	
	public ArrayList<LinkedList<Edge>> getNodes(){
		return this.edges;
	}
	/**
	 * removes the edge connecting two nodes. If there is one.
	 * 
	 * @param p is one of the nodes
	 * @param f is the other node
	 */
	public void removeEdge (int p, int f) {
		for(Edge neighbour: edges.get(f-1)) {
			if(neighbour.getTarget()==p) {
				edges.get(f-1).remove(neighbour);
			}
		}
		for(Edge neighbour: edges.get(p-1)) {
			if(neighbour.getTarget()==f) {
				edges.get(p-1).remove(neighbour);
			}
		}
	}
	
	/**
	 * This method returns a LinkedList of integers. This LinkedList has the indexes of the nodes 
	 * that are connected to the node f.  
	 * @param f is the node whose neighbors we want to know
	 * @return a LinkedList of f neighbors
	 */
	public LinkedList<Integer> neighbors(int f){
		LinkedList<Integer> neigs = new LinkedList<Integer>();
		for (Edge e: edges.get(f-1)) {
			neigs.add(e.getTarget());
		}
		return neigs;
	}
	
	/**
	 * This method returns a LinkedList of objects Edge. This LinkedList has all the Edges associated
	 * to the node f.
	 * @param f is the node whose edges we want to know
	 * @return return a LinkedList of edges connected to f.
	 */
	public LinkedList<Edge> adjacentEdges(int f){
		return edges.get(f-1);
	}
	
	/**
	 * This method returns the object Edge that represents the connection between two given nodes
	 * 
	 * @param s is one of the nodes
	 * @param t is the other node
	 * @return the edge that connects the nodes s and t
	 */
	public Edge getEdge(int s, int t) {
		if(this.neighbors(s).contains(t)) {
			for(Edge e: this.adjacentEdges(s))
				if(e.getTarget()==(t)) {
					return e;
				}
		}
		return null;
	}
	
    /**
     * @see java.lang.Object#toString()
     */
	public String toString() {
		return "Graph: \nsize=" + size + "\nedges=" + edges;
	}
	
	
}
