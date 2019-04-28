package grafo;

public class Edge {
	private int weight;
	private int target;
	private float pherormones;
	
	Edge(int t, int w, float f){
		weight = w;
		target = t; 
		pherormones = f; 
	}

	public int getWeight() {
		return weight;
	}
	
	public int getTarget() {
		return target;
	}
	
	public float getPherormone() {
		return pherormones;
	}

	public void setPherormone(int i) {
		this.pherormones = i; 
	}
	
	public void updatePheromone(float f) {
		this.pherormones += f;
	}
	
	public double getCijk(double alfa, double beta) {
		return (alfa+this.getPherormone())/(beta+this.getWeight());
	}
	
	@Override
	public String toString() {
		return "Target=" + target + " Weight=" + weight +" Pherormone="+ pherormones;
	}
	
	
	
}
