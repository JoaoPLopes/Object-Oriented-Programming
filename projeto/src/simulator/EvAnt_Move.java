package simulator;

import exceptions.EdgeNextMoveException;

/**
 * Class: EvAnt_Move.java
 * This class extends the abstract class Event. An event Ant Move generates an event with a time stamp
 * 
 * @author Joao Lopes, Gonçalo Carvalho, Alessio Vacca
 *
 */
public class EvAnt_Move extends Event{
	
	protected Traverser ant;
	protected int nextNode;
	
	/**
	 * Constructs an Event Ant Move
	 * @param time the time stamp associated to the event
	 * @param ant the Traverser associated to the movement
	 * @param nn is the next node where the Traverser is going to move
	 */
	EvAnt_Move(double time, Traverser _ant, int nn) {
		super(time);
		ant = _ant;
		nextNode = nn;
	}

	
	/**
	 * 
	 * Simulate the Event Ant Move
	 * As a result of this simulation the traverser will move to the scheduled edge and
	 * another event ant move is added to the  PEC
	 */
	public void simulate() {
		
		getAnt().move(nextNode);
		if(!getAnt().getPath().hasHamiltonCycle()) {
			try {
				getAnt().chooseNextNode();
				
				int newNextNode = getAnt().chooseNextNode();
				
				int weight = ColonySimulator.grafo.getEdge(getAnt().getPath().getCurrentNode(), newNextNode).getWeight();
				
				double t = time_stamp + expRandom(ColonySimulator.dados.getDelta() * weight );
				
				if(t<ColonySimulator.dados.getFinalinst())
					ColonySimulator.pec.addEvPEC(new EvAnt_Move(t, getAnt(), newNextNode ));
			}
			catch (EdgeNextMoveException ex) {
				System.exit(-1);
			}
			

		}
	}
	
	/**
	 * 
	 * @return the traverser associated to the event
	 */
	public Ant getAnt() {
		return (Ant) ant;
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
		return "EvAnt_Move [ant=" + ant +" time "+ super.time_stamp+"]";
	}
	
}