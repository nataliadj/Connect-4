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
	 * Check the latest move to see if a player has won the game
	 * 
	 * PRECONDITION: column >= 0 && column < 7
	 * @param column	The column of the most recent move 
	 * @param player	Player whom made the move being checked
	 * @return			true if there are 4 in a row around the new move, otherwise false
	 */
	public boolean winCond(int column, int player) {
		ArrayList<ArrayList<Integer>> board = new ArrayList<ArrayList<Integer>>(gs.getBoard());
		ArrayList<Integer> columnImp = board.get(column);
		int row = board.get(column).size()-1;
		
		//check vertical
		if (row >= 3) {
			if ((columnImp.get(row-1) == columnImp.get(row-2)) 
					&& (columnImp.get(row-2) == columnImp.get(row-3))) {
				return true;
			}
		}
		
		//check horizontal
		int horizontalFind = 0;
		for (int col = 0; col < 6; col++) {
			System.out.println(col + " " + row);
			if (!board.get(col).isEmpty() && board.get(col).get(row) == player) {
				horizontalFind ++;
			} else {
				horizontalFind = 0;
			}
			
			if (horizontalFind == 4) {
				return true;
			}
		}
		
		//check diagonal; The following two 'for' loops will loop through
		//the diagonals '/' and '\' of the most recent move to check if it won the game
		int tempRow1 = row;	//for "/"
		int tempRow2 = row;	//for "\"
		int diagFind1 = 0;
		int diagFind2 = 0;
		int break1 = 1;
		int break2 = 1;
		for (int col = column; col >= 0; col--) {
			if ((tempRow1 > 6) || (tempRow1 < 0)) {
				break;
			}
			if ((tempRow2 > 6) || (tempRow2 < 0)) {
				break;
			}
			
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
		
		tempRow1 = row +1;
		tempRow2 = row -1;
		break1 = 1;
		break2 = 1;
		for (int col = column+1; col < 6; col++) {
			if ((tempRow1 > 6) || (tempRow1 < 0)) {
				break;
			}
			if ((tempRow2 > 6) || (tempRow2 < 0)) {
				break;
			}
			if ((!board.get(col).isEmpty()) && (board.get(col).get(tempRow1) == player) && (break1 == 1)) {
				diagFind1++;
			} else {
				break1 = 0;
			}
			
			if ((!board.get(col).isEmpty()) && (board.get(col).get(tempRow2) == player) && (break2 == 1) ) {
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
		
		if (diagFind1 > 3) {
			return true;
		}
		if (diagFind2 > 3) {
			return true;
		}
		
		return false;
	}
}
