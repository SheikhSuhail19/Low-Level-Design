package Chess.pieces;

import Chess.board.Board;
import Chess.board.Square;
import Chess.player.Color;

public class Rook extends Piece {
	public Rook(Color color) {
		super(color);
	}

	public boolean isValidMove(Board board, Square from, Square to) {
		return from.getRow() == to.getRow() || from.getCol() == to.getCol();
	}
}
