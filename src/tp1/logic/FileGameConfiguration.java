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
	private Mario mario;
	
	private List<GameObject> gameObjects;
	
	
	public FileGameConfiguration(String fileName, GameWorld game) throws GameLoadException{
		
		
		try (BufferedReader inStream = new BufferedReader(new FileReader(fileName))){
			
			this.gameObjects = new ArrayList<>();
			this.mario = new Mario(game, new Position(0, 0)); //inicializamos con esa posición
			
			String line = inStream.readLine();
			//si line está vacio, excepcion??
			
			parseStatus(line);
			
			line = inStream.readLine();
						
			while(line != null && !line.isEmpty()) { //no se si esa condición es correcta, aunq el profe lo tenía así
				String [] objWords = line.trim().split("\\s+");
				parseAndAddObject(objWords, game);
				line = inStream.readLine();
				
			}
			
			if (this.mario == null) { //si no hay mario, no hay juego
                throw new GameLoadException("File format error: Mario not found in file");
           }
			
		}
		catch(FileNotFoundException e) {
			throw new GameLoadException(Messages.FILE_NOT_FOUND.formatted(fileName));
		}
		catch(IOException e) {
			throw new GameLoadException(Messages.READ_ERROR.formatted(fileName), e);
		}
		catch(ObjectParseException | OffBoardException e) {
			throw new GameLoadException(Messages.INVALID_FILE_CONFIGURATION.formatted(fileName), e);
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
			if(scan.hasNext()) 
				throw new GameLoadException("Invalid game status: Too many arguments in header");
		}
		catch (NoSuchElementException e) {
			throw new GameLoadException(Messages.INCORRECT_GAME_STATUS.formatted(line), e);
		}	
	}
	
	
	private void parseAndAddObject(String [] objWords, GameWorld game) throws ObjectParseException, OffBoardException{
		
		Mario mario = this.mario.parse(objWords, game);
		GameObject obj = mario;

		if(obj != null)
			this.mario = mario;
		else {
			obj = GameObjectFactory.parse(objWords, game);
			this.gameObjects.add(obj);
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
		return mario;
	}

	@Override
	public List<GameObject> getObjects() {
		return gameObjects;
	}
	
}
