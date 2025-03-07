package simulator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import exceptions.EdgeNextMoveException;
import exceptions.NonPositive;
import exceptions.WrongXLMvalue;
import grafo.Graph;
import grafo.WeightedGraph;
import my_java_dom_parser.SaxParser;
import my_java_dom_parser.Weights;
import my_java_dom_parser.XmlData;


/**
 * Class: Ant.java
 * 
 * This class has the Ant Colony Simulator. The Simulator is the main method of this class and consists in a cycle where 
 * in each iteration it is simulated the next event in the PEC.
 * 
 * The Simulator is running while the simulation time is lower than the final instant given by the XML parameters.
 * This class has as static fields the data from the XML file, the PEC, a list of ants and the graph. It also has 
 * as static field the simulation time and the number of times the observations are going to be printed.
 * 
 * In the main method the sequence of operations are. First the XML file is read than a graph is created with the XML information. 
 * Also with the XML information an Ant Colony is created. An ant colony consists in a LinkedList of objects Ant. Finally the
 * simulation begins. As explained before the simulation is a while loop that simulates the next event in the PEC and stops when the 
 * simulation time reaches the final instant from the XML file
 * 
 * @author Joao Lopes, Gon�alo Carvalho, Alessio Vacca
 *
 */
public class ColonySimulator {
	
	protected static int antcolsize;
	
	static WeightedGraph grafo;
	static XmlData dados;
	static PendingEventContainer pec;
	static List<Traverser> ants = new LinkedList<Traverser>();
	
	
	static SaxParser MySax = new SaxParser();
	static final int SPACEDVALUES =20;
	static double reportincrements;
	static double time;
	
	/**
	 * The main mehtod is the Simulator. A simulation is a while loop that simulates the next event in the PEC and stops when the 
	 * simulation time reaches the final instant from the XML file.
	 * 
	 * 
	 * @param args is an XML file with a description of the parameters of the simulator as well as the graph.
	 */
	public static void main(String[] args) {
		
		
		dados = MySax.MySaxParser(args[0]); 
		
		try {
			dados.validatedata();
		} catch (WrongXLMvalue ex) {
			ex.printStackTrace();
			System.exit(1);
		}
		
		grafo = new Graph(dados.getNbNodes());
		
		reportincrements = dados.getFinalinst()/SPACEDVALUES;
		
		Report report = new Report(reportincrements);
		
		int currentPathWeight = 0;
		
		for (Weights w :  dados.getEdges()) {
			grafo.addEdge(w.getDeparture(), w.getArraival(), w.getWeight());
		}
		
		for ( int i = 1 ; i<=dados.getNbNodes() ; i++) {
			  
			if (grafo.adjacentEdges(i).size()<=1 && dados.getNbNodes()>2) {
				System.out.println("For the current graph there are no hamilton cycles");
				System.exit(6);
			}	
		}
		
		
		pec = new PEC();

		for (int i=0; i<dados.getColonySize(); i++)
			ants.add(new Ant(dados.getNest()));

		try {
			
			for (Traverser ant: ants) {	
				
				int nextNode = ant.chooseNextNode();
				
				int weight = grafo.getEdge(ant.getPath().getCurrentNode(), nextNode).getWeight();
				
				pec.addEvPEC(new EvAnt_Move(Event.expRandom(dados.getDelta()*weight), (Ant) ant, nextNode));	
			}
		}
		
		catch (EdgeNextMoveException ex) {
			System.out.println(ex.getMessage());
			System.exit(3);
		}

		
		
		EvReport evreport = new EvReport(reportincrements);
		pec.addEvPEC(evreport);
		
		Event currentEvent = (Event) pec.nextEvPEC();
		
		double currentTime = currentEvent.time_stamp;
		
		Ant current_ant;
		
		while (currentTime < dados.getFinalinst()) {
			
			if (currentEvent instanceof EvAnt_Move) {
				
				currentEvent.simulate();
				
				current_ant = ((EvAnt_Move) currentEvent).getAnt();
				
				if(current_ant.getPath().hasHamiltonCycle()) {
					
					
						currentPathWeight = current_ant.getPath().getTotalPathWeight();
						current_ant.placingPheroSetEvents(currentPathWeight, currentTime );
						
						if (report.CheckForCycleUpdate(currentPathWeight)) {
							current_ant.getPath().getVisited().remove(dados.getNbNodes());
							current_ant.getPath().getPathWeights().remove(dados.getNbNodes()-1);
							report.setHamilton((List<Integer>) deepClone(current_ant.getPath().getVisited()));
						}
					
					
					current_ant.getPath().removeCycle();
						int newNextNode = current_ant.chooseNextNode();
						
						int weight = grafo.getEdge(current_ant.getPath().getCurrentNode(), newNextNode).getWeight();
						
						double t = currentTime + Event.expRandom(dados.getDelta() * weight );
							if(t<dados.getFinalinst()) {
								pec.addEvPEC(new EvAnt_Move(t, current_ant, newNextNode));
							}
					
					
				}
				report.updateReport(currentEvent);
			}
			
			else if (currentEvent instanceof EvPhero_Evap) {
				currentEvent.simulate();
				report.updateReport(currentEvent);
			}
			
			else {
				currentEvent.simulate();
				System.out.println(report);
				report.updateinstant();
			}
			
			if(!pec.isEmpty()) {	
				currentEvent = (Event) pec.nextEvPEC();
				currentTime = currentEvent.time_stamp;
			}
					
		}
		
		currentEvent.simulate();
		System.out.println(report);
		report.updateinstant();
		System.out.println(pec);
	}
	
	
	/**
	 *
	 * Static method used in this class to copy the value of an object to another. Only the value of the object is 
	 * passed not the reference.
	 * 
	 * @param object is an object which value we want to pass to other object.
	 * @return the value of the object
	 */
	
	public static Object deepClone(Object object) {
	    try {
	      ByteArrayOutputStream baos = new ByteArrayOutputStream();
	      ObjectOutputStream oos = new ObjectOutputStream(baos);
	      oos.writeObject(object);
	      ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
	      ObjectInputStream ois = new ObjectInputStream(bais);
	      return ois.readObject();
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      return null;
	    }
	  }
}