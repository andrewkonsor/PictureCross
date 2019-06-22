
import java.io.BufferedReader;
import java.io.InputStreamReader;

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
            char [] s = board.getRow(i);
            if (solver.canSolve(board.getClueRows().get(i), s)){
               solver.givenRow(board.getClueRows().get(i), s);
            }
            
        }
        char [] testRow = {'o','-','-','-','o','-','-','-'};
        board.changeColumn(2, testRow);
        board.changeRow(0, testRow);
        Printing.printBoard(board);
       
       
       
    }

}
