package TicTacToe.board;

// Board class manages the game grid
public class Board {
	private final int size;
	private final Cell[][] cells;

	public Board(int size) {
		this.size = size;
		cells = new Cell[size][size];
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				cells[i][j] = new Cell(i, j);
	}

	public boolean isFull() {
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (cells[i][j].isEmpty()) return false;
		return true;
	}

	public boolean makeMove(Move move) {
		if (move.row() < 0 || move.row() >= size || move.col() < 0 || move.col() >= size) {
			throw new IllegalArgumentException("Move out of bounds");
		}
		Cell cell = cells[move.row()][move.col()];
		if (!cell.isEmpty()) return false;
		cell.setPlayer(move.player());
		return true;
	}

	public void printBoard() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Symbol symbol = cells[i][j].getPlayer() == null ? null : cells[i][j].getPlayer().getSymbol();
				System.out.print((symbol == null ? "-" : symbol) + " ");
			}
			System.out.println();
		}
	}

	public int getSize() {
		return size;
	}

	public Cell getCell(int row, int col) {
		return cells[row][col];
	}
}
