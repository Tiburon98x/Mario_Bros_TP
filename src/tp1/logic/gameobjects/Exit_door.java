//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.view.Messages;


public class Exit_door {

		private Position pos;


		
		public Exit_door(Position pos) {
			
			this.pos = pos;
	
		}
		
		
		
		public Position getPosition() {
			
	        return pos;
	        
	    } 
		
		
		
		public String getIcon() {
			
			return Messages.EXIT_DOOR;
			
		}
		
		
		
		public boolean isInPos(Position pos){
			
			return (this.pos.equals(pos));
			
		}

		
		
}