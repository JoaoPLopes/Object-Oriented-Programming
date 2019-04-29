package simulator;

/**
 * Class: EvAnt_Move.java
 * 
 * @author Joao Lopes
 *
 */

public class EvAnt_Move extends Event{
	
	protected Ant ant;
	
	/**
	 * Constructs an Event Ant Move
	 * @param time the time stamp associated to the event
	 * @param a the ant associated to the movement
	 */

	EvAnt_Move(double time, Ant a) {
		super(time);
		ant =  a;
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * 
	 * Simulate the Event Ant Move
	 * As a result of this simulation another event ant move is added to the  PEC
	 */
	public void simulate() {
		ant.move();
		double t = time_stamp + expRandom(ColonySimulator.dados.getMove().getDelta());
		if(t<ColonySimulator.dados.getSimulation().getFinalinst())
			ColonySimulator.pec.addEvPEC(new EvAnt_Move(t, ant));
		
	}
	
	/**
	 * 
	 * @return the index of the ant associated to the event
	 */
	public int getAnt() {
		return ant.getIdx();
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EvAnt_Move [ant=" + ant +" time "+ super.time_stamp+"]";
	}
	
}