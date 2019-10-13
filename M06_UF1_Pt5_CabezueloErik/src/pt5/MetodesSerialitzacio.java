package pt5;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MetodesSerialitzacio {

	/*
	 * Metode que crea les persones, i les guarda a l'arxiu myPeople.dat
	 */
	public static void crearPersones() throws IOException {
		File f = new File("myPeople.dat");
		// Un array de Strings que té tots els noms de les persones que es volen crear
		String[] noms = { "María López", "Gustavo Gómez", "Irene Salas", "Roberto Morgade", "Graciela Iglesias" };
		// Un array amb les edats de les persones:
		int[] edats = { 36, 1, 36, 63, 60 };
		// Comprovem si existeix el fitxer per si hagués cap problema
		if (comprovaFitxer(f)) {
			// Mirem que les dues arrays siguin de la mateixa mida, per evitar errors
			if (noms.length == edats.length) {
				// Declarem els Streams
				FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				// Un bucle que omplira l'arxiu myPeople.dat amb les dades de les arrays.
				for (int i = 0; i < noms.length; i++) {
					Persona p = new Persona(noms[i], edats[i]);
					oos.writeObject(p);
					System.out.println("Escrit la persona amb nom " + p.getNom() + " amb " + p.getEdat()
							+ " any/s a myPeople.dat.");
				}
				oos.close();
				fos.close();
			}else {
				// Si no son de la mateixa mida, mostrem un error i tanquem el programa
				System.out.println("Els arrays de noms i edats han de tenir la mateixa mida!");
				System.exit(0);
			}
		}
	}
	
	/*
	 * Metode que comprova si el fitxer myPeople.dat existeix.
	 * Si no existeix, el crea.
	 */
	private static boolean comprovaFitxer(File f) throws IOException {
		if (!f.exists())
			f.createNewFile();
		return true;
	}
}
