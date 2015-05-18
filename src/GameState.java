
import java.util.ArrayList;


public class GameState {
	
	private ArrayList<ArrayList<Integer>> board; 
	private int player = 0;
	private int turn = 0;
	
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
		turn++;
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
	 * 		:turn>0
	 * @param column
	 */
	public void remove(int column){
		//remove the last element of the column
		board.get(column).remove(board.get(column).size() - 1);
		turn--;
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
	 * Check if the whole board is full
	 * 
	 * @return boolean    true if whole board is filled otherwise false
	 */
	public boolean isFull() {
		if (turn >= 42) {	//board is full at 42 turns, ie 7x6
			return true;
		}
		return false;
	}
	
}
