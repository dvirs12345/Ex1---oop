package myMath;

import static org.junit.Assert.*;

import org.junit.Test;

public class MonomTest2 {

	@Test
	public void testGetComp() // Nothing really to test here
	{
		//TO DO
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
		System.out.println("*****TestMonomString*****");
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
		System.out.println("*****TestAdd*****");
		Monom[] ms = {new Monom("0x^2"), new Monom("2"),new Monom("x^2"), new Monom("0"),new Monom("x^0"),new Monom("-x")};
		Monom[] toadd = {new Monom("2"), new Monom("2"),new Monom("9x^2"), new Monom("0"),new Monom("x^0"),new Monom("-9x")};
		Monom[] ans = {new Monom("2"), new Monom("4"),new Monom("10x^2"), new Monom("0"),new Monom("2x^0"),new Monom("-10x")};
		for (int i = 0; i < ans.length; i++)
		{
			ms[i].add(toadd[i]);
			assertEquals(ms[i], ans[i]);
		}
		try 
		{
			ms[0].add(toadd[2]);
			System.out.println("No Error (Bad)");
		}
		catch(RuntimeException e) 
		{
			System.out.println("Error when trying to add Monoms with non equal powers" );
		}
	}

	@Test
	public void testToString()
	{
		System.out.println("*****TestToString*****");
		Monom[] ms = {new Monom("0x^2"), new Monom("2"),new Monom("x^2"), new Monom("0"),new Monom("x"),new Monom("-x")};
		String[] ans = {"0", "2.0","x^2", "0","x","-x"};
		for (int i = 0; i < ans.length; i++)
		{
			assertEquals(ms[i].toString(), ans[i]);
		}
	}

	@Test
	public void testMultiply() 
	{
		Monom[] ms = {new Monom("0x^2"), new Monom("2"),new Monom("x^2"), new Monom("0"),new Monom("x"),new Monom("-x")};
		Monom[] toadd = {new Monom("2"), new Monom("2"),new Monom("9x^2"), new Monom("0"),new Monom("x"),new Monom("-9x")};
		Monom[] ans = {new Monom("0"), new Monom("4"),new Monom("9x^4"), new Monom("0"),new Monom("x^2"),new Monom("9x^2")};
		for (int i = 0; i < ans.length; i++)
		{
			ms[i].multiply(toadd[i]);
			assertEquals(ms[i], ans[i]);
		}
	}

	@Test
	public void testMultiplyAndReturn()
	{
		Monom[] ms = {new Monom("0x^2"), new Monom("2"),new Monom("x^2"), new Monom("0"),new Monom("x"),new Monom("-x")};
		Monom[] toadd = {new Monom("2"), new Monom("2"),new Monom("9x^2"), new Monom("0"),new Monom("x"),new Monom("-9x")};
		Monom[] ans = {new Monom("0"), new Monom("4"),new Monom("9x^4"), new Monom("0"),new Monom("x^2"),new Monom("9x^2")};
		for (int i = 0; i < ans.length; i++)
		{
			assertEquals(ms[i].multiplyAndReturn(toadd[i]), ans[i]);
		}
	}

	@Test
	public void testInitFromString()
	{
		Monom m = new Monom("5x");
		function f = m.initFromString(m.toString());
		assertEquals(m, f);
	}

	@Test
	public void testCopy()
	{
		Monom m = new Monom("2x^2");
		function f = m.copy();
		assertEquals(m, f);
	}

	@Test
	public void testEqualsObject()
	{
		Monom[] ms = {new Monom("0x^2"), new Monom("2"),new Monom("x^2"), new Monom("0"),new Monom("x^0"),new Monom("-x")};
		Monom[] toadd = {new Monom("2"), new Monom("2"),new Monom("9x^2"), new Monom("0"),new Monom("x^0"),new Monom("-9x")};
		for (int i = 0; i < toadd.length; i++)
		{
			if(ms[i].equals(toadd[i]))
			{
				assertEquals(ms[i], toadd[i]);
			}
			else 
			{
				assertNotEquals(ms[i], toadd[i]);
			}
		}
	}

}
