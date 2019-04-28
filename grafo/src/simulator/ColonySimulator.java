package simulator;


import java.util.LinkedList;
import java.util.List;

import exceptions.NonPositive;
import grafo.Graph;
import my_java_dom_parser.Data;
import my_java_dom_parser.SaxParser;
import my_java_dom_parser.Weights;



public class ColonySimulator {
	
	protected static float simTime;
	protected static double delta;
	protected static int nest;
	protected static int nbnodes;
	protected static int antcolsize;
	protected static float plevel;
	
	static float alfa;
	static float beta;
	static float mean;
	static PEC pec;
	static List<Ant> ants = new LinkedList<Ant>();
	static EvAnt_Move EventMoveClass;
	static EvPhero_Evap EventEvapClass;
	static SaxParser MySax = new SaxParser();


	static double time;
	

	

	public static void main(String[] args) {
		
		Data dados = MySax.MySaxParser("data1.xml"); 
		
		System.out.println("LOLL");
		Graph grafo = new Graph(dados.getNbNodes(), dados.getNest());
		delta = dados.getMove().getDelta();
		nest = dados.getNest();
		nbnodes = dados.getNbNodes();
		alfa = dados.getMove().getAlpha();
		mean = dados.getEvaportaion().getEta();
		beta = dados.getMove().getbeta();
		simTime = dados.getSimulation().getFinalinst();
		//antcolsize = dados.getSimulation().getColonySize();
		plevel = dados.getSimulation().getPlevel();
		antcolsize =1;
		EventReport report= new EventReport(simTime);
		int currentpath=0;
		
		

		
		try {
			for (Weights w :  dados.getEdges()) {
				grafo.newEdge(grafo.getNode(w.getArraival()),grafo.getNode(w.getDeparture()), w.getWeight());
			}
		}
		catch (NonPositive ex) {
			System.out.println(ex.getMessage());
			System.exit(2);
		}
		
		
		
		
		pec = new PEC();

		for (int i=0; i<antcolsize; i++)
			ants.add(new Ant(grafo.getNode(nest),i));


		for (Ant ant: ants)
			pec.addEvPEC(new EvAnt_Move(ExpRandom.expRandom(delta), ant));
		
		Event currentEvent = pec.nextEvPEC();
		
		double currentTime = currentEvent.time_stamp;
		
		
		while (currentTime < simTime) {
			
			if (currentEvent instanceof EvAnt_Move) {
				EventMoveClass=(EvAnt_Move) currentEvent;
				EventMoveClass.simulate(grafo);
				int current_ant = EventMoveClass.getAnt();
				//System.out.println("current ant : "+ current_ant + " hamilton atual " + report.getHamilton() + "\n");
				if(ants.get(current_ant).hasHamiltonCycle(nbnodes+1)) {
					try {
						currentpath=ants.get(current_ant).bestPath(report.getOptimalCycleWeight() ,ants.get(current_ant).calcWeight(ants.get(current_ant).getPath().getPathWeight()));
						ants.get(current_ant).placingPheroSetEvents(currentpath, grafo, ants.get(current_ant).getPath().getVisited(), ants.get(current_ant).getPath().getPathWeight(),  plevel, currentTime, mean, pec );
						if (report.CheckForCycleUpdate(currentpath)) {
							ants.get(current_ant).getPath().getVisited().remove(nbnodes);
							report.setHamilton((List<Integer>) DeepCopy.deepClone(ants.get(current_ant).getPath().getVisited()));
						}
					}
					catch (NonPositive ex) {
						System.out.println("Path weight cannot be negative");
						System.exit(1);
					}
					ants.get(current_ant).removeCycle();
				}
				report.updateReport(currentTime, EventMoveClass);
				if(report.checkTime(currentTime, simTime))
					System.out.println(report);
			}
			else {
				EventEvapClass= (EvPhero_Evap) currentEvent;
				EventEvapClass.simulate(grafo);
				report.updateReport(currentTime, EventEvapClass);
				if(report.checkTime(currentTime, simTime))
					System.out.println(report);
			}
				currentEvent = pec.nextEvPEC();
				currentTime = currentEvent.time_stamp;
		}
		if(report.checkTime(currentTime, simTime))
			System.out.println(report);
		
	}

}
