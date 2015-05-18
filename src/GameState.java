
import java.util.ArrayList;


public class GameState {
	
	private ArrayList<ArrayList<Integer>> board; 
	private int player = 0;
	
	/**
	 * preconditions : player == 0 || player == 1
	 * @param player : int
	 */
	public GameState() {
		this.board = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 7; i++) {
			this.board.add(new ArrayList<Integer>());
		}
	}
	
	/**
	 * preconditions : 	col >= 0 && col <= 6
	 * 					player == 0 || player == 1
	 * 
	 * @param column : int
	 */
	public void add(int column) {
		board.get(column).add(player);
		if (player == 1) {
			player = 0;
		} else {
			player = 1;
		}
		
	}
	
	/**
	 * Removes the last item in a column
	 * 
	 * preconditions : column >= 0 && column <= 6
	 * 				 : column's size >= 1
	 * @param column
	 */
	public void remove(int column){
		//remove the last element of the column
		board.get(column).remove(board.get(column).size() - 1);
		//change players
		if (player == 1) {
			player = 0;
		} else {
			player = 1;
		}
	}
	
	/**
	 * @return int
	 */
	public int getPlayer() {
		return player;
	}
	
	/**
	 * @return ArrayList<ArrayList<Integer>>
	 */
	public ArrayList<ArrayList<Integer>> getBoard(){
		return board;
	}
	
	/**
	 * precondition : col >= 0 && col <= 6
	 * @param col : int
	 * @return boolean
	 */
	public boolean isFull(int col) {
		if(board.get(col).size() == 7) {
			return true;
		}
		return false;
	}
	
}
