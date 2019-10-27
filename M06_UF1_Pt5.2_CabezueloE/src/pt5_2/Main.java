package pt5_2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class Main {

	// Scanner que farem servir al llarg de l'aplicació
	static Scanner sc = new Scanner(System.in);
	// HashMap on desarem els cursos
	HashMap<String, Curs> cursos = new HashMap<String, Curs>();

	/*
	 * Metode que afegeix un curs nou al fitxer XML i al HashMap
	 */
	public void afegirCurs() throws TransformerException {
		boolean fi = false;
		String nomCurs, tutor;
		String nomAlumne;
		ArrayList<String> alumnes = new ArrayList<String>();
		int idCurs = 0;

		do {
			System.out.println("Introdueix l'ID del curs: ");

			try {
				idCurs = sc.nextInt();
				sc.nextLine();

				if (existeixIDCurs(idCurs)) {
					System.out.println("Aquest ID ja existeix");
				} else {
					fi = true;
				}

			} catch (InputMismatchException e) {
				System.out.println("Ha de ser un numero sencer!");
				sc.nextLine();
			}

		} while (!fi);

		fi = false;

		do {

			System.out.println("Introdueix el nom del curs: ");
			nomCurs = sc.nextLine();

			if (existeixCurs(nomCurs)) {
				System.out.println("Aquest curs ja existeix!");
			} else {
				fi = true;
			}

		} while (!fi);

		fi = false;

		System.out.println("Introdueix el nom del tutor del curs: ");
		tutor = sc.nextLine();
		System.out.println("Introdueix alumnes al curs (Escriu X per terminar): ");

		while (!fi) {

			nomAlumne = sc.nextLine();
			if (nomAlumne.equalsIgnoreCase("x"))
				fi = true;
			else
				alumnes.add(nomAlumne);
		}

		System.out.println("Afegint curs " + nomCurs);

		Curs c = new Curs(idCurs, nomCurs, tutor, alumnes);

		cursos.put(nomCurs, c);
		// Truquem al metode que afegeix el curs al fitxer XML
		MetodesDOM.afegirCursDOM(c);
	}

	/*
	 * Metode que inicialitza l'aplicacio demanant crear un curs i un modul, si no
	 * hi ha cap fitxer XML creat
	 */
	public void iniciAplicacio() throws TransformerException {

		System.out.println("Primer hem de crear un curs.");
		afegirCurs();

		System.out.println("Ara hem d'introduir un nou modul: ");
		Iterator<String> it = cursos.keySet().iterator();
		cursos.get(it.next()).afegirModul();
	}

	/*
	 * Metode que imprimeix el menu de l'aplicacio
	 */
	public void menu() {
		System.out.println("Benvingut al gestor del institut. Que vols fer? ");
		System.out.println("1 - Afegir curs.");
		System.out.println("2 - Modificar curs.");
		System.out.println("3 - Eliminar curs");
		System.out.println("4 - Mostra un curs");
		System.out.println("5 - Mostra tots els cursos");
		System.out.println("6 - Sortir");
	}

	/*
	 * Metode que realitza les accions que hagi escollit l'usuari, trucant als
	 * diferents metodes. Retorna un boolea true o false depenent de si l'usuari vol
	 * sortir o no de l'aplicacio.
	 */
	public boolean accions() throws TransformerException {
		int opcio = 0;
		boolean ok = false;
		String curs;
		Curs c;
		
		while (!ok) {
		
			try {
				opcio = sc.nextInt();
				sc.nextLine();
				ok = true;
			} catch (InputMismatchException e) {
				System.out.println("Has de ficar un numero!");
				sc.nextLine();
				continue;
			}
		
		}
		
		switch (opcio) {
		
		default:
			System.out.println("El numero ha de ser entre l'1 i el 6!");
			break;
		
		case 1:
			// Truquem al metode que afegeix el curs
			afegirCurs();
			break;
		
		case 2:
			// Modifiquem el curs seleccionat
			System.out.println("Introdueix el curs a modificar: ");
			curs = sc.nextLine();
			// Si existeix
			if (existeixCurs(curs)) {
				// Truquem el metode
				c = cursos.get(curs).modificaCurs();
				// Comprovem si son iguals els dos cursos per veure si hi ha hagut canvis
				if (!cursos.get(curs).equals(c)) {
					// Gestionem fitxer XML i hashmap
					MetodesDOM.modificaCursDOM(cursos.get(curs), c);
					cursos.remove(curs);
					cursos.put(c.getNomCurs(), c);
				}
			} else {
				System.out.println("Aquest curs no existeix!");
			}
			break;
		
		case 3:
			// Eliminem el curs seleccionat, tant del hashmap com del fitxer XML
			System.out.println("Introdueix el curs a eliminar: ");
			curs = sc.nextLine();
			if (existeixCurs(curs)) {
				MetodesDOM.eliminaCursDOM(cursos.get(curs));
				cursos.remove(curs);
				System.out.println("Eliminat curs " + curs);
			} else {
				System.out.println("Aquest curs no existeix!");
			}
			break;
		
		case 4:
			// Truquem al metode que mostra el curs seleccionat
			System.out.println("Introdueix el curs a mostrar: ");
			curs = sc.nextLine();
			if (existeixCurs(curs)) {
				cursos.get(curs).printCurs();
			} else {
				System.out.println("Aquest curs no existeix!");
			}
			break;
		
		case 5:
			// Truquem per a tots els cursos al metode printCurs() de la classe Curs
			for (String key : cursos.keySet()) {
				cursos.get(key).printCurs();
			}
			break;
		
		case 6:
			// Finalitzem l'execució
			System.out.println("Fins aviat!");
			sc.close();
			return false;
		}
		return true;
	}

	/*
	 * Metode que comprova si l'ID d'aquest curs esta sent utilitzat per un altre.
	 * Retorna un boolea (true) si ja existeix l'ID, false si no.
	 */
	public boolean existeixIDCurs(int ID) {
		Iterator<String> it = cursos.keySet().iterator();
		while (it.hasNext()) {
			if (cursos.get(it.next()).getIdCurs() == ID) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Metode que comprova si el curs existeix dins el hashmap
	 */
	public boolean existeixCurs(String curs) {
		return cursos.containsKey(curs);
	}

	/*
	 * Metode main principal
	 */
	public static void main(String[] args) {
		Main m = null;
		try {
			// Si el fitxer existia
			if (MetodesDOM.inicialitzarFitxer()) {
				// Creem els objectes i omplim els hashmaps amb les dades del fitxer
				m = MetodesDOM.crearObjectes();
			} else {
				m = new Main();
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		try {
			// Si el hashmap es buit, truquem al principi de l'aplicacio per crear el primer curs i modul
			if (m.cursos.size() == 0)
				m.iniciAplicacio();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		try {
			// Comença l'aplicacio principal (Menu i opcions)
			do {
				m.menu();
			} while (m.accions());
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Getters i Setters del hashmap
	 */
	public HashMap<String, Curs> getCursos() {
		return cursos;
	}

	public void setCursos(HashMap<String, Curs> cursos) {
		this.cursos = cursos;
	}
}
