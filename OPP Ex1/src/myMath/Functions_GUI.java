package myMath;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import com.google.gson.*;

public class Functions_GUI implements functions {
	private LinkedList<function> arr;
	private Color[] colors = { 
			Color.BLUE,
			Color.CYAN,
			Color.GREEN,
			Color.ORANGE,
			Color.MAGENTA,
			Color.PINK,
			Color.RED,
			Color.YELLOW
	};
	
	
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
		File OutputFile = new File(file);
		BufferedReader br = new BufferedReader(new FileReader(OutputFile) );
		String stTemp;
		while ((stTemp = br.readLine()) != null) {
			String row = stTemp;
			ComplexFunction cf = (ComplexFunction) ComplexFunction.help1.initFromString(row);
			cf.initFromString(row);
			this.add(cf);
		}
		br.close();
	}

	@Override
	public void saveToFile(String file) throws IOException {
		try {
			File OutputFile = new File(file);
			OutputFile.createNewFile();
			FileWriter fw = new FileWriter(OutputFile);
			PrintWriter pw = new PrintWriter(fw);
			String st = "";
			Iterator<function> it = this.arr.iterator();
			int cont = 0;
			while (it.hasNext()) {
				st += cont+") "+colors[cont%colors.length].toString()+" f(x)= ";
				function function = it.next();
				st += (function.toString());
				if(it.hasNext())
					st += "\n";
				cont++;
			}
			pw.println( st);
			pw.close();
			BufferedReader br = new BufferedReader(new FileReader(OutputFile) );
			String stTemp;
			while ((stTemp = br.readLine()) != null) {
				System.out.println(stTemp);
			}
			pw.println(st);
			pw.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(rx.get_min(),rx.get_max());
		StdDraw.setYscale(ry.get_min(),ry.get_max());
		StdDraw.setPenRadius(0.002);
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());
		for (int i = (int) rx.get_min(); i < rx.get_max()+1 ; i++) {
			StdDraw.text(i, -0.25, ""+i);
		}
		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
		for (int i = (int) ry.get_min(); i < ry.get_max()+1 ; i++) {
			StdDraw.text(-0.25,i , ""+i);
		}
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
		int j = 0;
		while (it.hasNext()) {
			StdDraw.setPenColor(colors[j%colors.length]);
			function f = it.next();
			double[] x = new double[resolution];
			double[] y = new double[resolution];
			for (int i = 0; i < resolution; i++) {
				x[i] = rx.get_min() + i*(rx.get_max()-rx.get_min())/(resolution);
				y[i] = f.f(x[i]);
				if(i>0 && ( ry.isIn(y[i-1]) || ry.isIn(y[i])))
					StdDraw.line(x[i-1], y[i-1], x[i], y[i]);
			}
			j++;
		}
	}

	public static void main(String[] args) throws IOException{
		Functions_GUI fg0 = new Functions_GUI();
		fg0.initFromFile("function_file.txt");
		Functions_GUI fg = new Functions_GUI();
		fg.add( new Polynom("X^2"));
		fg.add( new Polynom("X+3"));
		fg.add( new Polynom("3"));
		fg.add( new Polynom("-x+1"));
		fg.add( new Polynom("X"));
		fg.add( new Polynom("-0.5x"));
		fg.add( new Polynom("X^3"));
		fg.add( new Sinus(new Polynom("5X+1")));
//		fg.drawFunctions("GUI_params.txt");
		String file = "out.txt";
		try {
			fg.saveToFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		fg.drawFunctions(1200, 600, new Range(-9, 11), new Range(-3, 7), 800);
	}

	@Override
	public void drawFunctions(String json_file) {
		File Json = new File(json_file);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(Json));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String st0 ="";
		String stTemp;
		try {
			while ((stTemp = br.readLine()) != null) {
				st0 += stTemp;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		GUI_params params = gson.fromJson(st0, GUI_params.class);
		Range rx = new Range(params.Range_X[0], params.Range_X[1]);
		Range ry = new Range(params.Range_Y[0], params.Range_Y[1]);
		this.drawFunctions(params.Width, params.Height, rx, ry, params.Resolution);
	}
	
	private class GUI_params {
		int Width;
		int Height;
		int Resolution;
		int[] Range_X;
		int[] Range_Y;
		
		@SuppressWarnings("unused")
		public GUI_params(int width, int height, int resolution, int[] Range_X, int[] Range_Y) {
			this.Width = width;
			this.Height = height;
			this.Resolution = resolution;
			this.Range_X = Range_X;
			this.Range_Y = Range_Y;
		}
	}
}
