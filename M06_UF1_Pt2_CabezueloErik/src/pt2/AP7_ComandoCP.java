package pt2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AP7_ComandoCP {
	
	public static void copiaFitxers(String rutaArxiu, String rutaCopia) {
		String fileText = "";
		File original = new File(rutaArxiu);
		File copia = new File(rutaCopia);
		PrintWriter pw;
			try {
				Scanner scOriginal = new Scanner(original);
				if (!copia.exists()) {
					copia.createNewFile();
				}
				pw = new PrintWriter(rutaCopia);
				while (scOriginal.hasNext()) {
					fileText = fileText + scOriginal.nextLine() + "\r\n";
				}
				pw.write(fileText);
				pw.close();
				scOriginal.close();
				System.out.println("\nFitxer copiat!\n");
			} catch (FileNotFoundException e) {
				System.out.println("\nAquest fitxer no existeix!\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String rutaArxiu;
		String rutaCopia;
		boolean exit = false;
		do {
			System.out
					.println("Introdueix la ruta de l'arxiu que vols copiar(Introdueix FI per terminar el programa: ");
			rutaArxiu = sc.nextLine();
			if (!rutaArxiu.equalsIgnoreCase("fi")) {
				System.out.println("Introdueix la ruta on vols copiar l'arxiu:");
				rutaCopia = sc.nextLine();
				copiaFitxers(rutaArxiu, rutaCopia);
			} else {
				System.out.println("Fins aviat!");
				exit = true;
			}
		} while (!exit);
		sc.close();
	}
}
