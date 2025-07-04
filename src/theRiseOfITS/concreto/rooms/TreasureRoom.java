package theRiseOfITS.concreto.rooms;

import java.util.List;

import theRiseOfITS.astratto.Item;
import theRiseOfITS.astratto.Room;

public class TreasureRoom extends Room{
	public TreasureRoom(String name, List<Item> items) {
		this.setName(name);
		this.setItems(items);
	}
}
