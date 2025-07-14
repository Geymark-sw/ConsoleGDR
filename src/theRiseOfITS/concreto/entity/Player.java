package theRiseOfITS.concreto.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import theRiseOfITS.astratto.Chest;
import theRiseOfITS.astratto.Entity;
import theRiseOfITS.astratto.Item;
import theRiseOfITS.astratto.Room;
import theRiseOfITS.concreto.items.Armor;
import theRiseOfITS.concreto.items.Bomb;
import theRiseOfITS.concreto.items.Coin;
import theRiseOfITS.concreto.items.Potion;
import theRiseOfITS.concreto.items.Weapon;
import theRiseOfITS.concreto.rooms.BossRoom;
import theRiseOfITS.concreto.rooms.Direction;
import theRiseOfITS.concreto.rooms.Floor;
import theRiseOfITS.mechanics.CombatSystem;
import theRiseOfITS.utilities.utility;

public class Player extends Entity {

	private int value;
	private Item[] inventory;
	private int equippedWeaponDamage = 0;
	private Weapon equippedWeapon = null;
	private int equippedArmorDefense = 0;
	private Armor equippedArmor = null;
	private Room currentRoom;
	private Floor currentFloor;

	public Player(String name) {
		// Imposto hp, atk, def iniziali fissi
		super(name, 1000000, 1000000, 5); // esempio: 100 HP, 10 ATK, 5 DEF
		this.inventory = new Item[10];
		this.value = 0;

		// Aggiungo i bonus da equipaggiamento
		this.setDef(this.getDef() + equippedArmorDefense);
		this.setAtk(this.getAtk() + equippedWeaponDamage);
	}

	public Player() {
		// TODO Auto-generated constructor stub
	}

	public Player(String name, int hp, int atk, int def) {
		super(name, hp, atk, def);
		// TODO Auto-generated constructor stub
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

	public Floor getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(Floor currentFloor) {
		this.currentFloor = currentFloor;
	}
	

	// function that checks if the player is dead or not (HP = 0)
	public boolean isDead() {
		return this.getHp() <= 0;
	}

	// function that removes from the inventory the consumed or null items
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

	// function that allows the player to collect an item and place it in his
	// inventory
	public boolean pickupItem(Item item) {
		if (item instanceof Coin) {
			((Coin) item).consume();
			this.value += ((Coin) item).getValue();
			System.out.println("Hai raccolto " + ((Coin) item).getValue() + " monete!");
			return true;
		} else {
			for (int i = 0; i < this.inventory.length; i++) {
				if (this.inventory[i] == null) {
					this.inventory[i] = item;
					item.setRaccolto(true);
					System.out.println("Hai raccolto " + item + "!");
					return true;
				}

			}
		}
		return false;
	}

	public boolean openChest(Chest chest) {
		if (chest == null) {
			return false;
		}

		if (chest.getOggettiContenuti().isEmpty()) {
			System.out.println("Apri la chest, ma è vuota!");
			return true;
		}

		Scanner scanner = new Scanner(System.in);

		List<Item> oggetti = new ArrayList<>(chest.getOggettiContenuti());
		System.out.println("Hai aperto la chest. Contiene i seguenti oggetti:");

		for (int i = 0; i < oggetti.size(); i++) {
			Item item = oggetti.get(i);
			System.out.println((i + 1) + ". " + item.getNome());
		}

		for (int i = 0; i < oggetti.size(); i++) {
			Item item = oggetti.get(i);
			String risposta = "";

			do {
				System.out.print("Vuoi raccogliere '" + item.getNome() + "'? (s/n): ");
				risposta = scanner.nextLine().trim().toLowerCase();

				if (risposta.equals("s")) {
					boolean raccolto = pickupItem(item);
					if (raccolto) {
						chest.getOggettiContenuti().remove(item); // rimuoviamo l'oggetto dalla chest solo se raccolto
						i--; // aggiornamento per evitare salti nella lista dopo la rimozione
					} else {
						System.out.println("Inventario pieno! Non puoi raccogliere l'oggetto.");
					}
				} else if (risposta.equals("n")) {
					System.out.println("Hai deciso di lasciare '" + item.getNome() + "' nella chest.");
				} else {
					System.out.println("Input non valido. Digita 's' per sì o 'n' per no.");
				}

			} while (!risposta.equals("s") && !risposta.equals("n"));
		}

		return true;
	}

	public void showInventory() {
		StringBuilder sb = new StringBuilder();
		sb.append("Inventario:\n");

		for (int i = 1; i <= inventory.length; i++) {
			Item item = inventory[i - 1];
			if (item != null) {
				sb.append("Slot ").append(i).append(": ").append(item.getNome()).append("\n");
			} else {
				sb.append("Slot ").append(i).append(": [vuoto]\n");
			}
		}
		System.out.println(sb.toString());
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

	// function that allows the player to increase his health by using a given
	// potion
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
		System.out.println("Usi " + potion.getNome() + "!, la tua salute ora è " + this.getHp() + "hp");
		return true;
	}

	// funciton that allows the player to use a bomb to open a secret door
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

		////////// METTERE L'EFFETTO DELLA BOMBA QUI//////////////////'

		bomb.consume();

		// Rimuove la bomba usata dall'inventario (la mette a null)
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i] == bomb) {
				inventory[i] = null;
				break;
			}
		}
		removeConsumedOrNullItemsFromInventory();
		System.out.println("Usi " + bomb.getNome() + "!");
		return true;
	}

	public boolean buyItem(Item item) {
		if (item == null) {
			System.out.println("Errore, item non trovato");
			return false;
		}

		if (item.getPrice() <= this.getValue()) {
			this.setValue(this.getValue() - item.getPrice());
			System.out.println("Hai acquistato " + item.getNome() + "!");
			return true;
		}
		System.out.println("Non hai abbastanza soldi!");
		return false;

	}

	// function that allows the player to equip a weapon and increase their attack
	// by its damage value
	public boolean equipWeapon(Weapon weapon) {
		if (weapon == null) {
			System.out.println("Errore, arma non trovata");
			return false;
		}
		// check if the player has already equipped a weapon
		if (this.getEquippedWeapon() != null) {
			this.unequipWeapon(this.getEquippedWeapon());
		}

		// check if the weapon is in the inventory
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
		// increase the player atk by the weapon
		this.setEquippedWeapon(weapon);
		weapon.equip();
		this.setEquippedWeaponDamage(weapon.getDamage());
		this.setAtk(this.getAtk() + this.getEquippedWeaponDamage());
		System.out.println("Equipaggi " + weapon.getNome() + "!, il tuo attacco aumenta a " + this.getAtk() + " atk");
		return true;
	}

	public boolean unequipWeapon(Weapon weapon) {
		// controllo se ho un arma equipaggiata
		if (this.getEquippedWeapon() == null) {
			return false; // non hai armi equipaggiate
		}

		if (this.getEquippedWeapon().equals(weapon)) {
			// tolgo l'attacco dell'arma dall'attacco del player e disequipaggio l'arma
			this.setAtk(this.getAtk() - this.getEquippedWeaponDamage());
			this.setEquippedWeapon(null);
			weapon.setEquipped(false);// bruteforce perchè weapon non ha il metodo unequip
			this.setEquippedWeaponDamage(0);
			System.out.println(
					"Disequipaggi " + weapon.getNome() + ", il tuo attacco diminuisce a " + this.getAtk() + " atk");
			return true;
		}
		return false;
	}

	public boolean equipArmor(Armor armor) {
		if (armor == null) {
			System.out.println("Errore, armatura non trovata");
			return false;
		}
		// check if the player has already equipped an armor
		if (this.getEquippedArmor() != null) {
			this.unequipArmor(this.getEquippedArmor());
		}

		// check if the armor is in the inventory
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
		// increase the player armor by the armor's defence
		this.setEquippedArmor(armor);
		armor.equip();
		this.setEquippedArmorDefense(armor.getDefense());
		this.setDef(this.getDef() + this.getEquippedArmorDefense());
		System.out.println("Equipaggi " + armor.getNome() + "!, la tua difesa aumenta a " + this.getDef() + " def");
		return true;
	}

	public boolean unequipArmor(Armor armor) {
		// controllo se ho un arma equipaggiata
		if (this.getEquippedArmor() == null) {
			return false; // non hai armature equipaggiate
		}

		if (this.getEquippedArmor().equals(armor)) {
			// tolgo l'attacco dell'arma dall'attacco del player
			this.setDef(this.getDef() - this.getEquippedArmorDefense());
			this.setEquippedArmor(null);
			armor.setEquipped(false);// bruteforce perchè armor non ha il metodo unequip
			this.setEquippedArmorDefense(0);
			System.out
					.println("Disequipaggi " + armor.getNome() + ", la tua difesa scende a " + this.getDef() + " def");
			return true;
		}
		return false;
	}

	public boolean discardItemFromInventory(Item item) {
		if (item == null) {
			System.out.println("Errore, item non trovato");
			return false; // oggetto nullo
		}

		if (item.equals(this.equippedArmor) || item.equals(this.equippedWeapon)) {
			System.out.println("Non puoi scartare un oggetto equipaggiato!");
			return false; // impossibile scartare un oggetto equipaggiato
		}

		if (item.isKey()) {
			System.out.println("Non puoi scartare un oggetto chiave!");
			return false; // impossibile scartare un oggetto chiave
		}

		else {
			for (int i = 0; i < inventory.length; i++) {
				if (item.equals(inventory[i])) {
					inventory[i] = null;
					removeConsumedOrNullItemsFromInventory();
					System.out.println("Hai scartato " + item.getNome());
					return true; // oggetto rimosso con successo
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

		// Avvia combattimento se ci sono mob o boss
		 List<Entity> nemici = new ArrayList<>();
		    if (nextRoom.getMobs() != null && !nextRoom.getMobs().isEmpty()) {
		        nemici.addAll(nextRoom.getMobs());
		    }

		    if (nextRoom instanceof BossRoom bossRoom) {
		        if (bossRoom.getBoss() != null) {
		            nemici.add(bossRoom.getBoss());
		        }
		    }

		    if (!nemici.isEmpty()) {
		        CombatSystem combat = new CombatSystem(this, nemici);
		        combat.startCombat();
		    }
		}
	

	public void examineRoom(Room room) {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("\nStai esaminando la stanza: " + room.getName());

	    // Ciclo interattivo che continua finché ci sono oggetti o l'utente non esce
	    while (true) {
	        boolean itemsPresent = room.getItems() != null && !room.getItems().isEmpty();

	        if (itemsPresent) {
	            System.out.println("\nOggetti a terra:");
	            // Mostra la lista aggiornata degli oggetti
	            for (Item item : room.getItems()) {
	                System.out.println("- " + item.getNome());
	            }
	            System.out.print("Cosa vuoi fare? (es. 'raccogli [nome oggetto]' o 'esci'): ");
	        } else {
	            System.out.println("Non ci sono più oggetti da raccogliere nella stanza.");
	            break; // Esce dal ciclo se non ci sono più oggetti
	        }

	        String input = scanner.nextLine().trim().toLowerCase();

	        // Se l'utente vuole uscire dall'interazione
	        if (input.equals("esci")) {
	            System.out.println("Smetti di esaminare la stanza.");
	            break;
	        }

	        // Se l'utente vuole raccogliere un oggetto
	        if (input.startsWith("raccogli ")) {
	            String itemName = input.substring(9).trim();
	            Item itemToPickup = null;
	            
	            // Cerca l'oggetto nella stanza per nome (ignorando maiuscole/minuscole)
	            for (Item item : room.getItems()) {
	                if (item.getNome().equalsIgnoreCase(itemName)) {
	                    itemToPickup = item;
	                    break;
	                }
	            }
	            
	            if (itemToPickup != null) {
	                // Prova a raccogliere l'oggetto
	                if (pickupItem(itemToPickup)) {
	                    System.out.println("Hai raccolto: " + itemToPickup.getNome());
	                    room.getItems().remove(itemToPickup); // Rimuove l'oggetto dalla stanza
	                } else {
	                    System.out.println("Inventario pieno! Non puoi raccogliere l'oggetto.");
	                    break;
	                }
	            } else {
	                System.out.println("Oggetto non trovato nella stanza: " + itemName);
	            }
	        } else {
	            System.out.println("Comando non valido.");
	        }
	    }
	    utility.pause(2);
	}
	
	public void openInventoryMenu() {
	    Scanner scanner = new Scanner(System.in);
	    boolean continueMenu = true;
	    Integer choice = 0;
	    
	        do {
				System.out.println("\n=== INVENTARIO ===");
				showInventory();
				System.out.println("\nCosa vuoi fare?");
				System.out.println("1. Equipaggia arma");
				System.out.println("2. Equipaggia armatura");
				System.out.println("3. Usa pozione");
				System.out.println("4. Usa bomba");
				System.out.println("5. Scarta oggetto");
				System.out.println("6. Mostra equipaggiamento attuale");
				System.out.println("7. Disequipaggia arma");
				System.out.println("8. Disequipaggia armatura");
				System.out.println("9. Esci dall'inventario");
				System.out.print("Scelta: ");
				
				try {
					choice = Integer.parseInt(scanner.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Scelta non valida");
				}
				
				switch (choice) {
				case 1:
					equipWeaponMenu();
					break;
				case 2:
					equipArmorMenu();
					break;
				case 3:
					usePotionMenu();
					break;
				case 4:
					useBombMenu();
					break;
				case 5:
					discardItemMenu();
					break;
				case 6:
					showCurrentEquipment();
					break;
				case 7:
					unequipCurrentWeapon();
					break;
				case 8:
					unequipCurrentArmor();
					break;
				case 9:
					continueMenu = false;
					break;
				
				}
			} while (continueMenu || choice < 1 || choice > 9);
	    
	}

	private void equipWeaponMenu() {
	    List<Weapon> weapons = getItemsByType(Weapon.class);
	    
	    if (weapons.isEmpty()) {
	        System.out.println("Non hai armi nell'inventario!");
	        return;
	    }
	    
	    System.out.println("\n=== EQUIPAGGIA ARMA ===");
	    for (int i = 0; i < weapons.size(); i++) {
	        Weapon weapon = weapons.get(i);
	        String status = weapon.isEquipped() ? " [EQUIPAGGIATA]" : "";
	        System.out.println((i + 1) + ". " + weapon.getNome() + 
	                          " (Danno: " + weapon.getDamage() + ")" + status);
	    }
	    
	    System.out.print("Quale arma vuoi equipaggiare? (0 per annullare): ");
	    Scanner scanner = new Scanner(System.in);
	    int choice = scanner.nextInt();
	    
	    if (choice > 0 && choice <= weapons.size()) {
	        Weapon selectedWeapon = weapons.get(choice - 1);
	        equipWeapon(selectedWeapon);
	    } else if (choice != 0) {
	        System.out.println("Scelta non valida!");
	    }
	}

	private void equipArmorMenu() {
	    List<Armor> armors = getItemsByType(Armor.class);
	    
	    if (armors.isEmpty()) {
	        System.out.println("Non hai armature nell'inventario!");
	        return;
	    }
	    
	    System.out.println("\n=== EQUIPAGGIA ARMATURA ===");
	    for (int i = 0; i < armors.size(); i++) {
	        Armor armor = armors.get(i);
	        String status = armor.isEquipped() ? " [EQUIPAGGIATA]" : "";
	        System.out.println((i + 1) + ". " + armor.getNome() + 
	                          " (Difesa: " + armor.getDefense() + ")" + status);
	    }
	    
	    System.out.print("Quale armatura vuoi equipaggiare? (0 per annullare): ");
	    Scanner scanner = new Scanner(System.in);
	    int choice = scanner.nextInt();
	    
	    if (choice > 0 && choice <= armors.size()) {
	        Armor selectedArmor = armors.get(choice - 1);
	        equipArmor(selectedArmor);
	    } else if (choice != 0) {
	        System.out.println("Scelta non valida!");
	    }
	}

	private void usePotionMenu() {
	    List<Potion> potions = getItemsByType(Potion.class);
	    
	    if (potions.isEmpty()) {
	        System.out.println("Non hai pozioni nell'inventario!");
	        return;
	    }
	    
	    System.out.println("\n=== USA POZIONE ===");
	    System.out.println("Salute attuale: " + this.getHp() + "/100");
	    
	    for (int i = 0; i < potions.size(); i++) {
	        Potion potion = potions.get(i);
	        System.out.println((i + 1) + ". " + potion.getNome() + 
	                          " (Cura: " + potion.getHp() + " HP)");
	    }
	    
	    System.out.print("Quale pozione vuoi usare? (0 per annullare): ");
	    Scanner scanner = new Scanner(System.in);
	    int choice = scanner.nextInt();
	    
	    if (choice > 0 && choice <= potions.size()) {
	        Potion selectedPotion = potions.get(choice - 1);
	        usePotion(selectedPotion);
	    } else if (choice != 0) {
	        System.out.println("Scelta non valida!");
	    }
	}

	private void useBombMenu() {
	    List<Bomb> bombs = getItemsByType(Bomb.class);
	    
	    if (bombs.isEmpty()) {
	        System.out.println("Non hai bombe nell'inventario!");
	        return;
	    }
	    
	    System.out.println("\n=== USA BOMBA ===");
	    for (int i = 0; i < bombs.size(); i++) {
	        Bomb bomb = bombs.get(i);
	        System.out.println((i + 1) + ". " + bomb.getNome());
	    }
	    
	    System.out.print("Quale bomba vuoi usare? (0 per annullare): ");
	    Scanner scanner = new Scanner(System.in);
	    int choice = scanner.nextInt();
	    
	    if (choice > 0 && choice <= bombs.size()) {
	        Bomb selectedBomb = bombs.get(choice - 1);
	        useBomb(selectedBomb);
	    } else if (choice != 0) {
	        System.out.println("Scelta non valida!");
	    }
	}

	private void discardItemMenu() {
	    System.out.println("\n=== SCARTA OGGETTO ===");
	    showInventory();
	    int slot = -1;
	    Scanner scanner = new Scanner(System.in);
	    do {
	    	
	    	System.out.print("Quale slot vuoi scartare? (0 per annullare): ");
			try {
				slot = Integer.parseInt(scanner.nextLine());
				if (slot < 0 || slot > inventory.length) {
					System.out.println("Slot non valido");
				}

			} catch (Exception e) {
				System.out.println("Slot non valido");
			}
			if (slot > 0 && slot <= inventory.length) {
				Item item = inventory[slot - 1];
				if (item != null) {
					discardItemFromInventory(item);
				} else {
					System.out.println("Slot vuoto!");
				}
			} 
		} while (slot < 0 || slot > inventory.length);
	}

	private void showCurrentEquipment() {
	    System.out.println("\n=== EQUIPAGGIAMENTO ATTUALE ===");
	    
	    if (equippedWeapon != null) {
	        System.out.println("Arma: " + equippedWeapon.getNome() + 
	                          " (Danno: " + equippedWeapon.getDamage() + ")");
	    } else {
	        System.out.println("Arma: Nessuna arma equipaggiata");
	    }
	    
	    if (equippedArmor != null) {
	        System.out.println("Armatura: " + equippedArmor.getNome() + 
	                          " (Difesa: " + equippedArmor.getDefense() + ")");
	    } else {
	        System.out.println("Armatura: Nessuna armatura equipaggiata");
	    }
	    
	    System.out.println("Statistiche totali:");
	    System.out.println("HP: " + this.getHp());
	    System.out.println("ATK: " + this.getAtk());
	    System.out.println("DEF: " + this.getDef());
	    System.out.println("Monete: " + this.getValue());
	}

	private void unequipCurrentWeapon() {
	    if (equippedWeapon != null) {
	        unequipWeapon(equippedWeapon);
	    } else {
	        System.out.println("Non hai nessuna arma equipaggiata!");
	    }
	}

	private void unequipCurrentArmor() {
	    if (equippedArmor != null) {
	        unequipArmor(equippedArmor);
	    } else {
	        System.out.println("Non hai nessuna armatura equipaggiata!");
	    }
	}


}
