package Chess.board;

import Chess.pieces.Piece;

public class Move {
	private final Square from;
	private final Square to;
	private final Piece piece;
	private Piece capturedPiece;
	private boolean castlingMove;
	private boolean enPassantMove;
	private boolean promotion;
	private Piece promotionPiece;

	public Move(Square from, Square to, Piece piece) {
		this.from = from;
		this.to = to;
		this.piece = piece;
	}

	public Square getFrom() {
		return from;
	}

	public Square getTo() {
		return to;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setCapturedPiece(Piece p) {
		this.capturedPiece = p;
	}

	public boolean isCastlingMove() {
		return castlingMove;
	}

	public void setCastlingMove(boolean val) {
		this.castlingMove = val;
	}

	public boolean isEnPassantMove() {
		return enPassantMove;
	}

	public void setEnPassantMove(boolean val) {
		this.enPassantMove = val;
	}

	public boolean isPromotion() {
		return promotion;
	}

	public void setPromotion(boolean val) {
		this.promotion = val;
	}

	public Piece getPromotionPiece() {
		return promotionPiece;
	}

	public void setPromotionPiece(Piece p) {
		this.promotionPiece = p;
	}
}
