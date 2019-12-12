package myMath;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class ComplexFunctionTest {
	
	@Test
	void testEquals() {
		ComplexFunction cf = new ComplexFunction(Operation.Plus, new Polynom("x"), new Polynom("1"));
		ComplexFunction cf2 = new ComplexFunction(Operation.Plus, new Polynom("x"), new Monom("1"));
		ComplexFunction cf3 = new ComplexFunction(Operation.Times, new Polynom("x+1"), ComplexFunction.help1.copy());
		ComplexFunction cf4 = new ComplexFunction(Operation.Times, new Polynom("x+1"), new Polynom("10"));
		cf4.div(new Polynom("10"));
		assertEquals(cf, cf2);
		assertEquals(cf, cf3);
		assertEquals(cf, cf4);
		assertEquals(cf2, cf3);
		assertEquals(cf2, cf4);
		assertEquals(cf3, cf4);
		assertEquals(cf, cf.copy());
		assertEquals(cf, new ComplexFunction(cf.getOp(), cf.left(), cf.right()));
		ComplexFunction cf5 = new ComplexFunction(Operation.Plus, new Polynom("x"), new Polynom("1"));
		ComplexFunction cf6 = new ComplexFunction(Operation.Times, new Polynom("x+1"), new ComplexFunction("div(1-x,1-x)"));
		assertNotEquals(cf, ComplexFunction.Zero);
		assertNotEquals(cf5, cf6);
	}
	
	@Test
	void testInitFromString() {
		String[] s = { "plus(x,x+5)","min(-x^2,-x-8)", "max(1,-x^2+2)",
				"comp(X^5,5x^2)", "mul(4x,x-3)","div(5,x+5)", "plus(div(x,x+5),comp(x,x+5))"};
		ComplexFunction[] CFArray = {
				new ComplexFunction(Operation.Plus, new ComplexFunction(Operation.None, new Polynom("x"), ComplexFunction.help1), 
						new ComplexFunction(Operation.None, new Polynom("X+5"), ComplexFunction.help1)),
				new ComplexFunction(Operation.Min, new ComplexFunction(Operation.None, new Polynom("-x^2"), ComplexFunction.help1), 
						new ComplexFunction(Operation.None, new Polynom("-x-8"), ComplexFunction.help1)),
				new ComplexFunction(Operation.Max, new ComplexFunction(Operation.None, new Polynom("1"), ComplexFunction.help1), 
						new ComplexFunction(Operation.None, new Polynom("-x^2+2"), ComplexFunction.help1)),
				new ComplexFunction(Operation.Comp, new ComplexFunction(Operation.None, new Polynom("X^5"), ComplexFunction.help1), 
						new ComplexFunction(Operation.None, new Polynom("5x^2"), ComplexFunction.help1)),
				new ComplexFunction(Operation.Times, new ComplexFunction(Operation.None, new Polynom("4x"), ComplexFunction.help1), 
						new ComplexFunction(Operation.None, new Polynom("x-3"), ComplexFunction.help1)),
				new ComplexFunction(Operation.Divid, new ComplexFunction(Operation.None, new Polynom("5"), ComplexFunction.help1), 
						new ComplexFunction(Operation.None, new Polynom("x+5"), ComplexFunction.help1)),
				new ComplexFunction(Operation.Plus,
						new ComplexFunction(Operation.Divid, 
								new ComplexFunction(Operation.None, 
										new Polynom("x"), 
										ComplexFunction.help1), 
								new ComplexFunction(Operation.None, 
										new Polynom("X+5"), 
										ComplexFunction.help1)), 
						new ComplexFunction(Operation.Comp, 
								new ComplexFunction(Operation.None, 
										new Polynom("x"), 
										ComplexFunction.help1), 
								new ComplexFunction(Operation.None, 
										new Polynom("X+5"), 
										ComplexFunction.help1)))	
		};
		
		for (int i = 0; i < s.length; i++) {
			ComplexFunction cf = (ComplexFunction) ComplexFunction.help1.initFromString(s[i]);
			assertEquals( CFArray[i], cf); 
			assertNotEquals(cf, CFArray[(i+1)%CFArray.length]);
		}
		
	}
	
	@Test
	void testComplexFunctionComplexFunction() {
		ComplexFunction[] cf = {
				new ComplexFunction(Operation.Plus,new Polynom("1"),new Polynom("x")),
				new ComplexFunction(Operation.Times,new Polynom("0"),new Polynom("x^100-3x+5")),
				new ComplexFunction(Operation.Min,new Polynom("-1"),new Polynom("x^3")),
				new ComplexFunction(Operation.Divid,new Polynom("1"),new Polynom("x^4")),
				new ComplexFunction(Operation.Max,new Polynom("0"),new Polynom("x^2-5")),
				new ComplexFunction(Operation.Comp,new Polynom("0.3333x^2"),new Polynom("x^3+1")),
				new ComplexFunction(Operation.None,new Polynom("0.5x^2-5"),new Polynom("0"))
		};
		ComplexFunction cf3 = ComplexFunction.help1;
		for (int i = 0; i < cf.length; i++) {
			ComplexFunction cf2 = new ComplexFunction(cf[i]);
			assertEquals(cf[i], cf2);
			assertNotEquals(cf[i], cf3);
			cf3 = cf2.copy();
		}
	}

	@Test
	void testF() {
		ComplexFunction[] cf = {
			new ComplexFunction(Operation.Plus,new Polynom("1"),new Polynom("x")),
			new ComplexFunction(Operation.Times,new Polynom("0"),new Polynom("x^100-3x+5")),
			new ComplexFunction(Operation.Min,new Polynom("-1"),new Polynom("x^3")),
			new ComplexFunction(Operation.Divid,new Polynom("1"),new Polynom("x^4")),
			new ComplexFunction(Operation.Max,new Polynom("0"),new Polynom("x^2-5")),
			new ComplexFunction(Operation.Comp,new Polynom("0.166666666666x"),new Polynom("0.5x")),
			new ComplexFunction(Operation.None,new Polynom("0.5x^2-5"),new Polynom("0"))
		};
		double[] place = { 1,2,3,4,5,6,7 };
		double[] actual = { 2,0,-1,0.00390625,20,0.5,19.5};
		for (int i = 0; i < cf.length; i++) {
			assertEquals(cf[i].f(place[i]), actual[i], 0.00001);  
			assertNotEquals(cf[i].f(0), actual[i]+10*Math.random()+0.002, 0.00001);
		}
	}

	@Test
	void testCopy() {
		ComplexFunction[] cf = {
				new ComplexFunction(Operation.Plus,new Polynom("1"),new Polynom("x")),
				new ComplexFunction(Operation.Times,new Polynom("0"),new Polynom("x^100-3x+5")),
				new ComplexFunction(Operation.Min,new Polynom("-1"),new Polynom("x^3")),
				new ComplexFunction(Operation.Divid,new Polynom("1"),new Polynom("x^4")),
				new ComplexFunction(Operation.Max,new Polynom("0"),new Polynom("x^2-5")),
				new ComplexFunction(Operation.Comp,new Polynom("0.3333x^2"),new Polynom("x^3+1")),
				new ComplexFunction(Operation.None,new Polynom("0.5x^2-5"),new Polynom("0"))
		};
		for (int i = 0; i < cf.length; i++) {
			ComplexFunction cf2 = cf[i].copy();
			assertEquals(cf[i], cf2);
			cf2.setOp(cf[(i+1)%cf.length].getOp());
			assertNotEquals(cf, cf2);
		}
	}

	@Test
	void testPlus() {
		ComplexFunction cf = new ComplexFunction(Operation.Times,new Polynom("2x+5"),new Polynom("2"));
		ComplexFunction cf2 = new ComplexFunction(Operation.Comp,new Polynom("x^5"),new Polynom("x^2"));
		ComplexFunction cf3 = new ComplexFunction(cf2);
		cf3.plus(cf.copy());
		ComplexFunction cf4 = new ComplexFunction(Operation.Plus, cf2, cf);
		assertEquals(cf3, cf4);
		ComplexFunction cf5 = new ComplexFunction(cf4);
		cf5.plus(cf.copy());
		assertNotEquals(cf4, cf5.copy());
	}

	@Test
	void testMul() {
		ComplexFunction cf = new ComplexFunction(Operation.Times,new Polynom("2x+5"),new Polynom("2"));
		ComplexFunction cf2 = new ComplexFunction(Operation.Comp,new Polynom("x^5"),new Polynom("x^2"));
		ComplexFunction cf3 = new ComplexFunction(cf2);
		cf3.mul(cf);
		ComplexFunction cf4 = new ComplexFunction(Operation.Times, cf2.copy(), cf.copy());
		assertEquals(cf3, cf4); 
		ComplexFunction cf5 = new ComplexFunction(cf4);
		cf5.mul(cf);
		assertNotEquals(cf4, cf5);
	}

	@Test
	void testDiv() {
		ComplexFunction cf = new ComplexFunction(Operation.None, new Polynom("2x"), ComplexFunction.help1);
		ComplexFunction cf2 = cf.copy();
		cf.div(new Polynom("x^2"));
		assertEquals(new ComplexFunction(Operation.Divid,cf2,new Polynom("x^2")), cf);
		cf.setLeft(new Polynom("x"));
		assertNotEquals(new ComplexFunction(Operation.Divid,cf2,new Polynom("x^2")), cf);
	}

	@Test
	void testMax() {
		ComplexFunction cf = new ComplexFunction(Operation.Max, new Polynom("2x"), new Polynom("x"));
		ComplexFunction cf2 = cf.copy();
		cf.max(new Polynom("x^2"));
		assertEquals(new ComplexFunction(Operation.Max,cf2,new Polynom("x^2")), cf);
		cf.setLeft(new Polynom("45"));
		assertNotEquals(cf, cf2);
	}

	@Test
	void testMin() {
		ComplexFunction cf = new ComplexFunction(Operation.Min, new Polynom("2x"), new Polynom("x"));
		ComplexFunction cf2 = cf.copy();
		cf.min(new Polynom("x^2"));
		assertEquals(new ComplexFunction(Operation.Min,cf2,new Polynom("x^2")), cf);
		cf.setLeft(new Polynom("45"));
		assertNotEquals(cf, cf2);
	}

	@Test
	void testComp() {
		ComplexFunction cf = new ComplexFunction(Operation.Comp, new Polynom("2x"), new Polynom("x"));
		ComplexFunction cf2 = cf.copy();
		cf.comp(new Polynom("x^2"));
		assertEquals(new ComplexFunction(Operation.Comp,cf2,new Polynom("x^2")), cf);
		cf.setLeft(new Polynom("45"));
		assertNotEquals(cf, cf2);
	}

	@Test
	void testLeft() {
		ComplexFunction cf = ComplexFunction.help1;
		cf.setLeft(new Monom("2x"));
		assertEquals(new Monom("2x"), cf.left());
		assertNotEquals(new Monom("3x"), cf.left());
	}

	@Test
	void testRight() {
		ComplexFunction cf = ComplexFunction.help1;
		cf.setRight(new Polynom("x"));
		assertEquals(new Polynom("x"), cf.right());
		assertNotEquals(new Polynom("x+1"), cf.right());
	}

	@Test
	void testGetOp() {
		ComplexFunction[] cf = {
				new ComplexFunction(Operation.Plus,new Polynom("1"),new Polynom("x")),
				new ComplexFunction(Operation.Times,new Polynom("0"),new Polynom("x^100-3x+5")),
				new ComplexFunction(Operation.Min,new Polynom("-1"),new Polynom("x^3")),
				new ComplexFunction(Operation.Divid,new Polynom("1"),new Polynom("x^4")),
				new ComplexFunction(Operation.Max,new Polynom("0"),new Polynom("x^2-5")),
				new ComplexFunction(Operation.Comp,new Polynom("0.3333x^2"),new Polynom("x^3+1")),
				new ComplexFunction(Operation.None,new Polynom("0.5x^2-5"),new Polynom("0"))
		};
		Operation[] op = {Operation.Plus,Operation.Times,Operation.Min,
							Operation.Divid,Operation.Max,Operation.Comp,Operation.None };
		for (int i = 0; i < op.length; i++) {
			assertEquals(cf[i].getOp(), op[i]);
			assertNotEquals(cf[i].getOp(), op[(i+1)%op.length]);
		}
		
	}

}
