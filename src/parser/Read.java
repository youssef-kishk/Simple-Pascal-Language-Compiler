package parser;

import lexicalAnalysis.LexicalAnalyser;

public class Read extends Parser{
	public String id;
	public static void read(){
	//Statement start with open bracket
	if(checkToken(++i).equals("14")){
		int rowNumber=Integer.parseInt(LexicalAnalyser.tokenStreams.get(i)[0]);
		while(true){
			Read r= new Read();
			++i;
			//if READ row ended without ) symbol
			if(Integer.parseInt(LexicalAnalyser.tokenStreams.get(i)[0])>rowNumber){
				System.out.println("Invalid Read Expression format");
				System.exit(0);
			}
			//add id to its arrayList
			if(checkToken(i).equals("16")){
				r.id=LexicalAnalyser.tokenStreams.get(i)[2];
				instructionsArrangment.add(r);
			}
			//check ,
			else if(checkToken(i).equals("18")){}
			//check )
			else if(checkToken(i).equals("15"))
				return;
		}
	}
	else{
		System.out.println("Invalid Read Expression format");
		System.exit(0);
	}
}

}
