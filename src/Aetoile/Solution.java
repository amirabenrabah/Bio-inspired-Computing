package Aetoile;

import java.util.ArrayList;

 
 
import Aetoile.Xi;
 

public class Solution {

	public Xi lit;
	public int fitnesse;

	public Solution() {
		super();
	}

	public Solution(Xi lit, int fitnesse) {
		super();
		this.lit = lit;
		this.fitnesse = fitnesse;
	}

	public Xi getLit() {
		return lit;
	}

	public void setLit(Xi lit) {
		this.lit = lit;
	}

	public int getFitnesse() {
		return fitnesse;
	}

	public void setFitnesse(int fitnesse) {
		this.fitnesse = fitnesse;
	}

	
	public static ArrayList<Solution> sort(ArrayList<Solution> list) {
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

	/*public static ArrayList<Solution> Sort(ArrayList<Solution> l){		
		Solution tmp;
		for (int i=0;i<l.size();i++){
			for(int j=i+1;j<l.size();j++){
				
				if(l.get(j).getFitnesse()>l.get(i).getFitnesse()){
					tmp=l.get(j);
					 
					
					l.get(j).setFitnesse(l.get(i).getFitnesse());
					l.get(j).setLit(l.get(i).getLit());;
					
					l.get(i).setFitnesse(tmp.getFitnesse());;
					l.get(i).setLit(tmp.getLit());
					
				}
				
				
			}
		
			}
			
		
	
		
		
		return l;
	}*/

}
