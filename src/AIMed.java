import java.util.ArrayList;


public class AIMed implements AIInterface {
	private int currcol;
	private int maxDepth = 5;
	
			
	public int getAIturn(GameState gs){
		minimax(gs, 0, 2);
		return currcol;
	}
	
	private int boardstate(GameState gs){
		ArrayList<ArrayList<Integer>> currBoard = gs.getBoard();
		int plpoints = 0;
		int aiPoints = 0;
		
		for (int i = 0; i < 7; i++){
			int size = currBoard.get(i).size();
			while (size < 7){
				currBoard.get(i).add(3);
				size ++;
			}
		}
		//i is col, k is row
		for (int i = 0; i < 7; i++){
			for (int k = 0; k < 6; k++){
				if (currBoard.get(i).get(k) == 0 || currBoard.get(i).get(k) == 3) continue; //If its not me (AI)
				
				
				/*
				 * Checks column, works for all columns
				 * Currently ignores the top 3 rows
				 */
				if (k < 3){
					for (int p = 1; p < 4; p++) {
						if (currBoard.get(i).get(k+p) == 1) 
							aiPoints++;
						else if (currBoard.get(i).get(k+p) == 0) 
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
				if(i <= 3 && k < 3){
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
				if (i <= 3){
					for (int p = 1; p < 4; p++) {
						if (currBoard.get(i+p).get(k) == 1) aiPoints++;
						else if (currBoard.get(i+p).get(k) == 0) plpoints++;
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
				if (i >= 3){
					for (int p = 1; p < 4; p++) {
						if (currBoard.get(i-p).get(k) == 1) aiPoints++;
						else if (currBoard.get(i-p).get(k) == 0) plpoints++;
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
				if (i >= 3 && k < 3){
					for (int p = 1; p < 4; p++){
						if (currBoard.get(i-p).get(k+p) == 1) aiPoints++;
						else if (currBoard.get(i-p).get(k+p) == 0) plpoints++;
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
		
		int count = 0;
		for (int i = 0; i < 7; i++){
			int m = currBoard.get(i).size();
			for (int k = 0; k < m; k++){
				if (currBoard.get(i).get(k) == 3){
					currBoard.get(i).remove(k);
					k--;
					m = currBoard.get(i).size();
					count ++;
				}
			}
		}
		if (count > 0){
			return -1;
		}
		
		return 0;
	}
	
	
	
	private int evalboard(GameState gs){
		ArrayList<ArrayList<Integer>> currBoard = gs.getBoard();
		int aiScore = 0;
		int nulltiles = 0;
		int remainingmoves = 0;
		int currScore = 0;
		//i is col, k is row
		for (int i = 0; i < 7; i++){
			int size = currBoard.get(i).size();
			while (size < 7){
				currBoard.get(i).add(3);
				size ++;
			}
		}
		
		for (ArrayList<Integer> l1 : currBoard) {
			   for (Integer n : l1) {
			       System.out.print(n + " "); 
			   }

			   System.out.println();
		} 
		int p = 0;
		for (int k = 5; k >= 0; --k){
			for (int i = 0; i < 6; ++i){
				if(currBoard.get(i).get(k) == 3 || currBoard.get(i).get(k) == 0) continue; 
                //Checks across horizontal
				//System.out.println("Checks across horizontal");
                if(i <= 3){ 
                    for(p=1;p<4;++p){
                        if(currBoard.get(i+p).get(k)==1)aiScore++;
                        else if(currBoard.get(i+p).get(k) == 0){
                        	aiScore=0;
                        	nulltiles = 0;
                        	break;
                        } else {
                        	nulltiles++;
                        }
                     
                    remainingmoves = 0; 
                    if(nulltiles>0) 
                        for(int c=0;c< 3;++c){
                            int column = i+c;
                            for(int m = k ; m < 6; m++){
                             if(currBoard.get(column).get(m) == 3)remainingmoves++;
                                else break;
                            } 
                        } 
                    
                    if(remainingmoves!=0) currScore += calculateScore(aiScore, remainingmoves);
                    aiScore=1;   
                    nulltiles = 0;
                } 
                
                    //Checking top side, going downwards
                if(k >= 3){
                	//System.out.println("Checking top side, going downwards");
                    for(p=1;p<4;++p){
                        if(currBoard.get(i).get(k-p) == 1)aiScore++;
                        else if(currBoard.get(i).get(k-p) == 0){aiScore=0;break;} 
                    } 
                    remainingmoves = 0; 
                    
                    if(aiScore>0){
                        int column = i;
                        for(int m=k+p; m <= k;m++){
                         if(currBoard.get(column).get(m)==3)remainingmoves++;
                            else break;
                        }  
                    }
                    if(remainingmoves!=0) currScore += calculateScore(aiScore, remainingmoves);
                    aiScore=1;  
                    nulltiles = 0;
                }
                 
                //CHecking rightside, horizontal left
                if(i>=3){
                	//System.out.println("CHecking rightside, horizontal left");
                	 for(p=1;p<4;++p){
                        if(currBoard.get(i-p).get(k) == 1)aiScore++;
                        else if(currBoard.get(i-p).get(k) == 0){aiScore=0; nulltiles=0;break;}
                        else nulltiles++;
                    }
                    remainingmoves=0;
                    if(nulltiles>0) 
                        for(int c=1;c<4;++c){
                            int column = i- c;
                            for(int m=k; m<= 5;m++){
                             if (currBoard.get(column).get(m)==3)remainingmoves++;
                                else break;
                            } 
                        } 
                    
                    if(remainingmoves!=0) currScore += calculateScore(aiScore, remainingmoves);
                    aiScore=1; 
                    nulltiles = 0;
                }
                 
                //Left up diagonal
                if(i<=3 && k>=3){
                	//System.out.println("Left up diagonal");
                	 for(p=1;p<4;++p){
                        if(currBoard.get(i+p).get(k-p) == 1)aiScore++;
                        else if(currBoard.get(i+p).get(k-p) == 0){aiScore=0;nulltiles=0;break;}
                        else nulltiles++;                        
                    }
                    remainingmoves=0;
                    if(nulltiles>0){
                        for(int c=1;c<4;++c){
                            int column = i+c, row = k-c;
                            for(int m=row;m<=5;++m){
                                if(currBoard.get(column).get(m)==3)remainingmoves++;
                                else if(currBoard.get(column).get(m)==1);
                                else break;
                            }
                        } 
                        if(remainingmoves!=0) currScore += calculateScore(aiScore, remainingmoves);
                        aiScore=1;
                        nulltiles = 0;
                    }
                }
                 
                
                //Right up diangonal
                if(k>=3 && i>=3){
                	//System.out.println("Right up diangonal");
                	for(p=1;p<4;++p){
                        if(currBoard.get(i-p).get(k-p) == 1)aiScore++;
                        else if(currBoard.get(i-p).get(k-p) == 0){aiScore=0;nulltiles=0;break;}
                        else nulltiles++;                        
                    }
                    remainingmoves=0;
                    if(nulltiles>0){
                        for(int c=1;c<4;++c){
                            int column = i-c, row = k-c;
                            for(int m=row;m<=5;++m){
                                if(currBoard.get(column).get(m)==3)remainingmoves++;
                                else if(currBoard.get(column).get(m)==1);
                                else break;
                            }
                        } 
                        if(remainingmoves!=0) currScore += calculateScore(aiScore, remainingmoves);
                        aiScore=1;
                        nulltiles = 0;
                    }
                } 
			}
			}
		}
		for (int i = 0; i < 7; i++){
			int m = currBoard.get(i).size();
			for (int k = 0; k < m; k++){
				if (currBoard.get(i).get(k) == 3){
					currBoard.get(i).remove(k);
					k--;
					m = currBoard.get(i).size();
				}
			}
		}
		for (ArrayList<Integer> l1 : currBoard) {
			   for (Integer n : l1) {
			       System.out.print(n + " "); 
			   }

			   System.out.println();
		} 
		System.out.println(currScore);
		return currScore;
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

	@Override
	public int minimax(GameState gs, int depth, int player) {
		 int gameResult = boardstate(gs);
	        if(gameResult==1)return Integer.MAX_VALUE;
	        else if(gameResult==2)return Integer.MIN_VALUE;
	        else if(gameResult==0)return 0;
	        
	        if(depth==maxDepth)return evalboard(gs);
	        
	        int maxScore=Integer.MIN_VALUE, minScore = Integer.MAX_VALUE;
	        for(int j=0;j < 7;++j){
	        	if (gs.getBoard().get(j).size() < 6){
		                
		            if(player==0){
		                    gs.add(j);
		                    int currentScore = minimax(gs, depth+1, 2);
		                    maxScore = Math.max(currentScore, maxScore);
		                    if(depth==0){
		                        System.out.println("Score for location "+j+" = "+currentScore);
		                        if(maxScore==currentScore) currcol = j;
		                        
								if(maxScore==Integer.MAX_VALUE){
									gs.remove(j);
									break;
								}
					         }
		            } else if (player==1){
		            	gs.add(j);
		                int currentScore = minimax(gs, depth+1, 1);
		                minScore = Math.min(currentScore, minScore);
		            }
		            gs.remove(j);
	        	}
	        }
	     return player==0?maxScore:minScore;
	     
	}
}
