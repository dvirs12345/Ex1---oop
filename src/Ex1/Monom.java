
package Ex1;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	
	private void set_coefficient(double a){
		this._coefficient = a;
	}
	
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	
	private static Monom getNewZeroMonom() {return  Monom.ZERO;}
	private double _coefficient; 
	private int _power;
	
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
		if (this.get_coefficient() == 0)             // ask Boaz!!
			this._power = 0;
	}
	
	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative of this monom.
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	/**
	 * this method returns the value of this monom/function at x. 
	 */
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	
	public boolean isZero() {return this.get_coefficient() == 0;}
	
	/**
	 * this function builds a monom from a string e.g.:
	 * {x, x^5, -9, 5X^2} 
	 */
	public Monom(String s) {
		if( s==null ) 
			throw new RuntimeException("Got null string: "+s);
		if( s.length()==0 ) 
			throw new RuntimeException("Got string without length: "+s);
		
		int coefficientlength = 0;
		double coefficient;
		int power;
		try {
			while(coefficientlength < s.length() && !(s.charAt(coefficientlength) == 'x' || s.charAt(coefficientlength) == 'X')) {
				coefficientlength++;
			}
			if (coefficientlength == 0) {
				coefficient = 1;
			} else {
				if(coefficientlength == 1 && s.charAt(coefficientlength-1) == '-')
					coefficient = Double.parseDouble((String) ""+s.charAt(0)+"1"); 
				else if(coefficientlength == 1)
					coefficient = Double.parseDouble((String) ""+s.charAt(0)); 
				else
					coefficient = Double.parseDouble((String) s.subSequence(0, coefficientlength)); 
			}
			if (s.length() == coefficientlength) {
				power = 0;
			} else if(s.length() == coefficientlength+1 && (s.charAt(coefficientlength) == 'x' || s.charAt(coefficientlength) == 'X')){
				power = 1;
			} else if (s.length() > coefficientlength+2 && (s.charAt(coefficientlength) == 'x' || s.charAt(coefficientlength) == 'X') && s.charAt(coefficientlength+1) == '^') {
				if(coefficientlength+2 == s.length()-1)
					power = Integer.parseInt((String) ""+s.charAt(s.length()-1));
				else
					power = Integer.parseInt((String) s.subSequence(coefficientlength+2, s.length()));
			} else {
				throw new RuntimeException("shood look like doublex^power while the power shood by >=0, got: "+s);
			}
		} catch(RuntimeException e) {
			throw new RuntimeException("shood look like doublex^power while the power shood by >=0, got: "+s);
		}
		if (coefficient == 0) {
			power = 0;
		}
		this.set_coefficient(coefficient);
		this.set_power(power);
	}
	
	/**
	 * this function add a monom to the monom,
	 * only if it he as the same power.
	 * @param m is the monom you adding.
	 */
	public void add(Monom m) {
		if (this._power != m._power)
			throw new RuntimeException("You can't add "+m+" to "+this.toString()+", there dosen't have the same power." );
		this._coefficient = this._coefficient + m._coefficient;
	}	
	
	/**
	 * @return algebraic representation of the monom: a*x^b.
	 */
	public String toString() {
		if (this._coefficient == 0) {
			return "0";
		} else if(this._coefficient == 1) {
			if (this._power == 0)
				return "1";
			else if(this._power == 1) {
				return "x";
			} else {
				return "x^"+this._power;
			}
		} else if(this._coefficient == -1) {
			if (this._power == 0)
				return "-1";
			else if(this._power == 1) {
				return "-x";
			} else {
				return "-x^"+this._power;
			}
		} else {
			if (this._power == 0)
				return ""+this._coefficient;
			else if(this._power == 1) {
				return String.format ("%.7f" , this._coefficient)+"x";
			} else {
				return String.format ("%.7f", this._coefficient)+"x^"+this._power;
			}
		}
	}
	
	/**
	 * this function multipy d and the monom.
	 * @param d is the monom you multipy.
	 */
	public void multiply(Monom d) {
		this._coefficient = this._coefficient*d._coefficient;
		this._power = this._power+d._power;
	}
	
	/**
	 * this function multiply d and this monom.
	 * @return the monom of the Multiplication.
	 */
	public Monom multiplyAndReturn(Monom d) {
		return new Monom(this._coefficient*d._coefficient, this._power+d._power);
	}
	
	/**
	 * @return if this monom equal to monom m1.
	 */
	public boolean equals(Object m1) {
		if (m1 instanceof Monom ) {
			if ( this._coefficient >= ((Monom) m1)._coefficient-EPSILON && this._coefficient <= ((Monom) m1)._coefficient+EPSILON 
			    && this._power == ((Monom) m1)._power)
				return true;
			return false;
		} else {
			return false;
		}
	}

	@Override
	public function initFromString(String s) {
		return new Monom(s);
	}

	@Override
	public function copy() {
		return new Monom(this._coefficient, this._power);
	}
}
