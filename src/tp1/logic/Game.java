//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS
package tp1.logic;


import tp1.control.*;

import tp1.logic.gameobjects.*;
//import tp1.view.*;



public class Game implements GameModel, GameStatus, GameWorld {

	public static final int DIM_X = 30;
	public static final int DIM_Y = 15;
	private GameObjectContainer gameObjects;
	private Mario mario;
	private Controller controller;
	
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
		initLevel(level);
		
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
            //		controller.reset_message();
            	initLevel1(); // si no existe el nivel, carga el 1
                break;
        }
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
			gameObjects.addItem(new Land(this, new Position(col,13)));
			gameObjects.addItem(new Land(this, new Position(col,14)));
			
		}

		gameObjects.addItem(new Land(this, new Position(9,Game.DIM_Y-3)));
		gameObjects.addItem(new Land(this, new Position(12,Game.DIM_Y-3)));
		
		for(int col = 17; col < Game.DIM_X; col++) {
				
			gameObjects.addItem(new Land(this, new Position(col, Game.DIM_Y-2)));
			gameObjects.addItem(new Land(this, new Position(col, Game.DIM_Y-1)));	
			
		}

		gameObjects.addItem(new Land(this, new Position(2,9)));
		gameObjects.addItem(new Land(this, new Position(5,9)));
		gameObjects.addItem(new Land(this, new Position(6,9)));
		gameObjects.addItem(new Land(this, new Position(7,9)));
		gameObjects.addItem(new Land(this, new Position(6,5)));
		
		// Salto final-
		@SuppressWarnings("unused")
		int tamX = 8, tamY= 8;
		int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;
		
		for(int col = 0; col < tamX; col++) {
			
			for (int fila = 0; fila < col+1; fila++) {
				
				gameObjects.addItem(new Land(this, new Position(posIniX+ col,posIniY- fila )));

			}
		}
		// 3. Personajes
		gameObjects.addItem(new Goomba(this, new Position(19, 0)));
		this.mario = new Mario(this, new Position(0, Game.DIM_Y-3));
		gameObjects.addItem(this.mario);
		gameObjects.addItem(new Exit_door(this, new Position(Game.DIM_X-1, Game.DIM_Y-3)));
	}
	
private void initLevel1() {
		
		// 1. Mapa
		gameObjects = new GameObjectContainer();
		for(int col = 0; col < 15; col++) {
	
			//Coordenadas cambiadas de orden!!!!
			gameObjects.addItem(new Land(this, new Position(col,13)));
			gameObjects.addItem(new Land(this, new Position(col,14)));
			
		}

		gameObjects.addItem(new Land(this, new Position(9,Game.DIM_Y-3)));
		gameObjects.addItem(new Land(this, new Position(12,Game.DIM_Y-3)));
		
		for(int col = 17; col < Game.DIM_X; col++) {
				
			gameObjects.addItem(new Land(this, new Position(col, Game.DIM_Y-2)));
			gameObjects.addItem(new Land(this, new Position(col, Game.DIM_Y-1)));	
			
		}

		gameObjects.addItem(new Land(this, new Position(2,9)));
		gameObjects.addItem(new Land(this, new Position(5,9)));
		gameObjects.addItem(new Land(this, new Position(6,9)));
		gameObjects.addItem(new Land(this, new Position(7,9)));
		gameObjects.addItem(new Land(this, new Position(6,5)));
		
		// Salto final
		@SuppressWarnings("unused")
		int tamX = 8, tamY= 8;
		int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;
		
		for(int col = 0; col < tamX; col++) {
			
			for (int fila = 0; fila < col+1; fila++) {
				
				gameObjects.addItem(new Land(this, new Position(posIniX+ col,posIniY- fila )));

			}
		}
		
		this.mario = new Mario(this, new Position(0, Game.DIM_Y-3));
		gameObjects.addItem(this.mario);
		// 3. Personajes
		gameObjects.addItem(new Goomba(this, new Position(19, 0)));
		gameObjects.addItem(new Goomba(this, new Position(6, 4)));
		gameObjects.addItem(new Goomba(this, new Position(6, 12)));
		gameObjects.addItem(new Goomba(this, new Position(8, 12)));
		gameObjects.addItem(new Goomba(this, new Position(10, 10)));
		gameObjects.addItem(new Goomba(this, new Position(11, 12)));
		gameObjects.addItem(new Goomba(this, new Position(14, 12)));
		

		gameObjects.addItem(new Exit_door(this, new Position(Game.DIM_X-1, Game.DIM_Y-3)));
	}
	

	
	
	
//	public GameObjectContainer getContainer() {
//		
//		return this.gameObjects;
//		
//	}

	 

//----------------GameModel-----------------------

	 @Override
	 public void update() {
		 
		 if (finished || lives <= 0) return;

	    // Control del tiempo
		 remainingTime--;
		 if (remainingTime <= 0) {
		    	
			 controller.run_out_time_message();
			 marioMuere();
			 return;		        
		 }
		    
		 gameObjects.update(this);		  
		   		    			
		 gameObjects.removeDead();
	 }

	 @Override
	 public void reset(int level) {
		 this.level = level;
		 this.points = 0;
		 this.remainingTime = 100;
		 this.finished = false;
		 this.win = false;
		 this.gameObjects = new GameObjectContainer();
		 initLevel(level);
	 }
	 
	 public void perderVida(int level) {
		 this.level = level;
		 this.remainingTime = 100;
		 this.finished = false;
		 this.win = false;
		 this.gameObjects = new GameObjectContainer();
		 initLevel(level);
		 
	 }


	 @Override
	 public boolean isFinished() {
	    return finished;
	 }

	@Override
	public void exit() {
			finished = true;
		}
	 
	 @Override
		public int getCurrentLevel() {
			return level;
		}
	 
	 @Override
	 public void addActionToMario(Action act) {
			this.mario.addAction(act);
		}
	 
//------------GameStatus-----------------------

		
	@Override
	public int points() {
		return points;
	}

	@Override
	public boolean playerWins() {
		return win;
	}

	@Override
	public String positionToString(int col, int row) {
		Position pos = new Position(col , row);
		return gameObjects.busqueda(pos);
	}
	
	@Override
	public int numLives() {
		return lives;
	}
	
	@Override
	public int remainingTime() {
		return remainingTime;
	}
	
	@Override
	public boolean playerLoses() {
		return lives <= 0;
	}
	
	

//---------GameWorld------------------

	@Override
	public void marioExited() {
		int pointsEarned = remainingTime * 10; 
		points += pointsEarned; 
		remainingTime = 0;
		finished = true;
		win = true;
	//	controller.exit_message();
	}

	@Override
	public int addPoints(int pts) {
	
		return this.points += pts;
    
	 }	

	@Override
	public boolean isSolid(Position pos) {
		return gameObjects.solido(pos);
 	}
	
	@Override
	public boolean isEmpty(Position pos) {
		return gameObjects.vacio(pos);
	}
	
	@Override
	public boolean isInside(Position pos) {
		Position bordes = new Position(Game.DIM_X, Game.DIM_Y);
		return pos.isInPosition(bordes);
	}

	@Override
	public void marioMuere() {

		lives--;
		if (lives <= 0) {
//			controller.game_over_message();
			finished = true;
			win = false;
		} else {
//			controller.reset_message();
			perderVida(level);
		}
	}
	
	@Override
	public void doInteraction(GameItem other) {
		gameObjects.doInteraction(other);
	}
	


	
}