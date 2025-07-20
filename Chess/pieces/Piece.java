package Chess.pieces;

import Chess.board.Board;
import Chess.board.Square;
import Chess.player.Color;

public abstract class Piece {
	protected final Color color;
	private boolean hasMoved = false;

	public Piece(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setHasMoved(boolean moved) {
		this.hasMoved = moved;
	}

	public boolean hasMoved() {
		return hasMoved;
	}

	public abstract boolean isValidMove(Board board, Square from, Square to);
}
