package my_java_dom_parser;

public class Evaporation {
	
	private float eta;
	private float rho;
	
	Evaporation(float e, float r){
		this.eta = e;
		this.rho = r;
	}
	
	public float getEta() {
		return this.eta;
	}
	
	public float getRho() {
		return this.rho;
	}

	@Override
	public String toString() {
		return "Evaportaion [eta=" + eta + ", rho=" + rho + "]";
	}
}