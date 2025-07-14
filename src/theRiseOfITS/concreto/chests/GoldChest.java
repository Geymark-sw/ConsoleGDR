package theRiseOfITS.concreto.chests;

import java.util.ArrayList; // Import ArrayList
import java.util.List;
import java.util.stream.Collectors; // Import Collectors

import theRiseOfITS.astratto.Chest;
import theRiseOfITS.astratto.Item;
import theRiseOfITS.concreto.items.Armor;
import theRiseOfITS.concreto.items.Weapon;

public class GoldChest extends Chest {

	public GoldChest(String nome, List<Item> oggettiContenuti) {
        // Pass a mutable ArrayList to the superclass constructor
        super(nome, onlyArmorAndWeapon(oggettiContenuti));
    }
	
	public GoldChest() {
		// TODO Auto-generated constructor stub
	}

	private static List<Item> onlyArmorAndWeapon(List<Item> originals) {
		return originals.stream()
	            .filter(item -> item instanceof Weapon || item instanceof Armor)
	            .collect(Collectors.toCollection(ArrayList::new)); // <-- Change this line
	            // Or, for Java 16+, .toList() can sometimes return a mutable list,
	            // but .collect(Collectors.toCollection(ArrayList::new)) is safer for older versions
	            // and explicitly guarantees mutability.
	}
	
}