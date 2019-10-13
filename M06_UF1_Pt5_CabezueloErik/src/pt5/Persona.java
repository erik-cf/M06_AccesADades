package pt5;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Persona implements Serializable {

	

	private String nom;
	private int edat;

	public Persona() {

	}

	public Persona(String nom, int edat) {
		super();
		this.nom = nom;
		this.edat = edat;
	}

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

	

}
