package pt5_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import javax.xml.transform.TransformerException;

public class Curs {

	/*
	 * Atributs de la classe Curs
	 */
	private int idCurs;
	private String nomCurs;
	private String tutor;
	private ArrayList<String> alumnes;
	private HashMap<String, Modul> moduls;

	/*
	 * Constructor on se li omplen tots els atributs, excepte el hashmap de moduls
	 * que s'inicialitza buit
	 */
	public Curs(int idCurs, String nomCurs, String tutor, ArrayList<String> alumnes) {
		super();
		this.idCurs = idCurs;
		this.nomCurs = nomCurs;
		this.tutor = tutor;
		this.alumnes = alumnes;
		moduls = new HashMap<String, Modul>();
	}

	/*
	 * Constructor buit
	 */
	public Curs() {

	}

	/*
	 * Getters i Setters
	 */
	public int getIdCurs() {
		return idCurs;
	}

	public void setIdCurs(int idCurs) {
		this.idCurs = idCurs;
	}

	public String getNomCurs() {
		return nomCurs;
	}

	public void setNomCurs(String nomCurs) {
		this.nomCurs = nomCurs;
	}

	public String getTutor() {
		return tutor;
	}

	public void setTutor(String tutor) {
		this.tutor = tutor;
	}

	public ArrayList<String> getAlumnes() {
		return alumnes;
	}

	public void setAlumnes(ArrayList<String> alumnes) {
		this.alumnes = alumnes;
	}

	public HashMap<String, Modul> getModuls() {
		return moduls;
	}

	public void setModuls(HashMap<String, Modul> moduls) {
		this.moduls = moduls;
	}

	/*
	 * Metode que afegeix un modul nou amb les dades que te aquest.
	 */
	public void afegirModul() throws TransformerException {
		Scanner sc = new Scanner(System.in);
		boolean fi = false;
		String titol;
		String nomProfessor;
		String uf;
		ArrayList<String> professors = new ArrayList<String>();
		ArrayList<String> ufs = new ArrayList<String>();
		int idModul = 0;
		// Mentre que l'ID sigui correcte (un numero i no existeixi ja) fem el bucle
		do {
			System.out.println("Introdueix l'ID del modul: ");

			try {
				idModul = sc.nextInt();
				sc.nextLine();

				if (existeixIDModul(idModul)) {
					System.out.println("Aquest ID de modul ja existeix!");
				} else {
					fi = true;
				}

			} catch (InputMismatchException e) {
				System.out.println("Ha de ser un numero sencer!");
				sc.nextLine();
			}

		} while (!fi);
		// Tornem la variable fi al valor inicial
		fi = false;

		System.out.println("Introdueix el titol del modul: ");
		titol = sc.nextLine();

		System.out.println("Introdueix professors al modul (Escriu X per terminar): ");
		while (!fi) {

			nomProfessor = sc.nextLine();

			if (nomProfessor.equalsIgnoreCase("x"))
				fi = true;
			else
				professors.add(nomProfessor);
		}

		fi = false;

		System.out.println("Introdueix ufs al modul (Escriu X per terminar): ");
		while (!fi) {

			uf = sc.nextLine();

			if (uf.equalsIgnoreCase("x"))
				fi = true;
			else
				ufs.add(uf);
		}

		System.out.println("Afegint modul " + titol);

		Modul m = new Modul(this.getIdCurs(), this.getNomCurs(), this.getTutor(), this.getAlumnes(), idModul, titol,
				professors, ufs, this);

		moduls.put(titol, m);
		// Truquem al metode que afegeix el modul al fitxer XML
		MetodesDOM.afegirModulsCurs(this, m);
	}

	/*
	 * Metode que modifica un curs. Retorna el curs modificat per tal de poder-ho
	 * canviar al hashmap corresponent i al fitxer XML
	 */
	public Curs modificaCurs() throws TransformerException {

		do {
			menuModificaCurs();
		} while (accionsCurs());

		return this;
	}

	/*
	 * Metode que imprimeix un curs especific
	 */
	public void printCurs() {
		System.out.println("\nMOSTRANT CURS " + this.getNomCurs());
		System.out.println("\tID del curs: " + this.getIdCurs());
		System.out.println("\tTutor del curs: " + this.getTutor());
		System.out.println("\tAlumnes del curs: ");
		for (String alumne : alumnes) {
			System.out.println("\t\t - " + alumne);
		}
		System.out.println("\tModuls del curs: ");
		for (String modul : moduls.keySet()) {
			System.out.println("\t\tModul " + moduls.get(modul).getTitol());
			System.out.println("\t\t\tID del modul: " + moduls.get(modul).getIdModul());
			System.out.println("\t\t\tProfessors del modul: ");
			for (String professor : moduls.get(modul).getProfessors()) {
				System.out.println("\t\t\t\t - " + professor);
			}
			System.out.println("\t\t\tUFs del modul: ");
			for (String uf : moduls.get(modul).getUfs()) {
				System.out.println("\t\t\t\t - " + uf);
			}
		}
	}

	/*
	 * Menu amb les opcions per a modificar un curs
	 */
	public void menuModificaCurs() {
		System.out.println("Que vols fer amb el curs " + this.getNomCurs() + "?");
		System.out.println("1 - Afegir un modul");
		System.out.println("2 - Modifica un modul");
		System.out.println("3 - Elimina un modul");
		System.out.println("4 - Canviar el nom del curs");
		System.out.println("5 - Canviar el tutor del curs");
		System.out.println("6 - Afegir alumnes al curs");
		System.out.println("7 - Treure alumnes del curs");
		System.out.println("8 - Tornar");
	}

	/*
	 * Metode que realitza les funcions depenent de l'opcio escollida per l'usuari
	 */
	public boolean accionsCurs() throws TransformerException {
		Scanner sc = new Scanner(System.in);
		int opcio = 0;
		boolean ok = false;
		String modul;
		String curs;
		String tutor;
		Modul m;

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
			System.out.println("Has de ficar un numero de l'1 al 8!");

		case 1:
			// Truquem al metode que afegeix un modul nou al curs
			afegirModul();
			break;

		case 2:
			// Demanem el modul a modificar
			System.out.println("Introdueix el modul a modificar:");
			modul = sc.nextLine();
			// Comprovem si existeix el modul
			if (existeixModul(modul)) {
				// Truquem al metode que modifica el modul
				m = moduls.get(modul).modificaModul();
				// Si el modul que ens retornen i el que hi havia son diferents, ho canviem al
				// hashmap i a l'XML
				if (!moduls.get(modul).equals(m)) {
					// Metode que modifica el fitxer XML
					MetodesDOM.modificaModulDOM(this, moduls.get(modul), m);
					// Gestionem el hashmap
					moduls.remove(moduls.get(modul).getTitol());
					moduls.put(m.getTitol(), m);
				} else {
					System.out.println("Aquest modul no existeix!");
				}
			}
			break;

		case 3:
			// Demanem el modul a eliminar:
			System.out.println("Introdueix el modul a eliminar: ");
			modul = sc.nextLine();
			// Si existeix, l'eliminem
			if (existeixModul(modul)) {
				// Metode que elimina el modul del fitxer XML
				MetodesDOM.eliminaModulDOM(this, moduls.get(modul));
				// Eliminem del hashmap
				moduls.remove(modul);
			} else {
				System.out.println("Aquest modul no existeix!");
			}
			break;

		case 4:
			// Canviem el nom del curs
			System.out.println("Introdueix el nom nou del curs: ");
			curs = sc.nextLine();
			this.setNomCurs(curs);
			break;

		case 5:
			// Canviem el tutor del curs
			System.out.println("Introdueix el nom nou del tutor del curs: ");
			tutor = sc.nextLine();
			this.setTutor(tutor);
			break;

		case 6:
			// Truquem al metode que afegeix alumnes
			afegirAlumnes();
			break;

		case 7:
			// Truquem al metode que elimina alumnes
			eliminaAlumnes();
			break;

		case 8:
			// L'ususari ha seleccionat tornar
			return false;
		}
		return true;
	}

	/* 
	 * Metode que afegeix alumnes a l'arraylist del curs especific
	 */
	public void afegirAlumnes() {
		Scanner sc = new Scanner(System.in);
		String nomAlumne;
		boolean fi = false;

		System.out.println("Introdueix els alumnes que vols afegir(Escriu una x per terminar):");
		while (!fi) {

			nomAlumne = sc.nextLine();

			if (nomAlumne.equalsIgnoreCase("x"))
				fi = true;
			else
				alumnes.add(nomAlumne);
		}
	}

	/*
	 * Metode que elimina alumnes de l'arraylist del curs
	 */
	public void eliminaAlumnes() {
		Scanner sc = new Scanner(System.in);
		String nomAlumne;
		boolean fi = false;

		System.out.println("Introdueix els alumnes que vols eliminar (Escriu una x per terminar):");
		while (!fi) {

			nomAlumne = sc.nextLine();

			if (nomAlumne.equalsIgnoreCase("x")) {
				fi = true;
			} else {

				if (alumnes.contains(nomAlumne)) {
					alumnes.remove(nomAlumne);
				} else {
					System.out.println("Aquest alumne no existeix en aquest curs!");
				}
			}
		}
	}

	/*
	 * Metode que comprova si hi ha cap modul amb un ID especific que se li passa com a parametre. Retorna un boolea depenent si ho conte o no.
	 */
	public boolean existeixIDModul(int ID) {
		Iterator<String> it = moduls.keySet().iterator();

		while (it.hasNext()) {
			if (moduls.get(it.next()).getIdModul() == ID) {
				return true;
			}
		}

		return false;
	}
	
	/*
	 * Metode que sobreescriu el metode equals de l'objecte Curs
	 */
	public boolean equals(Curs nou) {
		if (nou == null) {
			return false;
		}

		if (this == nou) {
			return true;
		}

		return this.getNomCurs().equals(nou.getNomCurs()) && this.getIdCurs() == nou.idCurs
				&& this.getAlumnes().equals(nou.getAlumnes()) && this.getTutor().equals(nou.getTutor());
	}

	/*
	 * Metode que comprova si ja hi ha un modul amb el nom especificat. Retorna un boolea depenent si existeix o no.
	 */
	public boolean existeixModul(String modul) {
		return moduls.containsKey(modul);
	}
}
