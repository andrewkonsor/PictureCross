
import java.util.ArrayList;
import java.util.List;

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

        for (int i = 0; i < row.length; i++) {
            if (row[i] == 'x') {
                numFilledSpots++;
            } else {
                break;
            }
        }

        for (int i = 0; i < row.length; i++) {
            if (row[row.length - i - 1] == 'x') {
                numFilledSpots++;
            } else {
                break;
            }
        }

        for (int i = 0; i < row.length - 1; i++) {
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
    
    /**
     * Solve as many cells for singled clues
     * @param board 
     */
    public void boardPartialSingleClue(Board board){

        for (int r : (ArrayList<Integer>) board.getIncompletedRows()) {
            
            char[] newRow = partialSingleClue(board.getClueRows().get(r), board.getRow(r));
            board.changeRow(r, newRow);           
        }


        for (int c : (ArrayList<Integer>) board.getIncompletedColumns()) {
                
            char[] newCol = partialSingleClue(board.getClueColumns().get(c), board.getColumn(c));
            board.changeColumn(c, newCol);     
        }
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
    
    
    /*public boolean boardEdgeSolve (Board board){
        
    }*/

    public boolean puzzleSolved(Board board) {
        return board.getIncompletedColumns().isEmpty() && board.getIncompletedColumns().isEmpty();
    }

    public static void main(String[] args) {
        SolvingFunctions solver = new SolvingFunctions();
        List<Integer> clue = new ArrayList<>();
        String s = "--oo-o--";
        char [] row = s.toCharArray();
        char [] result = new char[row.length]; 
        int size = 8;
        clue.add(4);

        boolean hasGap=false;
 
        System.out.println(solver.fillSingleClueWithGaps(clue, row));

    }

}
