package simulator;

import grafo.Edge;

/**
 * Class: EvPhero_Evap.java
 * 
 * @author Joao Lopes, Gonçalo Carvalho, Alessio Vacca
 *
 */
public class EvPhero_Evap extends Event {
	
	protected Edge direction1edge;
	protected Edge direction2edge;
	
	/**
	 * Constructs an Event EvPhero_Evap
	 * @param time the time stamp associated to the event
	 * @param direction1edge is the edge where the pherormone level is going to be decremented
	 * @param direction2edge is the symmetric edge in the graph. Both corresponds to the same edge since it is non-oriented graph
	 */
	EvPhero_Evap(double time, Edge _edge, Edge _edge2) {
		super(time);
		this.direction1edge=_edge;
		this.direction2edge=_edge2;
	}
	
	/**
	 * 
	 * Simulate the Event EvPhero_Evap
	 * As a result of this simulation the level of pheromone in the given edge is decremented
	 * and another event EvPhero_Evap is added to the  PEC
	 */
	public void simulate() {
			
		direction1edge.updatePheromone(-ColonySimulator.dados.getRho());
		direction2edge.updatePheromone(-ColonySimulator.dados.getRho()); 
	 
		if (direction1edge.getPherormone() <=0) {
			direction1edge.setPherormone(0);
			direction2edge.setPherormone(0);
			return ;
		}
	
		double t = time_stamp + expRandom(ColonySimulator.dados.getEta());
		if (t<ColonySimulator.dados.getFinalinst())
			ColonySimulator.pec.addEvPEC(new EvPhero_Evap(t, this.direction1edge , this.direction2edge ));		
	}
	
	/**
	 * @return the time stamp associated to the event
	 */
	public double getTimeStamp() {
		return this.time_stamp;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EvPhero_Evap [edge1=" + direction1edge.getTarget() + " edge2=" + direction2edge.getTarget() + " time "+ super.time_stamp+"]";
	}
	}
		
	

	
