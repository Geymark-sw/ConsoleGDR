package theRiseOfITS.concreto.entity;

import theRiseOfITS.astratto.Entity;
import theRiseOfITS.astratto.Item;
import theRiseOfITS.astratto.Room;
import theRiseOfITS.concreto.items.Coin;

public class Player extends Entity {
	
	private int value;
	private Item[] inventory;
	private int equippedWeaponDamage = 0;
	private int equippedArmorDefense = 0;
	
	public Player(String name, int hp, int atk, int def) {
		super(name, hp, atk, def);
		this.inventory = new Item[10];
		this.value = 0;
		this.setDef(def+equippedArmorDefense);
		this.setAtk(atk+equippedWeaponDamage);
	}

	
	
	public int getValue() {
		return value;
	}



	public void setValue(int value) {
		this.value = value;
	}



	public Item[] getInventory() {
		return inventory;
	}



	public void setInventory(Item[] inventory) {
		this.inventory = inventory;
	}

	//function that allows the player to collect an item and place it in his inventory
	public boolean raccogliItem(Item item) {
		if(item instanceof Coin) {
			((Coin) item).consume();
			this.value += ((Coin) item).getValue();
		} else {
			for(int i=0; i<this.inventory.length; i++) {
				if(this.inventory[i] == null) {
					this.inventory[i] = item;
					return true;
				}
					
			}
		}
		return false;
	}
	
	
	//function that allows the player to change rooms
	public void changeRoom() {
		//da implementare
	}
	
	public void examineRoom(Room room) {
		
	}
	
	//function that allows the player to do damage to a given mob, 
	//reducing his health if the attack goes through the mob's defence
	public boolean attack(Mob mob) {
		int mobHp = mob.getHp();
		int mobDef = mob.getDef();
		if (this.getAtk() > mobDef) {
			mobHp -= (this.getAtk() - mobDef);
			mob.setHp(mobHp);
			return true;
		} else {
			return false;
		}
		
	}
	
	
	
}
