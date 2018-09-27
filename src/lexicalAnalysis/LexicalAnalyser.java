package lexicalAnalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class LexicalAnalyser {

	
	static File file2 = new File("compilerProg.txt");
	private static String Prog = "";
	public static List<String[]> tokenStreams = new ArrayList<String[]>();
	public static ArrayList<String> vars = new ArrayList<String>();
	
	
	//read input program in a single line string
	public static void readInputProg() throws FileNotFoundException{
		Scanner in = new Scanner(file2);
		String result = "";
		String temp = "";
		
		temp = in.nextLine();
		
		if(!in.hasNext()){
			
			result = temp;
		}
		else{
			
			temp = removeSpaces(temp);
			result += temp +" ";
			while(in.hasNextLine()){
				
				temp = in.nextLine();
				temp = removeSpaces(temp);
				result += temp +" ";
				
			}
			
		}
		//a5er el satr 7aykoun fih space zyada.
		
		in.close();
		Prog = result;
		System.out.println("\n >PROGRAM<");
		System.out.println(Prog);
		
	}
	
	//helper function
	private static String removeSpaces(String temp){
		
		String result = "";
		result = temp.replace(" ", "");
		result = result.replace("\t", "");
		
		return result;
	}
	
	//helper functions
	//record VARs
	private static void recordVARs(){
		
		//VARs start at row[3] in tokenStreams
		for(int t=3; tokenStreams.get(t)[0].equals("3"); t++){
			
			vars.add(tokenStreams.get(t)[2]);
			
		}
		
		System.out.println("\n >VARIABLES<");
		System.out.println(vars);
		
		
	} 
	
	//find a given VAR
	private static boolean findVAR(String find){
		return vars.contains(find);
	}
	
	//output a token stream table 
	//Each row in tokenStreams is in the following format:
	// line - token - tokenSpecifier
	public static void tokenStreams(){
		
		int line = 1;
		String tokenValue = "";
		String tokenSpecifier = "";
		String temp = "";
		String instTemp = "";
		String opTemp = "";
		int i = 0;
		int k = 0;
		
			
			//First, you must find PROGRAMM Name
			for(i=0; i<7;i++){
				temp += Prog.charAt(i);
			}
			if (temp.equals("PROGRAM")){
				//PROGRAM
				tokenValue = Token.tokenCoding.get("PROGRAM");
				tokenStreams.add(new String[] {String.valueOf(line), tokenValue});
				
				//ProgNAME
				temp = "";
				for(i=7; Prog.charAt(i)!= ' ';i++){
					temp += Prog.charAt(i);
				}
				//check if it's a valid <id>
				if(Token.idValidation(temp)){
					tokenValue = Token.tokenCoding.get("id");
					tokenSpecifier = temp;
					tokenStreams.add(new String[] {String.valueOf(line), tokenValue, tokenSpecifier});
					
				}else{
					
					System.out.println("Line: "+line+" Syntax Error! You Must enter a valid Program Name!");
					System.exit(0);
					
				}
								
			}else {
				
				System.out.println("Line: "+line+" Syntax Error! You Must Begin by PROGRAM");
				System.exit(0);
				
			}

			i++;
			
			//check for VAR and all variables
			temp = "";
			line++;
			for( k=i; Prog.charAt(k)!= ' ';k++){
				
				temp += Prog.charAt(k);
				
			}
			if (temp.equals("VAR")){
				//add VAR to list
				tokenValue = Token.tokenCoding.get("VAR");
				tokenStreams.add(new String[] {String.valueOf(line), tokenValue});
				
				//consider all variables after VAR
				i = ++k;
				temp = "";
				line++;
				for(k=i; Prog.charAt(k)!= ' '; k++){
					
					while(Prog.charAt(k)!= ',' && Prog.charAt(k) != ' ' ){
						temp += Prog.charAt(k++);
						if(Prog.charAt(k) == ' '){
							k--;
							break;
						}
					}
					
					
					//get a variable
					if(temp == null){
						
							System.out.println("Line: "+line+" Syntax Error! You Must write a var before ,");
							System.exit(0);
							
							
					}
						//if it's a valid <id>, put it into list
					if(Token.idValidation(temp)){
							tokenValue = Token.tokenCoding.get("id");
							tokenSpecifier = temp;
							tokenStreams.add(new String[] {String.valueOf(line), tokenValue, tokenSpecifier});
							temp = "";
						}else{
							
							System.out.println("Line: "+line+" Syntax Error! You Must enter a valid VAR!");
							System.exit(0);
							
						}

				}

			
			}else{

				System.out.println("Line: "+line+" Syntax Error! VAR must be followed by Valid VARs");
				System.exit(0);
				
				
			}
			
				
			//check BEGIN word
			line++;
			i = ++k;
			temp = "";
			for(k=i; Prog.charAt(k)!=' ';k++){
				temp += Prog.charAt(k);
			}
			
			if(temp.equalsIgnoreCase("BEGIN")){
				
				//add Begin to list
				tokenValue = Token.tokenCoding.get("BEGIN");
				tokenStreams.add(new String[] {String.valueOf(line), tokenValue});
				
			}else{
				
				System.out.println("Line: "+line+" Syntax Error! Instructions must start with BEGIN!");
				System.exit(0);
				
			}
			
			//record valid VARs
			recordVARs();
			
			//begin reading all instructions until finding END.
			i = ++k;
			line++;
			temp = "";
			
			for(k=i; k<Prog.length(); k++){
	
				//get an instruction in temp
				while(Prog.charAt(k) != ' '){
					temp += Prog.charAt(k++);
				}
				
				if(temp.contains(":=")){
						
					//assignment instructions
					instTemp = "";
					for(int t = 0; t<temp.length(); t++){
					
							instTemp += temp.charAt(t++); 
							
						
						if(Token.idValidation(instTemp)){
							
							while(Token.idValidation(instTemp)){
								instTemp += temp.charAt(t++);
							}
							
							//take only the <id>
							instTemp = instTemp.substring(0, instTemp.length()-1);
							

							//if it's predefined,store it and its next operator
							if(findVAR(instTemp)){
								
								tokenValue = Token.tokenCoding.get("id");
								tokenSpecifier = instTemp;
								tokenStreams.add(new String[] {String.valueOf(line), tokenValue, tokenSpecifier});
								
								//check the next operand
								instTemp = "" + temp.charAt(--t);
								if(instTemp .equals(":")){
									instTemp += temp.charAt(++t);
									if(instTemp.equals(":=")){
										
										tokenValue = Token.tokenCoding.get(":=");
										tokenStreams.add(new String[] {String.valueOf(line), tokenValue});
										
										
									}else{
										
										System.out.println("Line: "+line+" Syntax Error! Use only ':=' in Assignment instructions!");
										System.exit(0);
										
									}			
									
								}else{
									
									tokenValue = Token.tokenCoding.get(instTemp);
									
									if(tokenValue == null ){
										
										System.out.println("Line: "+line+" Syntax Error! Invalid Operator!");
										System.exit(0);
										
									}else{
									
										tokenStreams.add(new String[] {String.valueOf(line), tokenValue});
																			
									}
								}
								
								
							}else{
								
								System.out.println("Line: "+line+" Syntax Error! You must declare the Var!");
								System.exit(0);
								
							}
							
						}
//						else if(instTemp.equals("(")| instTemp.equals(")") | instTemp.equals(";")){
							else if(Token.tokenCoding.get(instTemp)!= null){
							
							tokenValue = Token.tokenCoding.get(instTemp);
							tokenStreams.add(new String[] {String.valueOf(line), tokenValue});
							t--;
							
						}
						else{
							
							System.out.println("Line: "+line +" Syntax Error! Not a Valid Expression!");
							System.exit(0);
							
						}
						
						instTemp = "";
						
					}
						
					
				}else if(temp.startsWith("READ(") | temp.startsWith("WRITE(")){
					
					instTemp = "";
					int f =0;
					if(temp.startsWith("READ(")){
						
						tokenValue = Token.tokenCoding.get("READ");
						f = 5;
						
					}else{
						
						tokenValue = Token.tokenCoding.get("WRITE");
						f = 6;
						
					}
					
					tokenStreams.add(new String[] {String.valueOf(line), tokenValue});
					
					tokenValue = Token.tokenCoding.get("(");
					tokenStreams.add(new String[] {String.valueOf(line), tokenValue});
					
					for(int t=f; t<temp.length()-1; t++){
						
						instTemp = "" + temp.charAt(t);
						while(Token.idValidation(instTemp)){
							instTemp += temp.charAt(++t);
							
						}
						
						//take only <id>
						instTemp = instTemp.substring(0, instTemp.length()-1);

						//check if it's Valid
						if (!findVAR(instTemp)){
							
							System.out.println("Line: "+line+" Syntax Error! VARs must be predefined!");
							System.exit(0);
							
						}else{
							
							tokenValue = Token.tokenCoding.get("id");
							tokenSpecifier = instTemp;
							tokenStreams.add(new String[] {String.valueOf(line), tokenValue, tokenSpecifier});
						
						}
						
						//check next symbol
						tokenValue = Token.tokenCoding.get(""+temp.charAt(t));
						tokenStreams.add(new String[] {String.valueOf(line), tokenValue});
					
					}
					
				
				}	
					
				else if(temp.equals("END.")){
					
					tokenValue = Token.tokenCoding.get("END.");
					tokenStreams.add(new String[] {String.valueOf(line), tokenValue});

				}else{	
					System.out.println("Line: "+line+" Syntax Error! Invalid instruction!");
					System.exit(0);
				}
				
				temp = "";
				instTemp = "";
				line++;
				
			}
			//check if program ends with END.
			if(!tokenValue.equals("5")){	
				
				System.out.println("Line: "+line+" Syntax Error! Instructions must end with END.!");
				System.exit(0);
				
			}
	}
	
	
}
