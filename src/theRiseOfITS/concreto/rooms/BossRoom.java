package theRiseOfITS.concreto.rooms;

import theRiseOfITS.astratto.Room;
import theRiseOfITS.concreto.entity.Boss;
import theRiseOfITS.utilities.FactoryMob;

public class BossRoom extends Room {
	private Boss boss;
	//questa stringa livello si connette con NAME in floor per poter dire quale boss generare
	private String nomelivello;
	public BossRoom(String name,String nomelivello) {
		super(name);
		this.setKey(true);
		this.nomelivello = nomelivello;
		this.boss = FactoryMob.getBossPerLevel(nomelivello);
	}

	public Boss getBoss() {
		return boss;
	}
	
	public String getLivello() {
		return nomelivello;
	}
	
    public boolean isBossDefeated() {
        return boss.isDead();
    }


}
