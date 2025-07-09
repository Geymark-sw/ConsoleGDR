package theRiseOfITS.concreto.rooms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import theRiseOfITS.astratto.Room;

public class Floor {
	
	private String name;
	private Map<String, Room> rooms = new HashMap<String, Room>(); 
	private Room initialRoom;
	private Map<String, Point> coordinates = new HashMap<>();
	
	
	public Floor(String name) {
		
		this.name = name;
	}

	/**
	 * 
	 * @param a Room of where you are
	 * @param dir Direction of the door
	 * @param b Room of where you want to be
	 */
	public void join(Room a, Direction dir, Room b) {
		if(a.addDoor(dir, b)) {
			b.addDoor(dir.opposite(), a);
		}
	}

	public String getName() {
		return name;
	}

	public Room getInitialRoom() {
		return initialRoom;
	}

	public void generateMap(int specialRooms) {
		Random rand = new Random();
		int totalRooms = rand.nextInt(11);
		List<Room> list = new ArrayList<Room>();
		
		initialRoom = new InitialRoom("Stanza Iniziale");
		rooms.put(initialRoom.getName(), initialRoom);
		list.add(initialRoom);
		
		for (int i = 0; i < totalRooms; i++) {
			Room newRoom = new RandomRoom("Stanza_" + i);
			Room joinRoom = list.get(rand.nextInt(list.size()));
			
			List<Direction> directions = new ArrayList<>(List.of(Direction.values()));
			Collections.shuffle(directions);
			
			boolean joined = false;
			for (Direction direction : directions) {
				//nel caso in cui la stanza non ha una porta collegata in quella direzione 
				if(!joinRoom.getDoor().containsKey(directions)) {
					//colleghi una nuova stanza alla stanza attuale
					join(joinRoom, direction, newRoom);
					list.add(newRoom);
					rooms.put(newRoom.getName(), newRoom);
					joined = true;
					break;
					
				}
			}
			// nel caso non dovessi riuscire a collegare la stanza riprova
			if(!joined) {
				i--;
			}
		}
		
		int changed = 0;
		for (Room room : list) {
			
		}
		
	}
	
	@Override
	public String toString() {
		return "Floor [name=" + name + "]";
	}
	
}
