package Chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Chess.pieces.Bispo;
import Chess.pieces.Cavalo;
import Chess.pieces.King;
import Chess.pieces.Piao;
import Chess.pieces.Rainha;
import Chess.pieces.Rook;
import bordgame.Board;
import bordgame.Piece;
import bordgame.Position;


public class ChessMattch {
private Board bord;
private int turn;
private Color currentplayer; 
private List<Piece> piecesonthebord = new ArrayList<>();
private List<Piece> capturedpieces = new ArrayList<>();
private boolean check;
private boolean checkamat;

public boolean getCheckamat() {
	return checkamat;
}

public ChessMattch() {
	bord = new Board(8, 8);
	turn = 1;
	currentplayer = Color.WHITE;
	
	initialSetup();
}

public ChessPiece[][] getpieces() {
	ChessPiece[][] mat = new ChessPiece[bord.getRow()][bord.getColumns()];
	for(int i=0; i<bord.getRow(); i++) {
		for(int j=0; j<bord.getColumns(); j++ ) {
			mat[i][j] =(ChessPiece) bord.piece(i, j);
			
		}
	}
	return mat;
	
}


public int getTurn() {
	return turn;
}
private Color opponent(Color color) {
	return (color == Color.WHITE) ? Color.BLACK: Color.WHITE;
}
 
private ChessPiece king(Color color) {
	List<Piece> list = piecesonthebord.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
	for(Piece p: list) {
		if(p instanceof King) {
			return (ChessPiece)p;
		} 
	}
	throw new IllegalStateException("Não existe o rei da cor" + color + "no tabuleiro");
	
	
}
private boolean testCheck(Color color) {
	Position kingposition = king(color).getChessPosition().toposition();
	List<Piece> opponnentePieces =  piecesonthebord.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
	for(Piece p: opponnentePieces) {
		boolean[][]mat = p.possibilimoves();
		if(mat [kingposition.getLinha()] [kingposition.getColuna()]) {
			return true;
		}
	}
	return false;
}




public Color getCurrentplayer() {
	return currentplayer;
}

public boolean[][] possibilymoves(ChessPosition sourceposition) {
	Position position = sourceposition.toposition();
	validatesourceposition(position);
	return bord.piece(position).possibilimoves();
}

public  ChessPiece performaceChessMove(ChessPosition sourceposition, ChessPosition targetposition) {
	Position source = sourceposition.toposition();
	Position target = targetposition.toposition();
	validatesourceposition(source);
	valideteTargetPosition(source, target);
	Piece capturepiece = makemove(source, target);
	if(testCheck(currentplayer)) {
		undoMove(source, target, (Piece) capturedpieces);
		throw new ChessException("vocẽ não pode se colocar em check");
	}
	check = (testCheck(opponent(currentplayer))) ? true: false;
	if(testcheckMate(opponent(currentplayer))) {
		checkamat = true;
	} else {
		nextTurn();
	}
	nextTurn();
return (ChessPiece)capturepiece;
	
}

public boolean getCheck() {
	return check;
}

private Piece makemove(Position source, Position target) {
	ChessPiece p = (ChessPiece)bord.removePiece(source);
	p.incresecout();
	Piece capturePiece = bord.removePiece(target);
	bord.placepiece(p, target);
	
	if(capturePiece != null) {
		piecesonthebord.remove(capturePiece);
	}
	//#especial move
	if(p instanceof King && target.getColuna() == source.getColuna()+2) {
		Position sourcet = new Position(source.getLinha(), source.getColuna()+3);
		Position targett = new Position(source.getLinha(), source.getColuna()+1);
		ChessPiece rook = (ChessPiece) bord.removePiece(sourcet);
		bord.placepiece(rook, targett);
		rook.incresecout();
		
	}
	if(p instanceof King && target.getColuna() == source.getColuna()+2) {
		Position sourcet = new Position(source.getLinha(), source.getColuna()-4);
		Position targett = new Position(source.getLinha(), source.getColuna()-1);
		ChessPiece rook = (ChessPiece) bord.removePiece(sourcet);
		bord.placepiece(rook, targett);
		rook.incresecout();
		
	}
	return (ChessPiece)capturePiece;
}
private void undoMove(Position source, Position target, Piece capturedPiece) {
	ChessPiece p = (ChessPiece)bord.removePiece(target);
	p.decrementcout();
	bord.placepiece(p, source);
	if(capturedPiece != null) {
		bord.placepiece(capturedPiece, target);
		capturedpieces.remove(capturedPiece);
		piecesonthebord.add(capturedPiece);
		}
	if(p instanceof King && target.getColuna() == source.getColuna()+2) {
		Position sourcet = new Position(source.getLinha(), source.getColuna()+3);
		Position targett = new Position(source.getLinha(), source.getColuna()+1);
		ChessPiece rook = (ChessPiece) bord.removePiece(target);
		bord.placepiece(rook, source);
		rook.decrementcout();		
	}
	if(p instanceof King && target.getColuna() == source.getColuna()-2) {
		Position sourcet = new Position(source.getLinha(), source.getColuna()-4);
		Position targett = new Position(source.getLinha(), source.getColuna()-1);
		ChessPiece rook = (ChessPiece) bord.removePiece(targett);
		bord.placepiece(rook, targett);
		rook.decrementcout();		
	}
}



private void validatesourceposition(Position position) {
	if (!bord.ThereIsAPice(position)) {
		throw new ChessException("There is no piece on source position");
	}
	if (currentplayer != ((ChessPiece)bord.piece(position)).getColor()) {
		throw new ChessException("The chosen piece is not yours");
	}
	if (!bord.piece(position).istherepossibilymoves()) {
		throw new ChessException("There is no possible moves for the chosen piece");
	}
}

private void nextTurn() {
	turn++;
	currentplayer = (currentplayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
}




private void valideteTargetPosition(Position source, Position target) {
	if(!bord.piece(source).possibilimoves(target)) {
		throw new ChessException("A peça escolhida não pode se mover para a posição de destino");
		}
	
	}
private boolean testcheckMate(Color color) {
	if(!testCheck(color) ) {
		return false;
	}
	List<Piece> list = piecesonthebord.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
	
	for(Piece p : list) {
		boolean[][] mat = p.possibilimoves();
		for(int i=0; i<bord.getRow(); i++) {
			for(int j=0; j<bord.getColumns(); j++) {
				if(mat[i][j]) {
					Position source = ((ChessPiece)p).getChessPosition().toposition();
					Position target = new Position(i, j);
					Piece capturedpiece  = makemove(source, target);
					boolean testcheck = testCheck(color);
					undoMove(source, target, capturedpiece);
					if(!testcheck) {
						return false;
					}
					
				}
			}
		}
	}
	return true;
}







private void placenewpiece(char column, int row, ChessPiece piece) {
	bord.placepiece(piece, new ChessPosition(column, row).toposition());
	piecesonthebord.add(piece);
}
private void initialSetup() {
	  placenewpiece('a', 1, new Rook(bord, Color.WHITE));
	  placenewpiece('b', 1, new Cavalo(bord, Color.WHITE));
	  placenewpiece('c', 1, new Bispo(bord, Color.WHITE));
      placenewpiece('d', 1, new Rainha(bord, Color.WHITE));
      placenewpiece('e', 1, new King(bord, Color.WHITE, this));
      placenewpiece('f', 1, new Bispo(bord, Color.WHITE));
      placenewpiece('g', 1, new Cavalo(bord, Color.WHITE));
      placenewpiece('h', 1, new Rook(bord, Color.WHITE));
      placenewpiece('a', 2, new Piao(bord, Color.WHITE));
      placenewpiece('b', 2, new Piao(bord, Color.WHITE));
      placenewpiece('c', 2, new Piao(bord, Color.WHITE));
      placenewpiece('d', 2, new Piao(bord, Color.WHITE));
      placenewpiece('e', 2, new Piao(bord, Color.WHITE));
      placenewpiece('f', 2, new Piao(bord, Color.WHITE));
      placenewpiece('g', 2, new Piao(bord, Color.WHITE));
      placenewpiece('h', 2, new Piao(bord, Color.WHITE));

      placenewpiece('a', 8, new Rook(bord, Color.BLACK));
      placenewpiece('b', 8, new Cavalo(bord, Color.BLACK));
      placenewpiece('c', 8, new Bispo(bord, Color.BLACK));
      placenewpiece('d', 8, new Rainha(bord, Color.BLACK));
      placenewpiece('e', 8, new King(bord, Color.BLACK, this));
      placenewpiece('f', 8, new Bispo(bord, Color.BLACK));
      placenewpiece('g', 8, new Cavalo(bord, Color.BLACK));
      placenewpiece('h', 8, new Rook(bord, Color.BLACK));
      placenewpiece('a', 7, new Piao(bord, Color.BLACK));
      placenewpiece('b', 7, new Piao(bord, Color.BLACK));
      placenewpiece('c', 7, new Piao(bord, Color.BLACK));
      placenewpiece('d', 7, new Piao(bord, Color.BLACK));
      placenewpiece('e', 7, new Piao(bord, Color.BLACK));
      placenewpiece('f', 7, new Piao(bord, Color.BLACK));
      placenewpiece('g', 7, new Piao(bord, Color.BLACK));
      placenewpiece('h', 7, new Piao(bord, Color.BLACK));
	
}


}
