package CodeGenerator;

import java.util.ArrayList;

import parser.Parser;
import parser.Read;
import parser.Write;

public class NonAssignmentGenerator {
	
	static ArrayList<String> vars;
	static int count ;
	
	public static void generateRead(Read read){
		
		vars = new ArrayList<String>();
		CodeGenerator.generatedCode.add("+JSUB XREAD");
		
		//get the number of variables to read
		count = 0;
		count++;
		vars.add(read.id);
		while((CodeGenerator.iterator + 1)<Parser.instructionsArrangment.size() && 
				Parser.instructionsArrangment.get(CodeGenerator.iterator + 1).getClass()
				.toString().equals("class parser.Read")){
			
			CodeGenerator.iterator ++;
			Read r =(Read)Parser.instructionsArrangment.get(CodeGenerator.iterator);
			vars.add(r.id);
			count++;
		
		}
		
		//displaying result
		CodeGenerator.generatedCode.add("WORD "+String.valueOf(count));
		for(int i=0;i<vars.size();i++){
			
			CodeGenerator.generatedCode.add("WORD "+vars.get(i));
			
		}
		
		
	}
	
	public static void generateWrite(Write write){
		
		vars = new ArrayList<String>();
		CodeGenerator.generatedCode.add("+JSUB XWRITE");
		
		//get the number of variables to read
		count = 0;
		count++;
		vars.add(write.id);
		while((CodeGenerator.iterator + 1)<Parser.instructionsArrangment.size() && Parser.instructionsArrangment
				.get(CodeGenerator.iterator + 1).getClass()
				.toString().equals("class parser.Write")){
			
			CodeGenerator.iterator ++;
			Write r =(Write)Parser.instructionsArrangment.get(CodeGenerator.iterator);
			vars.add(r.id);
			count++;
		
		}
		
		//displaying result
		CodeGenerator.generatedCode.add("WORD "+String.valueOf(count));
		for(int i=0;i<vars.size();i++){
			
			CodeGenerator.generatedCode.add("WORD "+vars.get(i));
			
		}
		
		
	}
		
}
