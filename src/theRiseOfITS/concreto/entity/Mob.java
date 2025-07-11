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
	public Mob() {
		// TODO Auto-generated constructor stub
	}
	
	Random random = new Random();
	
	public List<Item> getDropsList() {
		return dropsList;
	}
	public void setDropsList(List<Item> dropsList) {
		this.dropsList = dropsList;
	}
	public Random getRandom() {
		return random;
	}
	public void setRandom(Random random) {
		this.random = random;
	}
	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return super.isDead();
	}
	
	public List<Item> getListDrop() {
	    int maxRange = random.nextInt(6); // da 0 a 5 oggetti
	    List<Item> drop = new ArrayList<>();

	    for (int i = 0; i < maxRange && !dropsList.isEmpty(); i++) {
	        Item randomItem = dropsList.get(random.nextInt(dropsList.size()));
	        drop.add(randomItem);
	    }

	    return drop;
	}

}
