
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
    private boolean [] completedRows;
    private boolean [] completedColumns;
    
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
    
    public Board(int size) {
        this.size = size;
        board = new char[size][size];
        completedRows = new boolean[size];
        completedColumns = new boolean[size];
        for (int i=0;i<size;i++){
            for (int j = 0; j < size; j++) {
                this.board[i][j]='-';
            }
        }

        
        //Hard Coded values
        for (int i=0;i<size;i++){
        rows.add(new ArrayList<>());
        columns.add(new ArrayList<>());
        }
        rows.get(0).add(4);
        rows.get(1).add(6);
        rows.get(2).add(8);
        rows.get(3).add(8);
        rows.get(4).add(8);
        rows.get(5).add(8);
        rows.get(6).add(6);
        rows.get(7).add(4);
        columns.get(0).add(4);
        columns.get(1).add(6);
        columns.get(2).add(8);
        columns.get(3).add(8);
        columns.get(4).add(8);
        columns.get(5).add(8);
        columns.get(6).add(6);
        columns.get(7).add(4);
    }
    
    
}
