package Chess.pieces;

import Chess.board.Board;
import Chess.board.Square;
import Chess.player.Color;

public class Knight extends Piece {
	public Knight(Color color) {
		super(color);
	}

	@Override
	public boolean isValidMove(Board board, Square from, Square to) {
		int dr = Math.abs(from.getRow() - to.getRow());
		int dc = Math.abs(from.getCol() - to.getCol());
		return dr * dc == 2;
	}
}
