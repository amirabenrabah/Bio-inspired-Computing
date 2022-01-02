package application;

public class Result{
	public String FileName;
	public String Result;
	public String Nb_clause;
	public String Time;
	

	public Result(String FileName,String Result,String Nb_clause,String temp) {
		this.FileName=FileName;
		this.Result=Result;
		this.Nb_clause=Nb_clause;
		this.Time=temp;
	}

}
