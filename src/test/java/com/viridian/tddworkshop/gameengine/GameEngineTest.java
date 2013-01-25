package com.viridian.tddworkshop.gameengine;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.viridian.tddworkshop.Element;
import com.viridian.tddworkshop.Position;
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
	
		Position[] oldPosition = engine.getCurrentElement().getCorners();
		
		engine.executeCommand(TetrisEngine.MOVELEFT_COMMAND);
		
		TestUtils.assertElementHasMovedLeftAndDown(oldPosition,engine.getCurrentElement());
		
		oldPosition = engine.getCurrentElement().getCorners();
		
		engine.executeCommand(TetrisEngine.MOVERIGHT_COMMAND);
		
		TestUtils.assertElementHasMovedRightAndDown(oldPosition,engine.getCurrentElement());
		
		oldPosition = engine.getCurrentElement().getCorners();
		engine.executeCommand(TetrisEngine.BOOST_DOWN);
		
		TestUtils.assertElementHasMovedStraightDown(oldPosition, engine.getCurrentElement());
	}
	
	@Test
	public void elementShouldStopAtTheBottomOfTheGrid(){
		
		Element landingElement = engine.getCurrentElement();
		
		this.landElement(landingElement);
		
		Assert.assertEquals(engine.getGrid().bottom(), landingElement.getBottomLeftCorner().getY());
	}
	
	@Test
	public void whenAnElementLandsANewOneShouldAppear(){
		
		Element landingElement = engine.getCurrentElement();
		
		landElement(landingElement);
		
		Element movingElement = engine.getCurrentElement();
		
		Assert.assertNotEquals(landingElement, movingElement);
		Assert.assertFalse(movingElement.hasLanded());
	}
	
	@Test
	public void whenAnObjectLandsOnTheGridThenTheBottomShouldBeRaised(){
		
		Element landingElement = engine.getCurrentElement();
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
		
		
		Element landingElement = engine.getCurrentElement();
		landElement(landingElement);
		expectedGridHeight += landingElement.getTopRightCorner().getY();
		
		Element newElement = engine.getCurrentElement();
		landElement(newElement);
		expectedGridHeight += newElement.getTopRightCorner().getY();
		

		Assert.assertEquals(newElement.getHorizontalProjection().getShapeSegments(), landingElement.getHorizontalProjection().getShapeSegments());
		
		TetrisGrid grid = this.engine.getGrid();
		
		for (Segment segment: newElement.getHorizontalProjection().getShapeSegments()){
			
			HeightProfile heightAt = grid.heightAt(segment);
			
			for (int h : heightAt){
				Assert.assertEquals(expectedGridHeight, h);
			}
		}
	}
	
	private void landElement(Element landingElement) {
		do {
			engine.onIdle();
		}while (!landingElement.hasLanded());
	}
}
