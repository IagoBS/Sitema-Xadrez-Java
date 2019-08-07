package Chess.pieces;

import Chess.ChessPiece;
import Chess.Color;
import bordgame.Board;
import bordgame.Position;

public class Rook extends ChessPiece {

	public Rook(Board bord, Color color) {
		super(bord, color);
	}
	
	@Override
	public String toString() {
		return "R";
	}
	
	
	
	
	
	
	
	@Override
	public boolean[][] possibilimoves() {
		boolean[][] mat = new boolean[getBord().getRow()][getBord().getColumns()];
		
		
		Position p= new Position(0, 0);
		//above
		p.setValues(position.getLinha() -1, position.getColuna());
		while(getBord().positionExists(p) && !getBord().ThereIsAPice(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() -1);
		}
		if(getBord().positionExists(p) && isthereopponentpiece(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		//left
		p.setValues(position.getLinha() , position.getColuna() -1);
		while(getBord().positionExists(p) && !getBord().ThereIsAPice(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() -1);
		}
		if(getBord().positionExists(p) && isthereopponentpiece(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		//right
		
		p.setValues(position.getLinha() , position.getColuna() +1);
		while(getBord().positionExists(p) && !getBord().ThereIsAPice(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() +1);
		}
		if(getBord().positionExists(p) && isthereopponentpiece(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//below
				p.setValues(position.getLinha() +1, position.getColuna());
				while(getBord().positionExists(p) && !getBord().ThereIsAPice(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
					p.setLinha(p.getLinha() +1);
				}
				if(getBord().positionExists(p) && isthereopponentpiece(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
		return mat;
	}

	

}
