package pt5;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class Main {
	
	static File dat = new File("myPeople.dat");
	static File xml = new File("myPeople.xml");
	
	public static void main(String[] args) {
		
		try {
			MetodesSerialitzacio.crearPersones();
		} catch (IOException e) {
			System.out.println("Error escribint les persones a myPeople.dat");
			System.out.println(e.toString());
		}
		
		try {
			MetodesDOM.escriureXML(dat, xml);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}
