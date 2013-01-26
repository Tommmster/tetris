package com.viridian.tddworkshop.app;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.viridian.tddworkshop.Tetris;
import com.viridian.tddworkshop.tetris.view.Screen;

public class TDDTetris {

	
	public static void main(String[] args) {
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Tetris game = new Tetris();
		game.start(new Screen((int)screen.getWidth(), (int)screen.getHeight()));
	}
}
