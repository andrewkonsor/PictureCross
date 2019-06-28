
import java.awt.Dialog;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrew
 * @version Jun 6, 2019
 */
public class PictureCross {

    /**
     * @param args the command line arguments
     */

    
    private static Board createBoard(){
        int size=0;
        System.out.println("Enter board size:");
        /*try {
        BufferedReader reader =
                   new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(reader.readLine());
        } catch (Exception e) {
        }
        */
        size=8;
        Board board = new Board(size);

           return board;
        }
    
        
    
    public static void main(String[] args) {
       SolvingFunctions solver = new SolvingFunctions();
        Board board = PictureCross.createBoard();
        boolean isProgressing= false;
        Printing.printBoard(board);
        
        //Solve Given
        solver.boardGivenRows(board);
        
        do {            
           isProgressing=false;
        //Check for finished rows
        if(solver.boardCompleteRows(board)) isProgressing=true;
        
            System.out.println("Test");
        //Solve Solvable Rows
        if(solver.boardSolveRows(board)) isProgressing=true;
        
        
        } while (isProgressing);

        
        
        if (!isProgressing) System.out.println("Stuck");
        if (solver.puzzleSolved(board)) System.out.println("\n Puzzle Solved!!!");

    }

}
