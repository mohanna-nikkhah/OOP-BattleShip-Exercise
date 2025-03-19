public class Board {
    private char[][] grid;
    private int size;
    public Board(int size){
        this.grid = new char[size][size];
        this.size = size;
        initializeBoard();
    }
    public void initializeBoard(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = '~';
            }
        }
    }
    public void printGrid(){
        System.out.print("     ");
        for (int i = 0; i < size; i++) {
            System.out.print((char) ('A' + i) + "   ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            if (i < 10) {
                System.out.print("  " + i + " ");
            } else {
                System.out.print(" " + i + " ");
            }

            for (int j = 0; j < size; j++) {
                System.out.print(" " + grid[i][j] + "  ");
            }
            System.out.println();
        }
    }
    public boolean canPlaceShip(int row, int col, int size, boolean horizontal) {
        if (horizontal) {
            if (col + size > this.size){
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (grid[row][col + i] != '~'){
                    return false;
                }
            }
        } else {
            if (row + size > this.size){
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (grid[row + i][col] != '~'){
                    return false;
                }
            }
        }
        return true;
    }
    public void placeShips(int row, int col, int size, boolean horizontal) {
        if (horizontal) {
            for (int i = 0; i < size; i++) {
                grid[row][col + i] = 'S';
            }
        } else {
            for (int i = 0; i < size; i++) {
                grid[row + i][col] = 'S';
            }
        }
    }

    public char[][] getGrid() {
        return grid;
    }
}

