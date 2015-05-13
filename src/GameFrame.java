import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GameFrame extends JFrame {
	private int gameType = 0;

	private GameEngine ge;
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
        
        /*JMenuBar menuBar = new JMenuBar();
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
			}
			
		});*/
        
		JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new GridLayout(10,1));
		
		JButton startButton = new JButton("New Game");
		buttonPanel.add(startButton);

		add(board, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.EAST);
		
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
		
	}  
}
