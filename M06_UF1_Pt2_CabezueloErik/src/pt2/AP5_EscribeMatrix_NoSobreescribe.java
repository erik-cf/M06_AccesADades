package pt2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AP5_EscribeMatrix_NoSobreescribe {

	public static void main(String[] args) {
		String text = "Yo sólo puedo mostrarte la puerta, tú eres quien la tiene que atravesar.";
		String nomArchivo = "frasesMatrix.txt";
		File f = new File(nomArchivo);
		System.out.println("Creando fichero frasesMatrix.txt...");
		FileWriter wr;
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			wr = new FileWriter(nomArchivo, true);
			System.out.println(
					"Escribiendo la frase \"Yo sólo puedo mostrarte la puerta, tú eres quien la tiene que atravesar.\"");
			wr.write(text + "\r\n");
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
