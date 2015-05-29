import java.util.*;


public class AIEasy implements AIInterface{
	
	public int decideMove(GameState gs) {
		int me = gs.getPlayer();
		int opponent;
		if(me == 0) {
			opponent = 1;
		} else {
			opponent = 0;
		}
		//check opponent's win condition
		for(int i = 0; i < gs.getBoard().size(); i++) {
			if(!(gs.getBoard().get(i).size() >= 6)) {
				//first priority
				//winning move
				gs.getBoard().get(i).add(me);
				if(gs.winCond(i, me)) { 
					gs.getBoard().get(i).remove(gs.getBoard().get(i).size()-1);
					return i;
				}	
				gs.getBoard().get(i).remove(gs.getBoard().get(i).size()-1);
			
				//second priority
				//block opponent's winning move
				for(int j = 0; j < gs.getBoard().size(); j++) {
					if(gs.getBoard().get(j).size() < 6) {
						gs.getBoard().get(j).add(opponent);
							if(gs.winCond(j, opponent)) {
								gs.getBoard().get(j).remove(gs.getBoard().get(j).size()-1);
								return j;
							}
						gs.getBoard().get(j).remove(gs.getBoard().get(j).size()-1);
					}
				}	
				//gs.getBoard().get(i).remove(gs.getBoard().get(i).size()-1);
			
				//third priority
				//block opponent's horizontal move
				int horizontalFind = 0;
				gs.getBoard().get(i).add(opponent);
				for (int col = 0; col < 7; col++) {
					int row = gs.getBoard().get(i).size()-1;
					if ((gs.getBoard().get(col).size() >= row+1) && gs.getBoard().get(col).get(row) == opponent) {
						horizontalFind ++;
					} else {
						horizontalFind = 0;
					}
				
					if (horizontalFind == 3) {
						gs.getBoard().get(i).remove(gs.getBoard().get(i).size()-1);
						return i;
					}
				}
				gs.getBoard().get(i).remove(gs.getBoard().get(i).size()-1);
			}
		}
		//last priority
		//find "me" with the most verticalFind
		int maxAt = 0;
		int max = 0;
		int verticalFind = 0;
		for(int i = 0; i < gs.getBoard().size(); i++) {
			/*if(gs.getBoard().get(maxAt).size() == 6) {
				maxAt = i;
				
			}*/
			for(int j = 0; j < gs.getBoard().get(i).size(); j++) {
				if(gs.getBoard().get(i).get(j) == me) {
					verticalFind++;
				} else {
					verticalFind = 0;
				}
			}
			if(verticalFind > max) {
				max = verticalFind;
				maxAt = i;
			}
		}
		if(gs.getBoard().get(maxAt).size() == 6) {
			for(int i = 0; i < gs.getBoard().size(); i++) {
				if(gs.getBoard().get(i).size() < 6) {
					maxAt = i;
					break;
				}
			}
		}
		return maxAt;
	}

}
