package myMath;

import java.awt.Color;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import com.google.gson.*;

public class Functions_GUI implements functions {
	private LinkedList<function> arr;
	
	public Functions_GUI() {
		this.arr = new LinkedList<function>();
	}
	
	@Override
	public boolean add(function arg0) {
		return this.arr.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends function> arg0) {
		return this.arr.addAll(arg0); 
	}

	@Override
	public void clear() {
		this.arr.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		return this.arr.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return this.arr.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return this.arr.isEmpty();
	}

	@Override
	public Iterator<function> iterator() {
		return this.arr.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		return this.arr.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return this.arr.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return this.arr.retainAll(arg0);
	}

	@Override
	public int size() {
		return this.arr.size();
	}

	@Override
	public Object[] toArray() {
		return this.arr.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return this.arr.toArray(arg0);
	}

	@Override
	public void initFromFile(String file) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveToFile(String file) throws IOException {
		Iterator<function> it = this.arr.iterator();
		while (it.hasNext()) {
			function function = it.next();
			file += function.toString()+"\n";
		}
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		// TODO Auto-generated method stub
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(rx.get_min(),rx.get_max());
		StdDraw.setYscale(ry.get_min(),ry.get_max());
		StdDraw.setPenRadius(0.0001);
		StdDraw.setFont();
		for (int i = (int) rx.get_min(); i < rx.get_max(); i+=((rx.get_max()-rx.get_min())/20)) {
			StdDraw.line(i, ry.get_min(), i, ry.get_max());
		}
		for (int i = (int) ry.get_min(); i < ry.get_max(); i+=((rx.get_max()-rx.get_min())/20)) {
			StdDraw.line(-width/2, i, width/2, i);
		}
		StdDraw.setPenRadius(0.002);
		Iterator<function> it = this.arr.iterator();
		Color[] colors = { 
				Color.BLUE,
				Color.CYAN,
				Color.GREEN,
				Color.ORANGE,
				Color.MAGENTA,
				Color.PINK,
				Color.RED,
				Color.YELLOW
		};
		int j = 0;
		while (it.hasNext()) {
			StdDraw.setPenColor(colors[j%colors.length]);
			function f = it.next();
			double[] x = new double[resolution];
			double[] y = new double[resolution];
			for (int i = 0; i < resolution; i++) {
				x[i] = rx.get_min() + i*(rx.get_max()-rx.get_min())/(resolution);
				y[i] = f.f(x[i]);
				if(i>0)
					StdDraw.line(x[i-1], y[i-1], x[i], y[i]);
			}
			j++;
		}
	}

	public static void main(String[] args){
		Functions_GUI fg = new Functions_GUI();
		fg.add( new Polynom("X^2"));
		fg.add( new Polynom("X+3"));
		fg.add( new Polynom("3"));
		fg.add( new Polynom("-x+1"));
		fg.add( new Polynom("X"));
		fg.add( new Polynom("-0.5x"));
		fg.add( new Polynom("X^3"));
		fg.add( new Sinus(new Polynom("5X+1")));
		fg.drawFunctions(1200, 600, new Range(-9, 11), new Range(-3, 7), 800);
	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub
		Gson c = new Gson();
		 
	}

}
