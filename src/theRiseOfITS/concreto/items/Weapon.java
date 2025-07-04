package theRiseOfITS.concreto.items;

import theRiseOfITS.astratto.Item;
import theRiseOfITS.interfacce.Equippable;

public class Weapon extends Item implements Equippable{
	
	private int damage;
	private boolean equipped;	
	
	public Weapon(String name, boolean key, int damage) {
		super(name, key);
		this.damage = damage;
		this.equipped = false;
		
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

	
	public boolean isEquipped() {
		return equipped;
	}

	public void setEquipped(boolean equipped) {
		this.equipped = equipped;
	}

	//function that allows the weapon to be equipped and placed in a different slot 
	//while still being in the inventory slot
	@Override
	public void equip() {
		this.equipped = true;
	}
	
	
	
}
