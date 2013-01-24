package com.viridian.tddworkshop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.viridian.tddworkshop.gameengine.StoppageListener;


public class Element {
	

	/**
	 * Every element is contained inside a rectangular block. For the simplest case (a rectangle),
	 * every cell inside that block will be filled. For more complex elements, some will be empty (0)
	 */
	int blockShape[][] = {{1,1}, {1,1}};
	
	/**
	 * Width of the rectangular container/
	 */
	final private int blockWidth;
	
	/**
	 * Height of the rectangular container.
	 */
	final private int blockHeight;
	
	/**
	 * The amount of '1's in blockShape.
	 */
	final int numberOfCells = 4;
	
	
	
	private Position topRight;
	
	private Position bottomLeft;
	
	private  List<Position> vertexes;
	
	private final int numberOfCorners;
	
	private TetrisGrid grid;
	
	/**
	 * Whether this element has landed or not
	 */
	private boolean isMoving;
	
	Collection<StoppageListener> listeners;
	
	/**
	 * @param grid On which grid this element will move.
	 * @param numberofCorners
	 */
	public Element(TetrisGrid grid, int numberofCorners) {
		this.blockWidth = 2;
		this.blockHeight =2 ;
		
		numberOfCorners = numberofCorners;
		this.topRight = grid.getStartPosition();
		this.bottomLeft = new Position(this.topRight.x - blockWidth, this.topRight.y + blockHeight);
		
		this.grid = grid;
		
		vertexes = Arrays.asList(this.bottomLeft, this.topRight);
		
		this.isMoving = false;
		
		this.listeners = new ArrayList<StoppageListener>();
	}
	
	public void registerListener(StoppageListener listener){
		this.listeners.add(listener);
	}
	
	public void start(){
		this.isMoving = true;
	}
	
	public void stop(){
		this.isMoving = false;
	}
	
	public boolean hasLanded(){
		return !this.isMoving;
	}
	
	/**
	 * Moves the element in the grid. Does nothing if the element has reached the bottom of the grid.
	 * 
	 * @param where Will only consider "left" and "right" values.
	 */
	public void move(String where) {
		
		if (this.hasLanded()){
			System.out.println("already landed");
			return;
		}
		if (this.hasReachedBottom()) {
			System.out.println("reached bottom");
			for (StoppageListener listener : this.listeners){
				listener.elementHasStoppedMoving(this);
			}
			return;
		}
		
		if ("down".equals(where) ){
			this.bottomLeft.y+=1;
			this.topRight.y+=1;
			
			return;
			
		}
		if ("left".equals(where) && notPressedAgainstLeftBorder()){
			
			this.bottomLeft.x --;
			this.topRight.x --;
		}
		
		if ("right".equals(where) && notPressedAgainstRightBorder()){
			
			this.bottomLeft.x ++;
			this.topRight.x ++;
		}
	}
	
	public void idleMove(){
		if (hasReachedBottom() || this.hasLanded()) {
			
			for (StoppageListener listener : this.listeners){
				listener.elementHasStoppedMoving(this);
			}
			return;
		}
		
		this.bottomLeft.y++;
		this.topRight.y++;
	}
	
	private boolean notPressedAgainstLeftBorder() {
		return this.bottomLeft.x > this.grid.leftSide();
	}
	
	public boolean notPressedAgainstRightBorder() {
		return this.topRight.x <= this.grid.rightSide();
	}
	
	private boolean hasReachedBottom() {
		return this.bottomLeft.y >= this.grid.bottom();
	}

	public Position getBottomLeftCorner() {
		return new Position(this.bottomLeft.x, this.bottomLeft.y);
	}
	
	public Position getTopRightCorner() {
		return new Position(this.topRight.x, this.topRight.y);
	}
	
	public int getNumberOfCorners(){
		return this.numberOfCorners;
	}
	
	
	
	public int getWidth() {
		return blockWidth;
	}

	public int getHeight() {
		return blockHeight;
	}

	/**
	 * Returns an array containing a copy of the current position.
	 * @return
	 */
	public Position[] getCorners() {
		return new Position []{new Position(this.vertexes.get(0).x, this.vertexes.get(0).y), new Position(this.vertexes.get(1).x, this.vertexes.get(1).y)};
	}
	
	public Position getStartingPoint() {
		return getTopRightCorner();
	}
	
	protected void placeAt(Position bottomLeft){
		this.placeLeft(bottomLeft);
	}
	
	protected void placeRight(Position p){
		this.topRight = p;
		this.bottomLeft = new Position(p.x -this.blockWidth, p.y + this.blockHeight);
		
		this.vertexes = Arrays.asList(this.bottomLeft, this.topRight);
	}
	
	protected void placeLeft (Position p){
		this.bottomLeft = p;
		this.topRight = new Position(p.x + this.blockWidth , p.y - this.blockHeight);
		this.vertexes = Arrays.asList(this.bottomLeft, this.topRight);
	}
}