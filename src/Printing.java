
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
                //board.getBoard()[i][j]='-';
                System.out.print(board.getBoard()[i][j]);
                System.out.print("  ");
                
            }
            System.out.println();
        }
    }
    
    public static void print(char [] x){
        for (int i=0;i<x.length;i++){
            System.out.print(x[i]);
            System.out.print("  ");
        }
        System.out.println();
    }
    
    
    
    public static void main(String[] args) throws IOException {
        System.out.println("Enter table size");
        int size = System.in.read()-48;
        char [][] picture = new char[size][size];
        for (int i=0;i<size;i++){
            for (int j = 0; j < size; j++) {
                picture[i][j]='-';
                System.out.print(picture[i][j]);
                System.out.print("  ");
                
            }
            System.out.println();
        }
    }
}
