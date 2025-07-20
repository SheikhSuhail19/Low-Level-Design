package Chess.pieces;

import Chess.board.Board;
import Chess.board.Square;
import Chess.player.Color;

public class Pawn extends Piece {
	public Pawn(Color color) {
		super(color);
	}

	public boolean isValidMove(Board board, Square from, Square to) {
		// Simplified pawn movement logic
		return true;
	}
}
