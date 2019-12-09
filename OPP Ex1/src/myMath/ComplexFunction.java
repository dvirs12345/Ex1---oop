package myMath;

@SuppressWarnings("serial")
public class ComplexFunction implements complex_function{
	private function left; 
	private function right;
	private Operation op;

	public ComplexFunction() {;}
	
	public ComplexFunction(ComplexFunction f) {
		this.left = f.left;
		this.right = f.right;
		this.op = f.op;
	}
	
	public boolean equals(Object cf) {
		if( !(cf instanceof ComplexFunction) ) {
			return false;
		} else {
			boolean flag = true;
			flag &= (this.op == ((ComplexFunction) cf).getOp());
			flag &= (this.left == ((ComplexFunction) cf).left());
			if(this.op != Operation.None ) {
				flag &= (this.right == ((ComplexFunction) cf).right());
			}
			return flag;
		}
	}

	@SuppressWarnings("null")
	@Override
	public double f(double x) {
		switch (this.op)
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
			return (Double) null; // TODO Auto-generated method stub
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
					cf.left = cf.initFromString(s.substring(5, i-1));
					cf.right = cf.initFromString(s.substring(i+1, s.length()-2));
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
					cf.left = cf.initFromString(s.substring(5, i-1));
					cf.right = cf.initFromString(s.substring(i+1, s.length()-2));
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
					cf.left = cf.initFromString(s.substring(5, i-1));
					cf.right = cf.initFromString(s.substring(i+1, s.length()-2));
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
					cf.left = cf.initFromString(s.substring(5, i-1));
					cf.right = cf.initFromString(s.substring(i+1, s.length()-2));
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
					cf.left = cf.initFromString(s.substring(5, i-1));
					cf.right = cf.initFromString(s.substring(i+1, s.length()-2));
					cf.op = Operation.Min;
					return cf;
				}
			}
		} else {
			return new Polynom(s);
		}
		ComplexFunction error = new ComplexFunction();
		error.op = Operation.Error;
		return error;

	}

	@Override
	public ComplexFunction copy() {
		return new ComplexFunction(this);
	}

	@Override
	public void plus(function f1) {
		ComplexFunction temp = new ComplexFunction();
		temp.left = new ComplexFunction(this);
		temp.right = f1;
		temp.op = Operation.Plus;
		this.left = temp.left;
		this.right = temp.right;
		this.op = temp.op;
	}

	@Override
	public void mul(function f1) {
		ComplexFunction temp = new ComplexFunction();
		temp.left = new ComplexFunction(this);
		temp.right = f1;
		temp.op = Operation.Times;
		this.left = temp.left;
		this.right = temp.right;
		this.op = temp.op;
	}

	@Override
	public void div(function f1) {
		ComplexFunction temp = new ComplexFunction();
		temp.left = new ComplexFunction(this);
		temp.right = f1;
		temp.op = Operation.Divid;
		this.left = temp.left;
		this.right = temp.right;
		this.op = temp.op;
	}

	@Override
	public void max(function f1) {
		ComplexFunction temp = new ComplexFunction();
		temp.left = new ComplexFunction(this);
		temp.right = f1;
		temp.op = Operation.Max;
		this.left = temp.left;
		this.right = temp.right;
		this.op = temp.op;
	}

	@Override
	public void min(function f1) {
		ComplexFunction temp = new ComplexFunction();
		temp.left = new ComplexFunction(this);
		temp.right = f1;
		temp.op = Operation.Min;
		this.left = temp.left;
		this.right = temp.right;
		this.op = temp.op;
	}

	@Override
	public void comp(function f1) {
		ComplexFunction temp = new ComplexFunction();
		temp.left = new ComplexFunction(this);
		temp.right = f1;
		temp.op = Operation.Comp;
		this.left = temp.left;
		this.right = temp.right;
		this.op = temp.op;
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

	public void setOp(Operation OP) {
		this.op = OP;
	}

	public void setLeft(function Left) {
		this.left = Left;
	}

	public void setRight(function Right) {
		this.right = Right;
	}
}
