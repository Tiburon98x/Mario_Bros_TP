//package tp1.control.commands;
//
//import java.util.Arrays;
//
//import tp1.logic.Game;
//import tp1.logic.GameModel;
//import tp1.logic.gameobjects.GameObject;
//import tp1.logic.gameobjects.GameObjectFactory;
//import tp1.view.GameView;
//import tp1.view.Messages;
//
//public class AddObjectCommand extends AbstractCommand {
//
//	private static final String NAME = "addObject";
//	private static final String SHORTCUT = "aO";
//	private static final String DETAILS = "[a]dd[O]bject <object_description>";
//	private static final String HELP = "adds to the board the object given by object_description";
//	
//	private String[] objectDescription;
//	
//	public AddObjectCommand(String[] objectDescription) {
//		super(NAME, SHORTCUT, DETAILS, HELP);
//		this.objectDescription = objectDescription;
//	}
//	
//	@Override
//	public void execute(GameModel game, GameView view) {
//		if (!(game instanceof Game)) {
//			view.showError("AddObject command requires full Game instance");
//			return;
//		}
//		
//		Game g = (Game) game;
//		
//		// Intentar parsear el objeto
//		GameObject obj = GameObjectFactory.parse(objectDescription, g);
//		
//		if (obj == null) {
//			// No se pudo parsear
//			String objStr = String.join(" ", objectDescription);
//			view.showError(Messages.INVALID_GAME_OBJECT.formatted(objStr));
//			return;
//		}
//		
//		// Añadir el objeto al juego
//		g.addGameObject(obj);
//		
//		// Mostrar el tablero actualizado
//		view.showGame();
//	}
//
//	@Override
//	public Command parse(String[] commandWords) {
//		// Verificar que es el comando addObject o aO
//		if (!matchCommandName(commandWords[0])) {
//			return null;
//		}
//		
//		// Debe tener al menos 3 palabras: "aO" "(x,y)" "TIPO"
//		if (commandWords.length < 3) {
//			return new ErrorCommand(
//				Messages.COMMAND_PARAMETERS_MISSING + ": " + DETAILS
//			);
//		}
//		
//		// Copiar la descripción del objeto (desde índice 1 en adelante)
//		String[] objectDesc = Arrays.copyOfRange(commandWords, 1, commandWords.length);
//		
//		return new AddObjectCommand(objectDesc);
//	}
//
//}