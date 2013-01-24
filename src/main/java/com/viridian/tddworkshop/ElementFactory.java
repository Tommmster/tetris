package com.viridian.tddworkshop;

public class ElementFactory {

	
	public Element createSquare (TetrisGrid grid){
		
		Element element = new Element(grid, 2);
		return element;
	}
	
	public Element createStraight(){
		return null;
	}
	
	public Element createL(){
		return null;
	}
	
	public Element createS(){
		return null;
	}
	
}
