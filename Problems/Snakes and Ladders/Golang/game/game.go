package game

import (
	"snakeladders/board"
	"snakeladders/dice"
	"snakeladders/jumper"
	"snakeladders/player"
	"time"
)

type Game struct {
	Jumper  []jumper.Jumper
	Board   *board.Board
	Dice    *dice.Dice
	Players []player.Player
}

var gameIntance *Game
var queue []player.Player

func newGame(jumper []jumper.Jumper, boardSize int, players []player.Player, sides uint) *Game {

	gameIntance = &Game{
		Jumper:  jumper,
		Board:   board.NewBoard(boardSize),
		Dice:    dice.NewDice(sides),
		Players: players,
	}

	queue = make([]player.Player, 0)
	queue = append(queue, players...)
	return gameIntance
}

func GetGameInstance(jumper []jumper.Jumper, boardSize int, players []player.Player, sides uint) *Game {
	if gameIntance == nil {
		gameIntance = newGame(jumper, boardSize, players, sides)
	}
	return gameIntance
}

func (g *Game) StartGame() {

	for {
		currentPlayer := queue[0]

		// Dice roll and player movement logic
		roll := g.Dice.Roll()

		println("Current Player: ", currentPlayer.GetName(), " rolled a ", roll)
		if g.Board.IsValidMove(currentPlayer.GetCurrentPosition(), int(roll)) {
			newPos := currentPlayer.GetCurrentPosition() + int(roll)
			// check for snake or ladder
			for _, jumpVal := range g.Jumper {
				if jumpVal.GetStartPosition() == newPos {
					newPos = jumpVal.GetEndPosition()
					println("Jumping from ", jumpVal.GetStartPosition(), " to ", jumpVal.GetEndPosition())
					break
				}
			}
			// check win
			if g.Board.CheckWinner(newPos) {
				println("Winner Winner chicken dinner!! player = ", currentPlayer.GetName())
				return
			}
			currentPlayer.SetCurrentPosition(newPos)
		}
		println("Current Player: ", currentPlayer.GetName(), " Position: ", currentPlayer.GetCurrentPosition())
		// Move the current player to the back of the queue

		queue = append(queue, currentPlayer)
		queue = queue[1:]
		time.Sleep(10 * time.Millisecond) // Sleep for 500 milliseconds to simulate turn-based play

	}
}
