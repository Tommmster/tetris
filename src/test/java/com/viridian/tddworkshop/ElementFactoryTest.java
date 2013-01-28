package com.viridian.tddworkshop;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class ElementFactoryTest {

	
	@Ignore@Test 
	public void squareShouldBeSquaredShaped(){
		
		
		ElementFactory factory = new ElementFactory();
		TetrisGrid grid = new TetrisGrid(10, 10);
		
		SquareBlock square = (SquareBlock) factory.createSquare(grid);
		
		Assert.assertEquals(4, square.getNumberOfCorners());
		Assert.assertEquals(4, square.getNumberOfCorners());
		
		
	}

}
