package main

import (
	"snakeladders/game"
	"snakeladders/jumper"
	"snakeladders/player"
)

func main() {
	// This is the entry point of the application.
	// You can initialize the game here, create players, set up the board, etc.
	player1 := player.NewPlayer("Alice")
	player2 := player.NewPlayer("Bob")
	players := []player.Player{*player1, *player2}

	// Jumper and board setup would go here
	jumperFactory := jumper.JumperFactory{}
	snake1 := jumperFactory.NewJumperFactory("snake", 14, 7)
	snake2 := jumperFactory.NewJumperFactory("snake", 22, 3)
	snake4 := jumperFactory.NewJumperFactory("snake", 99, 1)
	snake3 := jumperFactory.NewJumperFactory("snake", 30, 11)
	ladder1 := jumperFactory.NewJumperFactory("ladder", 2, 12)
	ladder2 := jumperFactory.NewJumperFactory("ladder", 8, 18)
	ladder3 := jumperFactory.NewJumperFactory("ladder", 4, 16)
	ladder4 := jumperFactory.NewJumperFactory("ladder", 5, 15)
	ladder5 := jumperFactory.NewJumperFactory("ladder", 10, 20)

	jumpers := []jumper.Jumper{snake1, snake2, snake3, ladder1, ladder2, ladder3, ladder4, ladder5, snake4}

	game := game.GetGameInstance(jumpers, 100, players, 6)

	game.StartGame()
}
