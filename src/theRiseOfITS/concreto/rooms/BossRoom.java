package theRiseOfITS.concreto.rooms;

import java.util.List;

import theRiseOfITS.astratto.Room;
import theRiseOfITS.concreto.entity.Boss;
import theRiseOfITS.concreto.entity.Mob;
import theRiseOfITS.utilities.FactoryMob;

public class BossRoom extends Room {
	private Boss boss;
	// questa stringa livello si connette con NAME in floor per poter dire quale
	// boss generare
	private String nomelivello;

	public BossRoom(String name, String nomelivello) {
		super(name);
		this.setKey(true);
		this.nomelivello = nomelivello;
		this.boss = FactoryMob.getBossPerLevel(nomelivello);
	}

	public BossRoom() {
		// TODO Auto-generated constructor stub
	}

	public Boss getBoss() {
		return boss;
	}

	public String getLivello() {
		return nomelivello;
	}

	public boolean isBossDefeated() {
		return boss == null || boss.isDead();
	}

	public void rimuoviBossSconfitti() {
		if (boss != null && boss.isDead()) {
			boss = null;
		}
	}

}
