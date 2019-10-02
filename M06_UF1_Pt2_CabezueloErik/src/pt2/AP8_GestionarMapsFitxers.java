package pt2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

public class AP8_GestionarMapsFitxers {

	static File dadesBotiga = new File("dadesBotiga.txt");
	public static TreeMap<String, Float> jocs = AP8_codiPt1.jocs;

	public static boolean emplenarFitxer() throws IOException {
		Iterator<String> i = jocs.keySet().iterator();
		String nomJoc;
		FileWriter fw;
		fw = new FileWriter(dadesBotiga);
		Float preuJoc;
		String preuJocString;
		while (i.hasNext()) {
			nomJoc = i.next();
			preuJoc = jocs.get(nomJoc);
			preuJocString = String.valueOf(preuJoc).replace('.', ',');
			fw.write(nomJoc + " " + preuJocString + "\r\n");
		}
		fw.close();
		return true;
	}

	public static boolean emplenarMap() throws FileNotFoundException {
		Scanner scF = new Scanner(dadesBotiga);
		String nomJoc;
		Float preuJoc;
		while(scF.hasNext()) {
			nomJoc = scF.next();
			preuJoc = scF.nextFloat();
			if(jocs.containsKey(nomJoc)) {
				System.out.println("\nEl Map ja conté el joc\n" + nomJoc);
			}else {
				jocs.put(nomJoc, preuJoc);
				System.out.println("Insertat el joc " + nomJoc + " al preu de " + preuJoc + "€ al TreeMap.");
			}
		}
		scF.close();
		return true;
	}

	public static boolean buidarMap() {
		if(!jocs.isEmpty())
			jocs.clear();
		else
			return false;
		return true;
	}

	public static void creaFitxer() throws IOException {
		if (!dadesBotiga.exists()) {
			dadesBotiga.createNewFile();
		}
	}

	public static void main(String[] args) {
		
		AP8_codiPt1 m = new AP8_codiPt1();
		boolean notEnd = true;
		try {
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
