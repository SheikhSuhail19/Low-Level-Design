package TicTacToe.player;

import TicTacToe.board.Board;
import TicTacToe.board.Move;
import TicTacToe.board.Symbol;

// Abstract player class
public abstract class Player {
	private final String name;
	private final Symbol symbol;

	public Player(String name, Symbol symbol) {
		this.name = name;
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public abstract Move makeMove(Board board);
}
