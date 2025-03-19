import java.util.Random;
import java.util.Scanner;

public class Player {
    private String name;
    private Board board;
    private Board trackingBoard;
    private Ship[] ships;
    private boolean isAI;
    public Player(String name, int gridSize, boolean isAI){
        this.name = name;
        this.board = new Board(gridSize);
        this.trackingBoard = new Board(gridSize);
        this.ships = new Ship[]{
                new Ship(5), new Ship(4), new Ship(3) , new Ship(2)
        };
        this.isAI = isAI;
    }
    public void placeShips(boolean randomPlacement){
        if (randomPlacement){
            placeShipsRandomly();
        }else {
            placeShipsManually();
        }
    }
    private void placeShipsRandomly(){
        Random random = new Random();
        for (int i = 0; i < ships.length; i++) {
            Ship ship = ships[i];
            boolean isPlaced = false;
            while (!isPlaced) {
                int row = random.nextInt(board.getGrid().length);
                int col = random.nextInt(board.getGrid().length);
                boolean horizontal = random.nextBoolean();
                if (board.canPlaceShip(row, col, ship.getSize(), horizontal)) {
                    board.placeShips(row, col, ship.getSize(), horizontal);
                    isPlaced = true;
                }
            }
        }
    }
    private void placeShipsManually(){
        Scanner scanner  = new Scanner(System.in);
        for (int i = 0; i < ships.length; i++){
            Ship ship = ships[i];
            boolean isPlaced = false;
            while (!isPlaced){
                System.out.println(name + ", place your ship of size " + ship.getSize());
                String  input = scanner.nextLine();
                if (Utils.isValidInput(input , board.getGrid().length)){
                    Coordinate coord = new Coordinate(input , board.getGrid().length);
                    if (!coord.isValid()){
                        System.out.println("Invalid coordinates. Try again!");
                        continue;
                    }
                    System.out.println("Horizontal? (yes/no) :");
                    boolean horizontal = scanner.nextLine().equalsIgnoreCase("yes");
                    if (board.canPlaceShip(coord.getRow(), coord.getCol(), ship.getSize(), horizontal)){
                        board.placeShips(coord.getRow(), coord.getCol(), ship.getSize(), horizontal);
                        isPlaced = true;
                    }else {
                        System.out.println("Can't place this ship here. Try again!");
                    }
                }else {
                    System.out.println("Invalid input. Try again!");
                }
            }
        }
    }
    public void playerTurn(Player opponent){
        System.out.println(name + "'s turn:");
        trackingBoard.printGrid();
        if (isAI){
            aiTurn(opponent.getBoard());
        }else {
            humanTurn(opponent.getBoard());
        }
    }
    private void humanTurn(Board opponentBoard){
        Scanner scanner = new Scanner(System.in);
        boolean validAttack = false;
        while (!validAttack){
            String input = scanner.nextLine();
            if (Coordinate.isValidInput(input, board.getGrid().length)){
                Coordinate coord = new Coordinate(input, board.getGrid().length);
                if (!coord.isValid()){
                    System.out.println("Invalid coordinates. Try again!");
                    continue;
                }
                char[][] grid = opponentBoard.getGrid();
                if (trackingBoard.getGrid()[coord.getRow()][coord.getCol()] != '~'){
                    System.out.println("Miss!");
                }else {
                    if (grid[coord.getRow()][coord.getCol()] == 'S'){
                        System.out.println("Hit!");
                        grid[coord.getRow()][coord.getCol()] = '*';
                        trackingBoard.getGrid()[coord.getRow()][coord.getCol()] = '*';
                    }else {
                        System.out.println("Miss!");
                        trackingBoard.getGrid()[coord.getRow()][coord.getCol()] = '0';
                    }
                    validAttack = true;
                }
            }else {
                System.out.println("Invalid input. Try again!");
            }
        }
    }
    void aiTurn(Board opponentBoard){
        Random random = new Random();
        boolean validAttack = false;
        while (!validAttack){
            int row = random.nextInt(opponentBoard.getGrid().length);
            int col = random.nextInt(opponentBoard.getGrid().length);
            if (trackingBoard.getGrid()[row][col] == '~'){
                if (opponentBoard.getGrid()[row][col] == 'S'){
                    System.out.println("AI Hit!");
                    opponentBoard.getGrid()[row][col] = '*';
                    trackingBoard.getGrid()[row][col] = '*';
                }else {
                    System.out.println("AI Miss!");
                    trackingBoard.getGrid()[row][col] = '0';
                }
                validAttack = true;
            }
        }
    }
    public boolean allShipsSunk(){
        for (int i = 0; i < board.getGrid().length; i++) {
            for (int j = 0; j < board.getGrid()[i].length; j++) {
                if (board.getGrid()[i][j] == 'S') {
                    return false;
                }
            }
        }
        return true;
    }
    public Board getBoard() {
        return board;
    }
    public Board getTrackingBoard() {
        return trackingBoard;
    }
    public String getName() {
        return name;
    }
}
