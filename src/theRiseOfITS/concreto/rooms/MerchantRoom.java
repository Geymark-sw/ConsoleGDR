package theRiseOfITS.concreto.rooms;

import theRiseOfITS.astratto.Room;
import theRiseOfITS.concreto.entity.Merchant;

public class MerchantRoom extends Room{
	private Merchant merchant;
	
	public MerchantRoom(String name) {
		super(name);
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
}
