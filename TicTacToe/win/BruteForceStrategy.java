package TicTacToe.win;

import TicTacToe.board.Board;
import TicTacToe.board.Move;
import TicTacToe.board.Symbol;
import TicTacToe.player.Player;

// Brute-force strategy: checks entire row, column, and diagonals every time
public class BruteForceStrategy implements WinningStrategy {
	private int size;

	@Override
	public void reset(int size) {
		this.size = size;
	}

	@Override
	public boolean checkWinner(Board board, Player player, Move move) {
		Symbol symbol = player.getSymbol();
		int row = move.row();
		int col = move.col();

		boolean rowWin = true, colWin = true, diagWin = true, antiDiagWin = true;

		for (int i = 0; i < size; i++) {
			if (board.getCell(row, i).getPlayer() == null || board.getCell(row, i).getPlayer().getSymbol() != symbol)
				rowWin = false;
			if (board.getCell(i, col).getPlayer() == null || board.getCell(i, col).getPlayer().getSymbol() != symbol)
				colWin = false;
			if (board.getCell(i, i).getPlayer() == null || board.getCell(i, i).getPlayer().getSymbol() != symbol)
				diagWin = false;
			if (board.getCell(i, size - i - 1).getPlayer() == null || board.getCell(i, size - i - 1).getPlayer().getSymbol() != symbol)
				antiDiagWin = false;
		}

		return rowWin || colWin || diagWin || antiDiagWin;
	}
}
