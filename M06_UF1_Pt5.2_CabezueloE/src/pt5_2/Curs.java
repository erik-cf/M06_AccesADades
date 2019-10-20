package pt5_2;

import java.util.ArrayList;

public class Curs {

	private int idCurs;
	private String nomCurs;
	private String tutor;
	private ArrayList<String> alumnes;

	public Curs(int idCurs, String nomCurs, String tutor, ArrayList<String> alumnes) {
		super();
		this.idCurs = idCurs;
		this.nomCurs = nomCurs;
		this.tutor = tutor;
		this.alumnes = alumnes;
	}

	public int getIdCurs() {
		return idCurs;
	}

	public void setIdCurs(int idCurs) {
		this.idCurs = idCurs;
	}

	public String getNomCurs() {
		return nomCurs;
	}

	public void setNomCurs(String nomCurs) {
		this.nomCurs = nomCurs;
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

}
