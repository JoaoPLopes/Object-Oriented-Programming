package simulator;

import java.util.ArrayList;
import java.util.List;

import exceptions.NonPositive;
import grafo.Edge;
import grafo.Graph;


/**
 * 
 * Class: Ant.java
 * This class implements the Traverser interface.
 * An Ant is associated to an object graph. It can move in his graph nodes.
 * The ant stores its current node as the previous visited nodes.
 * @author Joao Lopes
 *
 */
public class Ant implements Traverser{
	private Path path;
	private Graph grafo;
	private double alfa;
	private double beta;
	private int idx;
	
	/**
	 * Constructs an object Ant
	 * @param n is the node where a new Ant is placed
	 * Using this constructor the other Ant field are associated to the correspondent 
	 * static fields in the class ColonySimulator
	 */
	Ant(int n, int i){
		path = new Path(n);
		grafo = ColonySimulator.grafo;
		alfa = ColonySimulator.alfa;
		beta = ColonySimulator.beta;
		idx = i;
	}
	
	public int getIdx() {
		return this.idx;
	}
	
	
	public Path getPath() {
		
		return path;
		
	}
	
	public List<Integer> getNonVisited(List<Integer> adjacents){
		List <Integer> nonVisited = new ArrayList<>();
		for (int i: adjacents) {
			if(!path.getVisited().contains(i)) {
				nonVisited.add(i);
			}
		}
		return nonVisited;
	}
	
	/**
	 * If there is a closed cycle inside the list containing the visited nodes this method removes
	 * all the nodes inside that cycle.
	 */
	public void removeCycle() {
		int current = path.getCurrentNode();
		int l = path.getVisited().size();
		path.getVisited().remove(l-1);
		path.getPathWeight().remove(l-2);
		l -=1;
		while (path.getVisited().get(l-1)!=current) {
			path.getVisited().remove(l-1);
			path.getPathWeight().remove(l-2);
			l-=1;
		}
	}
	
	
	public void placingPheroSetEvents(int totalPathWeight, List<Integer> hamiltonCycle, List<Integer> weights , float plevel, double time_stamp, float mean, PEC pec )  {
		int i=0;
		int j=1;
		double t=0;
		for (Integer hamiltontemp : hamiltonCycle) {
			if (i == grafo.getNNodes() )
				return;
			if ( (grafo.getEdge(hamiltontemp, hamiltonCycle.get(i+1)).getPherormone() == 0) && (grafo.getEdge( hamiltonCycle.get(i+1),hamiltontemp).getPherormone()==0)) {
				t=time_stamp + Event.expRandom(mean);
				if (t<ColonySimulator.simTime)
					pec.addEvPEC(new EvPhero_Evap(t,grafo.getEdge(hamiltontemp, hamiltonCycle.get(i+1)), grafo.getEdge( hamiltonCycle.get(i+1), hamiltontemp)));
			}
			
			grafo.getEdge(hamiltonCycle.get(i), hamiltonCycle.get(i+1)).updatePheromone(plevel*weights.get(j-1)/totalPathWeight);
			grafo.getEdge(hamiltonCycle.get(i+1), hamiltonCycle.get(i)).updatePheromone(plevel*weights.get(j-1)/totalPathWeight);
	
			i++;
			j++;
			
		}
	}
	
	
	public int calcWeight(List<Integer> path) {
		int sum=0;
		for(Integer path_temp : path) {
			sum+= path_temp;
		}
		return sum;
	}
	
	public int bestPath(int pathweight, int new_weight_path) throws NonPositive{
		if(new_weight_path < 0)
			throw new NonPositive("A path weight is Negative, something is wrong");
		if (pathweight==0)
			return new_weight_path;
		else
			return new_weight_path;
			
		}

	public boolean hasHamiltonCycle(int nbnodes) {
		if ((path.getVisited().size()==nbnodes) && path.getVisited().get(0)==path.getVisited().get(nbnodes-1)) {
			return true;
		}else
			return false;
	}
	
	public void move() {

		List<Double> probabilities = new ArrayList<>();
		List<Edge> nonVisited = new ArrayList<>();
		
		for(Edge e: grafo.adjacentEdges(path.getCurrentNode())) {
			if(!path.getVisited().contains(e.getTarget())) {
				probabilities.add(e.getCijk(alfa, beta));
				nonVisited.add(e);
			}
		}
		
		int idx = 0;
		int prevNode;
		if (probabilities.size()>0) {	
			probabilities = normProb(probabilities);
			idx = chooseProb(probabilities);
			prevNode = path.getCurrentNode();
			path.setCurrentNode( nonVisited.get(idx).getTarget());
			path.addVisitedNode((path.getCurrentNode()));
			path.addPathWeight(grafo.getEdge(prevNode, path.getCurrentNode()).getWeight());
		}else {
			for(Edge n: grafo.adjacentEdges(this.path.getCurrentNode())) {
				probabilities.add(n.getCijk(alfa, beta));
				nonVisited.add(n);
			}
			probabilities = normProb(probabilities);
			idx = chooseProb(probabilities);
			prevNode = path.getCurrentNode();
			path.setCurrentNode(nonVisited.get(idx).getTarget());
			path.addVisitedNode((path.getCurrentNode()));
			path.addPathWeight(grafo.getEdge(prevNode, path.getCurrentNode()).getWeight());
			// If it does not have an Hamilton cycle then the ant should go back
			if(!this.hasHamiltonCycle(grafo.getNNodes()+1)) {
				this.removeCycle();
			}
		}
	}
	
	/**
	 * Static method used to choose randomly an element of a vector with a certain probability.
	 * It is used in this class in the method move() to choose the next node when an ant is moving
	 * @param probabilities is a vector of probabilities. His sum is one
	 * @return return the index of the element that was picked randomly
	 */
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
	
	/**
	 * Static method that normalizes a vector of probabilities
	 * It is used in this class to compute the normalized probability of moving to a certain node
	 * in the move() method
	 * @param probabilities is the vector we want to normalize
	 * @return a normalized vector of probabilities
	 */
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
	
	
}
