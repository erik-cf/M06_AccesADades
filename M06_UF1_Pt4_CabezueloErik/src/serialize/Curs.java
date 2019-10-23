package serialize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Curs extends Pt2 {

	private String tutor;
	private ArrayList<String> alumnes;
	private HashMap<String, Modul> moduls;
	Scanner sc = new Scanner(System.in);

	public Curs() {

	}

	public Curs(String tutor, ArrayList<String> alumnes, HashMap<String, Modul> moduls) {
		super();
		this.tutor = tutor;
		this.alumnes = alumnes;
		this.moduls = moduls;
	}

	public Curs(String tutor, ArrayList<String> alumnes) {
		super();
		this.tutor = tutor;
		this.alumnes = alumnes;
		moduls = new HashMap<String, Modul>();
	}

	public Modul getModul(String modul) {
		return moduls.get(modul);
	}

	public void printCurs() {
		System.out.println("\nTutor del curs" + this.tutor);
		System.out.println("Alumnes del curs:");
		for (String alumne : alumnes) {
			System.out.println("\t - " + alumne);
		}
		System.out.println("\nModuls del curs: ");
		for (String key : moduls.keySet()) {
			System.out.println("\tMODUL " + moduls.get(key).getNom());
			System.out.println("\t\tProfe: " + moduls.get(key).getProfe());
			System.out.println("\t\tUFs: ");
			for (String uf : moduls.get(key).getUfs()) {
				System.out.println("\t\t\t - " + uf);
			}
		}
	}

	public void setCurs(String curs) {
		menuSetCurs();
		accionsCurs();
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

	public HashMap<String, Modul> afegirModul() {
		String nom;
		String profe;
		ArrayList<String> ufs = new ArrayList<String>();
		String uf;
		System.out.println("Introdueix el nom del modul: ");
		nom = sc.nextLine();
		System.out.println("Introdueix el nom del professor: ");
		profe = sc.nextLine();
		System.out.println("Introdueix les ufs del modul (Introdueix una X per a sortir):");
		do {
			uf = sc.nextLine();
			if (!uf.equalsIgnoreCase("x"))
				ufs.add(uf);
		} while (!uf.equalsIgnoreCase("x"));
		Modul m = new Modul(this.tutor, this.alumnes, nom, profe, ufs);
		moduls.put(nom, m);
		return moduls;
	}

	public void menuSetCurs() {
		System.out.println("\nQue vols fer amb el curs?");
		System.out.println("\t1 - Canviar de tutor.");
		System.out.println("\t2 - Afegir alumnes al curs.");
		System.out.println("\t3 - Treure alumnes del curs.");
		System.out.println("\t4 - Afegir un nou modul al curs.");
		System.out.println("\t5 - Modificar dades d'un modul");
		System.out.println("\t6 - Eliminar un modul del curs");
		System.out.println("\t7 - Tornar.");
	}

	public void accionsCurs() {
		boolean inputCorrecte = false;
		int opcio = 0;
		String alumne, modul;
		while (!inputCorrecte) {
			try {
				opcio = sc.nextInt();
				sc.nextLine();
				inputCorrecte = true;
			} catch (Exception e) {
				System.out.println("Has d'introduir un número!");
				sc.nextLine();
			}
		}
		switch (opcio) {
		default:
			System.out.println("Has de ficar una opció de l'1 al 7!");
			break;
		case 1:
			System.out.println("Introdueix el nom del nou tutor:");
			this.setTutor(sc.nextLine());

			break;
		case 2:
			System.out.println("Introdueix els alumnes del curs (Introdueix una X per a sortir):");
			do {
				alumne = sc.nextLine();
				if (!alumne.equalsIgnoreCase("x")) {
					alumnes.add(alumne);
					System.out.println("Afegit alumne " + alumne);
				}
			} while (!alumne.equalsIgnoreCase("x"));
			break;
		case 3:
			System.out.println("Introdueix el nom del alumne a treure: ");
			alumne = sc.nextLine();
			if (alumnes.contains(alumne)) {
				alumnes.remove(alumnes.indexOf(alumne));
			}
			System.out.println("Eliminat l'alumne " + alumne);
			break;
		case 4:
			afegirModul();
			break;
		case 5:
			System.out.println("Introdueix el modul a modificar: ");
			modul = sc.nextLine();
			if (moduls.containsKey(modul)) {
				moduls.get(modul).setModul(modul);
			}else {
				System.out.println("El modul no existeix...");
			}
			break;
		case 6:
			System.out.println("Introdueix el nom del modul a esborrar:");
			modul = sc.nextLine();
			if (moduls.containsKey(modul)) {
				moduls.remove(modul);
			}else {
				System.out.println("El modul no existeix...");
			}
			break;
		case 7:
			return;
		}

	}

}
