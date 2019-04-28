package my_java_dom_parser;

public class Weights {
	private int departure;
	private int arraival;
	private int weight;
	
	Weights(int d, int a){
		this.departure = d;
		this.arraival = a;
	}
	
	public int getDeparture() {
		return this.departure;
	}
	
	public void setWeight(int weight) {
		this.weight=weight;
	}
	
	public int getArraival() {
		return this.arraival;
	}
	
	public int getWeight() {
		return this.weight;
	}

	@Override
	public String toString() {
		return "Edges [departure=" + departure + ", arraival=" + arraival + ", weight=" + weight + "]";
	}
	
	
}
