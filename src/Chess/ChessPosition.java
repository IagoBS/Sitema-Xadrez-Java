package Chess;

import bordgame.BordExcepition;
import bordgame.Position;

public class ChessPosition {
private char column;
private int row;
public ChessPosition(char column, int row) {
	if(column < 'a' || column > 'h' || row <1 || row>8) {
		throw new ChessException("Erro instanciando chess position");
		
	}
	this.column = column;
	this.row = row;
}
public char getColumn() {
	return column;
}

public int getRow() {
	return row;
}
protected Position toposition() {
	return new Position(8- row, column - 'a');
	
}
protected static ChessPosition fromposition(Position position) {
	return new ChessPosition((char)('a' + position.getColuna()), 8 - position.getLinha());
	
}
@Override 
public String toString() {
	return  "" + column + row;
}


}
