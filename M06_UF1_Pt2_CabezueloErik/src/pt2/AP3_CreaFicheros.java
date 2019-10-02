package pt2;

import java.io.File;
import java.io.IOException;

public class AP3_CreaFicheros {

	/*
	 * Aquest metode crea un directori amb la ruta que tingui l'objecte File f que
	 * es passa per parametre:
	 */
	public static void creaDirectori(File f) {
		// Utilitzem el metode mkdir() per crear el directori
		f.mkdir();
	}

	/*
	 * Aquest metode crea un fitxer amb la ruta que tingui l'objecte File f que es
	 * passa per parametre:
	 */
	public static void creaFitxer(File f) {
		try {
			// Creem el fitxer
			f.createNewFile();
			// Mostrem missatge de que s'ha creat:
			System.out.println("Fitxer " + f.getName() + " creat.");
		} catch (IOException e) {
			// Si no es pot crear mostra un error:
			System.out.println("No se ha podido crear el archivo en " + f.getAbsolutePath());
		}
	}

	/*
	 * Aquest metode li canvia el nom del fitxer que se li passa com primer
	 * parametre, pel nom del fitxer del segon parametre
	 */
	public static void renombraFitxer(File f, File renombrado) {
		// Guardem el nom antic en un string per poder imprimir-ho despres
		String nomAntic = f.getName();
		// Si ho renombra mostra un missatge
		if (f.renameTo(renombrado)) {
			System.out.println("Fitxer " + nomAntic + " renombrat a " + renombrado.getName());
		} else {
			// Si no, mostra un error
			System.out.println("No s'ha pogut renombrar el fitxer.");
		}
	}

	/*
	 * Aquest metode llista tots els fitxers que hi hagi a la ruta que se li passi
	 * per parametre (sempre que sigui un directori) dins del File f
	 */
	public static void llistaFitxers(File f) {
		// Si la ruta es un directori, entra a la condicio
		if (f.isDirectory()) {
			System.out.println("\n\nFitxers a " + f.getName());
			System.out.println("-----------------------------------");
			// Creem un array d'Strings a partir de l'array que ens retorna el metode llist
			// de la clase File amb el nom de tots els arxius del directori
			String[] files = f.list();
			// Recorrem l'array de Strings amb els noms dels fitxers i printem cada cas:
			for (String file : files) {
				System.out.println(file);
			}
			System.out.println("-----------------------------------\n\n");
		} else {
			// Si la ruta que se li pasa no es un directori, mostra un error
			System.out.println(f.toString() + " no es un directori");
		}
	}

	/*
	 * Aquest metode esborra el fitxer que se li passi per parametre
	 */
	public static void esborraFitxer(File f) {
		// Si ho aconsegueix esborrar, mostra un missatge
		if (f.delete()) {
			System.out.println("Fitxer esborrat.");
		} else {
			// Si no, mostra un error
			System.out.println("No s'ha pogut esborrar.");
		}
	}

	/*
	 * El metode main crida a tots els metodes de la classe
	 */
	public static void main(String[] args) {
		// Cridem a tots els metodes amb l'ordre que surten a la practica
		creaDirectori(new File("myFiles"));
		creaFitxer(new File("myFiles\\fichero1"));
		creaFitxer(new File("myFiles\\fichero2"));
		renombraFitxer(new File("myFiles\\fichero2"), new File("myFiles\\ficheroRenombrado"));
		llistaFitxers(new File("myFiles"));
		esborraFitxer(new File("myFiles\\fichero1"));
		llistaFitxers(new File("myFiles"));
	}
}
