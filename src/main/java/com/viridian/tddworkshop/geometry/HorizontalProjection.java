package com.viridian.tddworkshop.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.viridian.tddworkshop.Position;

public class HorizontalProjection {
	
	List<Segment> segments;
	
	List<Position> positions;
	
	public HorizontalProjection(Position ...points) throws IllegalArgumentException {
	
		require(points.length,1);
	
		positions = Arrays.asList(points);
		
		segments = new ArrayList<Segment>();
	}
	
	public HorizontalProjection(int ...points) throws IllegalArgumentException {
		
		require(points.length, 1);
		
		segments = new ArrayList<Segment>();
		
		for (int i=0; i< points.length-1; i++){
			
			final int from = points[i];
			final int to = points[i+1];
			
			segments.add(new Segment(from, to));
		}
	}

	private void require(int len, int min) {
		if (len <=min){
			throw new IllegalArgumentException ("need at least two points");
		}
	}
	
	public void moveLeft(){
		for (Segment s: this.segments){
			s.moveLeft();
		}
		
	}
	
	public void moveRight(){
		for (Segment s: this.segments){
			s.moveRight();
		}
		
	}
	public Iterable<Segment> getShapeSegments(){
		
		if (this.segments.isEmpty()){
			
			for (int i=0; i< positions.size()-1; i++){
				
				final int from = positions.get(i).getX();
				final int to = positions.get(i+1).getX();
				
				segments.add(new Segment(from, to));
			}
		}
		return this.segments;
	}
}
