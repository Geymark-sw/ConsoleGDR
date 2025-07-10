package theRiseOfITS.concreto.rooms;

import java.util.List;

import theRiseOfITS.astratto.Item;
import theRiseOfITS.astratto.Room;
import theRiseOfITS.concreto.entity.Mob;
import theRiseOfITS.utilities.FactoryItem;
import theRiseOfITS.utilities.FactoryMob;

public class RandomRoom extends Room{
	
	private Mob mob;
	private List<Item> loot;
	
	public RandomRoom(String name) {
		super(name);
		this.mob = FactoryMob.generateRandomMob();
		this.loot = FactoryItem.generaDropCasuale(3);
	}

	public Mob getMob() {
		return mob;
	}

	public void setMob(Mob mob) {
		this.mob = mob;
	}

	public List<Item> getLoot() {
		return loot;
	}

	public void setLoot(List<Item> loot) {
		this.loot = loot;
	}
	
	
	
}
