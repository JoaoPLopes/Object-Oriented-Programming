package my_java_dom_parser;

public class Move {
	private float alpha;
	private float beta;
	private float delta;
	
	Move(float a, float b, float d){
		this.alpha = a;
		this.beta = b;
		this.delta = d;
	}

	public float getAlpha() {
		return this.alpha;
	}
	
	public float getbeta() {
		return this.beta;
	}
	
	public float getDelta() {
		return this.delta;
	}

	@Override
	public String toString() {
		return "Move [alpha=" + alpha + ", beta=" + beta + ", delta=" + delta + "]";
	}


}
