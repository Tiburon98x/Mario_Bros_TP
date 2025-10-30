package tp1.control.commands;

import tp1.logic.Game;
import tp1.view.GameView;

public class ResetCommand extends AbstractCommand implements Command {
	
	public ResetCommand(String name, String shorcut, String details, String help) {
		super(name, shorcut, details, help);
		// TODO Auto-generated constructor stub
	}


	protected boolean matchCommand(String Command) {
		
		
		return false;
	
	}
	
	
	public String helpText() {
		
		
		return null;		
	}
	
	public Command parse(String[] Commands) {
	
		
		return null;
				
	}
	
	public void execute(Game game, GameView view) {
		
		
	}
}
