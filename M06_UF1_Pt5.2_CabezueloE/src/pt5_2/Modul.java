package pt5_2;

import java.util.ArrayList;

public class Modul extends Curs {

	private int idModul;
	private String titol;
	private ArrayList<String> professors;
	private ArrayList<String> ufs;

	public Modul(int idCurs, String nomCurs, String tutor, ArrayList<String> alumnes, int idModul,
			String titol, ArrayList<String> professors, ArrayList<String> ufs, Curs curs) {
		super(idCurs, nomCurs, tutor, alumnes);
		this.idModul = idModul;
		this.titol = titol;
		this.professors = professors;
		this.ufs = ufs;
	}

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

}
