
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
        size=15;
        Board board = new Board(size);
        int x=0;
        int y=0;
        for (List<Integer> rows: board.getClueRows()){
            for (int r:rows){
                x=r+x;
            }
        }
        for (List<Integer> col: board.getClueColumns()){
            for (int c:col){
                y=c+y;
            }
        }
        if (x==y)
        System.out.println("Is valid board");
           return board;
        }
    
        
    
    public static void main(String[] args) {
       SolvingFunctions solver = new SolvingFunctions();
        Board board = PictureCross.createBoard();
        boolean isProgressing= false;
        Printing.printBoard(board);
        
        //Solve Given
        solver.boardGivenRows(board);
        //Solve Partial single R/C
        solver.boardPartialClue(board);
        //Printing.printBoard(board);
        do {            
           isProgressing=false;
        //Check for finished rows
        if(solver.boardCompleteRows(board)) isProgressing=true;

        //Solve Solvable Rows
        if(solver.boardSolveRows(board)) isProgressing=true;
        
        //Fill gaps in single clue rows
        if (solver.boardFillSingleClueWithGaps(board)) isProgressing=true;
        
        //Solve Caped single clues
        if (solver.boardSolveSingleClueCapped(board)) isProgressing=true;
        
        //Solve Edges
        if (solver.boardEdgeSolve(board)) isProgressing=true;
        
        //Check each R/C possibilities
        if (solver.boardPossiblitiesCheck(board)) isProgressing=true;
        
        } while (isProgressing && board.getIncompletedColumns().size()>0);

        
        
        
        if (solver.puzzleSolved(board)) System.out.println("\n Puzzle Solved!!!");
        else if (!isProgressing) System.out.println("Stuck");
    }

}
