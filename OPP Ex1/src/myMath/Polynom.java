package myMath;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Eli Ruvinov
 *
 */
@SuppressWarnings("serial")
public class Polynom implements Polynom_able {
	private HashMap <Integer, Monom> monomsHash;
	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {
		this.monomsHash = new HashMap<Integer, Monom>();
	}
	/**
	 * @return how many monoms there are in the polynom.
	 */
	public int size() { return this.monomsHash.size();}
	/**
	 * @return if there is atlas one monom in the polynom.
	 */
	public boolean isEmpty() {return (this.size() == 0);}
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "2x^2-4-1.2x-7.1", "3-3.4x+13.1x-1.2-3X^23"};
	 * @param s: is a string represents a polynom
	 * @param help: the index of the start of the monom that next in line for entry.

	 */
	public Polynom(String s) {
		try {
			this.monomsHash = new HashMap<Integer, Monom>();
			int help = 0;
			if(s.length() >= 2 && s.charAt(0) == '+') {
				s = s.substring(1);
			}
			for (int i = 0; i < s.length(); i++) {  
				if(i == s.length()-1)
					this.add(new Monom((String) s.subSequence(help,i+1)));
				if(s.charAt(i) == '+') {
					this.add(new Monom((String) s.subSequence(help,i)));
					help = i+1;
				} else if(s.charAt(i) == '-' && i != 0) {
					this.add(new Monom((String) s.subSequence(help,i)));
					help = i;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("The string: "+s+" isn't represent a polynom" );
		}
	}
	
	@Override
	/**
	 * @return the value of f(x) (= polynom) in the specific x that entered.
	 * @param sum: is the sum of the monoms of the polynom in the specific x that entered.
	 */
	public double f(double x) {
		double[] sum = new double[1];
		this.monomsHash.forEach((power, monom) -> { 
			sum[0]+=(monom.get_coefficient()*( Math.pow(x, power) )); 
		});
		return sum[0];
	}
	
	@Override
	/**
	 * This function is adding the monoms of Polynom_able p1 to the polynom. 
	 */
	public void add(Polynom_able p1) {
		Iterator<Monom> itrP1 = p1.iteretor();
		while (itrP1.hasNext()) {
			Monom mapMon = itrP1.next(); 
			this.add(mapMon);
		}
		Iterator<Monom> itr = this.iteretor();
		while (itr.hasNext()) {
			Monom mapMon = itr.next(); 
			if(mapMon.get_coefficient() == 0) 
				this.monomsHash.remove(mapMon.get_power());
		}
		if(this.isEmpty())
			this.toZero();
	}
	
	/**
	 * this function transform the polynom to Zero.
	 */
	private void toZero() {
		this.monomsHash = new HashMap<Integer, Monom>();
		this.monomsHash.put(0, Monom.ZERO);
	}
	
	@Override
	/**
	 * This function is adding the monom m1 to the polynom. 
	 */
	public void add(Monom m1) {
		if (this.monomsHash.get(m1.get_power()) == null) {
			this.monomsHash.put(m1.get_power(), m1);
		} else {
			Monom temp = new Monom(m1.get_coefficient()+this.monomsHash.get(m1.get_power()).get_coefficient(), m1.get_power());
			this.monomsHash.replace(m1.get_power(), this.monomsHash.get(m1.get_power()), temp);
		}
		Iterator<Monom> itr = this.iteretor();
		while (itr.hasNext()){
			Monom mapMon = (Monom)itr.next(); 
			if(mapMon.get_coefficient() == 0) {
				this.monomsHash.remove(mapMon.get_power());
				itr = this.iteretor();
			}
		}
		if(this.isEmpty())
			this.toZero();
	}

	@Override
	/**
	 * This function is subtracting the monoms of Polynom_able p1 from the polynom. 
	 */
	public void substract(Polynom_able p1) {
		Polynom p = new Polynom();
		p.add(Monom.MINUS1);
		p.multiply(p1);
		this.add(p);
	}

	@Override
	public void multiply(Polynom_able p1) {
		this.monomsHash = this.multiplyAndReturn(p1).monomsHash;
	}
	private Polynom multiplyAndReturn(Polynom_able p1) {
		Polynom p = new Polynom();
		Iterator<Monom> itr =  p1.iteretor();
		while (itr.hasNext()) {
			Monom p1Mon = (Monom)itr.next(); 
			Iterator<Monom> itr2 = this.iteretor();
			while (itr2.hasNext()) {
				Monom thisMon = itr2.next(); 
				p.add(p1Mon.multiplyAndReturn(thisMon));
			}
		}
		return p;
	}
	
	@Override
	public boolean equals(Object p1) {
		if (p1 instanceof Polynom_able ){
			int p1Size = 0;
			Iterator<Monom> itrP1 = ((Polynom_able) p1).iteretor();
			while (itrP1.hasNext()) {
				itrP1.next();
				p1Size++;
			}
			if(this.size() != p1Size)
				return false;
			Iterator<Monom> itr = ((Polynom_able) p1).iteretor(); 
			while (itr.hasNext()) {
				Monom mapMon = itr.next(); 
				if ( !(mapMon.equals( this.monomsHash.get(mapMon.get_power() ) ) ) ){
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Iterator<Monom> iteretor() {
		Iterator<Monom> itr = this.monomsHash.values().iterator();
		return itr;
	}
	
	@Override
	public boolean isZero() {
		return (this.monomsHash.size() == 1 && this.monomsHash.get(0) == Monom.ZERO);
	}

	@Override
	public double root(double x0, double x1, double eps) {
		if(this.f(x0)*this.f(x1) <= 0 ) {
				double mid = (x0+x1)/2;
			if( this.f(mid) < eps && this.f(mid) > -eps )
				return mid;
			else if (this.f(x0)*this.f(mid) < 0 )
				return root(x0,mid,eps);
			else 
				return root(mid,x1,eps);
		} else
			throw new RuntimeException("Not ." );
	}

	@Override
	public Polynom_able copy() {
		Polynom_able p = new Polynom();
		this.monomsHash.forEach((power, monom) -> {
			p.add(monom);
		});
		return p;
	}

	@Override
	public Polynom_able derivative() {
		Polynom_able p = new Polynom();
		this.monomsHash.forEach((power, monom) -> {
			Monom m = new Monom(monom);
			m = m.derivative();
			p.add(m);
		});
		return p;
	}

	@Override
	public double area(double x0, double x1, double eps) {
		if(x1 <= x0)
			return 0.;
		double area = 0.0;
		while(x0+eps <= x1) {
			if(this.f(x0+eps)>0 && this.f(x0+eps)>0)
				area += (eps * ( this.f(x0+eps) + this.f(x0+eps) ) / 2);
			x0 += eps;
		}
		return area;
	}
	
	/**
	 * @return algebraic representation of the polynom: a0+a1*x+a2*x^2+a3*x^3... ,
	 *         from the high power to the low.
	 * @param s: is the algebraic representation of the polynom.
	 */
	public String toString() {
		String s ="";
		TreeMap<Integer, Monom> sorted = new TreeMap<>(); 
	    sorted.putAll(this.monomsHash); 
	    for (Map.Entry<Integer, Monom> entry : sorted.entrySet())  
			s = entry.getValue()+" "+s;
	    for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == ' ') {
				if(i == s.length()-1) {
					s = s.substring(0, i);
				} else if (s.charAt(i+1) == '-') {
					s = s.substring(0, i)+s.substring(i+1, s.length());
				} else {
					s = s.substring(0, i)+'+'+s.substring(i+1, s.length());
				}
			}
		}
	    if(s.length() > 2 && s.charAt(s.length()-1) == '0' && s.charAt(s.length()-2) == '+')
	    	s = s.substring(0, s.length()-2);
	    return s;
	}
	
	@Override
	public void multiply(Monom m1) {
		this.monomsHash = this.multiplyHelp(m1).monomsHash;
	}
	
	private Polynom multiplyHelp(Monom m1) {
		Polynom p = new Polynom();
		this.monomsHash.forEach((power, monom) -> {
			p.add(m1.multiplyAndReturn(monom));
		});
		return p;
	}
	
	@Override
	public function initFromString(String s) {
		return new Polynom(s);
	}
}