package tp1.control.commands;

import java.util.Arrays;

import tp1.logic.GameModel;
import tp1.logic.GameWorld;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.GameObjectFactory;
import tp1.view.GameView;
import tp1.view.Messages;

public class AddObjectCommand extends AbstractCommand {

	private static final String NAME = "addObject";
	private static final String SHORTCUT = "aO";
	private static final String DETAILS = "[a]dd[O]bject <object_description>";
	private static final String HELP = "adds to the board the object given by object_description";
	
	  private String[] objWords; //Comparar con contructur de actioncommand
	  private String objString;	
	public AddObjectCommand(String[] objectDescription) {
		super(NAME, SHORTCUT, DETAILS, HELP);
		this.objString = String.join(" ", objectDescription);;
	}
	
	// Es necsario hacer un execute aparte o se puede usar downcasting
	//Opción feo con execute en todos lados
	//  @Override
//	    public boolean execute(GameWorld game, GameView view) {
//	        GameObject obj = GameObjectFactory.parse(objWords, game);
//	        if (obj != null) {
//	            game.addGameObject(obj);
//	            return true;
//	        } else {
//	            view.showMessage(Messages.INVALID_GAME_OBJECT + ": " + objString);
//	            return false;
//	        }
	// game.update();
//	     view.showGame();
//	    }
	 
	 //Opción ideal si se permite downcasting y quitar los execute
	 @Override
	   public void execute(GameModel game, GameView view) {
	       GameObject obj = GameObjectFactory.parse(objWords, (GameWorld) game);
	       if (obj != null) {
	           game.addGameObject(obj);
	          // return true;
	       } else {
	       
	           view.showMessage(Messages.INVALID_GAME_OBJECT + ": " + objString);
	           //return false;
	           
	       }
	       
	       game.update();
	       view.showGame();
	   }

	 
//	@Override
//	public Command parse(String[] commandWords) {
//		// Verificar que es el comando addObject o aO
//		if (!matchCommandName(commandWords[0])) {
//			return null;
//		}
//		
//		// Debe tener al menos 3 palabras: "aO" "(x,y)" "TIPO"
//		if (commandWords.length < 2) {
////			return new ErrorCommand(
////				Messages.COMMAND_PARAMETERS_MISSING + ": " + DETAILS
////			);
//			return null;
//		}
//		
//		// Copiar la descripción del objeto (desde índice 1 en adelante)
//		String[] objectDescription = Arrays.copyOfRange(commandWords, 1, commandWords.length);
//		
//		return new AddObjectCommand(objectDescription);
//	}
	 @Override
	   public Command parse(String[] commandWords) {
	       if (matchCommandName(commandWords[0])) {
	           if (commandWords.length < 2)
	               return null;
	           
	           String[] objWords = Arrays.copyOfRange(commandWords, 1, commandWords.length);
	           return new AddObjectCommand(objWords);
	       }
	       return null;
	   }

}