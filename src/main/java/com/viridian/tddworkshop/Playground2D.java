package com.viridian.tddworkshop;

import com.viridian.tddworkshop.geometry.HeightProfile;
import com.viridian.tddworkshop.geometry.Segment;

public interface Playground2D {

	int getWidth();
	
	int getHeight();
	
	int top();
	
	int bottom();
	
	int leftSide();
	
	int rightSide();
	
	HeightProfile heightAt(Segment s);
	
	Position getStartPosition();
	
	void land(TetrisBlock t);
	
	Iterable<TetrisBlock> getLandedElements();
	
}
