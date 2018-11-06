package algorithms;
import java.util.ArrayList;


public class Equation {
	static //where coeff[n] = a in ax^n
	int maxDegree = 99;
	ArrayList<Double> coeff = new ArrayList<Double>(99);
	
	
	public Equation(){
		this(maxDegree);
	}

	public Equation(int size){
		coeff = new ArrayList<Double>(size);
		for(int i = 0; i<size; i++){
			coeff.add(0.0);
		}
	}
	

	
	public double compute(double x1){
		double sum = 0;
		for(int i = 0; i<this.coeff.size(); i++){
			if(coeff.get(i) == 0){
				continue;
			}
			sum += coeff.get(i) * Math.pow(x1, i);
		}
		return sum;
	}
	
	//solves the equation for f(x) = 0 using newton's method 
	public static double solve(Equation a){
		double x1 = 1;
		double approx0 = 0.00001;
		
		for(int i = 0; i<200; i++){
			double fx = a.compute(x1);
			double dx = (a.compute(x1+approx0) - a.compute(x1));
			dx /= approx0;
			x1 = x1 - (fx/dx);
		}
		if(Math.abs(a.compute(x1)) > 0.1){
			return Double.NaN;
		}
		return x1;
	}
	
	
	public static Equation deepcopy(Equation a){
		Equation b = new Equation();
		
		for(int i = 0; i<a.coeff.size(); i++){
			b.coeff.set(i,a.coeff.get(i));
		}
		
		return b;		
	}
	
	
	public String toEqnString(){
		String s = "";
		for(int i = 0; i<this.coeff.size(); i++){
			if(this.coeff.get(i) != null && this.coeff.get(i) > 0){
				s +=" + " + this.coeff.get(i) + "x^" + i;
			} else if (this.coeff.get(i) != null && this.coeff.get(i) < 0){
				s +=(" - " + this.coeff.get(i) + "x^" + i);
			}
		}
		return s;
	}
	
	public static Equation Multiply(Equation a, Equation b){
		Equation c = new Equation(99); 
		
		for(int i = 0; i<a.coeff.size(); i++){
			for(int k = 0; k<b.coeff.size(); k++){
				if(a.coeff.get(i) == 0 || b.coeff.get(k) == 0){
					continue;
				}
				int degree = i + k;
				if(c.coeff.get(degree) == null){
					c.coeff.set(degree, 0.0);
				}
				double init = c.coeff.get(degree);
				c.coeff.set(degree, init + a.coeff.get(i) * b.coeff.get(k));
			}
		}
		
		return c;
	}
	
	//adds two equations 
	public static Equation addition(Equation a, Equation b){
		Equation c = deepcopy(b);
		for(int i = 0; i<a.coeff.size(); i++){
			c.coeff.set(i, a.coeff.get(i) + c.coeff.get(i));
		}
		return c;
	}
	
	public static Equation subtract(Equation a, Equation b){
		Equation c = deepcopy(b);
		for(int i = 0; i<a.coeff.size(); i++){
			c.coeff.set(i, a.coeff.get(i) - c.coeff.get(i));
		}
		return c;
	}
	
	
	public static Equation basicExpressionParse(String s){
		s = s.trim();
		//check if contains x 
		Equation n = new Equation(99);
		int degree = 0;
		double a;
		if(!s.contains("x")){
			n.coeff.set(0, Double.parseDouble(s));
			return n;
		} else{
			//coeff = [0,x]
			String argstr = s.subSequence(0,s.indexOf("x")).toString();
			if(argstr.length() == 0){
				a = 1;
			} else{
				a = Double.parseDouble(argstr);
			}
			//try to find carat
			if(!s.contains("^")){
				degree = 1;	
			} else{
				String sub = s.subSequence(s.indexOf("^")+1,s.length()).toString();
				degree = (int) Double.parseDouble(sub);
			}
		}
		
		n.coeff.set(degree, a);
		return n;
	}
	
	
	public static void main(String args[]){
		Equation a = EquationParser.parseString("500x+2");
		System.out.println(solve(a));
	}	
}
