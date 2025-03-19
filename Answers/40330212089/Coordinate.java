public class Coordinate {
    private int row;
    private int col;
    public Coordinate(String input , int gridSize){
        input = input.substring(0,1).toUpperCase() + input.substring(1);
        if (isValidInput(input , gridSize)){
            this.row = Integer.parseInt(input.substring(1));
            this.col = input.charAt(0) - 'A';
        }else {
            this.row = -1;
            this.col = -1;
        }
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public boolean isValid(){
        return row != -1 && col != -1;
    }
    public static boolean isValidInput(String input , int gridSize){
        if (input.length() < 2 || input.length() > 3){
            return false;
        }
        input = input.substring(0,1).toUpperCase() + input.substring(1);
        char col = input.charAt(0);
        String rowPart = input.substring(1);
        if (col < 'A' || col > (char) ('A' + gridSize - 1)){
            return false;
        }
        for (int i = 0; i < rowPart.length(); i++){
            char ch = rowPart.charAt(i);
            if (ch < '0' || ch > '9'){
                return false;
            }
        }
        int rowNum = Integer.parseInt(rowPart);
        if (rowNum < 0 || rowNum >= gridSize){
            return false;
        }
        return true;
    }
}
