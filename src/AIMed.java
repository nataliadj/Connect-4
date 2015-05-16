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
                    	gs.undoMove(col);
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
			gs.undoMove(col);
		}
		}
		return 0;
	}
			
			
	private int boardstate(GameState gs){
		ArrayList<ArrayList<Integer>> currBoard = gs.getBoard();
		int plpoints = 0;
		int aiPoints = 0;
		//i is row, k is col
		for (int i = 0; i < 6; i++){
			for (int k = 0; k < 7; k++){
				if (currBoard.get(i).get(k) == 0) continue; //If its not me (AI)
				
				/*
				 * Checks column, works for all columns
				 * Currently ignores the top 3 rows
				 */
				if (i < 3){
					for (int p = 1; p < 4; p++) {
						if (currBoard.get(i+p).get(k) == 1) 
							aiPoints++;
						else if (currBoard.get(i+p).get(k) == 0) 
							plpoints++;
						else break;
					}
					if (plpoints == 4)
						return 2;
					if (aiPoints == 4)
						return 1;
					aiPoints = 0;
					plpoints = 0;
				}
				
				/*
				 * Check right-up diagonal
				 * Left side
				 */
				if(k <= 3 && i < 3){
					for (int p = 1; p < 4; p++) {
						if (currBoard.get(i+p).get(k+p) == 1) aiPoints++;
						else if (currBoard.get(i+p).get(k+p) == 0) plpoints++;
						else break;		
					}
					if (plpoints == 4)
						return 2;
					if (aiPoints == 4)
						return 1;
					aiPoints = 0;
					plpoints = 0;
				}
				
				/*
				 * Check Horizontal
				 * Left Side
				 */
				if (k <= 3){
					for (int p = 1; p < 4; p++) {
						if (currBoard.get(i).get(k+p) == 1) aiPoints++;
						else if (currBoard.get(i).get(k+p) == 0) plpoints++;
						else break;
					}
					if (plpoints == 4)
						return 2;
					if (aiPoints == 4)
						return 1;
					aiPoints = 0;
					plpoints = 0;
				}
				
				/*
				 * Check Horizontal
				 * Right side
				 */
				if (k >= 3){
					for (int p = 1; p < 4; p++) {
						if (currBoard.get(i).get(k-p) == 1) aiPoints++;
						else if (currBoard.get(i).get(k-p) == 0) plpoints++;
						else break;
					}					
					if (plpoints == 4)
						return 2;
					if (aiPoints == 4)
						return 1;
					aiPoints = 0;
					plpoints = 0;
				}
				
				/*
				 * Left up diagonal
				 * RIght side
				 */
				if (k >= 3){
					for (int p = 1; p < 4; p++){
						if (currBoard.get(i+p).get(k-p) == 1) aiPoints++;
						else if (currBoard.get(i+p).get(k-p) == 0) plpoints++;
						else break;
					}
					if (plpoints == 4)
						return 2;
					if (aiPoints == 4)
						return 1;
					aiPoints = 0;
					plpoints = 0;
				}					
			}
		}
		return 0;
	}
	
	
	private int evalboard(GameState gs){
		ArrayList<ArrayList<Integer>> currBoard = gs.getBoard();
		int aiScore = 0;
		int nulltiles = 0;
		int remainingmoves = 0;
		int currScore = 0;
		//i is row, k is col
		for (int i = 0; i < 6; i++){
			for (int k = 0; k < 7; k++){
				if (currBoard.get(i).get(k) == 0) continue; //If its not me (AI)
				
				/*
				 * Checks column, works for all columns
				 * Currently ignores the top 3 rows and only checks upwards direction
				 */
				if (i < 3){
					for (int p = 1; p < 4; p++) {
						if (currBoard.get(i+p).get(k) == 1) 
							aiScore++;
						else if (currBoard.get(i+p).get(k) == 0){
							aiScore = 0;
							nulltiles = 0;
							break;
						} else {
							nulltiles++; 
						}
						remainingmoves = 0;
						if (aiScore > 0){
							for (int m = i+p; m < i; m++){
								if (currBoard.get(m).get(k) != 1 && currBoard.get(m).get(k) != 0){
									remainingmoves++;
								} else {
									break;
								}
							}
						}
						//Calculates the score for this column
						if (remainingmoves != 0){
							currScore += calculateScore(aiScore, remainingmoves);
						}
						aiScore = 1;
						nulltiles = 0;
					}
					
				}
				
				/*
				 * Check right-up diagonal
				 * Left side
				 */
				if(k <= 3 && i < 3){
					for (int p = 1; p < 4; p++) {
						if (currBoard.get(i+p).get(k+p) == 1) aiScore++;
						else if (currBoard.get(i+p).get(k+p) == 0){
							aiScore = 0;
							nulltiles = 0;
							break;
						} else {
							nulltiles++; 
						}
						
						
					}
					
				}
				
				/*
				 * Check Horizontal
				 * Left Side
				 */
				if (k <= 3){
					for (int p = 1; p < 4; p++) {
						if (currBoard.get(i).get(k+p) == 1) aiScore++;
						else if (currBoard.get(i).get(k+p)  == 0){
							aiScore = 0;
							nulltiles = 0;
							break;
						} else {
							nulltiles++; 
						}
						remainingmoves = 0;
						if (nulltiles > 0){
							for (int m=1; m < 4; m++){
								int cols = k + m;
								if (currBoard.get(m).get(cols) != 1 && currBoard.get(m).get(cols) != 0){
									remainingmoves++;
								} else{
									break;
								}
							}
						}
						//Calculates the score for this column
						if (remainingmoves != 0){
							currScore += calculateScore(aiScore, remainingmoves);
						}
						aiScore = 1;
						nulltiles = 0;
					}
					
				}
				
				/*
				 * Check Horizontal
				 * Right side
				 */
				if (k >= 3){
					for (int p = 1; p < 4; p++) {
						if (currBoard.get(i).get(k-p) == 1) aiScore++;
						else if (currBoard.get(i).get(k-p)  == 0){
							aiScore = 0;
							nulltiles = 0;
							break;
						}else {
							nulltiles++; 
						}
						remainingmoves = 0;
						if (nulltiles > 0){
							for (int m=1; m < 4; m++){
								int cols = k - m;
								if (currBoard.get(m).get(cols) != 1 && currBoard.get(m).get(cols) != 0){
									remainingmoves++;
								} else{
									break;
								}
							}
						}
						//Calculates the score for this column
						if (remainingmoves != 0){
							currScore += calculateScore(aiScore, remainingmoves);
						}
						aiScore = 1;
						nulltiles = 0;
					
				}
				
				/*
				 * Left up diagonal
				 * RIght side
				 */
				if (k >= 3){
					for (int p = 1; p < 4; p++){
						if (currBoard.get(i+p).get(k-p) == 1) aiScore++;
						else if (currBoard.get(i+p).get(k-p)  == 0){
							aiScore = 0;
							nulltiles = 0;
							break;
						}else {
							nulltiles++; 
						}
					}
					
				}					
			}
		}
		}
		return 0;
	}


	private int calculateScore(int aiScore, int remainingmoves) {
		int moveScore = 4 - remainingmoves;
        if(aiScore==0){
        	return 0;
        } else if(aiScore==1) {
        	return 1*moveScore;
        } else if(aiScore==2){
        	return 10*moveScore;
        } else if(aiScore==3){
        	return 100*moveScore;
        } else { 
        	return 1000;
        }
	}
}
