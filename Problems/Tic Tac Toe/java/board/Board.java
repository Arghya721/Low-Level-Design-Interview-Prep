package board;

import java.util.Arrays;

import shape.Shape;

public class Board {
    private int size;
    private Shape[][] board;

    public Board(int size) {
        this.size = size;
        board = new Shape[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(board[i], Shape.EMPTY);
        }
    }

    public void playMove(int x, int y, Shape shape) {
        this.board[x][y] = shape;
        System.out.println(shape + " placed on " + x + "," + y);
    }

    public boolean isDraw(int totalMoves) {
        return size * size == totalMoves;
    }

    public boolean isWinner(Shape shape) {
        // row check
        for (int i = 0; i < size; i++) {
            boolean rowCheck = true;
            for (int j = 0; j < size; j++) {
                if (shape != board[i][j]) {
                    rowCheck = false;
                    break;
                }
            }
            if (rowCheck)
                return true;
        }

        // col check
        for (int i = 0; i < size; i++) {
            boolean colCheck = true;
            for (int j = 0; j < size; j++) {
                if (shape != board[j][i]) {
                    colCheck = false;
                    break;
                }
            }
            if (colCheck)
                return true;
        }

        // leftDiag check
        boolean leftDiagCheck = true;
        for (int i = 0; i < size; i++) {

            if (board[i][i] != shape) {
                leftDiagCheck = false;
                break;
            }
        }

        if (leftDiagCheck)
            return true;

        // rightDiag check
        boolean rightDiagCheck = true;
        for (int i = 0; i < size; i++) {
            if (board[i][size - 1 - i] != shape) {
                rightDiagCheck = false;
                break;
            }
        }

        if (rightDiagCheck)
            return true;

        return false;
    }

    public boolean isValidMove(int x, int y, Shape shape) {
        if (x >= 0 && x < size && y >= 0 && y < size) {
            if (board[x][y] == Shape.EMPTY)
                return true;
        }

        return false;
    }

    public void printBoard() {
        String separator = "+";
        for (int i = 0; i < size; i++) {
            separator += "---+";
        }

        for (int i = 0; i < size; i++) {
            System.out.println(separator);
            for (int j = 0; j < size; j++) {
                System.out.print("| ");
                if (board[i][j] == Shape.EMPTY) {
                    System.out.print(" ");
                } else {
                    System.out.print(board[i][j]);
                }
                System.out.print(" ");
            }
            System.out.println("|");
        }
        System.out.println(separator);
    }

}
