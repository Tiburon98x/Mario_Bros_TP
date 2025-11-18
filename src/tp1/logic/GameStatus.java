//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic;

//para gameview
public interface GameStatus {
	
	public int points();
	public int numLives();
	public int remainingTime();
	public boolean playerWins();
	public boolean playerLoses();
	public String positionToString(int col, int row);
	
}