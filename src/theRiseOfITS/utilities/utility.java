package theRiseOfITS.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.File;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	public static List<Item> randomListOfItemsFromDB(int size){
		List<Item> allItems = leggiItemsDaJson("/TheRiseOfITS/src/theRiseOfITS/databases/ItemsDB.json");
		 List<Item> selezionati = new ArrayList<>();
		 Random random = new Random();

	        if (allItems == null || allItems.isEmpty() || size <= 0) {
	            return selezionati; // lista vuota
	        }

	        for (int i = 0; i < size; i++) {
	            int index = random.nextInt(allItems.size());
	            selezionati.add(allItems.get(index));
	        }

	        return selezionati;
	}
	
	public static List<Item> leggiItemsDaJson(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(filePath), new TypeReference<List<Item>>() {}); //usa istance of per filtrare tipi di item
        } catch (IOException e) {
            e.printStackTrace();
            return List.of(); // ritorna lista vuota se c'è un errore
        }
    }
}
