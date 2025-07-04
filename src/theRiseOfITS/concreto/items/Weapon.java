package theRiseOfITS.concreto.items;

import theRiseOfITS.astratto.Item;

public class Weapon extends Item{
	
	private int damage;
	
	public Weapon(String name, boolean key, int damage) {
		super(name, key);
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	@Override
	public String toString() {
		return "Weapon [damage=" + damage + "]";
	}
	
	
	
}
