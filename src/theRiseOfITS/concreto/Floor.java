package theRiseOfITS.concreto;

import java.util.List;
import java.util.Map;

import theRiseOfITS.astratto.Room;

public class Floor {
	
	private int id;
	private String name;
	private List<Room> rooms; 
	
	
	public Floor(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	@Override
	public String toString() {
		return "Floor [id=" + id + ", name=" + name + "]";
	}
	
	
	
	

}
