package pt2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AP7_ComandoCP {
	
	/*
	 * Aquest metode copia el contingut del fitxer que hi ha a la ruta que se li passa com a primer parametre, a la ruta que es passa en el segon string que se li passa per parametre
	 */
	public static void copiaFitxers(String rutaArxiu, String rutaCopia) throws IOException {
		// En aquesta variable fileText, emmagatzenem el contingut del fitxer
		String fileText = "";
		// Creem dos objectes de File, un per l'arxiu original i un per la copia
		File original = new File(rutaArxiu);
		File copia = new File(rutaCopia);
		// Necessitarem escriure les dades, per aixo utilitzem un PrintWriter
		PrintWriter pw;
				// Necessitarem llegir tambe el fitxer original, pel que utilitzarem Scanner
				Scanner scOriginal = new Scanner(original);
				// Si el fitxer que es la copia no existeix ja, el creem
				if (!copia.exists()) {
					copia.createNewFile();
				}
				// Inicialitzem l'objecte de PrintWriter amb l'objecte de File de la copia
				pw = new PrintWriter(rutaCopia);
				// Recorrerem tot el fitxer original per llegir el contingut
				while (scOriginal.hasNext()) {
					// Ho guardem a la variable fileText
					fileText = fileText + scOriginal.nextLine() + "\r\n";
				}
				// Despres escriurem el contingut a l'arxiu copia
				pw.write(fileText);
				pw.close();
				scOriginal.close();
				System.out.println("\nFitxer copiat!\n");	

	}

	public static void main(String[] args) {
		// Utilitzarem Scanner per agafar les rutes de l'original i la copia
		Scanner sc = new Scanner(System.in);
		// Dos strings, un per la ruta del arxiu original i un per la ruta de la copia
		String rutaArxiu;
		String rutaCopia;
		// Un boolea per saber si l'usuari ha decidit sortir o no
		boolean exit = false;
		// Mentre que no vulgui sortir l'usuari repetirem el programa amb el bucle do : while
		do {
			System.out
					.println("Introdueix la ruta de l'arxiu que vols copiar(Introdueix FI per terminar el programa: ");
			// Emmagatzenem la ruta de l'arxiu original
			rutaArxiu = sc.nextLine();
			// Si no es la paraula fi, continuem el programa
			if (!rutaArxiu.equalsIgnoreCase("fi")) {
				// Emmagatzenem la ruta de l'arxiu que sera la copia
				System.out.println("Introdueix la ruta on vols copiar l'arxiu:");
				rutaCopia = sc.nextLine();
				try {
					// Cridem a la funcio que copia els fitxers
					copiaFitxers(rutaArxiu, rutaCopia);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				// Si l'usuari escriu fi, acaba el programa
				System.out.println("Fins aviat!");
				exit = true;
			}
		} while (!exit);
		sc.close();
	}
}
