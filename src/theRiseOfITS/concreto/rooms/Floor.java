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
	
	public Floor() {
		// TODO Auto-generated constructor stub
	}

	public Map<Point, Room> getRooms() {
		return rooms;
	}

	public void setRooms(Map<Point, Room> rooms) {
		this.rooms = rooms;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setInitialRoom(Room initialRoom) {
		this.initialRoom = initialRoom;
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
					int index = rand.nextInt(specialRooms.size());
					Room newRoom = specialRooms.get(index);
					newRoom.setPosition(nx, ny);
					join(base, direction, newRoom);
					rooms.put(newPoint, newRoom);
					specialRooms.remove(index); // rimuove l'esatta istanza
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
	
	public void printMap() {
	    if (rooms.isEmpty()) {
	        System.out.println("La mappa è vuota.");
	        return;
	    }

	    // Trova i limiti della griglia
	    int minX = Integer.MAX_VALUE;
	    int maxX = Integer.MIN_VALUE;
	    int minY = Integer.MAX_VALUE;
	    int maxY = Integer.MIN_VALUE;

	    for (Point p : rooms.keySet()) {
	        if (p.x < minX) minX = p.x;
	        if (p.x > maxX) maxX = p.x;
	        if (p.y < minY) minY = p.y;
	        if (p.y > maxY) maxY = p.y;
	    }

	    // Stampiamo la mappa per righe (y dall'alto verso il basso)
	    for (int y = maxY; y >= minY; y--) {
	        // 1) Stampa il nome/simbolo della stanza o '.' se vuota
	        for (int x = minX; x <= maxX; x++) {
	            Point p = new Point(x, y);
	            Room room = rooms.get(p);
	            if (room != null) {
	                char c;
	                if (room instanceof InitialRoom) c = 'I';
	                else if (room instanceof BossRoom) c = 'B';
	                else if (room instanceof TreasureRoom) c = 'T';
	                else if (room instanceof MerchantRoom) c = 'M';
	                else c = 'R'; // RandomRoom o stanza generica

	                System.out.print(" " + c + " ");
	            } else {
	                System.out.print(" . ");
	            }
	        }
	        System.out.println();

	        // 2) Stampiamo le connessioni verticali (porte sopra e sotto)
	        for (int x = minX; x <= maxX; x++) {
	            Point p = new Point(x, y);
	            Room room = rooms.get(p);
	            if (room != null) {
	                Room upRoom = room.getConnectedRoom(Direction.NORD);
	                if (upRoom != null) {
	                    System.out.print(" | ");
	                } else {
	                    System.out.print("   ");
	                }
	            } else {
	                System.out.print("   ");
	            }
	        }
	        System.out.println();

	        // 3) Stampiamo le connessioni orizzontali sotto ogni stanza (porta a est e ovest)
	        // Per questo disegniamo solo se y != minY, ma per semplicità lo stampiamo per ogni riga qui sotto

	        for (int x = minX; x <= maxX; x++) {
	            Point p = new Point(x, y);
	            Room room = rooms.get(p);
	            if (room != null) {
	                Room rightRoom = room.getConnectedRoom(Direction.EST);
	                if (rightRoom != null) {
	                    System.out.print(" - ");
	                } else {
	                    System.out.print("   ");
	                }
	            } else {
	                System.out.print("   ");
	            }
	        }
	        System.out.println();
	    }
	}


}
