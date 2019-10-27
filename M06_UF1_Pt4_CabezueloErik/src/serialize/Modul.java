package serialize;

import java.util.ArrayList;
import java.util.HashMap;

public class Modul extends Curs {

	private String nom;
	private String profe;
	private ArrayList<String> ufs;

	public Modul() {

	}

	public Modul(String tutor, ArrayList<String> alumnes, HashMap<String, Modul> moduls, String nom, String profe,
			ArrayList<String> ufs) {
		super(tutor, alumnes, moduls);
		this.nom = nom;
		this.profe = profe;
		this.ufs = ufs;
	}

	public Modul(String tutor, ArrayList<String> alumnes, String nom, String profe, ArrayList<String> ufs) {
		super(tutor, alumnes);
		this.nom = nom;
		this.profe = profe;
		this.ufs = ufs;
	}

	public void printUFs() {

	}

	public void printModul() {

	}

	public void setModul(String modul) {
		menuModul();
		accionsModul();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getProfe() {
		return profe;
	}

	public void setProfe(String profe) {
		this.profe = profe;
	}

	public ArrayList<String> getUfs() {
		return ufs;
	}

	public void setUfs(ArrayList<String> ufs) {
		this.ufs = ufs;
	}

	public void menuModul() {
		System.out.println("1 - Canviar el nom al modul.");
		System.out.println("2 - Canviar el professor.");
		System.out.println("3 - Afegir UFs al modul.");
		System.out.println("4 - Canviar de nom una UF.");
		System.out.println("5 - Eliminar una UF.");
		System.out.println("6 - Tornar.");
	}

	public void accionsModul() {
		boolean inputCorrecte = false;
		int opcio = 0;
		String uf, modul, ufNova;
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
			System.out.println("Has de ficar una opció de l'1 al 6!");
			break;
		case 1:
			System.out.println("Introdueix el nou nom del modul: ");
			this.nom = sc.nextLine();
			break;
		case 2:
			System.out.println("Introdueix el nou professor: ");
			this.profe = sc.nextLine();
			break;
		case 3:
			System.out.println("Introdueix les ufs del curs (Introdueix una X per a sortir):");
			do {
				uf = sc.nextLine();
				if (!uf.equalsIgnoreCase("x")) {
					ufs.add(uf);
					System.out.println("Afegida uf " + uf);
				}
			} while (!uf.equalsIgnoreCase("x"));
			break;
		case 4:
			System.out.println("Introdueix el nom de la UF a canviar");
			uf = sc.nextLine();
			if(ufs.contains(uf)) {
				System.out.println("Introdueix el nom per a la nova UF: ");
				ufNova = sc.nextLine();
				ufs.add(ufNova);
				ufs.remove(ufs.indexOf(uf));
			}
			break;
		case 5:
			System.out.println("Introdueix el nom de la UF a esborrar: ");
			uf = sc.nextLine();
			if(ufs.contains(uf)) {
				ufs.remove(ufs.indexOf(uf));
			}else {
				System.out.println("No s'ha trobat aquesta UF.");
			}
			break;
		case 6:
			return;
			
		}

	}
}
