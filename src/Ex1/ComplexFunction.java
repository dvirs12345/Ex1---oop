package Ex1;

import com.sun.org.apache.xpath.internal.operations.Operation;

public class ComplexFunction implements complex_function{
	function left; 
	function right;
	Operation op;
	
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
			break;
		case Times: 
			return (this.left.f(x)*this.right.f(x));
			break;
		case Divid: 
			return (this.left.f(x)/this.right.f(x));
			break;
		case Max: 
			return Math.max(this.left.f(x),this.right.f(x));
			break;
		case Min: 
			return Math.min(this.left.f(x),this.right.f(x));
			break;
		case Comp: 
			return (this.left.f(this.right.f(x)));
			break;
		case None: 
			return this.left.f(x);// TODO Auto-generated method stub
			break;
		case Error:
		default: 
			return null; 
			break;
		}
	}

	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		return null;
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
