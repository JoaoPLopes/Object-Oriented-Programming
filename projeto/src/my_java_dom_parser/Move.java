package my_java_dom_parser;


/**
 * Public class Move.Java
 * An object Move is responsible for storing the informations from the xml file regarding the move events.
 * 
 * @author Joao Lopes, Gonçalo Carvalho, Alessio Vacca
 *
 */
public class Move {
	static float alpha;
	static float beta;
	static float delta;
	
	/**
	 * Constructor of an object Move
	 *
	 * @param a is the alpha from the xml file
	 * @param b is the beta from the xml file
	 * @param d us the teta from the xml file
	 */
	Move(float a, float b, float d){
		alpha = a;
		beta = b;
		delta = d;
	}
	
	
	/**
	 * getter method of the beta parameter read from the file
	 * @return the beta from the file
	 */
	public float getAlpha() {
		return alpha;
	}
	
	/**
	 * getter method of the parameters regarding the simulation read from the file
	 * @return an object containing all the elements regarding the simulation
	 */
	public float getbeta() {
		return beta;
	}
	
	/**
	 * getter method of the alpha parameter read from the file
	 * @return the alpha from the file
	 */
	public float getDelta() {
		return delta;
	}

    /**
     * @see java.lang.Object#toString()
     */
	@Override
	public String toString() {
		return "Move [alpha=" + alpha + ", beta=" + beta + ", delta=" + delta + "]";
	}


}
