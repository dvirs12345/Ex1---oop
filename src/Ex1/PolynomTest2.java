package Ex1;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;

import org.junit.Test;

import myMath.Monom;
import myMath.Polynom;

public class PolynomTest2 {

	@Test
	public void testPolynom() {
		System.out.println("\ntestPolynom(): ");
		Polynom p = new Polynom();
		p.add(Monom.ZERO);
		Polynom p1 = new Polynom("0");
		System.out.println("The polynom p is: "+p);
		System.out.println("The polynom p1 is: "+p1);
		assertEquals(p,p1);
		Polynom p2 = new Polynom("x+5X^2");
		assertNotEquals(p2,p1);
	}

	@Test
	public void testAddMonom() {
		System.out.println("\ntestAddMonom(): ");
		Polynom p = new Polynom();
		p.add(Monom.ZERO);
		p.add(Monom.MINUS1);
		p.add(new Monom(1311111111, 0));
		p.add(new Monom(0.0000001, 2));  
		p.add(new Monom("9x^15"));
		p.add(new Monom("-6878X^3"));
		Polynom p2 = new Polynom(p.toString());
		System.out.println("The polynom p is: "+p);
		System.out.println("The polynom p2 is: "+p2);
	}

	@Test
	public void testAddPolynom_able() {
		System.out.println("\ntestAddPolynom_able(): ");
		Polynom p1 = new Polynom();
		Polynom_able p2 = new Polynom();
		System.out.println("The polynom p1 is: "+p1+" (empty polynom)");
		p1.add(Monom.MINUS1);
		p2.add(new Monom("x"));
		p2.add(new Monom("X^5"));
		System.out.println("The polynom p1 is: "+p1);
		System.out.println("The polynom p2 is: "+p2);
		p1.add(p2);
		System.out.println("p1 + p2 is: "+p1);
		if(!p1.equals(new Polynom("x^5+x-1")))
			fail("function AddPolynom_able not good ");
	}
	
	@Test
	public void testToString() {
		System.out.println("\ntestToString(): ");
		Polynom p3 = new Polynom();
		p3.add(new Monom(11, 1));
		System.out.println("The polynom p3 is: "+p3);
		p3.add(new Monom("X^5"));
		System.out.println("The polynom p3 is: "+p3);
	}


	@Test
	public void testF() {
		Polynom p1 = new Polynom("x");
		Polynom p3 = new Polynom("-x");
		Polynom p2 = new Polynom("2x^2+5");
		Polynom p4 = new Polynom("3X^3+5");
		if(p2.f(1)+1 == p4.f(1) && p1.f(0) == p3.f(0))
			return;
		fail("not good f(x)"); 
	}


	@Test
	public void testMultiplyPolynom_able() {
		System.out.println("\ntestMultiplyPolynom_able: ");
		Polynom p = new Polynom("X+1");
		Polynom p1 = new Polynom("X-1");
		Polynom p2 = new Polynom("X^2-1");
		System.out.println("p: "+p);
		System.out.println("p1: "+p1);
		p.multiply(p1);
		System.out.println("p * p1: "+p);
		System.out.println("p2: "+p2);
		if(!p.equals(p2))
			fail("Not true"); 
	}

	@Test
	public void testEqualsPolynom_able() {
		System.out.println("\ntestEqualsPolynom_able(): ");
		Polynom p = new Polynom();
		p.add(new Monom("2x^3"));
		p.add(new Monom("0"));
		p.add(new Monom("-5x^2"));
		p.add(new Monom("-8x"));
		Polynom_able p2 = new Polynom(p.toString());
		System.out.println("*The polynom p1 is: "+p+"\n**The polynom p2 is: "+p2);
		System.out.println("p1 == p2 : "+p.equals(p2));
		if (!p.equals(p2))
			fail("Not equal");
		Polynom p3 = new Polynom("x+5");
		Polynom p4 = new Polynom("1.01x+5");
		Polynom p5 = new Polynom("1.0000001x+5");
		Polynom p6 = (Polynom) p3.copy();
		System.out.print(p3+" == "+p4+" : "+p3.equals(p4));
		if (p3.equals(p4))
			fail(p3.toString()+" != "+p4.toString());
		if (!p3.equals(p5))
			fail(p3.toString()+" != "+p5.toString());
		if (!p3.equals(p6))
			fail(p3.toString()+" != "+p5.toString());
	}
	@Test
	public void testRoot() {
		System.out.println("\ntestRoot(): ");
		Polynom_able p = new Polynom("X");
		double root = p.root(-2, 1, 0.001);
		if( !(root <= 0.001 && root >= -0.001) )
			fail("Not true");  
		System.out.println("Root = "+root);
	}

	@Test
	public void testCopy() {
		System.out.println("\ntestCopy(): ");
		Polynom p  = new Polynom("9.0x^15-6878.0x^3-735.0x");
		Polynom_able p1 = p.copy();
		System.out.println("*The polynom p is: "+p+"\n**The polynom p1 is: "+p1);
		if(  !( p.equals(p1))  )
			fail("Not equals");
	}
	
	@Test
	public void testDerivative() {
		System.out.println("testDerivative(): ");
		Polynom p = new Polynom();
		p.add(new Monom(11,11));
		System.out.println("p(x) is: "+p);
		System.out.println("p'(x) is: "+(Polynom) p.derivative());
		if(  !( p.derivative().equals(new Polynom("121x^10")))  )
			fail("Not equals");
	}

	@Test
	public void testArea() {
		System.out.println("\ntestArea(): ");
		Polynom p = new Polynom();
		p.add(new Monom(1,1));
		p.add(new Monom(5,0));
		double area = p.area(-5, 0, 0.00001);
		System.out.println(area);
	}

	@Test
	public void testSubstract() {
		System.out.println("\ntestSubstract(): ");
		String s = "-x^3+x+5.0";
		Polynom p = new Polynom(s);
		System.out.println("p is: "+p);
		Polynom p2 = new Polynom(s);
		System.out.println("p2 is: "+p2);
		p.substract(p2);
		System.out.println("p - p2: "+p+" == 0 ? "+p.isZero());
		if(!p.isZero())
			fail("p not as spuse to be");
	} 
	
	@Test
	public void isEmpty() {
		Polynom p = new Polynom();
		if(!p.isEmpty()) {
			System.out.println(p);
			fail("The polynom isn't empty.");
		}
		p.add(Monom.ZERO);
		if(p.isEmpty())
			fail("The polynom is empty.");
	}
	
	@Test
	public void testIteretor() {
		System.out.println("\ntestIteretor(): ");
		Polynom p = new Polynom();
		p.add(new Monom(1,1));
		p.add(new Monom(5,0));
		p.add(new Monom(-1,3));
		p.add(new Monom(6,2));
		p.add(new Monom(1,4));
		p.add(new Monom(0,0));
		System.out.println("The iteretor suppose to print all the monoms in p:");
		Iterator<Monom> itr = p.iteretor();
		while (itr.hasNext()){
			Monom mapMon = (Monom)itr.next(); 
			System.out.println(mapMon);
		}
	}

	@Test
	public void testMultiplyMonom() {
		System.out.println("\ntestMultiplyMonom(): ");
		Polynom p = new Polynom();
		p.add(new Monom(1,1));
		p.add(new Monom(5,0));
		p.add(new Monom(-1,3));
		System.out.println("p is: "+p);
		p.multiply(Monom.ZERO);
		System.out.println("p * Monom.ZERO: "+p);
		Polynom p1 = new Polynom();
		p1.add(new Monom(6,2));
		p1.add(new Monom(1,4));
		System.out.println("p1 is: "+p1);
		Polynom p2 = (Polynom)p1.copy();
		p2.multiply(Monom.MINUS1);
		p2.multiply(Monom.MINUS1);
		System.out.println("p1 * -1 * -1: "+p2);
		if(!p.isZero() || !p1.equals(p2))
			fail(""); 
	}

}
