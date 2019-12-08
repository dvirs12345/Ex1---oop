package Ex1;

import static org.junit.Assert.*;

import org.junit.Test;

public class MonomTest2 {

	@Test
	public void testGetComp() // Nothing really to test here
	{
		fail("Not yet implemented");
	}

	@Test
	public void testMonomDoubleInt() 
	{ 
		Monom m = new Monom(0.5,2);
		assertEquals(0.5, m.get_coefficient(), 0.0001);
		assertEquals(2, m.get_power());
	}

	@Test
	public void testMonomMonom() 
	{
		Monom m = new Monom(0.5,2);
		Monom mcopy = new Monom(m);
		assertEquals(m, mcopy);
	}

	@Test
	public void testGet_coefficient()
	{
		Monom m = new Monom(0.5,2);
		assertEquals(m.get_coefficient(), 0.5,0.0001);
	}

	@Test
	public void testGet_power()
	{
		Monom m = new Monom(0.5,2);
		assertEquals(m.get_power(), 2,0.0001);
	}

	@Test
	public void testDerivative()
	{
		Monom[] ms = {new Monom("2x^2"), new Monom("2"), new Monom("7x"),new Monom("-x")};
		Monom[] ans = {new Monom("4x"),new Monom("0"),new Monom("7"),new Monom("-1")};
		for (int i = 0; i < ans.length; i++)
		{
			assertEquals(ms[i].derivative(), ans[i]);
		}
	}

	@Test
	public void testF() 
	{
		Monom[] ms = {new Monom("2x^2"), new Monom("2"), new Monom("7x"),new Monom("-x")};
		double[] points = {0,6,2,7};
		double[] ans = {0,2,14,-7};
		for (int i = 0; i < ans.length; i++)
		{
			assertEquals(ms[i].f(points[i]), ans[i], 0.0001);
		}
	}

	@Test
	public void testIsZero() 
	{
		Monom[] ms= {new Monom("0x^2"), new Monom("2"),new Monom("x^2"), new Monom("0"),new Monom("x^0")};
		boolean[] ans = {true,false,false,true,false};
		for (int i = 0; i < ans.length; i++)
		{
			assertEquals(ms[i].isZero(), ans[i]);
		}
	}

	@Test
	public void testMonomString() 
	{
		Monom[] ms = {new Monom("0x^2"), new Monom("2"),new Monom("x^2"), new Monom("0"),new Monom("x^0"),new Monom("-x")};
		double[] anscoef = {0,2,1,0,1,-1};
		int[] anspow = {0,0,2,0,0,1};
		for (int i = 0; i < anscoef.length; i++)
		{
			assertEquals(ms[i].get_coefficient(), anscoef[i],0.0001);
			assertEquals(ms[i].get_power(), anspow[i]);
		}
		try 
		{
			Monom m = new Monom("");
			System.out.println("No Error in first");
		}
		catch(RuntimeException e) 
		{
			System.out.println("Error in first");
		}
	
		try 
		{
			Monom m = new Monom("2^");
			System.out.println("No Error in 2^");
		}
		catch(RuntimeException e) 
		{
			System.out.println("Error in 2^");
		}
		
		try 
		{
			Monom m = new Monom("x^");
			System.out.println("No Error in x^");
		}
		catch(RuntimeException e) 
		{
			System.out.println("Error in x^");
		} 
		
		try 
		{
			Monom m = new Monom("^");
			System.out.println("No Error in ^");
		}
		catch(RuntimeException e) 
		{
			System.out.println("Error in ^");
		} 
		
		try 
		{
			Monom m = new Monom("^0");
			System.out.println("No Error in ^0");
		}
		catch(RuntimeException e) 
		{
			System.out.println("Error in ^0");
		} 
		
		try 
		{
			Monom m = new Monom("-");
			System.out.println("Coefficient = "+m.get_coefficient());
			System.out.println("Power = "+m.get_power());
		}
		catch(RuntimeException e) 
		{
			System.out.println("Error in -");
		} 
	}

	@Test
	public void testAdd()
	{
		Monom[] ms = {new Monom("0x^2"), new Monom("2"),new Monom("x^2"), new Monom("0"),new Monom("x^0"),new Monom("-x")};
		Monom[] toadd = {new Monom("2"), new Monom("2"),new Monom("9x^2"), new Monom("0"),new Monom("x^0"),new Monom("-9x")};
		Monom[] ans = {new Monom("2x^2"), new Monom("4"),new Monom("10x^2"), new Monom("0"),new Monom("x^0"),new Monom("-10x")};
		for (int i = 0; i < ans.length; i++)
		{
			ms[i].add(toadd[i]);
			assertEquals(ms[i], ans[i]);
		}
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testMultiply() {
		fail("Not yet implemented");
	}

	@Test
	public void testMultiplyAndReturn() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitFromString() {
		fail("Not yet implemented");
	}

	@Test
	public void testCopy() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

}
