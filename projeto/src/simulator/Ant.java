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
 * @author Joao Lopes, Gonçalo Carvalho, Alessio Vacca
 *
 */
public class Ant implements Traverser{
	private TraverserPath path;
	private int idx;
	
	/**
	 * Constructs an object Ant
	 * @param n is the node where a new Ant is placed
	 */
	Ant(int n){
		path = new Path(n);
	}
	
	/**
	 * This method is getter of the traverser path.
	 * @return a path implementation of the TraverserPath interface
	 */
	public Path getPath() {
		
		return (Path) path;
		
	}
	
	/**
	 * This method is used by an object Ant to place pheromones in the graph.
	 * @param totalPathWeight is the weight of the path where the pherormones are going to be layed
	 * @param time is the current simulation time
	 */
	public void placingPheroSetEvents(int totalPathWeight, double time )  {
		int i=0;
		double t=0;
		int nextNode;
		float inc = pherormoneInc(totalPathWeight);
		for (Integer node : getPath().getVisited()) {
			if (i == ColonySimulator.grafo.getNNodes() )
				return;
			
			nextNode =  getPath().getVisited().get(i+1);
			
			if ( (ColonySimulator.grafo.getEdge(node, nextNode).getPherormone() == 0)){
				
				t = time + Event.expRandom(ColonySimulator.dados.getEta());
				
				if (t<ColonySimulator.dados.getFinalinst())
					
					ColonySimulator.pec.addEvPEC(new EvPhero_Evap(t, ColonySimulator.grafo.getEdge(node, nextNode), ColonySimulator.grafo.getEdge(nextNode, node)));
					}
			
			ColonySimulator.grafo.getEdge(node, nextNode).updatePheromone(inc);
			ColonySimulator.grafo.getEdge(nextNode, node).updatePheromone(inc);
			
			i++;
			
		}
	}
	
	/**
	 * This method chooses randomly the next node where the Traverser is going to move
	 * 
	 * @return the index of the next node
	 * @throws EdgeNextMoveException if the next node has an invalid index
	 */
	public int chooseNextNode() throws EdgeNextMoveException {
		
		List<Double> probabilities = new ArrayList<>();
		List<Edge> nonVisited = new ArrayList<>();
		
		if (ColonySimulator.grafo.adjacentEdges(getPath().getCurrentNode()).size()==0)
			throw new EdgeNextMoveException();

		for(Edge e: ColonySimulator.grafo.adjacentEdges(getPath().getCurrentNode())) {
			if(!getPath().getVisited().contains(e.getTarget())) {
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
	
	/**
	 *  move the traverser from the current node to the next node
	 * @param nextNode is the next node of the traverser 
	 */
	public void move(int nextNode) {
			int prevNode = getPath().getCurrentNode();
			getPath().setCurrentNode(nextNode);
			getPath().addVisitedNode(nextNode);
			int weight = ColonySimulator.grafo.getEdge(prevNode, nextNode).getWeight();
			getPath().addPathWeight(weight);
			//System.out.println(path.getVisited() + "\n");
			// If it does not have an Hamilton cycle then the ant should go back
			if(getPath().hasDuplicate()) {
				if(!getPath().hasHamiltonCycle()) {
					getPath().removeCycle();
				}
			}
			
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
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
		for (int i = 0; i < probabilities.size(); i++) {
			cumulativeProbability += probabilities.get(i);
			if (p <= cumulativeProbability) {
				return i;
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
	
	/**
	 * This static method computes the increment of pherormones when a Traverser is laying pheromones int the graph
	 * @param totalPathWeight is the weight of the path where the pheromones are being layed
	 * @return the level of pherormone increment
	 */
	public static float pherormoneInc(int totalPathWeight) {
		return ColonySimulator.dados.getpLevel()*ColonySimulator.dados.getGraphWeight();
	}
	
	/**
	 * This static method is used to compute the non-normalized probabilities of a traverser moving through a given edge
	 * @param e is the edge where the traverser is going to move
	 * @return the non-normalized probability of that move
	 */
	public static double getCijk(Edge e) {
		return (ColonySimulator.dados.getAlpha()+e.getPherormone())/(ColonySimulator.dados.getBeta()+e.getWeight()); 
	}
	
}