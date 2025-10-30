
package tp1.control.commands;
import tp1.logic.*;
import tp1.view.*;



public interface Command {

	
	public void execute(Game game, GameView view);	
	
	Command parse(String[] a);
	
	String helpText();
	
}


