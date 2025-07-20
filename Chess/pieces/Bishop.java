package Chess.pieces;

import Chess.board.Board;
import Chess.board.Square;
import Chess.player.Color;

public class Bishop extends Piece {
	public Bishop(Color color) {
		super(color);
	}

	public boolean isValidMove(Board board, Square from, Square to) {
		return Math.abs(from.getRow() - to.getRow()) == Math.abs(from.getCol() - to.getCol());
	}
}
