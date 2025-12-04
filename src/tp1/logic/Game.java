//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS
package tp1.logic;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import tp1.exception.GameLoadException;
import tp1.exception.GameModelException;
import tp1.exception.ObjectParseException;
import tp1.exception.OffBoardException;
import tp1.logic.gameobjects.*;
import tp1.view.Messages;

public class Game implements GameModel, GameStatus, GameWorld {

	public static final int DIM_X = 30;
	public static final int DIM_Y = 15;
	private GameObjectContainer gameObjects;
	private Mario mario;
	
	private int level;
    @SuppressWarnings("unused")
	private int time;
    private int points;
    private int lives; 
    private int remainingTime;
    private boolean finished = false;
    private boolean win = false;
    
    private GameConfiguration fileloader;
       
	public Game(int nLevel) {
		
		this.level = nLevel;
		this.time = 0;
		this.points = 0;
		this.lives = 3;
		this.gameObjects = new GameObjectContainer();
		initLevel(level);
		
	}
 	
	private void initLevel(int level) {
		
		this.remainingTime = 100;
		
        switch (level) {	        
            case 0:
                initLevel0();
                break;
            case 1:
            	initLevel1();
            	break;
            case 2:
            	initLevel2();
            	break;
            case -1:
            	initLevel_vacío();
            	break;
            	default:
            	initLevel1(); // si no existe el nivel, carga el 1
                break;
                
        }

	 }
	 
	private void initLevel0() {
		
		// 1. Mapa
		gameObjects = new GameObjectContainer();
		for(int col = 0; col < 15; col++) {
	
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
		
		@SuppressWarnings("unused")
		int tamX = 8, tamY= 8;
		int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;
		
		for(int col = 0; col < tamX; col++) {
			
			for (int fila = 0; fila < col+1; fila++) {
				
				gameObjects.addItem(new Land(this, new Position(posIniX+ col,posIniY- fila )));

			}
		}
		gameObjects.addItem(new Goomba(this, new Position(19, 0)));
		this.mario = new Mario(this, new Position(0, Game.DIM_Y-3));
		gameObjects.addItem(this.mario);
		gameObjects.addItem(new Exit_door(this, new Position(Game.DIM_X-1, Game.DIM_Y-3)));
		
	}
	
	private void initLevel1() {
		
		gameObjects = new GameObjectContainer();
		for(int col = 0; col < 15; col++) {
	
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
		gameObjects.addItem(new Goomba(this, new Position(19, 0)));
		gameObjects.addItem(new Goomba(this, new Position(6, 4)));
		gameObjects.addItem(new Goomba(this, new Position(6, 12)));
		gameObjects.addItem(new Goomba(this, new Position(8, 12)));
		gameObjects.addItem(new Goomba(this, new Position(10, 10)));
		gameObjects.addItem(new Goomba(this, new Position(11, 12)));
		gameObjects.addItem(new Goomba(this, new Position(14, 12)));
		
		gameObjects.addItem(new Exit_door(this, new Position(Game.DIM_X-1, Game.DIM_Y-3)));
		
	}

	private void initLevel2() {
		
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
	
			// 3. Personajes
	
			this.mario = new Mario(this, new Position(0, Game.DIM_Y-3));
			
			gameObjects.addItem(this.mario);
			
			gameObjects.addItem(new Goomba(this, new Position(19, 0)));
	
			gameObjects.addItem(new Goomba(this, new Position(6, 4)));
	
			gameObjects.addItem(new Goomba(this, new Position(6, 12)));
	
			gameObjects.addItem(new Goomba(this, new Position(8, 12)));
	
			gameObjects.addItem(new Goomba(this, new Position(10, 10)));
	
			gameObjects.addItem(new Goomba(this, new Position(11, 12)));
	
			gameObjects.addItem(new Goomba(this, new Position(14, 12)));
	
			//Objetos nuevos (He cambiado córdenadas no sé si esté bien
	
			gameObjects.addItem(new Box(this, new Position(4,9)));
	
			gameObjects.addItem(new Mushroom(this, new Position(8,12)));
	
			gameObjects.addItem(new Mushroom(this, new Position(20,2)));
		
			gameObjects.addItem(new Exit_door(this, new Position(Game.DIM_X-1, Game.DIM_Y-3)));
	
		}

	private void initLevel_vacío() {
		gameObjects = new GameObjectContainer();
	}
	
//----------------GameModel-----------------------

	 @Override
	 public void update() {
		 
		 if (finished || lives <= 0) return;

		 remainingTime--;
		 if (remainingTime <= 0) {
		    	
			 marioMuere();
			 return;		        
		 }
		    
		 gameObjects.update(this);		  
		   		    			
		 gameObjects.removeDead();
		 
	 }
	 
	@Override
    public void reset(int level) throws GameModelException { 
        if (level != 0 && level != 1 && level != 2 && level != -1) {
            throw new GameModelException(Messages.INVALID_LEVEL_NUMBER);
        }

        this.fileloader = null; // Olvidamos el fichero cargado
        this.level = level;     
//        this.lives = 3;
//        this.points = 0;
	    //this.remainingTime = 100;
        
        if (this.level == -1) {
            this.lives = 3;
            this.points = 0;
        }
        
	    this.finished = false;
	    this.win = false;
	    this.gameObjects = new GameObjectContainer();
	    
	    initLevel(level);
	}
	 	   
	@Override 
	public void reset() throws GameModelException {
	     
		this.remainingTime = 100;
		this.finished = false;
	    this.win = false;
	    this.gameObjects = new GameObjectContainer();
	    
	    //Reset de fichero en caso de que proceda
		if (this.fileloader != null) {
		
			//establecemos estado
		    this.remainingTime = fileloader.getRemainingTime();
		    this.points = fileloader.getPoints();
		    this.lives = fileloader.getLives();
		    
		    //establecemos objetos + mario
		    this.mario = fileloader.getMario();
		    this.gameObjects.addItem(this.mario);		    
		    for (GameObject obj : fileloader.getObjects()) {
		        this.gameObjects.addItem(obj);
		    }	            	           
		}
		//sino, resetea el nivel actual (si es -1 no se mantiene el estado)
		else {		   
			
			if (this.level == -1) {
                this.lives = 3;
                this.points = 0;
            }
			initLevel(this.level);
		}
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
		this.gameObjects.SetAction(act);
	}
	 
	 @Override
	 public void AddObjectToContainer() {		 
		 gameObjects.updateAdd(this);
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

		int nextLives = lives - 1;

		if (nextLives <= 0) {
			this.lives = 0;
			finished = true;
			win = false;
		} else {

			try {

				reset();
				
				this.lives = nextLives; //Arreglamos el error de las vidas				
				
			} catch (GameModelException e) {
				//Necesario este try-catch pues reset lanza excepciones
				//No obstante, en teoría, nunca va a pasar que lance la excepción pues ya se pudo
				//cargar anteriormente
			}
		}
	}
	
	@Override
	public void doInteraction(GameItem other) {
		gameObjects.doInteraction(other);
	}

	@Override
	public void addObject(String[] WORDS) throws OffBoardException, ObjectParseException {		
     
		GameObject obj = this.mario.parse(WORDS, this);
        		
		if (obj != null) 
			this.mario=(Mario) obj;
		
		else 		
			obj = GameObjectFactory.parse(WORDS,this);        
        
		gameObjects.addItem(obj);      
	}

	@Override
	public void addMushroom(Mushroom mushroom) {
	    this.gameObjects.addLater(mushroom);   
	}

	
	@Override
	public void save(String fileName) throws GameModelException {
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            
            out.println(remainingTime + " " + points + " " + lives);

            out.println(gameObjects.stringifyObjects()); //serializacion de los objetos
            
		} catch (IOException e) {
            throw new GameModelException(Messages.WRITE_ERROR.formatted(fileName) + e.getMessage());
        }
	}	
	
	@Override
    public void load(String fileName) throws GameLoadException {
		
		//creamos la conf con el fichero, si falla lanza excepción y no altera el juego
        GameConfiguration config = new FileGameConfiguration(fileName, this);

        this.fileloader = config;
              
        try {
            reset(); // Esto cargará datos desde this.fileloader
        } catch (GameModelException e) {
            throw new GameLoadException(Messages.INVALID_FILE_CONFIGURATION.formatted(fileName), e);
        }
    }

}