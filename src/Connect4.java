
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Connect4 {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new GameFrame ("Connect 4");
				frame.setSize(700,600);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
