package theRiseOfITS.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import theRiseOfITS.astratto.Entity;
import theRiseOfITS.astratto.Item;

public class utility {
	//function to check the health status of a given entity
	public static int checkHp(Entity entity) {
		return entity.getHp();
	}
	
	public static Item randomItemFromList(List<Item> list) {
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
