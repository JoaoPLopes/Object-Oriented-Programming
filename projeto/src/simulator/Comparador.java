package simulator;

import java.util.Comparator;
/**
 * 
 * Public Class: Comparador.Java
 * This class implements the interface comparator from java.util
 * The comparator is used to compare two objects that implements the Interface DiscreteEvent.
 * Two Discrete Events are compared through their time stamp. A lower Discrete Event has a lower time stamp.
 * 
 * @author Joao Lopes, Gonçalo Carvalho, Alessio Vacca
 *
 */
public class Comparador implements Comparator<DicreteEvent>{

	/**
	 * Implementation of the method compare from the interface Comparator. 
	 * Two Discrete Events are compared through their time stamp.
	 * 
	 * @param l is one of the objects DiscreteEvent to be compared 
	 * @param r is the other object DiscreteEvent to be compared 
	 */
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
