package CodeGenerator;

import parser.Exp;

public class AssignmentGenerator {
	
	static String var;
	static String op;
	//can be done with stack in order to more optimizing using LOAD A
	
	public static void generateAssignment(Exp ex){
		
		int i = 0;
		int j = 0;
		var = ex.variables.get(i++);
		if(!var.equals(CodeGenerator.regA)){
			
			CodeGenerator.generatedCode.add("LDA "+var);
		}
		
		while(j < ex.operations.size()){
			
			var = ex.variables.get(i++);
			op = ex.operations.get(j++);
			
			if(op.equals("+"))
				CodeGenerator.generatedCode.add("ADD "+var);
			
			else if(op.equals("*"))
				CodeGenerator.generatedCode.add("MUL "+var);
			
			else{
				System.out.println("SHOULD NO ENTER! Error in operator!");
				System.exit(0);
			}
			
		}
			CodeGenerator.generatedCode.add("STA "+ex.leftSideVar);
			CodeGenerator.regA = ex.leftSideVar;
	}

}
