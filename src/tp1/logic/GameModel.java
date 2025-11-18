//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic;

//para controller
public interface GameModel {
	
	public boolean isFinished();
	public void update();
	public void reset(int level);
	public void exit();
	public int getCurrentLevel();
	public boolean playerWins();
	public void addActionToMario(Action act);
	public boolean playerLoses();
	public boolean addGameObject(String[] WORDS);
	public void AddObject();
	
}