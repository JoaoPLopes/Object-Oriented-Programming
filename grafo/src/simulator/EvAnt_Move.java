package simulator;

import grafo.Edge;
import grafo.Graph;
import grafo.Node;

import java.util.ArrayList;

import java.util.List;

public class EvAnt_Move extends Event{
	
	protected Ant ant;
	

	EvAnt_Move(double time, Ant a) {
		super(time);
		ant =  a;
		// TODO Auto-generated constructor stub
	}

	public void simulate(Graph grafo) {
		// TODO Auto-generated method stub
		List<Edge> adjacents = ant.getPath().getCurrentNode().getAdjacent();
		List<Double> probabilities = new ArrayList<>();
		List<Edge> nonVisited = new ArrayList<>();
		//System.out.println(ant.getPath().getVisited());
		//System.out.println("simulate ant: " + ant.idx +" WeightPath " + ant.getPath().getPathWeight());
		
		
		// Adjacent non visited edges to the current node
		for(Edge n: adjacents) {
			int target = n.getTargetNodeIdx();
			if(!ant.getPath().getVisited().contains(target)) {
				probabilities.add(n.getCijk(alfa, beta));
				nonVisited.add(n);
			}
		}
		int idx = 0;
		
		if (probabilities.size()>0) {	
			probabilities = normProb(probabilities);
			Node prevCurrentNode=ant.getPath().getCurrentNode();
			idx = chooseProb(probabilities);
			ant.getPath().setCurrentNode(nonVisited.get(idx).getTargetNode());
			ant.getPath().addVisitedNode(ant.getPath().getCurrentNode().getIdx());
			ant.getPath().addPathWeight(grafo.getNode(prevCurrentNode.getIdx()).getEdge(ant.getPath().getCurrentNode()).getEdgeweight());
			
		}else {
			for(Edge n: adjacents) {
				probabilities.add(n.getCijk(alfa, beta));
				nonVisited.add(n);
			}
			probabilities = normProb(probabilities);
			Node prevCurrentNode=ant.getPath().getCurrentNode();
			idx = chooseProb(probabilities);
			ant.getPath().setCurrentNode(nonVisited.get(idx).getTargetNode());
			ant.getPath().addVisitedNode(ant.getPath().getCurrentNode().getIdx());	
			ant.getPath().addPathWeight(grafo.getNode(prevCurrentNode.getIdx()).getEdge(ant.getPath().getCurrentNode()).getEdgeweight());
			
			if(!ant.hasHamiltonCycle(nbnodes+1)) {
				ant.removeCycle();
			}
		}
		
		//System.out.println(nonVisited.get(idx).getTargetNode());
		
		pec.addEvPEC(new EvAnt_Move(time_stamp + ExpRandom.expRandom(mean), ant));
		
	}
	
	
	public static int chooseProb(List<Double> probabilities){
		double p = Math.random();
		double cumulativeProbability = 0.0;
		for (Double item : probabilities) {
			cumulativeProbability += item;
			if (p <= cumulativeProbability) {
				double x = item;
				return probabilities.indexOf(x);
			}
		}
		return 0;
	}
	
	public static List<Double> normProb(List<Double> probabilities){
		double sum=0;
		for(int i=0; i<probabilities.size();i++) {
			sum += probabilities.get(i);
		}
		
		for(int i=0; i<probabilities.size();i++) {
			probabilities.set(i, probabilities.get(i)/sum);
		}
		return probabilities;
	}
	
	public int getAnt() {
		return this.ant.idx;
	}
	
	@Override
	public String toString() {
		return "EvAnt_Move [ant=" + ant.idx +" time "+ super.time_stamp+"]";
	}
	
}
