package pt2;

import java.io.File;
import java.io.IOException;

public class AP3_CreaFicheros {
	public static void creaDirectori(File f) {
		f.mkdir();
	}
	
	public static void creaFitxer(File f) {
		try {
			f.createNewFile();
			System.out.println("Fitxer " + f.getName() + " creat.");
		} catch (IOException e) {
			System.out.println("No se ha podido crear el archivo en " + f.getAbsolutePath());
		}
	}
	
	public static void renombraFitxer(File f, File renombrado) {
		String nomAntic = f.getName();
		if(f.renameTo(renombrado)) {
			System.out.println("Fitxer " + nomAntic + " renombrat a " + renombrado.getName());
		}else {
			System.out.println("No s'ha pogut renombrar el fitxer.");
		}
	}
	
	public static void llistaFitxers(File f) {
		if(f.isDirectory()) {
			System.out.println("\n\nFitxers a " + f.getName());
			System.out.println("-----------------------------------");
			String[] files = f.list();
			for(String file : files) {
				System.out.println(file);
			}
			System.out.println("-----------------------------------\n\n");
		}else {
			System.out.println(f.toString() + " no es un directori");
		}
	}
	
	public static void esborraFitxer(File f) {
		if(f.delete()) {
			System.out.println("Fitxer esborrat.");
		}else {
			System.out.println("No s'ha pogut esborrar.");
		}
	}
	
	public static void main(String[] args) {
		creaDirectori(new File("myFiles"));
		creaFitxer(new File("myFiles\\fichero1"));
		creaFitxer(new File("myFiles\\fichero2"));
		renombraFitxer(new File("myFiles\\fichero2"), new File("myFiles\\ficheroRenombrado"));
		llistaFitxers(new File("myFiles"));
		esborraFitxer(new File("myFiles\\fichero1"));
		llistaFitxers(new File("myFiles"));
	}
}
