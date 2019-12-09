package myMath;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub
		
	}

}
