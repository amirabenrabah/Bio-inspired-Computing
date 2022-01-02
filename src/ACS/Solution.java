package ACS;

import java.util.ArrayList;

import Profondeur.Cls;
import Profondeur.Xi;
import Profondeur.Satisfiabilite;

public class Solution {

	public int init[];
	public int fitnesse;

	public Solution() {
		super();
	}

	public Solution(int[] init, int fitnesse) {
		super();
		this.init = init;
		this.fitnesse = fitnesse;
	}

	public int[] getInit() {
		return init;
	}

	public void setInit(int[] init) {
		this.init = init;
	}

	public int getFitnesse() {
		return fitnesse;
	}

	public void setFitnesse(int fitnesse) {
		this.fitnesse = fitnesse;
	}

	public ArrayList<Solution> max(ArrayList<Solution> list) {
		int index, maxval;Solution solu;
		ArrayList<Solution> listSol = new ArrayList<Solution>();
		while (list.size()!=0) {
			maxval = list.get(0).fitnesse;
			index = 0;
			for (int j = 1; j < list.size() - 1; j++) {
				solu = list.get(j);
				if (solu.fitnesse > maxval) {
					index = j;
					maxval = solu.fitnesse;
				}
			}
			listSol.add(list.get(index));
			list.remove(index);
		}
		return listSol;
	}

	public int num_satisfied(int[] solution, Satisfiabilite p) {
		int tmpindex;
		int ct = 0;
		for (Cls c : p.Clause) {
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
