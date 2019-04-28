package simulator;


public abstract class Event {
	protected double time_stamp;
	protected double mean = ColonySimulator.delta;
	protected float alfa = ColonySimulator.alfa;
	protected float beta = ColonySimulator.beta;
	protected int nbnodes = ColonySimulator.nbnodes;
	protected double plevel = ColonySimulator.plevel;
	
	protected PEC pec = ColonySimulator.pec;
	
	Event(double time){
		time_stamp = time;	
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(mean);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(time_stamp);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (Double.doubleToLongBits(mean) != Double.doubleToLongBits(other.mean))
			return false;
		if (Double.doubleToLongBits(time_stamp) != Double.doubleToLongBits(other.time_stamp))
			return false;
		return true;
	}
	
	
	

}
