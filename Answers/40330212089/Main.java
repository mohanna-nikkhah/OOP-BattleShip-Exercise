import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter grid size between 5 and 26:");
        int gridSize = scanner.nextInt();
        Game game = new Game(gridSize);
        game.start();
    }
}

