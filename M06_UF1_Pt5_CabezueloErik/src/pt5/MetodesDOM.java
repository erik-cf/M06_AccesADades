package pt5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MetodesDOM {

	static ArrayList<Persona> aLP;

	public static void rebreArray(ArrayList<Persona> aLP) {
		MetodesDOM.aLP = aLP;
	}

	public static void escriureXML(File xml, File dat) throws ParserConfigurationException, IOException, ClassNotFoundException, TransformerException {
		FileInputStream fis = new FileInputStream(dat);
		ObjectInputStream ois = new ObjectInputStream(fis);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Persona p;
		Document doc = db.newDocument();

		Element arrel = doc.createElement("persones");
		doc.appendChild(arrel);
		
		Element persona;
		Element nom;
		Element edat;
		while (ois.available() != 0) {
			persona = doc.createElement("persona");
			arrel.appendChild(persona);
			p = (Persona) ois.readObject();

			nom = doc.createElement("nom");
			nom.appendChild(doc.createTextNode(p.getNom()));
			persona.appendChild(nom);
			
			edat = doc.createElement("edat");
			edat.appendChild( doc.createTextNode( String.valueOf( p.getEdat() ) ) );
			persona.appendChild(edat);
		}
		ois.close();
		
		
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = tf.newTransformer();
		DOMSource doms = new DOMSource(doc);
		StreamResult streamR = new StreamResult(xml);
		
		t.transform(doms, streamR);
		
	}
	
	public static void imprimeixXML(File xml) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(xml);
		doc.getDocumentElement().normalize();
		
		NodeList nList = doc.getElementsByTagName("persona");
		System.out.println("Numero persones: " + nList.getLength());
		for(int i = 0; i < nList.getLength(); i++) {
			
		}
	}
}
