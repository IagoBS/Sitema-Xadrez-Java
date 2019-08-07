package bordgame;

public class Board {
private Integer row;
private Integer columns;
private Piece[][] pieces;
public Board(Integer row, Integer columns) {
	if(row <1 ||  columns<1) {
		throw new BordExcepition("Erro criando tabuleiro, é necessario que aja 1 linha e uma coluna");
	}
	this.row = row;
	this.columns = columns;
	pieces = new Piece[row][columns];
}
public Integer getRow() {
	return row;
}
public void setRow(Integer row) {
	this.row = row;
}
public Integer getColumns() {
	return columns;
}
public void setColumns(Integer columns) {
	this.columns = columns;
}
public Piece piece(Integer row, Integer columns) {
	if (!positionExists(row, columns)) {
		throw new BordExcepition("Position not on the bord");
	}
return pieces[row][columns];
}
public Piece piece(Position position) {
	return pieces[position.getLinha()][position.getColuna()];
	
}
public void placepiece(Piece piece, Position position) {
	if(ThereIsAPice(position)) {
		throw new BordExcepition("There is alredy a piece on position "+ position);
		
	}
	pieces[position.getLinha()][position.getColuna()] = piece;
	piece.position = position;
	
}

public Piece removePiece(Position position) {
	if(!positionExists(position)) {
		throw new BordExcepition("essa posicao não existe");
	}
	if(piece(position) == null) {
		return null;
	}
	Piece aux = piece(position);
	aux.position = null;
	pieces[position.getLinha()][position.getColuna()] = null;
	return aux;
}



private boolean positionExists(Integer rows, Integer column) {
	return  rows >= 0 && rows < row && column >=0 && column < columns;
	
}
public boolean positionExists(Position position) {
	return positionExists(position.getLinha(), position.getColuna());
	
}
public boolean ThereIsAPice(Position position) {
	
	return piece(position) != null;
	
	
}




}
