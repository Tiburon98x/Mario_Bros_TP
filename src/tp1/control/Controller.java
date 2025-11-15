//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.control;

import tp1.control.commands.*;
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

			String[] userWords = view.getPrompt();
			Command command = CommandGenerator.parse(userWords);

			if (command != null) {
				command.execute(game, view);
			}
			else if (userWords.length == 1 && userWords[0].isEmpty()){
				
				game.update();
				view.showGame();
			}
			else 
				view.showError(Messages.UNKNOWN_COMMAND.formatted(String.join(" ", userWords)));
		} 
		if(game.playerWins()) {
			view.showMessage(Messages.MARIO_WINS);
		}
		else if(game.playerLoses()) {
			view.showMessage(Messages.GAME_OVER);
		}
		
	}
	    
    public void run_out_time_message(){
    	
    	view.showMessage("¡Tiempo agotado!");
    	
    }

}