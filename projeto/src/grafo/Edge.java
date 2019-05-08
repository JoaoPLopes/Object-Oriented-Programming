package grafo;

/**
 * Class: Edge.Java
 * 
 * An object edge is an object between two nodes of a graph. An edge has as fields the index of the node that this edge is pointing at
 * a weight and a pherormone level. The weight represents the weight of the connection.
 * 
 * @author Joao Lopes, Gonçalo Carvalho, Alessio Vacca
 *
 */
public class Edge {
	private int weight;
	private int target;
	private float pherormones;
	   
	/**
	 * Constructor of an object Edge
	 * 
	 * @param t is one of the nodes to be be connected
	 * @param w	is the weigh of the connection
	 * @param f is the  pherormone level in the edge
	 */
	Edge(int t, int w, float f){
		weight = w;
		target = t;
		pherormones = f;
	}

	/**
	 * Getter method for the feild weight
	 * @return the Edge weight
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * Getter of the field target
	 * @return the target node of the edge
	 */
	public int getTarget() {
		return target;
	}
	
	/**
	 * Getter of the field pherormone
	 * @return  pherormone level of the edge
	 */
	public float getPherormone() {
		return pherormones;
	}

	/**
	 * Setter of the field pherormone
	 * 
	 * @param i is the pherormone level to be setted
	 */
	public void setPherormone(int i) {
		this.pherormones = i; 
	}
	
	/**
	 * This method updates the pherormone level by adding it a value that is passed
	 * 
	 * @param f is the pherormine level to be added
	 */
	public void updatePheromone(float f) {
		this.pherormones += f;
	}
	
	/**
	 * This method performs a relation between the weight of the edge and its pherormone level.
	 * Two parameters are needed to perform this relation
	 * 
	 * @param alfa is one of the parameters in the relation
	 * @param beta is the other parameter
	 * @return the value of the relation between the weight an the pherormone level of the edge
	 */
	public double getCijk(double alfa, double beta) {
		return (alfa+this.getPherormone())/(beta+this.getWeight()); 
	}
	
	
    /**
     * @see java.lang.Object#toString()
     */
	@Override
	public String toString() {
		return "Target=" + target + " Weight=" + weight +" Pherormone="+ pherormones;
	}
	
	
	
}
