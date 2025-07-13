package TicTacToe.board;

import TicTacToe.player.Player;

// Move encapsulates the details of a player's move
public record Move(int row, int col, Player player) {
}
