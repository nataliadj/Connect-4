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
		Random rand = new Random();
		int randNum = rand.nextInt((6-0) +1);
		//check opponent's win condition
		//System.out.println(gs.getBoard().size());
		for(int i = 0; i < gs.getBoard().size(); i++) {
			//first priority
			//winning move
			gs.getBoard().get(i).add(me);
			if(gs.winCond(i, me) && gs.getBoard().get(i).size() < 6) { 
				//System.out.println("AI me at " + i);
				gs.remove(i);
				return i;
			}	
			gs.remove(i);
			
			//second priority
			//block opponent's winning move
			gs.getBoard().get(i).add(opponent);	
			if(gs.winCond(i, opponent) && gs.getBoard().get(i).size() < 6) {
				//System.out.println("AI opp at " + i);
				gs.remove(i);
				return i;
			}	
			gs.getBoard().get(i).remove(gs.getBoard().get(i).size()-1);
			
			
		}
		boolean flag = false;
		while(flag == false) {
			if(gs.getBoard().get(randNum).size() != 6) {
				flag = true;
			} else {
				randNum = rand.nextInt((6-0) + 1);
			}
		}
		return randNum;
	}

}
