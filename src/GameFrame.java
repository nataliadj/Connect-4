import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class GameFrame extends JFrame {
	private int gameType = 0;

	private GameEngine ge = new GameEngine();
	/*public GameFrame (String title) {
		super(title);
	
	// Set layout manager
	setLayout(new BorderLayout());
	
	//Create Swing components
	final JTextArea textArea = new JTextArea();
	JButton button = new JButton ("Click me!");
	
	//Add swing components to content pane
	Container c = getContentPane();

	c.add(textArea, BorderLayout.CENTER);
	c.add(button, BorderLayout.SOUTH);
	
	//Add behavior
	button.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			textArea.append("Hello\n");
			
		}
		
	});*/
	public GameFrame(String title){
		super (title);
		
		setLayout(new BorderLayout());
		JPanel board = new JPanel();  
        board.setLayout(new GridLayout(7, 6)); 
        final Square[][] boardGUI = new Square[7][6];
      
        for (int i=0; i<7; i++) { 
            for (int j = 0; j <6; j++) {
               boardGUI[i][j] = new Square(i, j);
               board.add(boardGUI[i][j]);
            }
        }
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem newGame = new JMenuItem ("New Game");
        fileMenu.add(newGame);
        newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//String name = JOptionPane.showInputDialog(new JFrame(), "What is your name?", null);
				Object[] options = {"Human vs Human", "Human vs Computer"};
				String choice = (String)JOptionPane.showInputDialog(null,"Choose new game type",
						"New Game",JOptionPane.QUESTION_MESSAGE,null,options,"Human vs Human");

				if (choice.equals("Human vs Human")) {
					gameType = 1;
				} else {
					gameType = 0;
				}
				
				//initialize new game
				for (int i=0; i<7; i++) { 
		            for (int j = 0; j <6; j++) {
		               boardGUI[i][j].setValue(-1);
		            }
		        }
				ge = new GameEngine();
			}
			
		});
        JPanel dropButtonPanel = new JPanel();
        dropButtonPanel.setLayout(new GridLayout(1, 1));
		
		JButton drop1Button = new JButton("drop");
		dropButtonPanel.add(drop1Button);
		
		JButton drop2Button = new JButton("drop");
		dropButtonPanel.add(drop2Button);
		
		JButton drop3Button = new JButton("drop");
		dropButtonPanel.add(drop3Button);
		
		JButton drop4Button = new JButton("drop");
		dropButtonPanel.add(drop4Button);
		
		JButton drop5Button = new JButton("drop");
		dropButtonPanel.add(drop5Button);
		
		JButton drop6Button = new JButton("drop");
		dropButtonPanel.add(drop6Button);
        
		JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new GridLayout(10,1));
		JButton startButton = new JButton("New Game");
		buttonPanel.add(startButton);
		final JTextArea textArea = new JTextArea();
		buttonPanel.add(textArea);

		//add(buttonPanel, BorderLayout.EAST);
		add(dropButtonPanel, BorderLayout.NORTH);
		add(board, BorderLayout.CENTER);
		
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//String name = JOptionPane.showInputDialog(new JFrame(), "What is your name?", null);

				//initialize game type
				Object[] options = {"Human vs Human", "Human vs Computer"};
				String input = (String)JOptionPane.showInputDialog(null,"Choose new game type",
						"New Game",JOptionPane.QUESTION_MESSAGE,null,options,"Human vs Human");
				
				if (input.equals("Human vs Human")) {
					gameType = 1;
				} else {
					gameType = 0;
				}
				
				//initialize new game
				for (int i=0; i<7; i++) { 
		            for (int j = 0; j <6; j++) {
		               boardGUI[i][j].setValue(-1);
		            }
		        }
			}
			
		});

		drop1Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.append("Hello " + ge.validMove(0) + "\n");
				System.out.println(ge.validMove(0));
				if (ge.validMove(0) >= 0) {
					boardGUI[ge.validMove(0)][0].setValue(ge.getPlayer());
					ge.makeMove(0);
					/*if (ge.winCond(0, ge.getPlayer())) {
						Object[] options = {"Human vs Human", "Human vs Computer"};
						String end = (String)JOptionPane.showInputDialog(null,"Player " + ge.getPlayer() + " won! \nCreate new game?",
								"New Game",JOptionPane.QUESTION_MESSAGE,null,options,"Human vs Human");
					}*/
				}
			}
			
		});

		drop2Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.append("Hello " + ge.validMove(1) + "\n");
				System.out.println(ge.validMove(1));
				if (ge.validMove(1) >= 0) {
					boardGUI[ge.validMove(1)][1].setValue(ge.getPlayer());
					ge.makeMove(1);
					//if (ge.winCond(0, ge.getPlayer())) {
					//	JOptionPane.INFORMATION_MESSAGE;
					//}
				}
			}
			
		});

		drop3Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.append("Hello " + ge.validMove(2) + "\n");
				System.out.println(ge.validMove(2));
				if (ge.validMove(2) >= 0) {
					boardGUI[ge.validMove(2)][2].setValue(ge.getPlayer());
					ge.makeMove(2);
					//if (ge.winCond(0, ge.getPlayer())) {
					//	JOptionPane.INFORMATION_MESSAGE;
					//}
				}
			}
			
		});

		drop4Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.append("Hello " + ge.validMove(3) + "\n");
				System.out.println(ge.validMove(3));
				if (ge.validMove(3) >= 0) {
					boardGUI[ge.validMove(3)][3].setValue(ge.getPlayer());
					ge.makeMove(3);
					//if (ge.winCond(0, ge.getPlayer())) {
					//	JOptionPane.INFORMATION_MESSAGE;
					//}
				}
			}
			
		});

		drop5Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.append("Hello " + ge.validMove(4) + "\n");
				System.out.println(ge.validMove(4));
				if (ge.validMove(4) >= 0) {
					boardGUI[ge.validMove(4)][4].setValue(ge.getPlayer());
					ge.makeMove(4);
					//if (ge.winCond(0, ge.getPlayer())) {
					//	JOptionPane.INFORMATION_MESSAGE;
					//}
				}
			}
			
		});

		drop6Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.append("Hello " + ge.validMove(5) + "\n");
				System.out.println(ge.validMove(5));
				if (ge.validMove(5) >= 0) {
					boardGUI[ge.validMove(5)][5].setValue(ge.getPlayer());
					ge.makeMove(5);
					//if (ge.winCond(0, ge.getPlayer())) {
					//	JOptionPane.INFORMATION_MESSAGE;
					//}
				}
			}
			
		});
		
	}  
}
