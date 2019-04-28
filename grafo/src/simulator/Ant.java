package simulator;

import java.util.ArrayList;
import java.util.List;

import exceptions.NonPositive;
import grafo.Graph;
import grafo.Node;
import simulator.PEC;


public class Ant {
	private Path path;
	int idx;
	
	Ant(Node n, int i){
		path= new Path(n);
		idx = i;
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
	
	public void removeCycle() {
		int current = path.getCurrentNode().getIdx();
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
	

	public void placingPheroSetEvents(int totalPathWeight, Graph grafo, List<Integer> hamiltonCycle, List<Integer> weights , float plevel, double time_stamp, float mean, PEC pec ) {
		int i=0;
		int j=1;
		for (Integer hamiltontemp : hamiltonCycle) {
			if (i == grafo.getNbnodes() )
				return;
			
			if ( (grafo.getNode(hamiltontemp).getEdgeint(hamiltonCycle.get(i+1)).getPheromone() == 0) && (grafo.getNode(hamiltonCycle.get(i+1)).getEdgeint(hamiltontemp)).getPheromone()==0) {
				pec.addEvPEC(new EvPhero_Evap(time_stamp + ExpRandom.expRandom(mean), grafo.getNode(hamiltontemp).getEdgeint(hamiltonCycle.get(i+1)), grafo.getNode(hamiltonCycle.get(i+1)).getEdgeint(hamiltontemp)));
			}
			
			grafo.getNode(hamiltontemp).getEdgeint(hamiltonCycle.get(i+1)).updatePheromone(plevel*weights.get(j-1)/totalPathWeight);
			grafo.getNode(hamiltonCycle.get(i+1)).getEdgeint(hamiltontemp).updatePheromone(plevel*weights.get(j-1)/totalPathWeight);
	
			i++;
			
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
	
	
}
