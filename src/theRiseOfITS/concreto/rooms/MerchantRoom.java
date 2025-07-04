package theRiseOfITS.concreto.rooms;

import java.util.List;

import theRiseOfITS.astratto.Entity;
import theRiseOfITS.astratto.Item;
import theRiseOfITS.astratto.Room;
import theRiseOfITS.concreto.entity.Merchant;
import theRiseOfITS.concreto.entity.Mob;

public class MerchantRoom extends Room{
	private Merchant merchant;
	
	public MerchantRoom(String name, List<Item> items, List<Entity> npcs, List<Mob> mobs) {
		super(name, items, npcs, mobs);
		// TODO Auto-generated constructor stub
	}
	
	public MerchantRoom(String name, List<Item> items, Merchant merchant) {
		this.setName(name);
		this.setItems(items);
		this.merchant = merchant;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
}
