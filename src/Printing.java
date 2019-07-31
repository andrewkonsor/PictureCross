
import java.io.IOException;

/**
 *
 * @author Andrew
 * @version Jun 6, 2019
 */
public class Printing {

    public static void printBoard(Board board){
      
        for (int i=0;i<board.getSize();i++){
            for (int j = 0; j < board.getSize(); j++) {

                System.out.print(board.getBoard()[i][j]);
                System.out.print("  ");
                
            }
            System.out.println();
        }
        System.out.println("Print complete\n");
    }
    
    public static void print(char [] x){
        for (int i=0;i<x.length;i++){
            System.out.print(x[i]);
            System.out.print("  ");
        }
        System.out.println();
    }
    
    
  
}
