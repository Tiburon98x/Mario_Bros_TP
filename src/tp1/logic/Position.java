//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic;

import tp1.exception.PositionParseException;
import tp1.view.Messages;

public class Position {
	
	private final int col;
	private final int row;			
	
	public Position(int col, int row) {

		this.col = row;	
		this.row = col;
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
	
	public static Position parse(String s) throws PositionParseException {

		s = s.trim();
        if (!s.startsWith("(") || !s.endsWith(")")) {
             throw new PositionParseException("Invalid position format: " + s);
        }
        s = s.replace("(", "").replace(")", "");
        String[] parts = s.split(",");
        
        if (parts.length != 2) {
             throw new PositionParseException("Invalid position format: " + s);
        }
        
        try {
            int col = Integer.parseInt(parts[0].trim());
            int row = Integer.parseInt(parts[1].trim());
            return new Position(row, col);
        } catch (NumberFormatException e) {
            throw new PositionParseException(Messages.INVALID_POSITION.formatted(s), e);
        }

    }
	
	@Override
    public String toString() { //podríamos usar stringify, pero queda más clean en esta clase con toString
        return "(%d,%d)".formatted(col, row); //formato para el fichero
    }
	
}