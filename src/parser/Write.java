package parser;

import lexicalAnalysis.LexicalAnalyser;

public class Write extends Parser{
	public String id;
	public static void write(){
		//Statement start with open bracket
		if(checkToken(++i).equals("14")){
			int rowNumber=Integer.parseInt(LexicalAnalyser.tokenStreams.get(i)[0]);
			while(true){
				Write w= new Write();
				++i;
				//if WRITE row ended without ) symbol
				if(Integer.parseInt(LexicalAnalyser.tokenStreams.get(i)[0])>rowNumber){
					System.out.println("Invalid WRITE Expression format");
					System.exit(0);
				}
				//add id to its arrayList
				if(checkToken(i).equals("16")){
					w.id=LexicalAnalyser.tokenStreams.get(i)[2];
					instructionsArrangment.add(w);
				}
				//check ,
				else if(checkToken(i).equals("18")){}
				//check )
				else if(checkToken(i).equals("15"))
					return;
			}
		}
		else{
			System.out.println("Invalid Write Expression format");
			System.exit(0);
		}
	}

}
