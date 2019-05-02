package simulator;

public class EvReport extends Event {
	
	private static int instant = 1;
	private double reportincrements;
	
	
	EvReport(double time){
		super(time);
		reportincrements = ColonySimulator.reportincrements;
	}
	 
	public void simulate() {
		instant ++;
		if (!(reportincrements*instant > ColonySimulator.dados.getSimulation().getFinalinst()))
			ColonySimulator.pec.addEvPEC(new EvReport(reportincrements*instant));
	}

	@Override
	public String toString() {
		
		return "EvReport [reportincrements=" + reportincrements + "    "+ super.time_stamp + "]";
	}


}