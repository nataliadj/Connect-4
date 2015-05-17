
import java.util.ArrayList;


public class GameEngine {
	private GameState gs = new GameState();
	private Stack<Integer> pastMoves = new Stack<Integer>();	//Stores a list of all moves made
	private Stack<Integer> pastUndoes = new Stack<Integer>();
	private AIMed computer = new AIMed();
	
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
			return (5 - size);
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
		pastMoves.push(column);		//add a pastmove
		
		//empty pastUndoes, cannot redo anything before this
		while (!pastUndoes.empty()) {	
			pastUndoes.pop();
		}
	}
	
	/**
	 * undoes the most recent move
	 */
	public void undoMove() {
		//there must be moves to undo
		if (pastMoves.empty()) {
			return;
		}
		//removes a move from the moves list
		int move = pastMoves.pop();
		
		//add the move to be undo-ed to the undoes list
		pastUndoes.push(move);
		
		//undo the move
		gs.remove(move);
	}
	
	/**
	 * Redoes a move
	 */
	public void redoMove() {
		//a redo must be allowed
		if (pastUndoes.empty()) {
			return;
		}
		
		//removes a move from the undoes list
		int move = pastUndoes.pop();
		
		//add the move to the pastMoves
		pastMoves.push(move);
		
		//make the move
		gs.add(move);
	}
	
	/**
	 * Check if a redo can be done or not
	 * @return	True if a redo can be done, otherwise false
	 */
	public boolean redoAvailable() {
		if (pastUndoes.empty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * placeholder
	 * @return
	 */
	public int callAi() {
		return computer.minimax(gs, 0, getPlayer());
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
			if ((columnImp.get(row-1) == player) && (columnImp.get(row-1) == columnImp.get(row-2)) 
					&& (columnImp.get(row-2) == columnImp.get(row-3))) {
				return true;
			}
		}
		
		//check horizontal
		int horizontalFind = 0;
		for (int col = 0; col < 7; col++) {
			if ((board.get(col).size() >= row+1) && board.get(col).get(row) == player) {
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
			if ((tempRow1 >= board.get(col).size()) || (tempRow1 < 0)) {
				break1 = 0;
			}
			if ((tempRow2 >= board.get(col).size()) || (tempRow2 < 0)) {
				break2 = 0;
			}
			
			if ((break1 == 1) && (board.get(col).get(tempRow1) == player)) {
				diagFind1++;
			} else {
				break1 = 0;
			}
			
			if ((break2 == 1) && (board.get(col).get(tempRow2) == player)) {
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
		for (int col = column+1; col < 7; col++) {
			if ((tempRow1 >= board.get(col).size()) || (tempRow1 < 0)) {
				break1 = 0;
			}
			if ((tempRow2 >= board.get(col).size()) || (tempRow2 < 0)) {
				break2 = 0;
			}
			if ((break1 == 1) && (board.get(col).get(tempRow1) == player)) {
				diagFind1++;
			} else {
				break1 = 0;
			}
			
			if ((break2 == 1) && (board.get(col).get(tempRow2) == player)) {
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
//Have AI easy and AI med. AI easy is literally jsut random columns Having trouble with inivisbly inserted stuff. AI med has only the barebones of the minimax algo TODO needs a boardstate (400+ lines) function TODO AI hard = minimax + optimisation + heuristics
