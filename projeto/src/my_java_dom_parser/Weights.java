package my_java_dom_parser;

/**
 * 
 * Public class: Weights.java
 * 
 * The class weights stores the graph connections that are given in the xml file.
 * 
 * @author Joao Lopes, Gonçalo Carvalho, Alessio Vacca
 *
 */
public class Weights {
	int departure;
	int arraival;
	int weight;
	
	/**
	 * Constructs an object Weight
	 * @param d is the departure node of the edge
	 * @param a is the arraival node of the edge
	 */
	Weights(int d, int a){
		departure = d;
		arraival = a;
	}
	
	/**
	 * Getter method of the departure field of an object weights
	 * @return the departure node
	 */
	public int getDeparture() {
		return departure;
	}
	
	/**
	 * Setter method of the the field weight
	 * @param _weight is the weight to be setted
	 */
	public void setWeight(int _weight) {
		weight=_weight;
	}
	
	/**
	 * Getter method of the field arrival from the obejct weights
	 * @return the arrival node 
	 */
	public int getArraival() {
		return arraival;
	}
	
	/**
	 * Getter method of the field weight
	 * @return returns the weight of an object Weights
	 */
	public int getWeight() {
		return weight;
	}

    /**
     * @see java.lang.Object#toString()
     */
	@Override
	public String toString() {
		return "Edges [departure=" + departure + ", arraival=" + arraival + ", weight=" + weight + "]";
	}
	
	
}
