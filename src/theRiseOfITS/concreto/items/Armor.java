package theRiseOfITS.concreto.items;

import theRiseOfITS.astratto.Item;

public class Armor extends Item{
	
	private int defense;

	public Armor(String name, boolean key, int defense) {
		super(name, key);
		this.defense = defense;
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
	
	

}
