package pt2;

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

public class AP8_codiPt1 {

	Scanner sc = new Scanner(System.in);
	public static TreeMap<String, Float> jocs = new TreeMap<String, Float>();

	/*
	 * Aquest mètode inicia el menú y truca a les funcions depenent de l'opció
	 * escollida. Retorna true o false depenent de si l'usuari vol tancar el
	 * programa o no.
	 */
	public boolean inici() throws IOException {
		int opcio = 0;
		boolean opcioValida = false;
		// Mostrem el menu
		System.out.println("Benvingut a la Game!");
		System.out.println("=====================");
		System.out.println("1 - Insertar un joc");
		System.out.println("2 - Modificar un joc");
		System.out.println("3 - Borrar joc");
		System.out.println("4 - Mostrar tots els productes");
		System.out.println("5 - Emmagatzemar el Map en un fitxer (PRACTICA 2)");
		System.out.println("6 - Emplenar el Map amb les dades del fitxer (PRACTICA 2)");
		System.out.println("7 - Borrar tot el Map actual (PRACTICA 2) (Aixo es per a probar que s'emmagatzemi el fitxer en el Map)");
		System.out.println("8 - Sortir");
		// Fem la comprovació de que s'està introduint un número
		try {
			opcio = sc.nextInt();
			sc.nextLine();
			opcioValida = true;
		} catch (Exception e) {
			System.out.println("Has d'introduir una opcio numerica!");
			sc.nextLine();
		}
		// Si hem introduit un numero es truca a la funció que calgui
		if (opcioValida) {
			switch (opcio) {
			default:
				System.out.println("\nLa opcio no es a la llista!\n");
				break;
			// La primera opcio inserta el joc
			case 1:
				if (insertarJoc())
					System.out.println("\nEl joc s'ha desat correctament.\n");
				break;
			// La segona opció modifica un dels jocs
			case 2:
				if (modificaJoc())
					System.out.println("\nEl joc s'ha modificat correctament.\n");
				break;
			// La tercera opcio borra un joc
			case 3:
				if (borraJoc())
					System.out.println("\nEl joc s'ha esborrat correctament.\n");
				break;
			// La opcio quarta mostra tots els jocs
			case 4:
				mostrarProductes();
				break;
			// La opció cinquena emplena el fitxer amb les dades del Map
			case 5:
				if(AP8_GestionarMapsFitxers.emplenarFitxer())
					System.out.println("\nEl fitxer s'ha creat correctament\n");
				break;
			// La sisena opcio agafa les dades del fitxer per a ficarla al TreeMap
			case 6:
				if(AP8_GestionarMapsFitxers.emplenarMap())
					System.out.println("\nEl Map s'ha omplert correctament amb les dades del fitxer\n");
				break;
			// La setena opcio elimina tots els productes que hi hagin dins el TreeMap
			case 7:
				if(AP8_GestionarMapsFitxers.buidarMap())
					System.out.println("\nTots els productes del Map s'han eliminat correctament.\n");
				else
					System.out.println("\nEl TreeMap ja es buit!\n");
				break;
			// Amb la opció 8 sortim del programa
			case 8:
				System.out.println("Fins aviat!");
				return false;
			}
		}
		return true;
	}

	/*
	 * Aquesta funcio inserta els jocs demanant un nom i un preu. Truca a dos
	 * funcions: nomIsNull per comprovar que el nom no sigui buit y compJoc per
	 * comprovar que el joc no existeixi. Retorna true si s'ha desat el joc, false
	 * si no s'ha pogut desar.
	 */
	public boolean insertarJoc() {
		String nom;
		float preu = 0;
		boolean preuErroni = true;
		// Mentre que el nom escrit sigui null, ho demanara fins que s'introdueixi un
		// nom de joc correcte
		do {
			System.out.println("Introdueix el nom del joc:");
			nom = sc.nextLine();
		} while (nomIsNull(nom));
		// Si el nom no existeix demana el preu per a introduir-ho
		if (!compJoc(nom)) {
			System.out.println("Introdueix el preu del joc:");
			// Mentre que no s'insereix un preu correcte (un numero) ho demana.
			while (preuErroni) {
				try {
					preu = sc.nextFloat();
					preuErroni = false;
				} catch (Exception e) {
					System.out.println("Has d'introduir un número!\n");
					sc.nextLine();
				}
			}
			// Quan esta tot correcte, inserim el joc al TreeMap
			jocs.put(nom, preu);
		} else {
			// Si existeix el joc, ho informa i torna a la pantalla d'inici.
			System.out.println("\nAquest joc ja existeix! Prova a modificar-ho en la opció corresponent!\n");
			return false;
		}
		return true;

	}

	/*
	 * Aquesta funcio modifica els jocs demanant un nom per trobar-los i el nou preu
	 * que aplicarà al joc. Truca a dues funcions: compJoc per comprovar que el joc
	 * existeix i nomIsNull per comprovar que el nom del joc no sigui buit. Retorna
	 * true si s'ha pogut modificar el joc, false si no.
	 */
	public boolean modificaJoc() {
		String nom = "";
		float preu = 0;
		boolean preuErroni = true;
		// Mentre que el nom sigui buit ho demanara fins que sigui un nom correcte
		do {
			System.out.println("Introdueix el nom per buscar-ho:");
			nom = sc.nextLine();
		} while (nomIsNull(nom));
		// Comprovem que el joc existeixi per tal de poder-ho modificar
		if (compJoc(nom)) {
			System.out.println("Introdueix el nou preu del joc:");
			while (preuErroni) {
				// Validem que el preu es un numero real
				try {
					preu = sc.nextFloat();
					preuErroni = false;
				} catch (Exception e) {
					System.out.println("Has d'introduir un número!");
					sc.nextLine();
				}
			}
			// Si tot es correcte s'afegeix el joc
			jocs.put(nom, preu);
		} else {
			// En cas que no es pugui perque no existeixi retorna false
			System.out.println("\nAquest joc no existeix! No es pot modificar un joc que no és a la base de dades...\n");
			return false;
		}
		// Si tot surt be, retorna true
		return true;
	}

	/*
	 * Aquesta funció esborra el joc indicat per l'usuari. Truca a dues funcions:
	 * nomIsNull per veure que el joc del nom no sigui buit i compJoc per comprovar
	 * que el joc que s'esborra existeixi
	 */
	public boolean borraJoc() {
		String nom;
		// Demanem el nom i mirem que no sigui buit, si no ho demanara fins que sigui
		// correcte
		do {
			System.out.println("Introdueix el nom del joc:");
			nom = sc.nextLine();
		} while (nomIsNull(nom));
		// Si el joc existeix, ho esborrara
		if (compJoc(nom)) {
			jocs.remove(nom);
			return true;
			// Si no, no pot borrar-ho
		} else {
			System.out.println("\nAquest joc no existeix!\n");
		}
		return false;
	}

	/*
	 * Aquesta funcio mostra els productes disponibles al HashMap. Com es un treeMap
	 * ho ordena automaticament
	 */
	public void mostrarProductes() {
		// Comprovem que no sigui buida
		if (jocs.isEmpty()) {
			System.out.println("\nNo hi ha cap producte!\n");
		} else {
			Iterator<String> it = jocs.keySet().iterator();
			System.out.println("\n-------");
			// Mentre que hi hagi productes per mostrar, els mostra un per un
			while (it.hasNext()) {
				String nomJoc = it.next();
				System.out.println(nomJoc + " - Preu: " + jocs.get(nomJoc));
			}
			System.out.println("-------\n");
		}

	}

	/*
	 * Aquesta funcio comprova si el nom del joc que se li passa per parametre
	 * existeix dins el HashMap. Retorna true si existeix, false si no.
	 */
	public boolean compJoc(String nom) {
		// Si el TreeMap conte la key amb el nom, retorna true
		if (jocs.containsKey(nom))
			return true;
		return false;
	}

	/*
	 * Aquesta funcio comprova que el nom que se li passa per parametre es null o
	 * buit Retorna true si el nom es null, false si el nom es correcte.
	 */
	public boolean nomIsNull(String nom) {
		// Si el nom es null o buit, mostra error i retorna true.
		if (nom == null || nom.equals("")) {
			System.out.println("El nom del joc ha de contenir algun caracter (no pot estar buit)!");
			return true;
		}
		return false;
	}	
	
}
