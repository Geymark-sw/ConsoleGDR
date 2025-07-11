package theRiseOfITS.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import theRiseOfITS.astratto.Item;
import theRiseOfITS.concreto.items.Armor;
import theRiseOfITS.concreto.items.Bomb;
import theRiseOfITS.concreto.items.Coin;
import theRiseOfITS.concreto.items.Potion;
import theRiseOfITS.concreto.items.Weapon;

public class FactoryItem {
	private static final Random rand = new Random();

	public FactoryItem() {
		// TODO Auto-generated constructor stub
	}
	// genera item casuale tra i 4
	public static Item generaItemCasuale() {
		int tipo = rand.nextInt(5);

		return switch (tipo) {
		case 0 -> generaBombaCasuale();
		case 1 -> generaPozioneCasuale();
		case 2 -> generaArmaCasuale();
		case 3 -> generaArmaturaCasuale();
		default -> generaMoneteCasuali();
		};
	}

	public static Armor generaArmaturaCasuale() {
		return switch (rand.nextInt(6)) {
		case 0 -> new Armor("Armatura di Cuoio", false, 10);
		case 1 -> new Armor("Corazza d'Acciaio", false, 20);
		case 2 -> new Armor("Veste Magica", false, 15);
        case 3 -> new Armor("Scudo del Drago", false, 25);
        case 4 -> new Armor("Tunica Oscura", false, 18);
        default -> new Armor("Armatura Improvvisata", false, 5);
		};
	}

	public static Weapon generaArmaCasuale() {
		return switch (rand.nextInt(6)) {
		case 0 -> new Weapon("Spada di Ferro", false, 10);
		case 1 -> new Weapon("Ascia Runica", false, 20);
		case 2 -> new Weapon("Daga Velenosa", false, 18);
        case 3 -> new Weapon("Lancia Infernale", false, 30);
        case 4 -> new Weapon("Bastone della Tempesta", false, 22);
        default -> new Weapon("Spada Sconosciuta", false, 10);
		};
	}

	// genera bomba
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
		int dimensione = rand.nextInt(3); 

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
		int quanti = rand.nextInt(max + 1); 

		for (int i = 0; i < quanti; i++) {
			drop.add(generaItemCasuale());
		}

		return drop;
	}

	public static Random getRand() {
		return rand;
	}
	
	
}
