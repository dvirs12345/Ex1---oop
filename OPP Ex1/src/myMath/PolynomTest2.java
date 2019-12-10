package myMath;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Iterator;
import org.junit.Test;

public class PolynomTest2 {

	@Test
	public void testPolynom() {
		Polynom p = new Polynom();
		p.add(Monom.ZERO);
		Polynom p1 = new Polynom("0");
		assertEquals(p,p1);
		Polynom p2 = new Polynom("x+5X^2");
		assertNotEquals(p2,p1);
	}
	
	@Test
	public void testAddMonom() {
		Polynom p = new Polynom();
		p.add(Monom.ZERO);
		assertEquals(p.toString(), "0");
		p.add(Monom.MINUS1);
		assertEquals(p.toString(), "-1");
		p.add(new Monom(3, 0));
		assertEquals(p.toString(), "2.0");
		p.add(new Monom(1, 2));
		assertEquals(p.toString(), "x^2+2.0");
		p.add(new Monom("9x^15"));
		System.out.println(p.toString());
		assertEquals(p.toString(), "9.0000000x^15+x^2+2.0");
		p.add(new Monom("-6X^3"));
		assertEquals(p.toString(), "9.0000000x^15-6.0000000x^3+x^2+2.0");
	}

	@Test
	public void testAddPolynom_able() {
		Polynom p1 = new Polynom();
		Polynom_able p2 = new Polynom();
		p1.add(Monom.MINUS1);
		assertEquals(p1.toString(), "-1");
		p2.add(new Monom("x"));
		p2.add(new Monom("X^5"));
		p1.add(p2);
		assertEquals(p1, new Polynom("x^5+x-1"));
	}
	
	@Test
	public void testToString() {
		Polynom[] ms = {new Polynom("0x^2"), new Polynom("2"),new Polynom("x^2"), new Polynom("0"),new Polynom("x"),new Polynom("-x")};
		String[] ans = {"0", "2.0","x^2", "0","x","-x"};
		for (int i = 0; i < ans.length; i++)
		{
			assertEquals(ms[i].toString(), ans[i]);
		}
		Polynom p3 = new Polynom();
		p3.add(new Monom(11, 1));
		p3.add(new Monom("X^5"));
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
		Polynom p = new Polynom("X+1");
		Polynom p1 = new Polynom("X-1");
		Polynom p2 = new Polynom("X^2-1");
		p.multiply(p1);
		if(!p.equals(p2))
			fail("Not true"); 
	}
	
	@Test
	public void testEqualsPolynom_able() {
		Polynom p = new Polynom();
		p.add(new Monom("2x^3"));
		p.add(new Monom("0"));
		p.add(new Monom("-5x^2"));
		p.add(new Monom("-8x"));
		Polynom_able p2 = new Polynom(p.toString());
		if (!p.equals(p2))
			fail("Not equal");
		Polynom p3 = new Polynom("x+5");
		Polynom p4 = new Polynom("1.01x+5");
		Polynom p5 = new Polynom("1.0000001x+5");
		Polynom p6 = (Polynom) p3.copy();
		if (p3.equals(p4))
			fail(p3.toString()+" != "+p4.toString());
		if (!p3.equals(p5))
			fail(p3.toString()+" != "+p5.toString());
		if (!p3.equals(p6))
			fail(p3.toString()+" != "+p5.toString());
	}
	
	@Test
	public void testRoot() {
		Polynom_able p = new Polynom("X");
		double root = p.root(-2, 1, 0.001);
		if( !(root <= 0.001 && root >= -0.001) )
			fail("Not true");  
	}

	@Test
	public void testCopy() {
		Polynom p  = new Polynom("9.0x^15-6878.0x^3-735.0x");
		Polynom_able p1 = p.copy();
		if(  !( p.equals(p1))  )
			fail("Not equals");
	}
	
	@Test
	public void testDerivative() {
		Polynom p = new Polynom();
		p.add(new Monom(11,11));
		if(  !( p.derivative().equals(new Polynom("121x^10")))  )
			fail("Not equals");
	}
	
	@Test
	public void testArea() {
		Polynom p = new Polynom();
		p.add(new Monom(1,1));
		p.add(new Monom(5,0));
		double area = p.area(-5, 0, 0.00001);
	}
	
	@Test
	public void testSubstract() {
		String s = "-x^3+x+5.0";
		Polynom p = new Polynom(s);
		Polynom p2 = new Polynom(s);
		p.substract(p2);
		if(!p.isZero())
			fail("p not as spuse to be");
	} 
	
	@Test
	public void isEmpty() {
		Polynom p = new Polynom();
		if(!p.isEmpty()) {
			fail("The polynom isn't empty.");
		}
		p.add(Monom.ZERO);
		if(p.isEmpty())
			fail("The polynom is empty.");
	}
	
	@Test
	public void testIteretor() {
		Polynom p = new Polynom();
		p.add(new Monom(1,1));
		p.add(new Monom(5,0));
		p.add(new Monom(-1,3));
		p.add(new Monom(6,2));
		p.add(new Monom(1,4));
		p.add(new Monom(0,0));
		Iterator<Monom> itr = p.iteretor();
		while (itr.hasNext()){
			Monom mapMon = (Monom)itr.next(); 
		}
	}
	
	@Test
	public void testMultiplyMonom() {
		Polynom p = new Polynom();
		p.add(new Monom(1,1));
		p.add(new Monom(5,0));
		p.add(new Monom(-1,3));
		p.multiply(Monom.ZERO);
		Polynom p1 = new Polynom();
		p1.add(new Monom(6,2));
		p1.add(new Monom(1,4));
		Polynom p2 = (Polynom)p1.copy();
		p2.multiply(Monom.MINUS1);
		p2.multiply(Monom.MINUS1);
		if(!p.isZero() || !p1.equals(p2))
			fail(""); 
	}
	
}
