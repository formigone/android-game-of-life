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

		seedGrid(true);
	}
	
	private Organism organismFactory(int i, int width, int height) {
		return organismFactory(i, width, height, false);
	}
	
	private Organism organismFactory(int i, int width, int height, boolean life) {
		return new Organism(life, i % this.width, i / this.height, width, height);
	}
	
	public int getNeighborCount(int i) {
		int total = 0;
		Organism org = getCell(i);
		int x = org.getX();
		int y = org.getY();

		// Top left
		if (getCell(x - 1, y - 1) != null)
			total += (getCell(x - 1, y - 1).isAlive() ? 1 : 0);

		// Top center
		if (getCell(x, y - 1) != null)
			total += (getCell(x, y - 1).isAlive() ? 1 : 0);
		
		// Top right
		if (getCell(x + 1, y - 1) != null)
			total += (getCell(x + 1, y - 1).isAlive() ? 1 : 0);
		
		// Left
		if (getCell(x - 1, y) != null)
			total += (getCell(x - 1, y).isAlive() ? 1 : 0);
		
		// Right
		if (getCell(x + 1, y) != null)
			total += (getCell(x + 1, y).isAlive() ? 1 : 0);
		
		// Bottom left
		if (getCell(x - 1, y + 1) != null)
			total += (getCell(x - 1, y + 1).isAlive() ? 1 : 0);
		
		// Bottom center
		if (getCell(x, y + 1) != null)
			total += (getCell(x, y + 1).isAlive() ? 1 : 0);
		
		// Bottom right
		if (getCell(x + 1, y + 1) != null)
			total += (getCell(x + 1, y + 1).isAlive() ? 1 : 0);
		
		return total;
	}
	
	public void nextGeneration() {
		List<Organism> nextGen = new ArrayList<Organism>(width * height);
		int cellWidth = cells.get(0).getWidth();
		int cellHeight = cells.get(0).getHeight();
		int neighbors;
		
		for (int i = 0; i < width * height; i++) {
			neighbors = 0;
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
		int index = width * y + x;

		if (index < 0 || index > width * height || x < 0 || y < 0 || x > width - 1 || y > height - 1)
			return null;

		return cells.get(index);
	}
	
	public Organism getCell(int i) {
		return getCell(getXCoord(i), getYCoord(i));
	}

	private int getXCoord(int i) {
		return i % width;
	}
	
	private int getYCoord(int i) {
		return i / height;
	}

	public void seedGrid(boolean randomize) {
		Random rand = new Random();
		Organism cell;

		for (int i = 0; i < width * height; i++) {
			cell = getCell(i);
			
			if (randomize)
				cell.setLife(rand.nextBoolean());
			// Only make even cells alive (index 0, 2, 4, 6, ...)
			else
				cell.setLife(i % 2 == 0 ? true : false);
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
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
