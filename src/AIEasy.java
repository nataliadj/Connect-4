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
			//System.out.println(i);
			//if (gs.getBoard().get(i).size() != 0) {

				gs.getBoard().get(i).add(me);
				if(gs.winCond(i, me)) { 
					System.out.println("AI me at " + i);
					gs.getBoard().get(i).remove(gs.getBoard().get(i).size()-1);
					return i;
				}
				
				gs.getBoard().get(i).remove(gs.getBoard().get(i).size()-1);
				gs.getBoard().get(i).add(opponent);
				
				if(gs.winCond(i, opponent)) {
					System.out.println("AI opp at " + i);
					gs.getBoard().get(i).remove(gs.getBoard().get(i).size()-1);
					return i;
				}
				
				gs.getBoard().get(i).remove(gs.getBoard().get(i).size()-1);
			}
		//}
		return randNum;
	}

}
