package com.viridian.tddworkshop.tetris.view;

public class Screen {

	final int gameWidth;
	
	final int gameHeight;
	
	public Screen(int w, int h){
		gameWidth = w;
		gameHeight = h;
	}

	public int getGameWidth() {
		return gameWidth;
	}

	public int getGameHeight() {
		return gameHeight;
	}
	
	
}
