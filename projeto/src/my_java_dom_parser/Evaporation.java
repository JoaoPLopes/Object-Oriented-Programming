package my_java_dom_parser;

public class Evaporation {
	
	static float eta;
	static float rho;
	
	Evaporation(float e, float r){
		eta = e; 
		rho = r;
	}
	
	public float getEta() {
		return eta;
	} 
	
	public float getRho() {
		return rho;
	}

	@Override
	public String toString() {
		return "Evaportaion [eta=" + eta + ", rho=" + rho + "]";
	}
}