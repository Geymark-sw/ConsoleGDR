package theRiseOfITS.utilities;

import java.util.ArrayList;
import java.util.List;

import theRiseOfITS.astratto.Item;
import theRiseOfITS.concreto.entity.Merchant;
import theRiseOfITS.concreto.items.Armor;
import theRiseOfITS.concreto.items.Bomb;
import theRiseOfITS.concreto.items.Potion;
import theRiseOfITS.concreto.items.Weapon;

public class FactoryMerchant {
	
	public static Merchant creaMerchant() {
		List<Item> inventario = new ArrayList<>();
		
		inventario.add(new Potion("Pozione piccola", false, 10));
		inventario.add(new Weapon("Spada di Ferro", false, 10));
		inventario.add(new Armor("Scudo di Legno", false, 10));
		inventario.add(new Bomb("Bomba media", false,25));
		
		Merchant mercante = new Merchant("Nicoletta", 900, 50, 50, inventario,"Sono qui per aiutarti ad abbattere questa società marcia");
		mercante.assegnaPrezziCasuali(5, 200);
		
		return mercante;
		
		
	}

}
