package theRiseOfITS.concreto.rooms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import theRiseOfITS.astratto.Room;

public class Floor {
	//il nome indica il livello del piano
	private String name;
	private Map<Point, Room> rooms = new HashMap<Point, Room>();
	private Room initialRoom;

	public Floor(String name) {

		this.name = name;
	}

	/**
	 * 
	 * @param a   Room of where you are
	 * @param dir Direction of the door
	 * @param b   Room of where you want to be
	 */
	public void join(Room a, Direction dir, Room b) {
		if (a.addDoor(dir, b)) {
			b.addDoor(dir.opposite(), a);
		}
	}

	public String getName() {
		return name;
	}
	

	public Room getInitialRoom() {
		return initialRoom;
	}

	public void generateMap() {
		Random rand = new Random();
		int totalRooms = rand.nextInt(6, 15);
		// lista delle stanze disponibili da poter collegare
		List<Room> list = new ArrayList<Room>();
		initialRoom = new InitialRoom("Stanza Iniziale");

		initialRoom.setPosition(0, 0);
		Point initialPoint = new Point(0, 0);
		rooms.put(initialPoint, initialRoom);
		list.add(initialRoom);

		// finche il numero di stanze presenti all'interno della mappa è inferiore
		// al numero di stanze totali desiderate il ciclo continua a crearne di nuove
		int i = 1;
		while (rooms.size() < totalRooms) {
			Room base = list.get(rand.nextInt(list.size()));
			List<Direction> directions = new ArrayList<>(List.of(Direction.values()));
			Collections.shuffle(directions);

			boolean joined = false;
			for (Direction direction : directions) {
				Point offset = direction.increment();
				int nx = base.getX() + offset.x;
				int ny = base.getY() + offset.y;
				Point newPoint = new Point(nx, ny);

				if (!rooms.containsKey(newPoint)) {
					Room newRoom = new RandomRoom("Stanza_" + i);
					newRoom.setPosition(nx, ny);
					join(base, direction, newRoom);
					rooms.put(newPoint, newRoom);
					list.add(newRoom);
					joined = true;
					break;
				}
			}
			// Nel caso in cui la stanza abbia gia 4 collegamenti la stanza viene eliminata
			// dalla lista
			// delle stanze possibili da usare
			if (!joined && list.size() > 0) {
				list.remove(base);
			}
			i ++;
		}
		//passo anche il livello perche il boss cambia in base al livello in cui ci troviamo
		Room bossRoom = new BossRoom("Stanza del Boss",name);
		Room treasureRoom = new TreasureRoom("Stanza del tesoro");
		Room merchantRoom = new MerchantRoom("Stanza del mercante");
		List<Room> specialRooms = new ArrayList<Room>(List.of(bossRoom, treasureRoom, merchantRoom));
		int numberofSpecialRoom = specialRooms.size();
		int count = 0;
		while (count < numberofSpecialRoom) {
			Room base = list.get(rand.nextInt(list.size()));
			List<Direction> directions = new ArrayList<>(List.of(Direction.values()));
			Collections.shuffle(directions);

			boolean joined = false;
			for (Direction direction : directions) {
				Point offset = direction.increment();
				int nx = base.getX() + offset.x;
				int ny = base.getY() + offset.y;
				Point newPoint = new Point(nx, ny);
				
				if (!rooms.containsKey(newPoint)) {
					Room newRoom = specialRooms.get(rand.nextInt(specialRooms.size()));
					newRoom.setPosition(nx, ny);
					join(base, direction, newRoom);
					rooms.put(newPoint, newRoom);
					//specialRooms.remove(newRoom);
					specialRooms.removeIf(r -> r.getName().equals(newRoom.getName()));
					joined = true;
					count++;
					break;
				}
			}
			if (!joined && list.size() > 0) {
				list.remove(base);
			}
		}
		
		
		
		
		
		

	}

	@Override
	public String toString() {
		return "Floor [name=" + name + "]";
	}

}
