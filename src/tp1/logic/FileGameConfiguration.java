//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import tp1.view.Messages;
import tp1.exception.GameLoadException;
import tp1.exception.ObjectParseException;
import tp1.exception.OffBoardException;
import tp1.logic.gameobjects.*;


public class FileGameConfiguration implements GameConfiguration {

	
	private int remainingTime;
	private int points, lives;

	private String[] marioWords = null; 
    private List<String[]> objectsWords;
    
    private GameWorld game;
    
	public FileGameConfiguration(String fileName, GameWorld game) throws GameLoadException{
		
		
		try (BufferedReader inStream = new BufferedReader(new FileReader(fileName))){
			
			this.game = game;
//			this.gameObjects = new ArrayList<>();
//			this.mario = new Mario(game, new Position(0, 0)); //inicializamos con esa posición
			this.objectsWords = new ArrayList<>();
			
			String line = inStream.readLine(); //leemos del fichero
			
			parseStatus(line); //establecemos tiempo, puntos y vidas
			
			line = inStream.readLine();
						
			while(line != null && !line.isEmpty()) { //leemos hasta el final
//				String [] objWords = line.trim().split("\\s+");
//				parseAndAddObject(objWords, game);
//				line = inStream.readLine();
				String[] objWords = line.trim().split("\\s+");
				stringObject(objWords);
				line = inStream.readLine();

			}
			
			if (this.marioWords == null) { //si no hay mario, no hay juego
                throw new GameLoadException(Messages.NOT_MARIO_FOUND);
           }
			
		}
		catch(FileNotFoundException e) {
			throw new GameLoadException(Messages.FILE_NOT_FOUND.formatted(fileName), e);
		}
		catch(IOException e) {
			throw new GameLoadException(Messages.READ_ERROR.formatted(fileName), e);
		}
		catch(ObjectParseException | OffBoardException e) {
			throw new GameLoadException(Messages.INVALID_FILE_CONFIGURATION.formatted(fileName), e);
		}	
		catch(GameLoadException e) {
			throw e;		//necesario para evitar doble mensaje de la excepcion de parseStatus
		}
		catch(Exception e) {
			throw new GameLoadException(e.getMessage(), e); //excepción general, capaz haya q corregirlo para los tests
		}
	}
	
	private void parseStatus(String line) throws GameLoadException{
		try (Scanner scan = new Scanner(line)){
			
			remainingTime = scan.nextInt();
			points = scan.nextInt();
			lives = scan.nextInt();
		}
		catch (NoSuchElementException e) {
			throw new GameLoadException(Messages.INCORRECT_GAME_STATUS.formatted(line));
		}	
	}
	
	private void stringObject(String[] words) throws ObjectParseException, OffBoardException {
        @SuppressWarnings("unused")
		GameObject obj = GameObjectFactory.parse(words, game); //Solo para validar
        
        String type = words[1];
        
        if (type.equalsIgnoreCase(Messages.OBJECT_MARIO) || type.equalsIgnoreCase(Messages.OBJECT_MARIO_SHORTCUT)) {
             this.marioWords = words;
        } else {
             this.objectsWords.add(words);
        }
    }

	@Override
	public int getRemainingTime() {
		return remainingTime;
	}

	@Override
	public int getPoints() {
		return points;
	}

	@Override
	public int getLives() {
		return lives;
	}

	@Override
    public Mario getMario() {
        try {
            // Recreamos a Mario con los datos originales
            return (Mario) GameObjectFactory.parse(marioWords, game);
        } catch (Exception e) {
            // Esto no debería pasar porque ya validamos en el constructor
            return null; 
        }
    }

    @Override
    public List<GameObject> getObjects() {
        List<GameObject> realObjects = new ArrayList<>();
        
        for (String[] words : objectsWords) {
            try {
            	
                // Recreamos cada objeto         	
                realObjects.add(GameObjectFactory.parse(words, game));
                
            } catch (Exception e) {
                // No debería ocurrir
            }
        }
        return realObjects;
    }
	
}
