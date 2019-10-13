package pt5;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Persona implements Serializable {

	// Atributs de persona:
	private String nom;
	private int edat;

	/*
	 * Constructor de la classe Persona
	 */
	public Persona(String nom, int edat) {
		super();
		this.nom = nom;
		this.edat = edat;
	}
	
	/*
	 * Getters i setters
	 */
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getEdat() {
		return edat;
	}

	public void setEdat(int edat) {
		this.edat = edat;
	}

	/*
	 * Metode toString
	 */
	public String toString() {
		return "\nPersona amb nom " + nom + " i edat " + edat + ".\n";
	}
	

	

}
