package theRiseOfITS.concreto.entity;

import java.util.ArrayList;
import java.util.List;

import theRiseOfITS.astratto.Entity;
import theRiseOfITS.astratto.Item;
import theRiseOfITS.astratto.Room;
import theRiseOfITS.concreto.items.Armor;
import theRiseOfITS.concreto.items.Bomb;
import theRiseOfITS.concreto.items.Coin;
import theRiseOfITS.concreto.items.Potion;
import theRiseOfITS.concreto.items.Weapon;

public class Player extends Entity {
	
	private int value;
	private Item[] inventory;
	private int equippedWeaponDamage = 0;
	private int equippedArmorDefense = 0;

	public Player(String name) {
		// Imposto hp, atk, def iniziali fissi
		super(name, 100, 10, 5);  // esempio: 100 HP, 10 ATK, 5 DEF
		this.inventory = new Item[10];
		this.value = 0;

		// Aggiungo i bonus da equipaggiamento
		this.setDef(this.getDef() + equippedArmorDefense);
		this.setAtk(this.getAtk() + equippedWeaponDamage);
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
	
	//function that removes from the inventory the consumed or null items
	public void removeConsumedOrNullItemsFromInventory() {
	    // Lista temporanea per raccogliere solo gli oggetti validi
	    List<Item> oggettiValidi = new ArrayList<>();

	    for (Item item : this.inventory) {
	        if (item instanceof Potion) {
	            if (!((Potion) item).isUsed()) {
	                oggettiValidi.add(item);
	            }
	        } else if (item instanceof Bomb) {
	            if (!((Bomb) item).isUsed()) {
	                oggettiValidi.add(item);
	            }
	        } else if (item != null) {
	            oggettiValidi.add(item);
	        }
	    }
	    // Ricrea un array con la stessa dimensione di quello vecchio
	    Item[] nuovoInventario = new Item[this.inventory.length];
	    for (int i = 0; i < oggettiValidi.size() && i < nuovoInventario.length; i++) {
	        nuovoInventario[i] = oggettiValidi.get(i);
	    }

	    this.inventory = nuovoInventario;
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
	
	public boolean usePotion() {
	    Item[] inventory = this.getInventory();

	    for (int i = 0; i < inventory.length; i++) {
	        Item item = inventory[i];

	        if (item instanceof Potion) {
	            Potion potion = (Potion) item;

	            if (this.getHp() >= 100) {
	                return false; // Salute già al massimo
	            }

	            if ((this.getHp() + potion.getHp()) > 100) {
	                this.setHp(100);
	            } else {
	                this.setHp(this.getHp() + potion.getHp());
	            }
	            
	            potion.isUsed();
	            inventory[i] = null;
	            removeConsumedOrNullItemsFromInventory();
	            return true;
	        }
	    }
	    
	    return false; // Nessuna pozione trovata nell'inventario
	}

		
		
		
	
	public boolean isDead() {
		return this.getHp() <= 0;
	}
	
	public boolean useBomb(Bomb bomb) {
		//da implementare
		return false;
	}
	
	public boolean equipWeapon(Weapon weapon) {
		 for (int i = 0; i < inventory.length; i++) {
			 if(weapon instanceof Weapon) {
				 
			 }
		 }
		return false;
	}
	
	public boolean equipArmor(Armor armor) {
		//da implementare
		return false;
	}
	
	
	//function that allows the player to change rooms
	public void changeRoom() {
		//da implementare
	}
	
	
	public String examineRoom(Room room) {
		String stampa = "";
		List<Item> oggettiPerTerra = room.getItems();
		//Se i mob attaccano il giocatore immediatamente, esaminare quali mob ci sono è inutile
		List<Mob> mobNellaStanza = room.getMobs();
		stampa = stampa + "A terra trovi i seguenti oggetti: " + oggettiPerTerra + "\nE questi mob: " + mobNellaStanza;
		return stampa;
	}
	
	
}
