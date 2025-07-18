package theRiseOfITS.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import theRiseOfITS.astratto.Item;
import theRiseOfITS.concreto.entity.Boss;
import theRiseOfITS.concreto.entity.Mob;
import theRiseOfITS.concreto.items.Armor;
import theRiseOfITS.concreto.items.Bomb;
import theRiseOfITS.concreto.items.Coin;
import theRiseOfITS.concreto.items.Potion;
import theRiseOfITS.concreto.items.Weapon;

public class FactoryMob {

	private static Random random = new Random();

	// generiamo una lista di mob che vengono creati dalla prossima funzione
	public static List<Mob> getMobList() {
		List<Mob> generateMobList = new ArrayList<Mob>();
		int randomMob = random.nextInt(4);

		for (int i = 0; i < randomMob; i++) {
			generateMobList.add(generateRandomMob());

		}

		return generateMobList;
	}

	
	public FactoryMob() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static Random getRandom() {
		return random;
	}


	public static void setRandom(Random random) {
		FactoryMob.random = random;
	}


	// generiamo un numero randomico, in base al numero entriamo nel case che ci
	// darà il rispettivo mob
	public static Mob generateRandomMob() {
		int specimen = random.nextInt(5);

		switch (specimen) {
		case 0:
			return createSegrataria();
		case 1:
			return createCyberStudent();
		case 2:
			return createStudenteArmellini();
		case 3:
			return createSergioForm();
		default:
			return createStudenteArmellini();

		}
	}

	// in base alla stringa entriamo nel case che ci
	// darà il rispettivo Boss
	public static Boss getBossPerLevel(String nomeLivello) {
		switch (nomeLivello) {
		case "Piano terra":
			return creaClaudia();
		case "Palazzo Accademia Informatica":
			return creaMariella();
		case "SMI":
			return creaDimitri();
		case "Altopiano ITS":
			return creaMara();
		case "﷼₽₾₷₴௹₯€₫":
			return creaMoniTancini();

		}
		return null;

	}

	/*
	 * Creazione Boss, vengono creati Mob in base al case precedente
	 */
	private static Boss creaMoniTancini() {
		int hp = 500;
		int atk = 30;
		int def = 20;
		List<Item> drop = List.of(new Bomb("Bomba grande", false, 50), new Potion("Pozione grande", false, 50),
				new Coin("Crediti formativi", false), new Weapon("Diploma ITS", true, 99999999),
				new Armor("Occhiali del Moni", true, 99999999));

		return new Boss("Moni Tancini, il Burattinaio delle Ombre", hp, atk, def, drop, "¤₭₰₠₹₩₿₯₫₮₲₸₾₻₶฿৻؋$¢₤₧₥₩");
	}

	private static Boss creaMara() {
		int hp = 400;
		int atk = 30;
		int def = 30;
		List<Item> drop = List.of(new Bomb("Bomba grande", false, 50), new Potion("Pozione grande", false, 50),
				new Coin("Crediti formativi", false), new Weapon("Spada Rivelatrice", false, 60),
				new Armor("Occhiali di Mara", false, 60));

		return new Boss("Mara Pinto, colei che fa tutto ma non fa nulla", hp, atk, def, drop,
				"Chiamate la SEGRETERIAAAA!!!!");
	}

	private static Boss creaDimitri() {
		int hp = 300;
		int atk = 25;
		int def = 20;
		List<Item> drop = List.of(new Bomb("Bomba grande", false, 50), new Potion("Pozione grande", false, 50),
				new Coin("Crediti formativi", false), new Weapon("Pitone d'ottone", true, 30),
				new Armor("Polo di Dimitri", true, 30));

		return new Boss("Dimitri, eeeeh volevi imparare Flask?", hp, atk, def, drop,
				"Ė cosi semplice che ci riuscirebbe anche un bambino di quinta elementare");
	}

	private static Boss creaMariella() {
		int hp = 250;
		int atk = 20;
		int def = 20;
		List<Item> drop = List.of(new Bomb("Bomba grande", false, 50), new Potion("Pozione grande", false, 50),
				new Coin("Crediti formativi", false), new Weapon("La Spada dell'Ozio", false, 20),
				new Armor("Gambali della discordia", false, 20));

		return new Boss("Mariella, la nullafacente", hp, atk, def, drop, "Io non mi occupo di queste cose");
	}

	private static Boss creaClaudia() {
		int hp = 150;
		int atk = 15;
		int def = 15;
		List<Item> drop = List.of(new Bomb("Bomba grande", false, 50), new Potion("Pozione grande", false, 50),
				new Coin("Crediti formativi", false), new Weapon("Spacca PC", false, 20),
				new Armor("Scarpe LV", false, 20));

		return new Boss("Claudia, la raccomandata", hp, atk, def, drop, "Faccio sempre tutto io");
	}

	/*
	 * Creazione Mob, vengono creati Mob in base al case precedente
	 */
	private static Mob createSergioForm() {
		int hp = 50 + random.nextInt(10);
		int atk = 5 + random.nextInt(5);
		int def = 0 + random.nextInt(5);
		List<Item> drop = List.of(new Bomb("Bomba media", false, 25), new Potion("Pozione media", false, 25),
				new Coin("Crediti formativi", false));
		return new Mob("Sergio Formiggini", hp, atk, def, drop);
	}

	private static Mob createStudenteArmellini() {
		int hp = 30 + random.nextInt(6);
		int atk = 5 + random.nextInt(3);
		int def = 0 + random.nextInt(5);
		List<Item> drop = List.of(new Bomb("Bomba piccola", false, 10), new Potion("Pozione piccola", false, 10),
				new Coin("Crediti formativi", false));
		return new Mob("Studente Armellini", hp, atk, def, drop);
	}

	private static Mob createCyberStudent() {
		int hp = 30 + random.nextInt(10);
		int atk = 5 + random.nextInt(3);
		int def = 0 + random.nextInt(5);
		List<Item> drop = List.of(new Bomb("Bomba piccola", false, 10), new Potion("Pozione piccola", false, 10),
				new Coin("Crediti formativi", false));
		return new Mob("Studente di Cyber", hp, atk, def, drop);
	}

	private static Mob createSegrataria() {
		int hp = 40 + random.nextInt(5);
		int atk = 6 + random.nextInt(5);
		int def = 0 + random.nextInt(5);
		List<Item> drop = List.of(new Bomb("Bomba piccola", false, 10), new Potion("Pozione piccola", false, 10),
				new Coin("Crediti formativi", false));
		return new Mob("Segretaria Its", hp, atk, def, drop);
	}
}
