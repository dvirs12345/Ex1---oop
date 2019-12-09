package myMath;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class ComplexFunctionTest {
	
	@Test
	void testInitFromString() {
		ComplexFunction cf = new ComplexFunction();
		ComplexFunction cf2 = new ComplexFunction();
		cf.setLeft(cf.initFromString("x")); 
		cf.setOp(Operation.None); 
		cf2.setLeft(new Polynom("x"));
		cf2.setOp(Operation.None); 
		assertEquals(cf, cf2); // ***************************
	}
	
	@Test
	void testComplexFunctionComplexFunction() {
		ComplexFunction cf = new ComplexFunction();
		cf.setLeft(new Polynom("1")); 
		cf.setLeft(new Polynom("x")); 
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
		assertEquals(cf.f(0), 1, 0.00001);
		assertNotEquals(cf.f(0), 3); // ***************************
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
		ComplexFunction cf = new ComplexFunction();
		cf.setLeft(new Polynom("2x+5"));
		cf.setRight(new Polynom("2"));
		cf.setOp(Operation.Times);
		ComplexFunction cf2 = new ComplexFunction();
		cf2.setLeft(new Polynom("x^5"));
		cf2.setRight(new Polynom("x^2"));
		cf2.setOp(Operation.Comp);
		ComplexFunction cf3 = new ComplexFunction(cf2);
		cf3.plus(cf);
		ComplexFunction cf4 = new ComplexFunction();
		cf4.setLeft(cf2);
		cf4.setRight(cf);
		cf4.setOp(Operation.Plus);
		assertEquals(cf3, cf4); // *****************************************
		ComplexFunction cf5 = new ComplexFunction(cf4);
		cf5.plus(cf);
		assertNotEquals(cf4, cf5);
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
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testMax() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testMin() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testComp() {
		fail("Not yet implemented"); // TODO
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
