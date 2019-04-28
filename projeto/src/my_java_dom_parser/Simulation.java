package my_java_dom_parser;

public class Simulation {
	private float finalinst;
	private int antcolsize;
	private float plevel;
	
	Simulation(float f, int a, float p){
		this.finalinst = f;
		this.antcolsize = a;
		this.plevel = p;
	}
	
	public float getFinalinst() {
		return this.finalinst;
	}
	
	public int getColonySize() {
		return this.antcolsize;
	}
	
	public float getPlevel() {
		return this.plevel;
	}

	@Override
	public String toString() {
		return "Simulation [finalinst=" + finalinst + ", antcolsize=" + antcolsize + ", plevel=" + plevel + "]";
	}
	
}
