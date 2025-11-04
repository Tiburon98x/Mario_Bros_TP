//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.control;



import tp1.logic.*;
import tp1.view.*;



public class Controller {

	//private Game game;
	private GameModel gameModel;
	private GameView view;
	private boolean running = true; //para el bucle while
	private String[] input;
	private ActionList actionList;
	
	
	public Controller(GameModel game, GameView view) {
		
		this.gameModel = game;
		this.view = view;
		this.game.setController(this);
		this.actionList = new ActionList();
		
	}


	
	public void run() {
		
		view.showWelcome();

		while (running) {
			
         
 
            if (game.isFinished()) {
            	
                running = false;
                view.showGame();
                
                if (game.playerLoses()) {
                	
                    view.showMessage("Player looses");
                    
                } else {
                	
                    view.showMessage(Messages.MARIO_WINS);
                    
                }
                break;
                
            }
            
    }
//		while (!game.isFinished()) {
//
//		    String[] userWords = view.getPrompt();
//		    Command command = CommandGenerator.parse(userWords);
//
//		    if (command != null) 
//				command.execute(game, view);
//		    else 
//		        view.showError(Messages.UNKNOWN_COMMAND.formatted(String.join(" ", words)));
//		}   
//		
		
		
	}
	
	
	
//	private void exit() {
//		
//		view.showMessage(Messages.PLAYER_QUITS);
//        running = false;
//    }

	
	
//	private void help() {
//		
//	    for (String line : Messages.HELP_LINES) {
//	        view.showMessage(line);
//	    }
//	}

    
	
//    private void reset(int level) {
//    	
//        game.reset(level);
//		view.showMessage("Game reset to level " + level);
//		
//    }

    
    
//    private void processActions(String[] input) {
//    	
//        // Empieza desde i=1 porque parts[0] es "action" o "a"
//        for (int i = 1; i < input.length; i++) {
//        	
//            Action act = parseAction(input[i]);
//            if (act != null) {
//            	
//            	 if (!actionList.add(act)) {
//            		 
//                     view.showMessage("Añada como máximo 4 acciones");
//                     
//                 }
//            } else {
//            	
//				view.showError("Unknown action: " + input[i]);
//				
//            }
//        }
//    }

    
    
//    private void update() {
//    	
//    	game.update();
//
//    }
    
    
    
    public Iterable<Action> actions() {
    	
        return actionList.IterableAndClear();
        
    }
    
    
    
//    private Action parseAction(String str) {
//    	
//        str = str.toLowerCase();
//        switch (str) {
//            case "l":
//            case "left":
//                return Action.LEFT;
//            case "r":
//            case "right":
//                return Action.RIGHT;
//            case "u":
//            case "up":
//                return Action.UP;
//            case "d":
//            case "down":
//                return Action.DOWN;
//            case "s":
//            case "stop":
//                return Action.STOP;
//            default:
//                return null;
//                
//        }
//    }
    
    
    
    public void run_out_time_message(){
    	
    	view.showMessage("¡Tiempo agotado!");
    	
    }
    
    
    
    public void game_over_message() {
    	
        view.showMessage(Messages.GAME_OVER);
       	
    }
    
    
    
    public void reset_message() {
    	
    	view.showMessage("Mario ha muerto. Reiniciando nivel...");
    }
    
    
    
    public void exit_message() {
        view.showMessage(Messages.MARIO_WINS);
    }
    
    

}