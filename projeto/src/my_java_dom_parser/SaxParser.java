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
import org.xml.sax.SAXParseException;

public class SaxParser extends DefaultHandler {
	
		List<Weights> edges;
		private static final String SIMULATION="simulation";
		private static final String FINALINST="finalinst";
		private static final String ANTCOLSIZE="antcolsize";
		private static final String PLEVEL="plevel";
		private static final String GRAPH="graph";
		private static final String NBNODES="nbnodes";
		private static final String NESTNODE="nestnode";
		private static final String MOVE="move";
		private static final String ALPHA="alpha";
		private static final String BETA="beta";
		private static final String DELTA="delta";
		private static final String EVAPORATION="evaporation";
		private static final String ETA="eta";
		private static final String RHO="rho";
		private static final String NODE="node";
		private static final String NODEIDX="nodeidx";
		private static final String WEIGHT="weight";
		private static final String TARGETNODE="targetnode";
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
	            factory.setValidating(true);
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
		    	
		if (elementName.equalsIgnoreCase(SIMULATION)) {
			simul = new Simulation(Float.parseFloat(attributes.getValue(FINALINST)),Integer.parseInt(attributes.getValue(ANTCOLSIZE)),Float.parseFloat(attributes.getValue(PLEVEL)));		
		}
		
		if (elementName.equalsIgnoreCase(GRAPH)) {
		    nbnodes = Integer.parseInt(attributes.getValue(NBNODES));
		    nest_node = Integer.parseInt(attributes.getValue(NESTNODE));
		} 
		    	
		if (elementName.equalsIgnoreCase(MOVE)) {

				mov = new Move(Float.parseFloat(attributes.getValue(ALPHA)),Float.parseFloat(attributes.getValue(BETA)),Float.parseFloat(attributes.getValue(DELTA)));
		}

		if (elementName.equalsIgnoreCase(EVAPORATION)) {
		    evap = new Evaporation(Float.parseFloat(attributes.getValue(ETA)),Float.parseFloat(attributes.getValue(RHO)));
		}
		    	
		if (elementName.equalsIgnoreCase(NODE)) {
		    nodeindex = attributes.getValue(NODEIDX);
		}
		        // if current element is publisher
		if (elementName.equalsIgnoreCase(WEIGHT)) {
		    tempweight = new Weights(Integer.parseInt(nodeindex), Integer.parseInt(attributes.getValue(TARGETNODE)));
		}
		        
		        
	}
		    
	public void endElement(String s, String s1, String element) throws SAXException {
		        // if end of book element add to list
		if (element.equalsIgnoreCase(WEIGHT)) {
		    tempweight.setWeight(Integer.parseInt(tmpValue));
		    graphtotalweight+=Integer.parseInt(tmpValue);
		    edges.add(tempweight);
		    
		}
		
		if (element.equalsIgnoreCase(SIMULATION)) {
		    grafo = new Data(nbnodes, nest_node, edges, mov,  simul , evap);
		    grafo.addGraphWeight(graphtotalweight);
		}
		        

	}
		    
	public void characters(char[] ac, int i, int j) throws SAXException {
		tmpValue = new String(ac, i, j);
	}
	
	public void warning(SAXParseException e)throws SAXParseException{
		System.out.println("Warning! "+ e.getMessage());
	}

	public void error(SAXParseException e)throws SAXParseException{
		System.out.println("Error! "+ e.getMessage());
		System.exit(-2);
	}

	public void fatalError(SAXParseException e) throws SAXParseException{
		System.out.println("Fatal error! "+ e.getMessage()+"\nAbortando");
		System.exit(-1);
	}

		    
}

