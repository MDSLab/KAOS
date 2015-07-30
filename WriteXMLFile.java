package DeMeo;
import DeMeo.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.*; 
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
 
public class WriteXMLFile 
{
 
	public  void writeXML(Vector<Cluster> n, String nomefile) 
	{
 
	  try 
	  {
 
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("Graph");
		doc.appendChild(rootElement);
		
		for (int i=0;i<n.size();i++)
		{
	 
			// Node elements
			Element node = doc.createElement("Cluster");
			rootElement.appendChild(node);
	 
			//set attribute to node element
			Attr attr = doc.createAttribute("id");
		    attr.setValue(n.get(i).getId());
			node.setAttributeNode(attr);
	 
			// shorten way
			//node.setAttribute("id", "1");
	 
		
	 
			// label elements
			Element label = doc.createElement("label");
			label.appendChild(doc.createTextNode(n.get(i).getLabel()));
			node.appendChild(label);
	 
		
	 
			// matrix elements
			Element matrix = doc.createElement("matrix");
			matrix.appendChild(doc.createTextNode(n.get(i).getMatrix()));
			node.appendChild(matrix);
			
				// probability elements
			Element prob = doc.createElement("probability");
			prob.appendChild(doc.createTextNode(n.get(i).getProb()));
			node.appendChild(prob);
		
	    }

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(nomefile+".xml"));
 
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
 
		transformer.transform(source, result);
 
		System.out.println("File saved!");
 
	  } 
	  catch 
	  (ParserConfigurationException pce) 
	  {
		pce.printStackTrace();
	  } 
	  catch 
	  (TransformerException tfe) 
	  {
		tfe.printStackTrace();
	  }
	}
}