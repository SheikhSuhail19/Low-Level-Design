package TicTacToe.win;

import TicTacToe.board.Board;
import TicTacToe.board.Move;
import TicTacToe.board.Symbol;
import TicTacToe.player.Player;

import java.util.HashMap;
import java.util.Map;

public class OptimizedStrategy implements WinningStrategy {
	private final Map<Symbol, int[]> rowCount = new HashMap<>();
	private final Map<Symbol, int[]> colCount = new HashMap<>();
	private final Map<Symbol, Integer> diagCount = new HashMap<>();
	private final Map<Symbol, Integer> antiDiagCount = new HashMap<>();
	private int size;

	@Override
	public void reset(int size) {
		this.size = size;
		rowCount.clear();
		colCount.clear();
		diagCount.clear();
		antiDiagCount.clear();
	}

	/**
	 * Checks if the player has won after making a move.
	 * This method uses optimized counting to determine if a player has won.
	 * * It maintains counts for rows, columns, and both diagonals for each player.
	 * * When a player makes a move, it updates the counts for the respective row, column,
	 * * diagonal, and anti-diagonal.
	 * * If any of these counts reach the size of the board, the player is declared the winner.
	 *
	 * @param board  The game board
	 * @param player The player making the move
	 * @param move   The move made by the player
	 * @return true if the player has won, false otherwise
	 */
	@Override
	public boolean checkWinner(Board board, Player player, Move move) {
		Symbol symbol = player.getSymbol();
		int row = move.row();
		int col = move.col();

		rowCount.putIfAbsent(symbol, new int[size]);
		colCount.putIfAbsent(symbol, new int[size]);
		diagCount.putIfAbsent(symbol, 0);
		antiDiagCount.putIfAbsent(symbol, 0);

		rowCount.get(symbol)[row]++;
		colCount.get(symbol)[col]++;
		if (row == col) diagCount.put(symbol, diagCount.get(symbol) + 1);
		if (row + col == size - 1) antiDiagCount.put(symbol, antiDiagCount.get(symbol) + 1);

		return rowCount.get(symbol)[row] == size ||
				colCount.get(symbol)[col] == size ||
				diagCount.get(symbol) == size ||
				antiDiagCount.get(symbol) == size;
	}
}
