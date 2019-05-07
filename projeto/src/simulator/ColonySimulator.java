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
	

	public static void main(String[] args) {
		
		
		dados = MySax.MySaxParser(args[0]); 
		
		try {
			dados.validatedata();
		} catch (WrongXLMvalue ex) {
			// TODO Auto-generated catch block
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
		
		while (currentTime < dados.getFinalinst()) {
			
			if (currentEvent instanceof EvAnt_Move) {
				
				currentEvent.simulate();
				
				Ant current_ant = (Ant) ((EvAnt_Move) currentEvent).getAnt();
				
				if(current_ant.getPath().hasHamiltonCycle()) {
					
					try {
						currentPathWeight = current_ant.getPath().getTotalPathWeight();
						current_ant.placingPheroSetEvents(currentPathWeight, currentTime );
						
						if (report.CheckForCycleUpdate(currentPathWeight)) {
							current_ant.getPath().getVisited().remove(dados.getNbNodes());
							current_ant.getPath().getPathWeights().remove(dados.getNbNodes()-1);
							report.setHamilton((List<Integer>) deepClone(current_ant.getPath().getVisited()));
						}
					}
					
					catch (NonPositive ex) {
						System.out.println("Path weight cannot be negative");
						System.exit(1);
					}
					
					current_ant.getPath().removeCycle();
					
					try {
						int newNextNode = current_ant.chooseNextNode();
						
						int weight = grafo.getEdge(current_ant.getPath().getCurrentNode(), newNextNode).getWeight();
						
						double t = currentTime + Event.expRandom(dados.getDelta() * weight );
							if(t<dados.getFinalinst())
								pec.addEvPEC(new EvAnt_Move(t, current_ant, newNextNode));
					}
					
					catch (EdgeNextMoveException ex) {
						System.out.println(ex.getMessage());
						System.exit(-1);
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