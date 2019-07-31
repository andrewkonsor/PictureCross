
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
                //else System.err.println("Error at " + i + ":" + n);
            }
        i++;    
        }
    }

    public static boolean validChange (char prev, char now){
        if (prev=='o'&& now=='x') {
            System.err.println("Invalid Change");
            return false;
        }
        
        
        else if (prev=='x'&& now=='o') {
            System.err.println("Invalid Change");
            return false;
        }
        
        else if (prev=='x'&& now=='-') {
            //System.err.println("Invalid Change");
            return false;
        }
        
        else if (prev=='o'&& now=='x') {
            //System.err.println("Invalid Change");
            return false;
        }
        
        
        
        return true;
    }
    
    public void board1Clues(){
        
        rows.get(0).add(2);
        rows.get(0).add(2);
        rows.get(1).add(3);
        rows.get(1).add(3);
        rows.get(2).add(6);
        rows.get(3).add(4);
        rows.get(4).add(4);
        rows.get(5).add(6);
        rows.get(6).add(3);
        rows.get(6).add(3);
        rows.get(7).add(2);
        rows.get(7).add(2);

        columns.get(0).add(2);
        columns.get(0).add(2);
        columns.get(1).add(3);
        columns.get(1).add(3);
        columns.get(2).add(6);
        columns.get(3).add(4);
        columns.get(4).add(4);
        columns.get(5).add(6);
        columns.get(6).add(3);
        columns.get(6).add(3);
        columns.get(7).add(2);
        columns.get(7).add(2);
    }
    
    public void board2Clues (){
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
    
    public void board3Clues (){
        
        rows.get(0).add(4);
        rows.get(1).add(6);
        rows.get(2).add(7);
        rows.get(3).add(6);
        rows.get(4).add(4);
        rows.get(5).add(1);
        rows.get(6).add(1);
        rows.get(7).add(1);
        columns.get(0).add(1);
        columns.get(1).add(3);
        columns.get(2).add(3);
        columns.get(3).add(3);
        columns.get(4).add(5);
        columns.get(5).add(5);
        columns.get(6).add(8);
        columns.get(7).add(1);
        columns.get(7).add(1);
    }
    
    public void board4Clues(){
        rows.get(0).add(1);
        rows.get(0).add(6);
        rows.get(1).add(5);
        rows.get(2).add(1);
        rows.get(2).add(4);
        rows.get(3).add(2);
        rows.get(3).add(3);
        rows.get(4).add(3);
        rows.get(4).add(2);
        rows.get(5).add(4);
        rows.get(5).add(1);
        rows.get(6).add(5);
        rows.get(7).add(6);
        rows.get(7).add(1);
        
        columns.get(0).add(1);
        columns.get(0).add(6);
        columns.get(1).add(5);
        columns.get(2).add(1);
        columns.get(2).add(4);
        columns.get(3).add(2);
        columns.get(3).add(3);
        columns.get(4).add(3);
        columns.get(4).add(2);
        columns.get(5).add(4);
        columns.get(5).add(1);
        columns.get(6).add(5);
        columns.get(7).add(6);
        columns.get(7).add(1);
        
    }
    
    public void board5Clues(){
        rows.get(0).add(1);
        rows.get(0).add(1);
        rows.get(1).add(1);
        rows.get(1).add(2);
        rows.get(1).add(4);
        rows.get(2).add(5);
        rows.get(2).add(3);
        rows.get(3).add(1);
        rows.get(3).add(3);
        rows.get(3).add(3);
        rows.get(4).add(1);
        rows.get(4).add(7);
        rows.get(4).add(1);
        rows.get(5).add(1);
        rows.get(5).add(9);
        rows.get(6).add(2);
        rows.get(6).add(9);
        rows.get(7).add(4);
        rows.get(7).add(8);
        rows.get(8).add(1);
        rows.get(8).add(3);
        rows.get(8).add(3);
        rows.get(9).add(7);
        rows.get(9).add(1);
        rows.get(10).add(8);
        rows.get(11).add(9);
        rows.get(12).add(8);
        rows.get(13).add(6);
        rows.get(14).add(3);
        
        
        columns.get(0).add(3);
        columns.get(1).add(4);
        columns.get(2).add(3);
        columns.get(2).add(1);
        columns.get(2).add(2);
        columns.get(3).add(3);
        columns.get(3).add(5);
        columns.get(4).add(2);
        columns.get(4).add(2);
        columns.get(4).add(5);
        columns.get(5).add(6);
        columns.get(5).add(5);
        columns.get(6).add(5);
        columns.get(6).add(4);
        columns.get(7).add(5);
        columns.get(7).add(4);
        columns.get(8).add(4);
        columns.get(8).add(4);
        columns.get(9).add(1);
        columns.get(9).add(4);
        columns.get(9).add(4);
        columns.get(10).add(2);
        columns.get(10).add(3);
        columns.get(10).add(3);
        columns.get(11).add(2);
        columns.get(11).add(3);
        columns.get(11).add(3);
        columns.get(12).add(3);
        columns.get(12).add(4);
        columns.get(12).add(3);
        columns.get(13).add(3);
        columns.get(13).add(3);
        columns.get(13).add(2);
        columns.get(14).add(1);
        columns.get(14).add(3);
        columns.get(14).add(1);
    }
    
    public void board6Clues(){
        
    }
    
    public Board() {
        
                System.out.println("Enter board size:");
        
        
        try {
        BufferedReader reader =
                   new BufferedReader(new InputStreamReader(System.in));
        this.size = Integer.parseInt(reader.readLine());
        } catch (Exception e) {
        }
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
        
        for (int i = 0; i < this.size; i++) {
            System.out.println("Enter clues for row " + i);
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String lines = br.readLine();

                String[] strs = lines.trim().split("\\s+");

                for (int j = 0; j < strs.length; j++) {
                    rows.get(i).add(Integer.parseInt(strs[j]));
                }
            } catch (Exception e) {
            }
        }
        
        for (int i = 0; i < this.size; i++) {
            System.out.println("Enter clues for column " + i);
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String lines = br.readLine();

                String[] strs = lines.trim().split("\\s+");

                for (int j = 0; j < strs.length; j++) {
                    columns.get(i).add(Integer.parseInt(strs[j]));
                }
            } catch (Exception e) {
            }
        }
        //board5Clues();
    }
    
    
}
