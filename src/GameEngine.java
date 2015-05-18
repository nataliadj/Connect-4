
import java.util.ArrayList;
import java.util.Stack;


public class GameEngine {
	private GameState gs = new GameState();
	private Stack<Integer> pastMoves = new Stack<Integer>();	//Stores a list of all moves made
	private Stack<Integer> pastUndoes = new Stack<Integer>();
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
	 * @precondition !pastMoves.isEmpty() 
	 * @return the column of move undone
	 */
	public int undoMove() {
		
		//removes a move from the moves list
		int move = pastMoves.pop();
		
		//add the move to be undo-ed to the undoes list
		pastUndoes.push(move);
		
		//undo the move
		gs.remove(move);
		
		return move;
	}

	/**
	 * Check if an undo can be done or not
	 * @return	True if an undo can be done, otherwise false
	 */
	public boolean undoAvailable() {
		if (pastMoves.isEmpty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * Redoes a move
	 * @precondition !pastUndoes.isEmpty()
	 * @return the column of the move redone
	 */
	public int redoMove() {
		
		//removes a move from the undoes list
		int move = pastUndoes.pop();
		
		//add the move to the pastMoves
		pastMoves.push(move);
		
		//make the move
		gs.add(move);
		
		return move;
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
		return computer.decideMove(gs);
	}
	
	public boolean checkWinCond(int column, int player) {
		return gs.winCond(column, player);
	}
	
	public boolean checkDrawCond() {
		return gs.drawCond();
	}
}
