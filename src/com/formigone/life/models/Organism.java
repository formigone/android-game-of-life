package com.formigone.life.models;

public class Organism {
	private boolean life;
	
	public Organism(boolean life) {
		this.life = life;
	}
	
	public Organism() {
		this(false);
	}
	
	public boolean isAlive() {
		return life;
	}
	
	public void setLife(boolean life) {
		this.life = life;
	}
}
