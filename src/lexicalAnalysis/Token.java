package lexicalAnalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Token {
	
	static File file1 = new File("tokenscoding.txt");
	static HashMap <String,String> tokenCoding = new HashMap<String,String>();
	
	
	//Reading the tokenscoding.txt values to a HashMap
	//Format: Value - code
	public static void readTokenCoding() throws FileNotFoundException{
		Scanner in = new Scanner(file1);
		int check;
			String s;
			String temp1;
			String temp2;
			
		while(in.hasNext()){
			
			s = in.nextLine();
			check = 0;
			temp1 ="";
			temp2 ="";
			
			for(int i=0;i<s.length();i++){
				if(check == 1)
					temp2 += s.charAt(i);
				else if(s.charAt(i)!=' ')
					temp1 += s.charAt(i);
				else
					check=1;
			}
			
			tokenCoding.put(temp1, temp2);
		}
		
		in.close();
		
		System.out.println("\n >TOKEN CODING<");
		System.out.println(tokenCoding);
	}

	//Regex pattern validation for <id>
	 public static boolean idValidation(String line){
		 
	      String pattern = "([A-Za-z]+)([0-9A-Za-z]*)";
	      
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);

	      // Now create matcher object.
	      Matcher m = r.matcher(line);
	      
	     return m.matches();
	}
	 
	
	//Regex pattern validation for <int>
	 static boolean intValidation(String line){
			 
		      String pattern = "([0-9]+)";

		      // Create a Pattern object
		      Pattern r = Pattern.compile(pattern);

		      // Now create matcher object.
		      Matcher m = r.matcher(line);
		      
		      return m.matches();
		}

	
	
}
