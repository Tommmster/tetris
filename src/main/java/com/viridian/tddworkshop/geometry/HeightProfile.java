package com.viridian.tddworkshop.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class HeightProfile implements Iterable<Integer> {

	private int [] heights;
	
	/**
	 * from which x coordinate this profile starts counting
	 */
	final int from;
	
	/**
	 * until which x coordinate this profile starts counting
	 */
	final int to;
	
	final int initialHeight;
	
	public HeightProfile(int from, int to, int initialHeight){
		this.initialHeight = initialHeight;
		this.from = from;
		this.to = to ;
		this.heights = new int[this.to - this.from];
		
		Arrays.fill(heights, initialHeight);
	}
	
	public int from(){
		return this.from;
	}
	
	public int to(){
		return this.to;
	}
	
	private HeightProfile(int from, int to, int[] heights, final int initialHeight){
		this.from = from;
		this.to = to;
		this.initialHeight = initialHeight;
		
		this.heights = new int [to - from];
		System.arraycopy(heights, from, this.heights, 0, to - from);
	}
	
	
	public Iterator<Integer> iterator() {
		List<Integer> l = new ArrayList<Integer>(heights.length);
		
		for (int i=0; i< heights.length; i++){
			l.add(this.heights[i]);
		}
		
		return Collections.unmodifiableList(l).iterator();
	}

	public HeightProfile heightRange(int xfrom, int xto){
		return new HeightProfile(xfrom, xto, this.heights, this.initialHeight);
	}
	
	public void raiseAt(int x, int howmuch){
		this.heights[x] += howmuch;
	}
	
	public boolean isFlat(){
		
		if (this.heights.length ==0) return false;
		
		if (this.heights.length == 1) return true;
		
		int first = this.heights[0];
		
		for (int i=1; i < this.heights.length; i++){
			if (heights[i] != first){
				return false;
			}
		}
		
		return true;
	}
	
	protected int [] getHeights(){
		return this.heights;
	}
	
	@Override
	public boolean equals(Object o){
		if (! (o instanceof HeightProfile)){
			return false;
		}
		
		HeightProfile that = (HeightProfile)o;
		
		return this.from == that.from() && this.to == that.to() && Arrays.equals(this.heights, that.getHeights());
	}
}
