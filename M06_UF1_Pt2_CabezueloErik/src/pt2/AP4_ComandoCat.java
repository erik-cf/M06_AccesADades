package pt2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AP4_ComandoCat {

	public static void mostraArxiu(String ruta) {
		File f = new File(ruta);
		if (f.exists()) {
			
			boolean notEmpty = false;
			Scanner scF = null;
			
			try {
				scF = new Scanner(f);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			while (scF.hasNext()) {
				notEmpty = true;
				System.out.println(scF.nextLine());
			}
			
			if (!notEmpty) {
				System.out.println("El fitxer esta buit.");
			}
			
		} else {
			System.out.println("Aquest fitxer no existeix.");
		}
	}

	public static void main(String[] args) {
		boolean continua = true;
		Scanner sc = new Scanner(System.in);
		String ruta;
		do {
			System.out.println("Introdueix la ruta d'un fitxer per a mirar un contingut:");
			System.out.println("Escriu fi per a terminar el programa.");
			ruta = sc.nextLine();
			if (ruta.equalsIgnoreCase("fi")) {
				System.out.println("Fins aviat!");
				continua = false;
			} else {
				mostraArxiu(ruta);
			}
		} while (continua);
		sc.close();
	}
}
