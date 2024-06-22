
import java.util.InputMismatchException;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean continuePlaying = true;
        while (continuePlaying) {
            playGame(scanner);
            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.next().toLowerCase();
            continuePlaying = response.equals("yes");
        }

        scanner.close();
        System.out.println("Thank you for playing!");
    }

    public static void playGame(Scanner scanner) {
        char[][] board = new char[3][3];
        for (char[] board1 : board) {
            for (int col = 0; col < board1.length; col++) {
                board1[col] = ' ';
            }
        }

        System.out.println("Instructions:");
        System.out.println("1. How to play: If you want to enter in the first box, then you have to enter '0 0'.");
        System.out.println("   When your next turn comes and if you want to enter in front of your first entry, then you enter '0 1'.");
        System.out.println("   By this method, you can play the game further however you want.");
        System.out.println("2. Put a space between the two numbers that you enter.");
        System.out.println("   Example: If you want to enter '00', then you enter '0 0' (you put a space between the two numbers).");

        char player = 'X';
        boolean gameOver = false;
        int moves = 0; // Keep track of the number of moves made

        while (!gameOver && moves < 9) { // Stop if the board is full (9 moves)
            printBoard(board);
            System.out.print("Player , Enter your " + player + " whenever you want (row and column): ");

            int row = -1, col = -1;

            try {
                row = scanner.nextInt();
                col = scanner.nextInt();

                if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                    board[row][col] = player; // place the element
                    gameOver = haveWon(board, player);
                    moves++; // Increment the move counter

                    if (gameOver) {
                        printBoard(board);
                        System.out.println("Player " + player + " has won the game!");
                    } else if (moves == 9) { // If all moves are done and no winner, it's a draw
                        printBoard(board);
                        System.out.println("The game is a draw!");
                    } else {
                        // Switch player
                        player = (player == 'X') ? 'O' : 'X';
                    }
                } else if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] != ' ') {
                    System.out.println("Invalid move. The cell is already occupied. Try again!");
                } else {
                    System.out.println("Invalid move. The coordinates must be between 0 and 2. Try again!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter two numbers separated by a space.");
                scanner.next(); // Clear the invalid input
            }
        }
    }

    public static boolean haveWon(char[][] board, char player) {
        // check the rows
        for (char[] board1 : board) {
            if (board1[0] == player && board1[1] == player && board1[2] == player) {
                return true;
            }
        }

        // check for col
        for (int col = 0; col < board[0].length; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }

        // diagonal
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }

        return board[0][2] == player && board[1][1] == player && board[2][0] == player;
    }

    public static void printBoard(char[][] board) {
        System.out.println("    0   1   2");
        System.out.println("  +---+---+---+");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + " |");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(" " + board[i][j] + " |");
            }
            System.out.println();
            System.out.println("  +---+---+---+");
        }
    }
}
