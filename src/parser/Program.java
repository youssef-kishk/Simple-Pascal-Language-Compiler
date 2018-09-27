package parser;

import lexicalAnalysis.LexicalAnalyser;

public class Program extends Parser{
	public String programName;
	//PROGRAM <prog-name>
	 
	 public Program (){
		 
	 }
	 
	 
	public  void prog(){
		//<prog-name> ::= id
		programName=LexicalAnalyser.tokenStreams.get(++i)[2];
		if(programName.equals(""))
			System.out.println("Invalid code");
	}
}
