package grafo;

import java.util.ArrayList;
import java.util.List;


public class Node {
	
	protected int nodeidx;
	
	List<Edge> edges = new ArrayList<>();

	public Node(int i) {
		// TODO Auto-generated constructor stub
		nodeidx=i;
	}
	
	public int getIdx() {
		return nodeidx;
	}
	
	public void addEdge (Node t, int w) {
		Edge edge = new Edge(t);
		edge.setEdgeweight(w);
		edges.add(edge);
		
	}
	
	
	public Edge getEdge(Node nextnode) {
		int idx=0;
		for(Edge tempedge : edges) {
			if(nextnode==tempedge.getTargetNode() )
				return edges.get(idx);
			idx++;
		}
		return edges.get(0);
	}
	
	public Edge getEdgeint(int nextnode) {
		int idx=0;
		for(Edge tempedge : edges) {
			if(nextnode==tempedge.getTargetNodeIdx() )
				return edges.get(idx);
			idx++;
		}
		return edges.get(0);
	}

	
	public List<Edge> getAdjacent() {
		return edges;
	}
	
	public boolean isAdjacent(int b) {
		for (Edge e: this.edges) {
			if(e.getTargetNode().getIdx()==b) {
				return true;
			}
		}
		return false;
	}
	
	
//	public int EdgeWeight(int targetNode) {
//		if (this.isAdjacent(targetNode)){
//			for (Edge e: this.edges) {
//				if(e.getTargetNodeIdx()==targetNode) {
//					return e.getEdgeweight();
//				}
//		}
//			return 0;
//	}


	@Override
	public String toString() {
		return "Node [nodeidx=" + nodeidx + ", edges=" + edges + "]";
	}

	

}