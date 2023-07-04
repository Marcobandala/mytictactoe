import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;
    private int scorePlayer;
    private int scoreComputer;
    private boolean gameEnd;
    private Random random;
    private Scanner scanner;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = '0';
        scorePlayer = 0;
        scoreComputer = 0;
        gameEnd = false;
        random = new Random();
        scanner = new Scanner(System.in);
        initializeBoard();
    }
                // Tictaotoe board
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    private void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-';
    }

    private void makeMove(int row, int col) {
        board[row][col] = currentPlayer;
        currentPlayer = (currentPlayer == '0') ? 'X' : '0';
    }

    private void makeComputerMove() {
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!isValidMove(row, col));

        System.out.println("Computer moves: " + (row + 1) + " " + (col + 1));
        makeMove(row, col);
    }

    private boolean checkWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] != '-' && board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] != '-' && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return true;
        }
        if (board[0][2] != '-' && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            return true;
        }

        return false;
    }

    private void updateScore() {
        if (currentPlayer == '0') {
            scorePlayer++;
        } else {
            scoreComputer++;
        }
    }

    private void resetBoard() {
        initializeBoard();
        currentPlayer = '0';
        gameEnd = false;
    }

    public void startGame() {
        System.out.println("Welcome to Tic Tac Toe!");

        while (true) {
            System.out.println("\nNew Game!");

            // Randomize who moves first
            currentPlayer = random.nextBoolean() ? '0' : 'X';

            while (!gameEnd) {
                printBoard();

                if (currentPlayer == '0') {
                    System.out.println("Player's turn.");
                    System.out.print("Enter row and column (e.g., A1, B3): ");
                    String input = scanner.nextLine().toUpperCase();
                    if (input.length() == 2) {
                        int row = input.charAt(0) - 'A';
                        int col = input.charAt(1) - '1';

                        if (isValidMove(row, col)) {
                            makeMove(row, col);
                        } else {
                            System.out.println("Invalid move. Try again.");
                            continue;
                        }
                    } else {
                        System.out.println("Invalid input. Try again.");
                        continue;
                    }
                } else {
                    System.out.println("Computer's turn.");
                    makeComputerMove();
                }

                if (checkWin()) {
                    printBoard();
                    if (currentPlayer == '0') {
                        System.out.println("Player wins!");
                        updateScore();
                    } else {
                        System.out.println("Computer wins!");
                        updateScore();
                    }
                    gameEnd = true;
                } else if (isBoardFull()) {
                    printBoard();
                    System.out.println("It's a tie!");
                    gameEnd = true;
                }
            }

            System.out.println("\nScores:");
            System.out.println("Player: " + scorePlayer);
            System.out.println("Computer: " + scoreComputer);

            System.out.print("\nPlay again? (Y/N): ");
            String playAgain = scanner.nextLine().toUpperCase();
            if (!playAgain.equals("Y")) {
                break;
            }

            resetBoard();
            gameEnd = false;
        }

        System.out.println("Thanks for playing Tic Tac Toe!");
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.startGame();
    }
}
