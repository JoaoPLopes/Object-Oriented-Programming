package my_java_dom_parser;

public class Weights {
	int departure;
	int arraival;
	int weight;
	
	Weights(int d, int a){
		departure = d;
		arraival = a;
	}
	
	public int getDeparture() {
		return departure;
	}
	
	public void setWeight(int _weight) {
		weight=_weight;
	}
	
	public int getArraival() {
		return arraival;
	}
	
	public int getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return "Edges [departure=" + departure + ", arraival=" + arraival + ", weight=" + weight + "]";
	}
	
	
}
