package pt5;

import java.io.File;
import java.io.IOException;

public class Main {

	// Declarem els dos fitxers que utilitzarem per aquesta practica:
	static File dat = new File("myPeople.dat");
	static File xml = new File("myPeople.xml");

	public static void main(String[] args) {

		// Truquem als metodes que hem creat a la classe MetodesSerialitzacio
		try {
			MetodesSerialitzacio.crearPersones();
		} catch (IOException e) {
			System.out.println("Error escribint les persones a myPeople.dat");
		}

		// Truquem als metodes que hem creat a la classe MetodesDOM
		try {
			MetodesDOM.escriureXML(dat, xml);
			MetodesDOM.imprimeixXML(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
