//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.control;



import tp1.logic.*;
import tp1.view.*;



public class Controller {

	private Game game;
	private GameView view;
	private boolean running = true; //para el bucle while
	private String[] input;
	private ActionList actionList;
	
	
	public Controller(Game game, GameView view) {
		
		this.game = game;
		this.view = view;
		this.game.setController(this);
		this.actionList = new ActionList();
		
	}


	
	public void run() {
		
		view.showWelcome();

		while (running) {
			
            view.showGame(); // pinta el tablero
            input = view.getPrompt();

            if (input.length == 0|| input[0].isEmpty()) {
            	
                // comando vacío = update
                update();
                continue;
                
            }
            
            //Lo que recibe (oración) lo divide en palabras lo mete en un array y lo pasa a minúsculas
            String command = input[0].toLowerCase(); 
            
            switch (command) {
//            case "e":
//            case "exit":
//                exit();
//                break;

            case "h":
            case "help":
                help();
                break;
                
            case "r":
            case "reset":
                int level = 0;
                if (input.length > 1) {
                	// Pasa el string a entero y si puede con catch evita que pare de funcionar
                    try {
                        level = Integer.parseInt(input[1]);
                    } catch (NumberFormatException e) {
        				view.showError("Invalid level number, keeping current level.");
                        level = game.getCurrentLevel();

                    }
                }
                reset(level);
                break;
                
            case "a":
            case "action":
                processActions(input);
                update(); // ejecutar actualización después de las acciones
                break;

            case "u":
            case "update":
                update();
                break;
        
            default:
                view.showMessage("Message.UNKNOWN_COMMAND" + input);
        }
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

	
	
	private void help() {
		
	    for (String line : Messages.HELP_LINES) {
	        view.showMessage(line);
	    }
	}

    
	
    private void reset(int level) {
    	
        game.reset(level);
		view.showMessage("Game reset to level " + level);
		
    }

    
    
    private void processActions(String[] input) {
    	
        // Empieza desde i=1 porque parts[0] es "action" o "a"
        for (int i = 1; i < input.length; i++) {
        	
            Action act = parseAction(input[i]);
            if (act != null) {
            	
            	 if (!actionList.add(act)) {
            		 
                     view.showMessage("Añada como máximo 4 acciones");
                     
                 }
            } else {
            	
				view.showError("Unknown action: " + input[i]);
				
            }
        }
    }

    
    
    private void update() {
    	
    	game.update();

    }
    
    
    
    public Iterable<Action> consumeActions() {
    	
        return actionList.IterableAndClear();
        
    }
    
    
    
    private Action parseAction(String str) {
    	
        str = str.toLowerCase();
        switch (str) {
            case "l":
            case "left":
                return Action.LEFT;
            case "r":
            case "right":
                return Action.RIGHT;
            case "u":
            case "up":
                return Action.UP;
            case "d":
            case "down":
                return Action.DOWN;
            case "s":
            case "stop":
                return Action.STOP;
            default:
                return null;
                
        }
    }
    
    
    
    public void run_out_time_message(){
    	
    	view.showMessage("¡Tiempo agotado!");
    	
    }
    
    
    
    public void game_over_message() {
    	
       	view.showMessage("Game Over");
       	
    }
    
    
    
    public void reset_message() {
    	
    	view.showMessage("Mario ha muerto. Reiniciando nivel...");
    }
    
    
    
    public void exit_message() {
    	
    	view.showMessage("Thanks, Mario! Your mission is complete.");
    }
    
    

}