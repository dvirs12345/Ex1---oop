package Ex1;

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
	public void testToString() 
	{
		Polynom[] ms = {new Polynom("0x^2"), new Polynom("2"),new Polynom("x^2"), new Polynom("0"),new Polynom("x"),new Polynom("-x")};
		String[] ans = {"0", "2.0","x^2", "0","x","-x"};
		for (int i = 0; i < ans.length; i++)
		{
			assertEquals(ms[i].toString(), ans[i]);
		}
		Polynom p3 = new Polynom();
		p3.add(new Monom(11, 1));
		assertEquals(p3.toString(), "11.0000000x");
		p3.add(new Monom("X^5"));
		assertEquals(p3.toString(), "x^5+11.0000000x");
	}
	
	@Test
	public void testF() {
		Polynom p1 = new Polynom("x");
		Polynom p3 = new Polynom("-x");
		Polynom p2 = new Polynom("2x^2+5");
		Polynom p4 = new Polynom("3X^3+5");
		assertEquals(p1.f(13), p2.f(2));
		assertEquals(p4.f(-2), p3.f(19));
	}
	
	@Test
	public void testMultiplyPolynom_able() {
		Polynom p = new Polynom("X+1");
		Polynom p1 = new Polynom("X-1");
		Polynom p2 = new Polynom("X^2-1");
		p.multiply(p1);
		assertEquals(p.toString(), p2.toString());
	}
	
	@Test
	public void testEqualsPolynom_able() {
		Polynom p = new Polynom();
		p.add(new Monom("2x^3"));
		p.add(new Monom("0"));
		p.add(new Monom("-5x^2"));
		p.add(new Monom("-8x"));
		Polynom_able p2 = new Polynom(p.toString());
		assertEquals(p, p2);
		Polynom p3 = new Polynom("x+5");
		Polynom p4 = new Polynom("1.01x+5");
		Polynom p5 = new Polynom("1.0000001x+5");
		Polynom p6 = (Polynom) p3.copy();
		assertNotEquals(p3, p4);
		assertEquals(p3, p5);
		assertEquals(p3, p6);
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
		assertEquals(p, p1);
	}
	
	@Test
	public void testDerivative() {
		Polynom p = new Polynom();
		p.add(new Monom(11,11));
		assertEquals(p.derivative(), new Polynom("121x^10"));
	}
	
	@Test
	public void testArea() {
		Polynom p = new Polynom();
		p.add(new Monom(1,1));
		p.add(new Monom(5,0));
		double area = p.area(-5, 0, 0.00001);
		assertEquals(area, 12.500024999877184);
	}
	
	@Test
	public void testSubstract() {
		String s = "-x^3+x+5.0";
		Polynom p = new Polynom(s);
		Polynom p2 = new Polynom(s);
		p.substract(p2);
		assertEquals(p.toString(), "0");
	} 
	
	@Test
	public void isEmpty() {
		Polynom n = new Polynom();
		Polynom p = new Polynom();
		assertEquals(p, n);
		p.add(Monom.ZERO);
		assertNotEquals(p, n);
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
		String result = "";
		while (itr.hasNext()){
			Monom mapMon = (Monom)itr.next(); 
			result+=mapMon.toString(); 
		}
		assertEquals("5.0x6.0000000x^2-x^3x^4", result);
	}
	
	@Test
	public void testMultiplyMonom() {
		Polynom p = new Polynom();
		p.add(new Monom(1,1));
		p.add(new Monom(5,0));
		p.add(new Monom(-1,3));
		p.multiply(Monom.ZERO);
		assertEquals("0", p.toString());
		Polynom p1 = new Polynom();
		p1.add(new Monom(6,2));
		p1.add(new Monom(1,4));
		Polynom p2 = (Polynom)p1.copy();
		p2.multiply(Monom.MINUS1);
		assertNotEquals(p2, p1);
		p2.multiply(Monom.MINUS1);
		assertEquals(p2, p1);
	}
	
}
