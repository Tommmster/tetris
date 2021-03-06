package com.viridian.tddworkshop;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.viridian.tddworkshop.testutils.TestUtils;

public class ElementTraslationTest {

	private TetrisGrid REALLY_LARGE_GRID;
	
	@Before
	public void setUp(){
		this.REALLY_LARGE_GRID = new TetrisGrid(100, 100);
	}
	
	@Ignore @Test
	public void appShouldStartWithAnEmptyScenario() throws Exception{

		Tetris tetrisGame = new Tetris();
		
		tetrisGame.start(null);
		
		synchronized (this) {
			this.wait();
		}
	}
	
	@Test
	public void elementShouldMoveDownWhileActive(){
		SquareBlock element = new SquareBlock(this.REALLY_LARGE_GRID, 2);
		element.start();
		Position [] initials = element.getCorners();
		
		element.move("down");
		
		TestUtils.assertElementHasMovedStraightDown(initials, element);
	}
	
	@Test
	public void elementShouldStopMovingWhenItReachesBottomOfTheGrid() {
		TetrisGrid grid = new TetrisGrid(10,3);
		SquareBlock element = new SquareBlock(grid, 2);
		element.start();
		Position[] initials = element.getCorners();
		
		element.move("down");

		TestUtils.assertElementHasMovedStraightDown(initials, element);
		
		final Position[] previousPositions = element.getCorners();
		
		element.move("down"); 
		TestUtils.assertElementHasStoppedMoving(previousPositions, element);
	}
	
	@Test
	public void elementShouldMoveSidewaysWhenToldTo() {
		
		TetrisGrid grid = new TetrisGrid(10,10);
		SquareBlock element = new SquareBlock(grid, 2);
		element.start();
		Position[] initials = element.getCorners();
		
		element.move("left");
		TestUtils.assertElementHasMovedLeftAndDown(initials, element);
		
		initials = element.getCorners();
		
		element.move("right");
		TestUtils.assertElementHasMovedRightAndDown(initials, element);
	}
	
	
	@Test
	public void elementShouldNotEscapeTheGridThroughTheSides(){
		
		TetrisGrid grid = new TetrisGrid(10,10);
		SquareBlock leftSideElement = this.getAnElementPressedAgainstTheSideOfThisGrid("leftSide", grid);
		
		Position[] initials = leftSideElement.getCorners();
		leftSideElement.move("left");
		TestUtils.assertElementHasStoppedMoving(initials, leftSideElement);
		
		
		SquareBlock rightSideElement = this.getAnElementPressedAgainstTheSideOfThisGrid("rightSide", grid);
		initials = rightSideElement.getCorners();
		rightSideElement.move("right");
		TestUtils.assertElementHasStoppedMoving(initials, rightSideElement);
	}
	
	@Test
	public void elementShouldBePlacedAtTheTopOfTheGridInTheMiddle(){
		TetrisGrid grid = new TetrisGrid(10,10);
		SquareBlock anElement = new SquareBlock(grid, 2);
		
		Assert.assertEquals(anElement.getStartingPoint(), new Position(grid.getWidth() /2, grid.top()));
	}
	
	@Test
	public void deconstruct(){
		TetrisGrid grid = new TetrisGrid(10,10);
		SquareBlock anElement = new SquareBlock(grid, 2);
		
		List<Position> positions = new ArrayList<Position>(4);
		
		for (Position p : anElement.deconstruct()){
			System.out.println(p);
		}
	}
	/**
	 * @param whichSide will ignore values different than "leftSide" or "rightSide". Will return null if this advice is ignored.
	 * @param whichGrid
	 * @return
	 */
	private SquareBlock getAnElementPressedAgainstTheSideOfThisGrid(String whichSide, TetrisGrid whichGrid){
		SquareBlock element = new SquareBlock(whichGrid, 2);
		if ("leftSide".equals(whichSide)){
			
			final Position leftSide = new Position(whichGrid.leftSide(),whichGrid.top());
			
			element.placeLeft(leftSide);
			return element;
		}
		
		if ("rightSide".equals(whichSide)){
			final Position rightSide = new Position(whichGrid.rightSide(),whichGrid.top());
			
			element.placeRight(rightSide);
			return element;
		}
		return null;
	}

	
}
