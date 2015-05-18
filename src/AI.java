import java.util.Random;


public class AI implements AIInterface{
	private int prevNum = 0;

	public int decideMove(GameState gs) {
		Random rand = new Random();
		int randomNum = rand.nextInt((5) + 1);
		prevNum = randomNum;
		return randomNum;
	}

}
