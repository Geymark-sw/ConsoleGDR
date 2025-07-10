package theRiseOfITS.concreto.rooms;

import java.util.List;

import theRiseOfITS.astratto.Item;
import theRiseOfITS.astratto.Room;
import theRiseOfITS.concreto.entity.Mob;
import theRiseOfITS.utilities.FactoryItem;
import theRiseOfITS.utilities.FactoryMob;

public class RandomRoom extends Room{

		
	public RandomRoom(String name) {
		super(name);
		this.setMobs(FactoryMob.getMobList());
		this.setItems(FactoryItem.generaDropCasuale(3));
	}
	
}
