package my_java_dom_parser;

public class Move {
	static float alpha;
	static float beta;
	static float delta;
	
	Move(float a, float b, float d){
		alpha = a;
		beta = b;
		delta = d;
	}

	public float getAlpha() {
		return alpha;
	}
	
	public float getbeta() {
		return beta;
	}
	
	public float getDelta() {
		return delta;
	}

	@Override
	public String toString() {
		return "Move [alpha=" + alpha + ", beta=" + beta + ", delta=" + delta + "]";
	}


}
