import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Chess.ChessException;
import Chess.ChessMattch;
import Chess.ChessPiece;
import Chess.ChessPosition;

public class Programa {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		ChessMattch chesmattch = new ChessMattch();
		List<ChessPiece> captured = new ArrayList<>();
		
		 
		while (!chesmattch.getCheckamat()) {
			try {
				System.out.flush();
				UI.printMatch(chesmattch, captured);
				System.out.println();
				System.out.println("Source ");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibilymoves = chesmattch.possibilymoves(source);
				System.out.flush();
				UI.printBoard(chesmattch.getpieces(), possibilymoves);
			System.out.println();
			System.out.println("Target ");
			ChessPosition target = UI.readChessPosition(sc);
			
			
			
			ChessPiece capturePiece = chesmattch.performaceChessMove(source, target);
			if(capturePiece != null) {
				captured.add(capturePiece);
			}
			
			
			}
			catch(ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
		
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			
		}
		UI.clearScreen();
		UI.printMatch(chesmattch, captured);
		System.out.flush();
		}
	}	

	


