package serialize;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Pt2 extends Curs {

	private HashMap<Integer, Curs> cursos;

	public Pt2(String tutor, String profe, ArrayList<String> ufs, String tutor2, ArrayList<String> alumnes,
			HashMap<Integer, Modul> moduls, HashMap<Integer, Curs> cursos) {
		super(tutor, profe, ufs, tutor2, alumnes, moduls);
		this.cursos = cursos;
	}

	public void dadesIn(File f) {

	}

	public void dadesOut(File f) {

	}

	public HashMap<Integer, Curs> getCursos() {
		return cursos;
	}

	public void setCursos(HashMap<Integer, Curs> cursos) {
		this.cursos = cursos;
	}

	public static void main(String[] args) {
		boolean fi = false;
		int opcio = 0;
		String curs;
		Scanner sc = new Scanner(System.in);
		while (!fi) {
			menu();
			try {
				opcio = sc.nextInt();
			}catch(InputMismatchException e) {
				System.out.println("Has de ficar un numero enter!");
				sc.nextLine();
				continue;
			}
			switch(opcio) {
			default:
				System.out.println("La opcio no es a la llista!");
				break;
			case 1:
				afegirCurs();
				break;
			case 2:
				System.out.println("Introdueix un curs per modificar: ");
				curs = sc.nextLine();
				setCurs(curs);
			}
			
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
	
	public static void afegirCurs() {
		
	}

}
