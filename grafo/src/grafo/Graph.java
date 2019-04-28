package grafo;


import java.util.Arrays;

import exceptions.NonPositive;



public class Graph {
	
	protected int nbnodes;
	protected int nestnode;
	
	protected Node[] nodes;
	
	
	public Graph(int nb, int nest) {
		nbnodes=nb;
		nestnode=nest;
		
		nodes = new Node[nbnodes];
		
		for(int i=0;i<nbnodes;i++) {
			if (i!=nestnode-1) {nodes[i]=new Node(i+1);}
		}
		
		nodes[nestnode-1]=new Node(nestnode);
	}
	
	public Node getNode(int idx) {
		return nodes[idx-1];
	}
	
	public void newEdge(Node sourceNode, Node targetNode, int edgeWeight) throws NonPositive {
		if (sourceNode.getIdx()>nbnodes) {
			System.out.println("Nonexistent Source Node");
		}else if (targetNode.getIdx()>nbnodes) {
			System.out.println("Nonexistent Target Node");
		}else {
			if(edgeWeight <=0)
					throw new NonPositive("XML has negative or weightless edges");
			nodes[sourceNode.getIdx()-1].addEdge(targetNode,edgeWeight);
			nodes[targetNode.getIdx()-1].addEdge(sourceNode,edgeWeight);
		}
	}
	
	public int getNbnodes () {
		return this.nbnodes;
	}

	@Override
	public String toString() {
		return "Graph [nbnodes=" + nbnodes + ", nestnode=" + nestnode + ", nodes=" + Arrays.toString(nodes) + "]";
	}



	
}