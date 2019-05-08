package my_java_dom_parser;

/**
 * Public class Simulation.Java
 * An object Simulation is responsible for storing the informations from the xml file regarding the simulation events.
 * 
 * @author Joao Lopes, Gonçalo Carvalho, Alessio Vacca
 *
 */
public class Simulation {
	static float finalinst;
	static int antcolsize;
	static float plevel;
	
	/**
	 * Constructs an object Simulation. 
	 * 
	 * @param f is the simulation final instant
	 * @param a is the ant colony size
	 * @param p is the p level from the xml file
	 */
	Simulation(float f, int a, float p){
		finalinst = f;
		antcolsize = a;
		plevel = p;
	}
	

	/**
	 * getter method of the final instant of the simulation read from the file
	 * @return the final instant of the simulation
	 */
	public float getFinalinst() {
		return finalinst;
	}
	
	/**
	 * getter method of the colony size of the simulation read from the file
	 * @return the colony size of the simulation
	 */
	public int getColonySize() {
		return antcolsize;
	}
	
	
	/**
	 * getter method of the plevel parameter read from the file
	 * @return the plevel from the file
	 */
	public float getPlevel() {
		return plevel;
	}
	
    /**
     * @see java.lang.Object#toString()
     */
	@Override
	public String toString() {
		return "Simulation [finalinst=" + finalinst + ", antcolsize=" + antcolsize + ", plevel=" + plevel + "]";
	}
	
}
