package pt2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AP6_EscribirLineas {
	public static void main(String[] args) {
		String archivo = "escribeLineas.txt";
		File f = new File(archivo);
		FileWriter wr = null;
		try {
			if(f.exists()) {
				f.createNewFile();
			}
			wr = new FileWriter(archivo, true);
			for(int i = 1; i <= 10; i++) {
				wr.write("Linea " + i + "\r\n");
			}
			System.out.println("Escribint linees...");
			wr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
