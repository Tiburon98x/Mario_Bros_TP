//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS
package tp1.logic;


import tp1.control.*;
import tp1.logic.gameobjects.*;
import tp1.view.*;



public class Game {

	public static final int DIM_X = 30;
	public static final int DIM_Y = 15;
	private GameObjectContainer gameObjects;
	private Mario mario;
	private Controller controller;
	private GameView view;
	
	private int level;
    @SuppressWarnings("unused")
	private int time;
    private int points;
    private int lives; 
    private int remainingTime = 100;
    private boolean finished = false;
    private boolean win = false;

    
    
	public Game(int nLevel) {
		
		this.level = nLevel;
		this.time = 0;
		this.points = 0;
		this.lives = 3;
		this.gameObjects = new GameObjectContainer();
		initLevel0();
		
		}
	
	
	 
	 public void setController(Controller controller) {
		 
        this.controller = controller;
        
	 	}
	 
	 
	 
	 private void initLevel(int level) {
	  
	        switch (level) {
	        
	            case 0:
	                initLevel0();
	                break;
	            case 1:
	            	initLevel1();
	            	break;
	             default:
	            	 initLevel0(); // si no existe el nivel, carga el 0
	                break;
	        }
	  }
	  
	  
	 
	  public String positionToString(int col, int row) {

		  Position pos = new Position(col , row);
		  return gameObjects.busqueda(pos);
	}
	
	
	  
	public boolean playerWins() {

		return win;
	}
	
	public boolean playerLoses() {

		return lives <= 0;
	}

	public int remainingTime() {

		return remainingTime;
	}

	public int points() {
		
		return points;
	}

	public int numLives() {
		
		return lives;
	}

	@Override
	public String toString() {
		
		return "TODO: Hola soy el game";
		
	}
	

	private void initLevel0() {
		
		// 1. Mapa
		gameObjects = new GameObjectContainer();
		for(int col = 0; col < 15; col++) {
	
			//Coordenadas cambiadas de orden!!!!
			gameObjects.add(new Land(new Position(col,13)));
			gameObjects.add(new Land(new Position(col,14)));
			
		}

		gameObjects.add(new Land(new Position(9,Game.DIM_Y-3)));
		gameObjects.add(new Land(new Position(12,Game.DIM_Y-3)));
		
		for(int col = 17; col < Game.DIM_X; col++) {
				
			gameObjects.add(new Land(new Position(col, Game.DIM_Y-2)));
			gameObjects.add(new Land(new Position(col, Game.DIM_Y-1)));	
			
		}

		gameObjects.add(new Land(new Position(2,9)));
		gameObjects.add(new Land(new Position(5,9)));
		gameObjects.add(new Land(new Position(6,9)));
		gameObjects.add(new Land(new Position(7,9)));
		gameObjects.add(new Land(new Position(6,5)));
		
		// Salto final
		@SuppressWarnings("unused")
		int tamX = 8, tamY= 8;
		int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;
		
		for(int col = 0; col < tamX; col++) {
			
			for (int fila = 0; fila < col+1; fila++) {
				
				gameObjects.add(new Land(new Position(posIniX+ col,posIniY- fila )));

			}
		}
		// 3. Personajes
		gameObjects.add(new Goomba(this, new Position(19, 0)));
		this.mario = new Mario(this, new Position(0, Game.DIM_Y-3));
		gameObjects.add(this.mario);
		gameObjects.add(new Exit_door(new Position(Game.DIM_X-1, Game.DIM_Y-3)));
	}
	
private void initLevel1() {
		
		// 1. Mapa
		gameObjects = new GameObjectContainer();
		for(int col = 0; col < 15; col++) {
	
			//Coordenadas cambiadas de orden!!!!
			gameObjects.add(new Land(new Position(col,13)));
			gameObjects.add(new Land(new Position(col,14)));
			
		}

		gameObjects.add(new Land(new Position(9,Game.DIM_Y-3)));
		gameObjects.add(new Land(new Position(12,Game.DIM_Y-3)));
		
		for(int col = 17; col < Game.DIM_X; col++) {
				
			gameObjects.add(new Land(new Position(col, Game.DIM_Y-2)));
			gameObjects.add(new Land(new Position(col, Game.DIM_Y-1)));	
			
		}

		gameObjects.add(new Land(new Position(2,9)));
		gameObjects.add(new Land(new Position(5,9)));
		gameObjects.add(new Land(new Position(6,9)));
		gameObjects.add(new Land(new Position(7,9)));
		gameObjects.add(new Land(new Position(6,5)));
		
		// Salto final
		@SuppressWarnings("unused")
		int tamX = 8, tamY= 8;
		int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;
		
		for(int col = 0; col < tamX; col++) {
			
			for (int fila = 0; fila < col+1; fila++) {
				
				gameObjects.add(new Land(new Position(posIniX+ col,posIniY- fila )));

			}
		}
		// 3. Personajes
		gameObjects.add(new Goomba(this, new Position(19, 0)));
		gameObjects.add(new Goomba(this, new Position(6, 4)));
		gameObjects.add(new Goomba(this, new Position(6, 12)));
		gameObjects.add(new Goomba(this, new Position(8, 12)));
		gameObjects.add(new Goomba(this, new Position(10, 10)));
		gameObjects.add(new Goomba(this, new Position(11, 12)));
		gameObjects.add(new Goomba(this, new Position(14, 12)));
		
		
		this.mario = new Mario(this, new Position(0, Game.DIM_Y-3));
		gameObjects.add(this.mario);
		gameObjects.add(new Exit_door(new Position(Game.DIM_X-1, Game.DIM_Y-3)));
	}

			
		
	public void update() {
	    // Primero Mario
	    if (finished || lives <= 0) return;

	    // Mario procesa las acciones del turno
	    mario.update(controller.consumeActions());

	    // Control del tiempo
	    remainingTime--;
	    if (remainingTime <= 0) {
	    	
	        controller.run_out_time_message();
	        marioMuere();
	        return;
	        
	    }

	    // Luego el resto de objetos (Goombas, etc.)
	    gameObjects.update(this);
	    // Interacciones de Mario con otros objetos
	    doInteractionsFrom(mario);
	}

	
	
	public int getCurrentLevel() {
		
	    return level;
	}
	
	public void reset(int level) {
		
	    this.time = 0;
	    // vidas y puntos se mantienen
	    this.level = level;
	    this.remainingTime = 100;
	    this.finished = false;
	    this.win = false;
	    // limpiar y volver a inicializar
	    this.gameObjects = new GameObjectContainer();
	    initLevel(level);
	    
	}
	
	
	public GameObjectContainer getContainer() {
		
		return this.gameObjects;
		
	}
	
	public boolean isSolid(Position pos) {
		
	    return gameObjects.solido(pos);
	    
	}

	public boolean isInside(Position pos) {
		
	    return gameObjects.dentro(pos);
	    
	}

	public boolean isEmpty(Position pos) {
		
	    return gameObjects.vacio(pos);
	    
	}
	
	public boolean isExit(Position pos) {
		
	    return gameObjects.getExitDoor().isInPos(pos);
	    
	}

	public void marioMuere() {
		
		lives--;
	    if (lives <= 0) {
	    	
	    	controller.game_over_message();
	        finished = true;
	        win = false;
	        
	    } else {
	    	
			controller.reset_message();;
	        reset(level);
	        
	    }
	    
	} 

	

	public void marioExited() {
		
	    int pointsEarned = remainingTime * 10; 
	    points += pointsEarned; 
	    finished = true;
	    win = true;
	    controller.exit_message();
	    
	}
	
	
	
	public boolean isFinished() {
		
	    return finished;
	    
	}
	
	public void finished() {
		
		finished = true;
		
	}
	
	public void doInteractionsFrom(Mario mario) {
		
	    gameObjects.doInteractionsFrom(mario);
	    
	}
	
	
	
	public void addPoints(int pts) {
		
	    this.points += pts;
	    
	}



	public void exit() {
			
		finished();
		view.showMessage(Messages.PLAYER_QUITS);
		
	}
	

	
	
}