package Chess.pieces;

import Chess.board.Board;
import Chess.board.Square;
import Chess.player.Color;

public class Queen extends Piece {
	public Queen(Color color) {
		super(color);
	}

	public boolean isValidMove(Board board, Square from, Square to) {
		// A queen can move in any direction, Rook + Bishop combination
		return from.getRow() == to.getRow() || from.getCol() == to.getCol() || Math.abs(from.getRow() - to.getRow()) == Math.abs(from.getCol() - to.getCol());
	}
}
