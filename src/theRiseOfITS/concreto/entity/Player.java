package theRiseOfITS.concreto.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

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
import theRiseOfITS.concreto.rooms.MerchantRoom;
import theRiseOfITS.concreto.rooms.TreasureRoom;
import theRiseOfITS.mechanics.CombatSystem;

public class Player extends Entity {

	private int value;
	private Item[] inventory;
	private int equippedWeaponDamage = 0;
	private Weapon equippedWeapon = null;
	private int equippedArmorDefense = 0;
	private Armor equippedArmor = null;
	private Room currentRoom;
	private Floor currentFloor;
	private int maxHp;

	public Player(String name) {
		// Imposto hp, atk, def iniziali fissi
		super(name, 99999, 99999999, 5); //valori moddati
		this.maxHp = 100;
		this.inventory = new Item[10];
		this.value = 999999; //valore moddato

		// Aggiungo i bonus da equipaggiamento
		this.setDef(this.getDef() + equippedArmorDefense);
		this.setAtk(this.getAtk() + equippedWeaponDamage);
	}

	public Player() {
		// TODO Auto-generated constructor stub
	}

	public Player(String name, int hp, int atk, int def) {
		super(name, hp, atk, def);
		this.maxHp = hp; // Initialize maxHp based on constructor input
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
	
	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	// function that checks if the player is dead or not (HP = 0)
	public boolean isDead() {
		return this.getHp() <= 0;
	}

	// function that removes from the inventory the consumed or null items
	public void removeConsumedOrNullItemsFromInventory() {
		List<Item> validItems = new ArrayList<>();
		for (Item item : this.inventory) {
			if (item != null) {
				if (item instanceof Potion && ((Potion) item).isUsed()) continue;
				if (item instanceof Bomb && ((Bomb) item).isUsed()) continue;
				validItems.add(item);
			}
		}
		Item[] newInventory = new Item[this.inventory.length];
		for (int i = 0; i < validItems.size(); i++) {
			newInventory[i] = validItems.get(i);
		}
		this.inventory = newInventory;
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
					// Messaggio spostato nel metodo chiamante per evitare duplicati
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
		System.out.println("Hai aperto la chest. Contiene i seguenti oggetti:");

		while (!chest.getOggettiContenuti().isEmpty()) {
			for (Item item : chest.getOggettiContenuti()) {
				System.out.println("- " + item.getNome());
			}
			System.out.print("Cosa vuoi raccogliere? (digita 'raccogli [nome oggetto]' o 'esci' per chiudere): ");
			String input = scanner.nextLine().trim().toLowerCase();

			if (input.equals("esci")) {
				System.out.println("Chiudi la chest, lasciando gli oggetti rimanenti.");
				return true;
			}

			if (input.startsWith("raccogli ")) {
				String itemName = input.substring(9).trim();
				Item itemToPickup = null;
				
				for (Item item : chest.getOggettiContenuti()) {
					if (item.getNome().equalsIgnoreCase(itemName)) {
						itemToPickup = item;
						break;
					}
				}

				if (itemToPickup != null) {
					if (pickupItem(itemToPickup)) {
						chest.getOggettiContenuti().remove(itemToPickup);
						System.out.println("Hai raccolto: " + itemToPickup.getNome());
					} else {
						System.out.println("Inventario pieno! Non puoi raccogliere " + itemToPickup.getNome() + ".");
						break; 
					}
				} else {
					System.out.println("Oggetto non trovato nella chest: " + itemName);
				}
			} else {
				System.out.println("Comando non valido.");
			}
		}
		
		if (chest.getOggettiContenuti().isEmpty()){
			System.out.println("Hai raccolto tutto dalla chest.");
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
	
	public boolean hasInventorySpace() {
		for (Item item : this.inventory) {
			if (item == null) {
				return true;
			}
		}
		return false;
	}
	
	/**
     * Allows the player to heal using a healing potion from their inventory.
     * The player will search for the first available Potion in their inventory.
     */
    public void heal() {
        Potion healingPotion = null;
        for (Item item : inventory) {
            if (item instanceof Potion) {
                healingPotion = (Potion) item;
                break; // Found the first potion, use it
            }
        }

        if (healingPotion != null) {
            if (this.getHp() >= this.maxHp) {
                System.out.println("Hai già la salute al massimo!");
                return;
            }

            int currentHp = this.getHp();
            int amountToHeal = healingPotion.getHp(); // Get healing amount from potion
            
            // Calculate new HP, ensuring it doesn't exceed maxHp
            int newHp = Math.min(currentHp + amountToHeal, this.maxHp);
            setHp(newHp);

            healingPotion.consume(); // Mark the potion as used
            removeConsumedOrNullItemsFromInventory(); // Clean up inventory

            System.out.println(getName() + " ha usato " + healingPotion.getNome() + " e recuperato " + (newHp - currentHp) + " HP!");
            System.out.println("HP attuali: " + getHp() + "/" + this.maxHp);
        } else {
            System.out.println("Non hai Pozioni di Cura nell'inventario!");
        }
    }


	public boolean usePotion(Potion potion) {
		// This method is now redundant if 'heal()' handles all potion usage during combat.
		// If you intend to have other types of potions or specific potion usage outside of combat,
		// you might keep this. For basic healing during combat, the new heal() method is sufficient.
		if (potion == null) {
			System.out.println("Errore, pozione non trovata.");
			return false;
		}

		if (this.getHp() >= this.maxHp) { // Use maxHp here
			System.out.println("Hai già la salute al massimo!");
			return false;
		}

		if (this.getHp() + potion.getHp() > this.maxHp) { // Use maxHp here
			this.setHp(this.maxHp);
		} else {
			this.setHp(this.getHp() + potion.getHp());
		}

		potion.consume();
		removeConsumedOrNullItemsFromInventory();
		System.out.println("Usi " + potion.getNome() + "! La tua salute ora è " + this.getHp() + "hp.");
		return true;
	}

	public boolean useBomb(Bomb bomb) {
		if (bomb == null) {
			System.out.println("Errore, bomba non trovata.");
			return false;
		}
		
		////////// METTERE L'EFFETTO DELLA BOMBA QUI //////////////////
		bomb.consume();
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

	public boolean equipWeapon(Weapon weapon) {
		if (weapon == null) {
			System.out.println("Errore, arma non trovata.");
			return false;
		}
		if (this.getEquippedWeapon() != null) {
			this.unequipWeapon(this.getEquippedWeapon());
		}
		this.setEquippedWeapon(weapon);
		weapon.equip();
		this.setEquippedWeaponDamage(weapon.getDamage());
		this.setAtk(this.getAtk() + this.getEquippedWeaponDamage());
		System.out.println("Equipaggi " + weapon.getNome() + "! Il tuo attacco aumenta a " + this.getAtk() + " atk.");
		return true;
	}

	public boolean unequipWeapon(Weapon weapon) {
		if (this.getEquippedWeapon() == null || !this.getEquippedWeapon().equals(weapon)) {
			return false;
		}
		this.setAtk(this.getAtk() - this.getEquippedWeaponDamage());
		this.getEquippedWeapon().setEquipped(false);
		this.setEquippedWeapon(null);
		this.setEquippedWeaponDamage(0);
		System.out.println("Disequipaggi " + weapon.getNome() + ", il tuo attacco diminuisce a " + this.getAtk() + " atk.");
		return true;
	}

	public boolean equipArmor(Armor armor) {
		if (armor == null) {
			System.out.println("Errore, armatura non trovata.");
			return false;
		}
		if (this.getEquippedArmor() != null) {
			this.unequipArmor(this.getEquippedArmor());
		}
		this.setEquippedArmor(armor);
		armor.equip();
		this.setEquippedArmorDefense(armor.getDefense());
		this.setDef(this.getDef() + this.getEquippedArmorDefense());
		System.out.println("Equipaggi " + armor.getNome() + "! La tua difesa aumenta a " + this.getDef() + " def.");
		return true;
	}

	public boolean unequipArmor(Armor armor) {
		if (this.getEquippedArmor() == null || !this.getEquippedArmor().equals(armor)) {
			return false;
		}
		this.setDef(this.getDef() - this.getEquippedArmorDefense());
		this.getEquippedArmor().setEquipped(false);
		this.setEquippedArmor(null);
		this.setEquippedArmorDefense(0);
		System.out.println("Disequipaggi " + armor.getNome() + ", la tua difesa scende a " + this.getDef() + " def.");
		return true;
	}

	public boolean discardItemFromInventory(Item item) {
		if (item == null) {
			System.out.println("Errore, item non trovato.");
			return false;
		}
		if (item.equals(this.equippedArmor) || item.equals(this.equippedWeapon)) {
			System.out.println("Non puoi scartare un oggetto equipaggiato!");
			return false;
		}
		if (item.isKey()) {
			System.out.println("Non puoi scartare un oggetto chiave!");
			return false;
		}
		for (int i = 0; i < inventory.length; i++) {
			if (item.equals(inventory[i])) {
				inventory[i] = null;
				removeConsumedOrNullItemsFromInventory();
				System.out.println("Hai scartato " + item.getNome());
				return true;
			}
		}
		return false;
	}
	
	public void tradeWithMerchant(Merchant merchant) {
		Scanner scanner = new Scanner(System.in);
		boolean trading = true;

		System.out.println("\n--- Benvenuto nel negozio di " + merchant.getName() + "! ---");
		merchant.speak();

		while (trading) {
			System.out.println("\nLe tue monete: " + this.getValue());
			merchant.mostraInventario(); // mostra gli oggetti del mercante
			System.out.println("\nCosa vuoi fare?");
			System.out.println(" - Compra [numero oggetto] (es. 'compra 1')");
			System.out.println(" - Esci");
			System.out.print("Comando: ");
			String input = scanner.nextLine().trim().toLowerCase();

			if (input.equals("esci")) {
				System.out.println("Arrivederci! Torna a trovarci.");
				trading = false;
			} else if (input.startsWith("compra ")) {
				try {
					int itemIndex = Integer.parseInt(input.substring(7).trim()) - 1;
					if (itemIndex >= 0 && itemIndex < merchant.getInventario().size()) {
						Item itemToBuy = merchant.getInventario().get(itemIndex);
						if (this.getValue() >= itemToBuy.getPrice()) {
							if (this.hasInventorySpace()) {
								merchant.vendi(itemIndex, this);
							} else {
								System.out.println("Il tuo inventario è pieno! Non puoi comprare questo oggetto.");
							}
						} else {
							System.out.println("Non hai abbastanza monete per acquistare " + itemToBuy.getNome() + ".");
						}
					} else {
						System.out.println("Numero oggetto non valido.");
					}
				} catch (NumberFormatException e) {
					System.out.println("Comando non valido. Usa 'compra [numero oggetto]'.");
				}
			} else {
				System.out.println("Comando non riconosciuto.");
			}
		}
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

		String availableDirections = doors.keySet().stream()
									   .map(Enum::name)
									   .collect(Collectors.joining(", "));
		System.out.println("Le uscite disponibili sono: " + availableDirections);
		System.out.print("Dove vuoi andare? (es. 'vai nord'): ");

		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine().trim().toLowerCase();
		String[] parts = input.split(" ", 2);

		if (parts.length < 2 || !parts[0].equals("vai")) {
			System.out.println("Comando non valido. Usa il formato 'vai [direzione]'.");
			return;
		}

		String directionString = parts[1];
		Direction selectedDir = null;

		try {
			selectedDir = Direction.valueOf(directionString.toUpperCase());
		} catch (IllegalArgumentException e) {
			System.out.println("Direzione non valida: " + directionString);
			return;
		}

		if (!doors.containsKey(selectedDir)) {
			System.out.println("Non c'è un'uscita in quella direzione.");
			return;
		}
		
		///// CAMBIO STANZA /////

		Room nextRoom = currentRoom.getConnectedRoom(selectedDir);
		this.setCurrentRoom(nextRoom);

		System.out.println("Ti sei spostato verso " + selectedDir + " nella stanza: " + nextRoom.getName());

		List<Entity> nemici = new ArrayList<>();
		if (nextRoom.getMobs() != null && !nextRoom.getMobs().isEmpty()) {
			nemici.addAll(nextRoom.getMobs());
		}
		// Controllo se è una boss room
		if (nextRoom instanceof BossRoom bossRoom && bossRoom.getBoss() != null) {
			nemici.add(bossRoom.getBoss());
		}
		if (!nemici.isEmpty()) {
			CombatSystem combat = new CombatSystem(this, nemici);
			combat.startCombat();
		}
		// Controllo se è una merchant room
		if(nextRoom instanceof MerchantRoom merchantRoom && merchantRoom.getMerchant() != null) {
			Merchant mercante = merchantRoom.getMerchant();
			tradeWithMerchant(merchantRoom.getMerchant());
		}
		// Controllo se è una treasure room
		if(nextRoom instanceof TreasureRoom treasureRoom && treasureRoom.getChest() != null) {
			Chest chest = treasureRoom.getChest();
			System.out.println("\nEntri in una Stanza del Tesoro! C'è una " + chest.getNome() + ".");
			openChest(chest); // Il giocatore interagisce con la chest
		}
	}

	public void examineRoom(Room room) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nStai esaminando la stanza: " + room.getName());
	
		while (true) {
			boolean itemsPresent = room.getItems() != null && !room.getItems().isEmpty();
			if (itemsPresent) {
				System.out.println("Oggetti a terra:");
				for (Item item : room.getItems()) {
					System.out.println("- " + item.getNome());
				}
				System.out.print("Cosa vuoi fare? (es. 'raccogli [nome oggetto]' o 'esci'): ");
			} else {
				System.out.println("Non ci sono oggetti da raccogliere nella stanza.");
				break;
			}
	
			String input = scanner.nextLine().trim().toLowerCase();
	
			if (input.equals("esci")) {
				break;
			}
	
			if (input.startsWith("raccogli ")) {
				String itemName = input.substring(9).trim();
				Item itemToPickup = null;
				
				for (Item item : room.getItems()) {
					if (item.getNome().equalsIgnoreCase(itemName)) {
						itemToPickup = item;
						break;
					}
				}
				
				if (itemToPickup != null) {
					if (pickupItem(itemToPickup)) {
						System.out.println("Hai raccolto: " + itemToPickup.getNome());
						room.getItems().remove(itemToPickup);
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
	}
	
	public void openInventoryMenu() {
		Scanner scanner = new Scanner(System.in);
		boolean continueMenu = true;

		while (continueMenu) {
			System.out.println("\n=== INVENTARIO ===");
			showInventory();
			System.out.println("\nComandi disponibili:");
			System.out.println("- equipaggia [nome oggetto]");
			System.out.println("- disequipaggia [arma/armatura]");
			System.out.println("- usa [nome oggetto]"); // This will primarily be for Bomb now
			System.out.println("- scarta [nome oggetto]");
			System.out.println("- mostra equipaggiamento");
			System.out.println("- curati"); // Added new command for healing
			System.out.println("- esci");
			System.out.print("Cosa vuoi fare? ");

			String input = scanner.nextLine().trim().toLowerCase();
			String[] parts = input.split(" ", 2);
			String command = parts[0];
			String argument = parts.length > 1 ? parts[1].trim() : "";

			switch (command) {
				case "equipaggia":
					handleEquipCommand(argument);
					break;
				case "disequipaggia":
					handleUnequipCommand(argument);
					break;
				case "usa":
					handleUseCommand(argument);
					break;
				case "scarta":
					handleDiscardCommand(argument);
					break;
				case "mostra":
					if ("equipaggiamento".equals(argument)) {
						showCurrentEquipment();
					} else {
						System.out.println("Comando non riconosciuto. Prova con 'mostra equipaggiamento'.");
					}
					break;
				case "curati": // New case for healing
					heal();
					break;
				case "esci":
					continueMenu = false;
					break;
				default:
					System.out.println("Comando non valido.");
					break;
			}
		}
	}
	
	public void openCombatInventoryMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== INVENTARIO (Combattimento - 1 Azione) ===");
        showInventory();
        System.out.println("\nComandi disponibili (scegli un'azione):");
        System.out.println("- equipaggia [nome oggetto]");
        System.out.println("- usa [nome oggetto]"); // Per bombe o altri consumabili non di cura
        System.out.println("- curati"); // Per le pozioni di cura
        System.out.println("- mostra equipaggiamento");
        System.out.println("- esci"); // Per non fare nulla e perdere il turno

        boolean azioneCompletata = false;
        while (!azioneCompletata) {
            System.out.print("Cosa vuoi fare? ");
            String input = scanner.nextLine().trim().toLowerCase();
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String argument = parts.length > 1 ? parts[1].trim() : "";

            switch (command) {
                case "equipaggia":
                    handleEquipCommand(argument);
                    azioneCompletata = true; // L'azione è stata tentata
                    break;
                case "usa":
                    handleUseCommand(argument); // Gestisce bombe, se ne hai altri tipi di consumabili
                    azioneCompletata = true; // L'azione è stata tentata
                    break;
                case "curati":
                    heal(); // Usa la funzione di cura esistente
                    azioneCompletata = true; // L'azione è stata tentata
                    break;
                case "mostra":
                    if ("equipaggiamento".equals(argument)) {
                        showCurrentEquipment();
                        // Mostrare l'equipaggiamento non consuma l'azione, il giocatore può scegliere un'altra azione
                        System.out.println("\nPuoi ancora scegliere un'azione dall'inventario.");
                    } else {
                        System.out.println("Comando non riconosciuto. Prova con 'mostra equipaggiamento'.");
                    }
                    break;
                case "esci":
                    System.out.println("Non hai effettuato nessuna azione dall'inventario.");
                    azioneCompletata = true; // Il giocatore ha scelto di non fare nulla
                    break;
                default:
                    System.out.println("Comando non valido. Riprova.");
                    break;
            }
        }
    }

	private Item findItemByNameInInventory(String name) {
		for (Item item : inventory) {
			if (item != null && item.getNome().equalsIgnoreCase(name)) {
				return item;
			}
		}
		return null;
	}

	private void handleEquipCommand(String itemName) {
		if (itemName.isEmpty()) {
			System.out.println("Devi specificare il nome dell'oggetto da equipaggiare.");
			return;
		}
		Item itemToEquip = findItemByNameInInventory(itemName);
		if (itemToEquip == null) {
			System.out.println("Oggetto non trovato nell'inventario: " + itemName);
			return;
		}
		if (itemToEquip instanceof Weapon) {
			equipWeapon((Weapon) itemToEquip);
		} else if (itemToEquip instanceof Armor) {
			equipArmor((Armor) itemToEquip);
		} else {
			System.out.println("Puoi equipaggiare solo armi o armature.");
		}
	}

	private void handleUseCommand(String itemName) {
		if (itemName.isEmpty()) {
			System.out.println("Devi specificare il nome dell'oggetto da usare.");
			return;
		}
		Item itemToUse = findItemByNameInInventory(itemName);
		if (itemToUse == null) {
			System.out.println("Oggetto non trovato nell'inventario: " + itemName);
			return;
		}
		if (itemToUse instanceof Bomb) {
			useBomb((Bomb) itemToUse);
		} else if (itemToUse instanceof Potion) {
			System.out.println("Per curarti, usa il comando 'curati'.");
		}
		else {
			System.out.println("Questo oggetto non può essere 'usato' in questo modo.");
		}
	}

	private void handleDiscardCommand(String itemName) {
		if (itemName.isEmpty()) {
			System.out.println("Devi specificare il nome dell'oggetto da scartare.");
			return;
		}
		Item itemToDiscard = findItemByNameInInventory(itemName);
		if (itemToDiscard == null) {
			System.out.println("Oggetto non trovato nell'inventario: " + itemName);
			return;
		}
		discardItemFromInventory(itemToDiscard);
	}

	private void handleUnequipCommand(String itemType) {
		if (itemType.equalsIgnoreCase("arma")) {
			unequipCurrentWeapon();
		} else if (itemType.equalsIgnoreCase("armatura")) {
			unequipCurrentArmor();
		} else {
			System.out.println("Puoi disequipaggiare solo 'arma' o 'armatura'.");
		}
	}

	private void showCurrentEquipment() {
		System.out.println("\n=== EQUIPAGGIAMENTO ATTUALE ===");
		if (equippedWeapon != null) {
			System.out.println("Arma: " + equippedWeapon.getNome() + " (Danno: " + equippedWeapon.getDamage() + ")");
		} else {
			System.out.println("Arma: Nessuna arma equipaggiata");
		}
		if (equippedArmor != null) {
			System.out.println("Armatura: " + equippedArmor.getNome() + " (Difesa: " + equippedArmor.getDefense() + ")");
		} else {
			System.out.println("Armatura: Nessuna armatura equipaggiata");
		}
		System.out.println("\nStatistiche totali:");
		System.out.println("HP: " + this.getHp() + "/" + this.maxHp); // Show current/max HP
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