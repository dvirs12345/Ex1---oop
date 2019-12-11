package myMath;

@SuppressWarnings("serial")
public class ComplexFunction implements complex_function{
	public static final ComplexFunction Zero = new ComplexFunction(Operation.None, Monom.ZERO, Monom.ZERO);
	public static final ComplexFunction help1 = new ComplexFunction(Operation.Times, new Monom("1"), new Monom("1"));
	private final double eps = 0.000001;
	private final Range EqX_Range = new Range(-5000, 5000);
	private final double EqStep = 0.02;

	private function left; 
	private function right;
	private Operation op;

	public ComplexFunction(String op, function left, function right) {
		String[] opertors = {"plus","mul","div", "min", "max", "comp"};
		Operation[] Opertors = {Operation.Plus,Operation.Times,Operation.Divid,Operation.Min,Operation.Max,Operation.Comp};
		this.left = left;
		this.right = right;
		boolean flag = true;
		for (int i = 0; i < Opertors.length; i++) {
			if(op.equals(opertors[i])) {
				this.op = Opertors[i];
				flag = false;
			}
		}
		if(flag)
			throw new RuntimeException("");
	}
	
	public ComplexFunction(Operation op, function left, function right) {
		this.left = left;
		this.right = right;
		this.op = op;
	}
	
	public ComplexFunction( function left) {
		this.left = left;
		this.right = help1;
		this.op = Operation.None;
	}

	public ComplexFunction(ComplexFunction f) {
		this.left = f.left.copy();
		this.right = f.right.copy();
		this.op = f.op;
	}

	public String toString() {
		try {
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
				return null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("");	
		}
	}

	private boolean equalsInXRaenge(Object cf, double x1, double x2, double stepSise, double eps) {
		if( cf instanceof ComplexFunction ) {
			double min = Math.min(x1, x2);
			double max = Math.max(x1, x2);
			for (double i = min; i < max; i+=stepSise) {
				if( Math.abs(((ComplexFunction) cf).f(i)- this.f(i)) > eps ) {
					return false;
				}
			}
			return true;
		}else { 
			return false;
		}

	}

	public boolean equals(Object cf) {
		if( cf instanceof ComplexFunction ) {
			return equalsInXRaenge(cf, EqX_Range.get_min(), EqX_Range.get_max(), EqStep, eps);
		} else {
			return false;
		}
	}

	@SuppressWarnings("null")
	@Override
	public double f(double x) {
		try {
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
				return (Double) null; 
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("");	
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
		String[] opertors = {"plus","mul","div", "min", "max", "comp"};
		Operation[] Opertors = {Operation.Plus,Operation.Times,Operation.Divid,Operation.Min,Operation.Max,Operation.Comp};
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
						ComplexFunction cf = new ComplexFunction( Opertors[j],
								help1.initFromString(s.substring(opertors[j].length()+1, i)),
								help1.initFromString(s.substring(i+1, s.length()-1)));
						return cf;
					}
				}
			}
		}
		return new ComplexFunction(Operation.None, new Polynom(s), help1);
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
