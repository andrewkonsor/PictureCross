
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrew
 * @version Jun 6, 2019
 */
public class SolvingFunctions {
/**
 * Function solves rows which have rows that are given at the start
 * row only has one possible solution
     * @param clue    
     * @param row    
     * @return     
*/
    public char [] givenRow ( List<Integer> clue, char [] row){
        int size = row.length;
        int count =0;
        for (int x:clue){
            for (int i=0;i<x;i++){
                row [count]='o';
                count++;
            }
            if (count <size){
                row[count]='x';
                count++;
            }
        }
        return row;
    }
    
    /**
     * Check if the row can be solved given the clue
     * @param clue
     * @param row
     * @return boolean 
     */
    public boolean isGivenRow (List<Integer> clue, char [] row){
       int size=row.length;
       int numFilledSpots=0;
       for (int i=0;i<clue.size();i++){
           numFilledSpots=numFilledSpots+clue.get(i);
       }
       
       return numFilledSpots+clue.size()-1==size;
    }
    
    public char [] partialSingleClue (List<Integer> clue, char [] row){
        
        if (clue.size()==1 && row.length-clue.get(0)<(double) row.length/2){
            int fill = clue.get(0)-(row.length-clue.get(0));
            int empty = row.length-clue.get(0);
            System.out.println(fill + " " + empty);
            for (int i=empty;i<fill+empty;i++){
                if (!validChange(row[i],'o')){
                    System.out.println("invalid change");
                    break;
                }
                row[i]='o';
            }
        }
        return row;
    }
    
    public char [] isSingleClueWithGaps (List <Integer> clue, char [] row){
     return row;
    }
        
    
    /**
     * Is Correct 
     * @param clue
     * @param row
     * @return  
     */
    public boolean isFinished (List<Integer> clue, char [] row){
        String s = new String(row);
        System.out.println(s);
        return false;
    }
    
    public boolean validChange (char x, char y){
        if (x=='o'&&y=='x') return false;
        else if (x=='x'&&y=='o') return false;
        return true;
    }
    
    public static void main(String[] args) {
        SolvingFunctions solver = new SolvingFunctions();
        List<Integer> clue = new ArrayList<>();
        int size = 8;
        clue.add(6);

        
        
        char [] row = new char[size];
        for (int i=0;i<size;i++){
         row[i]='-';
        }
        
        

        String s = new String(row);
        System.out.println(s);
        
    }
}
