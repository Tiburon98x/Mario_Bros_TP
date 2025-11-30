//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic.gameobjects;

import tp1.exception.GameParseException;
import tp1.exception.ObjectParseException;
import tp1.exception.OffBoardException;
import tp1.exception.PositionParseException;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;
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
	
	
	
	public static GameObject parse (String objWords[], GameWorld game) throws ObjectParseException, OffBoardException {

//		Position pos = Position.parse(objWords[0]); //lanza PPE
//		
//		if (!game.isInside(pos)) {
//            throw new OffBoardException(Messages.OBJECT_POSITION_OFF_BOARD.formatted(String.join(" ", objWords)));
//        }
		
		for (GameObject obj : availableObjects) {
			
			GameObject object = obj.parse(objWords, game);
			if(object != null)
				return object;
			
		}
		
		throw new ObjectParseException(Messages.UNKNOWN_GAME_OBJECT.formatted(String.join(" ", objWords)));		
	}

	
	
}