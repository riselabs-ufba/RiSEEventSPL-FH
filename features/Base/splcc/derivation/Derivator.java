package rise.splcc.derivation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import rise.splcc.data.Activity;

public class Derivator {
	
	
	private static String fileName = "Build_Complete_Product.xml";
	private static List<Feature> featureList = readXML(fileName);
	

//	public static void main(String[] args) {
//		String fileName = "Build_Complete_Product.xml";
//        List<Feature> featureList = readXML(fileName);
//        for(Feature feature : featureList){
//            System.out.println(feature.toString());
//        }
//	}
	
	private static Derivator instanceDerivator;
	public static Derivator getInstanceDerivator() {
		if (instanceDerivator == null) {
			Derivator.instanceDerivator = new Derivator();
		}
		return Derivator.instanceDerivator;
	}
	
	public List<Feature> getFeatureList(){
		return this.featureList;
	}
	
	public String getStatus(String featureName){
		for(Feature feature : featureList){
			if(feature.getName().equals(featureName))
				return feature.getStatus();
		}
		return null;
	}
	
	private static List<Feature> readXML(String fileName) {
        List<Feature> featureList = new ArrayList<>();
        Feature feature = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            while(xmlEventReader.hasNext()){
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
               if (xmlEvent.isStartElement()){
                   StartElement startElement = xmlEvent.asStartElement();
                   if(startElement.getName().getLocalPart().equals("property")){
                       feature = new Feature();

                       Attribute nameAttr = startElement.getAttributeByName(new QName("name"));
                       if(nameAttr != null){
                    	  feature.setName(nameAttr.getValue());
                       }
                       Attribute statusAttr = startElement.getAttributeByName(new QName("value"));
                       if(statusAttr != null){
                    	  feature.setStatus(statusAttr.getValue());
                       }
                   }
                   
               }
               //if Employee end element is reached, add employee object to list
               if(xmlEvent.isEndElement()){
                   EndElement endElement = xmlEvent.asEndElement();
                   if(endElement.getName().getLocalPart().equals("property")){
                       featureList.add(feature);
                   }
               }
            }
             
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return featureList;
    }
	

	
	public void modifyXML() {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		try {
			docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(fileName);

			int qtdFeatures = featureList.size();
			for(int i =0; i<qtdFeatures ; i++){
				Node featureProperty = doc.getElementsByTagName("property").item(i);
				NamedNodeMap attr = featureProperty.getAttributes();
				Node nodeAttrStatus = attr.getNamedItem("value");
				nodeAttrStatus.setTextContent(featureList.get(i).getStatus());
				
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(fileName));
			transformer.transform(source, result);


		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
			
   
    }
	
	 

}
