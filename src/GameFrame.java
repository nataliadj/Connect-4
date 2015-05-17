import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GameFrame extends JFrame implements MouseListener{
	private int gameType;
	private boolean gameEnd;
	private Column[] boardGUI;
	private GameEngine ge;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	public GameFrame(String title){
		super (title);
		initMenuBar();
		setLayout(new GridLayout(1,7));
		this.gameType = 0;
		this.gameEnd = false;
		this.boardGUI  = new Column[7];
		initBoard();
		this.ge = new GameEngine();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Column col = (Column) e.getSource();	
		int player = ge.getPlayer();
		int colNum = col.getValue();
		int rowNum = ge.validMove(colNum);
		if (gameEnd == false) {
			if (ge.validMove(colNum) >= 0) {
				boardGUI[colNum].getSquare(rowNum).setValue(player);
				ge.makeMove(colNum);
				if (ge.winCond(colNum, player)) {
					Object[] options = {"Human vs Human", "Human vs Computer"};
					String color;
					if (player == 0) {
						color = "Red";
					} else {
						color = "Yellow";
					}
					String end = (String)JOptionPane.showInputDialog(null, color + " player " + "won! \nCreate new game?",
							"New Game",JOptionPane.QUESTION_MESSAGE,null,options,"Human vs Human");
					if (end != null) {
						if (end.equals("Human vs Human")) {
							gameType = 1;
						} else if (end.equalsIgnoreCase("Human vs Computer")){
							gameType = 0;
						}
						clearBoard();
						ge = new GameEngine();
					} else {
						gameEnd = true;
					}
				} else {
					//AI to be place here
					//if (gameType == 0) {
						//int aiMove = ge.callAi();
						//boardGUI[aiMove].getSquare(ge.validMove(aiMove)).setValue(ge.getPlayer());
						//ge.makeMove(aiMove);
					//}
				}
			}
		}
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Column col = (Column) e.getComponent();	
		col.setBackground(new Color (201, 182, 129));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Column col = (Column) e.getComponent();
		col.setBackground(new Color (222, 206, 162));
	}
	
	public void initBoard() {
		for (int i=0; i<7; i++) { 
			boardGUI[i] = new Column(i);
			boardGUI[i].addMouseListener(this);
			this.add(boardGUI[i]);
        }
	}
	
	public void clearBoard() {
		for (int i=0; i<7; i++) { 
			boardGUI[i].clearCol();
        }
	}
	
	public void initMenuBar() {
		this.menuBar = new JMenuBar();
        this.fileMenu = new JMenu("New Game");
        menuBar.add(fileMenu);
        JMenuItem multiPlayer = new JMenuItem ("Human vs Human");
        JMenuItem singlePlayer = new JMenuItem ("Human vs Computer");
        JMenuItem undo = new JMenuItem ("Undo");
        JMenuItem redo = new JMenuItem ("Redo");
        menuBar.add(undo);
        menuBar.add(redo);
        fileMenu.add(singlePlayer);
        fileMenu.add(multiPlayer);
        setJMenuBar(menuBar);
    
        singlePlayer.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			gameType = 0;
    			gameEnd = false;
    			clearBoard();
    			//initialize new game
    			ge = new GameEngine();
    		}	
    	});
        
        multiPlayer.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			clearBoard();
    			gameType = 1;
    			gameEnd = false;
    			//initialize new game  			
    			ge = new GameEngine();
    		}  		
    	}); 
        
        undo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (ge.undoAvailable()) {
					int col = ge.undoMove();
					int row = ge.validMove(col);
					boardGUI[col].getSquare(row).setValue(2);
					System.out.println("Undo column " + col);
				}
			}
        	
        });
        
        redo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (ge.redoAvailable()) {
					int player = ge.getPlayer();
					int col = ge.redoMove();
					int row = ge.validMove(col) + 1;
					boardGUI[col].getSquare(row).setValue(player);
					System.out.println("Redo column " + col);
				}
			}
        	
        });
	}
}
       
