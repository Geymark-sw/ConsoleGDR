package theRiseOfITS.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import theRiseOfITS.astratto.Item;
import theRiseOfITS.concreto.items.Bomb;
import theRiseOfITS.concreto.items.Coin;
import theRiseOfITS.concreto.items.Potion;

public class FactoryItem {
	 private static final Random rand = new Random();

	    public static Item generaItemCasuale() {
	        int tipo = rand.nextInt(3); // 0 = bomba, 1 = pozione, 2 = moneta

	        return switch (tipo) {
	            case 0 -> generaBombaCasuale();
	            case 1 -> generaPozioneCasuale();
	            default -> generaMoneteCasuali();
	        };
	    }

	   

	    private static Bomb generaBombaCasuale() {
	        int dimensione = rand.nextInt(3); 

	        return switch (dimensione) {
	            case 0 -> new Bomb("Bomba Piccola", false, 10);
	            case 1 -> new Bomb("Bomba Media", false, 25);
	            case 2 -> new Bomb("Bomba Grande", false, 50);
	            default -> new Bomb("Bomba Piccola", false, 10);
	        };
	    }

	    // genera pozione

	    private static Potion generaPozioneCasuale() {
	        int dimensione = rand.nextInt(3); // 0 = piccola, 1 = media, 2 = grande

	        return switch (dimensione) {
	            case 0 -> new Potion("Pozione Piccola", false, 10);
	            case 1 -> new Potion("Pozione Media", false, 25);
	            case 2 -> new Potion("Pozione Grande", false, 50);
	            default -> new Potion("Pozione Piccola", false, 10);
	        };
	    }

	    // genera monete con valore casuale

	    private static Coin generaMoneteCasuali() {
	    	return new Coin("Monete d'Oro", false);
	    }

	    // generiamo una lista di item casuali

	    public static List<Item> generaDropCasuale(int max) {
	        List<Item> drop = new ArrayList<>();
	        int quanti = rand.nextInt(max + 1); // da 0 a max oggetti

	        for (int i = 0; i < quanti; i++) {
	            drop.add(generaItemCasuale());
	        }

	        return drop;
	    }
}
