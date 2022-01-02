package Profondeur;

import java.util.ArrayList;

//Classe qui definit chaque clause avec chacune de ses literaux avec son chiffre
public class Cls {
	ArrayList<Xi> Xis;
	int num_Xi;

	public Cls(ArrayList<Xi> Xis, int num_Xi) {
		this.Xis = Xis;
		this.num_Xi = num_Xi;
	}

	public ArrayList<Xi> getXis() {
		return Xis;
	}

	public void setXis(ArrayList<Xi> Xis) {
		this.Xis = Xis;
	}

	public int getNum_Xi() {
		return num_Xi;
	}

	public void setNum_Xi(int Num_Xi) {
		this.num_Xi = Num_Xi;
	}
}
