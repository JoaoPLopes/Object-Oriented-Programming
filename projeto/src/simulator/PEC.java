package simulator;

import java.util.PriorityQueue;


/**
 * 
 * Class: PEC.java
 * Implements PendingEventContainer interface.
 * This class stores in a priority queue a set of events to be simulated. The events are sorted 
 * through their time stamp
 * @author Joao Lopes
 *
 */
public class PEC implements PendingEventContainer {
	
	PriorityQueue<Event> evQueue = new PriorityQueue<Event>(1, new Comparador());
	
	
	/**
	 * Constructs a PEC
	 */
	PEC(){
	}
	
	/**
	 * Add an event to the PEC
	 */
	public void addEvPEC(Object e) {
		evQueue.add((Event) e);
	}
	
	/**
	 * 
	 * @return true if there are no events in the PEC
	 */
	public boolean isEmpty() {
		return evQueue.size()==0;
	}
	
	
	/**
	 * Removes the event e to the PEC
	 */
	public void removeEvPEC(Object e) {
		evQueue.remove((Event) e);
	}
	
	
	/**
	 * Returns the next event in the PEC. The next event is the one with the smaller time stamp.
	 */
	public Event nextEvPEC() {
		return evQueue.poll();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PEC [events=" + evQueue + "]";
	}
	
}
