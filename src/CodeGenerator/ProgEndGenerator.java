package CodeGenerator;

import parser.Var;

public class ProgEndGenerator {
	
	public static void generateVar(Var var){
			
		CodeGenerator.generatedCode.add(var.id+" RESW 1");	
		
	}
	
	public static void generateEndProg(){
		
		CodeGenerator.generatedCode.add("LDL RETADR");
		CodeGenerator.generatedCode.add("RSUB");
		CodeGenerator.generatedCode.add("END");
		
	}

}
