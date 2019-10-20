package pt5_2;

import java.util.ArrayList;

public class Modul extends Curs {

	/***************************************************************
	 * Utilitzant un fitxer XML s’emmagatzemaran les dades dels cursos dels cicles
	 * que s’imparteixen al centre.
	 * 
	 * Els aspectes que cal tenir en compte són els següents:
	 * 
	 * Respecte a un curs:
	 * 
	 * Un curs té un codi identificatiu. Un curs té un tutor i una llista d’alumnes.
	 * Un curs té com a mínim un mòdul.
	 * 
	 * Respecte a un mòdul:
	 * 
	 * Un mòdul té un codi identificatiu. Un mòdul té un títol. Té un professor com
	 * a mínim. Té una sèrie d’unitats formatives.
	 * 
	 * 
	 * Cal realitzar el corresponent fitxer XML mitjançant un programa java.
	 */

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
