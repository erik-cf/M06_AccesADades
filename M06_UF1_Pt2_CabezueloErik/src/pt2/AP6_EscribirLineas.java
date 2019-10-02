package pt2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AP6_EscribirLineas {
	public static void main(String[] args) {
		// Nom de l'arxiu
		String archivo = "escribeLineas.txt";
		// Declarem i inicialitzem l'objecte File f amb el nom de l'arxiu
		File f = new File(archivo);
		// Declarem l'objecte de FileWriter
		FileWriter wr = null;
		try {
			// Si l'arxiu no existeix el creem:
			if(!f.exists()) {
				f.createNewFile();
			}
			// Inicialitzem l'objecte del FileWriter
			wr = new FileWriter(archivo, true);
			// Fem un bucle 10 vegades per a escriure 10 linies
			for(int i = 1; i <= 10; i++) {
				// Utilitzem \r\n per a fer un salt de linia a l'hora d'escriure
				// \r ens retorna al principi de linia, sense fer el salt
				// \n ens fa el salt de linia
				wr.write("Linea " + i + "\r\n");
			}
			System.out.println("Escribint linees...");
			wr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
