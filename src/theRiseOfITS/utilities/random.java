package theRiseOfITS.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import theRiseOfITS.astratto.Item;

public class random {

	//The function returns the index of a random item in a given list
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

}
