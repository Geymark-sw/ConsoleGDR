package theRiseOfITS.concreto.chests;

import java.util.List;

import theRiseOfITS.astratto.Chest;
import theRiseOfITS.astratto.Item;

public class StandardChest extends Chest{

	public StandardChest(String nome, List<Item> oggettiContenuti) {
		super(nome, oggettiContenuti);
		// TODO Auto-generated constructor stub
	}
	
	public StandardChest() {
		// TODO Auto-generated constructor stub
	}
	
	

    @Override
    public String toString() {
        return "Cassa: " + getNome() + " | Contenuto: " + getOggettiContenuti().size() + " oggetti";
    }
	
	
}
	
