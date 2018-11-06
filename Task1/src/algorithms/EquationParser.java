package algorithms;
import java.util.*;

public class EquationParser {

	
	
	public static Equation parseString(String s){
	
		//evaluate everything till standard form 
		// 4x(4x^4 + 2x^2) + 3x^2
		
		//base case
		//split at + and -
		//need to figure out which is negative and positive 
		String[] eqns = s.split("[+]");
		Equation total = new Equation();
		for(int i = 0; i<eqns.length; i++){
			//first expression is positive parse a minuses
			String[] eqns2 = eqns[i].split("[-]");
			Equation positive = Equation.basicExpressionParse(eqns2[0]);
			for(int k = 1; k<eqns2.length; k++){
				positive = Equation.subtract(positive, Equation.basicExpressionParse(eqns2[k]));
			}
			total = Equation.addition(total, positive);
		}
		
		return total;
		
		
		//need to split out the negatives 
		
		
		
		//call recursively on brackets 
		
		
	}
}
