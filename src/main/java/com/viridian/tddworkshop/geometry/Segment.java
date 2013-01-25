package com.viridian.tddworkshop.geometry;

import java.util.Iterator;

public class Segment implements Iterable<Integer> {

	int from;
	int to;
	
	
	
	public Segment(int from, int to){
		this.from = from;
		this.to = to;
	}
	
	public int length(){
		return to - from;
	}
	
	public int from(){
		return this.from;
	}
	
	public int to(){
		return this.to;
	}

	public void moveLeft(){
		this.from --;
		this.to --;
	}
	
	public void moveRight(){
		this.from ++;
		this.to ++;
	}
	public Iterator<Integer> iterator() {
		return new Range(this.from, this.to);
	}
	
	@Override
	public boolean equals(Object o){
		if (! (o instanceof Segment)){
			return false;
		}
		
		final Segment that = (Segment)o;
		return this.from == that.from() && this.to == that.to();
	}
	
	
	public String toString(){
		return this.from + " " + this.to;
	}
	
	class Range implements Iterator<Integer>{
		
		private final int from;
		
		private final int howmany;
		
		private int current;
		
		public Range (int from, int to){
			this.from = from;
			this.howmany = to - from;
			
			this.current = from;
		}

		public boolean hasNext() {
			return current < from + howmany;
		}

		public Integer next() {
			return ++ current;
		}

		public void remove() {
			throw new UnsupportedOperationException("Cant remove an item from a continous range");
		}
	}
}
