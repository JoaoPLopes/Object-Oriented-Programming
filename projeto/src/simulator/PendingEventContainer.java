package simulator;

/**
 * 
 * Interface of a Pending Event Container
 * Store a set of events sorted by some sort of parameter (for example: time, etc..)
 * 
 * @author Joao Lopes
 *
 */

public interface PendingEventContainer {
	 
	/**
	 * Add an event to the Event Container
	 * @param event the event to be added
	 */
	public void addEvPEC(Object event);
	/**
	 * Remove an event from the Event Container
	 * @param event the event to be removed
	 */
	public void removeEvPEC(Object event);
	/**
	 * Return the next event form the Even Container.
	 * @return the next event in the container
	 */
	public Object nextEvPEC();
	/**
	 * Check if the object PEC is empty
	 * @return true if the PEC has no elements
	 */
	public boolean isEmpty();
}
