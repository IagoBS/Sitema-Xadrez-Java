package Chess;

import bordgame.Board;
import bordgame.Piece;
import bordgame.Position;

public abstract class ChessPiece extends Piece {
private Color color;
private ChessPosition ChessPosition;
private int movecout;

public ChessPiece(Board bord, Color color) {
	super(bord);
	this.color = color;
}

public Color getColor() {
	return color;
}
protected boolean isthereopponentpiece(Position position) {
 ChessPiece p = (ChessPiece)getBord().piece(position);
 return p != null  && p.getColor() != color;
}

public ChessPosition getChessPosition() {
	return ChessPosition.fromposition(position);
}
public void incresecout() {
	movecout++;
}
public void decrementcout() {
	movecout--;
}

public int getMovecout() {
	return movecout;
}




}
