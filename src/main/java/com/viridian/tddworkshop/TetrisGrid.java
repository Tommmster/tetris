package com.viridian.tddworkshop;

import java.util.ArrayList;
import java.util.Collection;

import com.viridian.tddworkshop.geometry.HeightProfile;
import com.viridian.tddworkshop.geometry.Segment;

public class TetrisGrid implements Playground2D {

	/**
	 * Elements that have already landed.
	 */
	private Collection<TetrisBlock> landed;
	
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
	
	
	private final HeightProfile profile;
	
	public TetrisGrid(int width, int height){
		this.width = width;
		this.height = height;
		
		this.profile = new HeightProfile(0, this.width, this.bottom());
		
		this.startingPosition = new Position(this.width/2, this.top());
		this.landed = new ArrayList<TetrisBlock>();
	}
	
	public Iterable<TetrisBlock> getLandedElements(){
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
		return height;
	}
	
	public int leftSide(){
		return 0;
	}
	
	public int bottom() {
		return 0;
	}
	
	public int rightSide(){
		return this.width -1;
	}
	
	public void land(TetrisBlock element){
		
		this.landed.add(element);
		
		
		
	}
	

	private void raiseFloorAt(int xat, int height) {
		this.profile.raiseAt(xat, height);
	}

	/**
	 * 
	 * @param whatPoints an array containing a [from-to] range.
	 * @return an array of (to-from) elements. Each element will represent the height at that point
	 */
	public HeightProfile heightAt(Segment whatPoints) {
		return this.profile.heightRange(whatPoints.from(), whatPoints.to());
	}
}
