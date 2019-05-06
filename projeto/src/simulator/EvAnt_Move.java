package simulator;

import exceptions.EdgeNextMoveException;

/**
 * Class: EvAnt_Move.java
 * 
 * @author Joao Lopes
 *
 */
 
public class EvAnt_Move extends Event{
	
	protected Traverser ant;
	protected int nextNode;
	
	/**
	 * Constructs an Event Ant Move
	 * @param time the time stamp associated to the event
	 * @param ant the ant associated to the movement
	 */

	EvAnt_Move(double time, Traverser _ant, int nn) {
		super(time);
		ant = _ant;
		nextNode = nn;
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * 
	 * Simulate the Event Ant Move
	 * As a result of this simulation another event ant move is added to the  PEC
	 */
	public void simulate() {
		
		ant.move(nextNode);
		if(!ant.getPath().hasHamiltonCycle()) {
			try {
				ant.chooseNextNode();
				
				int newNextNode = ant.chooseNextNode();
				
				int weight = ColonySimulator.grafo.getEdge(ant.getPath().getCurrentNode(), newNextNode).getWeight();
				
				double t = time_stamp + expRandom(ColonySimulator.dados.getDelta() * weight );
				
				if(t<ColonySimulator.dados.getFinalinst())
					ColonySimulator.pec.addEvPEC(new EvAnt_Move(t, ant, newNextNode ));
			}
			catch (EdgeNextMoveException ex) {
				System.exit(-1);
			}
			

		}
	}
	
	/**
	 * 
	 * @return the index of the ant associated to the event
	 */
	public Traverser getAnt() {
		return this.ant;
	}
	
	public double getTimeStamp() {
		return this.time_stamp;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EvAnt_Move [ant=" + ant +" time "+ super.time_stamp+"]";
	}
	
}