package pt5_2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Modul extends Curs {

	/*
	 * Atributs de la classe Modul
	 */
	private int idModul;
	private String titol;
	private ArrayList<String> professors;
	private ArrayList<String> ufs;

	/*
	 * Constructor buit
	 */
	public Modul() {

	}

	/*
	 * Constructor amb tots els atributs
	 */
	public Modul(int idCurs, String nomCurs, String tutor, ArrayList<String> alumnes, int idModul, String titol,
			ArrayList<String> professors, ArrayList<String> ufs, Curs curs) {
		super(idCurs, nomCurs, tutor, alumnes);
		this.idModul = idModul;
		this.titol = titol;
		this.professors = professors;
		this.ufs = ufs;
	}

	/*
	 * Getters i Setters
	 */
	public int getIdModul() {
		return idModul;
	}

	public void setIdModul(int idModul) {
		this.idModul = idModul;
	}

	public String getTitol() {
		return titol;
	}

	public void setTitol(String titol) {
		this.titol = titol;
	}

	public ArrayList<String> getProfessors() {
		return professors;
	}

	public void setProfessors(ArrayList<String> professors) {
		this.professors = professors;
	}

	public ArrayList<String> getUfs() {
		return ufs;
	}

	public void setUfs(ArrayList<String> ufs) {
		this.ufs = ufs;
	}

	/*
	 * Metode que mostra les opcions de modificacio d'un modul. Retorna el modul
	 * modificat per tal de fer els canvis després al fitxer XML i al hashmap de
	 * moduls corresponent
	 */
	public Modul modificaModul() {
		do {
			menuModificaModul();
		} while (accionsModul());
		return this;
	}

	/*
	 * Metode que mostra el menu de modificacio del modul
	 */
	public void menuModificaModul() {
		System.out.println("Que vols fer amb el modul " + this.titol + "?");
		System.out.println("1 - Canviar titol del modul");
		System.out.println("2 - Afegir professors al modul");
		System.out.println("3 - Treure professors al modul");
		System.out.println("4 - Afegir ufs al modul");
		System.out.println("5 - Truere ufs al modul");
		System.out.println("6 - Tornar");
	}

	/*
	 * Metode que fa les accions depenent de l'accio escollida per l'usuari. Retorna
	 * un booleà depenent de si l'usuari ha triat l'opció de Tornar o no.
	 */
	public boolean accionsModul() {
		// Declarem variables
		Scanner sc = new Scanner(System.in);
		int opcio = 0;
		boolean ok = false;
		String curs;
		String tutor;
		Modul m;
		// Si es fica cap cosa que no sigui un enter, el programa no deixa continuar
		// l'usuari
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
		
		ok = false;
		
		switch (opcio) {
		
		default:
			// Si fiquem un número que no es a les opcions
			System.out.println("Has de ficar un número de l'1 al 6!");
			break;
		
		case 1:
			// Canviem el valor del titol del modul
			System.out.println("Introdueix el nou titol del modul: ");
			this.setTitol(sc.nextLine());
			break;
		
		case 2:
			// Truquem el metode que afegeix professors
			afegirProfessors();
			break;
		
		case 3:
			// Truquem el metode que elimina professors
			eliminaProfessors();
			break;
		
		case 4:
			// Truquem el metode que afegeix ufs
			afegirUfs();
			break;
		
		case 5:
			// Truquem el metode que elimina ufs
			eliminaUfs();
			break;
		
		case 6:
			// Retornem false, perquè l'usuari ha decidit tornar enrere
			return false;
		}
		// Si no s'ha escollit la sisena opcio, continuem al mateix menu
		return true;
	}

	/*
	 * Metode que afegeix professors a un modul
	 */
	public void afegirProfessors() {
		Scanner sc = new Scanner(System.in);
		String nomProfessor;
		boolean fi = false;
		
		System.out.println("Introdueix els professors que vols afegir(Escriu una x per terminar):");
		while (!fi) {
			
			nomProfessor = sc.nextLine();
			
			if (nomProfessor.equalsIgnoreCase("x"))
				fi = true;
			else
				professors.add(nomProfessor);
		}
	}

	/*
	 * Metode que elimina professors d'un modul
	 */
	public void eliminaProfessors() {
		Scanner sc = new Scanner(System.in);
		String nomProfessor;
		boolean fi = false;
		
		System.out.println("Introdueix els professors que vols eliminar (Escriu una x per terminar):");
		while (!fi) {
		
			nomProfessor = sc.nextLine();
			
			if (nomProfessor.equalsIgnoreCase("x")) {
				fi = true;
			} else {
			
				if (professors.contains(nomProfessor)) {
					professors.remove(nomProfessor);
				} else {
					System.out.println("Aquest professor no existeix en aquest modul!");
				}
			}
			
		}
	}

	/*
	 * Metode que afegeix UFs a un modul
	 */
	public void afegirUfs() {
		Scanner sc = new Scanner(System.in);
		String uf;
		boolean fi = false;
		
		System.out.println("Introdueix les UFs que vols afegir(Escriu una x per terminar):");
		while (!fi) {
			
			uf = sc.nextLine();
		
			if (uf.equalsIgnoreCase("x"))
				fi = true;
			else
				ufs.add(uf);
		
		}
	}

	/*
	 * Metode que elimina UFs d'un modul
	 */
	public void eliminaUfs() {
		Scanner sc = new Scanner(System.in);
		String uf;
		boolean fi = false;
		
		System.out.println("Introdueix les UFs que vols eliminar (Escriu una x per terminar):");
		while (!fi) {
		
			uf = sc.nextLine();
			
			if (uf.equalsIgnoreCase("x")) {
				fi = true;
			} else {
			
				if (ufs.contains(uf)) {
					ufs.remove(uf);
				} else {
					System.out.println("Aquesta UF no existeix en aquest modul!");
				}
			
			}
		}
	}

	/*
	 * Metode que sobreescriu el metode equals dels objectes de la classe Modul
	 */
	public boolean equals(Modul nou) {
		if (nou == null) {
			// Si el nou modul es null, es retorna false
			return false;
		}
		
		if (this == nou) {
			// Si son iguals, retorna true
			return true;
		}
		
		// Depenent de si tenen els mateixos valors als atributs, retorna true o false
		return this.getIdModul() == nou.getIdModul() && this.getTitol().equals(nou.getTitol())
				&& this.getProfessors().equals(nou.getProfessors()) && this.getUfs().equals(nou.getUfs());
	}

}
