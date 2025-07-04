package theRiseOfITS.concreto.rooms;

import java.util.List;
import java.util.Map;

import theRiseOfITS.astratto.Room;

public class Floor {
	
	private static int idStatico = 0;
	private int id;
	private String name;
	private List<Room> rooms; 
	
	
	public Floor(String name) {
		super();
		this.id = idStatico;
		idStatico++;
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
