package Chess.pieces;

import Chess.board.Board;
import Chess.board.Square;
import Chess.player.Color;

public class King extends Piece {
	public King(Color color) {
		super(color);
	}

	public boolean isValidMove(Board board, Square from, Square to) {
		int dr = Math.abs(from.getRow() - to.getRow());
		int dc = Math.abs(from.getCol() - to.getCol());
		return (dr <= 1 && dc <= 1);
	}
}
