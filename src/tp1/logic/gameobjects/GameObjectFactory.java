//package tp1.logic.gameobjects;
//
//import java.util.Arrays;
//import java.util.List;
//import tp1.logic.GameWorld;
//
//public class GameObjectFactory {
//
//	private static final List<GameObject> availableObjects = Arrays.asList(
//		new Land(),
//		new Exit_door(),
//		new Goomba(),
//		new Mario()
//	);
//
//	public static GameObject parse(String[] objWords, GameWorld game) {
//		if (objWords == null || objWords.length == 0) {
//			return null;
//		}
//		
//		for (GameObject obj : availableObjects) {
//			GameObject parsed = obj.parse(objWords, game);
//			if (parsed != null) {
//				return parsed;
//			}
//		}
//		
//		return null;
//	}
//	
//	/**
//	 * Obtiene la lista de objetos disponibles con su descripción
//	 * @return String con información de los objetos disponibles
//	 */
//	public static String objectHelp() {
//		StringBuilder sb = new StringBuilder();
//		sb.append("Available objects:").append(System.lineSeparator());
//		
//		for (GameObject obj : availableObjects) {
//			String help = obj.getObjectHelp();
//			if (help != null && !help.isEmpty()) {
//				sb.append("  ").append(help).append(System.lineSeparator());
//			}
//		}
//		
//		return sb.toString();
//	}
//}