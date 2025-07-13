package TicTacToe.player;

import TicTacToe.board.Board;
import TicTacToe.board.Move;
import TicTacToe.board.Symbol;

import java.util.Scanner;

public class HumanPlayer extends Player {
	private final Scanner scanner = new Scanner(System.in);

	public HumanPlayer(String name, Symbol symbol) {
		super(name, symbol);
	}

	@Override
	public Move makeMove(Board board) {
		System.out.println(getName() + "'s turn. Enter a number from 1 to " + (board.getSize() * board.getSize()) + ":");
		int input = scanner.nextInt();
		int row = (input - 1) / board.getSize();
		int col = (input - 1) % board.getSize();
		return new Move(row, col, this);
	}
}
