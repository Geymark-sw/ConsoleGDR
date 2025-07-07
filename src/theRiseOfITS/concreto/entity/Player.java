package theRiseOfITS.concreto.entity;

import java.util.ArrayList;
import java.util.List;

import theRiseOfITS.astratto.Entity;
import theRiseOfITS.astratto.Item;
import theRiseOfITS.astratto.Room;
import theRiseOfITS.concreto.items.Bomb;
import theRiseOfITS.concreto.items.Coin;
import theRiseOfITS.concreto.items.Potion;
import theRiseOfITS.interfacce.Consumable;

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
	
	public void removeConsumedItemsFromInventory() {
		//Questa lista mi serve per convertire da array a lista
		List<Item> itemCheRimangono = new ArrayList<>();

	    for (Item item : this.inventory) {
	        if (item instanceof Potion) {
	            if (!((Potion) item).isUsed()) {
	                itemCheRimangono.add(item); // tieni solo se non usata
	            }
	        } else if (item instanceof Bomb) {
	            if (!((Bomb) item).isUsed()) {
	                itemCheRimangono.add(item); // tieni solo se non usata
	            }
	        } else if (item instanceof Coin) {
	            if (!((Coin) item).isUsed()) {
	                itemCheRimangono.add(item); // tieni solo se non usata
	            }
	        } else {
	            // Altri oggetti (non consumabili) → li tieni sempre
	            itemCheRimangono.add(item);
	        }
	    }
	    //Converto di nuovo da lista ad array
	    this.inventory = itemCheRimangono.toArray(new Item[0]);
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
	
	public boolean usePotion(Potion potion) {
		
		return false;
	}
	
	public boolean useBomb(Bomb bomb) {
		//da implementare
		return false;
	}
	
	
	//function that allows the player to change rooms
	public void changeRoom() {
		//da implementare
	}
	
	
	public void examineRoom(Room room) {
		String stampa = "";
		List<Item> oggettiPerTerra = room.getItems();
		//Se i mob attaccano il giocatore immediatamente, esaminare quali mob ci sono è inutile
		List<Mob> mobNellaStanza = room.getMobs();
		stampa = stampa + "A terra trovi i seguenti oggetti: " + oggettiPerTerra + "\nE questi mob: " + mobNellaStanza +
				"\nVuoi raccogliere un oggetto?"; //da continuare
	}
	
	
}
