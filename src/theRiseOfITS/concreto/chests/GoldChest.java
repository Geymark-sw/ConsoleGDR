package theRiseOfITS.concreto.chests;

import java.util.List;

import theRiseOfITS.astratto.Chest;
import theRiseOfITS.astratto.Item;
import theRiseOfITS.concreto.items.Armor;
import theRiseOfITS.concreto.items.Weapon;

public class GoldChest extends Chest {

	public GoldChest(String nome, List<Item> oggettiContenuti) {
        super(nome, onlyArmorAndWeapon(oggettiContenuti));
    }

	private static List<Item> onlyArmorAndWeapon(List<Item> originals) {
		return originals.stream()
	            .filter(item -> item instanceof Weapon || item instanceof Armor)
	            .toList();
	}
	
}
