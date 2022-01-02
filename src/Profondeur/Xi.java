package Profondeur;

public class Xi {
	// La classe qui definit chaque literal avec le numéro du X et son signe
	// positive ou negatif
	int Xi;
	public boolean signe;
	

	public Xi(int Xi, boolean signe) {
		this.Xi = Xi;
		this.signe = signe;
	}

	public int getXi() {
		return Xi;
	}

	public void setXi(int Xi) {
		this.Xi = Xi;
	}

	public boolean getSigne() {
		return signe;
	}

	public void setSigne(boolean signe) {
		this.signe = signe;
	}

}
