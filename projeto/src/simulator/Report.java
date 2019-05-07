package simulator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Class: Report.java
 * A report has fields observations of the simulation. An observation has :
 * 
 * cycle_weight : The weight of the best hamilton cycle found so far.
 * cycle : The list in the best cycle found so far.
 * instant : The number of evaporation events realized so far
 * mevents : The number of move events realized so far
 * eevents : The number of evaporation evensts realized so far
 * spacedtime : The current simulation time
 * 
 * An object of this class is responsible to store the previous parameters and with the method toString() to print them to the terminal
 * 
 * @author Joao Lopes, Gonçalo Carvalho, Alessio Vacca
 *
 */
public class Report {
	
	private int cycle_weight;
	private List<Integer> cycle = new LinkedList<>();
	private int instant;
	private int mevents;
	private int eevents;
	private double spacedtime;
	 
	/**
	 * Constructs an object Report
	 * @param time is the time stamp associated with the first time the report will be printed.
	 */
	Report(double time) {
		this.cycle_weight=0;
		this.eevents = 0;
		this.mevents=0;
		this.instant=1;
		this.spacedtime=time;
	}
	
	
	/**
	 * Getter method for the field cycle_weight
	 * @return the weight of the best cycle found so far
	 */
	public int getOptimalCycleWeight() {
		
		return this.cycle_weight;
	}
	
	/**
	 * Setter method for the field cycle_weight
	 * @param weight is the new cycle_weight
	 */
	public void setOptimalCycleWeight(int weight) {
		this.cycle_weight=weight;
	}
	
	/**
	 * getter method for the field cycle
	 * @return the the cycle stored in the object
	 */
	public List<Integer> getHamilton() {
		
		return this.cycle;
		
	}
	
	/**
	 * Setter method for the field cycle
	 * @param hamilton is a list of integers that represents the new cycle to be stored in the object
	 */
	public void setHamilton(List<Integer> hamilton) {
		
		this.cycle = hamilton;
		
	}
	
	/**
	 * Increments the field instant. The field instant counts the number of times the report was updated.
	 */
	public void updateinstant() {
		
		this.instant ++ ;
	}
	
	/**
	 * This method receives an event and if this event is an ant move event increments the variable that count the
	 * ant move events. If the event is an Evaportaion Event then it increments the variable that counts the 
	 * number of evaporation events.
	 * @param current is the time stamp associated with the current event
	 */
	public void updateReport(Event current){
		
		if (current instanceof EvAnt_Move ) {
			this.mevents++;
		}
		else {
			this.eevents++;
		}
		
	}

	/**
	 * Boolean method that checks if a new given path weight is lower than the best path weight that is currently stored
	 * If the new path weight is lower than it keeps it and returns true. If not thant it just returns false.
	 * @param currentpath is the new cycle weight that we want to check if it is better than the current
	 * @return true if the new weight is lower than the previous.	
	 */
	public boolean CheckForCycleUpdate(int currentpath) {
		
		if (this.cycle_weight==0) {
			this.cycle_weight =currentpath;
			return true;
			
		}
		if (currentpath <= this.cycle_weight) {
			this.cycle_weight = currentpath;
			return true;
		}
		return false;
	}

	/**
	 * Prints an observation to the terminal. The print is done following the guidelines in the project description.
	 */
	@Override
	public String toString() {
		
		return "Observation " + (this.instant) + ":\n \t\t"+
							"Present instant:\t" + this.instant*this.spacedtime +
							"\n\t\t" +"Number of move events: \t" + this.mevents +
							"\n\t\t" +"Number of evaporation events:\t" + this.eevents+
							"\n\t\t" +"Hamiltonian cycle:\t" + Arrays.toString(cycle.toArray()).replace("[", "{").replace("]", "}").replace(", ", ",");
	}

	
	
	
	
	
	
	

}