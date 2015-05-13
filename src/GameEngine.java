import java.util.ArrayList;


public class GameEngine {
	private GameState gs = new GameState();
	private AI computer = new AI();
	
	//place constructor here
	
	/**
	 * Checks if a move is valid
	 * @param column	The column the move is inserting into
	 * @return	The row to insert into if the move is valid, otherwise -1
	 */
	public int validMove(int column) {
		ArrayList<ArrayList<Integer>> board = gs.getBoard();
		int size = board.get(column).size();
		if (size < 7) {
			return (6 - size);
		}
		return -1;
	}
	
	public int getPlayer() {
		return gs.getPlayer();
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
		
		//check diagonal
		int tempRow1 = row;	//for "/"
		int tempRow2 = row;	//for "\"
		int diagFind1 = 0;
		int diagFind2 = 0;
		int break1 = 1;
		int break2 = 1;
		for (int col = column; col >= 0; col--) {
			if ((board.get(col).get(tempRow1) == player) && (break1 == 1)) {
				diagFind1++;
			} else {
				break1 = 0;
			}
			
			if ((board.get(col).get(tempRow2) == player) && (break2 == 1) ) {
				diagFind2++;
			} else {
				break2 = 0;
			}
			
			if ((break2 == 0) && (break1 == 0)) {
				break;
			}
			tempRow1--;
			tempRow2++;
		}
		
		tempRow1 = row;
		tempRow2 = row;
		for (int col = column; col < 7; col++) {
			if ((board.get(col).get(tempRow1) == player) && (break1 == 1)) {
				diagFind1++;
			} else {
				break1 = 0;
			}
			
			if ((board.get(col).get(tempRow2) == player) && (break2 == 1) ) {
				diagFind2++;
			} else {
				break2 = 0;
			}
			
			if ((break2 == 0) && (break1 == 0)) {
				break;
			}
			tempRow1++;
			tempRow2--;
		}
		
		if (tempRow1 > 3) {
			return true;
		}
		if (tempRow2 > 3) {
			return true;
		}
		
		return false;
	}
}
