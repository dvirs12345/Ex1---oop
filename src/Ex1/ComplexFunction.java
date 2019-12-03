package Ex1;

import com.sun.org.apache.xpath.internal.operations.Operation;

public class ComplexFunction implements complex_function{
	ComplexFunction left; 
	ComplexFunction right;
	Operation op;
	
	public ComplexFunction(ComplexFunction f) {
		this.left = f.left;
		this.right = f.right;
		this.op = f.op;
	}
	
	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		return 0;
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
