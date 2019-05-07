package simulator;
/**
 * 
 * Class: EvReport.java
 * This class extends the abstract class Event. An event Ant Move generates an event with a time stamp
 * 
 * @author Joao Lopes, Gonçalo Carvalho, Alessio Vacca
 *
 */
public class EvReport extends Event {
	
	private static int instant = 1;
	private double reportincrements;
	

	/**
	 * Constructs an event EvReport 
	 * @param time is the time tamp associated with this event
	 */
	EvReport(double time){
		super(time);
		reportincrements = ColonySimulator.reportincrements;
	}
	 
	/**
	 * Simulate the event EvReport. As a result of this simulation a new event of this kind is generated with a new time stamp.
	 * If the new time stamp is larger than the final instant of the simulation than no other event is created.
	 */
	public void simulate() {
		instant ++;
		if (!(reportincrements*instant > ColonySimulator.dados.getFinalinst()))
			ColonySimulator.pec.addEvPEC(new EvReport(reportincrements*instant));
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
		
		return "EvReport [reportincrements=" + reportincrements + "    "+ super.time_stamp + "]";
	}


}