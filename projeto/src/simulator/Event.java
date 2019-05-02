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
	
	protected double time_stamp;
	
	/**
	 * 
	 * @param time the time stamp of the event
	 */
	Event(double time){
		time_stamp = time;	

	}
	
	
	/**
	 * Abstract method to be implemented in the subclasses
	 */
	
	public abstract void simulate();
	
	
	/**
	 * @see java.lang.Object#hasCode()
	 */


	/**
	 * @see java.lang.Object#equals()
	 */

	 
	/**
	 * Static method used to generate random time stamps for the newly created events according 
	 * to an exponential distribution
	 * @param m is the mean of the exponential distribution
	 * @return a random double given by an exponential distribution
	 */
	
	public static double expRandom(double m ) {
		Random random = new Random();
		double next = random.nextDouble();
		return -m*Math.log(1.0-next);
		}
	
	
	
	

}
