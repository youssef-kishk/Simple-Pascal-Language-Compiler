package parser;

import java.util.ArrayList;

import lexicalAnalysis.LexicalAnalyser;

public class Exp extends Parser{
	
	public String leftSideVar;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<String> variables = new ArrayList();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<String> operations = new ArrayList();
	static int bracketsOperationPosition;
	static int bracketsVariablePosition;
	public  void exp(){
		Exp e = new Exp();
		e.leftSideVar=LexicalAnalyser.tokenStreams.get(i)[2];
		i=i+2;
		bracketsOperationPosition = operations.size();
		bracketsVariablePosition = variables.size();
		while(true){
		try{
			if(checkToken(i).equals("11")){
				instructionsArrangment.add(e);
				return;
			}
			//check +
			if(checkToken(i).equals("13"))
				e.operations.add("+");	
			//check *
			else if(checkToken(i).equals("17"))
				e.operations.add("*");
			//check (
			else if (checkToken(i).equals("14")){
				i++;
				while(!checkToken(i).equals("15")){
					if(checkToken(i).equals("13")){
						e.operations.add(bracketsOperationPosition, "+");
						bracketsOperationPosition++;
						i++;
					}
					else if(checkToken(i).equals("17")){
						e.operations.add(bracketsOperationPosition, "*");
						bracketsOperationPosition++;
						i++;
					}
					else{
						e.variables.add(bracketsVariablePosition,LexicalAnalyser.tokenStreams.get(i)[2]);
						bracketsVariablePosition++;
						i++;
					}
				}
			}
			else
				e.variables.add(LexicalAnalyser.tokenStreams.get(i)[2]);
				i++;
			}
			catch(Exception ex){
					System.out.println("Invalid Assign Expressionaa");
					System.exit(0);
			}
		}
		
	}

}
