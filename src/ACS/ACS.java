package ACS;

import java.util.ArrayList;
import java.util.Arrays;

import ACS.Solution;
import Profondeur.Satisfiabilite;
import application.Result;
import Aetoile.A_etoile;
public class ACS <E> {
	public final static int Nbr_Litereaux = 75;
	public final static int Max_Iteration = 80;
	public final static int NbrF = 10;
	public final static int fitnesse = 325;
	public static int Satfile=0; 
	

	public final static double[][] Pheromone = new double[75][2];
	public static double[][] Proba = new double[75][2];

	public final static double a = 0.6, b = 0.6, r = 1;
	

	public static int[] MaxProba() {

		double max = Proba[0][0];
		int ligne = 0, colone = 0;
		for (int i = 0; i < Nbr_Litereaux; i++) {
			for (int j = 0; j < 2; j++) {
				if (Proba[i][j] > max) {
					max = Proba[i][j];
					ligne = i;
					colone = j;
				}
			}
		}
		int t[] = { ligne, colone };
		return t;
	}

	public static ArrayList<Solution> genererSolution(Satisfiabilite p) {
		int Sol_init[];
		ArrayList<Solution> init = new ArrayList<Solution>();
		for (int i = 0; i < NbrF; i++) {
			 
			Sol_init = new int[Nbr_Litereaux];
			for (int j = 0; j < Nbr_Litereaux; j++)
				Sol_init[j] = -1;
			 Sol_init=A_etoile.solution(p);
			 
			init.add(new Solution(Sol_init, new Solution().num_satisfied(Sol_init, p)));
		} 
		return init;
	}
	public ArrayList<Solution> genererSolutionn(Satisfiabilite p) {
		int Sol_init[];
		ArrayList<Solution> init = new ArrayList<Solution>();
		for (int i = 0; i < NbrF; i++) {
			Sol_init = new int[Nbr_Litereaux];
			for (int j = 0; j < Nbr_Litereaux; j++){
				Sol_init[j] = -1;
			int random = (int) (Math.random() * 100) % 75;
			if (Math.random() < 0.5)
				Sol_init[random] = 1;
			else
				Sol_init[random] = 0;}
			init.add(new Solution(Sol_init, new Solution().num_satisfied(Sol_init, p)));
		}
		return init;
	}

	public static void Proba(Solution sol, Satisfiabilite p) {

		double somme = 0;
		int t[];
		int tab[] = new int[75];
		for (int i = 0; i < 75; i++)
			tab[i] = -1;

		ArrayList<Double> list = new ArrayList<Double>();

		for (int k = 0; k < Nbr_Litereaux; k++) {
			t = sol.init.clone();
			if (t[k] == -1) {
				t[k] = tab[k] = 1;
				list.add((Math.pow(Pheromone[k][1], a) * Math.pow(new Solution().num_satisfied(tab, p), b)));
				somme += list.get(k * 2);
				t[k] = tab[k] = 0;
				list.add((Math.pow(Pheromone[k][0], a) * Math.pow(new Solution().num_satisfied(tab, p), b)));
				somme += list.get(k * 2 + 1);
			} else {
				list.add((double) 0);
				list.add((double) 0);
			}
		}

		int j = 0;
		for (int i = 0; i < list.size(); i = i + 2) {
			if (list.get(i) == 0) {
				Proba[j][1] = 0;
				Proba[j][0] = 0;
			} else {
				Proba[j][1] = list.get(i) / somme;
				Proba[j][0] = list.get(i + 1) / somme;
			}
			j++;
		}
		double q = 0.1;
		if (Math.random() > q) {
			double proba = 0;
			j = 0;
			int index;
			for (double i : list) {
				proba += i;
				if (proba > Math.random()) {
					if ((j + 1) % 2 == 1) {
						index = (int) ((j + 1) / 2);
						sol.init[index] = 1;
					} else {
						index = (int) (j / 2);
						sol.init[index] = 0;
					}
					sol.fitnesse = new Solution().num_satisfied(sol.init, p);
					break;
				}
				j++;

			}
		} else {
			t = MaxProba();
			if (t[1] == 1)
				sol.init[t[0]] = 1;
			else
				sol.init[t[0]] = 0;
			sol.fitnesse = new Solution().num_satisfied(sol.init, p);
		}

	}

	public static void offline(Solution best) {
		for (int j = 0; j < 75; j++) {
			if (best.init[j] == 0) {
				Pheromone[j][0] = (1 - r) * Pheromone[j][0] + r * (fitnesse - best.fitnesse);
				Pheromone[j][1] = (1 - r) * Pheromone[j][1];
			} else {
				Pheromone[j][0] = (1 - r) * Pheromone[j][0];
				Pheromone[j][1] = (1 - r) * Pheromone[j][1] + r * (fitnesse - best.fitnesse);
			}
		}
	}

	public static Result solution(Satisfiabilite p) {
		
		long st = System.currentTimeMillis();
		
		for (int i = 0; i < 75; i++)
			Pheromone[i][0] = Pheromone[i][1] = 0.1;
		Solution best = new Solution();
		for (int m = 0; m < Max_Iteration; m++) {
			ArrayList<Solution> init = genererSolution(p);
			for (Solution sol : init) {
				for (int l = 0; l < Nbr_Litereaux; l++)
					Proba(sol, p);
				if (sol.fitnesse > best.fitnesse)
					best = sol;
			}
			offline(best);
		}
	return new Result("",""+Arrays.toString(best.init).replace(" ",""),""+best.fitnesse,""+(System.currentTimeMillis()-st));
	}
}
