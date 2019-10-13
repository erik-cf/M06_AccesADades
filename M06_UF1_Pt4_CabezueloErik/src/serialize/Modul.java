package serialize;

import java.io.Serializable;
import java.util.ArrayList;

public class Modul implements Serializable {
	
	private String tutor;
	private String profe;
	private ArrayList<String> ufs;
	
	public Modul(String tutor, String profe, ArrayList<String> ufs) {
		super();
		this.tutor = tutor;
		this.profe = profe;
		this.ufs = ufs;
	}

	public void printUFs() {
		
	}
	
	public void printModul() {
		
	}
	
	public void setModul(String modul) {
		
	}

	public String getTutor() {
		return tutor;
	}

	public void setTutor(String tutor) {
		this.tutor = tutor;
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

}
