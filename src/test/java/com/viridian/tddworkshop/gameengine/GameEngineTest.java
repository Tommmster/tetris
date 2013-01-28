package com.viridian.tddworkshop.gameengine;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.viridian.tddworkshop.Position;
import com.viridian.tddworkshop.SquareBlock;
import com.viridian.tddworkshop.TetrisGrid;
import com.viridian.tddworkshop.geometry.HeightProfile;
import com.viridian.tddworkshop.geometry.Segment;
import com.viridian.tddworkshop.testutils.TestUtils;

public class GameEngineTest {

	
	private TetrisEngine engine;

	@Before
	public void setUp(){
		engine = new TetrisEngine();
	}
	
	@Test
	public void whenTheGameStartsThereShouldBeAGridAndAnElement(){
		
		Assert.assertNotNull(engine.getGrid());
		Assert.assertNotNull(engine.getCurrentElement());
	}
	
	@Test
	public void whenReceivingAMoveCommandTheElementShouldMove(){
	
		SquareBlock b  = (SquareBlock)engine.getCurrentElement();
		Position[] oldPosition = b.getCorners();
		
		engine.executeCommand(TetrisEngine.MOVELEFT_COMMAND);
		
		TestUtils.assertElementHasMovedLeftAndDown(oldPosition,b);
		
		oldPosition = b.getCorners();
		
		engine.executeCommand(TetrisEngine.MOVERIGHT_COMMAND);
		
		TestUtils.assertElementHasMovedRightAndDown(oldPosition,b);
		
		oldPosition = b.getCorners();
		engine.executeCommand(TetrisEngine.BOOST_DOWN);
		
		TestUtils.assertElementHasMovedStraightDown(oldPosition, b);
	}
	
	@Test
	public void elementShouldStopAtTheBottomOfTheGrid(){
		
		SquareBlock landingElement = (SquareBlock)engine.getCurrentElement();
		
		this.landElement(landingElement);
		
		Assert.assertEquals(engine.getGrid().bottom(), landingElement.getBottomLeftCorner().getY());
	}
	
	@Test
	public void whenAnElementLandsANewOneShouldAppear(){
		
		SquareBlock landingElement = (SquareBlock)engine.getCurrentElement();
		
		landElement(landingElement);
		
		SquareBlock movingElement =(SquareBlock) engine.getCurrentElement();
		
		Assert.assertNotEquals(landingElement, movingElement);
		Assert.assertFalse(movingElement.hasLanded());
	}
	
	@Test
	public void whenAnObjectLandsOnTheGridThenTheBottomShouldBeRaised(){
		
		SquareBlock landingElement =(SquareBlock) engine.getCurrentElement();
		final int initialBottom = engine.getGrid().bottom();
		Iterable<Segment> landingAt = landingElement.getHorizontalProjection().getShapeSegments();
		
		landElement(landingElement);
		
		HeightProfile heights = engine.getGrid().heightAt(landingAt.iterator().next());
		
		for (Integer h : heights){
			Assert.assertTrue(h > initialBottom);
		}
	}
	
	@Test
	public void elementsShouldBeAbleToLandOnTopOfEachOther(){
		int expectedGridHeight = 0;
				
		SquareBlock landingElement = (SquareBlock)engine.getCurrentElement();
		landElement(landingElement);
		
		SquareBlock newElement = (SquareBlock)engine.getCurrentElement();
		landElement(newElement);
		expectedGridHeight = newElement.getTopRightCorner().getY();
		

		Assert.assertEquals(newElement.getHorizontalProjection().getShapeSegments(), landingElement.getHorizontalProjection().getShapeSegments());
		
		TetrisGrid grid = (TetrisGrid)this.engine.getGrid();
		
		for (Segment segment: newElement.getHorizontalProjection().getShapeSegments()){
			
			HeightProfile heightAt = grid.heightAt(segment);
			
			for (int h : heightAt){
				Assert.assertEquals(expectedGridHeight, h);
			}
		}
	}
	
	@Test
	public void elementsShouldNotFallOnTopOfEachOtherIfTheyAreInDifferentX(){
		SquareBlock leftSideElement = (SquareBlock)this.engine.getCurrentElement();
		
		do{}while(leftSideElement.move("left"));
		landElement(leftSideElement);
		
		SquareBlock centerElement = (SquareBlock)this.engine.getCurrentElement();
		landElement(centerElement);
		
		
		for (Segment se : leftSideElement.getHorizontalProjection().getShapeSegments()){
				
			HeightProfile hp = this.engine.getGrid().heightAt(se);
			
			Assert.assertEquals(0, hp.from());
			Assert.assertEquals(2, hp.to());
			
			HeightProfile expected = new HeightProfile(0 ,2, 2);
			Assert.assertEquals(expected, hp);
			
		}
		
		for (Segment se : centerElement.getHorizontalProjection().getShapeSegments()){
			HeightProfile hp = this.engine.getGrid().heightAt(se);
			
			Assert.assertEquals(3, hp.from());
			Assert.assertEquals(5, hp.to());

			HeightProfile expected = new HeightProfile(3 ,5, 2);
			Assert.assertEquals(expected, hp);
		}
	}
	
	
	
	public void whenARowIsFilledThenTheRowShouldBeCleared(){
		
	}
	
	private void landElement(SquareBlock landingElement) {
		do {
			engine.onIdle();
		}while (!landingElement.hasLanded());
	}
}
