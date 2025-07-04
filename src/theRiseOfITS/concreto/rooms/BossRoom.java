package theRiseOfITS.concreto.rooms;

import java.util.List;

import theRiseOfITS.astratto.Entity;
import theRiseOfITS.astratto.Item;
import theRiseOfITS.astratto.Room;
import theRiseOfITS.concreto.entity.Boss;
import theRiseOfITS.concreto.entity.Mob;

public class BossRoom extends Room{
	private Boss boss;

	public BossRoom(String name, List<Item> items, List<Entity> npcs, List<Mob> mobs) {
		super(name, items, npcs, mobs);
		// TODO Auto-generated constructor stub
	}

	public BossRoom(String name, List<Item> items, Boss boss) {
		this.setName(name);
		this.setItems(items);
		this.setBoss(boss);
		
	}

	public Boss getBoss() {
		return boss;
	}

	public void setBoss(Boss boss) {
		this.boss = boss;
	}
	
	
}
