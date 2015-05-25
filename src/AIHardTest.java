import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class AIHardTest {

	@Test
	public void AIBoardpts() {
		GameState gs= new GameState();
		gs.add(3);
		gs.add(3);
		gs.add(4);
		gs.add(5);
		//gs.add(1);

		for (int i = 5; i >= 0; i--) {
			for (ArrayList<Integer> a: gs.getBoard()) {
				if (a.size() <= i) {
					System.out.print(" - ");
				} else {
					System.out.print(" " + a .get(i) + " ");
				}
			}
			System.out.print("\n");
		}
		int a = AIBoardpts(gs);
		//boolean b = winCond(0, 0, gs);
		System.out.println("state is: " + a);

	}
	
	public int AIBoardpts (GameState gs){
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
						for (int p = 1; p < 4; p++){
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
	                        for(int c = 1; c < 4; c++){
	                            int needRow = row + c;
	                            if (Board.get(col).get(needRow) == 3){
		                            reqMoves++;
		                        }
	                        } 
	                    } 
	                    
	                    if(reqMoves != 0){
	                    	Score += calculateScore(aiPoints, reqMoves);
	                    	System.out.println("Checking COL: "+calculateScore(aiPoints, reqMoves));
	                    }
	                    aiPoints = 1;   
	                    remaining = 0;
	                    reqMoves = 0;
					}
					
					
					//Check horizontal R to L
					if (col >= 3){
						for (int p = 1; p < 4; p++){
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
	                        for(int c = 1; c < 4; c++){
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
	                    	System.out.println("Checking R to L: "+calculateScore(aiPoints, reqMoves));
	                    }
	                    aiPoints = 1;   
	                    remaining = 0;
	                    reqMoves = 0;
					}
					
					//Checking horizontal L to R
					if (col <= 3){
						for (int p = 1; p < 4; p++){
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
	                        for(int c = 1; c < 4; c++){
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
	                    	System.out.println("Checking L to R: "+calculateScore(aiPoints, reqMoves));
	                    }
	                    aiPoints = 1;   
	                    remaining = 0;
	                    reqMoves = 0;
					}
					
					//Checks diagonal Up Right
					if (col <= 3 && row < 3){
						for (int p = 1; p < 4; p++){
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
	                    	System.out.println("Checking Right up diag: "+calculateScore(aiPoints, reqMoves));
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
	                    	System.out.println("Checking Left up diag: "+calculateScore(aiPoints, reqMoves));
	                    }
	                    aiPoints = 1;   
	                    remaining = 0;
	                    reqMoves = 0;
						
					}
				}
			}
		}
		
		
		//Remove the temp empty space
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

}