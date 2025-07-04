package theRiseOfITS.astratto;

import java.util.List;

import theRiseOfITS.concreto.entity.Mob;


public abstract class Room {
	
	private static int idStatico = 0;
	private int id;
	private String name;
	private List<Item> items;
	private List<Entity> npcs;
	private List<Mob> mobs;
	
	
	public Room(String name, List<Item> items, List<Entity> npcs, List<Mob> mobs) {
		super();
		this.id = idStatico;
		idStatico ++;
		this.name = name;
		this.items = items;
		this.npcs = npcs;
		this.mobs = mobs;
	}

	public Room() {
		
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


	public List<Entity> getNpcs() {
		return npcs;
	}


	public void setNpcs(List<Entity> npcs) {
		this.npcs = npcs;
	}


	public List<Mob> getMobs() {
		return mobs;
	}


	public void setMobs(List<Mob> mobs) {
		this.mobs = mobs;
	}

	@Override
	public String toString() {
		return "Ti trovi nella seguente stanza: " + name 
				+ "\nPer terra ci sono i seguenti oggetti: "+ items
				+ "\n Ci sono i seguenti mob: " + mobs;
	}
	
	


	
	
	
	
}
