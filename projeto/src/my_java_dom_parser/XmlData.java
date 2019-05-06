package my_java_dom_parser;

import java.util.List;

import exceptions.WrongXLMvalue;

public interface XmlData {

	public int getNbNodes();
	
	public int getNest();
	
	public Object getMove();
	
	public double getDelta();
	
	public double getAlpha();
	
	public double getBeta();
	
	public Object getSimulation();

	public double getFinalinst();
	
	public float getpLevel();
	
	public float getColonySize();
	
	public Object getEvaporation();
	
	public float getRho();
	
	public float getEta();
	
	public void addGraphWeight(int currentWeight);
	
	public int getGraphWeight();
	
	public List<Weights> getEdges();
	
	public void validatedata() throws WrongXLMvalue;
	
}
