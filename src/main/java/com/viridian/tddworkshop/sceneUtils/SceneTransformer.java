package com.viridian.tddworkshop.sceneUtils;

public class SceneTransformer {

	
	private final int HORIZONTAL_TRANSFORMATION_FACTOR;
	private final int VERTICAL_TRANSFORMATION_FACTOR;

	public SceneTransformer(int worldWidth, int worldHeight, int sceneWidth, int sceneHeight){
		
		HORIZONTAL_TRANSFORMATION_FACTOR = sceneWidth  / worldWidth;
		VERTICAL_TRANSFORMATION_FACTOR = sceneHeight  / worldHeight;
	}
	
	public int[] xtransform(int[] xpoints ){
		return transform(xpoints, HORIZONTAL_TRANSFORMATION_FACTOR);
	}
	
	public int[] scaleHorizontalLine(int from, int to){
		return new int[]{to * HORIZONTAL_TRANSFORMATION_FACTOR, from * HORIZONTAL_TRANSFORMATION_FACTOR };
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
			scenePoints[i] = xpoints[i] * TRANSFORMATION_FACTOR;
		}
		return scenePoints;
	}
	
}
