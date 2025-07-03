package theRiseOfITS.astratto;

import java.util.List;

import theRiseOfITS.concreto.Merchant;
import theRiseOfITS.concreto.Mob;

public abstract class Room {
	
	private int id;
	private String name;
	private List<Item> items;
	private List<Merchant> npcs;
	private List<Mob> mobs;
	
	
	public Room(int id, String name, List<Item> items, List<Merchant> npcs, List<Mob> mobs) {
		super();
		this.id = id;
		this.name = name;
		this.items = items;
		this.npcs = npcs;
		this.mobs = mobs;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Item> getItems() {
		return items;
	}


	public void setItems(List<Item> items) {
		this.items = items;
	}


	public List<Merchant> getNpcs() {
		return npcs;
	}


	public void setNpcs(List<Merchant> npcs) {
		this.npcs = npcs;
	}


	public List<Mob> getMobs() {
		return mobs;
	}


	public void setMobs(List<Mob> mobs) {
		this.mobs = mobs;
	}


	
	
	
	
}
