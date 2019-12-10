package Ex1;

@SuppressWarnings("serial")
public class ComplexFunction implements complex_function{
	public static final ComplexFunction Zero = new ComplexFunction(Operation.None, Monom.ZERO, Monom.ZERO);
	private function left; 
	private function right;
	private Operation op;
	
	public static void main(String[] args){
		ComplexFunction cf = new ComplexFunction();
//		ComplexFunction cf2 = new ComplexFunction();
//		ComplexFunction cf3 = new ComplexFunction();
		String s = "plus(x,x+5)";
		System.out.println(s.substring(0, 5).equals("plus(" ) && s.charAt(s.length()-1) == ')');
		cf.initFromString("plus(x,x+5)");
		System.out.println((cf.op == Operation.Plus));
		System.out.println(cf.f(0));
	}

	public ComplexFunction(Operation op, function left, function right) {
		this.left = left;
		this.right = right;
		this.op = op;
	}
	
	public ComplexFunction() {;}
	
	public ComplexFunction(ComplexFunction f) {
		this.left = f.left.copy();
		this.right = f.right.copy();
		this.op = f.op;
	}
	
	public String toString() {
		switch (this.op){ 
		case Plus: 
			return "plus("+this.left.toString()+","+this.right.toString()+")"; 
		case Times: 
			return "mul("+this.left.toString()+","+this.right.toString()+")";
		case Divid: 
			return "div("+this.left.toString()+","+this.right.toString()+")";
		case Max: 
			return "max("+this.left.toString()+","+this.right.toString()+")";
		case Min: 
			return "min("+this.left.toString()+","+this.right.toString()+")";
		case Comp: 
			return "comp("+this.left.toString()+","+this.right.toString()+")";
		case None: 
			return this.left.toString();
		default: 
			return null; // TODO 
		}
	}
	
	public boolean equals(Object cf) {
		if( cf instanceof ComplexFunction ) {
			boolean flag;
			flag = (this.left == ((ComplexFunction) cf).left()) && (this.op == ((ComplexFunction) cf).getOp());
			if(flag && this.op != Operation.None ) {
				flag = (flag && (this.right == ((ComplexFunction) cf).right()) );
			}
			return flag;
		} else {
			return false;
		}
	}

	@SuppressWarnings("null")
	@Override
	public double f(double x) {
		switch (this.op){ 
		case Plus: 
			return (this.left.f(x)+this.right.f(x)); 
		case Times: 
			return (this.left.f(x)*this.right.f(x));
		case Divid: 
			return (this.left.f(x)/this.right.f(x));
		case Max: 
			return Math.max(this.left.f(x),this.right.f(x));
		case Min: 
			return Math.min(this.left.f(x),this.right.f(x));
		case Comp: 
			return (this.left.f(this.right.f(x)));
		case None: 
			return this.left.f(x);
		default: 
			return (Double) 0.; // TODO 
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
		String[] opertors = {"plus","mul","div", "min", "max"};
		Operation[] Opertors = {Operation.Plus,Operation.Times,Operation.Divid,Operation.Min,Operation.Max};
		for (int j = 0; j < opertors.length; j++) {
			if(s.length() >= opertors[j].length()+5 && s.substring(0, opertors[j].length()+1).equals(opertors[j]+"(" )
					&& s.charAt(s.length()-1) == ')') {
				int num = 0; // the number of open () מספר הסוגריים הפתוחים.
				for(int i = opertors[j].length()+1; i<s.length()-2; i++) {
					if (s.charAt(i) == '(') {
						num++ ;
					} else if (s.charAt(i) == ')') {
						num-- ;
					} else if (s.charAt(i) == ',' && num == 0) {
						ComplexFunction cf = new ComplexFunction();
						cf.left = cf.initFromString(s.substring(opertors[j].length()+1, i-1));
						cf.right = cf.initFromString(s.substring(i+1, s.length()-1));
						cf.op = Opertors[j];
						return cf;
					}
				}
			}
		}
		return new Polynom(s);
	}

	@Override
	public ComplexFunction copy() {
		return new ComplexFunction(this);
	}

	@Override
	public void plus(function f1) {
		ComplexFunction temp = new ComplexFunction(Operation.Plus, this.copy(), f1.copy());
		this.left = temp.left;
		this.right = temp.right;
		this.op = temp.op;
	}

	@Override
	public void mul(function f1) {
		ComplexFunction temp = new ComplexFunction(Operation.Times, this.copy(), f1.copy());
		this.left = temp.left;
		this.right = temp.right;
		this.op = temp.op;
	}

	@Override
	public void div(function f1) {
		ComplexFunction temp = new ComplexFunction(Operation.Divid, this.copy(), f1.copy());
		this.left = temp.left;
		this.right = temp.right;
		this.op = temp.op;
	}

	@Override
	public void max(function f1) {
		ComplexFunction temp = new ComplexFunction(Operation.Max, this.copy(), f1.copy());
		this.left = temp.left;
		this.right = temp.right;
		this.op = temp.op;
	}

	@Override
	public void min(function f1) {
		ComplexFunction temp = new ComplexFunction(Operation.Min, this.copy(), f1.copy());
		this.left = temp.left;
		this.right = temp.right;
		this.op = temp.op;
	}

	@Override
	public void comp(function f1) {
		ComplexFunction temp = new ComplexFunction(Operation.Comp, this.copy(), f1.copy());
		this.left = temp.left;
		this.right = temp.right;
		this.op = temp.op;
	}

	@Override
	public function left() {		
		return this.left.copy();
	}

	@Override
	public function right() {
		return this.right.copy();
	}

	@Override
	public Operation getOp() {
		return this.op;
	}

	public void setOp(Operation OP) {
		this.op = OP;
	}

	public void setLeft(function Left) {
		this.left = Left.copy();
	}

	public void setRight(function Right) {
		this.right = Right.copy();
	}
}
