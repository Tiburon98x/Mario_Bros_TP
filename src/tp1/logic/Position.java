//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic;


public class Position {
	
	private int col;
	private int row;
	
	
	
	public Position(int row2, int col2) {

		this.row = row2;
		this.col = col2;
		
	}
	

	
	public boolean isInPosition(Position delta) {
		
	        return row >= 0 && row < delta.row && col >= 0 && col < delta.col;
	        
	    }
	
	
	
	@Override
	public boolean equals(Object obj) {
				
		Position pos = (Position) obj; 
		return this.col == pos.col && this.row == pos.row;
				
	}
	
	
	
	public Position translateMario(Action a) {	
		
	    return new Position(this.row + a.getX(), this.col + a.getY());
	     
	}
	

	
	public Position translate(Position delta) {   
		
        return new Position(this.row + delta.row, this.col + delta.col);
        
    }
	

	
}