package DeMeo;
import DeMeo.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.Vector;

public class ReadXMLFile
{

	//public static final String xmlFilePath = "prova.xml";
    
    //legge il file xml e ritorna un vector con i Clusters del grafico
	public Vector<Cluster> readXmlNodi(String xmlFilePath) 
	{
	    Vector<Cluster> vecN=new Vector<Cluster>();

		try {

			File xmlFile = new File(xmlFilePath);

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			Document doc = documentBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nodeList = doc.getElementsByTagName("Cluster");

			System.out.println("===============================================================");
			Cluster no;
			
			
			//do this the old way, because nodeList is not iterable
			for (int itr = 0; itr < nodeList.getLength(); itr++) 
			{

				Node node = nodeList.item(itr);

				System.out.println("\nNode Name :" + node.getNodeName());

				if (node.getNodeType() == Node.ELEMENT_NODE) 
				{

					Element eElement = (Element) node;
					no=new Cluster();

					System.out.println("Cluster id : "+ eElement.getAttribute("id"));
					no.setId(eElement.getAttribute("id"));
										
					System.out.println("Label : "+ eElement.getElementsByTagName("label").item(0).getTextContent());
					no.setLabel(eElement.getElementsByTagName("label").item(0).getTextContent());
					
					System.out.println("Probability : "+ eElement.getElementsByTagName("probability").item(0).getTextContent());
					no.setProb(eElement.getElementsByTagName("probability").item(0).getTextContent());
					
					System.out.println("B(h,k,i) : "+ eElement.getElementsByTagName("matrix").item(0).getTextContent());
					no.setMatrix(eElement.getElementsByTagName("matrix").item(0).getTextContent());
					
					vecN.add(no);

				}
			}
			
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return vecN;
	}
	

}//end class