package pt2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

public class AP8_GestionarMapsFitxers {
	// Creem un objecte File que sera accessible per aquesta classe que es on es
	// desaran les dades del TreeMap
	static File dadesBotiga = new File("dadesBotiga.txt");
	// Referenciem el TreeMap del programa de la practica 1 perque sigui mes
	// accessible des d'aquesta
	public static TreeMap<String, Float> jocs = AP8_codiPt1.jocs;

	/*
	 * Aquest metode emplena el fitxer amb les dades del TreeMap Retorna true si tot
	 * va be
	 */
	public static boolean emplenarFitxer() throws IOException {
		// Necessitem un iterator per recorre el TreeMap
		Iterator<String> i = jocs.keySet().iterator();
		// Necessitem un float i un String per a guardar els valors del TreeMap
		Float preuJoc;
		String nomJoc;
		// Utilitzarem un filewriter per escriure a l'arxiu les dades
		FileWriter fw;
		fw = new FileWriter(dadesBotiga);
		// Com per configuracio regional se'ns desaran els floats de manera diferent de
		// com es guarden a Java, per tal que no falli el programa hem de declarar un
		// String per a poder substituir els punts (.), per comes (,)
		String preuJocString;
		// Recorrem el TreeMap
		while (i.hasNext()) {
			// Desem el nom del joc en la variable nomJoc
			nomJoc = i.next();
			// Desem el preu en la variable preuJoc
			preuJoc = jocs.get(nomJoc);
			// Fem la operacio de canvi dels punts per comes
			preuJocString = String.valueOf(preuJoc).replace('.', ',');
			// Escribim les dades al fitxer
			fw.write(nomJoc + " " + preuJocString + "\r\n");
		}
		fw.close();
		return true;
	}

	/*
	 * Aquest metode emplena el TreeMap amb les dades del fitxer Retorna true si tot
	 * va be
	 */
	public static boolean emplenarMap() throws FileNotFoundException {
		// Utilitzarem un scanner per a llegir el fitxer
		Scanner scF = new Scanner(dadesBotiga);
		// Guardarem en variables les dades
		String nomJoc;
		Float preuJoc;
		// Recorrerem el contingut del fitxer per agafar les dades
		while (scF.hasNext()) {
			nomJoc = scF.next();
			preuJoc = scF.nextFloat();
			// Si el TreeMap ja conte el joc, no farem res i ho avisarem
			if (jocs.containsKey(nomJoc)) {
				System.out.println("\nEl Map ja conté el joc\n" + nomJoc);
			} else {
				// Si no ho conte, ho guardem al treeMap
				jocs.put(nomJoc, preuJoc);
				System.out.println("Insertat el joc " + nomJoc + " al preu de " + preuJoc + "€ al TreeMap.");
			}
		}
		scF.close();
		return true;
	}

	/*
	 * Aquest metode buida el TreeMap per tal que sigui mes facil probar si el
	 * fitxer desa les dades al TreeMap Retorna true si el TreeMap es buida i no es
	 * buit
	 */
	public static boolean buidarMap() {
		// Si el tree map no es buit, el buidem
		if (!jocs.isEmpty())
			jocs.clear();
		// Si no, retornem false per avisar que era buit ja
		else
			return false;
		return true;
	}

	/*
	 * Aquest metode crea el Fitxer per a gestionar la botiga, Si no existeix, el
	 * crea, i si existeix, no.
	 */
	public static void creaFitxer() throws IOException {
		if (!dadesBotiga.exists()) {
			dadesBotiga.createNewFile();
		}
	}

	public static void main(String[] args) {
		// Cridem a la classe que te el codi de la practica 1 amb totes les funcions d'aquesta
		AP8_codiPt1 m = new AP8_codiPt1();
		boolean notEnd = true;
		try {
			// Primer creem el fitxer per a evitar errors a l'hora d'escriure
			creaFitxer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Mentre que l'usuari no esculli la opcio de sortir, no s'acaba el programa.
		while (notEnd)
			try {
				notEnd = m.inici();
			} catch (IOException e) {
				System.out.println("Hi ha hagut un error a l'hora de manipular el fitxer (llegir o escriure)");
			}
	}

}
