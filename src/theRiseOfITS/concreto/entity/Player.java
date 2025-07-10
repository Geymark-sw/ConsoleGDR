package theRiseOfITS.concreto.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import theRiseOfITS.astratto.Entity;
import theRiseOfITS.astratto.Item;
import theRiseOfITS.astratto.Room;
import theRiseOfITS.concreto.items.Armor;
import theRiseOfITS.concreto.items.Bomb;
import theRiseOfITS.concreto.items.Coin;
import theRiseOfITS.concreto.items.Potion;
import theRiseOfITS.concreto.items.Weapon;
import theRiseOfITS.concreto.rooms.Direction;

public class Player extends Entity {
	
	private int value;
	private Item[] inventory;
	private int equippedWeaponDamage = 0;
	private Weapon equippedWeapon = null;
	private int equippedArmorDefense = 0;
	private Armor equippedArmor = null;
	private Room currentRoom;

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
	
	public Room getCurrentRoom() {
		return currentRoom;
	}


	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
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
			System.out.println("Hai raccolto "+ ((Coin) item).getValue() + " monete!");
		} else {
			for(int i=0; i<this.inventory.length; i++) {
				if(this.inventory[i] == null) {
					this.inventory[i] = item;
					System.out.println("Hai raccolto "+ item +"!");
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
	    	System.out.println("Errore, pozione non trovata");
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
	    	System.out.println("Pozione non trovata nell'inventario");
	        return false;
	    }

	    if (this.getHp() >= 100) {
	    	System.out.println("Hai già la salute al massimo!");
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
	    System.out.println("Usi "+potion.getNome()+"!, la tua salute ora è "+this.getHp()+"hp");
	    return true;
	}

	
	//funciton that allows the player to use a bomb to open a secret door
	public boolean useBomb(Bomb bomb) {
		if (bomb == null) {
			System.out.println("Errore, bomba non trovata");
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
	    	System.out.println("Bomba non trovata nell'inventario");
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
	    System.out.println("Usi "+bomb.getNome()+"!");
	    return true;
	}
	
	public boolean buyItem(Item item) {
		if(item == null) {
			System.out.println("Errore, item non trovato");
			return false;
		}
		
		if(item.getPrice() <= this.getValue() ) {
			this.setValue(this.getValue() - item.getPrice());
			System.out.println("Hai acquistato "+item.getNome()+"!");
			return true;
		}
		System.out.println("Non hai abbastanza soldi!");
		return false;
		
	}
	
	//function that allows the player to equip a weapon and increase their attack by its damage value
	public boolean equipWeapon(Weapon weapon) {
	    if (weapon == null) {
	    	System.out.println("Errore, arma non trovata");
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
	    	System.out.println("Arma non trovata nell'inventario");
	        return false;
	    }
	    //increase the player atk by the weapon
	    this.setEquippedWeapon(weapon);
	    weapon.equip();
	    this.setEquippedWeaponDamage(weapon.getDamage());
	    this.setAtk(this.getAtk()+this.getEquippedWeaponDamage());
	    System.out.println("Equipaggi "+weapon.getNome()+"!, il tuo attacco aumenta a "+this.getAtk()+" atk");
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
			System.out.println("Disequipaggi "+weapon.getNome()+", il tuo attacco diminuisce a "+this.getAtk()+" atk");
			return true;
		}
		return false;
	}

	public boolean equipArmor(Armor armor) {
		if (armor == null) {
			System.out.println("Errore, armatura non trovata");
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
	    	System.out.println("Armatura non trovata nell'inventario");
	        return false;
	    }
	    //increase the player armor by the armor's defence
	    this.setEquippedArmor(armor);
	    armor.equip();
	    this.setEquippedArmorDefense(armor.getDefense());
	    this.setDef(this.getDef()+this.getEquippedArmorDefense());
	    System.out.println("Equipaggi "+armor.getNome()+"!, la tua difesa aumenta a "+this.getDef()+" def");
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
			System.out.println("Disequipaggi "+armor.getNome()+", la tua difesa scende a "+this.getDef()+" def");
			return true;
		}
		return false;
	}
	
	public boolean discardItemFromInventory(Item item) {
		if(item == null) {
			System.out.println("Errore, item non trovato");
			return false; //oggetto nullo
		}
		
		if(item.equals(this.equippedArmor) || item.equals(this.equippedWeapon)) {
			System.out.println("Non puoi scartare un oggetto equipaggiato!");
			return false; //impossibile scartare un oggetto equipaggiato	
		}
		
		if(item.isKey()) {
			System.out.println("Non puoi scartare un oggetto chiave!");
			return false; //impossibile scartare un oggetto chiave
		}
		
		else {
			for (int i = 0; i < inventory.length; i++) {
				if(item.equals(inventory[i])) {
					inventory[i] = null;
					removeConsumedOrNullItemsFromInventory();
					System.out.println("Hai scartato "+item.getNome());
					return true; //oggetto rimosso con successo
				}
			}
		}
		return false;
	}
	
	public void chooseAndChangeRoom() {
	    if (currentRoom == null) {
	        System.out.println("Errore: non sei in nessuna stanza.");
	        return;
	    }

	    Map<Direction, Room> doors = currentRoom.getDoor();
	    if (doors.isEmpty()) {
	        System.out.println("Non ci sono uscite disponibili.");
	        return;
	    }

	    System.out.println("Dove vuoi andare? Uscite disponibili:");
	    List<Direction> options = new ArrayList<>(doors.keySet());
	    for (int i = 0; i < options.size(); i++) {
	        Direction dir = options.get(i);
	        Room next = doors.get(dir);
	        System.out.println(i + 1 + ". " + dir + " → " + next.getName());
	    }

	    Scanner scanner = new Scanner(System.in);
	    int choice = -1;

	    while (choice < 1 || choice > options.size()) {
	        System.out.print("Inserisci il numero della direzione: ");
	        if (scanner.hasNextInt()) {
	            choice = scanner.nextInt();
	        } else {
	            scanner.next(); // Consuma input errato
	        }

	        if (choice < 1 || choice > options.size()) {
	            System.out.println("Scelta non valida. Riprova.");
	        }
	    }

	    Direction selectedDir = options.get(choice - 1);
	    Room nextRoom = currentRoom.getConnectedRoom(selectedDir);
	    this.setCurrentRoom(nextRoom);

	    System.out.println("Ti sei spostato verso " + selectedDir + " nella stanza: " + nextRoom.getName());
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
