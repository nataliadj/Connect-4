
import java.util.ArrayList;


public class GameState {
	
	private ArrayList<ArrayList<Integer>> board; 
	private int player;
	private int turn;
	
	/**
	 * preconditions : player == 0 || player == 1
	 * @param player : int
	 */
	public GameState() {
		this.board = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 7; i++) {
			this.board.add(new ArrayList<Integer>());
		}
		this.player = 0;
		this.turn = 0;
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
	 * add to bottom of column
	 * @param column	column to add to
	 */
	public void push(int column) {
		board.get(column).add(player, 0);
		turn ++;
		//change players
		if (player == 1) {
			player = 0;
		} else {
			player = 1;
		}
	}
	
	/**
	 * removes the first item in the board
	 * PRECONDITION, The item removed is the players color
	 * 				There is an item in the board
	 * @param column
	 */
	public void popOut(int column) {
		board.get(column).remove(0);
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
	
	/**
	 * Check the latest move to see if a player has won the game
	 * 
	 * PRECONDITION: column >= 0 && column < 7
	 * @param column	The column of the most recent move 
	 * @param player	Player whom made the move being checked
	 * @return			true if there are 4 in a row around the new move, otherwise false
	 */
	public boolean winCond(int column, int player) {
		//ArrayList<ArrayList<Integer>> board = new ArrayList<ArrayList<Integer>>(gs.getBoard());
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
	

	/**
	 * Full board scan for win condition
	 * @param tile	The player's tiles you are checking
	 * @return	true if there is a 4 in a row otherwise false
	 */
	public boolean winScan(int tile) {
		//search through the board for any 4 in a rows
		//horizontal
		for (int row = 0; row < 6; row++) {
				int count = 0;
				for (int col = 0; col <7; col++) {
					if (row >= board.get(col).size()) {
						count = 0;
						continue;
					}
					if (board.get(col).get(row) == tile) {
						count++;
					} else {
						count = 0;
					}
					if (count >= 4) {
						return true;
					}
				}		
		}
		
		//vertical
		for (int col =0; col<7;col++) {
			int count = 0;
			for (int row = 0; row < 6; row++) {
				if (board.get(col).size() <= row) {
					break;
				}
				if (board.get(col).get(row) == tile) {
					count++;
				} else {
					count = 0;
				}
				if (count >= 4) {
					return true;
				}
			}
		}
		
		//diagonal
		for (int col=0; col<7;col++) {
			for (int row = 0; row <6; row++) {
				if (row >= board.get(col).size()) {
					break;
				}
				int count = 0;
				//bounds checks
				for (int x = 0; x <6; x++) {
					if ((col + x) > 6) {
						continue;
					}
					if (board.get(col+x).size() <= (row + x)) {
						continue;
					}
					
					//scan for 4 in a row
					// the / diagonal
					if (board.get(col+x).get(row+x) == tile) {
						count++;
					} else {
						count = 0;
					}
					
					if (count >= 4) {
						return true;
					}
				}
				
				count = 0;
				//the \ diagonal
				for (int x = 0; x <6; x++) {
					if ((col + x) > 6) {
						continue;
					}
					if (board.get(col+x).size() <= (row - x)) {
						continue;
					}
					if ((row - x) < 0) {
						continue;
					}
					
					//scan for 4 in a row
					// the / diagonal
					if (board.get(col+x).get(row-x) == tile) {
						count++;
					} else {
						count = 0;
					}
					
					if (count >= 4) {
						return true;
					}
				}
				
			}
		}
		return false;
	}
}
