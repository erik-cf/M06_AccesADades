package pt2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AP2_ReadLineFileJava {
	public static void main(String[] args) {
		Scanner scF = null;
		File f = new File("src\\pt2\\ReadLineFileJava.java");
		try {
			scF = new Scanner(f);
		} catch (FileNotFoundException e) {
			System.out.println("No s'ha trobat el fitxer a " + f.getAbsolutePath());
		}
		while (scF.hasNext()) {
			System.out.println(scF.nextLine());
		}
	}
}
