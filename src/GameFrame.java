<<<<<<< Upstream, based on origin/master
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
=======
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GameFrame extends JFrame {
	private int gameType = 0;
	private boolean gameEnd = true;
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
		
		// Set layout manager
		setLayout(new BorderLayout());
		
		//Create Swing components
		JPanel board = new JPanel();  
        board.setLayout(new GridLayout(6, 7)); 
        final Square[][] boardGUI = new Square[6][7];
      
        for (int i=0; i<6; i++) { 
            for (int j = 0; j <7; j++) {
               boardGUI[i][j] = new Square(i, j);
               board.add(boardGUI[i][j]);
            }
        }
        
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("New Game");
        menuBar.add(fileMenu);
        JMenuItem multiPlayer = new JMenuItem ("Human vs Human");
        JMenuItem singlePlayer = new JMenuItem ("Human vs Computer");
        fileMenu.add(singlePlayer);
        fileMenu.add(multiPlayer);
        
        JPanel dropButtonPanel = new JPanel();
        dropButtonPanel.setLayout(new GridLayout(1, 1));
		
        //final ArrayList<JButton> dropButtons = new ArrayList<JButton>();
        /*for (int i = 0; i < 7; i++) {
        	dropButtons.add(new JButton("drop"));
        }
        for (JButton jb: dropButtons) {
        	dropButtonPanel.add(jb);
        }*/
        
		JButton drop1Button = new JButton("drop");
		JButton drop2Button = new JButton("drop");
		JButton drop3Button = new JButton("drop");
		JButton drop4Button = new JButton("drop");
		JButton drop5Button = new JButton("drop");
		JButton drop6Button = new JButton("drop");
		JButton drop7Button = new JButton("drop");

		dropButtonPanel.add(drop1Button);
		dropButtonPanel.add(drop2Button);
		dropButtonPanel.add(drop3Button);
		dropButtonPanel.add(drop4Button);
		dropButtonPanel.add(drop5Button);
		dropButtonPanel.add(drop6Button);
		dropButtonPanel.add(drop7Button);
		
        
		/*JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new GridLayout(10,1));
		JButton startButton = new JButton("New Game");
		buttonPanel.add(startButton);
		final JTextArea textArea = new JTextArea();
		buttonPanel.add(textArea);*/

		//Add swing components to content pane
		//add(buttonPanel, BorderLayout.EAST);
        setJMenuBar(menuBar);
		add(dropButtonPanel, BorderLayout.NORTH);
		add(board, BorderLayout.CENTER);
		
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
			
		});*/

		drop1Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameEnd == false) {
					//textArea.append("Hello " + ge.validMove(0) + "\n");
					if (ge.validMove(0) >= 0) {
						boardGUI[ge.validMove(0)][0].setValue(ge.getPlayer());
						int player = ge.getPlayer();
						ge.makeMove(0);
						if (ge.winCond(0, player)) {
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
								} else if ( end.equalsIgnoreCase("Human vs Computer")){
									gameType = 0;
								}
								
								if (gameEnd == false) {
									for (int i=0; i<6; i++) { 
							            for (int j = 0; j <7; j++) {
							               boardGUI[i][j].setValue(2);
							            }
							        }
									ge = new GameEngine();
								} 
							} else {
								gameEnd = true;
							}
						} else {
							if (gameType == 0) {
								int ph = ge.callAi();
								boardGUI[ge.validMove(ph)][0].setValue(ge.getPlayer());
								ge.makeMove(ph);
							}
						}
					}
				}
			}
		});

		drop2Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameEnd == false) {
					//textArea.append("Hello " + ge.validMove(1) + "\n");
					if (ge.validMove(1) >= 0) {
						boardGUI[ge.validMove(1)][1].setValue(ge.getPlayer());
						int player = ge.getPlayer();
						ge.makeMove(1);
						if (ge.winCond(1, player)) {
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
								} else if ( end.equalsIgnoreCase("Human vs Computer")){
									gameType = 0;
								}
								
								if (gameEnd == false) {
									for (int i=0; i<6; i++) { 
							            for (int j = 0; j <7; j++) {
							               boardGUI[i][j].setValue(2);
							            }
							        }
									ge = new GameEngine();
								} 
							} else {
								gameEnd = true;
							}
						} else {
							if (gameType == 0) {
								int ph = ge.callAi();
								boardGUI[ge.validMove(ph)][0].setValue(ge.getPlayer());
								ge.makeMove(ph);
							}
						}
					}
				}
			}
			
		});

		drop3Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameEnd == false) {
					//textArea.append("Hello " + ge.validMove(2) + "\n");
					if (ge.validMove(2) >= 0) {
						boardGUI[ge.validMove(2)][2].setValue(ge.getPlayer());
						int player = ge.getPlayer();
						ge.makeMove(2);
						if (ge.winCond(2, player)) {
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
								} else if ( end.equalsIgnoreCase("Human vs Computer")){
									gameType = 0;
								}
								
								if (gameEnd == false) {
									for (int i=0; i<6; i++) { 
							            for (int j = 0; j <7; j++) {
							               boardGUI[i][j].setValue(2);
							            }
							        }
									ge = new GameEngine();
								} 
							} else {
								gameEnd = true;
							}
						} else {
							if (gameType == 0) {
								int ph = ge.callAi();
								boardGUI[ge.validMove(ph)][0].setValue(ge.getPlayer());
								ge.makeMove(ph);
							}
						}
					}
				}
			}
			
		});

		drop4Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameEnd == false) {
					//textArea.append("Hello " + ge.validMove(3) + "\n");
					if (ge.validMove(3) >= 0) {
						boardGUI[ge.validMove(3)][3].setValue(ge.getPlayer());
						int player = ge.getPlayer();
						ge.makeMove(3);
						if (ge.winCond(3, player)) {
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
								} else if ( end.equalsIgnoreCase("Human vs Computer")){
									gameType = 0;
								}
								
								if (gameEnd == false) {
									for (int i=0; i<6; i++) { 
							            for (int j = 0; j <7; j++) {
							               boardGUI[i][j].setValue(2);
							            }
							        }
									ge = new GameEngine();
								} 
							} else {
								gameEnd = true;
							}
						} else {
							if (gameType == 0) {
								int ph = ge.callAi();
								boardGUI[ge.validMove(ph)][0].setValue(ge.getPlayer());
								ge.makeMove(ph);
							}
						}
					}
				}
			}
			
		});

		drop5Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameEnd == false) {
					//textArea.append("Hello " + ge.validMove(4) + "\n");
					if (ge.validMove(4) >= 0) {
						boardGUI[ge.validMove(4)][4].setValue(ge.getPlayer());
						int player = ge.getPlayer();
						ge.makeMove(4);
						if (ge.winCond(4, player)) {
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
								} else if ( end.equalsIgnoreCase("Human vs Computer")){
									gameType = 0;
								}
								
								if (gameEnd == false) {
									for (int i=0; i<6; i++) { 
							            for (int j = 0; j <7; j++) {
							               boardGUI[i][j].setValue(2);
							            }
							        }
									ge = new GameEngine();
								} 
							} else {
								gameEnd = true;
							}
						} else {
							if (gameType == 0) {
								int ph = ge.callAi();
								boardGUI[ge.validMove(ph)][0].setValue(ge.getPlayer());
								ge.makeMove(ph);
							}
						}
					}
				}
			}
			
		});

		drop6Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameEnd == false) {
					//textArea.append("Hello " + ge.validMove(5) + "\n");
					if (ge.validMove(5) >= 0) {
						boardGUI[ge.validMove(5)][5].setValue(ge.getPlayer());
						int player = ge.getPlayer();
						ge.makeMove(5);
						if (ge.winCond(5, player)) {
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
								} else if ( end.equalsIgnoreCase("Human vs Computer")){
									gameType = 0;
								}
								
								if (gameEnd == false) {
									for (int i=0; i<6; i++) { 
							            for (int j = 0; j <7; j++) {
							               boardGUI[i][j].setValue(2);
							            }
							        }
									ge = new GameEngine();
								} 
							} else {
								gameEnd = true;
							}
						} else {
							if (gameType == 0) {
								int ph = ge.callAi();
								boardGUI[ge.validMove(ph)][0].setValue(ge.getPlayer());
								ge.makeMove(ph);
							}
						}
					}
				}
			}
			
		});
		
		drop7Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameEnd == false) {
					//textArea.append("Hello " + ge.validMove(5) + "\n");
					if (ge.validMove(6) >= 0) {
						boardGUI[ge.validMove(6)][6].setValue(ge.getPlayer());
						int player = ge.getPlayer();
						ge.makeMove(6);
						if (ge.winCond(6, player)) {
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
								} else if ( end.equalsIgnoreCase("Human vs Computer")){
									gameType = 0;
								}
								
								if (gameEnd == false) {
									for (int i=0; i<6; i++) { 
							            for (int j = 0; j <7; j++) {
							               boardGUI[i][j].setValue(2);
							            }
							        }
									ge = new GameEngine();
								} 
							} else {
								gameEnd = true;
							}
						} else {
							if (gameType == 0) {
								int ph = ge.callAi();
								boardGUI[ge.validMove(ph)][0].setValue(ge.getPlayer());
								ge.makeMove(ph);
							}
						}
					}
				}
			}
			
		});
		
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
		}*/

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
	}  
}
>>>>>>> dfb97bb Have AI easy and AI med. AI easy is literally jsut random columns Having trouble with inivisbly inserted stuff. AI med has only the barebones of the minimax algo TODO needs a boardstate (400+ lines) function TODO AI hard = minimax + optimisation + heuristics
