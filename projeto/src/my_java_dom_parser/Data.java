package my_java_dom_parser;

import java.util.LinkedList;
import java.util.List;

import exceptions.WrongXLMvalue;

/**
 *  Class: Data.Java
 *  
 *  This class implements the XmlData.Java interface. It stores all the information read in XML file in static fields.
 * 
 *  @author Joao Lopes, Gonçalo Carvalho, Alessio Vacca
 *
 */
public class Data implements XmlData {
	static int nbnodes;
	static int nestnode;
	static List<Weights> edges;
	static Move move;
	static Simulation sim;
	static Evaporation evap;
	static int graphweight;
	
	/**
	 * Constructor of the object Data. It recieves as parameters the information from the xml
	 * @param nb is the number of graph nodes
	 * @param nest in the nest node
	 * @param ed is a list of all the graph edges
	 * @param m is an object with all the information regarding the move event
	 * @param s is an object with all the information regarding the simulation
	 * @param e is an object with all the information regarding the evaportaion
	 */
	Data(int nb, int nest, List<Weights> ed, Move m, Simulation s, Evaporation e){
		nbnodes = nb;
		nestnode  = nest;
		edges = ed;
		move = m;
		sim = s;
		evap = e;
	}
	
	/**
	 * getter method of the number of graph nodes read from the file
	 * @return the number of graph nodes
	 */
	public int getNbNodes() {
		return nbnodes;
	}
	
	/**
	 * getter method of the graph nest node read from the file
	 * @return the graph nest node
	 */
	public int getNest() {
		return nestnode;
	}
	
	/**
	 * getter method of the parameters regarding the ant move read from the file
	 * @return an object containing all the elements regarding the ant move
	 */
	public Move getMove() {
		return move;
	}
	
	/**
	 * getter method of the delta parameter read from the file
	 * @return the delta from the file
	 */
	public double getDelta() {
		return getMove().getDelta();
	}
	
	/**
	 * getter method of the alpha parameter read from the file
	 * @return the alpha from the file
	 */
	public double getAlpha() {
		return getMove().getAlpha();
	}
	
	
	/**
	 * getter method of the beta parameter read from the file
	 * @return the beta from the file
	 */
	public double getBeta() {
		return getMove().getbeta();
	}
	
	/**
	 * getter method of the parameters regarding the simulation read from the file
	 * @return an object containing all the elements regarding the simulation
	 */
	public Simulation getSimulation() {
		return sim;
	}
	
	/**
	 * getter method of the final instant of the simulation read from the file
	 * @return the final instant of the simulation
	 */
	public double getFinalinst() {
		return getSimulation().getFinalinst();
	}
	
	/**
	 * getter method of the plevel parameter read from the file
	 * @return the plevel from the file
	 */
	public float getpLevel() {
		return getSimulation().getPlevel();
	}
	
	/**
	 * getter method of the colony size of the simulation read from the file
	 * @return the colony size of the simulation
	 */
	public float getColonySize() {
		return getSimulation().getColonySize();
	}
	
	/**
	 * getter method of the edges that are stored in the file
	 * @return a list with the edges stored in the and their weights
	 */
	@Override
	public List<Weights> getEdges() {		
		return edges;
	}
	

	/**
	 * getter method of the parameters regarding the Evaporation Events read from the file
	 * @return an object containing all the elements regarding the Evaporation Events
	 */
	@Override
	public Evaporation getEvaporation() {
		return evap;
	}
	
	/**
	 * getter method of the rho parameter read from the file
	 * @return the rho from the file
	 */
	public float getRho() {
		return getEvaporation().getRho();
	}
	
	/**
	 * getter method of the eta parameter read from the file
	 * @return the eta from the file
	 */
	public float getEta() {
		return getEvaporation().getEta();
	}
	
	/**
	 * method used to compute the sum of the total graph weight. The total graph weight is the sum of the weights of all edges in the graph
	 *@param currentWeight is the weight of the current edge to be added
	 */
	public void addGraphWeight(int currentWeight) {
		graphweight+=currentWeight;
	}
	
	/**
	 * getter method of the edges that are stored in the file
	 * @return a list with the edges stored in the and their weights
	 */
	public int getGraphWeight() {
		return graphweight;
	}
	
	/**
	 * Validatedata() is a method used to check if the values read from the File are valid
	 * @throws WrongXLMvalue if one of the values from the file is incorrect
	 */
	public void validatedata() throws WrongXLMvalue {
		
		boolean validationnestnode=false;
		boolean validationofnbnodesdep=false;
		boolean validationofnbnodesarr=false;
		LinkedList<Integer> numberofdifnodes = new LinkedList<Integer>();


		if (getNbNodes() <1) {
			throw new WrongXLMvalue("Number of nodes should be greater or equal to 1");
		}
		
		if (getNest() <1 || getNest() > getNbNodes()) {
			throw new WrongXLMvalue("Number of nest node should be bigger than 0 and less than number of nodes");
		}
		
		if (getMove().getAlpha()<=0 || getMove().getbeta() <=0 || getMove().getDelta() <=0)
		{
			throw new WrongXLMvalue("Alpha, Beta and Delta need to be bigger than 0");
		}
		
		if (getEvaporation().getEta()<=0 || getEvaporation().getRho()<=0)
		{
			throw new WrongXLMvalue("Eta and Rho need to be bigger thab 0");
		}
		
		for (Weights temp : this.getEdges() ) {
			
			if (temp.getDeparture() > getNbNodes() || temp.getArraival() > getNbNodes())
				throw new WrongXLMvalue("There's a node index greater than the graph size");
			
			if (temp.getWeight() <=0) {
				throw new WrongXLMvalue("Edges with Weightless or Nonpositive Values");
			}
			if (temp.getDeparture() <=0 || temp.getArraival() <= 0) {
				throw new WrongXLMvalue("Departure or Arrival <=0 , should be >0");
			}
			
			if (temp.getDeparture() == this.getNest() || temp.getArraival() == this.getNest()) {
				validationnestnode = true;
			}
			
			for (Integer temp2 : numberofdifnodes) {
				
				if(temp.getDeparture()==temp2 ){
					validationofnbnodesdep = true;
							
				}
				if(temp.getArraival()==temp2 ) {
					validationofnbnodesarr=true;
					
				}
				
			}
			if(validationofnbnodesarr!=true) 
				numberofdifnodes.add(temp.getArraival());
			else 
				validationofnbnodesarr=false;
			
			if(validationofnbnodesdep!=true) 
				numberofdifnodes.add(temp.getDeparture());
			else 
				validationofnbnodesdep=false;
		
		
		
	}	 
		if(getNbNodes() != numberofdifnodes.size())
			throw new WrongXLMvalue("number of nodes not correct");
			
		if (validationnestnode != true) {
			throw new WrongXLMvalue("Nest node needs to be at least one of the target nodes or arrival");
		}
		if (getFinalinst()<=0) {
			throw new WrongXLMvalue("Time should be greater than 0");
		}
		if (getSimulation().getColonySize()<1) {
			throw new WrongXLMvalue("Colony size should be greater than 0");
		}
		if (getSimulation().getPlevel() < 0 ) {
			throw new WrongXLMvalue("Plevel should be greater than 0");
		}
		if (getSimulation().getColonySize()<1) {
			throw new WrongXLMvalue("Colony size should be greater than 0");
		}
		
	}
	
    /**
     * @see java.lang.Object#toString()
     */
	@Override
	public String toString() {
		return "Graph nbnodes=" + nbnodes + ", nestnode=" + nestnode + "\n" + edges + "\n" + move + "\n" + evap + "\n" + sim;
	}
	
}
