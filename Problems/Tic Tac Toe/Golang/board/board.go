package board

import (
	"fmt"
	"strings"
	"tictactoe/shape"
)

type Board struct {
	size  int
	board [][]shape.Shape
}

func NewBoard(size int) *Board {
	b := &Board{
		size:  size,
		board: make([][]shape.Shape, size),
	}

	for i := range b.board {
		b.board[i] = make([]shape.Shape, size)
		for j := range b.board[i] {
			b.board[i][j] = shape.Empty
		}
	}
	return b
}
func (b *Board) PlayMove(x int, y int, shape shape.Shape) {
	b.board[x][y] = shape
}

func (b *Board) IsDraw(totalMoves int) bool {
	return b.size*b.size == totalMoves
}

func (b *Board) IsValidMove(x int, y int) bool {
	if x >= 0 && x < b.size && y >= 0 && y < b.size {
		if b.board[x][y] == shape.Empty {
			return true
		}
	}
	return false
}

func (b *Board) IsWinner(shape shape.Shape) bool {
	size := b.size
	for i := 0; i < size; i++ {
		rowCheck := true
		for j := 0; j < size; j++ {
			if shape != b.board[i][j] {
				rowCheck = false
				break
			}
		}
		if rowCheck {
			return true
		}

	}

	// col check
	for i := 0; i < size; i++ {
		colCheck := true
		for j := 0; j < size; j++ {
			if shape != b.board[j][i] {
				colCheck = false
				break
			}
		}
		if colCheck {
			return true
		}

	}

	// leftDiag check
	leftDiagCheck := true
	for i := 0; i < size; i++ {
		if b.board[i][i] != shape {
			leftDiagCheck = false
			break
		}
	}

	if leftDiagCheck {
		return true
	}

	// rightDiag check
	rightDiagCheck := true
	for i := 0; i < size; i++ {
		if b.board[i][size-1-i] != shape {
			rightDiagCheck = false
			break
		}
	}

	return rightDiagCheck

}

func (b *Board) PrintBoard() {
	// Build the separator line using a strings.Builder for efficiency
	var sb strings.Builder
	sb.WriteString("+")
	for i := 0; i < b.size; i++ {
		sb.WriteString("---+")
	}
	separator := sb.String()

	// Print the board
	for i := 0; i < b.size; i++ {
		fmt.Println(separator)
		for j := 0; j < b.size; j++ {
			// Print the left wall of the cell
			fmt.Print("| ")

			// Print the shape or an empty space
			if b.board[i][j] == shape.Empty {
				fmt.Print(" ")
			} else {
				// Use the String() method of the Shape type
				fmt.Print(b.board[i][j])
			}

			// Print the right padding of the cell
			fmt.Print(" ")
		}
		// Print the final right wall of the row and a newline
		fmt.Println("|")
	}
	// Print the final separator line at the bottom
	fmt.Println(separator)
}
