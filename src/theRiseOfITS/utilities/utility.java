package theRiseOfITS.utilities;

import theRiseOfITS.astratto.Entity;

public class utility {
	//function to check the health status of a given entity
	public static int checkHp(Entity entity) {
		return entity.getHp();
	}
}
