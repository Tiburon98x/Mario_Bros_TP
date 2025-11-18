package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
//import tp1.logic.gameobjects.*;
import java.util.Arrays;
import java.util.List;



public class GameObjectFactory {

	private static final List<GameObject> availableObjects = Arrays.asList(
			
			new Land(),
			new Exit_door(),
			new Goomba(),
			new Mario(),
			new Box(),
			new Mushroom()
			
	);
	
	
	
	public static GameObject parse (String objWords[], GameWorld game) {

		for (GameObject obj : availableObjects) {
			
			GameObject object = obj.parse(objWords, game);
			if(object != null)
				return object;
			
		}
		
		return null;
		
	}

	
	
}