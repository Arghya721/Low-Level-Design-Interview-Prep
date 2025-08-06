package tictac

type GameState string

const (
	DRAW        GameState = "draw"
	WIN         GameState = "win"
	IN_PROGRESS GameState = "progress"
)
