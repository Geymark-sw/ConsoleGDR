package theRiseOfITS.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import theRiseOfITS.astratto.Item;

public class random {

	public static Item generateRandomItem(List<Item> list) {
		List<Integer> listIndexofItem = new ArrayList<Integer>();
		for (Item item : list) {
			listIndexofItem.add(item.getId());
		}
		Random random = new Random();
		int randomIndex = random.nextInt(listIndexofItem.size());
		int itemIndex = listIndexofItem.get(randomIndex);
		return list.get(itemIndex);
	}
	
	public static int generateRandomInt(int max) {
		List<Integer> listIndexofInteger = new ArrayList<Integer>();
		for(int i = 0; i <= max; i++) {
			listIndexofInteger.add(i);
		}
		Random random = new Random();
		int randomIndex = random.nextInt(listIndexofInteger.size());
		int intIndex = listIndexofInteger.get(randomIndex);
		return intIndex;
	}
}
