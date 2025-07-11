package theRiseOfITS.concreto.rooms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import theRiseOfITS.astratto.Chest;
import theRiseOfITS.astratto.Item;
import theRiseOfITS.astratto.Room;
import theRiseOfITS.concreto.chests.GoldChest;
import theRiseOfITS.utilities.FactoryItem;

public class TreasureRoom extends Room {

	private Chest chest;

	public TreasureRoom(String name) {
		super(name);
		generateGoldChest();
	}

	
	public TreasureRoom() {
		// TODO Auto-generated constructor stub
	}
	
	private void generateGoldChest() {
		List<Item> contenuto = new ArrayList<>();
		Random rand = new Random();
		int quanti = 2 + rand.nextInt(3);

		// 50% di probabilità tra armor e weapon
		for (int i = 0; i < quanti; i++) {
			if (rand.nextBoolean()) {
				contenuto.add(FactoryItem.generaArmaturaCasuale());
			} else {
				contenuto.add(FactoryItem.generaArmaCasuale());
			}
		}

		this.chest = new GoldChest("Forziere Dorato", contenuto);
	}

	public Chest getChest() {
        return chest;
    }

	@Override
	public String toString() {
		return "TreasureRoom [chest=" + chest + "]";
	}

}
