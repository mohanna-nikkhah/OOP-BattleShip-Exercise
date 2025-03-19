import java.util.Random;

public class AIPlayer extends Player {
    public AIPlayer(String name, int gridSize) {
        super(name, gridSize, true);
    }
    @Override
    public void playerTurn(Player opponent){
        System.out.println(getName() + "'s turn (AI):");
        getTrackingBoard().printGrid();
        aiTurn(opponent.getBoard());
    }
    public void aiTurn(Board opponentBoard){
        Random random = new Random();
        boolean validAttack = false;
        while (!validAttack){
            int row = random.nextInt(opponentBoard.getGrid().length);
            int col = random.nextInt(opponentBoard.getGrid().length);
            if (getTrackingBoard().getGrid()[row][col] == '~'){
                if (opponentBoard.getGrid()[row][col] == 'S') {
                    System.out.println("AI Hit!");
                    opponentBoard.getGrid()[row][col] = '*';
                    getTrackingBoard().getGrid()[row][col] = '*';
                }else {
                    System.out.println("AI Miss!");
                    getTrackingBoard().getGrid()[row][col] = '0';
                }
                validAttack = true;
            }
        }
    }
}
