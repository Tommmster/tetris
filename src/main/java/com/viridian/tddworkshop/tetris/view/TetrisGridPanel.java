package com.viridian.tddworkshop.tetris.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.viridian.tddworkshop.Position;
import com.viridian.tddworkshop.SquareBlock;
import com.viridian.tddworkshop.TetrisBlock;
import com.viridian.tddworkshop.TetrisGrid;
import com.viridian.tddworkshop.gameengine.GameEngine;
import com.viridian.tddworkshop.gameengine.TetrisEngine;
import com.viridian.tddworkshop.sceneUtils.SceneTransformer;

public class TetrisGridPanel extends JPanel {
	
	private static final int YOFFSET = 0;

	private static final int XOFFSET = 0;

	private static final long serialVersionUID = 1L;

	/**
	 * Grid width (in pixels)
	 */
	final int width;
	
	/**
	 * Grid height (in pixels)
	 */
	final int height;

	private final TetrisEngine gameEngine;
	
	private SceneTransformer coordinateTransformer;

	private final TetrisGrid tetrisGrid;
	
	public TetrisGridPanel (TetrisGrid grid, GameEngine gameEngine){
		
		this.tetrisGrid = grid;
		this.gameEngine = (TetrisEngine) gameEngine;
		
		this.width = 400;
		this.height = 800;
		
		
		this.coordinateTransformer = new SceneTransformer(grid.getWidth(), grid.getHeight(), this.width,this.height);
		
		this.setBorder(BorderFactory.createLineBorder(Color.red));
		this.setBounds(XOFFSET,YOFFSET,this.width, this.height);
		this.setPreferredSize(new Dimension(this.width, this.height));
		this.setVisible(true);
	}

	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		//drawGrid(g);
		Color red = Color.red;
		Color black = Color.BLACK;
		
		
		drawElement(g, red, gameEngine.getCurrentElement(), Color.BLACK);
		
		for (TetrisBlock landedElement : gameEngine.getGrid().getLandedElements()){
			drawElement(g,black, landedElement, Color.BLACK);
		}
	}

	private void drawGrid(Graphics g) {
		
		final int from = 0;
		int to = this.gameEngine.getGrid().getHeight();
		g.setColor(Color.GREEN);
		
		for (int i=0;i < this.gameEngine.getGrid().getWidth(); i++){
			
			int x[] = this.coordinateTransformer.scaleHorizontalLine(i, i);
			int y[] = this.coordinateTransformer.scaleHorizontalLine(from, to);
			
			g.drawLine(x[0], y[0], x[1],y[1]);
		}
		
		to = this.gameEngine.getGrid().getWidth();
		
		for (int i=0;i < this.gameEngine.getGrid().getHeight(); i++){
			
			int x[] = this.coordinateTransformer.scaleHorizontalLine(from, to);
			int y[] = this.coordinateTransformer.scaleHorizontalLine(i, i);
			
			g.drawLine(x[0], y[0], x[1],y[1]);
		}
	}

	private void drawElement(Graphics g, Color fillColor, TetrisBlock element, Color borderColor) {
	
		for (Position cellVertex : element.deconstruct()){
			final Position worldVertex = this.coordinateTransformer.traslate(cellVertex);
			int s[] = this.coordinateTransformer.scaleHorizontalLine(0, 1);
			int one = s[1];

			g.setColor(fillColor);
			g.fillRoundRect(worldVertex.getX(),worldVertex.getY(), one, one, 10, 10);
			g.setColor(borderColor);
			g.drawRoundRect(worldVertex.getX(), worldVertex.getY(),one, one, 10, 10);
		}
	}
}