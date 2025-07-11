package theRiseOfITS.concreto.rooms;

import java.util.List;
import java.util.Random;

import theRiseOfITS.astratto.Chest;
import theRiseOfITS.astratto.Item;
import theRiseOfITS.astratto.Room;
import theRiseOfITS.concreto.chests.StandardChest;
import theRiseOfITS.concreto.entity.Mob;
import theRiseOfITS.utilities.FactoryItem;
import theRiseOfITS.utilities.FactoryMob;

public class RandomRoom extends Room {

	private Chest chest;

	public RandomRoom(String name) {
		super(name);
		this.setMobs(FactoryMob.getMobList());
		this.setItems(FactoryItem.generaDropCasuale(3));
		generaChest();
	}
	
	public RandomRoom() {
		// TODO Auto-generated constructor stub
	}

	private void generaChest() {
		Random rand = new Random();
		boolean haUnaChest = rand.nextBoolean(); // 50% di possibilità, come in goldchest

		if (haUnaChest) {
			List<Item> oggetti = FactoryItem.generaDropCasuale(5);
			this.chest = new StandardChest("Chest comune", oggetti);
		}
	}

	public boolean hasChest() {
		return chest != null;
	}

	public Chest getChest() {
		return chest;
	}

	public void setChest(Chest chest) {
		this.chest = chest;
	}
	
	public void rimuoviMobSconfitti() {
	    List<Mob> mobs = this.getMobs(); 
	    mobs.removeIf(Mob::isDead);
	}

	@Override
	public String toString() {
		return "RandomRoom [chest=" + chest + "]";
	}

}
