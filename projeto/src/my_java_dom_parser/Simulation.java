package my_java_dom_parser;

public class Simulation {
	static float finalinst;
	static int antcolsize;
	static float plevel;
	
	Simulation(float f, int a, float p){
		finalinst = f;
		antcolsize = a;
		plevel = p;
	}
	
	public float getFinalinst() {
		return finalinst;
	}
	
	public int getColonySize() {
		return antcolsize;
	}
	
	public float getPlevel() {
		return plevel;
	}

	@Override
	public String toString() {
		return "Simulation [finalinst=" + finalinst + ", antcolsize=" + antcolsize + ", plevel=" + plevel + "]";
	}
	
}
