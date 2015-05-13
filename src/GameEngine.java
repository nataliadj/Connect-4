
import java.util.ArrayList;


public class GameEngine {
	GameState gs;
	AI computer;
	
	//place constructor here
	
	/**
	 * Checks if a move is valid
	 * @param column	The column the move is inserting into
	 * @return	The row to insert into if the move is valid, otherwise -1
	 */
	public int validMove(int column) {
		ArrayList<ArrayList<Integer>> board = gs.getBoard();
		int size = board.get(column).size();
		if (size < 6) {
			return size + 1;
		}
		return -1;
	}
	
	/**
	 * makes a move
	 * PRECONDITION: column >= 0 && column < 7

	 * @param column	The column to add to
	 */
	public void makeMove(int column) {
		gs.add(column);
	}
	
	/**
	 * placeholder
	 * @return
	 */
	public int callAi() {
		return computer.decideMove(gs);
	}
	
	/**
	 * 
	 * PRECONDITION: column >= 0 && column < 7
	 * @param column
	 * @param player	Player whom made the move being checked
	 * @return
	 */
	public boolean winCond(int column, int player) {
		ArrayList<ArrayList<Integer>> board = gs.getBoard();
		ArrayList<Integer> columnImp = board.get(column);
		int row = board.get(column).size() + 1;
		
		//check vertical
		if (row >= 3) {
			if ((columnImp.get(row-1) == columnImp.get(row-2)) 
					&& (columnImp.get(row-2) == columnImp.get(row-3))) {
				return true;
			}
		}
		
		//check horizontal
		int horizontalFind = 0;
		for (int col = 0; col < 7; col++) {
			if (board.get(col).get(row) == player) {
				horizontalFind ++;
			} else {
				horizontalFind = 0;
			}
			
			if (horizontalFind == 4) {
				return true;
			}
		}
		
		int tempRow1 = row;	//for "/"
		int tempRow2 = row;	//for "\"
		int diagFindOne = 0;
		int diagFindTwo = 0;
		int break1;
		int break2;
		for (int col = column; col >= 0; col--) {
			
			
			
			tempRow1--;
			tempRow2++;
		}
	}
}

