package com.viridian.tddworkshop;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.viridian.tddworkshop.gameengine.TetrisEngine;
import com.viridian.tddworkshop.tetris.command.TetrisKeyboardListener;
import com.viridian.tddworkshop.tetris.view.TetrisGridPanel;

public class Tetris extends JFrame{

	JPanel grid;
	
	private TetrisGridPanel theGrid;

	
	/**
	 * Create the empty scenario and display it.
	 */
	public void start() {
		
		final TetrisEngine gameEngine = new TetrisEngine();
		JFrame frame = new JFrame ("TDD Tetris");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1280, 1000));
		
		
		JLayeredPane pane = new JLayeredPane();
		
		theGrid = new TetrisGridPanel(new TetrisGrid(10, 20), gameEngine);
		theGrid.setFocusable(true);
		theGrid.requestFocusInWindow();
		theGrid.addKeyListener(new TetrisKeyboardListener(gameEngine, this));
		
		frame.setContentPane(pane);
		frame.getContentPane().add(theGrid);
		frame.pack();
		frame.setVisible(true);
		
		ActionListener repaintAction = new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				theGrid.repaint();
			}
		};
		
		ActionListener idleAction= new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				gameEngine.onIdle();
			}
		};
		
		Timer repaintTimer = new Timer(100,repaintAction);
		Timer idleTimer = new Timer(200,idleAction);
		
		
		repaintTimer.start();
		idleTimer.start();
	}
	
	public void stop(){
			
		
		
		System.exit(0);
	}
}
