package theRiseOfITS.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import theRiseOfITS.astratto.Item;
import theRiseOfITS.concreto.chests.GoldChest;
import theRiseOfITS.concreto.chests.StandardChest;

public class FactoryChest {
	private static final Random rand = new Random();

	// generiamo una chest casuale con item casuali
	public static StandardChest generaChestCasuale(String nome) {
		List<Item> oggetti = FactoryItem.generaDropCasuale(3);
		return new StandardChest("Standard Chest", oggetti);
	}
	
//generiamo una golden chest contenente armi e armature
	public static GoldChest generaGoldChest(String nome) {
		List<Item> oggetti = new ArrayList<>();

		int n = 1 + rand.nextInt(3);
		for (int i = 0; i < n; i++) {
			if (rand.nextBoolean()) {
				oggetti.add(FactoryItem.generaArmaCasuale());
			} else {
				oggetti.add(FactoryItem.generaArmaturaCasuale());
			}
		}

		return new GoldChest("Cassa Dorata", oggetti);
	}
}
