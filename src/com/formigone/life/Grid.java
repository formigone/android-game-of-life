package com.formigone.life;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.formigone.life.models.Organism;

public class Grid {

	private List<Organism> cells;
	private int width;
	private int height;
	
	public Grid(int width, int height, int cellWidth, int cellHeight) {
		this.width = width;
		this.height = height;
		
		cells = new ArrayList<Organism>(width * height);
		for (int i = 0; i < width * height; i++)
			cells.add(organismFactory(i, cellWidth, cellHeight));

		seedGrid();
	}
	
	private Organism organismFactory(int i, int width, int height) {
		return new Organism(false, i % width, i / height, width, height);
	}
	
	private int getNeighborCount(int i) {
		int total = 0;
		
		return total;
	}
	
	public void updateGrid() {
		List nextGen = new ArrayList();
		int cellWidth = cells.get(0).getWidth();
		int cellHeight = cells.get(0).getHeight();
		int neighbors = 0;
		
		for (int i = 0; i < width * height; i++) {
			neighbors = getNeighborCount(i);
			
			// Any live cell with fewer than two live neighbors dies
			if (neighbors < 2)
				nextGen.add(organismFactory(i, cellWidth, cellHeight));
			
			// Any live cell with two or three live neighbors lives
			
			// Any live cell with more than three live neighbors dies
			
			// Any dead cell with exactly three live neighbors becomes alive
			
			// Reset grid
			cells = nextGen;
		}
	}
	
	private Organism getCell(int x, int y) {
		return cells.get(width * y + x);
	}
	
	private Organism getCell(int i) {
		return getCell(getXCoord(i), getYCoord(i));
	}

	private int getXCoord(int i) {
		return i % width;
	}
	
	private int getYCoord(int i) {
		return i / height;
	}
	
	private void seedGrid() {
		Random rand = new Random();
		Organism cell;

		for (int i = 0; i < width * height; i++) {
			cell = getCell(i);
			cell.setLife(rand.nextBoolean());
//			cell.setLife(i % 2 == 0 ? true : false);
		}
	}

	public String getGrid() {
		String gridStatus = "";
		Organism cell;

		for (int i = 0; i < width * height; i++) {
			cell = getCell(i);
			gridStatus += (cell.isAlive() ? "[#]" : "[ ]") + " ";

			if ((i + 1) % width == 0)
				gridStatus += "\n";
		}

		return gridStatus;
	}
}
