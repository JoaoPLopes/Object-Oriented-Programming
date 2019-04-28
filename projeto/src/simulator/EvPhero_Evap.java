package simulator;

import grafo.Edge;

public class EvPhero_Evap extends Event {
	
	protected Edge direction1edge;
	protected Edge direction2edge;
	
	EvPhero_Evap(double time, Edge _edge, Edge _edge2) {
		super(time);
		this.direction1edge=_edge;
		this.direction2edge=_edge2;
	}

	public void simulate() {
			
		direction1edge.updatePheromone(-plevel);
		direction2edge.updatePheromone(-plevel);
	
		if (direction1edge.getPherormone() <=0) {
			direction1edge.setPherormone(0);
			direction2edge.setPherormone(0);
			return ;
		}
	
		double t = time_stamp + expRandom(mean);
		if (t<simTime)
			pec.addEvPEC(new EvPhero_Evap(t, this.direction1edge , this.direction2edge ));
		
			
	}

	@Override
	public String toString() {
		return "EvPhero_Evap [edge1=" + direction1edge.getTarget() + " edge2=" + direction2edge.getTarget() + " time "+ super.time_stamp+"]";
	}
	}
		
	

	

