package theRiseOfITS.astratto;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import theRiseOfITS.concreto.entity.Mob;
import theRiseOfITS.concreto.rooms.Direction;


public abstract class Room {
	
	private String name;
	private boolean isKey = false; 
	private int x;
	private int y;
	
	private List<Item> items;
	private List<Entity> npcs;
	private List<Mob> mobs;
	private Map<Direction, Room> doors;
	
	public Room(String name) {
		
		this.name = name;
		this.items = new ArrayList<Item>();
		this.npcs = new ArrayList<Entity>();
		this.mobs = new ArrayList<Mob>();
		this.doors = new EnumMap<>(Direction.class);
	}
	
	public Room() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}


	/**
	 * Check if a door can be added to a room
	 * @param dir direction
	 * @param dest destination
	 * @return
	 */
	public boolean addDoor(Direction dir, Room dest) {
		// Controlla se la stanza ha gia 4 porte collegate oppure
		// se la porta è stata gia collegata ad un'altra stanza
		if (doors.size() >= 4 || doors.containsKey(dir)) {
			return false;
		}
		doors.put(dir, dest);
		return true;
	}


	public Map<Direction, Room> getDoor() {
		return this.doors;
	}
	
	public int getNumberofDoors() {
		return doors.values().size();
	}
	
	/**
	 * set the position of the room 
	 * @param x 
	 * @param y
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
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

	
	public boolean isKey() {
		return isKey;
	}


	public void setKey(boolean isKey) {
		this.isKey = isKey;
	}
	
	public Room getConnectedRoom(Direction direction) {
	    return doors.get(direction); // null se non esiste
	}
	
	public void rimuoviMobSconfitti() {
	    mobs.removeIf(Mob::isDead);
	}

	public void rimuoviItemRaccolti() {
	    items.removeIf(Item::isRaccolto); // supponendo che Item abbia isCollected()
	}


	public Map<Direction, Room> getDoors() {
		return doors;
	}

	public void setDoors(Map<Direction, Room> doors) {
		this.doors = doors;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Ti trovi nella seguente stanza: " + name 
				+ "\nPer terra ci sono i seguenti oggetti: "+ items
				+ "\n Ci sono i seguenti mob: " + mobs;
	}
	

	
}
