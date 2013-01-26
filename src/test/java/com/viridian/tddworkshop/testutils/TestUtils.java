package com.viridian.tddworkshop.testutils;

import org.junit.Assert;
import org.junit.Ignore;

import com.viridian.tddworkshop.SquareBlock;
import com.viridian.tddworkshop.Position;

@Ignore
public class TestUtils {


	public static void assertElementHasStoppedMoving(Position[] initial, SquareBlock element) {

		for(int i=0; i< element.getNumberOfCorners(); i++){
			Assert.assertEquals(initial[i], element.getCorners()[i]);
		}
	}

	public static void assertElementHasMovedStraightDown(Position[] oldCorners, Position[] corners, int numberOfCorners){
		for (int i=0; i < numberOfCorners; i++){
			Assert.assertEquals(oldCorners[i].getX(), corners[i].getX());
			Assert.assertEquals(oldCorners[i].getY() - 1 , corners[i].getY());
		}
	}
	public static void assertElementHasMovedStraightDownInAHurry(Position[] oldCorners, SquareBlock element){
		int numberOfCorners = element.getNumberOfCorners();
		
		for (int i=0; i < numberOfCorners; i++){
			Assert.assertEquals(oldCorners[i].getX(), element.getCorners()[i].getX());
			Assert.assertEquals(oldCorners[i].getY() +2 , element.getCorners()[i].getY()); //TODO Magic number
		}
	}
	public static void assertElementHasMovedStraightDown(Position[] oldPosition, SquareBlock element){
		TestUtils.assertElementHasMovedStraightDown(oldPosition, element.getCorners(), element.getNumberOfCorners());
	}

	public static void assertElementHasMovedLeft(Position[] oldCorners, Position[] corners, int numberOfCorners){
		for (int i=0; i < numberOfCorners; i++){
			Assert.assertEquals(oldCorners[i].getX()-1, corners[i].getX());
			Assert.assertEquals(oldCorners[i].getY() , corners[i].getY());
		}
	}
	
	public static void assertElementHasMovedLeftAndDown(Position[] oldPosition, SquareBlock element){
		TestUtils.assertElementHasMovedLeft(oldPosition, element.getCorners(), element.getNumberOfCorners());
	}
	public static void assertElementHasMovedRightAndDown(Position[] oldPosition, SquareBlock element){
		TestUtils.assertElementHasMovedRight(oldPosition, element.getCorners(), element.getNumberOfCorners());
	}

	
	public static void assertElementHasMovedRight(Position[] initials,Position[] corners, int numberOfCorners) {
		for (int i=0; i < numberOfCorners; i++){
			Assert.assertEquals(initials[i].getX()+1, corners[i].getX());
			Assert.assertEquals(initials[i].getY() , corners[i].getY());
		}
	}
}
