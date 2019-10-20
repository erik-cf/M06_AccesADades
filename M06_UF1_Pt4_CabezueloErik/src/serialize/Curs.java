package serialize;

import java.util.ArrayList;
import java.util.HashMap;

public class Curs extends Modul {
	
	private String tutor;
	private ArrayList<String> alumnes;
	private HashMap<String, Modul> moduls;
	
	public Curs() {
		
	}
	
	public Curs(String nom, String profe, ArrayList<String> ufs, String tutor, ArrayList<String> alumnes,
			HashMap<String, Modul> moduls) {
		super(nom, profe, ufs);
		this.tutor = tutor;
		this.alumnes = alumnes;
		this.moduls = moduls;
	}

	public Modul getModul(String modul) {
		return moduls.get(modul);
	}
	
	public void printCurs() {
		System.out.println("\nTutor del curs" + this.tutor);
		System.out.println("Alumnes del curs:");
		for(String alumne : alumnes) {
			System.out.println("\t - " + alumne);
		}
		System.out.println("\nModuls del curs: ");
		for(String key : moduls.keySet()) {
			System.out.println("\tMODUL " + moduls.get(key).getNom());
			System.out.println("\t\tProfe: " + moduls.get(key).getProfe());
			System.out.println("\t\tUFs: " + moduls.get(key).getProfe());
			for(String uf : moduls.get(key).getUfs()) {
				System.out.println("\t\t\t - " + uf);
			}
		}
	}
	
	public void setCurs(String curs) {
		menuSetCurs();
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
	
	public HashMap <String, Modul> afegirModul() {
		String nom;
		String profe;
		ArrayList<String> ufs;
		
		return moduls;
	}
	
}
