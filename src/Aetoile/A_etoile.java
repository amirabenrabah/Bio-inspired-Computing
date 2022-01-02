package Aetoile;

import java.util.ArrayList;
import java.util.Arrays;


import Profondeur.Satisfiabilite;
import application.Result;
import Aetoile.Xi;

public class A_etoile {
	public final static int Nbr_Litereaux = 75;
	public final static long Temp_d = 60000;  
	public final static int CoutMax = 325;
	 	
	public static Result solution(Satisfiabilite p,int crt) {
		ArrayList<Solution> ouverte = new ArrayList<Solution>();
		ArrayList<Solution> fermer3=new ArrayList<Solution>();
		int[] fermer = new int[75];
		int[] fermer2 = new int[75];
		Arrays.fill(fermer2,-1);
		Arrays.fill(fermer,-1);
		

		for(int i=0;i<Nbr_Litereaux;i++){
			ouverte.add(new Solution(new Xi(i+1,false,0),0));
			ouverte.add(new Solution(new Xi(i+1,true,1),0));
		}
		
		
		long st = System.currentTimeMillis();
		int fitness=0;
		while(!ouverte.isEmpty()  && (System.currentTimeMillis() - st < Temp_d)){
			for (int i=0;i<ouverte.size();i++){ 
				fermer2[Math.abs(ouverte.get(i).getLit().getXi())-1]=ouverte.get(i).getLit().value;
				ouverte.get(i).setFitnesse(p.Heuristique(ouverte.get(i).getLit().getXi()) + p.Cout(fermer2));
				if(p.Cout(fermer2)==CoutMax){
						break;
								}
				fermer2[Math.abs(ouverte.get(i).getLit().getXi())-1]=-1;	
			}
	
		   ouverte=Solution.sort(ouverte);
		   fermer[ouverte.get(0).getLit().getXi()-1]=ouverte.get(0).getLit().getXi();
		  
		   
		   if (ouverte.get(0).getLit().signe){
			   fermer2[Math.abs(ouverte.get(0).getLit().getXi())-1]=1;
		   }else{
			   fermer2[Math.abs(ouverte.get(0).getLit().getXi())-1]=0;
		   }		   
		   fitness=ouverte.get(0).getFitnesse();
		   ouverte.remove(0);
		   
		   for (int k=0;k<ouverte.size();k++){
			   if (ouverte.get(k).getLit().getXi() == fermer[ouverte.get(k).getLit().getXi()-1]){
				   ouverte.remove(k);
			   }
			   
		   }
		    


   
		}
		String tmp="[",tmp1="",tmp2="",tmp3="";
		for (int k=0;k<fermer2.length;k++)
			tmp+=fermer2[k]+"|";	
		tmp+="]";
		
		tmp1+=""+fitness;
		tmp2+=""+p.Cout(fermer2);
		tmp3+=""+(System.currentTimeMillis() - st);
		
	
		return new Result("",tmp,tmp1,tmp3);
	
	}
	
	public static int[] solution(Satisfiabilite p) {
		ArrayList<Solution> ouverte = new ArrayList<Solution>();
		
		int[] fermer = new int[75];
		 
		int[] fermer2 = new int[75];
		Arrays.fill(fermer2,-1);
		Arrays.fill(fermer,-1);
		

		for(int i=0;i<Nbr_Litereaux;i++){
			ouverte.add(new Solution(new Xi(i+1,false,0),0));
			ouverte.add(new Solution(new Xi(i+1,true,1),0));
		}
		
		
		long st = System.currentTimeMillis();
		
		 
		int fitness=0;
		while(!ouverte.isEmpty()  && (System.currentTimeMillis() - st < Temp_d)){
			for (int i=0;i<ouverte.size();i++){ 
				fermer2[Math.abs(ouverte.get(i).getLit().getXi())-1]=ouverte.get(i).getLit().value;
				ouverte.get(i).setFitnesse(p.Heuristique(ouverte.get(i).getLit().getXi()) + p.Cout(fermer2));
				if(p.Cout(fermer2)==CoutMax){
						break;
								}
				fermer2[Math.abs(ouverte.get(i).getLit().getXi())-1]=-1;	
			}
	
		   ouverte=Solution.sort(ouverte);
		   fermer[ouverte.get(0).getLit().getXi()-1]=ouverte.get(0).getLit().getXi();
		  
		   
		   if (ouverte.get(0).getLit().signe){
			   fermer2[Math.abs(ouverte.get(0).getLit().getXi())-1]=1;
		   }else{
			   fermer2[Math.abs(ouverte.get(0).getLit().getXi())-1]=0;
		   }		   
		   fitness=ouverte.get(0).getFitnesse();
		   ouverte.remove(0);
		   
		   for (int k=0;k<ouverte.size();k++){
			   if (ouverte.get(k).getLit().getXi() == fermer[ouverte.get(k).getLit().getXi()-1]){
				   ouverte.remove(k);
			   }
			   
		   }
		    


   
		}
 
	
	return fermer2;
	
	
	}
	
	
	 
	 
}


