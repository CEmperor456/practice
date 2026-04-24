import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
  private static final char EMPTY = ' ';
  private static final char PLAYER_X = 'X';
  private static final char PLAYER_O = 'O';
  private static final int SIZE = 3;

  private char[][] board = new char[SIZE][SIZE];
  private char currentPlayer = PLAYER_X;

  public TicTacToe() {
    for (char[] row : board) {
      Arrays.fill(row, EMPTY);
    }
  }

  public void play() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome to Tic Tac Toe!");
    printBoard();

    while (true) { 
       System.out.println("Player " + currentPlayer + ", enter your move (row and column): ");
       int row, col;
       if (!scanner.hasNextInt()) {
          System.out.println("Please enter numbers");
          scanner.nextLine();
          continue;
       } 
       row = scanner.nextInt();
        if (!scanner.hasNextInt()) {
            System.out.println("Please enter two numbers (row and column).");
            scanner.nextLine();
            continue;
        }
        col = scanner.nextInt();

        // Convert to 0-based
        row -= 1;
        col -= 1;

        if (!isValidMove(row, col)) {
          System.out.println("Invalid move. Try again.");
          continue;
        }

        board[row][col] = currentPlayer;
        printBoard();

        if (hasWinner()) {
          System.out.println("Player " + currentPlayer + " wins!");
          break;
        }

        if (isBoardFull()) {
          System.out.println("It's a draw!");
          break;
        }

        switchPlayer();
    }

    scanner.close();
    System.out.println("Game over. Thanks for playing!");
  }

  private boolean isValidMove(int row, int col) {
    return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == EMPTY;
  }

  private void switchPlayer() {
    currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
  }

  private boolean hasWinner() {
    //Rows
    for (int i = 0; i  < SIZE; i++) {
      if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
        return true;
      }
    }

    //Columns
    for (int i = 0; i < SIZE; i++) {
      if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
        return true;
      }
    }
    //Diagonals
    if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
      return true;
    }

    if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
      return true;
    }
    return false;
  }

  private boolean isBoardFull() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (board[i][j] == EMPTY) 
          return false;
      }
    }
    return true;
  }
  
  private void printBoard() {
    System.out.println();
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        System.out.print(" " + (board[i][j] == EMPTY ? '_' : board[i][j]));
        if (j < SIZE -1) {
          System.out.print("|");
        }
      }
      System.out.println();
      if (i < SIZE - 1) {
        System.out.println("---+---+---");
      }
    }

    System.out.println();
  }

  public static void main (String[] args) {
    TicTacToe game = new TicTacToe();
    game.play();
  }
}