package com.viridian.tddworkshop;

import java.util.ArrayList;
import java.util.Collection;

public class TetrisGrid {

	/**
	 * Elements that have already landed.
	 */
	private Collection<Element> landed;
	
	/**
	 * Grid width (logical units)
	 */
	private final int width;
	
	/**
	 *  Grid height (logical units).
	 */
	private final int height;
	
	/**
	 * Where will the next one element will start.
	 */
	private final Position startingPosition;
	
	
	public TetrisGrid(int width, int height){
		this.width = width;
		this.height = height;
		
		this.startingPosition = new Position(this.width /2, this.top());
//		this.startingPosition = new Position(0, 0);
		System.out.println("Starting position at " + this.startingPosition);
		this.landed = new ArrayList<Element>();
	}
	
	public Iterable<Element> getLandedElements(){
		return this.landed;
	}
	
	public Position getStartPosition(){
		return new Position(this.startingPosition);
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	
	public int top() {
		return 0;
	}
	
	public int leftSide(){
		return 0;
	}
	
	public int bottom() {
		return this.height-1;
	}
	
	public int rightSide(){
		return this.width -1;
	}
	
	public void land(Element element){
		this.landed.add(element);
	}
}
