
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
        Printing.printBoard(board);
        
        for (int i=0;i<board.getSize();i++){
            char [] r = board.getRow(i);
            char [] c = board.getColumn(i);
            
            //Solve Given Columns and Rows
            if (solver.canSolve(board.getClueRows().get(i), r)){
               char [] solvedRow=solver.givenRow(board.getClueRows().get(i), r);
               board.changeRow(i, solvedRow);
               board.removeCompletedRows(i);
            }
            
            if (solver.canSolve(board.getClueColumns().get(i), c)){
               char [] solvedColumn=solver.givenRow(board.getClueColumns().get(i), c);
               board.changeColumn(i, solvedColumn);
               board.RemoveCompletedColumns(i);
            }
            
        }
       
        Printing.printBoard(board);
        
        
       
       
    }

}
