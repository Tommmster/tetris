package com.viridian.tddworkshop.tetris.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.viridian.tddworkshop.Element;
import com.viridian.tddworkshop.Position;
import com.viridian.tddworkshop.TetrisGrid;
import com.viridian.tddworkshop.gameengine.GameEngine;
import com.viridian.tddworkshop.gameengine.TetrisEngine;
import com.viridian.tddworkshop.sceneUtils.SceneTransformer;

public class TetrisGridPanel extends JPanel {
	
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
	
	public TetrisGridPanel (TetrisGrid grid, GameEngine gameEngine){
		
		this.gameEngine = (TetrisEngine) gameEngine;
		
		this.width = 400;
		this.height = 800;
		
		
		this.coordinateTransformer = new SceneTransformer(grid.getWidth(), grid.getHeight(), this.width,this.height);
		
		this.setBorder(BorderFactory.createLineBorder(Color.red));
		this.setBounds(100,100,this.width, this.height);
		this.setPreferredSize(new Dimension(this.width, this.height));
		this.setVisible(true);
	}
	
	private int[] sceneXpoints(int x, int width){
		return new int[] {x, x + width, x + width, x };
	}
	
	private int []sceneYpoints(int y, int height){
		return new int[] {y , y , (y + height) , (y + height) };
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		drawGrid(g);
		Color red = Color.red;
		Color black = Color.BLACK;
		
		
		drawElement(g, red, gameEngine.getCurrentElement());
		
		for (Element landedElement : gameEngine.getGrid().getLandedElements()){
			drawElement(g,black, landedElement);
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

	private void drawElement(Graphics g, Color red, Element element) {
		//Works only for squares
		Position bottomLeft = element.getBottomLeftCorner();
		int[] xPoints = this.sceneXpoints(bottomLeft.getX(), element.getWidth());
		int[] yPoints = this.sceneYpoints(bottomLeft.getY(), element.getHeight());
		int nPoints = 4;
		
		
		
		g.setColor(red);
		
//		g.fillRoundRect(x, y, nPoints, nPoints, 3, 3)
		g.fillPolygon(this.coordinateTransformer.xtransform(xPoints), this.coordinateTransformer.xtransform(yPoints), nPoints);
	}
}