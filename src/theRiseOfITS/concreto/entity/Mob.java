package theRiseOfITS.concreto.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import theRiseOfITS.astratto.Entity;
import theRiseOfITS.astratto.Item;

public class Mob extends Entity{

	private List<Item> dropsList = new ArrayList<Item>();
	
	public Mob(String name, int hp, int atk, int def, List<Item> dropsList) {
		super(name, hp, atk, def);
		this.dropsList = dropsList;
	}
	
	Random random = new Random();
	
	public List<Item> getListDrop() {
		int maxRange = random.nextInt(6);
		int i = 0;
		List<Item> drop = new ArrayList<Item>(); 
		while (i < maxRange) {
			Item randomItem = this.getListDrop().get(random.nextInt(this.getListDrop().size()));
			drop.add(randomItem);
		}
		return drop;
		
	}

}
