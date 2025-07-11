package theRiseOfITS.concreto.rooms;

import java.util.List;

import theRiseOfITS.astratto.Room;
import theRiseOfITS.concreto.entity.Merchant;
import theRiseOfITS.concreto.entity.Mob;
import theRiseOfITS.utilities.FactoryMerchant;

public class MerchantRoom extends Room{
	private Merchant merchant;
	
	public MerchantRoom(String name) {
		super(name);
		this.merchant = FactoryMerchant.creaMerchant();
	}
	
	public MerchantRoom() {
		// TODO Auto-generated constructor stub
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	
	public void rimuoviMobSconfitti() {
	    List<Mob> mobs = this.getMobs(); 
	    mobs.removeIf(mob -> mob.isDead());
	}

	@Override
	public String toString() {
		return "MerchantRoom [merchant=" + merchant + "]";
	}
	
	
}
