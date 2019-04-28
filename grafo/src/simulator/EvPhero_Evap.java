package simulator;

import grafo.Edge;
import grafo.Graph;

public class EvPhero_Evap extends Event {
	
	protected Edge direction1edge;
	protected Edge direction2edge;
	
	EvPhero_Evap(double time, Edge _edge, Edge _edge2) {
		super(time);
		this.direction1edge=_edge;
		this.direction2edge=_edge2;
	}

	public void simulate(Graph grafo) {
			
		direction1edge.updatePheromone(-plevel);
		direction2edge.updatePheromone(-plevel);
	
		if (direction1edge.getPheromone() <=0) {
			direction1edge.setPheromone(0);
			direction2edge.setPheromone(0);
			return ;
		}
	
		
		pec.addEvPEC(new EvPhero_Evap(time_stamp + ExpRandom.expRandom(mean), this.direction1edge , this.direction2edge ));
		
			
	}

	@Override
	public String toString() {
		return "EvPhero_Evap [edge1=" + direction1edge.getTargetNode() + " edge2=" + direction2edge.getTargetNode() + " time "+ super.time_stamp+"]";
	}
	}
		
	

	

