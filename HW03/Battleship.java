import java.util.Scanner;

public class Battleship {
    public static void main(String[] args) {
        final int NUMBER_OF_SHIPS = 5;
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Battleship!\n");
        
        // Prompt both users to coordinate their ships
        char[][] player1LocationBoard = promptUserForShips(1, NUMBER_OF_SHIPS, input);
        char[][] player2LocationBoard = promptUserForShips(2, NUMBER_OF_SHIPS, input);

        // Create history boards
        char[][] player1HistoryBoard = createHistoryBoard();
        char[][] player2HistoryBoard = createHistoryBoard();

        String successMessage = "PLAYER %d HIT PLAYER %d's SHIP!";
        String errorMessage = "PLAYER %d MISSED!";

        // Play game
        do {
            // Players 1 Move
            int[] player1Move = makeMove(1, player2HistoryBoard, input);
            player2LocationBoard = updateLocationBoard(player2LocationBoard, player1Move[0], player1Move[1]);
            player2HistoryBoard = updateHistoryBoard(player2LocationBoard, player2HistoryBoard, player1Move[0], player1Move[1]);

            if (player2HistoryBoard[player1Move[0]][player1Move[1]] == 'X') {
                System.out.println(String.format(successMessage, 1, 2));
                printBattleShip(player2HistoryBoard);
                if (checkVictory(player2LocationBoard)) {
                    printFinalMessage(1, player1LocationBoard, player2LocationBoard);
                    break;
                }
                System.out.println();
            }
            else {
                System.out.println(String.format(errorMessage, 1));
                printBattleShip(player2HistoryBoard);
                System.out.println();
            }
            // Players 2 Move
            int[] player2Move = makeMove(2, player1HistoryBoard, input);
            player1LocationBoard = updateLocationBoard(player1LocationBoard, player2Move[0], player2Move[1]);
            player1HistoryBoard = updateHistoryBoard(player1LocationBoard, player1HistoryBoard, player2Move[0], player2Move[1]);

            if (player1HistoryBoard[player2Move[0]][player2Move[1]] == 'X') {
                System.out.println(String.format(successMessage, 2, 1));
                printBattleShip(player1HistoryBoard);
                if (checkVictory(player1LocationBoard)) {
                    printFinalMessage(2, player1LocationBoard, player2LocationBoard);
                    break;
                }
                System.out.println();
            }
            else {
                System.out.println(String.format(errorMessage, 2));
                printBattleShip(player1HistoryBoard);
                System.out.println();
            }
        } while (true);
    }

    public static int[] makeMove(int playerNumber, char[][] opponentHistoryBoard, Scanner input) {
        int xCoordinate;
        int yCoordinate;

        while (true) {
            // Prompt player for input and check it
            System.out.printf("Player %d, enter hit row/column:\n", playerNumber);
            xCoordinate = input.nextInt();
            yCoordinate = input.nextInt();
            if (checkMoveValidity(xCoordinate, yCoordinate, opponentHistoryBoard)) {
                return new int[] {xCoordinate, yCoordinate};
            }            
        }
    }

    public static boolean checkMoveValidity(int xCoordinate, int yCoordinate, char[][] opponentHistoryBoard) {
        String errorMessage1 = "Invalid coordinates. Choose different coordinates.";
        String errorMessage2 = "You already fired on this spot. Choose different coordinates.";

        if (!checkCoordinatesValidity(xCoordinate, yCoordinate)) {
            System.out.println(errorMessage1);
            return false;
        }
        if (opponentHistoryBoard[xCoordinate][yCoordinate] != '-') {
            System.out.println(errorMessage2);
            return false;
        }
        return true;
    }

    public static char[][] promptUserForShips(int playerNumber, int numberOfShips, Scanner input) {
        System.out.printf("PLAYER %d, ENTER YOUR SHIPS' COORDINATES.\n", playerNumber);
        int[][] chosenCoordinates = new int[5][2];
        int counter = 0;

        // Prompt user for ships coordinates
        while (counter < numberOfShips) {
            System.out.printf("Enter ship %d location:\n", counter + 1);
            int xCoordinate = input.nextInt();
            int yCoordinate = input.nextInt();
            // Check if coordinates are valid and never used before
            if (checkShipCoordinates(chosenCoordinates, xCoordinate, yCoordinate, counter)) {
                // Mark coordinates as chosen
                chosenCoordinates[counter][0] = xCoordinate;
                chosenCoordinates[counter][1] = yCoordinate;
                counter++;
            }
        }
        // Create starting board representation 
        char[][] board = createStartingBoard(chosenCoordinates);
        printBattleShip(board);

        // Print 100 blank lines
        System.out.print("\n".repeat(100));

        return board;
    }

    public static char[][] updateLocationBoard(char[][] locationBoard, int xCoordinate, int yCoordinate) {
        // Check if player hit or missed an opponents ship, update board responsively
        if (locationBoard[xCoordinate][yCoordinate] == '@') {
            locationBoard[xCoordinate][yCoordinate] = 'X';
        }
        else {
            locationBoard[xCoordinate][yCoordinate] = 'O';
        }
        return locationBoard;
    }

    public static char[][] updateHistoryBoard(char[][] locationBoard, char[][] historyBoard, int xCoordinate, int yCoordinate) {
        // Check if player hit or missed an opponents ship, update board responsively
        historyBoard[xCoordinate][yCoordinate] = locationBoard[xCoordinate][yCoordinate];
        return historyBoard;
    }

    public static char[][] createHistoryBoard() {
        final int BOARD_SIZE = 5;
        char[][] historyBoard = new char[BOARD_SIZE][BOARD_SIZE];

        // Prepopulate all squares with '-'
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                historyBoard[i][j] = '-';
            }
        }
        return historyBoard;
    }

    public static char[][] createStartingBoard(int[][] chosenCoordinates) {
        // Create starting board representation based on chosen coordinates
        char[][] board = new char[5][5];
        for (int i = 0; i < 5; i++) {
            board[chosenCoordinates[i][0]][chosenCoordinates[i][1]] = '@';
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '\0') {
                    board[i][j] = '-';
                }
            }
        }
        return board;
    }

    public static boolean checkShipCoordinates(int[][] chosenCoordinates, int xCoordinate, int yCoordinate, int shipsCounter) {
        String errorMessage1 = "Invalid coordinates. Choose different coordinates.";
        String errorMessage2 = "You already have a ship there. Choose different coordinates.";

        // Check if coordinates are valid
        if (!checkCoordinatesValidity(xCoordinate, yCoordinate)) {
            System.out.println(errorMessage1);
            return false;
        }
        // Check if coordinates were not chosen before
        for (int i = 0; i < shipsCounter; i++) {
            if (chosenCoordinates[i][0] == xCoordinate && chosenCoordinates[i][1] == yCoordinate) {
                System.out.println(errorMessage2);
                return false;
            }
        }
        return true;
    }

    public static boolean checkCoordinatesValidity(int xCoordinate, int yCoordinate) {
        // Check if coordinates are valid
        final int MIN_INT = 0;
        final int MAX_INT = 4;
        if (xCoordinate < MIN_INT || xCoordinate > MAX_INT || yCoordinate < MIN_INT || yCoordinate > MAX_INT) {
            return false;
        }
        return true;
    }

    public static boolean checkVictory(char[][] locationBoard) {
        final int NUMBER_OF_SHIPS = 5;
        final int BOARD_SIZE = 5;
        int sankShipsCounter = 0;

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (locationBoard[i][j] == '@') {
                    return false;
                }
                if (locationBoard[i][j] == 'X') {
                    sankShipsCounter++;
                }
            }
        }
        if (sankShipsCounter == NUMBER_OF_SHIPS) {
            return true;
        }
        return false;
    }

    public static void printFinalMessage(int winnerPlayer, char[][] player1Board, char[][] player2Board) {
        System.out.printf("PLAYER %d WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!\n\nFinal boards:\n\n", winnerPlayer);
        printBattleShip(player1Board);
        System.out.println();
        printBattleShip(player2Board);
    }

    //Use this method to print game boards to the console.
    private static void printBattleShip(char[][] player) {
        System.out.print("  ");
        for (int row = -1; row < 5; row++) {
            if (row > -1) {
                System.out.print(row + " ");
            }
            for (int column = 0; column < 5; column++) {
                if (row == -1) {
                    System.out.print(column + " ");
                } else {
                    System.out.print(player[row][column] + " ");
                }
            }
            System.out.println("");
        }
    }
}
