package Ex1;

import com.sun.org.apache.xpath.internal.operations.Operation;

public class ComplexFunction implements complex_function{
	function left; 
	function right;
	Operation op;

	public ComplexFunction() {;}
	
	public ComplexFunction(ComplexFunction f) {
		this.left = f.left;
		this.right = f.right;
		this.op = f.op;
	}

	@Override
	public double f(double x) {
		switch (this.Operation)
		{ 
		case Plus: 
			return (this.left.f(x)+this.right.f(x)); 
			//			break;
		case Times: 
			return (this.left.f(x)*this.right.f(x));
			//			break;
		case Divid: 
			return (this.left.f(x)/this.right.f(x));
			//			break;
		case Max: 
			return Math.max(this.left.f(x),this.right.f(x));
			//			break;
		case Min: 
			return Math.min(this.left.f(x),this.right.f(x));
			//			break;
		case Comp: 
			return (this.left.f(this.right.f(x)));
			//			break;
		case None: 
			return this.left.f(x);// TODO Auto-generated method stub
			//			break;
		case Error:
		default: 
			return null; 
			//			break;
		}
	}

	@Override
	public function initFromString(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ') {
				s = s.substring(0, i)+s.substring(i+1, s.length());
				i--;
			}
		}
		if(s.length() >= 9 && s.substring(0, 5).equals("plus(" )
				&& s.charAt(s.length()-1) == ')') {
			int num = 0; // the number of open () מספר הסוגריים הפתוחים.
			for(int i = 5; i<s.length()-2; i++) {
				if (s.charAt(i) == '(') {
					num++ ;
				} else if (s.charAt(i) == ')') {
					num-- ;
				} else if (s.charAt(i) == ',' && num == 0) {
					ComplexFunction cf = new ComplexFunction();
					cf.left = ComplexFunction.initFromString(s.substring(5, i-1));
					cf.right = ComplexFunction.initFromString(s.substring(i+1, s.length()-2));
					cf.op = Operation.Plus;
					return cf;
				}
			}
		} else if(s.length() >= 8 && s.substring(0, 4).equals( "mul(") 
				&& s.charAt(s.length()-1) == ')') {
			int num = 0; // the number of open ().
			for(int i = 4; i<s.length()-2; i++) {
				if (s.charAt(i) == '(') {
					num++ ;
				} else if (s.charAt(i) == ')') {
					num-- ;
				} else if (s.charAt(i) == ',' && num == 0) {
					ComplexFunction cf = new ComplexFunction();
					cf.left = ComplexFunction.initFromString(s.substring(5, i-1));
					cf.right = ComplexFunction.initFromString(s.substring(i+1, s.length()-2));
					cf.op = Operation.Times;
					return cf;
				}
			}
		}  else if(s.length() >= 8 && s.substring(0, 4).equals("div(" )
				&& s.charAt(s.length()-1) == ')') {
			int num = 0; // the number of open ().
			for(int i = 4; i<s.length()-2; i++) {
				if (s.charAt(i) == '(') {
					num++ ;
				} else if (s.charAt(i) == ')') {
					num-- ;
				} else if (s.charAt(i) == ',' && num == 0) {
					ComplexFunction cf = new ComplexFunction();
					cf.left = ComplexFunction.initFromString(s.substring(5, i-1));
					cf.right = ComplexFunction.initFromString(s.substring(i+1, s.length()-2));
					cf.op = Operation.Divid;
					return cf;
				}
			}
		} else if(s.length() >= 8 && s.substring(0, 4).equals("max(" )
				&& s.charAt(s.length()-1) == ')') {
			int num = 0; // the number of open ().
			for(int i = 4; i<s.length()-2; i++) {
				if (s.charAt(i) == '(') {
					num++ ;
				} else if (s.charAt(i) == ')') {
					num-- ;
				} else if (s.charAt(i) == ',' && num == 0) {
					ComplexFunction cf = new ComplexFunction();
					cf.left = ComplexFunction.initFromString(s.substring(5, i-1));
					cf.right = ComplexFunction.initFromString(s.substring(i+1, s.length()-2));
					cf.op = Operation.Max;
					return cf;
				}
			}
		} else if(s.length() >= 8 && s.substring(0, 4).equals("min(" )
				&& s.charAt(s.length()-1) == ')') {
			int num = 0; // the number of open ().
			for(int i = 4; i<s.length()-2; i++) {
				if (s.charAt(i) == '(') {
					num++ ;
				} else if (s.charAt(i) == ')') {
					num-- ;
				} else if (s.charAt(i) == ',' && num == 0) {
					ComplexFunction cf = new ComplexFunction();
					cf.left = ComplexFunction.initFromString(s.substring(5, i-1));
					cf.right = ComplexFunction.initFromString(s.substring(i+1, s.length()-2));
					cf.op = Operation.Min;
					return cf;
				}
			}
		} else {
			return new Polynom(s);
		}

	}

	@Override
	public function copy() {
		return new ComplexFunction(this);
	}

	@Override
	public void plus(function f1) {
		ComplexFunction temp = new ComplexFunction();
		temp.left = new ComplexFunction(this);
		temp.right = f1;
		temp.op = Operation.plus;
		this = temp;
	}

	@Override
	public void mul(function f1) {
		ComplexFunction temp = new ComplexFunction();
		temp.left = new ComplexFunction(this);
		temp.right = f1;
		temp.op = Operation.Times;
		this = temp;
	}

	@Override
	public void div(function f1) {
		ComplexFunction temp = new ComplexFunction();
		temp.left = new ComplexFunction(this);
		temp.right = f1;
		temp.op = Operation.Divid;
		this = temp;
	}

	@Override
	public void max(function f1) {
		ComplexFunction temp = new ComplexFunction();
		temp.left = new ComplexFunction(this);
		temp.right = f1;
		temp.op = Operation.Max;
		this = temp;
	}

	@Override
	public void min(function f1) {
		ComplexFunction temp = new ComplexFunction();
		temp.left = new ComplexFunction(this);
		temp.right = f1;
		temp.op = Operation.Min;
		this = temp;
	}

	@Override
	public void comp(function f1) {
		ComplexFunction temp = new ComplexFunction();
		temp.left = new ComplexFunction(this);
		temp.right = f1;
		temp.op = Operation.Comp;
		this = temp;
	}

	@Override
	public function left() {		
		return this.left;
	}

	@Override
	public function right() {
		return this.right;
	}

	@Override
	public Operation getOp() {
		return this.op;
	}

}
