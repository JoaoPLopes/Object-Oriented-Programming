package simulator;

import exceptions.EdgeNextMoveException;

/**
 * Interface of a graph traverser. A graph traverser can move through a graph an store 
 * the path taken as well as the current node;
 * 
 * @author Joao Lopes, Gonçalo Carvalho, Alessio Vacca
 *
 */

public interface Traverser {
	
	
	/**
	 *  move the traverser from the current node to the next node
	 * @param nextNode is the next node of the traverser 
	 */
	public void move(int nextNode);

	/**
	 * This method returns the next node where the traverser is going to move
	 * @return an integer representing the node where the ant is going to move
	 * @throws EdgeNextMoveException if the next node has an invalid index
	 */
	public int chooseNextNode() throws EdgeNextMoveException;
	
	/**
	 * Returns a the traverser path
	 * @return an object path containing all the visited nodes nodes
	 */
	public TraverserPath getPath();
	
}
