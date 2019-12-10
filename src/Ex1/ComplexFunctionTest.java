package Ex1;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class ComplexFunctionTest {
	
	@Test
	void testEquals() {
		ComplexFunction cf = new ComplexFunction();
		cf.setLeft(new Polynom("x")); 
		cf.setRight(new Polynom("1")); 
		cf.setOp(Operation.Plus);
		ComplexFunction cf2 = new ComplexFunction();
		cf2.setLeft(new Polynom("x")); 
		cf2.setRight(new Polynom("1")); 
		cf2.setOp(Operation.Plus);
		assertEquals(cf, cf2);
		assertEquals(cf, cf.copy());
		assertEquals(cf, new ComplexFunction(cf.getOp(), cf.left(), cf.right()));
		assertNotEquals(cf, ComplexFunction.Zero);
	}
	
	@Test
	void testInitFromString() {
		ComplexFunction cf = new ComplexFunction();
		ComplexFunction cf2 = new ComplexFunction();
		ComplexFunction cf3 = new ComplexFunction();
		String s = "plus(x,x+5)";
		cf.initFromString(s);
		cf.setLeft(cf.initFromString("plus(x,x+5)")); 
		cf.setRight(cf.initFromString("plus(x,x+5)")); 
		cf.setOp(Operation.None); 
		cf2.setLeft(new Polynom("x"));
		cf2.setRight(cf2.initFromString("mul(x,x+5)"));
		cf2.setOp(Operation.None); 
		cf3.setLeft(new Polynom("2x"));
		cf3.setRight(cf3.initFromString("plus(0,0)"));
		cf3.setOp(Operation.Plus); 
		assertEquals(cf, cf2); // ***************************
		assertNotEquals(cf, cf3);
	}
	
	@Test
	void testComplexFunctionComplexFunction() {
		ComplexFunction cf = new ComplexFunction();
		function f = new Polynom("1");
		function g = new Polynom("x");
		cf.setLeft(f); 
		cf.setLeft(g); 
		cf.setOp(Operation.Plus); 
		ComplexFunction cf2 = new ComplexFunction(cf);
		assertEquals(cf, cf2);
		cf2.setOp(Operation.Max);
		ComplexFunction cf3 = new ComplexFunction(cf2);
		assertNotEquals(cf, cf3);
	}

	@Test
	void testF() {
		ComplexFunction cf = new ComplexFunction();
		cf.setLeft(new Polynom("1")); 
		cf.setLeft(new Polynom("x")); 
		cf.setOp(Operation.Plus);
		assertEquals(cf.f(0), 1, 0.001);    // ***************************
		assertNotEquals(cf.f(0), 3, 0.001); // ***************************
	}

	@Test
	void testCopy() {
		ComplexFunction cf = new ComplexFunction();
		cf.setLeft(new Monom("2x"));
		cf.setRight(new Polynom("x"));
		cf.setOp(Operation.Min);
		ComplexFunction cf2 = cf.copy();
		assertEquals(cf, cf2);
		cf.setLeft(new Polynom("45"));
		assertNotEquals(cf, cf2);
	}

	@Test
	void testPlus() {
		ComplexFunction cf = new ComplexFunction(Operation.Times,new Polynom("2x+5"),new Polynom("2"));
		ComplexFunction cf2 = new ComplexFunction(Operation.Comp,new Polynom("x^5"),new Polynom("x^2"));
		ComplexFunction cf3 = new ComplexFunction(cf2);
		cf3.plus(cf.copy());
		ComplexFunction cf4 = new ComplexFunction(Operation.Plus, cf2, cf);
		assertEquals(cf3, cf4); // *****************************************
		ComplexFunction cf5 = new ComplexFunction(cf4);
		cf5.plus(cf.copy());
		assertNotEquals(cf4, cf5.copy());
	}

	@Test
	void testMul() {
		ComplexFunction cf = new ComplexFunction();
		cf.setLeft(new Polynom("2x+5"));
		cf.setRight(new Polynom("2"));
		cf.setOp(Operation.Times);
		ComplexFunction cf2 = new ComplexFunction();
		cf2.setLeft(new Polynom("x^5"));
		cf2.setRight(new Polynom("x^2"));
		cf2.setOp(Operation.Comp);
		ComplexFunction cf3 = new ComplexFunction(cf2);
		cf3.mul(cf);
		ComplexFunction cf4 = new ComplexFunction();
		cf4.setLeft(cf2);
		cf4.setRight(cf);
		cf4.setOp(Operation.Times);
		assertEquals(cf3, cf4); // *****************************************
		ComplexFunction cf5 = new ComplexFunction(cf4);
		cf5.mul(cf);
		assertNotEquals(cf4, cf5);
	}

	@Test
	void testDiv() {
		ComplexFunction cf = new ComplexFunction();
		ComplexFunction cf2 = new ComplexFunction();
		cf.setLeft(new Polynom("2x"));
		cf.setOp(Operation.None);
		cf2 = cf.copy();
		cf.div(new Polynom("x^2"));
		assertEquals(new ComplexFunction(Operation.Divid,cf2,new Polynom("x^2")), cf);
		cf.setLeft(new Polynom("x"));
		assertNotEquals(new ComplexFunction(Operation.Divid,cf2,new Polynom("x^2")), cf);
	}

	@Test
	void testMax() {
		ComplexFunction cf = new ComplexFunction();
		cf.setLeft(new Monom("2x"));
		cf.setRight(new Polynom("x"));
		cf.setOp(Operation.Min);
		ComplexFunction cf2 = cf.copy();
		assertEquals(cf, cf2);
		cf.setLeft(new Polynom("45"));
		assertNotEquals(cf, cf2);
	}

	@Test
	void testMin() {
		ComplexFunction cf = new ComplexFunction();
		cf.setLeft(new Monom("2x"));
		cf.setRight(new Polynom("x"));
		cf.setOp(Operation.Min);
		ComplexFunction cf2 = cf.copy();
		assertEquals(cf, cf2);
		cf.setLeft(new Polynom("45"));
		assertNotEquals(cf, cf2);
	}

	@Test
	void testComp() {
		ComplexFunction cf = new ComplexFunction();
		cf.setLeft(new Monom("2x"));
		cf.setRight(new Polynom("x"));
		cf.setOp(Operation.Min);
		ComplexFunction cf2 = cf.copy();
		assertEquals(cf, cf2);
		cf.setLeft(new Polynom("45"));
		assertNotEquals(cf, cf2);
	}

	@Test
	void testLeft() {
		ComplexFunction cf = new ComplexFunction();
		cf.setLeft(new Monom("2x"));
		assertEquals(new Monom("2x"), cf.left());
		assertNotEquals(new Monom("3x"), cf.left());
	}

	@Test
	void testRight() {
		ComplexFunction cf = new ComplexFunction();
		cf.setRight(new Polynom("x"));
		assertEquals(new Polynom("x"), cf.right());
		assertNotEquals(new Polynom("x+1"), cf.right());
	}

	@Test
	void testGetOp() {
		ComplexFunction cf = new ComplexFunction();
		cf.setOp(Operation.Comp);
		assertEquals(cf.getOp(), Operation.Comp);
		assertNotEquals(cf.getOp(), Operation.Divid);
	}

}
