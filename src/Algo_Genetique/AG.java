package Algo_Genetique;


import java.util.ArrayList;

import Profondeur.Satisfiabilite;
import application.Result;



public class AG {


public Result solution(Satisfiabilite pr) {

		int populationsize = 40;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  ;
		int groupesize = populationsize / 2;
		double mutationrate = 0.03;
		double best = 0;
		int[] bestsol = new int[75];
		int j = 0;
		long st = System.currentTimeMillis();
		ArrayList<nouedd> nouvgeneration = new ArrayList<nouedd>();
		int gen = 0;
		while (!pr.Satisfaite(bestsol)) {
			j = 0;
			ArrayList<nouedd> population = new ArrayList<nouedd>();
			if (nouvgeneration.size() == populationsize) {
				population = new ArrayList<nouedd>();
				population.addAll(max(nouvgeneration));
				nouvgeneration = new ArrayList<nouedd>();
			} else {
				population.addAll(max(createpopulation(populationsize, pr)));
			}
			int vv = 0;
			while (vv < populationsize) {
				if (population.get(vv).getF() > best) {
					best = population.get(vv).getF();
					bestsol = population.get(vv).getD().clone();
				}
				vv++;
			}
			gen++;
			ArrayList<nouedd> tmp = new ArrayList<nouedd>();

			while (j < populationsize) {

				tmp = new ArrayList<nouedd>();
				tmp.addAll(population);
				ArrayList<nouedd> generation1 = new ArrayList<nouedd>();
				generation1.addAll(max(creategeneration(population, groupesize)));

				ArrayList<nouedd> generation2 = new ArrayList<nouedd>();
				generation2.addAll(max(creategeneration(population, groupesize)));

				population = new ArrayList<nouedd>();
				population.addAll(tmp);

				int i = 0;
				double max_pere1 = 0;
				double max_pere2 = 0;
				int index_pere1 = 0;
				int index_pere2 = 0;
				max_pere1 = pr.num_satisfied(generation1.get(0).getD());
				index_pere1 = 0;
				max_pere2 = pr.num_satisfied(generation2.get(0).getD());
				index_pere2 = 0;

				nouedd pere1 = new nouedd(max_pere1, generation1.get(index_pere1).getD());
				nouedd pere2 = new nouedd(max_pere2, generation2.get(index_pere2).getD());

				int max = 1;
				int min = 0;
				int range = max - min + 1;
				i = 0;
				int[] nouv = new int[75];

				while (i < 75) {
					int rand = (int) (Math.random() * range) + min;

					if (Math.random() < 1) {
						nouv[i] = pere1.getD()[i];
					} else {
						nouv[i] = pere2.getD()[i];
					}
					if (mutationrate > Math.random()) {

						if (nouv[i] == 1) {
							nouv[i] = 0;
						}
						if (nouv[i] == 0) {
							nouv[i] = 1;
						}
					}
					i++;
				}

				nouedd nouvv = new nouedd(pr.num_satisfied(nouv), nouv);

				nouvgeneration.add(nouvv);

				j++;

			}

			if (System.currentTimeMillis() - st > 30000) {
				return new Result("","Error","Error","Error Times Out");
			}

		}
		String Result="[";
		for (int w = 0; w < bestsol.length; w++) {
			Result+="|"+bestsol[w];
		}
		Result+="]";

		pr.Satisfaite(bestsol);

		long end = System.currentTimeMillis();


		return new Result("",Result,""+best,""+(end-st));

	}



	public ArrayList<nouedd> creategeneration(ArrayList<nouedd> population, int sizegeneration) {
		ArrayList<nouedd> generation = new ArrayList<nouedd>();
		int max = population.size();
		int min = 0;
		int range = max - min + 1;
		int i = 0;
		nouedd n;
		int rand;
		while (i < sizegeneration) {
			max = population.size() - 1;
			range = max - min + 1;
			rand = (int) (Math.random() * range) + min;
			n = population.get(rand);
			generation.add(n);
			population.remove(rand);
			i++;
		}
		return generation;
	}

	public ArrayList<nouedd> createpopulation(int sizepopulation, Satisfiabilite pr) {
		ArrayList<nouedd> population = new ArrayList<nouedd>();
		int i = 0, j;

		while (i < sizepopulation) {
			j = 0;
			int[] solution = new int[75];
			nouedd p1;
			while (j < solution.length) {
				if (Math.random() < 1)
					solution[j] = 1;
				else
					solution[j] = 0;

				j++;
			}
			p1 = new nouedd(pr.num_satisfied(solution), solution);
			population.add(p1);
			i++;
		}
		return population;
	}

	public ArrayList<nouedd> max(ArrayList<nouedd> list) {
		int tab[] = new int[75];
		int k;

		ArrayList<nouedd> listSol = new ArrayList<nouedd>();
		ArrayList<nouedd> tmp = new ArrayList<nouedd>();
		tmp.addAll(list);
		double maxval = tmp.get(0).getF();
		while (tmp.size() != 0) {
			k = 0;
			for (int j = 0; j < tmp.size(); j++) {
				if (tmp.get(j).getF() > maxval) {
					maxval = tmp.get(j).getF();
					k = j;
					tab = tmp.get(j).getD().clone();

				}
			}
			nouedd s = new nouedd(tmp.get(k).getF(), tab);
			tmp.remove(k);

			listSol.add(s);
		}

		return listSol;
	}

	public class nouedd {

		double f;
		int[] d = new int[75];

		public nouedd(double e, int[] d) {

			this.f = e;
			this.d = d;
		}

		public double getF() {
			return f;
		}

		public void setF(int f) {
			this.f = f;
		}

		public int[] getD() {
			return d;
		}

		public void setD(int[] d) {
			this.d = d;
		}

	}
}
