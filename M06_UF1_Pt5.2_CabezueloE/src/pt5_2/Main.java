package pt5_2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	static Scanner sc = new Scanner(System.in);

	public static void iniciAplicacio() {
		boolean fi = false;
		String nomCurs, tutor;
		String nomAlumne;
		ArrayList<String> alumnes = new ArrayList<String>();
		int idCurs = 0;
		System.out.println("Primer hem de crear un curs.");
		do {
			System.out.println("Introdueix l'ID del curs: ");
			try {
				idCurs = sc.nextInt();
				fi = true;
			} catch (InputMismatchException e) {
				System.out.println("Ha de ser un numero sencer!");
				sc.nextLine();
			}
		} while (!fi);
		fi = false;
		System.out.println("Introdueix el nom del curs: ");
		nomCurs = sc.nextLine();
		System.out.println("Introdueix el nom del tutor del curs: ");
		tutor = sc.nextLine();
		System.out.println("Introdueix alumnes al curs (Escriu X per terminar): ");
		nomAlumne = sc.nextLine();
		while (!fi) {
			if (nomAlumne.equalsIgnoreCase("x"))
				fi = true;
			else
				alumnes.add(nomAlumne);
		}
		System.out.println("Afegint curs " + nomCurs);
		Curs c = new Curs(idCurs, nomCurs, tutor, alumnes);
		System.out.println("Ara hem d'introduir un nou modul: ");

	}

	public static void main(String[] args) {
		iniciAplicacio();
	}
}
