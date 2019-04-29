package my_java_dom_parser;

import java.util.List;

public class Data {
	static int nbnodes;
	static int nestnode;
	static List<Weights> edges;
	static Move move;
	static Simulation sim;
	static Evaporation evap;
	
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
	
	
	@Override
	public String toString() {
		return "Graph nbnodes=" + nbnodes + ", nestnode=" + nestnode + "\n" + edges + "\n" + move + "\n" + evap + "\n" + sim;
	}
	
	
	
}
