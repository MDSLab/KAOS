package Kaos;
import Kaos.*;
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
	
	    //legge il file xml e ritorna un vector con i Clusters del grafico
	public Vector<String> readXmlParam(String xmlFilePath) 
	{
	    Vector<String> vec=new Vector<String>();

		try {

			File xmlFile = new File(xmlFilePath);

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			Document doc = documentBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nodeList = doc.getElementsByTagName("Parameters");

			System.out.println("===============================================================");
			String no;
			
			
			//do this the old way, because nodeList is not iterable
			for (int itr = 0; itr < nodeList.getLength(); itr++) 
			{

				Node node = nodeList.item(itr);

				System.out.println("\nNode Name :" + node.getNodeName());

				if (node.getNodeType() == Node.ELEMENT_NODE) 
				{

					Element eElement = (Element) node;
					no=new String();

					//System.out.println("Modality : "+ eElement.getAttribute("modality"));
					//no=eElement.getAttribute("modality");
					//vec.add(no);
					
					System.out.println("Modality : "+ eElement.getElementsByTagName("modality").item(0).getTextContent());
					no=eElement.getElementsByTagName("modality").item(0).getTextContent();
					vec.add(no);
										
					System.out.println("Distribution : "+ eElement.getElementsByTagName("distribution").item(0).getTextContent());
					no=eElement.getElementsByTagName("distribution").item(0).getTextContent();
					vec.add(no);
					
					System.out.println("Mu : "+ eElement.getElementsByTagName("mu").item(0).getTextContent());
					no=eElement.getElementsByTagName("mu").item(0).getTextContent();
					vec.add(no);
					
					System.out.println("Eta0: "+ eElement.getElementsByTagName("eta0").item(0).getTextContent());
					no=eElement.getElementsByTagName("eta0").item(0).getTextContent();
					vec.add(no);
					
					System.out.println("Beta0: "+ eElement.getElementsByTagName("beta0").item(0).getTextContent());
					no=eElement.getElementsByTagName("beta0").item(0).getTextContent();
					vec.add(no);
					
					System.out.println("Beta: "+ eElement.getElementsByTagName("beta").item(0).getTextContent());
					no=eElement.getElementsByTagName("beta").item(0).getTextContent();
					vec.add(no);

				}
			}
			
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return vec;
	}

}//end class