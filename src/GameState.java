import java.util.ArrayList;


public class GameState {
	
	private ArrayList<ArrayList<Integer>> board; 
	private int player;
	
	/**
	 * preconditions : player == 0 || player == 1
	 * @param player : int
	 */
	public GameState(int player) {
		this.player = player;
		this.board = new ArrayList<ArrayList<Integer>>();
	}
	
	/**
	 * preconditions : 	col >= 0 && col <= 6
	 * 					player == 0 || player == 1
	 * @param col : int
	 * @param player : int
	 */
	public void add(int col, int player) {
		board.get(col).add(player);
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
		if(board.get(col).size() == 6) {
			return true;
		}
		return false;
	}


}
