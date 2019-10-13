package pt5;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MetodesSerialitzacio {

	public static void crearPersones() throws IOException {
		File f = new File("myPeople.dat");
		String[] noms = { "María López", "Gustavo Gómez", "Irene Salas", "Roberto Morgade", "Graciela Iglesias" };
		int[] edats = { 36, 1, 36, 63, 60 };
		if (comprovaFitxer(f)) {
			if (noms.length == edats.length) {
				FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				for (int i = 0; i < noms.length; i++) {
					Persona p = new Persona(noms[i], edats[i]);
					oos.writeObject(p);
					System.out.println("Escrit la persona amb nom " + p.getNom() + " amb " + p.getEdat()
							+ " any/s a myPeople.dat.");
				}
				oos.close();
				fos.close();
			}
		}
	}

	private static boolean comprovaFitxer(File f) throws IOException {
		if (!f.exists())
			f.createNewFile();
		return true;
	}
}
