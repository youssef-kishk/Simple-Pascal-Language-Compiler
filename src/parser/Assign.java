package parser;

public class Assign extends Parser{
	public static void assign(){
		//check :=
		if(checkToken(i+1).equals("12")){
			new Exp().exp();
			
		} 
		else{
			System.out.println("Invalid Assign Expression");
			System.exit(0);
		}
	}

}
