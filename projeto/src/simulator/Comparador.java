package simulator;

import java.util.Comparator;

public class Comparador implements Comparator<Event>{

	public int compare(Event l, Event r) {	
		if((l.time_stamp - r.time_stamp) > 0) {
			return 1;
		}else if((l.time_stamp - r.time_stamp) < 0) {
			return -1;
		}else {
			return 0;
		}
	}
}
