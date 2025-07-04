package theRiseOfITS.concreto.items;

import java.util.Random;

import theRiseOfITS.astratto.Item;
import theRiseOfITS.interfacce.Consumable;

public class Coin extends Item implements Consumable{
	Random random = new Random();
	
	private boolean used = false;
	private int value = random.nextInt(1, 101);
	
	public Coin(String name, boolean key) {
		super(name, key);
	}

	@Override
	public void consume() {
		this.used = true;
		
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	@Override
	public String toString() {
		return "Coin [used=" + used + "]";
	}

	public int getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}
	
	
	

}
