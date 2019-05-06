package simulator;

import java.util.ArrayList;
import java.util.List;
import exceptions.EdgeNextMoveException;
import grafo.Edge;


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
	private TraverserPath path;
	private int idx;
	
	/**
	 * Constructs an object Ant
	 * @param n is the node where a new Ant is placed
	 * Using this constructor the other Ant field are associated to the correspondent 
	 * static fields in the class ColonySimulator
	 */
	Ant(int n, int i){
		path = new Path(n);
		idx = i;
	}
	
	public int getIdx() {
		return this.idx;
	}
	
	
	public Path getPath() {
		
		return (Path) path;
		
	}
	
	
	public void placingPheroSetEvents(int totalPathWeight, double time_stamp )  {
		int i=0;
		double t=0;
		int nextNode;
		for (Integer node : this.path.getVisited()) {
			if (i == ColonySimulator.grafo.getNNodes() )
				return;
			
			nextNode =  this.path.getVisited().get(i+1);
			
			if ( (ColonySimulator.grafo.getEdge(node, nextNode).getPherormone() == 0)){
				
				t = time_stamp + Event.expRandom(ColonySimulator.dados.getEta());
				
				if (t<ColonySimulator.dados.getFinalinst())
					
					ColonySimulator.pec.addEvPEC(new EvPhero_Evap(t, ColonySimulator.grafo.getEdge(node, nextNode), ColonySimulator.grafo.getEdge(nextNode, node)));
					}
			
			ColonySimulator.grafo.getEdge(node, nextNode).updatePheromone(pherormoneInc(totalPathWeight));
			ColonySimulator.grafo.getEdge(nextNode, node).updatePheromone(pherormoneInc(totalPathWeight));
			
			i++;
			
		}
	}
	
	
	public int chooseNextNode() throws EdgeNextMoveException {
		
		List<Double> probabilities = new ArrayList<>();
		List<Edge> nonVisited = new ArrayList<>();
		
		if (ColonySimulator.grafo.adjacentEdges(path.getCurrentNode()).size()==0)
			throw new EdgeNextMoveException();

		for(Edge e: ColonySimulator.grafo.adjacentEdges(path.getCurrentNode())) {
			if(!path.getVisited().contains(e.getTarget())) {
				probabilities.add(getCijk(e));
				nonVisited.add(e);
			}
		}
		
		int idx = 0;

		if (probabilities.size()>0) {	
			probabilities = normProb(probabilities);
			idx = chooseProb(probabilities);
			//path.setboolean(true);
			
		}else {
			for(Edge n: ColonySimulator.grafo.adjacentEdges(this.path.getCurrentNode())) {
				probabilities.add(getCijk(n));
				nonVisited.add(n);
			}
			probabilities = normProb(probabilities);
			idx = chooseProb(probabilities);

			//path.setboolean(false);
		}
		
		return nonVisited.get(idx).getTarget();
		
		
	}
	
	public void move(int nextNode) {
			int prevNode = path.getCurrentNode();
			path.setCurrentNode(nextNode);
			path.addVisitedNode(nextNode);
			int weight = ColonySimulator.grafo.getEdge(prevNode, nextNode).getWeight();
			path.addPathWeight(weight);
			//System.out.println(path.getVisited() + "\n");
			// If it does not have an Hamilton cycle then the ant should go back
			if(!this.path.hasHamiltonCycle()) {
				path.removeCycle();
				}
			
	}
	
	@Override
	public String toString() {
		return "Ant [path=" + path + ", idx=" + idx + "]";
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
	
	public static float pherormoneInc(int totalPathWeight) {
		return ColonySimulator.dados.getpLevel()*ColonySimulator.dados.getGraphWeight();
	}
	
	public static double getCijk(Edge e) {
		return (ColonySimulator.dados.getAlpha()+e.getPherormone())/(ColonySimulator.dados.getBeta()+e.getWeight()); 
	}
	
	
	
}