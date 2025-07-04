package theRiseOfITS.concreto.rooms;

import java.util.List;

import theRiseOfITS.astratto.Item;
import theRiseOfITS.astratto.Room;
import theRiseOfITS.concreto.entity.Mob;

public class RandomRoom extends Room{
	
	
	public RandomRoom(String name, List<Item> items, List<Mob> mobs, List<Item> chest) {
		this.setName(name);
		this.setItems(items);
		this.setMobs(mobs);
		this.setItems(items);
	}
	
	
}
