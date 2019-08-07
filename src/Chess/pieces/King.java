package Chess.pieces;

import Chess.ChessMattch;
import Chess.ChessPiece;
import Chess.Color;
import bordgame.Board;
import bordgame.Position;

public class King extends ChessPiece {

	private ChessMattch chessmath;

	public King(Board bord, Color color, ChessMattch chessmath) {
		super(bord, color);
		this.chessmath = chessmath;
	}

	private boolean testRookroque(Position position) {
		ChessPiece p = (ChessPiece) getBord().piece(position);
		return p != null && p instanceof Rook && p.getColor() == p.getColor() && p.getMovecout() == 0;
	}
// #especion move casting

	@Override
	public String toString() {
		return "K";
	}

	private boolean canmove(Position position) {
		ChessPiece p = (ChessPiece) getBord().piece(position);
		return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibilimoves() {
		boolean[][] mat = new boolean[getBord().getRow()][getBord().getColumns()];
		Position p = new Position(0, 0);

		// above
		p.setValues(position.getLinha() - 1, position.getColuna());
		if (getBord().positionExists(p) && canmove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// below
		p.setValues(position.getLinha() + 1, position.getColuna());
		if (getBord().positionExists(p) && canmove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// left
		p.setValues(position.getLinha() - 1, position.getColuna() - 1);
		if (getBord().positionExists(p) && canmove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// right
		p.setValues(position.getLinha() + 1, position.getColuna() + 1);
		if (getBord().positionExists(p) && canmove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// sw
		p.setValues(position.getLinha() + 1, position.getColuna() - 1);
		if (getBord().positionExists(p) && canmove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// se
		p.setValues(position.getLinha() + 1, position.getColuna() + 1);
		if (getBord().positionExists(p) && canmove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		if (getMovecout() == 0 && !chessmath.getCheck()) {
			// spcial move
			Position posicaodatorre1 = new Position(position.getLinha(), position.getColuna() + 3);
			if (testRookroque(posicaodatorre1)) {
				Position p1 = new Position(position.getLinha(), position.getColuna() + 1);
				Position p2 = new Position(position.getLinha(), position.getColuna() + 2);

				if (getBord().piece(p1) == null && getBord().piece(p2) == null) {
					mat[position.getLinha()][position.getColuna() + 2] = true;
				}
			}
			if (testRookroque(posicaodatorre1)) {
				Position p1 = new Position(position.getLinha(), position.getColuna() + 1);
				Position p2 = new Position(position.getLinha(), position.getColuna() + 2);

				if (getBord().piece(p1) == null && getBord().piece(p2) == null) {
					mat[position.getLinha()][position.getColuna() + 2] = true;
				}
			}
			Position posicaodatorre2 = new Position(position.getLinha(), position.getColuna() - 4);
			if (testRookroque(posicaodatorre1)) {
				Position p1 = new Position(position.getLinha(), position.getColuna() + 1);
				Position p2 = new Position(position.getLinha(), position.getColuna() + 2);

				if (getBord().piece(p1) == null && getBord().piece(p2) == null) {
					mat[position.getLinha()][position.getColuna() + 2] = true;
				}
			}
			if (testRookroque(posicaodatorre1)) {
				Position p1 = new Position(position.getLinha(), position.getColuna() - 1);
				Position p2 = new Position(position.getLinha(), position.getColuna() - 2);
				Position p3 = new Position(position.getLinha(), position.getColuna() - 3);

				if (getBord().piece(p1) == null && getBord().piece(p2) == null && getBord().piece(p3) == null) {
					mat[position.getLinha()][position.getColuna() + 2] = true;
				}
			}

		}

		return mat;
	}

}
