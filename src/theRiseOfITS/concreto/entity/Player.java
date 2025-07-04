package theRiseOfITS.concreto.entity;

import java.util.ArrayList;
import java.util.List;

import theRiseOfITS.astratto.Entity;
import theRiseOfITS.astratto.Item;

public class Player extends Entity {
	
	private int value;
	private List<Item> inventory;
	
	public Player(String name, int hp, int atk, int def) {
		super(name, hp, atk, def);
		this.inventory = new ArrayList<>(10);
		this.value = 0;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public List<Item> getInventory() {
		return inventory;
	}

	public void setInventory(List<Item> inventory) {
		this.inventory = inventory;
	}
	
	public void raccogliItem() {
		//da implementare (subito i
	}
	
	public void raccogliSoldi() {
		//da implementare
	}
	
	public void muoviti() {
		//da implementare
	}
	
	public void attacca() {
		//da implementare
	}
	
	
	
}
