package pt5_2;

import java.util.ArrayList;

public class Modul extends Curs {

	/***************************************************************
	 * Utilitzant un fitxer XML s�emmagatzemaran les dades dels cursos dels cicles
	 * que s�imparteixen al centre.
	 * 
	 * Els aspectes que cal tenir en compte s�n els seg�ents:
	 * 
	 * Respecte a un curs:
	 * 
	 * Un curs t� un codi identificatiu. Un curs t� un tutor i una llista d�alumnes.
	 * Un curs t� com a m�nim un m�dul.
	 * 
	 * Respecte a un m�dul:
	 * 
	 * Un m�dul t� un codi identificatiu. Un m�dul t� un t�tol. T� un professor com
	 * a m�nim. T� una s�rie d�unitats formatives.
	 * 
	 * 
	 * Cal realitzar el corresponent fitxer XML mitjan�ant un programa java.
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
