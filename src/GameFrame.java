import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class GameFrame extends JFrame {
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
		setLayout(new BorderLayout());
		JPanel board = new JPanel();  
        board.setLayout(new GridLayout(7, 6)); 
        Square[][] boardGUI = new Square[7][6];
      
        for (int i=0; i<7; i++) { 
            for (int j = 0; j <6; j++) {
               boardGUI[i][j] = new Square(i, j);
               board.add(boardGUI[i][j]);  
               //TODO adding mouse event
            }
        }  
		JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new GridLayout(10,1));
		
		JButton startButton = new JButton("New Game");
		JButton undoButton = new JButton("Undo");
		buttonPanel.add(startButton);
		buttonPanel.add(undoButton);

		add(board, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.EAST);
	}  
}
