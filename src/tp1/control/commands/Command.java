//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.control.commands;
import tp1.logic.*;
import tp1.view.*;

public interface Command {
	
	public void execute(GameModel game, GameView view);		
	Command parse(String[] a);	
	String helpText();
}


