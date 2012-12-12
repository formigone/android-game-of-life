package com.formigone.life;

import java.util.Random;

import com.formigone.life.models.Organism;

public class Grid {

	private Organism[] cells;
	private int width;
	private int height;
	
	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		
		cells = new Organism[width * height];
	}
	
	public void updateGrid() {
	}
	
	public String getGrid() {
		String gridStatus = "";
		Random rand = new Random();
		
		gridStatus = "THE GRID!" + rand.nextInt(108);
		
		return gridStatus;
	}
}
