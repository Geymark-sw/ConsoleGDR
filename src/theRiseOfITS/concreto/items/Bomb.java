package theRiseOfITS.concreto.items;

import theRiseOfITS.astratto.Item;
import theRiseOfITS.interfacce.Consumable;

public class Bomb extends Item implements Consumable{
	
	private int damage;
	private boolean used = false;
	
	public Bomb(String name, boolean key, int damage) {
		super(name, key);
		this.damage = damage;
	}
	
	@Override
	public void consume() {
		this.used = true;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	@Override
	public String toString() {
		return "Bomb [damage=" + damage + ", used=" + used + "]";
	}
	
	

}
