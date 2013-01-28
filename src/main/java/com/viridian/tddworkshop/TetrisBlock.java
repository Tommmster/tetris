package com.viridian.tddworkshop;

import com.viridian.tddworkshop.gameengine.StoppageListener;

public abstract class TetrisBlock implements Block{

	/**
	 * Whether this element is falling or not.
	 */
	private boolean isMoving;
	
	private StoppageListener listener;
	
	public TetrisBlock(){
		this.isMoving = false;
	}
	
	public boolean isMoving(){
		return this.isMoving;
	}
	
	public void start() {
		this.isMoving = true;
	}
	
	public void stop() {
		this.isMoving = false;
	}
	
	public boolean hasLanded(){
		return !isMoving;
	}
	
	public void fall(){
		this.move("down");
	}
	
	
	
		
	public void registerListener(StoppageListener listener){
		this.listener = listener;
	}
	
	interface TetrisFunction {
		void move (Iterable<Position> points);
	}
	
	class LeftFunction implements TetrisFunction {
		
		public void move(Iterable<Position> points) {
			for (Position p : points){
				p.x --;
			}
		}
	}
	
	class RightFunction implements TetrisFunction  {
		public void move(Iterable<Position> points) {
			
			for (Position p : points){
				p.x ++;
			}
		}
	}
	
	class DownFunction implements TetrisFunction {
		public void move(Iterable<Position> points) {
			for (Position p : points) {
				p.y --;
			}
		}
	}
}
