package my_java_dom_parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParser extends DefaultHandler {
	
		List<Weights> edges; 
		Move mov;
		Evaporation evap;
		Simulation simul ;
		Data grafo;
		String xmlFileName;
		Weights tempweight;
		String nodeindex;
		String tmpValue;
		int nbnodes, nest_node, graphtotalweight;
		
		public SaxParser() {};
		
	public Data MySaxParser(String xmlFileName) {
		
	        this.xmlFileName = xmlFileName;
	        edges = new ArrayList<Weights>();
	        parseDocument();
	    	return grafo;
	    }
	
	private void parseDocument() {
		        // parse
		        SAXParserFactory factory = SAXParserFactory.newInstance();
		        try {
		            SAXParser parser = factory.newSAXParser();
		            parser.parse(xmlFileName, this);
		        } catch (ParserConfigurationException e) {
		            System.out.println("ParserConfig error");
		        } catch (SAXException e) {
		            System.out.println("SAXException : xml not well formed");
		        } catch (IOException e) {
		            System.out.println("IO error");
		        }
	
	}
	
		    
	public void startElement(String s, String s1, String elementName, Attributes attributes) throws SAXException {
		    	
		if (elementName.equalsIgnoreCase("simulation")) {
			simul = new Simulation(Float.parseFloat(attributes.getValue("finalinst")),Integer.parseInt(attributes.getValue("antcolsize")),Float.parseFloat(attributes.getValue("plevel")));		
		}
		
		if (elementName.equalsIgnoreCase("graph")) {
		    nbnodes = Integer.parseInt(attributes.getValue("nbnodes"));
		    nest_node = Integer.parseInt(attributes.getValue("nestnode"));
		} 
		    	
		if (elementName.equalsIgnoreCase("move")) {

				mov = new Move(Float.parseFloat(attributes.getValue("alpha")),Float.parseFloat(attributes.getValue("beta")),Float.parseFloat(attributes.getValue("delta")));
		}

		if (elementName.equalsIgnoreCase("evaporation")) {
		    evap = new Evaporation(Float.parseFloat(attributes.getValue("eta")),Float.parseFloat(attributes.getValue("rho")));
		}
		    	
		if (elementName.equalsIgnoreCase("node")) {
		    nodeindex = attributes.getValue("nodeidx");
		}
		        // if current element is publisher
		if (elementName.equalsIgnoreCase("weight")) {
		    tempweight = new Weights(Integer.parseInt(nodeindex), Integer.parseInt(attributes.getValue("targetnode")));
		}
		        
		        
	}
		    
	public void endElement(String s, String s1, String element) throws SAXException {
		        // if end of book element add to list
		if (element.equalsIgnoreCase("weight")) {
		    tempweight.setWeight(Integer.parseInt(tmpValue));
		    graphtotalweight+=Integer.parseInt(tmpValue);
		    edges.add(tempweight);
		    
		}
		
		if (element.equalsIgnoreCase("simulation")) {
		    grafo = new Data(nbnodes, nest_node, edges, mov,  simul , evap);
		    grafo.addGraphWeight(graphtotalweight);
		}
		        

	}
		    
	public void characters(char[] ac, int i, int j) throws SAXException {
		tmpValue = new String(ac, i, j);
	}
		    
}

