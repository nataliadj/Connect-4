import java.util.ArrayList;

public class AIMed implements AIInterface {
	
	public int decideMove(GameState gs) {
		int computer = gs.getPlayer();
		int human;
		if (computer == 1) {
			human = 0;
		} else {
			human = 1;
		}
		
		int bestMove = 0;
		int bestPriority = 0;
		
		//loop through all possible moves giving each a priority value
		//and recording the move with the highest value
		for (int col = 0; col < 7; col++) {
			int currPriority = 0;
			
			//cant place move when column is full
			if (gs.getBoard().get(col).size() >= 6) {
				System.out.println("full board");
				continue;
			}
			
			//winning is the top priority
			if (winCond(gs, col, computer)) {
				bestMove = col;
				bestPriority = 150;
			}
			
			//blocking the player's win is second priority
			if (winCond(gs, col, human)) {
				bestMove = col;
				bestPriority = 100;
			}
			
			//we run helper functions only after checking win conditions
			//since doing so beforehand is a waste
			
			currPriority += horizontalPriority(gs, col, computer);
			currPriority += verticalPriority(gs, col, computer);
			currPriority += diagonalPriority(gs, col, computer);
			currPriority += horizontalPriority(gs, col, human)/2;
			currPriority += verticalPriority(gs, col, human)/2;
			currPriority += diagonalPriority(gs, col, human)/2;

			//dont make a move that leads to an oponents win
			gs.add(col);
			if (gs.winScan(human)) {
				
				currPriority = 0;
			}
			gs.remove(col);

			//if this move is better than previous moves, switch to this move
			if (currPriority >= bestPriority) {
				bestMove = col;
				bestPriority = currPriority;
			}
		}
		return bestMove;
	}
	
	private int diagonalPriority(GameState gs, int column, int computer) {
		ArrayList<ArrayList<Integer>> board = gs.getBoard();
		int row = board.get(column).size();	//before we make the move hence no '-1'
		int connected = 1;	//amount of connected horizontal tiles, starts at one to count the column tile
		int connected2 = 1;
		int free = 0;
		int free2 = 0;
		int counter = 0;
		
		//counts all the empty tiles and computer tiles left 4 and right 4 of the move
		//stops counting if we encounter a player tile
		
		//check before column
		int break1 = 1;
		int break2 = 1;
		int tempRow = row - 1;
		int tempRow2 = row + 1;
		for (int col = column - 1; col >= 0; col--) {
			counter++;
			
			//make sure row is inside the board
			if (tempRow < 0) {
				break1 = 0;
			}
			if (tempRow2 >= 7) {
				break2 = 0;
			}
			
			if (break1 == 1) {
				// the / check
				if (board.get(col).size() > tempRow) {	//the row is in the column
					if (board.get(col).get(tempRow) == computer) {
						connected ++;	//computer tile
					} else {
						break1 = 0;	//player tile
					}
				} else {
					free++; //empty tile
				}
				
			} else if (break2 == 1) {
				//the \ check
				if (board.get(col).size() > tempRow2) {	//the row is in the column
					if (board.get(col).get(tempRow2) == computer) {
						connected2 ++;	//computer tile
					} else {
						break2 = 0;	//player tile
					}
				} else {
					free2++; //empty tile
				}
			}
			
			
			tempRow--;
			tempRow2++;
			//only check 4 behind
			if (counter >= 4) {
				break;
			}
		}
		
		//check after column
		tempRow = row + 1;
		tempRow2 = row - 1;
		break1 = 1;
		break2 = 1;
		counter = 0;
		for (int col = column - 1; col >= 0; col--) {
			counter++;
			
			//make sure row is inside the board
			if (tempRow >= 6) {
				break1 = 0;
			}
			if (tempRow2 < 0) {
				break2 = 0;
			}
			
			if (break1 == 1) {
				// the / check
				if (board.get(col).size() > tempRow) {	//the row is in the column
					if (board.get(col).get(tempRow) == computer) {
						connected ++;	//computer tile
					} else {
						break1 = 0;	//player tile
					}
				} else {
					free++; //empty tile
				}
				
			} else if (break2 == 1) {
				//the \ check
				if (board.get(col).size() > tempRow2) {	//the row is in the column
					if (board.get(col).get(tempRow2) == computer) {
						connected2 ++;	//computer tile
					} else {
						break2 = 0;	//player tile
					}
				} else {
					free2++; //empty tile
				}
			}
			
			
			tempRow++;
			tempRow2--;
			//only check 4 behind
			if (counter >= 4) {
				break;
			}
		}
		//use information to give priority
		
		//no space for a 4 in a row
		if (free < 4-connected) {
			connected = 0;
		}
		if (free2 < 4-connected2) {
			connected2 = 0;
		}
		
		return connected + connected2;
		
	}
	
	
	/**
	 * Helper function to check whether this move is close to a 4 in a row, horizontally
	 */
	private int horizontalPriority(GameState gs, int column, int computer) {
		ArrayList<ArrayList<Integer>> board = gs.getBoard();
		int row = board.get(column).size();	//before we make the move hence no '-1'
		int connected = 1;	//amount of connected horizontal tiles, starts at one to count the column tile
		int free = 0;
		
		//check the left side of the column
		int horBreak = 1;
		for (int col = column - 1; col >= 0; col--) {
			
			if ((horBreak == 0) && ((row < board.get(col).size()))) {
				break;
			}
			
			//if there is no tile 
			if ((row >= board.get(col).size())) {
				free++;
				horBreak = 0;
			}
			
			//check number of connected to the left
			if ((horBreak == 1) && (board.get(col).get(row) == computer)) {
				connected++;
			} else {
				horBreak = 0;
			}
		}
		
		//check the right side of the column
		horBreak = 1;
		for (int col = column + 1; col < 7; col++) {
			
			if ((horBreak == 0) && ((row < board.get(col).size()))) {
				break;
			}
			
			//if there is no tile 
			if ((row >= board.get(col).size())) {
				free++;
				horBreak = 0;
			}
			
			//check number of connected to the right
			if ((horBreak == 1) && (board.get(col).get(row) == computer)) {
				connected++;
			} else {
				horBreak = 0;
			}
		}
		
		//no horizontal if there is no free space for a 4 in a row
		if (free < 4 - connected) {
			return 0;
		} else {
			//if there can be a 4 in a row return how close we are
			return connected;
		}
	}
	
	/**
	 * Helper function to check whether this move is close to a 4 in a row, Vertically
	 */
	private int verticalPriority(GameState gs, int column, int computer) {
		ArrayList<ArrayList<Integer>> board = gs.getBoard();
		int row = board.get(column).size();	//before we make the move hence no '-1'
		int connected = 1;	//amount of connected vertical tiles, starts at one to count the column tile
		int free = 5 - row;	//free slots left besides our next move
		
		//check how many are connected underneath
		for (int y = row-1; y >= 0; y--) {
			if (board.get(column).get(y) == computer) {
				connected++;
			} else {
				break;
			}
		}
		
		if (free < 4 - connected) {
			//no vertical priority if not enough space for a vertical
			return 0;
		} else {
			//if there is space for a 4 in a row, return how close we are
			return connected;
		}
	}
	
	//instance of winCondition which checks before we've made a move
	private boolean winCond(GameState gs, int column, int player) {
		ArrayList<ArrayList<Integer>> board = new ArrayList<ArrayList<Integer>>(gs.getBoard());
		ArrayList<Integer> columnImp = board.get(column);
		int row = board.get(column).size();
		
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
			if ((col == column) || ((board.get(col).size() >= row+1) && ((board.get(col).get(row) == player)))) {
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
		int tempRow1 = row - 1;	//for "/"
		int tempRow2 = row + 1;	//for "\"
		int diagFind1 = 1;
		int diagFind2 = 1;
		int break1 = 1;
		int break2 = 1;
		for (int col = column-1; col >= 0; col--) {
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


