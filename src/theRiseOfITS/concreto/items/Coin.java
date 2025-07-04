package theRiseOfITS.concreto.items;

import theRiseOfITS.astratto.Item;
import theRiseOfITS.interfacce.Consumable;

public class Coin extends Item implements Consumable{
	
	private boolean used = false;
	
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
	
	public 
	
	

}
