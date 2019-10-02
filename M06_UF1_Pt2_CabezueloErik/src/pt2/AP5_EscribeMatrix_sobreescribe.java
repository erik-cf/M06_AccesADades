package pt2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AP5_EscribeMatrix_sobreescribe {
	public static void main(String[] args) {
		// String amb la frase a escriure
		String text = "Yo sólo puedo mostrarte la puerta, tú eres quien la tiene que atravesar.";
		System.out.println("Creando fichero frasesMatrix.txt...");
		// Un String amb el nom del fitxer
		String nomArchivo = "frasesMatrix.txt";
		// Creem l'objecte File
		File f = new File(nomArchivo);
		// Declarem l'objecte FileWriter
		FileWriter wr;
		try {
			// Si el fitxer no existeix el creem
			if (!f.exists()) {
				f.createNewFile();
			}
			// Inicialitzem l'objecte fileWriter
			wr = new FileWriter(nomArchivo);
			System.out.println(
					"Escribiendo la frase \"Yo sólo puedo mostrarte la puerta, tú eres quien la tiene que atravesar.\"");
			// Escribim l'String text a l'arxiu
			wr.write(text);
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
