//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic;

import tp1.exception.PositionParseException;

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
	
//	public Position translateMario(Action a) {
//	    switch (a) {
//	        case LEFT:   
//	            return new Position(this.row - 1, this.col);
//	        case RIGHT:
//	            return new Position(this.row + 1, this.col);
//	        case UP:
//	            return new Position(this.row, this.col - 1);
//	        case DOWN:
//	            return new Position(this.row, this.col + 1);
//	        case STOP:
//	        default:
//	            return new Position(this.row, this.col);
//	    }
//	}
	
		
	public Position translate(Position delta) {   
        return new Position(this.row + delta.row, this.col + delta.col); 
    }

//	public static Position parse(String s){
//
//        s = s.trim();
//        s = s.replace("(", "").replace(")", "");
//        String[] parts = s.split(",");
//        int row = Integer.parseInt(parts[0]);
//        int col = Integer.parseInt(parts[1]);
//        return new Position(col, row);
//
//    }	//excepcion numerica??
	
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
            return new Position(col, row);
        } catch (NumberFormatException e) {
            throw new PositionParseException("Invalid position coordinates: " + s, e);
        }

    }	//excepcion numerica??
	
	@Override
    public String toString() { //podríamos usar stringify, pero queda más clean en esta clase con toString
        return "(%d,%d)".formatted(col, row); //recoge el formato deseado para el fichero
    }

	
	
}