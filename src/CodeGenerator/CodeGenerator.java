package CodeGenerator;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import parser.Parser;
import parser.Exp;
import parser.Program;
import parser.Read;
import parser.Var;
import parser.Write;

public class CodeGenerator {
	
	//Array list for all generated code 
	public static ArrayList<String> generatedCode = new ArrayList<String>();
	static String regA = "0";
	static int iterator ;
	
	public static void generateCode() throws FileNotFoundException{
			
			int flag = 0;
			for( iterator = 0;iterator<Parser.instructionsArrangment.size();iterator++){		

				
				//Program name with before-code instructions:
				//NAME - EXTREF - STL RETADR  - J EXTADR
				if(Parser.instructionsArrangment.get(iterator).getClass().toString().equals("class parser.Program")){
					
					Program prog = (Program) Parser.instructionsArrangment.get(iterator);
					ProgStartGenerator.generateProgStart(prog);
					
				}
				
				//check assignment statement
				else if(Parser.instructionsArrangment.get(iterator).getClass().toString().equals("class parser.Exp")){
					
					if(flag == 0){
						generatedCode.add("{EXADDR} LDA #0");
						flag = 1;
					}
					
					Exp ex = (Exp) Parser.instructionsArrangment.get(iterator);
					AssignmentGenerator.generateAssignment(ex);

				}
				
				//check non assignment statements
				// READ - Write 
				else if(Parser.instructionsArrangment.get(iterator).getClass().toString().equals("class parser.Read")){  
					
					if(flag == 0){
						generatedCode.add("{EXADDR} LDA #0");
						flag = 1;
					}
					
					Read read =(Read) Parser.instructionsArrangment.get(iterator);
					NonAssignmentGenerator.generateRead(read);
					
				}
				
				else if(Parser.instructionsArrangment.get(iterator).getClass().toString().equals("class parser.Write")){
				
					if(flag == 0){
						generatedCode.add("{EXADDR} LDA #0");
						flag = 1;
					}
					
					Write write = (Write) Parser.instructionsArrangment.get(iterator);
					NonAssignmentGenerator.generateWrite(write);
				
				}
							
				//Adding VARs
				else if(Parser.instructionsArrangment.get(iterator).getClass().toString().equals("class parser.Var")){
					
					Var var = (Var) Parser.instructionsArrangment.get(iterator); 
					ProgEndGenerator.generateVar(var);
					
				}

			}
			
			//end program
			ProgEndGenerator.generateEndProg();
			
			
			writeFile();
				
		}

	private static void writeFile() throws FileNotFoundException{
		
		//show result
		System.out.println("\n >GENERATED CODE<");
		for(int i=0; i<generatedCode.size();i++)	
			System.out.println(generatedCode.get(i));
		
		//write Assembly code in new file
		PrintWriter p =new PrintWriter("AssemblyCode.txt");
			for(int i=0;i<generatedCode.size();i++){
				p.println(generatedCode.get(i));
			}
			p.close();
			
	}

}
