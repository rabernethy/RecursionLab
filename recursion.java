/* recursion.java, written by Russell Abernethy */

public class recursion {

    public static void main(String[] args) {
        // Initialize an 8x8 gameboard for the 8 queens problem.
        int gameBoard[][] = new int[8][8];
        
        // Solves the 8 queens problem using backtracking.
        solve(gameBoard);

        // Print out the solved game board.
        printBoard(gameBoard);
    }

// overloaded solve method.
    public static boolean solve(int gameBoard[][]) {
        return solve(gameBoard, 0);
    }

// solve() method --- finds a solution for the 8 queens problem.
    public static boolean solve(int gameBoard[][], int position) {
        // Continue checking columns until the last one is checked.
        if (position >= gameBoard.length)
            return true;

        // Check each column, seeing if a queen can be placed in it.
        for(int i = 0; i < gameBoard.length; i++) {
            
            // Check and see if i is a valid location for a queen to be placed.
            if(isValid(gameBoard, i, position)) {

                // Mark that a queen has be placed at the current position.
                gameBoard[i][position] = 1;

                // Keep trying to solve the next position.
                if(solve(gameBoard, position + 1))
                    return true;

                // If this line is reached, there was no solution so backtrack.
                gameBoard[i][position] = 0;
            }
        }
        // A solution cannot be reached from the current board state.        
        return false;
    }

// isValid() method --- returns T/F if a possition is a valid spot to place a queen.
    public static boolean isValid(int gameBoard[][], int row, int col) {
        return (Case1(gameBoard, row, col) && Case2(gameBoard, row, col) && Case3(gameBoard, row, col));
    }

// Case1() method --- helper method for isValid(). Checks the current row for other queens.
    public static boolean Case1(int gameBoard[][], int row, int col) {
        // Check the left part of the current row for a potential confict.
        for(int i = 0; i < col; i++)
            if(gameBoard[row][i] == 1) {return false;}
        
        return true;
    }

    // Case2() method --- helper method for isValid(). Checks the upper left diagonal for other queens.
    public static boolean Case2(int gameBoard[][], int row, int col) {
        // Check the upper left diagonal for potential conflicts.
        for(int i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if(gameBoard[i][j] == 1) {return false;}
        
        return true;
    }

// Case3() method --- helper method for isValid(). Checks bottom left diagonal for other queens.
    public static boolean Case3(int gameBoard[][], int row, int col) {
        // Check the bottom left diagonal for potential conflicts.
        for(int i = row, j = col; j >= 0 && i < gameBoard.length; i++, j--)
            if(gameBoard[i][j] == 1) {return false;}
        
        return true;
    }

// printBoard() method --- prints out the game board.
    public static void printBoard(int gameBoard[][]) {
        for(int i = 0; i < gameBoard.length; i++) {
            for(int j = 0; j < gameBoard[0].length; j++) 
                    System.out.print(gameBoard[i][j]);
            System.out.println();
        }
    }
}

