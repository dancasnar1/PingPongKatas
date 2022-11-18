package Connect4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Connect4Main {
    public static void main(String[] args) {
        int turnAlternator, turnNumber, match, player, player1Wins, player2Wins;
        final int NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, MINIMUM_CHAIN_TO_WIN;
        String player1GamePiece, player2GamePiece;
        boolean gameOver;
        String[][] gameBoard;
        Object[] gameOverAndPlayer1WinsAndPlayer2Wins;
        NUMBER_OF_ROWS = 6;
        NUMBER_OF_COLUMNS = 7;
        MINIMUM_CHAIN_TO_WIN = 4;

        player1GamePiece = "0";
        player2GamePiece = "X";


        gameOverAndPlayer1WinsAndPlayer2Wins = new Object[]{false, 0, 0};
        gameOver = (boolean) gameOverAndPlayer1WinsAndPlayer2Wins[0];
        player1Wins = (int) gameOverAndPlayer1WinsAndPlayer2Wins[1];
        player2Wins = (int) gameOverAndPlayer1WinsAndPlayer2Wins[2];

        outerLoop:
        for (match = 1; ; match++) {
            gameBoard = emptyBoard(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS);
            turnNumber = 1;
            System.out.println("_____________________________________________\nMatch: " + match + " | Turn: " + turnNumber + "\n");
            turnNumber++;
            printBoard(gameBoard);

            player = startingPlayerTurn(player1Wins, player2Wins);

            for (turnAlternator = player; ; turnAlternator++, turnNumber++) {
                if (turnAlternator % 2 == 0) {
                    player = 2;
                } else {
                    player = 1;
                }

                dropPiece(gameBoard, getColumn(player, NUMBER_OF_COLUMNS, player1GamePiece, player2GamePiece) - 1, player, NUMBER_OF_COLUMNS, player1GamePiece, player2GamePiece);
                System.out.println();

                System.out.println("_____________________________________________\nMatch: " + match + " | Turn: " + turnNumber + "\n");
                printBoard(gameBoard);

                gameOverAndPlayer1WinsAndPlayer2Wins = checkForWin(gameBoard, gameOverAndPlayer1WinsAndPlayer2Wins, NUMBER_OF_COLUMNS, MINIMUM_CHAIN_TO_WIN, player1GamePiece, player2GamePiece);

                gameOver = (boolean) gameOverAndPlayer1WinsAndPlayer2Wins[0];
                player1Wins = (int) gameOverAndPlayer1WinsAndPlayer2Wins[1];
                player2Wins = (int) gameOverAndPlayer1WinsAndPlayer2Wins[2];

                if (gameOver == true) {
                    System.out.println("_____________________________________________\nPlayer 1 wins: " + player1Wins + " | Player 2 wins: " + player2Wins);
                    if (player1Wins >= 10 && player2Wins == 0) {
                        System.out.println("_____________________________________________\nPlayer 2, you should just give up now...");
                        System.exit(0);
                    }
                    if (player2Wins >= 10 && player1Wins == 0) {
                        System.out.println("_____________________________________________\nPlayer 1, you should just give up now...");
                        System.exit(0);
                    }
                    gameOver = false;
                    gameOverAndPlayer1WinsAndPlayer2Wins[0] = gameOver;
                    continue outerLoop;
                }
            }
        }
    }

    public static String[][] emptyBoard(int NUMBER_OF_ROWS, int NUMBER_OF_COLUMNS) {
        int row, column;
        String[][] emptyBoard;

        for (row = 0, emptyBoard = new String[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS]; row < emptyBoard.length; row++) {
            for (column = 0; column < emptyBoard[row].length; column++) {
                emptyBoard[row][column] = "[ ]";
            }
        }
        return emptyBoard;
    }

    public static void printBoard(String[][] gameBoard) {
        int row, column, columnLabel;

        for (row = 0; row < gameBoard.length; row++) {
            for (column = 0; column < gameBoard[row].length; column++) {
                System.out.print(gameBoard[row][column]);
            }
            System.out.println();
        }
        System.out.print(" ");

        for (columnLabel = 1; columnLabel <= gameBoard[row - 1].length; columnLabel++) {
            System.out.print(columnLabel + "  ");
        }
        System.out.println();
    }

    public static String[][] dropPiece(String[][] gameBoard, int column, int player, int NUMBER_OF_COLUMNS, String player1GamePiece, String player2GamePiece) {
        int row;

        outerLoop:
        for (row = gameBoard.length - 1; row >= -1; row--) {
            if (row < 0) {
                System.out.println("Column " + (column + 1) + " is already full.");
                dropPiece(gameBoard, getColumn(player, NUMBER_OF_COLUMNS, player1GamePiece, player2GamePiece) - 1, player, NUMBER_OF_COLUMNS, player1GamePiece, player2GamePiece);
                break;
            }
            if (gameBoard[row][column].equals("[ ]")) {
                if (player == 1) {
                    gameBoard[row][column] = "[" + player1GamePiece + "]";
                    break outerLoop;
                }
                if (player == 2) {
                    gameBoard[row][column] = "[" + player2GamePiece + "]";
                    break outerLoop;
                }
            }
        }
        return gameBoard;
    }

    public static Object[] checkForWin(String[][] gameBoard, Object[] gameOverAndPlayer1WinsAndPlayer2Wins, int NUMBER_OF_COLUMNS, int MINIMUM_CHAIN_TO_WIN, String player1GamePiece, String player2GamePiece) {
        int row, column, player1MaximumChain, player2MaximumChain, diagonalStartPoint, player1Wins, player2Wins, columnNumber, fullColumns;
        boolean gameOver;

        gameOver = (boolean) gameOverAndPlayer1WinsAndPlayer2Wins[0];
        player1Wins = (int) gameOverAndPlayer1WinsAndPlayer2Wins[1];
        player2Wins = (int) gameOverAndPlayer1WinsAndPlayer2Wins[2];

        horizontalOuterLoop:

        for (row = 0, player1MaximumChain = 1, player2MaximumChain = 1; row < gameBoard.length; row++) {
            for (column = 0; column < gameBoard[row].length - 1; column++) {
                if (gameBoard[row][column].equals("[" + player1GamePiece + "]") && gameBoard[row][column + 1].equals("[" + player1GamePiece + "]")) {
                    player1MaximumChain++;
                    if (player1MaximumChain >= MINIMUM_CHAIN_TO_WIN) {
                        player1Wins++;
                        System.out.println("\nPlayer 1 won horizontally!");
                        gameOver = true;
                        break horizontalOuterLoop;
                    }
                } else if (gameBoard[row][column].equals("[" + player2GamePiece + "]") && gameBoard[row][column + 1].equals("[" + player2GamePiece + "]")) {
                    player2MaximumChain++;
                    if (player2MaximumChain >= MINIMUM_CHAIN_TO_WIN) {
                        player2Wins++;
                        System.out.println("\nPlayer 2 won horizontally!");
                        gameOver = true;
                        break horizontalOuterLoop;
                    }
                } else {
                    player1MaximumChain = 1;
                    player2MaximumChain = 1;
                }
            }
            player1MaximumChain = 1;
            player2MaximumChain = 1;
        }
        verticalOuterLoop:

        for (row = 0, column = 0, player1MaximumChain = 1, player2MaximumChain = 1; column < gameBoard[row].length; column++) {
            for (row = 0; row < gameBoard.length - 1; row++) {
                if (gameBoard[row][column].equals("[" + player1GamePiece + "]") && gameBoard[row + 1][column].equals("[" + player1GamePiece + "]")) {
                    player1MaximumChain++;
                    if (player1MaximumChain >= MINIMUM_CHAIN_TO_WIN) {
                        player1Wins++;
                        System.out.println("\nPlayer 1 won vertically!");
                        gameOver = true;
                        break verticalOuterLoop;
                    }
                } else if (gameBoard[row][column].equals("[" + player2GamePiece + "]") && gameBoard[row + 1][column].equals("[" + player2GamePiece + "]")) {
                    player2MaximumChain++;
                    if (player2MaximumChain >= MINIMUM_CHAIN_TO_WIN) {
                        player2Wins++;
                        System.out.println("\nPlayer 2 won vertically!");
                        gameOver = true;
                        break verticalOuterLoop;
                    }
                } else {
                    player1MaximumChain = 1;
                    player2MaximumChain = 1;
                }
            }
            player1MaximumChain = 1;
            player2MaximumChain = 1;
        }
        diagonalDownRightOuterLoop1:

        for (diagonalStartPoint = gameBoard.length - 2, player1MaximumChain = 1, player2MaximumChain = 1; diagonalStartPoint >= 0; diagonalStartPoint--) {
            for (row = diagonalStartPoint, column = 0; row < gameBoard.length - 1 && column < gameBoard[row].length - 1; row++, column++) {
                if (gameBoard[row][column].equals("[" + player1GamePiece + "]") && gameBoard[row + 1][column + 1].equals("[" + player1GamePiece + "]")) {
                    player1MaximumChain++;
                    if (player1MaximumChain >= MINIMUM_CHAIN_TO_WIN) {
                        player1Wins++;
                        System.out.println("\nPlayer 1 won diagonally!");
                        gameOver = true;
                        break diagonalDownRightOuterLoop1;
                    }
                } else if (gameBoard[row][column].equals("[" + player2GamePiece + "]") && gameBoard[row + 1][column + 1].equals("[" + player2GamePiece + "]")) {
                    player2MaximumChain++;
                    if (player2MaximumChain >= MINIMUM_CHAIN_TO_WIN) {
                        player2Wins++;
                        System.out.println("\nPlayer 2 won diagonally!");
                        gameOver = true;
                        break diagonalDownRightOuterLoop1;
                    }
                } else {
                    player1MaximumChain = 1;
                    player2MaximumChain = 1;
                }
            }
            player1MaximumChain = 1;
            player2MaximumChain = 1;
        }
        diagonalDownRightOuterLoop2:

        for (diagonalStartPoint = 1, player1MaximumChain = 1, player2MaximumChain = 1; diagonalStartPoint < gameBoard[0].length - 1; diagonalStartPoint++) {
            for (row = 0, column = diagonalStartPoint; row < gameBoard.length - 1 && column < gameBoard[row].length - 1; row++, column++) {
                if (gameBoard[row][column].equals("[" + player1GamePiece + "]") && gameBoard[row + 1][column + 1].equals("[" + player1GamePiece + "]")) {
                    player1MaximumChain++;
                    if (player1MaximumChain >= MINIMUM_CHAIN_TO_WIN) {
                        player1Wins++;
                        System.out.println("\nPlayer 1 won diagonally!");
                        gameOver = true;
                        break diagonalDownRightOuterLoop2;
                    }
                } else if (gameBoard[row][column].equals("[" + player2GamePiece + "]") && gameBoard[row + 1][column + 1].equals("[" + player2GamePiece + "]")) {
                    player2MaximumChain++;
                    if (player2MaximumChain >= MINIMUM_CHAIN_TO_WIN) {
                        player2Wins++;
                        System.out.println("\nPlayer 2 won diagonally!");
                        gameOver = true;
                        break diagonalDownRightOuterLoop2;
                    }
                } else {
                    player1MaximumChain = 1;
                    player2MaximumChain = 1;
                }
            }
            player1MaximumChain = 1;
            player2MaximumChain = 1;
        }
        diagonalDownLeftOuterLoop1:

        for (diagonalStartPoint = gameBoard.length - 2, player1MaximumChain = 1, player2MaximumChain = 1; diagonalStartPoint >= 0; diagonalStartPoint--) {
            for (row = diagonalStartPoint, column = gameBoard[row].length - 1; row < gameBoard.length - 1 && column > 0; row++, column--) {
                if (gameBoard[row][column].equals("[" + player1GamePiece + "]") && gameBoard[row + 1][column - 1].equals("[" + player1GamePiece + "]")) {
                    player1MaximumChain++;
                    if (player1MaximumChain >= MINIMUM_CHAIN_TO_WIN) {
                        player1Wins++;
                        System.out.println("\nPlayer 1 won diagonally!");
                        gameOver = true;
                        break diagonalDownLeftOuterLoop1;
                    }
                } else if (gameBoard[row][column].equals("[" + player2GamePiece + "]") && gameBoard[row + 1][column - 1].equals("[" + player2GamePiece + "]")) {
                    player2MaximumChain++;
                    if (player2MaximumChain >= MINIMUM_CHAIN_TO_WIN) {
                        player2Wins++;
                        System.out.println("\nPlayer 2 won diagonally!");
                        gameOver = true;
                        break diagonalDownLeftOuterLoop1;
                    }
                } else {
                    player1MaximumChain = 1;
                    player2MaximumChain = 1;
                }
            }
            player1MaximumChain = 1;
            player2MaximumChain = 1;
        }
        diagonalDownLeftOuterLoop2:

        for (diagonalStartPoint = gameBoard[0].length - 2, player1MaximumChain = 1, player2MaximumChain = 1; diagonalStartPoint > 0; diagonalStartPoint--) {
            for (row = 0, column = diagonalStartPoint; row < gameBoard.length - 1 && column > 0; row++, column--) {
                if (gameBoard[row][column].equals("[" + player1GamePiece + "]") && gameBoard[row + 1][column - 1].equals("[" + player1GamePiece + "]")) {
                    player1MaximumChain++;
                    if (player1MaximumChain >= MINIMUM_CHAIN_TO_WIN) {
                        player1Wins++;
                        System.out.println("\nPlayer 1 won diagonally!");
                        gameOver = true;
                        break diagonalDownLeftOuterLoop2;
                    }
                } else if (gameBoard[row][column].equals("[" + player2GamePiece + "]") && gameBoard[row + 1][column - 1].equals("[" + player2GamePiece + "]")) {
                    player2MaximumChain++;
                    if (player2MaximumChain >= MINIMUM_CHAIN_TO_WIN) {
                        player2Wins++;
                        System.out.println("\nPlayer 2 won diagonally!");
                        gameOver = true;
                        break diagonalDownLeftOuterLoop2;
                    }
                } else {
                    player1MaximumChain = 1;
                    player2MaximumChain = 1;
                }
            }
            player1MaximumChain = 1;
            player2MaximumChain = 1;
        }
        for (columnNumber = 0, fullColumns = 0; gameOver != true && columnNumber < NUMBER_OF_COLUMNS; columnNumber++) {
            if (!gameBoard[0][columnNumber].equals("[ ]")) {
                fullColumns++;
                if (fullColumns >= NUMBER_OF_COLUMNS) {
                    System.out.println("\nPlayer 1 and Player 2 drew the game!");
                    gameOver = true;
                }
            }
        }
        gameOverAndPlayer1WinsAndPlayer2Wins[0] = gameOver;
        gameOverAndPlayer1WinsAndPlayer2Wins[1] = player1Wins;
        gameOverAndPlayer1WinsAndPlayer2Wins[2] = player2Wins;

        return gameOverAndPlayer1WinsAndPlayer2Wins;
    }

    public static int getColumn(int player, int NUMBER_OF_COLUMNS, String player1GamePiece, String player2GamePiece) {
        int inputAsInt;
        String gamePiece;

        if (player == 1) {
            gamePiece = player1GamePiece;
        } else {
            gamePiece = player2GamePiece;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("_____________________________________________\nPlayer " + player + ", choose the column for your " + gamePiece + " piece.");

        outerLoop:
        while (true) {
            while (!scanner.hasNextInt()) {
                System.out.println("Your choice must be an integer. Try again!");
                scanner.next();
            }
            inputAsInt = scanner.nextInt();

            if (inputAsInt >= 1 && inputAsInt <= NUMBER_OF_COLUMNS) {
                break outerLoop;
            } else {
                System.out.println("Your choice must be between 1 and " + NUMBER_OF_COLUMNS + ". Try again!");
                continue;
            }
        }
        return inputAsInt;
    }

    public static int startingPlayerTurn(int player1Wins, int player2Wins) {
        int player;

        if (player1Wins == player2Wins) {
            player = (int) ((Math.random() * 2) + 1);
        } else if (player1Wins > player2Wins) {
            player = 2;
        } else {
            player = 1;
        }
        return player;
    }
}
