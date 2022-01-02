package Profondeur;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

//Cette classe lit les données de chaque fichier et fait le prétraitement de ce dernier	

public class ReadData {

	public static ArrayList<Cls> Read(String filename) {
		ArrayList<Cls> Clauses = new ArrayList<>();
		ArrayList<Xi> Xis = null;
		Xi Xi;
		int a;

		BufferedReader reader = null;
		String ligne;
		String[] lig;

		try {
			reader = new BufferedReader(new FileReader(filename));
			while ((ligne = reader.readLine()) != null) {
				if (!(ligne.startsWith("c")) && !(ligne.startsWith("p")) && !(ligne.startsWith("0"))
						&& !(ligne.startsWith("%"))) {
					if (ligne.startsWith(" "))
						ligne = ligne.replaceFirst(" ", "");
					lig = ligne.split(" ");
					Xis = new ArrayList<>();
					for (int i = 0; i < 3; i++) {
						a = Integer.parseInt(lig[i]);
						if (a < 0) {
							Xi = new Xi(a, false);
						} else
							Xi = new Xi(a, true);

						Xis.add(Xi);
					}
					Cls c = new Cls(Xis, 20);
					Clauses.add(c);
				}
			}
			reader.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return Clauses;
	}

}
