package TicTacToe.win;

import TicTacToe.board.Board;
import TicTacToe.board.Move;
import TicTacToe.player.Player;

// Winning strategy interface
public interface WinningStrategy {
	boolean checkWinner(Board board, Player player, Move move);

	void reset(int size); // for new games
}
