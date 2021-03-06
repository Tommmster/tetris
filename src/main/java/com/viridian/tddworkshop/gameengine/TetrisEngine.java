package com.viridian.tddworkshop.gameengine;

import com.viridian.tddworkshop.ElementFactory;
import com.viridian.tddworkshop.Playground2D;
import com.viridian.tddworkshop.TetrisBlock;
import com.viridian.tddworkshop.TetrisGrid;

public class TetrisEngine implements GameEngine, StoppageListener {
	
	public static final String BOOST_DOWN = "boost_down";

	public static final String MOVERIGHT_COMMAND = "moveright";

	public static final String MOVELEFT_COMMAND = "moveleft";

	/**
	 * Where are we playing.
	 */
	private Playground2D grid;
	
	/**
	 * With which element.
	 */
	private TetrisBlock currentElement;

	private ElementFactory elementFactory;

	public TetrisEngine(){
		this.grid = new TetrisGrid(10, 20);
		this.insertNewMovingElement();
	}
	
	public Playground2D getGrid() {
		return this.grid;
	}

	public TetrisBlock getCurrentElement() {
		return this.currentElement;
	}
	
	private void setCurrentElement(TetrisBlock e){
		this.currentElement = e;
	}
	
	
	public void elementHasStoppedMoving(TetrisBlock element) {
		element.stop();
		this.grid.land(element);
		
		this.insertNewMovingElement();
	}

	private void insertNewMovingElement() {
		elementFactory = new ElementFactory();
		TetrisBlock newone = elementFactory.createSquare(this.grid);
		
		newone.start();
		newone.registerListener(this);
		this.setCurrentElement(newone);
	}

	public void executeCommand(Object command) {

		if (MOVELEFT_COMMAND.equals(command)){
			this.currentElement.move("left");
		}
		
		if (MOVERIGHT_COMMAND.equals(command)){
			this.currentElement.move("right");
		}
		
		if (BOOST_DOWN.equals(command)){
			this.currentElement.move("down");
		}
	}
	
	public void onIdle() {
		this.currentElement.fall();
	}

	
	
}
