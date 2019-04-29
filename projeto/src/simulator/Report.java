package simulator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
// o que é a variavel INSTANT, Não deviamos printar o Hamilton como {1,...5,1}, a ultima ligação conta o peso ??(5,1)
public class Report {
	
	private int cycle_weight;
	private List<Integer> cycle = new LinkedList<>();
	private int instant;
	private int mevents;
	private int eevents;
	private double spacedtime;
	
	Report(double time) {
		this.cycle_weight=0;
		this.eevents = 0;
		this.mevents=0;
		this.instant=1;
		this.spacedtime=time;
	}
	
		
	public int getOptimalCycleWeight() {
		
		return this.cycle_weight;
	}
	
	public void setOptimalCycleWeight(int weight) {
		this.cycle_weight=weight;
	}
	
	public List<Integer> getHamilton() {
		
		return this.cycle;
		
	}
	
	public void setHamilton(List<Integer> hamilton) {
		
		this.cycle = hamilton;
		
	}
	
	public void updateinstant() {
		
		this.instant ++ ;
	}
	
	public void updateReport(double currenttime, Event current){
		
		if (current instanceof EvAnt_Move ) {
			this.mevents++;
		}
		else {
			this.eevents++;
		}
		
	}

	
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

	@Override
	public String toString() {
		
		return "Observation " + (this.instant) + ":\n \t\t"+
							"Present instant:\t" + this.instant*this.spacedtime +
							"\n\t\t" +"Number of move events: \t" + this.mevents +
							"\n\t\t" +"Number of evaporation events:\t" + this.eevents+
							"\n\t\t" +"Hamiltonian cycle:\t" + Arrays.toString(cycle.toArray()).replace("[", "{").replace("]", "}").replace(", ", ",");
	}

	
	
	
	
	
	
	

}