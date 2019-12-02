package Ex1;

public class PolynomTest {
	public static void main(String[] args) {
		test0();
		test1();
		test2();
		test3();
	}
	public static void test0() {

		System.out.println("*****  Test0:  *****");
		Polynom p1 = new Polynom();
		String[] monoms = {"-11.33","x","-x^2", "0.5x^2", "-0.5x^5", "0.5x^6"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			p1.add(m);
		}
		Polynom p2 = new Polynom();
		String[] monoms2 = {"-0X","45x","-x^56", "-5x^2", "-x^5", "13X^4"};
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println(p1);
		System.out.println(p2+"\n");
	}
	public static void test1() {
		System.out.println("*****  Test1:  *****");
		Polynom p1 = new Polynom();
		String[] monoms = {"1","x"};
		Monom m = new Monom(monoms[1]);
		p1.add(m);
		double aa = p1.area(0, 1, 0.0001);
		System.out.println(aa);
		p1.substract(p1);
		System.out.println(p1+"\n");

	}
	public static void test2() {
		System.out.println("*****  Test2:  *****");
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-1.7x","-3.2x^2","4","-1.5x^2"};
		String[] monoms2 = {"5","-x","1.7x", "3.2x^2","-3","-1.5x^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		p1.add(p2);
		System.out.println("p1+p2: "+p1);
		p1.multiply(p2);
		System.out.println("(p1+p2)*p2: "+p1);
		String s1 = p1.toString();
		Polynom_able pp1 = new Polynom(s1);
		System.out.println("from string: "+pp1+"\n");
	}
	public static void test3() {
		System.out.println("*****  Test3:  *****");
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x"};
		String[] monoms2 = {"5", "1.7x"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		p1.add(p2);
		System.out.println("p1+p2: "+p1);
		p1.multiply(p2);
		System.out.println("(p1+p2)*p2: "+p1);
		String s1 = p1.toString();
		Polynom_able pp1 = new Polynom(s1);
		System.out.println("from string: "+pp1);
	}
}
