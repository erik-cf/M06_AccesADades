package pt2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AP2_ReadLineFileJava {
	public static void main(String[] args) {
		// Declarem els objectes
		Scanner scF = null;
		// Inicialitzem el File amb la ruta d'aquest arxiu:
		File f = new File("src\\pt2\\ReadLineFileJava.java");
		try {
			// Inicialitzem l'Scanner perque llegeixi aquest fitxer
			scF = new Scanner(f);
		} catch (FileNotFoundException e) {
			// Si no troba el fitxer...
			System.out.println("No s'ha trobat el fitxer a " + f.getAbsolutePath());
		}
		// Recorrem tot el fitxer imprimint linia per linia el contingut...
		while (scF.hasNext()) {
			System.out.println(scF.nextLine());
		}
	}
}
