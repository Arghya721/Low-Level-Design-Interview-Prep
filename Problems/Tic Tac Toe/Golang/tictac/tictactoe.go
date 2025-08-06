package tictac

import (
	"errors"
	"fmt"
	"sync"
	"tictactoe/board"
	"tictactoe/player"
)

// TicTacToe represents the game logic.
type TicTacToe struct {
	board     *board.Board
	players   []*player.Player
	moves     []*player.Player
	gameState GameState
	// Mutex for concurrent access
	mu sync.Mutex
}

// Instance for the singleton pattern.
var (
	instance *TicTacToe
	once     sync.Once
)

// GetTicTacToeInstance implements the singleton pattern for TicTacToe.
func GetTicTacToeInstance(size int, players []*player.Player) *TicTacToe {
	once.Do(func() {
		instance = &TicTacToe{
			board:     board.NewBoard(size),
			players:   players,
			moves:     make([]*player.Player, 0), // Initialize an empty slice
			gameState: IN_PROGRESS,
		}
	})
	return instance
}

// GetPlayers returns the players of the game.
func (t *TicTacToe) GetPlayers() []*player.Player {
	return t.players
}

// PlayMove handles a player's move.
func (t *TicTacToe) PlayMove(x, y int, player *player.Player) error {
	// Lock to prevent concurrent moves
	t.mu.Lock()
	defer t.mu.Unlock()

	if t.gameState != IN_PROGRESS {
		return errors.New("game is already over")
	}

	// Check if it's the correct player's turn
	if len(t.moves) > 0 && t.moves[len(t.moves)-1] == player {
		return errors.New("wrong player move")
	}

	if t.board.IsValidMove(x, y) {
		t.board.PlayMove(x, y, player.Shape)
		t.moves = append(t.moves, player)
		t.board.PrintBoard()

		// Check for winning conditions
		if t.board.IsWinner(player.Shape) {
			t.gameState = WIN
			fmt.Printf("Player %s is the winner !!!\n", player.Name)
			return nil
		}

		// Check for draw condition
		if t.board.IsDraw(len(t.moves)) {
			t.gameState = DRAW
			fmt.Println("DRAW")
			return nil
		}

		return nil
	} else {
		return errors.New("invalid move")
	}
}
