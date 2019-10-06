package beca;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	// Declarem un Scanner global per tal d'accedir des de tota la classe a aquest
	static Scanner sc = new Scanner(System.in);
	// Declarem l'arxiu que utilitzarem per llegir i escriure les dades de la beca
	static File f = new File("becadades.dat");

	/*
	 * El mètode menu ens mostra les opcions a les que l'usuari pot accedir per
	 * consola.
	 */
	public static void menu() {
		System.out.println("Benvingut al gestor de beques");
		System.out.println("1 - Introduir dades de les beques");
		System.out.println("2 - Llista les dades dels alumnes que soliciten la beca");
		System.out.println("3 - Fer backup de la informació de beques");
		System.out.println("4 - Sortir");
		System.out.println("Introdueix una opcio:");
	}

	/*
	 * El metode introduccioDades permet a l'usuari introduir per consola les seves
	 * dades referents a la beca i després aquest metode ho introdueix al fitxer
	 * becadades.dat
	 */
	public static boolean introduccioDades() throws IOException {
		// Declarem variables
		String nomCognom = "";
		char sexe = 'O';
		int opt;
		int edat = 0;
		int numSuspensos = -1;
		boolean residenciaFamiliar = false;
		float ingressosFamilia = 0;
		boolean inputCorrecte = false;
		// Els objectes que escriuran al nostre fitxer
		FileOutputStream fos = new FileOutputStream(f, true);
		DataOutputStream dos = new DataOutputStream(fos);

		// Demanem les dades i validem que siguin correctes
		System.out.println("Introdueix el nom i el cognom del becari:");
		nomCognom = sc.nextLine();

		while (!inputCorrecte) {
			System.out.println("Introdueix el sexe del becari:\n\t 1- Home\n\t2-Dona");
			try {
				opt = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Has de ficar una opció valida! 1 per home o 2 per a dona");
				sc.nextLine();
				continue;
			}
			switch (opt) {
			default:
				System.out.println("Has de ficar 1 per home, o 2 per a dona");
				break;
			case 1:
				sexe = 'H';
				inputCorrecte = true;
				break;
			case 2:
				sexe = 'M';
				inputCorrecte = true;
			}
		}
		inputCorrecte = false;

		while (!inputCorrecte) {
			System.out.println("Introdueix l'edat del becari:");
			try {
				edat = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Has de ficar un numero enter!");
				sc.nextLine();
				continue;
			}
			if (edat < 20 || edat > 60)
				System.out.println("L'edat ha d'estar entre 20 i 60 anys...");
			else
				inputCorrecte = true;
		}
		inputCorrecte = false;

		while (!inputCorrecte) {
			System.out.println("Introdueix el numero de suspensos del becari al curs anterior (Entre 0 i 4):");
			try {
				numSuspensos = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Has de ficar un numero enter!");
				sc.nextLine();
			}
			if (numSuspensos < 0 || numSuspensos > 4)
				System.out.println("El numero de suspensos ha d'estar entre 0 i 4");
			else
				inputCorrecte = true;
		}
		inputCorrecte = false;

		while (!inputCorrecte) {
			System.out.println("Introdueix si actualment es viu a la residencia familiar:\n\t 1 - Si\n\t2 - No");
			try {
				opt = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Has de ficar una opció valida! 1 per SI o 2 per NO");
				sc.nextLine();
				continue;
			}
			switch (opt) {
			default:
				System.out.println("Has de ficar 1 per SI, o 2 per NO");
				continue;
			case 1:
				residenciaFamiliar = true;
				break;
			case 2:
				residenciaFamiliar = false;
			}
			inputCorrecte = true;
		}
		inputCorrecte = false;

		while (!inputCorrecte) {
			System.out.println("Introdueix els ingressos de tota la unitat familiar del becari:");
			try {
				ingressosFamilia = sc.nextFloat();
				inputCorrecte = true;
			} catch (InputMismatchException e) {
				System.out.println("Has de ficar un numero!");
				sc.nextLine();
				continue;
			}
		}

		// Escribim les dades al fitxer per ordre.
		// Es important saber com les estem escribint per poder llegirles despres

		// La primera dada que escriurem es el length() del nom, posat que hem de saber
		// els bytes que hem de llegir a l'hora de mostrar-los, ja que cada nom te una
		// llargada diferent.
		dos.writeInt(nomCognom.length());
		dos.writeChars(nomCognom);
		dos.writeChar(sexe);
		dos.writeInt(edat);
		dos.writeInt(numSuspensos);
		dos.writeBoolean(residenciaFamiliar);
		dos.writeFloat(ingressosFamilia);
		dos.close();
		fos.close();
		return true;
	}

	/*
	 * Aquest metode mostra una llista amb les dades que es troben dins el fitxer
	 * becadades.dat
	 */
	public static void mostraDades() throws IOException {
		// Declarem les variables que utilitzarem
		int lengthNom;
		String nom;
		char sexe;
		boolean residenciaFamiliar;
		// Aquests objectes ens ajudaran a llegir les dades del fitxer
		FileInputStream fis = new FileInputStream(f);
		DataInputStream dis = new DataInputStream(fis);
		// Mentre que hi hagi dades per llegir, fem el bucle:
		while (dis.available() != 0) {
			System.out.println("\n--------------------------\n");
			lengthNom = dis.readInt();
			nom = "";
			// Per això abans hem guardat el length del nom, per saber quants bytes haurem
			// de llegir
			for (int i = 0; i < lengthNom; i++) {
				nom = nom + String.valueOf(dis.readChar());
			}
			// Mostrem les dades
			System.out.println("Nom i cognoms: " + nom);
			sexe = dis.readChar();
			if (sexe == 'H') {
				System.out.println("Sexe: Home");
			} else {
				System.out.println("Sexe: Dona");
			}
			System.out.println("Edat: " + dis.readInt());
			System.out.println("Numero Suspensos: " + dis.readInt());
			System.out.print("Viu a la residencia familiar? ");
			residenciaFamiliar = dis.readBoolean();
			if (residenciaFamiliar) {
				System.out.println("Si");
			} else {
				System.out.println("No");
			}
			System.out.println("Ingressos unitat familiar: " + dis.readFloat());
			System.out.println("\n--------------------------\n");

		}
		fis.close();
		dis.close();

	}

	/*
	 * Aquest metode fa un backup del fitxer de dades becadades.dat a
	 * becadadesBK.dat
	 */
	public static boolean backupDades() throws IOException {
		// Declarem les variables i objectes
		File backup = new File("becadadesBK.dat");
		FileInputStream fis = new FileInputStream(f);
		DataInputStream dis = new DataInputStream(fis);
		FileOutputStream fos = new FileOutputStream(backup);
		DataOutputStream dos = new DataOutputStream(fos);
		System.out.println("\nIniciant backup de dades a becadadesBK.dat...");
		// Llegim el fitxer becadades.dat i l'escribim al becadadesBK.dat
		while (dis.available() != 0) {
			dos.writeByte(dis.readByte());
		}
		dis.close();
		fis.close();
		dos.close();
		fos.close();
		return true;
	}

	/*
	 * Aquest metode ens comprova si existeix el fitxer becadades.dat En cas que no
	 * existeixi, ens ho crea per poder evitar errors.
	 */
	public static void creaFitxer(File ex) throws IOException {
		if (!ex.exists())
			ex.createNewFile();
	}

	public static void main(String[] args) {
		// Declarem les variables necessaries
		int opt = -1;
		boolean exit = false;
		while (!exit) {
			// Cridem al menu
			menu();
			try {
				// Llegim la opcio ficada per l'usuari:
				opt = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Has de ficar un numero!");
				sc.nextLine();
				continue;
			}
			sc.nextLine();
			switch (opt) {
			default:
				// Si l'usuari fica una opcio que no és a la llista:
				System.out.println("Aquesta opció no es troba al menú...");
				break;
			case 1:
				try {
					// Si esocgeix 1, voldra introduir dades
					if (introduccioDades())
						System.out.println("Introduides les dades\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				// Si escogeix 2, voldra mostrar-les
				try {
					mostraDades();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				try {
					// Si escogeix 3, voldra fer backup de les dades
					if (backupDades())
						System.out.println("Backup de les dades fet correctament.\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:
				// Si escogeix 4, voldra sortir
				exit = true;
				sc.close();
				System.out.println("Fins aviat!");
			}
		}
	}
}
