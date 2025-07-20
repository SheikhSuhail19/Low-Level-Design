package Chess.board;

import Chess.pieces.Bishop;
import Chess.pieces.King;
import Chess.pieces.Knight;
import Chess.pieces.Pawn;
import Chess.pieces.Piece;
import Chess.pieces.Queen;
import Chess.pieces.Rook;
import Chess.player.Color;

public class Board {
	private final Square[][] squares;

	public Board() {
		squares = new Square[8][8];
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				squares[i][j] = new Square(i, j);
	}

	public void initialize() {
		// Pawns
		for (int i = 0; i < 8; i++) {
			squares[1][i].setPiece(new Pawn(Color.BLACK));
			squares[6][i].setPiece(new Pawn(Color.WHITE));
		}
		// Rooks
		squares[0][0].setPiece(new Rook(Color.BLACK));
		squares[0][7].setPiece(new Rook(Color.BLACK));
		squares[7][0].setPiece(new Rook(Color.WHITE));
		squares[7][7].setPiece(new Rook(Color.WHITE));

		// Knights
		squares[0][1].setPiece(new Knight(Color.BLACK));
		squares[0][6].setPiece(new Knight(Color.BLACK));
		squares[7][1].setPiece(new Knight(Color.WHITE));
		squares[7][6].setPiece(new Knight(Color.WHITE));

		// Bishops
		squares[0][2].setPiece(new Bishop(Color.BLACK));
		squares[0][5].setPiece(new Bishop(Color.BLACK));
		squares[7][2].setPiece(new Bishop(Color.WHITE));
		squares[7][5].setPiece(new Bishop(Color.WHITE));

		// Queens
		squares[0][3].setPiece(new Queen(Color.BLACK));
		squares[7][3].setPiece(new Queen(Color.WHITE));

		// Kings
		squares[0][4].setPiece(new King(Color.BLACK));
		squares[7][4].setPiece(new King(Color.WHITE));
	}

	public Square getSquare(int row, int col) {
		return squares[row][col];
	}

	public Piece getPieceAt(Square square) {
		return square.getPiece();
	}

	public void setPieceAt(Square square, Piece piece) {
		square.setPiece(piece);
	}

	public void movePiece(Square from, Square to) {
		to.setPiece(from.getPiece());
		from.setPiece(null);
	}

	public boolean isInCheck(Color color) {
		// Not fully implemented here — would scan board to see if opponent can attack king
		return false;
	}

	public boolean canAnyMoveSaveKing(Color color) {
		return true; // placeholder
	}

	public boolean hasAnyValidMoves(Color color) {
		return true; // placeholder
	}

	public Board clone() {
		// Deep copy board state
		return this; // simplified for brevity
	}
}
