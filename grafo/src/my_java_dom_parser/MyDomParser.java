package my_java_dom_parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class MyDomParser {

	public static Data parser() {
		
		List<Weights> edges = new ArrayList<Weights>();
		Move mov = null;
		Evaporation evap= null;
		Simulation s = null;
		Data grafo = null;
		
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("data1.xml");
			NodeList nodeList = doc.getElementsByTagName("node");
			for (int i=0; i<nodeList.getLength();i++) {
				Node p = nodeList.item(i);
				if (p.getNodeType() == Node.ELEMENT_NODE) {
					Element node = (Element) p;
					String id = node.getAttribute("nodeidx");
					NodeList weightList = node.getChildNodes();
					for (int j = 0; j<weightList.getLength(); j++) {
						Node n = weightList.item	(j);
						if (n.getNodeType() == Node.ELEMENT_NODE) {
							Element weight = (Element) n;
							String w = weight.getAttribute("targetnode");
							String x = weight.getTextContent();
							Weights e = new Weights(Integer.parseInt(id), Integer.parseInt(w),  Integer.parseInt(x));
							edges.add(e);
						}
					}
				}
			}
			
			
			NodeList plevel = doc.getElementsByTagName("simulation");
			for (int i=0; i<plevel.getLength();i++) {
				Node p = plevel.item(i);
				if (p.getNodeType() == Node.ELEMENT_NODE) {
					Element node = (Element) p;
					String level = node.getAttribute("plevel");					
					String ant = node.getAttribute("antcolsize");
					String inst = node.getAttribute("finalinst");
					s = new Simulation(Float.parseFloat(inst), Integer.parseInt(ant),  Float.parseFloat(level));
					}
			}
			
			NodeList move = doc.getElementsByTagName("move");
			for (int i=0; i<move.getLength();i++) {
				Node p = move.item(i);
				if (p.getNodeType() == Node.ELEMENT_NODE) {
					Element node = (Element) p;
					String alpha = node.getAttribute("alpha");
					String beta = node.getAttribute("beta");
					String delta = node.getAttribute("delta");
					mov = new Move(Float.parseFloat(alpha), Float.parseFloat(beta),  Float.parseFloat(delta));

				}
			}
			
			NodeList evaporation = doc.getElementsByTagName("evaporation");
			for (int i=0; i<evaporation.getLength();i++) {
				Node p = evaporation.item(i);
				if (p.getNodeType() == Node.ELEMENT_NODE) {
					Element node = (Element) p;
					String eta = node.getAttribute("eta");
					String rho = node.getAttribute("rho");
					evap = new Evaporation(Float.parseFloat(eta), Float.parseFloat(rho));
				}
			}
			
			NodeList graph = doc.getElementsByTagName("graph");
			for (int i=0; i<graph.getLength();i++) {
				Node p = graph.item(i);
				if (p.getNodeType() == Node.ELEMENT_NODE) {
					Element node = (Element) p;
					String nb = node.getAttribute("nbnodes");
					String nest = node.getAttribute("nestnode");
					grafo = new Data(Integer.parseInt(nb), Integer.parseInt(nest), edges, mov, s, evap);
				}
			}

			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return grafo;
		
	}

}
