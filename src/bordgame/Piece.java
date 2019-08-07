package bordgame;

public abstract class Piece {
protected Position position;
private Board bord;
public Piece(Board bord) {
	this.bord = bord;
}
protected Board getBord() {
	return bord;
}
public abstract boolean[][] possibilimoves();
	public boolean possibilimoves(Position position) {
		return possibilimoves()[position.getLinha()][position.getColuna()];
	}
public boolean istherepossibilymoves() {
	boolean[][] mat = possibilimoves();
	for(int i=0; i<mat.length; i++) {
		for(int j=0; i<mat.length; j++) {
		if(mat[i][j]) {
			return true;
		}
		}
		}
	return false;
	}
	
}

