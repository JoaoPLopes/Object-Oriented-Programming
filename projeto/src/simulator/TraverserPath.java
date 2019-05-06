package simulator;

import java.util.List;

import exceptions.NonPositive;

public interface TraverserPath {

	public List<Integer> getVisited();
	
	public int getCurrentNode();
	
	public List<Integer> getPathWeights();
	
	public int getTotalPathWeight() throws NonPositive;
	
	public void addVisitedNode(int n);
	
	public void addPathWeight(int weight);
	
	public void setCurrentNode(int n);
	
	public void removeCycle();
	
	public boolean hasHamiltonCycle();
	
	
}
