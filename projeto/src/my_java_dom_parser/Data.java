package my_java_dom_parser;

import java.util.LinkedList;
import java.util.List;

import exceptions.WrongXLMvalue;

public class Data {
	static int nbnodes;
	static int nestnode;
	static List<Weights> edges;
	static Move move;
	static Simulation sim;
	static Evaporation evap;
	static int graphweight;
	
	Data(int nb, int nest, List<Weights> ed, Move m, Simulation s, Evaporation e){
		nbnodes = nb;
		nestnode  = nest;
		edges = ed;
		move = m;
		sim = s;
		evap = e;
	}
	
	public int getNbNodes() {
		return nbnodes;
	}
	
	public int getNest() {
		return nestnode;
	}
	
	public Move getMove() {
		return move;
	}
	
	public Simulation getSimulation() {
		return sim;
	}
	
	public double getFinalinst() {
		return getSimulation().getFinalinst();
	}
	
	public void addGraphWeight(int currentWeight) {
		graphweight+=currentWeight;
	}
	
	public int getGraphWeight() {
		return graphweight;
	}
	
	public double getDelta() {
		return getMove().getDelta();
	}
	
	public float getpLevel() {
		return getSimulation().getPlevel();
	}
	
	
	public Evaporation getEvaportaion() {
		return evap;
	}
	
	public float getRho() {
		return getEvaportaion().getRho();
	}
	
	public float getEta() {
		return getEvaportaion().getEta();
	}
	
	public List<Weights> getEdges(){
		return edges;
	}
	
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
		
		if (getEvaportaion().getEta()<=0 || getEvaportaion().getRho()<=0)
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
	
	
	@Override
	public String toString() {
		return "Graph nbnodes=" + nbnodes + ", nestnode=" + nestnode + "\n" + edges + "\n" + move + "\n" + evap + "\n" + sim;
	}
	
	
	
}
