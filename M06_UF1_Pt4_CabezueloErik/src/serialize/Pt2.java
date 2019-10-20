package serialize;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Pt2 extends Curs {

	private HashMap<String, Curs> cursos;
	static Scanner sc = new Scanner(System.in);

	public Pt2(String tutor, String profe, ArrayList<String> ufs, String tutor2, ArrayList<String> alumnes,
			HashMap<String, Modul> moduls, HashMap<String, Curs> cursos) {
		super(tutor, profe, ufs, tutor2, alumnes, moduls);
		this.cursos = cursos;
	}

	public Pt2() {

	}

	public void dadesIn(File f) {

	}

	public void dadesOut(File f) {

	}

	public HashMap<String, Curs> getCursos() {
		return cursos;
	}

	public void setCursos(HashMap<String, Curs> cursos) {
		this.cursos = cursos;
	}

	public static void main(String[] args) {
		boolean fi = false;
		while (!fi) {
			menu();
			fi = accions();
		}

	}

	public static void menu() {
		System.out.println("MENU INS ESTEVE TERRADAS");
		System.out.println("\t1 - AFEGIR UN NOU CURS");
		System.out.println("\t2 - MODIFICAR UN CURS");
		System.out.println("\t3 - MOSTRAR DADES CURS");
		System.out.println("\t4 - ELIMINAR UN CURS");
		System.out.println("\t5 - SORTIR");
		System.out.println("Escull una opcio");
	}

	public static boolean accions() {
		Pt2 pt = new Pt2();
		int opcio = 0;
		String curs;
		try {
			opcio = sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Has de ficar un numero enter!");
			sc.nextLine();
			return false;
		}
		switch (opcio) {
		default:
			System.out.println("La opcio no es a la llista!");
			break;
		case 1:
			pt.afegirCurs();
			break;
		case 2:
			System.out.println("Introdueix un curs per modificar: ");
			curs = sc.nextLine();
			pt.setCurs(curs);
			break;
		case 3:
			System.out.println("Introdueix el curs a mostrar:");
			curs = sc.nextLine();
			pt.cursos.get(curs).printCurs();
			break;
		case 4:
			System.out.println("Introdueix un curs a eliminar:");
			curs = sc.nextLine();
			pt.cursos.remove(curs);
			System.out.println("Curs " + curs + " eliminat.");
			break;
		case 5:
			System.out.println("Fins aviat!");
			sc.close();
			return true;
		}
		return false;
	}

	public void afegirCurs() {
		String curs;
		// Atributs de curs
		String tutor;
		ArrayList<String> alumnes = new ArrayList<String>();
		String alumne;
		boolean afegirNouModul = true;
		String yesornot = "";
		HashMap<String, Modul> moduls = new HashMap<String, Modul>();
		System.out.println("Introdueix el nom del curs:");
		curs = sc.nextLine();
		System.out.println("Introdueix el nom del tutor del curs");
		tutor = sc.nextLine();
		System.out.println("Introdueix els alumnes del curs (Introdueix una X per a sortir):");
		do {
			alumne = sc.nextLine();
			if (!alumne.equalsIgnoreCase("x"))
				alumnes.add(alumne);
		} while (alumne.equalsIgnoreCase("x"));
		do {
			System.out.println("Vols afegir moduls?(S/N)");
			yesornot = sc.nextLine();
			if (yesornot.equalsIgnoreCase("s")) {
				moduls = afegirModul();
			} else if (yesornot.equalsIgnoreCase("n")) {
				afegirNouModul = false;
			} else {
				System.out.println("Has de ficar una 'S' per a si, o una 'N' per a no!");
				continue;
			}
		} while (afegirNouModul);
		Curs c = new Curs(curs, );

	}

}
