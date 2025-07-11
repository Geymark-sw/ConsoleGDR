package theRiseOfITS.concreto.items;

import theRiseOfITS.astratto.Item;
import theRiseOfITS.interfacce.Equippable;

public class Armor extends Item implements Equippable{
	
	private int defense;
	private boolean equipped;

	public Armor(String name, boolean key, int defense) {
		super(name, key);
		this.defense = defense;
		this.equipped = false;
	}
	
	public Armor() {
		// TODO Auto-generated constructor stub
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	@Override
	public String toString() {
		return "Armor [defense=" + defense + "]";
	}

	public boolean isEquipped() {
		return equipped;
	}

	public void setEquipped(boolean equipped) {
		this.equipped = equipped;
	}

	//function that allows the armor to be equipped and placed in a different slot 
	//while still being in the inventory slot
	@Override
	public void equip() {
		this.equipped = true;
		
	}
	
	

}
