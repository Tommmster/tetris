package com.viridian.tddworkshop;

public class ElementFactory {

	
	public TetrisBlock createSquare (Playground2D grid){
		return new SquareBlock(grid, 2);
	}
	
	public TetrisBlock createStraight(Playground2D grid){
		return new StickBlock(grid);
	}
	
	public SquareBlock createL(){
		return null;
	}
	
	public SquareBlock createS(){
		return null;
	}
	
}
