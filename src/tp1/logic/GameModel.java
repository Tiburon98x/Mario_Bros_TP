//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic;

import tp1.exception.OffBoardException;
import tp1.exception.GameLoadException;
import tp1.exception.GameModelException;
import tp1.exception.ObjectParseException;

//para controller
public interface GameModel {
	
	public boolean isFinished();
	public void update();
	public void reset(int level) throws GameModelException;
	public void exit();
	public int getCurrentLevel();
	public void addActionToMario(Action act);
//	public boolean addGameObject(String[] WORDS);
	public void addObject(String[] WORDS) throws OffBoardException, ObjectParseException;
	public void AddObjectToContainer();
	public void save(String fileName) throws GameModelException;
	public void load(String fileName) throws GameLoadException;
	
}