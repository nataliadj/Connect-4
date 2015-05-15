import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

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
		this.getContentPane();
		this.ge = new GameEngine();
	}
	
	private void setGameType(int gameType) {
		this.gameType = gameType;
	}
	
	private void setGameEnd(boolean gameEnd) {
		this.gameEnd = gameEnd;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Column col = (Column) e.getSource();	
		int player = ge.getPlayer();
		int colNum = col.getValue();
		int rowNum = ge.validMove(colNum);
		if (gameEnd == false) {
			//textArea.append("Hello " + ge.validMove(0) + "\n");
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
						
						if (gameEnd == false) {
							initBoard();
							ge = new GameEngine();
						} 
					} else {
						gameEnd = true;
					}
				} else {
					//if (gameType == 0) {
					//	boardGUI[ge.validMove(ge.callAi())][0].setValue(ge.getPlayer());
					//	ge.makeMove(ge.callAi());
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
	
	public void initMenuBar() {
		this.menuBar = new JMenuBar();
        this.fileMenu = new JMenu("New Game");
        menuBar.add(fileMenu);
        JMenuItem multiPlayer = new JMenuItem ("Human vs Human");
        JMenuItem singlePlayer = new JMenuItem ("Human vs Computer");
        fileMenu.add(singlePlayer);
        fileMenu.add(multiPlayer);
        setJMenuBar(menuBar);
    
        singlePlayer.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			setGameType(0);
    			setGameEnd(false);
    			boardGUI  = new Column[7];
    			//initialize new game
    			initBoard();
    			ge = new GameEngine();
    		}	
    	});
        
        multiPlayer.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			initBoard();
    			setGameType(1);
    			setGameEnd(false);
    			boardGUI  = new Column[7];
    			//initialize new game  			
    			ge = new GameEngine();

    		}  		
    	}); 
	}
}
		// Set layout manager
		//
		
		//Create Swing components
		//JPanel board = new JPanel();  
       // board.setLayout(new GridLayout(6,7)); 
        //final Square[][] boardGUI = new Square[6][7];
 /*
        for (int i=0; i<6; i++) { 
            for (int j = 0; j <7; j++) {
               boardGUI[i][j] = new Square(i, j);
               board.add(boardGUI[i][j]);
            }
        }
 */
        
       // JMenuBar menuBar = new JMenuBar();
       // JMenu fileMenu = new JMenu("New Game");
        //menuBar.add(fileMenu);
       // JMenuItem multiPlayer = new JMenuItem ("Human vs Human");
       // JMenuItem singlePlayer = new JMenuItem ("Human vs Computer");
       // fileMenu.add(singlePlayer);
       // fileMenu.add(multiPlayer);		
       
		//Add behavior
		/*startButton.addActionListener(new ActionListener() {

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
				for (int i=0; i<6; i++) { 
		            for (int j = 0; j <6; j++) {
		               boardGUI[i][j].setValue(-1);
		            }
		        }
			}
			
		});
/*
		
		
		/*for (int i = 0; i < 7; i++) {
			dropButtons.get(i).addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (ge.validMove(i) >= 0) {
						boardGUI[ge.validMove(i)][i].setValue(ge.getPlayer());
						ge.makeMove(i);
					}
				}
				
			});
		}
*/
/*
        singlePlayer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gameType = 0;
				gameEnd = false;
					
				//initialize new game
				for (int i=0; i<6; i++) { 
		            for (int j = 0; j <7; j++) {
		               boardGUI[i][j].setValue(2);
		            }
		        }
				ge = new GameEngine();
			}
			
		});
        
        multiPlayer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gameType = 1;
				gameEnd = false;
				
				//initialize new game
				for (int i=0; i<6; i++) { 
		            for (int j = 0; j <7; j++) {
		               boardGUI[i][j].setValue(2);
		            }
		        }
				ge = new GameEngine();
			}
			
		});
*/
	//}  
	
//}
