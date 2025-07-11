package theRiseOfITS.concreto.rooms;

import theRiseOfITS.astratto.Room;
import theRiseOfITS.concreto.entity.Merchant;
import theRiseOfITS.utilities.FactoryMerchant;

public class MerchantRoom extends Room{
	private Merchant merchant;
	
	public MerchantRoom(String name) {
		super(name);
		this.merchant = FactoryMerchant.creaMerchant();
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@Override
	public String toString() {
		return "MerchantRoom [merchant=" + merchant + "]";
	}
	
	
}
