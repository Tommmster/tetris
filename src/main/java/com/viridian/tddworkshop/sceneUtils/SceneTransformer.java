package com.viridian.tddworkshop.sceneUtils;

import com.viridian.tddworkshop.Position;

public class SceneTransformer {

	
	private final int HORIZONTAL_TRANSFORMATION_FACTOR;
	private final int VERTICAL_TRANSFORMATION_FACTOR;

	public SceneTransformer(int worldWidth, int worldHeight, int sceneWidth, int sceneHeight){
		
		HORIZONTAL_TRANSFORMATION_FACTOR = sceneWidth  / worldWidth;
		VERTICAL_TRANSFORMATION_FACTOR = sceneHeight  / worldHeight;
	}
	
	public Position traslate(Position vertex){
		
		return new Position (vertex.getX() * HORIZONTAL_TRANSFORMATION_FACTOR,
				(20 - vertex.getY()) * VERTICAL_TRANSFORMATION_FACTOR);
	}
	
	public int[] traslate (int[] points, int offset){
		
		int [] traslated = new int[points.length];
		
		for (int i=0; i< points.length; i++){
			traslated [i] = points[i] + offset;
		}
		return traslated;
	}
	public int[] xtransform(int[] xpoints ){
		return transform(xpoints, HORIZONTAL_TRANSFORMATION_FACTOR);
	}
	
	public int[] scaleHorizontalLine(int from, int to){
		return new int[]{from * HORIZONTAL_TRANSFORMATION_FACTOR, to * HORIZONTAL_TRANSFORMATION_FACTOR };
//		return null;
	}
	
	public int[] scaleVerticalLine(int from, int to){
		return new int[]{to * VERTICAL_TRANSFORMATION_FACTOR, from * VERTICAL_TRANSFORMATION_FACTOR };
	}
	
	public int[] ytransform(int[] ypoints){
		return transform(ypoints,VERTICAL_TRANSFORMATION_FACTOR);
	}

	private int[] transform(int[] xpoints, int TRANSFORMATION_FACTOR) {
		int [] scenePoints = new int [xpoints.length];
		
		for (int i = 0 ; i < xpoints.length; i ++){
			int xCoordinate = xpoints[i];
			scenePoints[i] = xCoordinate * TRANSFORMATION_FACTOR;
		}
		return scenePoints;
	}
	
}
