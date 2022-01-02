package Profondeur;

//classe qui definit chaque literal avec son niveau et ses deux fils 1 et 0
public class Noeud {
	int niveau;
	int[] noeud = new int[75];

	public Noeud(int niveau, int[] noeud) {
		this.niveau = niveau;
		this.noeud = noeud.clone();
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public int[] getNoeud() {
		return noeud;
	}

	public void setNoeud(int[] noeud) {
		this.noeud = noeud;
	}

	public Noeud generate_droite() {
		this.noeud[this.niveau] = 1;
		return this;
	}

	public Noeud generate_gauche() {
		this.noeud[this.niveau] = 0;
		return this;
	}

}
