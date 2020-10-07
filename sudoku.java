/* sudoku.java, written by Russell Abernethy */

import java.io.*;
import java.util.*;

public class sudoku {
    public static void main(String[] args) {
        // Sodoku game to be solved:
        int gameBoard[][] = {
            {0,0,0,0,0,0,0,0,0},
            {0,3,0,0,0,0,1,6,0},
            {0,6,7,0,3,5,0,0,4},
            {6,0,8,1,2,0,9,0,0},
            {0,9,0,0,8,0,0,3,0},
            {0,0,2,0,7,9,8,0,6},
            {8,0,0,6,9,0,3,5,0},
            {0,2,6,0,0,0,0,9,0},
            {0,0,0,0,0,0,0,0,0}};

        // Solve the given sudoku.
        solve(gameBoard);

        // Print the sudoku board to the screen.
        print_board(gameBoard);

        // Project Euler - Problem 96
        eulerProblem96();
    }

// overloaded solve() method
    public static boolean solve(int gameBoard[][]) {
        return solve(gameBoard, 0);
    }

// solve() method --- finds a solution for a given sudoku.
    public static boolean solve(int gameBoard[][], int position) {
        // If there is no where left to check, you're done, return true.
        if(position >= gameBoard.length * gameBoard[0].length)
            return true;

        // If there is no number in a spot (represented with a zero).
        if(gameBoard[position/gameBoard.length][position%gameBoard[0].length] == 0) {
            
            // Go through each possible number.
            for(int i = 1; i <= 9; i++) {

                // Check if the current number can be placed in the spot.
                if(isValid(gameBoard, position, i)) {
                    // Place the number in the spot and try the next spot.
                    // If it is not possible, put zero back into the current spot.
                    gameBoard[position/gameBoard.length][position%gameBoard[0].length] = i;
                    if(solve(gameBoard, position + 1))
                        return true;
                    else
                        gameBoard[position/gameBoard.length][position%gameBoard[0].length] = 0;
                }
                
            }
            // If this point is reached, its backtracking time!
            return false;
        }
        // If this is reached, there is already a number in the space, so move onto the next space.
        return solve(gameBoard, position+1);
    }

// isValid() method --- returns T/F if a number can be placed at a given position.
    public static boolean isValid(int gameBoard[][], int position, int n) {
        // If every case is passed, return true, otherwise return false.

        return (caseRow(gameBoard, position, n) && caseCol(gameBoard, position, n) && caseBox(gameBoard, position, n));
    }

// caseRow() method --- helper method for isValid(). Checks if there is a duplicate number in the row.
    public static boolean caseRow(int gameBoard[][], int position, int n) {
        // Check the row for a duplicate number.
        for(int i = 0; i < 9; i++) {
            if(gameBoard[position/gameBoard.length][i] == n)
                return false;
        }
        return true;
    }

// caseCol() method --- helper method for isValid(). Checks if there is a duplicate number in the column.
    public static boolean caseCol(int gameBoard[][], int position, int n) {
        // Check the column for a duplicate number.
        for(int i = 0; i < 9; i++) {
            if(gameBoard[i][position%gameBoard[0].length] == n)
                return false;
        }
        return true;
    }

// caseBox() method --- helper method for isValid(). Checks if there is a duplicate number in the box.
    public static boolean caseBox(int gameBoard[][], int position, int n) {
        // Find the top left corner of the box that contains the current position.
        int row = (position / gameBoard.length) - (position / gameBoard.length % 3);
        int col = (position % gameBoard[0].length) - (position % gameBoard[0].length % 3);

        // Loop through the box and check for duplicates.
        for(int i = row; i < row + 3; i++) {
            for(int j = col; j < col + 3; j++) {
                if(gameBoard[i][j] == n) 
                    return false;
            }
        }
        return true;
    }

// print_board() method --- prints out the game board.
    public static void print_board(int gameBoard[][]) {
        for(int i = 0; i < gameBoard.length; i++) {
            if(i%3==0) // formating
                System.out.println();
            for(int j = 0; j < gameBoard[0].length; j++) {
                if(j%3==0) // formating
                    System.out.print(" ");
                System.out.print(gameBoard[i][j]);
            }
            System.out.println();
        }
    }
    public static void eulerProblem96() {

        int[][] board = new int[9][9]; // 2d array used to store game state.
        int sum = 0; // Sum of the top row of the first box in each solved puzzle.
        int n = 0; // The current line number of the file.
        try{
            // Open the file
            File f = new File("eulerSudoku.txt");
            Scanner scanner = new Scanner(f);

            
            while(scanner.hasNextLine()) {
                String data = scanner.nextLine();
                
                // Skip the first line of each sudoku, they have unessicary info?
                if(n == 0)
                    n++;
                // Add the soduku line by line to board.
                else if(n < 9) {
                    for(int col = 0; col < 9; col++)
                        board[n-1][col] = Integer.parseInt(String.valueOf(data.charAt(col)));
                    n++;
                }
                // Special case: add the last line of the soduku then solve it.
                else {
                    for(int col = 0; col < 9; col++)
                        board[n-1][col] = Integer.parseInt(String.valueOf(data.charAt(col)));
                    
                    solve(board);
                    print_board(board);
                    // Add the top row of the first box to sum.
                    sum += board[0][0]*100 + board[0][1]*10 + board[0][2];
                    System.out.println("current sum = " + sum);
                    n=0;
                }
            }
            System.out.println("The sum of all the sudokus is: " + sum);
            scanner.close();

        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
    }



}
    
        




