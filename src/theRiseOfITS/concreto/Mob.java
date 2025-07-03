package theRiseOfITS.concreto;

import theRiseOfITS.astratto.Entity;

public abstract class Mob extends Entity{

	public Mob(int id, String name, int hp, int atk, int def) {
		super(id, name, hp, atk, def);
	}
	
	

}
