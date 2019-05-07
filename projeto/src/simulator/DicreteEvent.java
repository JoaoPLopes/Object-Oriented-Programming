package simulator;

/**
 * Discrete Event interface. An Discrete Event is completed with a single method to simulate itself
 * 
 * @author Joao Lopes, Gonçalo Carvalho, Alessio Vacca
 *
 */
public interface DicreteEvent {
	
	/**
	 * Simulates the Discrete Event
	 */
	public void simulate();

	/**
	 * Getter method of the time stamp of the Discrete Event
	 * @return the time stamp of the Discrete Event
	 */
	public double getTimeStamp();
	

}
