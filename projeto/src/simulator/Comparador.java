package simulator;

import java.util.Comparator;

public class Comparador implements Comparator<DicreteEvent>{

	public int compare(DicreteEvent l, DicreteEvent r) {	
		if((l.getTimeStamp() - r.getTimeStamp()) > 0) {
			return 1;
		}else if((l.getTimeStamp()  - r.getTimeStamp()) < 0) {
			return -1;
		}else {
			return 0;
		}
	}
}
