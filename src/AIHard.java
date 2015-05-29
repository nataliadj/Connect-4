import java.util.ArrayList;

public class AIHard implements AIInterface {
	private int currcol = 0;
	private int maxDepth = 6;
	private int currentScore = 0;
	
	/**
	 * @inheritDoc
	 */
	public int decideMove(GameState gs){
		if (gs.getPlayer() == 1){
			minimax(gs, 0,  Integer.MIN_VALUE, Integer.MAX_VALUE);
		} else if (gs.getPlayer() == 0){
			hintimax(gs, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
		}
		return currcol;
	}
	
	
	/**
	 * Looks the the current board state and determines whether
	 * AI or player has won. It'll scan the whole board everytime
	 * it's called
	 * @param gs - The current gamestate which contains the board
	 * @return 1 = First player, 2 = Second Player(AI)
	 */
	private int GameResult (GameState gs){
		int plPoints = 0;
		int aiPoints = 0;
		ArrayList<ArrayList<Integer>> Board = gs.getBoard();
		//Populate the board with empty space
		for (int i = 0; i < 7; i++){
			int size = Board.get(i).size();
			while (size < 7){
				Board.get(i).add(3);
				size ++;
			}
		}
		
		for (int col = 0; col < 7; col ++){
			for (int row = 0; row < 6; row ++){
				if (Board.get(col).get(row) != 3){
					
					//Check column
					if (row < 3){
						//System.out.println("COL");
						for (int p = 0; p < 4; p++){
							if (Board.get(col).get(row+p) == 1){
								aiPoints++;
							} else if (Board.get(col).get(row+p) == 0){
								plPoints++;
							} else {
								break;
							}
						}
						//System.out.println("PL" + plPoints);
						//System.out.println("AI" + aiPoints);
						if (aiPoints == 4){
							cleantemp(gs);
							return 2;
						} else if (plPoints == 4){
							cleantemp(gs);
							return 1;
						}
						
						aiPoints = 0;
						plPoints = 0;
					}
					//Check horizontal L to R
					if (col <= 3){
						//System.out.println("L TO R");
						for (int p = 0; p < 4; p++){
							if (Board.get(col+p).get(row) == 1){
								aiPoints++;
							} else if (Board.get(col+p).get(row) == 0){
								plPoints++;
							} else {
								break;
							}
						}
						//System.out.println("PL" + plPoints);
						//System.out.println("AI" + aiPoints);
						if (aiPoints == 4){
							cleantemp(gs);
							return 2;
						} else if (plPoints == 4){
							cleantemp(gs);
							return 1;
						}
						aiPoints = 0;
						plPoints = 0;
					}
					//Checks diagonal Up Right
					if (col <= 3 && row < 3){
						//System.out.println("UPRIGHT");
						for (int p = 0; p < 4; p++){
							if (Board.get(col+p).get(row+p) == 1){
								aiPoints++;
							} else if (Board.get(col+p).get(row+p) == 0){
								plPoints++;
							} else {
								break;
							}
						}
						//System.out.println("PL" + plPoints);
						//System.out.println("AI" + aiPoints);
						if (aiPoints == 4){
							cleantemp(gs);
							return 2;
						} else if (plPoints == 4){
							cleantemp(gs);
							return 1;
						}
						aiPoints = 0;
						plPoints = 0;
					}
					//Checks diagonal Up left
					if (col >= 3 && row < 3){
						//System.out.println("UPLEFT");
						for (int p = 0; p < 4; p++){
							if (Board.get(col-p).get(row+p) == 1){
								aiPoints++;
							} else if (Board.get(col-p).get(row+p) == 0){
								plPoints++;
							} else {
								break;
							}
						}
						//System.out.println("PL" + plPoints);
						//System.out.println("AI" + aiPoints);
						if (aiPoints == 4){
							cleantemp(gs);
							return 2;
						} else if (plPoints == 4){
							cleantemp(gs);
							return 1;
						}
						aiPoints = 0;
						plPoints = 0;
					}
				}
			}
		}
		//Remove the temp empty space
		int count = 0;
		for (int i = 0; i < 7; i++){
			int m = Board.get(i).size();
			for (int k = 0; k < m; k++){
				if (Board.get(i).get(k) == 3){
					Board.get(i).remove(k);
					k--;
					m = Board.get(i).size();
					count ++;
				}
			}
		}
		//If there were empty space, game's not over
		if (count > 0){
			return -1;
		}
		//Draw condition
		return 0;
	}
	
	
	/**
	 * Calculates the AI's position (Point-wise) on this current board
	 * @param gs - The current gamestate which contains the board
	 * @return Points based on the calculateScore function, however it
	 * is purely just how many moves it'll take to achieve a winning 
	 * condition
	 */
	private int AIBoardpts (GameState gs){
		int Score = 0;
		int aiPoints = 1;
		int remaining = 0;
		int reqMoves = 0;
		ArrayList<ArrayList<Integer>> Board = gs.getBoard();
		//Populate the board with empty space
		for (int i = 0; i < 7; i++){
			int size = Board.get(i).size();
			while (size < 7){
				Board.get(i).add(3);
				size ++;
			}
		}
		
		for (int col = 0; col < 7; col ++){
			for (int row = 0; row < 6; row ++){
				if (Board.get(col).get(row) != 3 && Board.get(col).get(row) != 0){
					
					//Check column, if it's ai occupying space, add 1
					//If it's player reset the counter
					//and if its empty space add 1 to remaining moves to make
					if (row < 3){
						for (int p = 0; p < 4; p++){
							if (Board.get(col).get(row+p) == 1){
								aiPoints++;
							} else if (Board.get(col).get(row+p) == 0){
								aiPoints = 0;
								remaining = 0;
								break;
							} else {
								remaining++;
							}
						}
						
						//checking how many moves till winning condition can be met
						if(remaining > 0) {
	                        for(int c = 0; c < 4; c++){
	                            int needRow = row + c;
	                            if (Board.get(col).get(needRow) == 3){
		                            reqMoves++;
		                        }
	                        } 
	                    } 
	                    
	                    if(reqMoves != 0){
	                    	Score += calculateScore(aiPoints, reqMoves);
	                    	//System.out.println("Checking COL: "+calculateScore(aiPoints, reqMoves));
	                    }
	                    aiPoints = 1;   
	                    remaining = 0;
	                    reqMoves = 0;
					}
					
					
					//Check horizontal R to L
					if (col >= 3){
						for (int p = 0; p < 4; p++){
							if (Board.get(col-p).get(row) == 1){
								aiPoints++;
							} else if (Board.get(col-p).get(row) == 0){
								aiPoints = 0;
								remaining = 0;
								break;
							} else {
								remaining++;
							}
						}
						
						//checking how many moves till winning condition can be met
						if(remaining > 0) {
	                        for(int c = 0; c < 4; c++){
	                            int column = col - c;
	                            for(int m = 0; m <= row; m++){
		                             if (Board.get(column).get(m) == 3){
		                            	 reqMoves++;
		                             }
	                            } 
	                        }
						}
	                    
	                    if(reqMoves != 0){
	                    	Score += calculateScore(aiPoints, reqMoves);
	                    	//System.out.println("Checking R to L: "+calculateScore(aiPoints, reqMoves));
	                    }
	                    aiPoints = 1;   
	                    remaining = 0;
	                    reqMoves = 0;
					}
					
					//Checking horizontal L to R
					if (col <= 3){
						for (int p = 0; p < 4; p++){
							if (Board.get(col+p).get(row) == 1){
								aiPoints++;
							} else if (Board.get(col+p).get(row) == 0){
								aiPoints = 0;
								remaining = 0;
								break;
							} else {
								remaining++;
							}
						}
						
						//checking how many moves till winning condition can be met
						if(remaining > 0) {
	                        for(int c = 0; c < 4; c++){
	                            int column = col + c;
	                            for(int m = 0; m <= row; m++){
		                             if (Board.get(column).get(m) == 3){
		                            	 reqMoves++;
		                             }
	                            } 
	                        } 
						}
	                    if(reqMoves != 0){
	                    	Score += calculateScore(aiPoints, reqMoves);
	                    	//System.out.println("Checking L to R: "+calculateScore(aiPoints, reqMoves));
	                    }
	                    aiPoints = 1;   
	                    remaining = 0;
	                    reqMoves = 0;
					}
					
					//Checks diagonal Up Right
					if (col <= 3 && row < 3){
						for (int p = 0; p < 4; p++){
							if (Board.get(col+p).get(row+p) == 1){
								aiPoints++;
							} else if (Board.get(col+p).get(row+p) == 0){
								aiPoints = 0;
								remaining = 0;
								break;
							} else {
								remaining++;
							}
						}
						
						//checking how many moves till winning condition can be met
						if(remaining > 0) {
	                        for(int c = 1; c < 4; c++){
	                            int column = col + c;
	                            int rowno = row + c;
	                            for(int m = 0; m <= rowno; m++){
	                            	if(Board.get(column).get(m) == 3) reqMoves++;
	                                else if(Board.get(column).get(m) == 0);
	                                else if(Board.get(column).get(m) == 1);
	                                else break;
	                            } 
	                        } 
						}
	                    if(reqMoves != 0){
	                    	Score += calculateScore(aiPoints, reqMoves);
	                    	//System.out.println("Checking Right up diag: "+calculateScore(aiPoints, reqMoves));
	                    }
	                    aiPoints = 1;   
	                    remaining = 0;
	                    reqMoves = 0;
					}
					//Checks diagonal Up left
					if (col >= 3 && row < 3){
						for (int p = 1; p < 4; p++){
							if (Board.get(col-p).get(row+p) == 1){
								aiPoints++;
							} else if (Board.get(col-p).get(row+p) == 0){
								aiPoints = 0;
								remaining = 0;
								break;
							} else {
								remaining++;
							}
						}
						
						//checking how many moves till winning condition can be met
						if(remaining > 0) {
	                        for(int c = 1; c < 4; c++){
	                            int column = col - c;
	                            int rowno = row + c;
	                            for(int m = 0; m <= rowno; m++){
	                            	if(Board.get(column).get(m) == 3) reqMoves++;
	                                else if(Board.get(column).get(m) == 0);
	                                else if(Board.get(column).get(m) == 1);
	                                else break;
	                            } 
	                        } 
					}
	                    if(reqMoves != 0){
	                    	Score += calculateScore(aiPoints, reqMoves);
	                    	//System.out.println("Checking Left up diag: "+calculateScore(aiPoints, reqMoves));
	                    }
	                    aiPoints = 1;   
	                    remaining = 0;
	                    reqMoves = 0;
						
					}
				}
			}
		}
		
		for (int i = 0; i < 7; i++){
			int m = Board.get(i).size();
			for (int k = 0; k < m; k++){
				if (Board.get(i).get(k) == 3){
					Board.get(i).remove(k);
					k--;
					m = Board.get(i).size();
				}
			}
		}
		
		return Score;
	}
	
	/**
	 * Calculates score based on the given aiScore and remainingmoves
	 * As of this moment it does nothing but returns times 1 on the amount
	 * of moves needed to reach victory condition
	 * @param aiScore - Given from AIBoardpts function
	 * @param remainingmoves - Given from AIBoardpts function
	 * @return
	 */
	private int calculateScore(int aiScore, int remainingmoves) {
		int moveScore = remainingmoves;
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
	
	/**
	 * Minimax Algorithm with alpha-beta pruning
	 * @param gs - The current gamestate which contains the board
	 * @param depth - The depth of the search on the tree
	 * @param alpha - Both alpha and beta are values to determine whether the next level of the tree is an obviously bad one/
	 * @param beta - See alpha
	 * @return Depending on which part of the function it's in, it'll either return a score or min/max value.
	 */
	private int minimax(GameState gs, int depth, int alpha, int beta){
	        int turn = gs.getPlayer();
	        if(beta<=alpha){
	        	if(turn == 1) {
	        		return Integer.MAX_VALUE; 
	        	} else { 
	        		return Integer.MIN_VALUE;
	        	}
	        }
	        
	        int gameResult = GameResult(gs);
	        
	        if(gameResult==1){
	        	return Integer.MIN_VALUE/2;
	        }
	        
	        else if(gameResult==2){
	        	return Integer.MAX_VALUE/2;
	        }
	        
	        else if(gameResult==0){
	        	return 0; 
	        }
	        
	        if(depth==maxDepth)return AIBoardpts(gs);
	        
	        int maxScore = Integer.MIN_VALUE;
	        int minScore = Integer.MAX_VALUE;
	                
	        for(int j = 0; j < 7; j++){
		    	if (gs.getBoard().get(j).size() < 6){       
		    		 if(turn == 1){
		    			 gs.add(j);
		    			 
		    			 //counter++;
		    			 currentScore = minimax(gs, depth+1, alpha, beta);
		                 
		                 if(depth==0){
		                    //System.out.println("Score for location "+j+" = "+currentScore);
		                    //System.out.println("States made: "+ counter);
		                    if(currentScore > maxScore)currcol = j; 
		                    if(GameResult(gs) == 2){
		                    	currcol = j; 
		                    	gs.remove(j);
		                    	break;
		                    }
		                 }
		                    
		                    maxScore = Math.max(currentScore, maxScore);
		                    
		                    alpha = Math.max(currentScore, alpha);  

		    		 } else if (turn == 0){
		    			 gs.add(j);
		    			 //counter++;
		                 currentScore = minimax(gs, depth+1, alpha, beta);
		                 minScore = Math.min(currentScore, minScore);
		                 beta = Math.min(currentScore, beta); 
		    		 }
		    		 gs.remove(j);
		    		 if (currentScore == Integer.MAX_VALUE || currentScore == Integer.MIN_VALUE) break; 
		    	}
		    }
		   
		    return (turn==1)?maxScore:minScore;
	    }
	
	 
	/**
	* Minimax Algorithm with alpha-beta pruning
	* @param gs - The current gamestate which contains the board
	* @param depth - The depth of the search on the tree
	* @param alpha - Both alpha and beta are values to determine whether the next level of the tree is an obviously bad one/
	* @param beta - See alpha
		 * @return Depending on which part of the function it's in, it'll either return a score or min/max value.
	 * 
	 */
	private int hintimax(GameState gs, int depth, int alpha, int beta){
		 int turn = gs.getPlayer();
	        if(beta<=alpha){
	        	if(turn == 1) {
	        		return Integer.MAX_VALUE; 
	        	} else { 
	        		return Integer.MIN_VALUE;
	        	}
	        }
	        
	        int gameResult = GameResult(gs);
	        
	        if(gameResult==1){
	        	return Integer.MIN_VALUE/2;
	        }
	        
	        else if(gameResult==2){
	        	return Integer.MAX_VALUE/2;
	        }
	        
	        else if(gameResult==0){
	        	return 0; 
	        }
	        
	        if(depth==maxDepth)return AIBoardpts(gs);
	        
	        int maxScore = Integer.MIN_VALUE;
	        int minScore = Integer.MAX_VALUE;
	                
	        for(int j = 0; j < 7; j++){
		    	if (gs.getBoard().get(j).size() < 6){       
		    		 if(turn == 0){
		    			 gs.add(j);
		    			 currentScore = minimax(gs, depth+1, alpha, beta);
		                 
		    			 //counter++;
		    			
		                 
		                 if(depth==0){
		                    //System.out.println("Score for location "+j+" = "+currentScore);
		                    //System.out.println("MaxScore for location "+j+" = "+maxScore);
		                    //System.out.println("States made: "+ counter);
		                    if(currentScore < minScore)currcol = j; 
		                    if(GameResult(gs) == 1){
		                    	currcol = j; 
		                    	gs.remove(j);
		                    	break;
		                    }
		                 }
		                 
		                 minScore = Math.min(currentScore, minScore);
		                 beta = Math.min(currentScore, beta); 
		                    
		    		 } else if (turn == 1){
		    			 gs.add(j);
		    			 //counter++;
		    			 currentScore = minimax(gs, depth+1, alpha, beta);
		    			 maxScore = Math.max(currentScore, maxScore);
		                    
		                 alpha = Math.max(currentScore, alpha);  

		    		 }
		    		 gs.remove(j);
		    		 if (currentScore == Integer.MAX_VALUE || currentScore == Integer.MIN_VALUE) break; 
		    	}
		    }
		   
		    return (turn==0)?minScore:maxScore;
	    }
	 
	 
	/**
	* Cleans the board up after inserting temporary empty spaces(3)
	* @param gs  - The current gamestate which contains the board
	*/
    private void cleantemp (GameState gs){
		ArrayList<ArrayList<Integer>> Board = gs.getBoard();
		//Remove the temp empty space
		//int count = 0;
		for (int i = 0; i < 7; i++){
			int m = Board.get(i).size();
			for (int k = 0; k < m; k++){
				if (Board.get(i).get(k) == 3){
					Board.get(i).remove(k);
					k--;
					m = Board.get(i).size();
					//count ++;
				}
			}
		}
	}
	
}