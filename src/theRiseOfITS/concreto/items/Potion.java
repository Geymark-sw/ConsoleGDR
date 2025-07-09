package theRiseOfITS.concreto.items;

import theRiseOfITS.astratto.Item;
import theRiseOfITS.interfacce.Consumable;

public class Potion extends Item implements Consumable {
	
	private boolean used = false;
	private int hp;
	
	public Potion(String name, boolean key, int hp) {
		super(name, key);
		this.hp = hp;
	}

	@Override
	public void consume() {
		this.used = true;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}
	
	

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	@Override
	public String toString() {
		return "Potion [used=" + used + "]";
	}
	
	

}
