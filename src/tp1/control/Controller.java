//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.control;

import tp1.control.commands.*;
import tp1.exception.CommandParseException;
import tp1.exception.CommandExecuteException;

import tp1.logic.*;
import tp1.view.*;

public class Controller {

	private GameModel game;
	private GameView view;
	
	public Controller(GameModel game, GameView view) {
		
		this.game = game;
		this.view = view;
	}
	
	public void run() {
		
		view.showWelcome();
		view.showGame();
		
		while (!game.isFinished()) {			

			try {
			String[] userWords = view.getPrompt();
			
			if(userWords.length == 1 && userWords[0].isEmpty()){
				
				game.update();
				view.showGame();
			}
			else {Command command = CommandGenerator.parse(userWords);			
			command.execute(game, view);
			}
		
			
			} catch (CommandParseException | CommandExecuteException e){
			  
				view.showError(e.getMessage());
				Throwable cause = e.getCause();
				while(cause != null) {
					view.showError(cause.getMessage());
	 				cause = cause.getCause();
				}
			}
		
			
			
//			String[] userWords = view.getPrompt();
//			Command command = CommandGenerator.parse(userWords);
//			
//			if(command != null) {
//				command.execute(game, view);
//			}
//			else if (userWords.length == 1 && userWords[0].isEmpty()){
//					
//				game.update();
//				view.showGame();
//			}
//			else 
//				view.showError(Messages.UNKNOWN_COMMAND.formatted(String.join(" ", userWords)));
//			}
//		view.showEndMessage();	

		} 
		view.showEndMessage();

	}

}