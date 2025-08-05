package board

type Board struct {
	size int
}

func NewBoard(size int) *Board {
	return &Board{
		size: size,
	}
}

func (b *Board) GetSize() int {
	return b.size
}

func (b *Board) IsValidMove(playerPosition int, roll int) bool {
	if playerPosition == 0 && roll != 1 {
		return false
	}
	newPosition := playerPosition + roll
	return newPosition <= b.size
}

func (b *Board) CheckWinner(playerPosition int) bool {
	return playerPosition == b.size
}
