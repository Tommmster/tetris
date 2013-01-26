package com.viridian.tddworkshop.tetris.command;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.viridian.tddworkshop.Tetris;
import com.viridian.tddworkshop.gameengine.GameEngine;
import com.viridian.tddworkshop.gameengine.TetrisEngine;

/**
 * Listens for {up, down, left, right} arrow key events.
 */
public class TetrisKeyboardListener implements KeyListener {

	GameEngine tetrisEngine;
	
	private final Tetris tetris;
	
	public TetrisKeyboardListener( GameEngine engine, Tetris tetris){
		this.tetrisEngine = engine;
		this.tetris = tetris;
	}
	
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ESCAPE || e.getKeyChar() == 'q'){
			tetris.stop();
		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN){
			tetrisEngine.executeCommand(TetrisEngine.BOOST_DOWN);
			 System.out.println("Speeeed");
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT){
			tetrisEngine.executeCommand(TetrisEngine.MOVELEFT_COMMAND);
			System.out.println("Try to move left");
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT){
			tetrisEngine.executeCommand(TetrisEngine.MOVERIGHT_COMMAND);
			 System.out.println("Try to move right");
		}
		
		
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN){
			 System.out.println("Back to normal speed");
		}
	}
}