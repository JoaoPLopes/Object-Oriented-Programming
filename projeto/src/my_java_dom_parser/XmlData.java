package my_java_dom_parser;

import java.util.List;

import exceptions.WrongXLMvalue;

/**
 * 
 * Interface of the Data that is needed to solve this Ant Colony Simulator problem.
 *  
 *  A class that wants to be used to read the data from a file for this problem must implement all the following abstract methods
 *  that give all the parameters needed to perform the simulation.
 * 
 * 	The file can be of any type since it implements all the methods in this interface.
 * 
 * @author Joao Lopes, Gonçalo Carvalho, Alessio Vacca
 *
 */
public interface XmlData {

	/**
	 * getter method of the number of graph nodes read from the file
	 * @return the number of graph nodes
	 */
	public int getNbNodes();
	
	/**
	 * getter method of the graph nest node read from the file
	 * @return the graph nest node
	 */
	public int getNest();
	
	/**
	 * getter method of the parameters regarding the ant move read from the file
	 * @return an object containing all the elements regarding the ant move
	 */
	public Object getMove();
	
	/**
	 * getter method of the delta parameter read from the file
	 * @return the delta from the file
	 */
	public double getDelta();
	
	/**
	 * getter method of the alpha parameter read from the file
	 * @return the alpha from the file
	 */
	public double getAlpha();
	
	/**
	 * getter method of the beta parameter read from the file
	 * @return the beta from the file
	 */
	public double getBeta();
	
	/**
	 * getter method of the parameters regarding the simulation read from the file
	 * @return an object containing all the elements regarding the simulation
	 */
	public Object getSimulation();

	/**
	 * getter method of the final instant of the simulation read from the file
	 * @return the final instant of the simulation
	 */
	public double getFinalinst();
	
	/**
	 * getter method of the plevel parameter read from the file
	 * @return the plevel from the file
	 */
	public float getpLevel();
	
	/**
	 * getter method of the colony size of the simulation read from the file
	 * @return the colony size of the simulation
	 */
	public float getColonySize();
	
	/**
	 * getter method of the parameters regarding the Evaporation Events read from the file
	 * @return an object containing all the elements regarding the Evaporation Events
	 */
	public Object getEvaporation();
	
	/**
	 * getter method of the rho parameter read from the file
	 * @return the rho from the file
	 */
	public float getRho();
	
	/**
	 * getter method of the eta parameter read from the file
	 * @return the eta from the file
	 */
	public float getEta();
	
	/**
	 * method used to add the weight to the graph weigh counter
	 * @param currentWeight is the weight of the edge to be added
	 */
	public void addGraphWeight(int currentWeight);
	
	/**
	 * method used to compute the sum of the total graph weight. The total graph weight is the sum of the weights of all edges in the graph
	 * @return the total weight of the graph
	 */
	public int getGraphWeight();
	
	/**
	 * getter method of the edges that are stored in the file
	 * @return a list with the edges stored in the and their weights
	 */
	public List<Weights> getEdges();
	
	/**
	 * Validatedata() is a method used to check if the values read from the File are valid
	 * @throws WrongXLMvalue if one of the values from the file is incorrect
	 */
	public void validatedata() throws WrongXLMvalue;
	
}
