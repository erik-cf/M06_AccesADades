package pt2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AP5_EscribeMatrix_sobreescribe {
	public static void main(String[] args) {
		String text = "Yo sólo puedo mostrarte la puerta, tú eres quien la tiene que atravesar.";
		System.out.println("Creando fichero frasesMatrix.txt...");
		String nomArchivo = "frasesMatrix.txt";

		File f = new File(nomArchivo);
		FileWriter wr;
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			wr = new FileWriter(nomArchivo);
			System.out.println(
					"Escribiendo la frase \"Yo sólo puedo mostrarte la puerta, tú eres quien la tiene que atravesar.\"");
			wr.write(text);
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
