package com.viridian.tddworkshop;

import java.util.ArrayList;
import java.util.List;

public class StickBlock extends TetrisBlock {
	
	final int length = 4;

	private Position startAt;
	
	public StickBlock(Playground2D playground){
		startAt = playground.getStartPosition();
	}
	
	public boolean move(String where){
		return false;
	}

	public Iterable<Position> deconstruct() {
		List<Position> cells = new ArrayList<Position>();
		
		final int initialx = this.startAt.getX() -1;
		final int initialy = this.startAt.getY() -1;
		
		for (int i=0 ; i < this.length; i++){
			cells.add(new Position(initialx, initialy - i));
		}
		return cells;
	}
	
	 
	
	
}
