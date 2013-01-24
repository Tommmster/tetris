package com.viridian.tddworkshop;

public class Position {

	int x;
	int y;
	
	public Position(){
		this.x =0 ;
		this.y = 0;
	}
	
	protected Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	protected Position(Position p){
		this.x = p.getX();
		this.y = p.getY();
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	
	public boolean equals(Object that){
		if ( !(that instanceof Position))
			return false;
		
		Position p = (Position)that;
	
		return p.getX() == this.x && p.getY() == this.y;
		
	}
	
	public String toString(){
		return "("+this.x+","+ this.y+")";
	}
}
