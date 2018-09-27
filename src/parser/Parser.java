package parser;

import java.util.ArrayList;

import lexicalAnalysis.LexicalAnalyser;

public class Parser {
	public static ArrayList <Parser> instructionsArrangment = new ArrayList<Parser>();
	static int i=0;
	public static String checkToken(int i){
		return LexicalAnalyser.tokenStreams.get(i)[1];
	}
	public static void tokensStreamLoop(){
		while(i<LexicalAnalyser.tokenStreams.size()){
			//Program
			if(checkToken(i).equals("1")){
				Program p = new Program();
				p.prog();
				instructionsArrangment.add(p);
				
			}
			//VAR
			else if(checkToken(i).equals("2")){
				Var.var();
			}
			//BEGIN
			else if(checkToken(i).equals("3")){
				begin();
			}
			i++;
		}
	}
	//BEGIN <stmt-list>
	private static void begin(){
		//<stmt> ::= <assign> | <read> | <write> | <for>
		while(!checkToken(i+1).equals("5")){
			++i;
			//<read>::= READ ( <id-list> )
			if(checkToken(i).equals("7")){
				Read.read();
			}
			//<write>::= WRITE ( <id-list> )
			else if(checkToken(i).equals("8")){
				Write.write();
			}
			//<assign>::= id := <exp>
			//check id
			else if(checkToken(i).equals("16")){
				Assign.assign();
			}
		}
		//for(int i=0;i<instructionsArrangment.size();i++)
		//	System.out.println(instructionsArrangment.get(i).getClass());
	}
	
	
}
