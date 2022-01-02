package Profondeur;

import java.util.ArrayList;

import Profondeur.Cls;
import Profondeur.Xi;
 

//Cette classe teste la satifiabilité des clauses en testant chaque clause et retourner le nombre de clauses satisfaites
public class Satisfiabilite {
	public ArrayList<Cls> Clause = new ArrayList<>();
	int Num_cls;

	public Satisfiabilite(ArrayList<Cls> Clause, int Num_cls) {
		this.Clause = Clause;
		this.Num_cls = Num_cls;
	}

	public int getNum_cls() {
		return Num_cls;
	}

	public void setNum_cls(int Num_cls) {
		this.Num_cls = Num_cls;
	}

	public ArrayList<Cls> getClause() {
		return Clause;
	}

	public void setClause(ArrayList<Cls> Clause) {
		this.Clause = Clause;
	}
	
	//Retourne la clause entrée est satisfaite ou non
	public boolean Satisfaite(int[] Sol) {
		boolean Satfd = false;
		int TmpIn;
		for (Cls c : Clause) {
			Satfd = false;
			for (Xi L : c.getXis()) {
				TmpIn = L.getXi();
				if (L.signe == false) {
					TmpIn = L.getXi() * -1;
					if (Sol[TmpIn - 1] == 0) {
						Satfd = true;
					}
				} else {
					if (Sol[TmpIn - 1] == 1) {
						Satfd = true;
					}
				}
			}
			if (Satfd == false)
				return false;
		}
		return true;
	}

	//Si le litéral L existe dans la clause C
	public int SearchXi(int x) {
		int cpt = 0, tmp;
		for (Cls c : Clause) {
			for (Xi L : c.getXis()) {
				tmp = L.getXi();
				if (tmp - 1 == x) {
					cpt++;
					break;
				}
			}
			break;
		}
		return cpt;
	}
	//Retourne le nombre de clauses satisfaites
	public int Nbr_Clauses_Satisfaites(int[] Sol) {
		int TmpIn;
		int cpt = 0;
		for (Cls c : Clause) {
			for (Xi L : c.getXis()) {
				TmpIn = L.getXi();
				if (L.signe == false) {
					TmpIn *= -1;
					if (Sol[TmpIn - 1] == 0) {
						cpt++;
						break;
					}
				} else {
					if (Sol[TmpIn - 1] == 1) {
						cpt++;
						break;
					}
				}
			}
		}
		return cpt;
	}
	public  boolean sat(Cls c, int[] sol){
		for (int j=0;j<c.getXis().size();j++){
				if(sol[Math.abs(c.getXis().get(j).Xi)-1]==1 && c.getXis().get(j).Xi>0){
						return true;
				}			
				if(sol[Math.abs(c.getXis().get(j).Xi)-1]==0 && c.getXis().get(j).Xi<0){
						return true;
				}
		}

		
				return  false;
	}
		
		public  int Cout(int[] sol){
			int cpt=0;
			 for(Cls c:Clause)
					if(sat(c,sol))cpt++;
			 return cpt;
			}


		public int Heuristique(int x){
			int ct = 0;
			for (Cls c : Clause){
				for (Xi l : c.getXis()) {
					if (Math.abs(l.getXi()) == x){
						ct++;
						
					}
				}
			}
			return ct;
		}
		public double num_satisfied(int[] solution) {
		int tmpindex;
		double ct = 0;
		for (Cls c : this.Clause) {
			for (Xi l : c.getXis()) {
				tmpindex = l.getXi();
				if (l.signe == false) {
					tmpindex *= -1;
					if (solution[tmpindex - 1] == 0) {
						ct++;
						break;
					}
				} else {
					if (solution[tmpindex - 1] == 1) {
						ct++;
						break;
					}
				}
			}
		}
		return ct;
	}
}
