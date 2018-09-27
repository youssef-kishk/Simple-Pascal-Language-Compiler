package CodeGenerator;

import parser.Program;

public class ProgStartGenerator {
	
	public static void generateProgStart(Program prog){
		
		CodeGenerator.generatedCode.add(prog.programName+" Start 0");
		CodeGenerator.generatedCode.add("EXTREF XREAD,XWRITE");
		CodeGenerator.generatedCode.add("STL RETADR");
		CodeGenerator.generatedCode.add("j {EXADDR}");
		CodeGenerator.generatedCode.add("RETADR RESW 1");
		
	}

}
