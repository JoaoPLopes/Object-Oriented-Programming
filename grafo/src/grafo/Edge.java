package grafo;

public class Edge {
	
	protected Node targetnode;
	
	protected double edge_pheromone=0;
	
	protected int edgeweight = 0;
	

	public Edge(Node t) {
		// TODO Auto-generated constructor stub
		targetnode=t;
	}
	
	
	public Node getTargetNode() {
		return this.targetnode;
	}
	
	public int getTargetNodeIdx() {
		return this.targetnode.getIdx();
	}
	
	public void setEdgeweight(int edgeweight) {
		this.edgeweight = edgeweight;
	}
	
	public int getEdgeweight() {
		return edgeweight;
	}
	
	public double getPheromone() {
		return edge_pheromone;
	}
	
	public void updatePheromone(double f) {
		this.edge_pheromone += f;
	}
	
	public void setPheromone(double f) {
		this.edge_pheromone = f;
	}
	
	
	
	public double getCijk(double alfa, double beta) {
		return (alfa+this.getPheromone())/(beta+this.getEdgeweight());
	}

	@Override
	public String toString() {
		return "Edge [targetnode=" + targetnode.getIdx() + ", pheromone=" + edge_pheromone + ", edgeweight=" + edgeweight + "]";
	}





	
}
