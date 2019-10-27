package pt5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*
 * Classe per emmagatzemar tots els metodes que tenen a veure amb la creacio, escriptura i lectura dels fitxers XML
 */
public class MetodesDOM {

	/*
	 * Metode que crea el fitxer XML a partir de les dades del fitxer myPeople.dat
	 */
	public static void escriureXML(File dat, File xml)
			throws ParserConfigurationException, IOException, ClassNotFoundException, TransformerException {
		// Declarem variables i objectes que faran les funcions de lectura i escriptura
		FileInputStream fis = new FileInputStream(dat);
		ObjectInputStream ois = new ObjectInputStream(fis);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		// Creem un objecte persona per emmagatzemar els objectes que llegim de l'arxiu
		Persona p;
		// Creem un objecte Document, que sera el que tingui tota la informacio de l'XML
		Document doc = db.newDocument();

		// Creem un element arrel, com que emmagatzenarem persones, li direm així
		Element arrel = doc.createElement("persones");
		// El desem com a fill directe del document XML
		doc.appendChild(arrel);

		// Declarem variables per emmagatzemar les dades dels nodes que tindra el nostre
		// fitxer XML
		Element persona;
		Element nom;
		Element edat;

		// Llegim el primer objecte de myPeople.dat
		p = (Persona) ois.readObject();

		// Mentre que l'objecte p no sigui null, llegirem el fitxer i crearem els nodes
		// XML
		while (p != null) {
			// Creem un node persona, n'hi haura 1 per cada objecte desat al fitxer
			// myPeople.dat
			persona = doc.createElement("persona");
			// El desem com a fill de l'arrel
			arrel.appendChild(persona);

			// Creem un node nom, per emmagatzemar l'atribut Nom de l'objecte persona
			nom = doc.createElement("nom");
			// Desem com a text el nom de la persona
			nom.appendChild(doc.createTextNode(p.getNom()));
			// El desem com a fill del node persona
			persona.appendChild(nom);

			// Creem un node edat, on desarem l'edat
			edat = doc.createElement("edat");
			// Desem a dins com a text l'edat de cada persona llegida
			edat.appendChild(doc.createTextNode(String.valueOf(p.getEdat())));
			// El desem com a fill de persona també.
			persona.appendChild(edat);

			try {
				// Llegim el següent objecte persona del document
				p = (Persona) ois.readObject();
			} catch (Exception e) {
				// Si no queden mes persones que llegir, saltara una excepcio.
				// Dins d'aquesta deixarem p es igual a null, per sortir del bucle
				p = null;
			}
		}
		fis.close();
		ois.close();

		System.out.println("Escribint les dades de myPeople.dat a myPeople.xml...");

		// Ja tenim l'estructura del document dins l'objecte 'doc'
		// Ara cal crear el fitxer amb aquestes dades
		// Necessitarem dels següents objectes per a generar el fitxer XML
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = tf.newTransformer();
		// Comprovem que existeix el fitxer xml per evitar excepcions
		if (comprovaFitxerXML(xml)) {
			// Generem el fitxer XML
			DOMSource doms = new DOMSource(doc);
			StreamResult streamR = new StreamResult(xml);

			t.transform(doms, streamR);

			System.out.println("Fet.");
		} else {
			System.out.println("No s'ha trobat el fitxer XML ni s'ha pogut crear...");
		}

	}

	/*
	 * El següent mètode llegeix el fitxer XML i l'imprimeix per pantalla
	 */
	public static void imprimeixXML(File xml) throws ParserConfigurationException, SAXException, IOException {
		// Declarem les variables que caldran per tal de poder llegir el fitxer:
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(xml);

		// Normalitzem el document XML:
		doc.getDocumentElement().normalize();

		// Creem una llista amb els elements 'persona'
		NodeList nList = doc.getElementsByTagName("persona");

		// Fem un bucle per recorrer tots els nodes 'persona'
		for (int i = 0; i < nList.getLength(); i++) {
			// Agafem el node amb index 'i' dins la llista
			Node n = nList.item(i);
			// Mostrem les dades agafant els fills del node persona i mostrant els seus
			// fills
			System.out.print("\nPersona trobada amb nom: " + n.getChildNodes().item(0).getTextContent());
			System.out.println(" i edat: " + n.getChildNodes().item(1).getTextContent());
		}
	}

	/*
	 * Aquest metode comprova que existeixi el fitxer XML, i si no el crea per
	 * evitar errors.
	 */
	public static boolean comprovaFitxerXML(File xml) throws IOException {
		if (!xml.exists()) {
			xml.createNewFile();
		}
		return true;
	}

}
