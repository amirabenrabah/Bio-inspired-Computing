package Aetoile;

public class Xi {
	// La classe qui definit chaque literal avec le numéro du X et son signe
	// positive ou negatif
	int Xi;
	public boolean signe;
	 public int value;

	public Xi(int Xi, boolean signe,int value) {
		this.Xi = Xi;
		this.signe = signe;
		this.value=value;
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
