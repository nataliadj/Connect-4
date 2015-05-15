<<<<<<< Upstream, based on origin/master
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
=======
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Connect4 {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new GameFrame ("Connect 4");
				frame.setSize(600,600);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
>>>>>>> dfb97bb Have AI easy and AI med. AI easy is literally jsut random columns Having trouble with inivisbly inserted stuff. AI med has only the barebones of the minimax algo TODO needs a boardstate (400+ lines) function TODO AI hard = minimax + optimisation + heuristics
