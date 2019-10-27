package pt5_2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MetodesDOM {

	/*
	 * Objectes que necessitarem per a la gestio del fitxer XML
	 */
	static DocumentBuilderFactory dbf;
	static DocumentBuilder db;
	static Document doc;
	static Element arrel;
	static File arxiu;
	static TransformerFactory tf;
	static Transformer t;
	static DOMSource source;
	static StreamResult sR;

	/*
	 * Metode que comprova si existeix el fitxer per tal de saber si cal
	 * inicialitzar-ho o nomes cal llegir-ho
	 */
	public static boolean inicialitzarFitxer()
			throws SAXException, IOException, ParserConfigurationException, TransformerException {
		// Com aquest metode es truca si o si, inicialitzem totes les instancies
		// principals de DOM
		dbf = DocumentBuilderFactory.newInstance();
		db = dbf.newDocumentBuilder();
		// Fitxer XML
		arxiu = new File("institut.xml");
		tf = TransformerFactory.newInstance();
		t = tf.newTransformer();
		sR = new StreamResult(arxiu);
		// Si l'arxiu no existeix
		if (!arxiu.exists()) {
			// Creem el nou document
			doc = db.newDocument();
			// Creem l'arrel del document
			arrel = doc.createElement("cursos");
			// La fiquem com a filla del document
			doc.appendChild(arrel);
			// Escribim les dades al fitxer
			writeFile();
			// Retornem false perque no hi havia cap fitxer
			return false;
		// Si el fitxer ja existia
		} else {
			// Llegim el fitxer
			doc = db.parse(arxiu);
			// Agafem l'arrel del document
			arrel = doc.getDocumentElement();
			// Retornem true ja que existia el document
			return true;
		}

	}

	/*
	 * Metode que elimina un curs del fitxer XML
	 */
	public static void eliminaCursDOM(Curs c) throws TransformerException {
		// Agafem l'arrel
		Element cursosElement = doc.getDocumentElement();
		// Fem una llista de cursos
		NodeList cursos = doc.getElementsByTagName("curs");
		// Recorrem la llista
		for (int i = 0; i < cursos.getLength(); i++) {
			// Agafem cada curs
			Node curs = cursos.item(i);
			// Comprovem si l'id es igual al del curs que volem eliminar
			if (Integer.parseInt(curs.getAttributes().getNamedItem("id").getNodeValue()) == c.getIdCurs()) {
				// L'eliminem
				cursosElement.removeChild(curs);
			}
		}
		// Escrivim les dades
		writeFile();
	}

	/*
	 * Metode que elimina un modul d'un curs del fitxer XML
	 */
	public static void eliminaModulDOM(Curs c, Modul m) throws TransformerException {
		// Fem una llista dels cursos
		NodeList cursos = doc.getElementsByTagName("curs");
		NodeList moduls;
		Element curs;
		Node modul;
		Node modulsNode;
		// Recorrem la llista
		for (int i = 0; i < cursos.getLength(); i++) {
			// Agafem els elements d'aquesta
			curs = (Element) cursos.item(i);
			// Si coincideix l'id del Node amb l'id de l'objecte del que volem treure el modul, entrem a la condicio
			if (Integer.parseInt(curs.getAttributes().getNamedItem("id").getNodeValue()) == c.getIdCurs()) {
				// Fem una llista dels moduls
				moduls = curs.getElementsByTagName("modul");
				// Agafem l'arrel dels moduls <moduls>
				modulsNode = curs.getElementsByTagName("moduls").item(0);
				// Recorrem els moduls
				for (int j = 0; j < moduls.getLength(); j++) {
					modul = moduls.item(j);
					// Si l'id del node modul coincideix amb l'id del modul que volem eliminar entrem a la condicio
					if (Integer.parseInt(modul.getAttributes().getNamedItem("id").getNodeValue()) == m.getIdModul()) {
						// Eliminem el modul
						modulsNode.removeChild(modul);
					}

				}
			}
		}
		// Escrivim els canvis
		writeFile();
	}

	/*
	 * Metode que afegeix moduls al curs especificat
	 */
	public static void afegirModulsCurs(Curs c, Modul m) throws TransformerException {
		Node moduls;
		Element curs;
		// Llista de cursos
		NodeList cursos = doc.getElementsByTagName("curs");
		for (int i = 0; i < cursos.getLength(); i++) {
			// Agafem cada curs
			curs = (Element) cursos.item(i);

			// Si coincideixen ids, entrem a la condicio
			if (Integer.parseInt(curs.getAttributes().getNamedItem("id").getNodeValue()) == c.getIdCurs()) {

				// Si els curs no te moduls...
				if (curs.getElementsByTagName("modul").getLength() == 0) {
					// Creem l'element <moduls> que engloba tots els moduls d'un curs
					moduls = doc.createElement("moduls");
					curs.appendChild(moduls);
				} else {
					// Si el curs té moduls
					// Agafem el node <moduls> existent
					moduls = curs.getElementsByTagName("moduls").item(0);
				}

				// Creem els diferents elements
				Element modul = doc.createElement("modul");
				Attr idM = doc.createAttribute("id");
				idM.setValue(String.valueOf(m.getIdModul()));
				modul.setAttributeNode(idM);
				moduls.appendChild(modul);

				Element titol = doc.createElement("titol");
				titol.appendChild(doc.createTextNode(m.getTitol()));
				modul.appendChild(titol);

				Element professors = doc.createElement("professors");
				modul.appendChild(professors);

				Element professor;
				for (String prof : m.getProfessors()) {
					professor = doc.createElement("professor");
					professor.appendChild(doc.createTextNode(prof));
					professors.appendChild(professor);
				}

				Element ufs = doc.createElement("ufs");
				modul.appendChild(ufs);

				Element uf;
				for (String ufNom : m.getUfs()) {
					uf = doc.createElement("uf");
					uf.appendChild(doc.createTextNode(ufNom));
					ufs.appendChild(uf);
				}
			}
		}
		// Escrivim les dades noves
		writeFile();
	}

	/*
	 * Metode que afegeix un curs al fitxer XML
	 */
	public static void afegirCursDOM(Curs c) throws TransformerException {
		// Creem els elements
		Element curs = doc.createElement("curs");
		Attr id = doc.createAttribute("id");
		id.setValue(String.valueOf(c.getIdCurs()));
		curs.setAttributeNode(id);
		arrel.appendChild(curs);

		Element nomCurs = doc.createElement("nom");
		nomCurs.appendChild(doc.createTextNode(c.getNomCurs()));
		curs.appendChild(nomCurs);

		Element tutor = doc.createElement("tutor");
		tutor.appendChild(doc.createTextNode(c.getTutor()));
		curs.appendChild(tutor);

		Element alumnes = doc.createElement("alumnes");
		curs.appendChild(alumnes);

		Element alumne;
		for (String nomAlumne : c.getAlumnes()) {
			alumne = doc.createElement("alumne");
			alumne.appendChild(doc.createTextNode(nomAlumne));
			alumnes.appendChild(alumne);
		}
		//Escrivim les dades
		writeFile();
	}

	/*
	 * Metode que modifica un modul. La funcio que fa es eliminar el vell i crear el nou amb els canvis fets
	 */
	public static void modificaModulDOM(Curs c, Modul vell, Modul nou) throws TransformerException {
		eliminaModulDOM(c, vell);
		afegirModulsCurs(c, nou);
	}

	/*
	 * Metode que modifica un curs. La funcio que fa es eliminar el vell i crear el nou amb els canvis fets
	 */
	public static void modificaCursDOM(Curs vell, Curs nou) throws TransformerException {
		eliminaCursDOM(vell);
		afegirCursDOM(nou);
	}

	/*
	 * Metode que escriu les dades al fitxer XML
	 */
	public static void writeFile() throws TransformerException {
		source = new DOMSource(doc);
		t.transform(source, sR);
	}

	/*
	 * Metode que crea objectes i HashMaps a partir de les dades del fitxer. Aquest metode s'executa si a l'iniciar l'aplicacio ja existeix el fitxer.
	 */
	public static Main crearObjectes() {
		// Objectes i variables necessaries per a crear les instancies de les classes i llegir les dades
		NodeList cursos = doc.getElementsByTagName("curs");
		NodeList moduls;
		Node aux;
		NodeList listAux;
		Element curs;
		Element modul;
		
		Curs c;
		Modul m = new Modul();
		Main main = new Main();
		
		HashMap<String, Curs> hmCurs = new HashMap<String, Curs>();
		HashMap<String, Modul> hmModul;

		ArrayList<String> alumnes = new ArrayList<String>();
		ArrayList<String> professors = new ArrayList<String>();
		ArrayList<String> ufs = new ArrayList<String>();
		
		// Recorrem els cursos
		for (int i = 0; i < cursos.getLength(); i++) {
			
			// Inicialitzem/Reinicialitzem diferents objectes
			hmModul = new HashMap<String, Modul>();
			alumnes = new ArrayList<String>();
			c = new Curs();

			// Agafem el curs
			curs = (Element) cursos.item(i);

			// Fiquem valors a la instancia de curs
			c.setIdCurs(Integer.parseInt(curs.getAttributes().getNamedItem("id").getNodeValue()));
			c.setNomCurs(curs.getElementsByTagName("nom").item(0).getTextContent());
			c.setTutor(curs.getElementsByTagName("tutor").item(0).getTextContent());

			// Creem una llista dels alumnes del curs
			listAux = curs.getElementsByTagName("alumne");
			for (int j = 0; j < listAux.getLength(); j++) {
				aux = listAux.item(j);
				alumnes.add(aux.getTextContent());
			}
			c.setAlumnes(alumnes);

			// Creem una llista dels moduls del curs
			moduls = curs.getElementsByTagName("modul");
			// Recorrem la llista
			for (int j = 0; j < moduls.getLength(); j++) {
				//Agafem el modul
				modul = (Element) moduls.item(j);

				// Reinicialitzem objectes
				m = new Modul();
				professors = new ArrayList<String>();
				ufs = new ArrayList<String>();

				// Afegim les dades a la instancia de modul
				m.setIdModul(Integer.parseInt(modul.getAttributes().getNamedItem("id").getNodeValue()));

				m.setTitol(modul.getElementsByTagName("titol").item(0).getTextContent());

				listAux = modul.getElementsByTagName("professor");
				for (int k = 0; k < listAux.getLength(); k++) {
					aux = listAux.item(k);
					professors.add(aux.getTextContent());
				}
				m.setProfessors(professors);

				listAux = modul.getElementsByTagName("uf");
				for (int k = 0; k < listAux.getLength(); k++) {
					aux = listAux.item(k);
					ufs.add(aux.getTextContent());
				}
				m.setUfs(ufs);
				// Fiquem el modul al hashmap de moduls
				hmModul.put(m.getTitol(), m);
			}
			// Li donem valor al hashmap de moduls del curs
			c.setModuls(hmModul);

			// Fiquem el curs al hashmap de cursos
			hmCurs.put(c.getNomCurs(), c);
		}
		// Fiquem el hashmap de cursos a la classe principal
		main.setCursos(hmCurs);
		return main;
	}
}
