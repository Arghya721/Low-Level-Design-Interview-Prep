package main

import (
	"fmt"
	"tictactoe/player"
	"tictactoe/shape"
	"tictactoe/tictac"
)

// Main function, the entry point of the Go program.
func main() {
	// Create two player instances.
	player1 := player.NewPlayer("Arghya", shape.X)
	player2 := player.NewPlayer("Ayush", shape.O)

	// Get a singleton instance of the TicTacToe game.
	// We'll pass a slice of players.
	game := tictac.GetTicTacToeInstance(3, []*player.Player{player1, player2})

	// Simulate a winning game for player1 (X) in the first column.
	moves := []struct {
		x, y int
		p    *player.Player
	}{
		{0, 0, player1}, // X
		{0, 1, player2}, // O
		{1, 0, player1}, // X
		{1, 1, player2}, // O
		{2, 0, player1}, // X -> This move completes the win for player1
	}

	for _, move := range moves {
		err := game.PlayMove(move.x, move.y, move.p)
		if err != nil {
			fmt.Println(err.Error())
			return
		}
	}
}
