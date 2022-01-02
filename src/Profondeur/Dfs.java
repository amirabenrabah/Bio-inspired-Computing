package Profondeur;

import java.util.Stack;

import application.Result;
//classe qui retourne une instantiation en utilisant la recherche en profondeur
public class Dfs {
	public Result Dfs_Sol(Satisfiabilite p) {
		 
				
		int nbr_var = 75;
		int[] P = new int[nbr_var];
		int[] Best = new int[nbr_var];
		Stack<Noeud> solution = new Stack<Noeud>();
		Noeud Pere = new Noeud(-1, P);
		Noeud Gauche, Droite;
		long SystemCurrentTime = System.currentTimeMillis();

		while (!p.Satisfaite(Pere.getNoeud())) {
			if (Pere.getNiveau() < 74) {
				Gauche = new Noeud(Pere.getNiveau() + 1, Pere.getNoeud());
				Gauche = Gauche.generate_gauche();
				Droite = new Noeud(Pere.getNiveau() + 1, Pere.getNoeud());
				Droite = Droite.generate_droite();
				solution.push(Droite);
				solution.push(Gauche);
			}

			if (p.Nbr_Clauses_Satisfaites(Pere.getNoeud()) > p.Nbr_Clauses_Satisfaites(Best)) {
				for (int i = 0; i < Best.length; i++) {
					Best[i] = Pere.getNoeud()[i];
				}
			}
			Pere = solution.pop();

			if (System.currentTimeMillis() - SystemCurrentTime > 60000) {
				break;
			}

		}
		String tmp="[",tmp1="",tmp2="",tmp3="";
		for (int k=0;k<Best.length;k++)
			tmp+=Best[k]+"|";	
		tmp+="]";
		
	
		tmp2+=""+ p.Nbr_Clauses_Satisfaites(Best);
		tmp3+=""+(System.currentTimeMillis() - SystemCurrentTime);

		

		return new Result("",tmp,tmp2,tmp3);

	}

}
