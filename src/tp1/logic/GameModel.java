package tp1.logic;

//para controller
public interface GameModel {
	
	public boolean isFinished();
	public void update();
	public void reset(int level);
	public void exit();
	public int getCurrentLevel();
	public ActionList getActionList();
	public boolean playerWins();
}