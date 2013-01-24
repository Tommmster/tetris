package com.viridian.tddworkshop.sceneUtils;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SceneUtilsTest {
	
	/* in pixel units */
	final int SCENE_WIDTH = 100;
	final int SCENE_HEIGHT = 200;
	
	/* in world units */
	final int WORLD_WIDTH = 10;
	final int WORLD_HEIGHT = 20;
	private SceneTransformer transformer;
	
	@Before
	public void setUp(){
		transformer = new SceneTransformer(WORLD_WIDTH, WORLD_HEIGHT, SCENE_WIDTH, SCENE_HEIGHT);
	}
	@Test
	public void transformations(){
		
		int [] xpoints = {1,3,3,1}; 
		int []expected={10,30,30,10};
		
		Assert.assertTrue(Arrays.equals(expected, transformer.xtransform(xpoints)));
	}
	@Test
	public void whenTheXCordianteIsMaxInTheWorldItShouldBeMaxInTheScene(){
		int xpoints[] = {0,10,10,0};
		int []expected={0,100,100,0};
		
		Assert.assertTrue(Arrays.equals(expected, transformer.xtransform(xpoints)));
	}

	
}
