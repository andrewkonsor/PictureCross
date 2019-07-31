
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Andrew
 * @version Jun 6, 2019
 */
public class SolvingFunctions {

    /**
     * Function solves rows which have rows that are given at the start row only
     * has one possible solution
     *
     * @param clue
     * @param row
     * @return
     */
    public char[] givenRow(List<Integer> clue, char[] row) {
        int size = row.length;
        int count = 0;
        for (int x : clue) {
            for (int i = 0; i < x; i++) {
                row[count] = 'o';
                count++;
            }
            if (count < size) {
                row[count] = 'x';
                count++;
            }
        }
        return row;
    }

    /**
     * Solve all the given clues
     * @param board 
     */
    public void boardGivenRows(Board board) {

        System.out.println("\nSolve Given R/C");

        for (int i = 0; i < board.getSize(); i++) {
            char[] r = board.getRow(i);
            char[] c = board.getColumn(i);

            if (canSolve(board.getClueRows().get(i), r)) {
                char[] solvedRow = givenRow(board.getClueRows().get(i), r);
                board.changeRow(i, solvedRow);
                board.removeCompletedRows(i);
            }

            if (canSolve(board.getClueColumns().get(i), c)) {
                char[] solvedColumn = givenRow(board.getClueColumns().get(i), c);
                board.changeColumn(i, solvedColumn);
                board.removeCompletedColumns(i);
            }

        }
        Printing.printBoard(board);

    }

    /**
     * Check if the row can be solved given the clue
     *
     * @param clue
     * @param row
     * @return boolean
     */
    public boolean canSolve(List<Integer> clue, char[] row) {
        int size = row.length;
        int numFilledSpots = 0;
        int beggining=0;
        int ending=0;
        
        for (int i = 0; i < row.length; i++) {
            if (row[i] == 'x') {
                numFilledSpots++;
                beggining++;
            } else {
                break;
            }
        }

        for (int i = 0; i < row.length; i++) {
            if (row[row.length - i - 1] == 'x') {
                numFilledSpots++;
                ending++;
            } else {
                break;
            }
        }

        for (int i = beggining; i < row.length - ending; i++) {
            if (row[i] == 'x' && row[i + 1] == 'x') {
                numFilledSpots++;
            }
        }

        for (int i = 0; i < clue.size(); i++) {
            numFilledSpots = numFilledSpots + clue.get(i);
        }

        return numFilledSpots + clue.size() - 1 == size;
    }

    public char[] solveRow(List<Integer> clue, char[] row) {
        char[] result = new char[row.length];
        if (canSolve(clue, row)) {
            for (int i = 0; i < row.length; i++) {
                if (row[i] != 'x') {
                    result[i] = 'o';
                } else {
                    result[i] = 'x';
                }
            }

        }
        return result;
    }

    public boolean boardSolveRows(Board board) {
        boolean isChanged = false;

        ArrayList<Integer> completed = new ArrayList<>();
        for (int r : (ArrayList<Integer>) board.getIncompletedRows()) {
            if (canSolve(board.getClueRows().get(r), board.getRow(r))) {
                char[] newRow = solveRow(board.getClueRows().get(r), board.getRow(r));
                board.changeRow(r, newRow);
                completed.add(r);
                //System.out.println("Solved Row " + r);
                Printing.printBoard(board);
            }
        }

        if (completed.size() > 0) {
            isChanged = true;
        }
        for (int r : completed) {
            board.removeCompletedRows(r);
        }

        for (int c : (ArrayList<Integer>) board.getIncompletedColumns()) {
            if (canSolve(board.getClueColumns().get(c), board.getColumn(c))) {
                char[] newCol = solveRow(board.getClueColumns().get(c), board.getColumn(c));
                board.changeColumn(c, newCol);
                completed.add(c);
                System.out.println("Solved Column " + c);
                Printing.printBoard(board);
            }
        }

        if (completed.size() > 0) {
            isChanged = true;
        }
        for (int c : completed) {
            board.removeCompletedColumns(c);
        }

        if (isChanged) {
            System.out.println("Solve R/C");
            Printing.printBoard(board);
        }
        return isChanged;
    }

    
    //Functions to solve rows with single clues
    
    
    
    
    /**
     * Solve cells for partially solved clues
     * @param clue
     * @param row
     * @return 
     */
    public char[] partialSingleClue(List<Integer> clue, char[] row) {

        
        if (clue.size() == 1 && row.length - clue.get(0) < (double) row.length / 2) {
            int fill = clue.get(0) - (row.length - clue.get(0));
            int empty = row.length - clue.get(0);
            
            for (int i = empty; i < fill + empty; i++) {
                if (!Board.validChange(row[i], 'o')) {
                    
                    break;
                }
                row[i] = 'o';
            }
        }
        return row;
    }
    
    
    public char[] partialClue(List<Integer> clue, char[] row) {
        
        int start = 0;
        int end = 0;
        int numOfClues = clue.size();
        int currentClue = 0;
        char[] result = row;

        for (int c : clue) {
            end = end + c;
        }
        end = end + numOfClues - 1;

        for (int c : clue) {
            
            List<Integer> l = new ArrayList<>();
            end = end - clue.get(currentClue);
            
            char[] temp = new char[row.length - end - start];
            for (int i = 0; i < row.length - end - start; i++) {
                temp[i] = '-';
            }
            int count = 0;

            l.add(c);
            temp = partialSingleClue(l, temp);
            for (int i = start; i < row.length - end; i++) {
                result[i] = temp[count];
                count++;
            }

            //reset for next clue
            
            start = clue.get(currentClue) + 1 + start;
            end--; 
            currentClue++;
            if (numOfClues==currentClue) break;

        }

        return result;
    }
    
    /**
     * Solve as many cells for singled clues
     * @param board 
     */
    public void boardPartialClue(Board board){

        for (int r : (ArrayList<Integer>) board.getIncompletedRows()) {
            
            char[] newRow = partialSingleClue(board.getClueRows().get(r), board.getRow(r));
            board.changeRow(r, newRow);           
        }


        for (int c : (ArrayList<Integer>) board.getIncompletedColumns()) {
                
            char[] newCol = partialSingleClue(board.getClueColumns().get(c), board.getColumn(c));
            board.changeColumn(c, newCol);     
        }
        
        System.out.println("Partially solve single clues");
        Printing.printBoard(board);
    }
    
    
    

    public boolean isSingleClueWithGaps(List<Integer> clue, char[] row) {
        boolean hasGap = false;
        String s = new String(row);
        if (clue.size() == 1) {

            int first = s.indexOf("o");
            int last = s.lastIndexOf("o");
            if (first != last) {
                for (int i = 0; i < last - first - 1; i++) {
                    if (row[first + i + 1] == '-') {
                        return true;
                    }

                }
            }

        }
        return hasGap;
    }

    public char[] fillSingleClueWithGaps(List<Integer> clue, char[] row) {
        char[] result = row;
        String s = new String(row);
        int first = s.indexOf("o");
        int last = s.lastIndexOf("o");

        for (int i = 0; i < last - first - 1; i++) {
            result[first+i + 1] = 'o';
        }

        return result;
    }
    
    public boolean boardFillSingleClueWithGaps(Board board) {
        boolean isChanged = false;

        for (int r : (ArrayList<Integer>) board.getIncompletedRows()) {

            if (isSingleClueWithGaps(board.getClueRows().get(r), board.getRow(r))) {
                char[] newRow = fillSingleClueWithGaps(board.getClueRows().get(r), board.getRow(r));
                board.changeRow(r, newRow);
                isChanged=true;
            }

        }


        for (int c : (ArrayList<Integer>) board.getIncompletedColumns()) {
                
            if (isSingleClueWithGaps(board.getClueColumns().get(c), board.getColumn(c))){
                char[] newCol = fillSingleClueWithGaps(board.getClueColumns().get(c), board.getColumn(c));
                board.changeColumn(c, newCol);
                isChanged=true;
            }
              
        }
        
        if (isChanged){
            System.out.println("Fill Gaps in Single Clue R/C");
            Printing.printBoard(board);
        }
        
        return isChanged;
    }
    
    
    public boolean isSingleClueCapped (List<Integer> clue, char[] row){
        String r = new String(row);
        return (r.contains("ox") || r.contains("xo")) && clue.size()==1;
    }
    
    public char [] solveSingleClueCapped (List<Integer> clue, char[] row){
        char [] result = new char[row.length];
        if (isSingleClueCapped(clue, row)){
            String r = new String(row);
            int start = r.indexOf("xo");
            int end = r.indexOf("ox");
            int c = clue.get(0);
            
            if (start>-1){
                
                for (int i=0;i<row.length;i++){
                    if (i<=start)result[i]='x';
                    else if (i<=start+c) result[i] = 'o';
                    else result[i]= 'x';
                }
            }
            
            else if (end>-1){
                for (int i=0;i<row.length;i++){
                    if (i<=end-c)result[i]='x';
                    else if (i<=end) result[i] = 'o';
                    else result[i]= 'x';
                }
                
            }
            
            
        }
        
        return result;
    }
    
    public boolean boardSolveSingleClueCapped (Board board){
        boolean changed = false;
        ArrayList<Integer> completed = new ArrayList<>();

        for (int r : (ArrayList<Integer>) board.getIncompletedRows()) {
            if (isSingleClueCapped(board.getClueRows().get(r), board.getRow(r))) {
                char[] newRow = solveSingleClueCapped(board.getClueRows().get(r), board.getRow(r));
                board.changeRow(r, newRow);
                completed.add(r);
            }

        }
        if (completed.size() > 0) {
            changed = true;
        }
        for (int r : completed) {
            board.removeCompletedRows(r);
        }

        completed.clear();

        for (int c : (ArrayList<Integer>) board.getIncompletedColumns()) {
            if (isSingleClueCapped(board.getClueColumns().get(c), board.getColumn(c))) {
                char[] newCol = solveSingleClueCapped(board.getClueColumns().get(c), board.getColumn(c));
                board.changeColumn(c, newCol);
                completed.add(c);
            }
        }

        if (completed.size() > 0) {
            changed = true;
        }
        for (int c : completed) {
            board.removeCompletedColumns(c);
        }

        if (changed) {
            System.out.println("Solve Completed R/C");
            Printing.printBoard(board);
        }
        return changed;
    }
    
    

    /**
     * Is Correct
     *
     * @param clue
     * @param row
     * @return
     */
    public boolean isFinished(List<Integer> clue, char[] row) {

        int count = 0;
        List<Integer> resultList = new ArrayList<>();

        for (int c = 0; c < row.length; c++) {
            if (row[c] == 'o') {
                count++;
                if (c == row.length - 1) {
                    resultList.add(count);
                }
            } else if (count > 0) {
                resultList.add(count);
                count = 0;
            }
        }

        return resultList.equals(clue);
    }

    public char[] completeRow(List<Integer> clue, char[] row) {
        char[] result = new char[row.length];
        if (isFinished(clue, row)) {
            for (int i = 0; i < row.length; i++) {
                if (row[i] == '-') {
                    result[i] = 'x';
                } else if (row[i] == 'o') {
                    result[i] = 'o';
                } else {
                    result[i] = 'x';
                }
            }
        }
        return result;
    }

    public boolean boardCompleteRows(Board board) {
        boolean changed = false;
        ArrayList<Integer> completed = new ArrayList<>();

        for (int r : (ArrayList<Integer>) board.getIncompletedRows()) {
            if (isFinished(board.getClueRows().get(r), board.getRow(r))) {
                char[] newRow = completeRow(board.getClueRows().get(r), board.getRow(r));
                board.changeRow(r, newRow);
                completed.add(r);
            }

        }
        if (completed.size() > 0) {
            changed = true;
        }
        for (int r : completed) {
            board.removeCompletedRows(r);
        }

        completed.clear();

        for (int c : (ArrayList<Integer>) board.getIncompletedColumns()) {
            if (isFinished(board.getClueColumns().get(c), board.getColumn(c))) {
                char[] newCol = completeRow(board.getClueColumns().get(c), board.getColumn(c));
                board.changeColumn(c, newCol);
                completed.add(c);
            }
        }

        if (completed.size() > 0) {
            changed = true;
        }
        for (int c : completed) {
            board.removeCompletedColumns(c);
        }

        if (changed) {
            System.out.println("Solve Completed R/C");
            Printing.printBoard(board);
        }
        return changed;
    }
    
    public boolean canEdgeSolve (char [] row){
        return row[0] == 'o' || row[row.length-1]== 'o';
    }

    public char[] edgeSolve(List<Integer> clue, char[] row) {
        char[] result = row;
        int firstClue = clue.get(0);
        int lastClue = clue.get(clue.size() - 1);

        for (int i = 0; i < firstClue; i++) {
            if (row[i] == 'o') {
                for (int j = 0; j < firstClue - i; j++) {
                    result[j + i] = 'o';
                }
                if (row[0] == 'o' && firstClue < row.length) {
                    result[firstClue] = 'x';
                }
                break;
            }

        }
        

        for (int i = 0; i < lastClue; i++) {
            if (row[row.length - i - 1] == 'o') {
                for (int j = 0; j < lastClue - i; j++) {
                    result[row.length - i - j - 1] = 'o';
                }
                if (row[row.length - 1] == 'o' && lastClue < row.length) {
                    result[row.length - lastClue - 1] = 'x';
                }
                break;
            }
        }

        return result;
    }
    
    
    public boolean boardEdgeSolve (Board board){

        boolean changed=false;
        for (int r : (ArrayList<Integer>) board.getIncompletedRows()) {
            if (canEdgeSolve(board.getRow(r))) {
                char[] newRow = edgeSolve(board.getClueRows().get(r), board.getRow(r));
                board.changeRow(r, newRow);
                changed=true;
            }

        }

        for (int c : (ArrayList<Integer>) board.getIncompletedColumns()) {
            if (canEdgeSolve(board.getColumn(c))) {
                char[] newCol = edgeSolve(board.getClueColumns().get(c), board.getColumn(c));
                board.changeColumn(c, newCol);
                changed=true;
            }
        }

        if (changed) {
            System.out.println("Solve Edge R/C");
            Printing.printBoard(board);
        }
        return changed;
    }

    public boolean puzzleSolved(Board board) {
        return board.getIncompletedColumns().isEmpty() && board.getIncompletedColumns().isEmpty();
    }

    public ArrayList<char[]> possibleSolutions(List<Integer> clues, char[] row, int start, int clueIndex) {
        //System.out.println("Possible Solutions for depth: " + clueIndex );
        //char [] temp = new char[row.length];
        int currentClue = clueIndex;
        int numberOfPossilities;
        int end = row.length;
        int numberOfClues = clues.size();
        int remainingClues = numberOfClues - currentClue - 1;

        ArrayList<char[]> possibleSolList = new ArrayList<>();

        for (int i = currentClue + 1; i < numberOfClues; i++) {

            end = end - clues.get(i);

        }
        end = end - remainingClues;

        numberOfPossilities = end - start - clues.get(currentClue) + 1;
        for (int i = 0; i < numberOfPossilities; i++) {//number of possible spots for row
            char[] temp = row.clone();
            for (int j = 0; j < clues.get(currentClue); j++) {
                if (start + j < temp.length) {
                    if (row[start + j] == 'x') {
                        break;
                    }
                    temp[start + j] = 'o';
                }

            }
            //currentClue++;
            if (currentClue == numberOfClues - 1) {
                //check if temp is correct with clues
                if (isFinished(clues, temp)) {
                    for (int z = 0; z < temp.length; z++) {
                        if (temp[z] == '-') {
                            temp[z] = 'x';
                        }
                    }
                    possibleSolList.add(temp);
                    //System.out.println("Possile Sol added to");
                } else {
                    //System.out.println(temp);
                    
                }
            }else {
                //int nextClue = currentClue+1;
                possibleSolList.addAll(possibleSolutions(clues, temp, start + clues.get(currentClue), currentClue+1));
            }
            
            start++;
        }

        return possibleSolList;
    }
    
    public char [] possiblitiesCheck (List <char []> possibleList, char [] row){
        boolean [] o = new boolean[row.length];
        Arrays.fill(o, Boolean.TRUE);
        boolean [] x = new boolean[row.length] ;
        Arrays.fill(x, Boolean.TRUE);
        char [] result = new char [row.length];
        result= row.clone();
        for (char [] p : possibleList){
            for (int i=0; i<row.length;i++){
                if (o[i]&& p[i]!='o'){
                    o[i]=false;
                }else if (x[i] && p[i]!='x'){
                    x[i]= false;
                }
            }
        }
        
        for (int i=0;i<row.length;i++){
            if (o[i]) result[i]='o';
            else if (x[i]) result [i]='x';
        }
        return result;
        
    }
    
        public boolean boardPossiblitiesCheck (Board board){

        boolean changed=false;
        for (int r : (ArrayList<Integer>) board.getIncompletedRows()) {
            
            //char[] newRow = edgeSolve(board.getClueRows().get(r), board.getRow(r));
            char[] newRow = possiblitiesCheck(possibleSolutions(board.getClueRows().get(r), board.getRow(r), 0, 0), board.getRow(r));
            if (!Arrays.equals(newRow, board.getRow(r))){
                System.out.print("PF Row: " + r + " solved to ");
                for (char c: newRow) System.out.print(c);
                System.out.println("");
                board.changeRow(r, newRow);
                changed=true;
            } 
        }

        for (int c : (ArrayList<Integer>) board.getIncompletedColumns()) {
            
                char[] newCol = possiblitiesCheck(possibleSolutions(board.getClueColumns().get(c), board.getColumn(c), 0, 0), board.getColumn(c));
                if (!Arrays.equals(newCol, board.getColumn(c))){
                //System.out.print("PF Col: " + c + " solved to ");
                    for (char d: newCol) System.out.print(d);
                    System.out.println("");
                board.changeColumn(c, newCol);
                changed=true;
                }
            
        }

        if (changed) {
            System.out.println("Solve Possibilities");
            Printing.printBoard(board);
        }
        return changed;
    }
}
    
