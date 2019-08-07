package Chess.pieces;

import Chess.ChessPiece;
import Chess.Color;
import bordgame.Board;
import bordgame.Position;

public class Cavalo extends ChessPiece {
public  Cavalo(Board bord, Color color) {
	super(bord, color);
}


public String toString() {
	return "C";
}
private boolean canmove(Position position) {
	ChessPiece p = (ChessPiece)getBord().piece(position);
	return p == null || p.getColor() != getColor();
}
@Override
public boolean[][] possibilimoves() {
	boolean[][] mat = new boolean[getBord().getRow()][getBord().getColumns()];
	Position p = new Position(0, 0);
	
		p.setValues(position.getLinha() -1 , position.getColuna()-2);
	if(getBord().positionExists(p) && canmove(p)) {
		mat[p.getLinha()][p.getColuna()] = true;
	}
	 
			p.setValues(position.getLinha() -2 , position.getColuna()-1);
			if(getBord().positionExists(p) && canmove(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			
			
		
			p.setValues(position.getLinha() -2  , position.getColuna() +1);
			if(getBord().positionExists(p) && canmove(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
		 
			p.setValues(position.getLinha() -1 , position.getColuna() +2);
			if(getBord().positionExists(p) && canmove(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			
			
			//sw
			p.setValues(position.getLinha() +1 , position.getColuna() +2);
			if(getBord().positionExists(p) && canmove(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//se
			p.setValues(position.getLinha() +2 , position.getColuna() +1);
			if(getBord().positionExists(p) && canmove(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
	return mat;
}

}
