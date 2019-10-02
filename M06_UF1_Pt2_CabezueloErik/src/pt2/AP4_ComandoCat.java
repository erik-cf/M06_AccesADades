package pt2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AP4_ComandoCat {

	/*
	 * Aquest metode ens mostra el contingut d'un fitxer. Se li passa una ruta per
	 * parametre, que sera el fitxer a llegir
	 */
	public static void mostraArxiu(String ruta) {
		// Creem un objecte File amb la ruta del parametre
		File f = new File(ruta);
		// Si el fitxer existeix, proseguim
		if (f.exists()) {
			// Un boolea que variara depenent de si te contingut o no el fitxer
			boolean notEmpty = false;
			// Declarem l'Scanner
			Scanner scF = null;

			try {
				// Inicialitzem l'Scanner amb l'objecte f (fitxer a llegir)
				scF = new Scanner(f);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			// Recorrem el fitxer per a llegir les linies:
			while (scF.hasNext()) {
				// Si entra a la condicio del while, vol dir que el fitxer no es buit, pel que
				// canviem la variable notEmpty a false
				notEmpty = true;
				// Mostrem les linies una per una per a cada volta del bucle
				System.out.println(scF.nextLine());
			}
			// Si el fitxer esta buit (variable notEmpty = false) mostra un error
			if (!notEmpty) {
				System.out.println("El fitxer esta buit.");
			}

		} else {
			// Si el fitxer no existeix, mostra un error:
			System.out.println("Aquest fitxer no existeix.");
		}
	}

	public static void main(String[] args) {
		// Boolea per saber si l'usuari tanca el programa o no
		boolean continua = true;
		// Un Scanner per a llegir la ruta introduida per l'usuari
		Scanner sc = new Scanner(System.in);
		// Un string per a guardar la ruta
		String ruta;
		// Mentre que l'usuari no escrigui fi, demanem la ruta i truquem a la funcio
		// mostraArxiu(File f) que llegira el contingut si es pot
		do {
			System.out.println("Introdueix la ruta d'un fitxer per a mirar un contingut:");
			System.out.println("Escriu fi per a terminar el programa.");
			ruta = sc.nextLine();
			if (ruta.equalsIgnoreCase("fi")) {
				System.out.println("Fins aviat!");
				continua = false;
			} else {
				mostraArxiu(ruta);
			}
		} while (continua);
		sc.close();
	}
}
