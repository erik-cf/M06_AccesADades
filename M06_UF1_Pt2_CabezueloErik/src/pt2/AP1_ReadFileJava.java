package pt2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AP1_ReadFileJava {
	public static void main(String[] args) {
		// Creem els objectes necessaris...
		Scanner scF = null;
		// Afegim l'objecte File, que agafa la ruta d'aquest arxiu...
		File f = new File("src\\pt2\\ReadFileJava.java");
		try {
			// Inicialitzem l'Scanner amb l'arxiu
			scF = new Scanner(f);
		} catch (FileNotFoundException e) {
			// Si no troba el fitxer:
			System.out.println("No s'ha trobat el fitxer a " + f.getAbsolutePath());
		}
		// Recorrem tot el fitxer i mostrem totes les dades que hi hagi al fitxer
		while (scF.hasNext()) {
			System.out.println(scF.next());
		}
	}
}
