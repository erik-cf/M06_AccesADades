package serialize;

import java.util.ArrayList;
import java.util.HashMap;

public class Curs extends Modul {
	
	private String tutor;
	private ArrayList<String> alumnes;
	private HashMap<Integer, Modul> moduls;
	
	public Curs(String tutor, String profe, ArrayList<String> ufs, String tutor2, ArrayList<String> alumnes,
			HashMap<Integer, Modul> moduls) {
		super(tutor, profe, ufs);
		tutor = tutor2;
		this.alumnes = alumnes;
		this.moduls = moduls;
	}

	public static Modul getModul(String modul) {
		return null;
	}
	
	public void printCurs() {
		
	}
	
	public static void setCurs(String curs) {
		
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

	public HashMap<Integer, Modul> getModuls() {
		return moduls;
	}

	public void setModuls(HashMap<Integer, Modul> moduls) {
		this.moduls = moduls;
	}
	
}
