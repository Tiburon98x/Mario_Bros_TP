//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic;


public class Position {
	
	private final int col;
	private final int row;
		
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

	public static Position parse(String s) {

        // El formato esperado es "(fila,columna)"

        s = s.trim();

        s = s.replace("(", "").replace(")", "");

        String[] parts = s.split(",");

        int row = Integer.parseInt(parts[0]);

        int col = Integer.parseInt(parts[1]);

        return new Position(row, col);

    }
	
}