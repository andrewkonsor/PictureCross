
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrew
 * @version Jun 6, 2019
 */
public class Board {

    private int size;
    private char [][] board; 
    private List<List<Integer>> rows = new ArrayList<>();
    private List<List<Integer>> columns= new ArrayList<>();
    private List<Integer> incompletedRows= new ArrayList<>();
    private List<Integer> incompletedColumns = new ArrayList<>();
    
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public char[][] getBoard() {
        return board;
    }

    public List<List<Integer>> getClueRows() {
        return rows;
    }

    public void setClueRows(List<List<Integer>> rows) {
        this.rows = rows;
    }

    public List<List<Integer>> getClueColumns() {
        return columns;
    }

    public void setClueColumns(List<List<Integer>> columns) {
        this.columns = columns;
    }

    public void setSpot(char c,int row,int column) {
        this.board [row][column] = c;
    }

    public List<Integer> getIncompletedRows() {
        return incompletedRows;
    }

    public void removeCompletedRows(Integer rowNumber){
        incompletedRows.remove(rowNumber);
    }    
    
    public List<Integer> getIncompletedColumns() {
        return incompletedColumns;
    }
    

    public void removeCompletedColumns(Integer colNumber){
        incompletedColumns.remove(colNumber);
    }

    public char [] getRow(int n){
        char [] row = new char[size];
        for (int i= 0;i<size;i++){
            row[i] = board[n][i];
        }
        return row;
    }
    
    public char [] getColumn(int n){
        
        char [] column = new char[size];
        for (int i= 0;i<size;i++){
            column[i] = board[i][n];
        }
       
        return column;
    }
    
    public void changeRow (int n,char [] updated){
        
        char [] prev = getRow(n);
        int i=0;
        for (char c:prev){
            if (updated[i]!=c){
                if (validChange(c, updated[i])){
                    setSpot(updated[i], n, i);
                }
                
                else System.err.println("Error at " + n + ":" + i);
            }
        i++;    
        }
    }
    
    
    public void changeColumn (int n,char [] updated){
        
        char [] prev = getColumn(n);
        int i=0;
        for (char c:prev){
            if (updated[i]!=c){
                if (validChange(c, updated[i])){
                    setSpot(updated[i], i, n);
                }
                else System.err.println("Error at " + i + ":" + n);
            }
        i++;    
        }
    }

    public static boolean validChange (char prev, char now){
        if (prev=='o'&&(now=='x'||now=='-')) {
            System.err.println("Invalid Change");
            return false;
        }
        else if (prev=='x'&&(now=='o'||now=='-')) {
            System.err.println("Invalid Change");
            return false;
        }
        return true;
    }
    
    public void board1Clues(){
        
        rows.get(0).add(3);
        rows.get(1).add(3);
        rows.get(2).add(3);
        rows.get(3).add(4);
        rows.get(4).add(8);
        rows.get(5).add(9);
        rows.get(6).add(8);
        rows.get(7).add(9);
        rows.get(8).add(8);
        rows.get(9).add(4);
        rows.get(9).add(2);
        columns.get(0).add(2);
        columns.get(1).add(5);
        columns.get(2).add(6);
        columns.get(3).add(6);
        columns.get(4).add(6);
        columns.get(5).add(2);
        columns.get(5).add(5);
        columns.get(6).add(9);
        columns.get(7).add(10);
        columns.get(8).add(6);
        columns.get(8).add(1);
        columns.get(9).add(3);
    }
    
    public Board(int size) {
        this.size = size;
        board = new char[size][size];
        
        for (int i=0;i<size;i++){
         incompletedColumns.add(i);
         incompletedRows.add(i);
        }

        for (int i=0;i<size;i++){
            for (int j = 0; j < size; j++) {
                this.board[i][j]='-';
            }
        }

        
        
        for (int i=0;i<size;i++){
        rows.add(new ArrayList<>());
        columns.add(new ArrayList<>());
        }
        
        //Hard Coded values
        board1Clues();
    }
    
    
}
