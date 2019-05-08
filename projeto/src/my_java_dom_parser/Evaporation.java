package my_java_dom_parser;

/**
 * Public class Evaporation.Java
 * An object Evaporation is responsible for storing the informations from the xml file regarding the evaporation events.
 * 
 * @author Joao Lopes, Gonçalo Carvalho, Alessio Vacca
 *
 */
public class Evaporation {
	
	static float eta;
	static float rho;
	
	/**
	 * Constructor of an object Evaporation. An object evaporation stores the elements of the xml file regarding the Evaporation
	 * event, namely the parameters eta and rho.
	 * 
	 * @param e is the parameter eta
	 * @param r is the parameter rho
	 */
	Evaporation(float e, float r){
		eta = e; 
		rho = r;
	}
	
	/**
	 * getter method of the eta parameter read from the file
	 * @return the eta from the file
	 */
	public float getEta() {
		return eta;
	} 
	
	/**
	 * getter method of the rho parameter read from the file
	 * @return the rho from the file
	 */
	public float getRho() {
		return rho;
	}

    /**
     * @see java.lang.Object#toString()
     */
	@Override
	public String toString() {
		return "Evaportaion [eta=" + eta + ", rho=" + rho + "]";
	}
}