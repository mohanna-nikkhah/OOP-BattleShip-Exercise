import java.util.Scanner;

public class Game {
    private int gridSize;

    public Game(int gridSize) {
        this.gridSize = gridSize;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        while (playAgain) {
            System.out.println("Do you want to play against AI? (yes/no)");
            boolean againstAI = scanner.nextLine().equalsIgnoreCase("yes");
            Player player1 = new Player("Player1", gridSize, false);
            Player player2;
            if (againstAI) {
                player2 = new AIPlayer("AI Player", gridSize);
            } else {
                player2 = new Player("Player2" , gridSize , false);
            }
            System.out.println("Player1, Do you want to place ships? (yes/no");
            boolean manualPlacementPlayer1 = scanner.nextLine().equalsIgnoreCase("yes");
            player1.placeShips(!manualPlacementPlayer1);
            if (againstAI) {
                player2.placeShips(true);
            } else {
                System.out.println("Player2, Do you want to place ships? (yes/no)");
                boolean manualPlacementPlayer2 = scanner.nextLine().equalsIgnoreCase("yes");
                player2.placeShips(!manualPlacementPlayer2);
            }
            System.out.println("Player1's board:");
            player1.getBoard().printGrid();
            System.out.println("Player2's board:");
            player2.getBoard().printGrid();
            boolean player1Turn = true;
            while (true) {
                if (player1Turn) {
                    player1.playerTurn(player2);
                    if (player2.allShipsSunk()) {
                        System.out.println("Player1 wins!");
                        break;
                    }
                } else {
                    player2.playerTurn(player1);
                    if (player1.allShipsSunk()) {
                        System.out.println("Player2 wins!");
                        break;
                    }
                }
                player1Turn = !player1Turn;
            }
            System.out.println("Do you want to play again? (yes/no)");
            playAgain = scanner.nextLine().equalsIgnoreCase("yes");
        }
        scanner.close();
    }
}