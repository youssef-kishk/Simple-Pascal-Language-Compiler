package parser;

import lexicalAnalysis.LexicalAnalyser;

public class Var extends Parser{
	public String id;
	//VAR <id-list>
	public static void var(){
		//<id-list> ::= id | <id-list>, id
		while(true){
			Var v = new Var();
			v.id=LexicalAnalyser.tokenStreams.get(++i)[2];
			instructionsArrangment.add(v);
			if(!checkToken(i+1).equals("16")){
				return;
			}
		}
	}
}
