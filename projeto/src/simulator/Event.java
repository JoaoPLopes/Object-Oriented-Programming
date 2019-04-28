package simulator;

import java.util.Random;

/**
 * Abstract Class: Event.java
 * This class is the super class of the events that are going to be simulated and stored in the PEC
 * 
 * @author Joao Lopes
 *
 */


public abstract class Event implements DicreteEvent{
	
	protected double simTime;
	protected double time_stamp;
	protected double mean;
	protected float alfa;
	protected float beta;
	protected int nbnodes;
	protected float plevel;
	
	protected PEC pec = ColonySimulator.pec;
	
	
	/**
	 * 
	 * @param time the time stamp of the event
	 */
	Event(double time){
		time_stamp = time;	
		simTime = ColonySimulator.simTime;
		mean = ColonySimulator.delta;
		alfa = ColonySimulator.alfa;
		beta = ColonySimulator.beta;
		nbnodes = ColonySimulator.nbnodes;
		plevel = ColonySimulator.plevel;
	}
	
	
	/**
	 * Abstract method to be implemented in the subclasses
	 */
	
	public abstract void simulate();
	
	
	/**
	 * @see java.lang.Object#hasCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(mean);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(time_stamp);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * @see java.lang.Object#equals()
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (Double.doubleToLongBits(mean) != Double.doubleToLongBits(other.mean))
			return false;
		if (Double.doubleToLongBits(time_stamp) != Double.doubleToLongBits(other.time_stamp))
			return false;
		return true;
	}
	
	/**
	 * Static method used to generate random time stamps for the newly created events according 
	 * to an exponential distribution
	 * @param m is the mean of the exponential distribution
	 * @return a random double given by an exponential distribution
	 */
	public static double expRandom(double m) {
		Random random = new Random();
		double next = random.nextDouble();
		return -m*Math.log(1.0-next);
		}
	
	
	
	

}
