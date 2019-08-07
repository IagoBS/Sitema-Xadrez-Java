package Chess.pieces;

import Chess.ChessPiece;
import Chess.Color;
import bordgame.Board;
import bordgame.Position;

public class Piao extends ChessPiece {
public Piao(Board bord, Color color ) {
	super(bord, color);
}
@Override
public String toString() {
	return "P";
}

@Override
public boolean[][] possibilimoves() {
	boolean[][] mat = new boolean[getBord().getRow()][getBord().getColumns()];
	Position p = new Position(0, 0);
	if (getColor() == Color.WHITE) {
		p.setValues(position.getLinha() -1, position.getColuna());
		if(getBord().positionExists(p) && !getBord().ThereIsAPice(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			
		}
		p.setValues(position.getLinha() -2, position.getColuna());
		Position p2 = new Position(position.getLinha() -1, position.getColuna());
		if(getBord().positionExists(p) && !getBord().ThereIsAPice(p) && getBord().positionExists(p2) && !getBord().ThereIsAPice(p2) && getMovecout() == 0) {
			mat[p.getLinha()][p.getColuna()] = true;	
		}
		p.setValues(position.getLinha() -1, position.getColuna() -1);
		if(getBord().positionExists(p) && !getBord().ThereIsAPice(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setValues(position.getLinha() -1, position.getColuna() +1);
		if(getBord().positionExists(p) && !getBord().ThereIsAPice(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
	}
	else {
		p.setValues(position.getLinha() +1, position.getColuna());
		if(getBord().positionExists(p) && !getBord().ThereIsAPice(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			
		}
		p.setValues(position.getLinha() +2, position.getColuna());
		Position p2 = new Position(position.getLinha() -1, position.getColuna());
		if(getBord().positionExists(p) && !getBord().ThereIsAPice(p) && getBord().positionExists(p2) && !getBord().ThereIsAPice(p2) && getMovecout() == 0) {
			mat[p.getLinha()][p.getColuna()] = true;	
		}
		p.setValues(position.getLinha() +1, position.getColuna() -1);
		if(getBord().positionExists(p) && !getBord().ThereIsAPice(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setValues(position.getLinha() +1, position.getColuna() +1);
		if(getBord().positionExists(p) && !getBord().ThereIsAPice(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
	}
	return null;
}
}
