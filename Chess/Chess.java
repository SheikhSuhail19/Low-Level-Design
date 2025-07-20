package Chess;// Complete Chess Game LLD in Java

import Chess.board.Board;
import Chess.board.GameStatus;
import Chess.board.Move;
import Chess.board.Square;
import Chess.pieces.Piece;
import Chess.player.Color;
import Chess.player.Player;

import java.util.Stack;

public class Chess {
	private final Board board;
	private final Player whitePlayer;
	private final Player blackPlayer;
	private Color currentTurn;
	private GameStatus status;
	private final Stack<Move> moveHistory;

	public Chess(Player white, Player black) {
		this.whitePlayer = white;
		this.blackPlayer = black;
		this.currentTurn = Color.WHITE;
		this.status = GameStatus.IN_PROGRESS;
		this.board = new Board();
		this.moveHistory = new Stack<>();
		board.initialize();
	}

	public boolean makeMove(Move move) {
		if (!move.getPiece().isValidMove(board, move.getFrom(), move.getTo())) return false;
		if (isSelfCheck(move)) return false;

		Piece captured = board.getPieceAt(move.getTo());
		move.setCapturedPiece(captured);
		move.getPiece().setHasMoved(true);

		// Special Moves
		if (move.isCastlingMove()) {
			handleCastling(move);
		} else if (move.isEnPassantMove()) {
			handleEnPassant(move);
		} else if (move.isPromotion()) {
			handlePromotion(move);
		} else {
			board.movePiece(move.getFrom(), move.getTo());
		}

		moveHistory.push(move);
		switchTurn();
		updateStatus();
		return true;
	}

	private void handleCastling(Move move) {
		// Simplified version (no validation here)
		board.movePiece(move.getFrom(), move.getTo());
		if (move.getTo().getCol() == 6) {
			board.movePiece(board.getSquare(move.getTo().getRow(), 7), board.getSquare(move.getTo().getRow(), 5));
		} else {
			board.movePiece(board.getSquare(move.getTo().getRow(), 0), board.getSquare(move.getTo().getRow(), 3));
		}
	}

	private void handleEnPassant(Move move) {
		int direction = move.getPiece().getColor() == Color.WHITE ? 1 : -1;
		Square capturedPawnSquare = board.getSquare(move.getTo().getRow() - direction, move.getTo().getCol());
		board.setPieceAt(capturedPawnSquare, null);
		board.movePiece(move.getFrom(), move.getTo());
	}

	private void handlePromotion(Move move) {
		board.movePiece(move.getFrom(), move.getTo());
		board.setPieceAt(move.getTo(), move.getPromotionPiece());
	}

	private boolean isSelfCheck(Move move) {
		Board clone = board.clone();
		clone.movePiece(move.getFrom(), move.getTo());
		return clone.isInCheck(currentTurn);
	}

	private void switchTurn() {
		currentTurn = (currentTurn == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private void updateStatus() {
		if (board.isInCheck(currentTurn)) {
			if (!board.canAnyMoveSaveKing(currentTurn)) {
				status = GameStatus.CHECKMATE;
			} else {
				status = GameStatus.CHECK;
			}
		} else if (!board.hasAnyValidMoves(currentTurn)) {
			status = GameStatus.STALEMATE;
		} else {
			status = GameStatus.IN_PROGRESS;
		}
	}
}

