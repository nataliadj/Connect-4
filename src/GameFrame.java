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
							String end = (String)JOptionPane.showInputDialog(null, color + " player " + " won! \nCreate new game?",
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
								boardGUI[ge.validMove(ge.callAi())][0].setValue(ge.getPlayer());
								ge.makeMove(ge.callAi());
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
							String end = (String)JOptionPane.showInputDialog(null, color + " player " + " won! \nCreate new game?",
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
								System.out.println(ge.callAi());
								boardGUI[ge.validMove(ge.callAi())][0].setValue(ge.getPlayer());
								ge.makeMove(ge.callAi());
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
							String end = (String)JOptionPane.showInputDialog(null, color + " player " + " won! \nCreate new game?",
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
								boardGUI[ge.validMove(ge.callAi())][0].setValue(ge.getPlayer());
								ge.makeMove(ge.callAi());
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
							String end = (String)JOptionPane.showInputDialog(null, color + " player " + " won! \nCreate new game?",
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
								boardGUI[ge.validMove(ge.callAi())][0].setValue(ge.getPlayer());
								ge.makeMove(ge.callAi());
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
							String end = (String)JOptionPane.showInputDialog(null, color + " player " + " won! \nCreate new game?",
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
								boardGUI[ge.validMove(ge.callAi())][0].setValue(ge.getPlayer());
								ge.makeMove(ge.callAi());
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
							String end = (String)JOptionPane.showInputDialog(null, color + " player " + " won! \nCreate new game?",
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
								boardGUI[ge.validMove(ge.callAi())][0].setValue(ge.getPlayer());
								ge.makeMove(ge.callAi());
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
							String end = (String)JOptionPane.showInputDialog(null, color + " player " + " won! \nCreate new game?",
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
								boardGUI[ge.validMove(ge.callAi())][0].setValue(ge.getPlayer());
								ge.makeMove(ge.callAi());
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
