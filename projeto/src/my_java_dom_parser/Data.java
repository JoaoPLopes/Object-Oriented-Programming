package my_java_dom_parser;

import java.util.List;

public class Data {
	private int nbnodes;
	private int nestnode;
	private List<Weights> edges;
	private Move move;
	private Simulation sim;
	private Evaporation evap;
	
	Data(int nb, int nest,List<Weights> ed, Move m, Simulation s, Evaporation e){
		this.nbnodes = nb;
		this.nestnode  = nest;
		this.edges = ed;
		this.move = m;
		this.sim = s;
		this.evap = e;
	}
	
	public int getNbNodes() {
		return this.nbnodes;
	}
	
	public int getNest() {
		return this.nestnode;
	}
	
	public Move getMove() {
		return this.move;
	}
	
	public Simulation getSimulation() {
		return this.sim;
	}
	
	public Evaporation getEvaportaion() {
		return this.evap;
	}
	
	public List<Weights> getEdges(){
		return this.edges;
	}
	
	
	@Override
	public String toString() {
		return "Graph nbnodes=" + nbnodes + ", nestnode=" + nestnode + "\n" + edges + "\n" + move + "\n" + evap + "\n" + sim;
	}
	
	
	
}
