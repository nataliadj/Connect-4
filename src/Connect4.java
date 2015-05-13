import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Connect4 {
	private static GameEngine ge;
	private static boolean AI;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new GameFrame ("Connect 4");
				frame.setSize(600,700);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
