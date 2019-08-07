package Chess.pieces;

import Chess.ChessPiece;
import Chess.Color;
import bordgame.Board;
import bordgame.Position;

public class Bispo extends ChessPiece{
public Bispo(Board bord, Color color) {
	super(bord, color);
}

@Override
public String toString() {
	return "B";
}

@Override
public boolean[][] possibilimoves() {
	boolean[][] mat = new boolean[getBord().getRow()][getBord().getColumns()];
	
	
	Position p= new Position(0, 0);
	//nw
	p.setValues(position.getLinha() -1, position.getColuna() -1);
	while(getBord().positionExists(p) && !getBord().ThereIsAPice(p)) {
		mat[p.getLinha()][p.getColuna()] = true;
		p.setValues(p.getLinha() -1, p.getColuna() -1 );
	}
	if(getBord().positionExists(p) && isthereopponentpiece(p)) {
		mat[p.getLinha()][p.getColuna()] = true;
	}

	//ne
	p.setValues(position.getLinha() -1 , position.getColuna() +1);
	while(getBord().positionExists(p) && !getBord().ThereIsAPice(p)) {
		mat[p.getLinha()][p.getColuna()] = true;
		p.setValues(p.getLinha() -1,p.getColuna() +1 );
	}
	if(getBord().positionExists(p) && isthereopponentpiece(p)) {
		mat[p.getLinha()][p.getColuna()] = true;
	}

	//se
	
	p.setValues(position.getLinha() +1 , position.getColuna() +1);
	while(getBord().positionExists(p) && !getBord().ThereIsAPice(p)) {
		mat[p.getLinha()][p.getColuna()] = true;
		p.setValues(p.getLinha() +1 , p.getColuna() +1 );
	}
	if(getBord().positionExists(p) && isthereopponentpiece(p)) {
		mat[p.getLinha()][p.getColuna()] = true;
	}
	//so
			p.setValues(position.getLinha() +1, position.getColuna() -1 );
			while(getBord().positionExists(p) && !getBord().ThereIsAPice(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
				p.setValues(p.getLinha() +1 , p.getColuna() -1 );
			}
			if(getBord().positionExists(p) && isthereopponentpiece(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
	return mat;
}

}
