package simulator;

import java.util.PriorityQueue;

public class PEC {
	
	 PriorityQueue<Event> evQueue = new PriorityQueue<Event>(1, new Comparador());
	
	PEC(){
	}
	
	
	public void addEvPEC(Event e) {
		evQueue.add(e);
	}
	
	public void removeEvPEC(Event e) {
		evQueue.remove(e);
	}
	
	public Event nextEvPEC() {
		return evQueue.poll();
	}


	@Override
	public String toString() {
		return "PEC [events=" + evQueue + "]";
	}
	
}
