package TicTacToe;

import TicTacToe.board.Board;
import TicTacToe.board.Move;
import TicTacToe.board.Symbol;
import TicTacToe.player.HumanPlayer;
import TicTacToe.player.Player;
import TicTacToe.win.OptimizedStrategy;
import TicTacToe.win.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private final Board board;
	private final List<Player> players;
	private final WinningStrategy winningStrategy;
	private int currentPlayerIndex;

	public Game(int boardSize, List<Player> players, WinningStrategy strategy) {
		this.board = new Board(boardSize);
		this.players = players;
		this.winningStrategy = strategy;
		this.currentPlayerIndex = 0;
		this.winningStrategy.reset(boardSize);
	}

	public void start() {
		boolean isWon = false;
		board.printBoard();

		while (!board.isFull()) {
			Player currentPlayer = players.get(currentPlayerIndex);
			Move move = currentPlayer.makeMove(board);

			if (!board.makeMove(move)) {
				System.out.println("Invalid move. Try again.");
				continue;
			}

			board.printBoard();
			if (winningStrategy.checkWinner(board, currentPlayer, move)) {
				System.out.println(currentPlayer.getName() + " wins!");
				isWon = true;
				break;
			}

			currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
		}

		if (!isWon) {
			System.out.println("It's a draw!");
		}
	}

	public static void main(String[] args) {
		List<Player> players = new ArrayList<>();
		players.add(new HumanPlayer("Alice", Symbol.X));
		players.add(new HumanPlayer("Bob", Symbol.O));

		// Change to BruteForceStrategy to switch strategy
		WinningStrategy strategy = new OptimizedStrategy();

		Game game = new Game(3, players, strategy);
		game.start();
	}
}

