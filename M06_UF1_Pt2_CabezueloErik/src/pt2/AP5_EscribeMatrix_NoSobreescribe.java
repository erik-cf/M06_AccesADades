package pt2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AP5_EscribeMatrix_NoSobreescribe {

	public static void main(String[] args) {
		// String amb el text a escriure..
		String text = "Yo sólo puedo mostrarte la puerta, tú eres quien la tiene que atravesar.";
		// String amb el nom de l'arxiu
		String nomArchivo = "frasesMatrix.txt";
		// Creem l'objecte f amb el parametre nomArchivo
		File f = new File(nomArchivo);
		System.out.println("Creando fichero frasesMatrix.txt...");
		// Declarem l'objecte que escriura al fitxer:
		FileWriter wr;
		try {
			// Si no existeix el fitxer, el creem:
			if (!f.exists()) {
				f.createNewFile();
			}
			// Inicialitzem el FileWriter amb l'objecte File f, el segon parametre vol dir que quan escrigui, començara al final, i per tant, no sobreescriura el contingut:
			wr = new FileWriter(nomArchivo, true);
			System.out.println(
					"Escribiendo la frase \"Yo sólo puedo mostrarte la puerta, tú eres quien la tiene que atravesar.\"");
			// Escribim la frase al fitxer (El string \r\n es per a que faci un salt de linia)
			wr.write(text + "\r\n");
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
