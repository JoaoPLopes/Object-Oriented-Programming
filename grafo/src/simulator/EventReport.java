package simulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
// o que é a variavel INSTANT, Não deviamos printar o Hamilton como {1,...5,1}, a ultima ligação conta o peso ??(5,1)
public class EventReport {
	
	private int cycle_weight;
	private static final int INTERVALO = 20;
	private List<Integer> cycle = new LinkedList<>();
	private List<Double> reporttime = new ArrayList<>();
	private int controlReportTime;
	private int mevents;
	private int eevents;
	private double instant;
	
	EventReport(double timestamp) {
		this.cycle_weight=0;
		this.eevents = 0;
		this.mevents=0;
		this.instant=0.0;
		this.controlReportTime=0;
		for (int i=1; i<=INTERVALO ; i++)
			this.reporttime.add((timestamp*i)/INTERVALO);
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
	
	public void updateReport(double currenttime, Event current){
		
		this.instant = currenttime;
		if (current instanceof EvAnt_Move ) {
			this.mevents++;
		}
		else {
			this.eevents++;
		}
		
	}
	
	public boolean checkTime(double currenttime, double timestamp) {
		if (currenttime > timestamp) {
			this.controlReportTime= 20;
			return true;
		}
		if (currenttime >= this.reporttime.get(this.controlReportTime) && currenttime < this.reporttime.get(this.controlReportTime+1)) {
			this.controlReportTime++;
			return true;
		}
		else
			return false;
		
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
		
		return "Observation " + (this.controlReportTime) + ":\n \t\t"+
							"Present instant:\t" + this.reporttime.get(this.controlReportTime-1) +
							"\n\t\t" +"Number of move events: \t" + this.mevents +
							"\n\t\t" +"Number of evaporation events:\t" + this.eevents+
							"\n\t\t" +"Hamiltonian cycle:\t" + Arrays.toString(cycle.toArray()).replace("[", "{").replace("]", "}").replace(", ", ",");
	}

	
	
	
	
	
	
	

}
