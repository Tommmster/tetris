package com.viridian.tddworkshop;

public interface Block extends Deconstructible {

	boolean move(String where);
	
	boolean hasLanded();
}
