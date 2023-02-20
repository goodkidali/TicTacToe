import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> userMoves;
    static ArrayList<Integer> cpuMoves;


    public static void main(String[] args) {

        while(true) {
            char[][] gameBoard = { {' ', '|', ' ', '|', ' '},
                                   {'-', '+', '-', '+', '-'},
                                   {' ', '|', ' ', '|', ' '},
                                   {'-', '+', '-', '+', '-'},
                                   {' ', '|', ' ', '|', ' '}};
            userMoves = new ArrayList<Integer>();
            cpuMoves = new ArrayList<Integer>();

            Scanner game = new Scanner(System.in);
            System.out.println("Enter '1' to play against CPU or '2' to play against a friend:");
            int gameChoice = game.nextInt();

            if(gameChoice == 1){
                printGameBoard(gameBoard);
                while(true) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Enter move (1-9):");
                    int move = scanner.nextInt();
                    while(userMoves.contains(move) || cpuMoves.contains(move)) {
                        System.out.println("Invalid move! Position must be empty!!");
                        move = scanner.nextInt();
                    }
                    playerMove(gameBoard,move, "user");

                    String gameWinner = winner();
                    if(gameWinner.length() > 0) {
                        System.out.println(gameWinner);
                        printGameBoard(gameBoard);
                        System.out.println("Program restarting...");
                        break;
                    }

                    Random cpuMove = new Random();
                    int cpuPos = cpuMove.nextInt(9) + 1;
                    while(userMoves.contains(cpuPos) || cpuMoves.contains(cpuPos)) {
                        cpuPos = cpuMove.nextInt(9) + 1;
                    }
                    playerMove(gameBoard, cpuPos, "cpu");

                    printGameBoard(gameBoard);

                    gameWinner = winner();
                    if(gameWinner.length() > 0) {
                        System.out.println(gameWinner);
                        printGameBoard(gameBoard);
                        System.out.println("Program restarting...");
                        break;
                    }
                }
            }

            else if(gameChoice == 2) {
                printGameBoard(gameBoard);
                while(true) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Player 1 - Enter move (1-9):");
                    int move = scanner.nextInt();
                    while(userMoves.contains(move) || cpuMoves.contains(move)) {
                        System.out.println("Invalid move! Position must be empty!!");
                        move = scanner.nextInt();
                    }
                    playerMove(gameBoard,move, "user");

                    String gameWinner = winner();
                    if(gameWinner.length() > 0) {
                        System.out.println(gameWinner);
                        printGameBoard(gameBoard);
                        System.out.println("Program restarting...");
                        break;
                    }

                    printGameBoard(gameBoard);
                    Scanner scanner2 = new Scanner(System.in);
                    System.out.println("Player 2 - Enter move (1-9):");
                    int move2 = scanner2.nextInt();
                    while(userMoves.contains(move2) || cpuMoves.contains(move2)) {
                        System.out.println("Invalid move! Position must be empty!!");
                        move2 = scanner.nextInt();
                    }
                    playerMove(gameBoard, move2, "cpu");

                    printGameBoard(gameBoard);

                    gameWinner = winner();
                    if(gameWinner.length() > 0) {
                        System.out.println(gameWinner);
                        printGameBoard(gameBoard);
                        System.out.println("Program restarting...");
                        break;
                    }
                }
            }


        }
    }

    /**
     * This method takes the game board character array and iterates through each row
     * and column to output the entire board in separate lines respectively
     * @param gameBoard is the input character array that represents the game board
     */
    public static void printGameBoard(char[][] gameBoard) {

        for(char[] row : gameBoard){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }

    }

    /**
     * This method handles the movement of each player, separating player 1 from player 2 by symbol,
     * and inputs the move made into the game board
     * @param gameBoard is the character array containing the game board
     * @param move is the input integer from 1-9 representing the positions on the game board
     * @param player depicts which player is inputting a move to determine whether an 'X' or 'O' should
     *               be placed
     */
    public static void playerMove(char[][] gameBoard, int move, String player) {

        char symbol = ' ';

        if(player.equals("user")) {
            symbol = 'X';
            userMoves.add(move);
        }
        else if(player.equals("cpu")) {
            symbol = 'O';
            cpuMoves.add(move);
        }

        switch(move){
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    /**
     * This method checks the current state of the game board for any predetermined "winner"
     * cases. The cases are from left to right in the top, middle, and bottom rows; from top to bottom
     * in the left, middle, and right columns; and across the board diagonally from each side.
     * This method is called after each move from any player to check for a winner.
     * @return the winner based on which player moves contain all winning positions.
     */
    public static String winner() {

        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List cross1 = Arrays.asList(1,5,9);
        List cross2 = Arrays.asList(3,5,7);

        List<List> winnerCheck = new ArrayList<List>();
        winnerCheck.add(topRow);
        winnerCheck.add(midRow);
        winnerCheck.add(botRow);
        winnerCheck.add(leftCol);
        winnerCheck.add(midCol);
        winnerCheck.add(rightCol);
        winnerCheck.add(cross1);
        winnerCheck.add(cross2);

        for(List l : winnerCheck) {
            if(userMoves.containsAll(l)) {
                return "Player 1 Wins!!!";
            }
            else if(cpuMoves.containsAll(l)) {
                return "Player 2 Wins!!!";
            }
            if(userMoves.size()+cpuMoves.size() == 9) {
                return "Draw!";
            }
        }
        return "";
    }
}
