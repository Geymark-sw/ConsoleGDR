package theRiseOfITS.concreto.rooms;

import theRiseOfITS.astratto.Room;
import theRiseOfITS.concreto.entity.Boss;

public class BossRoom extends Room {
	private Boss boss;

	public BossRoom(String name) {
		super(name);
		this.setKey(true);
	}


	public Boss getBoss() {
		return boss;
	}

}
