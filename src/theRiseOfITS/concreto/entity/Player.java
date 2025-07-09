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
	private Weapon equippedWeapon = null;
	private int equippedArmorDefense = 0;
	private Armor equippedArmor = null;

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
	
	public int getEquippedWeaponDamage() {
		return equippedWeaponDamage;
	}

	public void setEquippedWeaponDamage(int equippedWeaponDamage) {
		this.equippedWeaponDamage = equippedWeaponDamage;
	}

	public int getEquippedArmorDefense() {
		return equippedArmorDefense;
	}

	public void setEquippedArmorDefense(int equippedArmorDefense) {
		this.equippedArmorDefense = equippedArmorDefense;
	}

	
	public Weapon getEquippedWeapon() {
		return equippedWeapon;
	}


	public void setEquippedWeapon(Weapon equippedWeapon) {
		this.equippedWeapon = equippedWeapon;
	}


	public Armor getEquippedArmor() {
		return equippedArmor;
	}


	public void setEquippedArmor(Armor equippedArmor) {
		this.equippedArmor = equippedArmor;
	}


		//function that checks if the player is dead or not (HP = 0)
		public boolean isDead() {
			return this.getHp() <= 0;
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
	
	public String showInventory() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Inventario:\n");

	    for (int i = 0; i < inventory.length; i++) {
	        Item item = inventory[i];
	        if (item != null) {
	            sb.append("Slot ").append(i).append(": ").append(item.getNome()).append("\n");
	        } else {
	            sb.append("Slot ").append(i).append(": [vuoto]\n");
	        }
	    }

	    return sb.toString();
	}
	
	
	public <T extends Item> List<T> getItemsByType(Class<T> tipo) {
	    List<T> risultati = new ArrayList<>();

	    for (Item item : inventory) {
	        if (tipo.isInstance(item)) {
	            risultati.add(tipo.cast(item));
	        }
	    }

	    return risultati;
	}


	//function that allows the player to increase his health by using a given potion
	public boolean usePotion(Potion potion) {
	    if (potion == null) {
	        return false;
	    }

	    Item[] inventory = this.getInventory();
	    boolean trovato = false;

	    for (Item item : inventory) {
	        if (item == potion) {
	            trovato = true;
	            break;
	        }
	    }

	    if (!trovato) {
	        return false;
	    }

	    if (this.getHp() >= 100) {
	        return false;
	    }

	    // Applica l'effetto della pozione
	    if (this.getHp() + potion.getHp() > 100) {
	        this.setHp(100);
	    } else {
	        this.setHp(this.getHp() + potion.getHp());
	    }

	    potion.consume();

	    // Rimuove la pozione usata dall'inventario (la mette a null)
	    for (int i = 0; i < inventory.length; i++) {
	        if (inventory[i] == potion) {
	            inventory[i] = null;
	            break;
	        }
	    }

	    removeConsumedOrNullItemsFromInventory();
	    return true;
	}

	
	//funciton that allows the player to use a bomb to open a secret door
	public boolean useBomb(Bomb bomb) {
		if (bomb == null) {
	        return false;
	    }
	    Item[] inventory = this.getInventory();
	    boolean trovato = false;
	    for (Item item : inventory) {
	        if (item == bomb) {
	            trovato = true;
	            break;
	        }
	    }
	    if (!trovato) {
	        return false;
	    }
	    
	    //////////METTERE L'EFFETTO DELLA BOMBA QUI//////////////////'
	    
	    bomb.consume();
		
		// Rimuove la bomba usata dall'inventario (la mette a null)
	    for (int i = 0; i < inventory.length; i++) {
	        if (inventory[i] == bomb) {
	            inventory[i] = null;
	            break;
	        }
	    }
	    removeConsumedOrNullItemsFromInventory();
	    return true;
	}
	
	public boolean buyItem(Item item) {
		if(item == null) {
			return false;
		}
		
		if(item.getPrice() <= this.getValue() ) {
			this.setValue(this.getValue() - item.getPrice());
			return true;
		}
		
		return false;
		
	}
	
	//function that allows the player to equip a weapon and increase their attack by its damage value
	public boolean equipWeapon(Weapon weapon) {
	    if (weapon == null) {
	        return false;
	    }
	    //check if the player has already equipped a weapon
	    if(this.getEquippedWeapon() != null) {
	    	this.unequipWeapon(this.getEquippedWeapon());
	    }
	    
	 //check if the weapon is in the inventory
	    Item[] inventory = this.getInventory();
	    boolean trovato = false;
	    
	    for (Item item : inventory) {
	        if (item == weapon) {
	            trovato = true;
	            break;
	        }
	    }

	    if (!trovato) {
	        return false;
	    }
	    //increase the player atk by the weapon
	    this.setEquippedWeapon(weapon);
	    weapon.equip();
	    this.setEquippedWeaponDamage(weapon.getDamage());
	    this.setAtk(this.getAtk()+this.getEquippedWeaponDamage());
	    return true;
	}
	
	public boolean unequipWeapon(Weapon weapon) {
		//controllo se ho un arma equipaggiata
		if(this.getEquippedWeapon() == null) {
			return false; //non hai armi equipaggiate
		}
		
		if(this.getEquippedWeapon().equals(weapon)) {
			//tolgo l'attacco dell'arma dall'attacco del player e disequipaggio l'arma
			this.setAtk(this.getAtk()-this.getEquippedWeaponDamage());
			this.setEquippedWeapon(null);
			weapon.setEquipped(false);//bruteforce perchè weapon non ha il metodo unequip
			this.setEquippedWeaponDamage(0);
			return true;
		}
		return false;
	}

	public boolean equipArmor(Armor armor) {
		if (armor == null) {
	        return false;
	    }
	    //check if the player has already equipped an armor
	    if(this.getEquippedArmor() != null) {
	    	this.unequipArmor(this.getEquippedArmor());
	    }
	    
	 //check if the armor is in the inventory
	    Item[] inventory = this.getInventory();
	    boolean trovato = false;
	    
	    for (Item item : inventory) {
	        if (item == armor) {
	            trovato = true;
	            break;
	        }
	    }

	    if (!trovato) {
	        return false;
	    }
	    //increase the player armor by the armor's defence
	    this.setEquippedArmor(armor);
	    armor.equip();
	    this.setEquippedArmorDefense(armor.getDefense());
	    this.setDef(this.getDef()+this.getEquippedArmorDefense());
	    return true;
	}
	
	public boolean unequipArmor(Armor armor) {
		//controllo se ho un arma equipaggiata
		if(this.getEquippedArmor() == null) {
			return false; //non hai armature equipaggiate
		}
		
		if(this.getEquippedArmor().equals(armor)) {
			//tolgo l'attacco dell'arma dall'attacco del player
			this.setDef(this.getDef()-this.getEquippedArmorDefense());
			this.setEquippedArmor(null);
			armor.setEquipped(false);//bruteforce perchè armor non ha il metodo unequip
			this.setEquippedArmorDefense(0);
			return true;
		}
		return false;
	}
	
	public boolean discardItem(Item item) {
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
