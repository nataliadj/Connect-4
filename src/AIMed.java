import java.util.ArrayList;


public class AIMed implements AIInterface {
	private int currcol = 0;
	
	public int minimax(GameState gs, int depth, int player) {
		
		//int result = winCond(gs, currcol, player);
				//if (result == 1000)
		if (depth == 3){
			return winCond(gs, currcol, player);
		} //or board is full
			//return heuristic of the best move possible
		int maxScore=Integer.MIN_VALUE;
		int minScore = Integer.MAX_VALUE;
				
		for (int col = 0; col < 6 ; col++){
		
		if (!gs.isFull(col)) {
			if (player == 0){ //Going second
				gs.add(col);
				currcol = col; 
				/*Here i check winning condition, if opponent's winning condition is less than 2 ill leave it as 0
				 *If winning condition for this player is less than 2, ill place bestValue at 500
				 *if more bestValue = 1000
				 *It'll pick the max value
				 */
				int currentScore = minimax(gs, (depth+1), 1);
				maxScore = Math.max(maxScore, currentScore);
				
				if(depth==0){
                    System.out.println("Score for location "+ col +" = "+currentScore);
                 
                    if(currentScore == Integer.MAX_VALUE/2){
                    	//gs.undoMove(col);
                    	break;
                    }
	                }
			} else if (player == 1){
				gs.add(col);
				currcol = col; 
				/*Here i check winning condition, if opponent's winning condition is less than 2 ill leave it as 0
				 *If winning condition for this player is less than 2, ill place bestValue at 500
				 *if more bestValue = 1000
				 *It'll pick the min value
				 */
				 
				int currentScore = minimax(gs, (depth+1), 0);
				
				minScore = Math.min(minScore, currentScore);
			}
			//gs.undoMove(col);
		}
		}
		return 0;
	}
			
			
	/*private int BoardState(GameState gs){
		ArrayList<ArrayList<Integer>> currBoard = gs.getBoard();
		int points;
		int aiPoints;
		int p = 0;
		//i is row, k is col
		for (int i = 0; i < 6; i++){
			for (int k = 0; k < 7; k++){
				if (currBoard.get(i).get(j) == 0) continue; //If its not me (AI)
				if (k <= 3){
					for(p = 1; k < 4; k++){
						if (currBoard.get(i).get(k+p) == 1) aiPoints++;
						else if (currboard.get(i).(k+p) == 0){
							aiPoints = 0;
							break;
						}
					}
				}
			}
		}
		return 0;
	}*/
	
	
	private int winCond(GameState gs, int column, int player) {
		ArrayList<ArrayList<Integer>> board = new ArrayList<ArrayList<Integer>>(gs.getBoard());
		ArrayList<Integer> columnImp = board.get(column);
		int row = board.get(column).size()-1;
		
		//check vertical
		if (row >= 3) {
			if ((columnImp.get(row-1) == player) && (columnImp.get(row-1) == columnImp.get(row-2)) 
					&& (columnImp.get(row-2) == columnImp.get(row-3))) {
				return 1000;
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
				return 1000;
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
			return 1000;
		}
		if (diagFind2 > 3) {
			return 1000;
		}
		
		return 0;
	}


}
