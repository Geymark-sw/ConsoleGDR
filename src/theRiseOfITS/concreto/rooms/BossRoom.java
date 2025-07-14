package theRiseOfITS.concreto.rooms;


import theRiseOfITS.astratto.Room;
import theRiseOfITS.concreto.entity.Boss;
import theRiseOfITS.utilities.FactoryMob;

public class BossRoom extends Room {
	private Boss boss;
	// questa stringa piano si connette con NAME in floor per poter dire quale
	// boss generare
	private String nomePiano;

	public BossRoom(String name, String nomePiano) {
		super(name);
		this.setKey(true);
		this.nomePiano = nomePiano;
		this.boss = FactoryMob.getBossPerLevel(nomePiano);
	}

	public BossRoom() {
		// TODO Auto-generated constructor stub	
	}

	public Boss getBoss() {
		return boss;
	}

	public String getNomelivello() {
		return nomePiano;
	}

	public void setNomelivello(String nomePiano) {
		this.nomePiano = nomePiano;
	}

	public void setBoss(Boss boss) {
		this.boss = boss;
	}

	public String getLivello() {
		return nomePiano;
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
