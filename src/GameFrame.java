import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GameFrame extends JFrame implements MouseListener{
	private boolean tutorialOn;
	private int gameType;
	private boolean gameEnd;
	private MenuPanel menu;
	private MenuPanel newGameMenu;
	private GameEngine ge;
	private Board board;
	private RightButtonPanel rightPanel;
	private RightButtonPanel tutorialPanel;
	private JPanel centerPanel;

	/**
	 * Constructor - Initializes up the layout and all the panels for the Frame
	 * @param title == "Connect 4"
	 */
	public GameFrame(String title){
		super (title);
		setLayout(new BorderLayout());
		initCenterPanel();
		initRightPanel();
		initTutorialPanel();
		remove(rightPanel);
		this.pack();
	}
	
	/**
	 * When the selected column is clicked, the move is made 
	 * and shows the move on UI
	 * Also checks whether that move wins the game
	 * Finally, it also contains the sequence of the tutorials
	 * @precondition ge != null
	 * @param e
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		boolean flag = false;
		int tutePassed = tutorialPanel.getTutePassed();
		for(int i = 0; i < 7; i++) {
			board.getCol(i).setBorder(null);
		}
		Column col = (Column) e.getSource();	
		int player = ge.getPlayer();
		int colNum = col.getValue();
		int rowNum = ge.validMove(colNum);
		if (gameEnd == false) {
			if (ge.validMove(colNum) >= 0) {
				board.getCol(colNum).getCircle(rowNum).setValue(player);
				ge.makeMove(colNum);
				if(tutorialOn == true) {
					menu.getResume().setText("Resume Tutorial");
					menu.getResume().setEnabled(true);
					if(tutePassed == 0) {
						if(colNum == 2) {
							tutePassed++;
							tutorialPanel.setTutePassed(tutePassed);
							//System.out.println("tutepassed = " + tutorialPanel.getTutePassed());
							tutorialPanel.getFeedback().setText("Good! Now, try placing on the fourth column.");
							tutorialPanel.getUndoButton().setEnabled(false);
						} else {
							tutorialPanel.getFeedback().setText("Wrong move! Click Undo to try again.");
							tutorialPanel.getUndoButton().setEnabled(true);
							flag = true;
						}
					} else if (tutePassed == 1) {
						if(colNum == 3) {
							tutePassed++;
							tutorialPanel.setTutePassed(tutePassed);
							tutorialPanel.getFeedback().setText("Well done! Click Next to proceed.");
							tutorialPanel.getUndoButton().setEnabled(false);
							tutorialPanel.getNextTut().setEnabled(true);
							flag = true;
						} else {
							tutorialPanel.getFeedback().setText("Wrong move! Click Undo to try again.");
							tutorialPanel.getUndoButton().setEnabled(true);
							flag = true;
						}
					} else if (tutePassed == 2) {
						if(colNum == 0) {
							tutePassed++;
							tutorialPanel.setTutePassed(tutePassed);
							tutorialPanel.getFeedback().setText("Good! Now, try this one.");
							createNewGame(0, 1);
							tutorialOn = true;
							board.getCol(4).getCircle(5).setValue(0);
							ge.makeMove(4);
							board.getCol(3).getCircle(5).setValue(1);
							ge.makeMove(3);
							board.getCol(3).getCircle(4).setValue(0);
							ge.makeMove(3);
							board.getCol(2).getCircle(5).setValue(1);
							ge.makeMove(2);
							board.getCol(1).getCircle(5).setValue(0);
							ge.makeMove(1);
							board.getCol(2).getCircle(4).setValue(1);
							ge.makeMove(2);
							board.getCol(2).getCircle(3).setValue(0);
							ge.makeMove(2);
							board.getCol(1).getCircle(4).setValue(1);
							ge.makeMove(1);
							board.getCol(1).getCircle(3).setValue(0);
							ge.makeMove(1);
							board.getCol(0).getCircle(5).setValue(1);
							ge.makeMove(0);
							tutorialPanel.getUndoButton().setEnabled(false);
						} else {
							tutorialPanel.getFeedback().setText("Wrong move! Click Undo to try again.");
							tutorialPanel.getUndoButton().setEnabled(true);
							flag = true;
						}
					} else if (tutePassed == 3) {
						if(colNum == 1) {
							tutePassed++;
							tutorialPanel.setTutePassed(tutePassed);
							tutorialPanel.getFeedback().setText("Well done! Click Next to proceed.");
							tutorialPanel.getUndoButton().setEnabled(false);
							tutorialPanel.getNextTut().setEnabled(true);
							gameEnd = true;
						} else {
							tutorialPanel.getFeedback().setText("Wrong move! Click Undo to try again.");
							tutorialPanel.getUndoButton().setEnabled(true);
							gameEnd = true;
						} 
					} else { 
						if(colNum == 4) {
							tutePassed++;
							tutorialPanel.setTutePassed(tutePassed);
							tutorialPanel.getInstructions().setText("Congratulations! You have completed the tutorial.");
							tutorialPanel.getFeedback().setText("Click Menu and start a new game!");
							tutorialPanel.getUndoButton().setEnabled(false);
							tutorialPanel.getNextTut().setEnabled(false);
							flag = true;
							menu.getResume().setEnabled(false);
							menu.getResume().setText("Resume Game");
						} else {
							tutePassed++;
							tutorialPanel.getFeedback().setText("Wrong move! Click Undo to try again.");
							tutorialPanel.getUndoButton().setEnabled(true);
							flag = true;
						}
					}
					
				}
				rightPanel.getUndoButton().setEnabled(true);
				rightPanel.getRedoButton().setEnabled(false);
				if (player == 0) {
					rightPanel.setColor(1);
				} else if (player == 1) {
					rightPanel.setColor(0);
				}
				if (ge.checkWinCond(colNum, player)) {
					rightPanel.setColor(player);
					if(tutorialOn == false)
						endWin();
				} else if (ge.checkDrawCond()) {
					if(tutorialOn == false)
						endDraw();
				} else {
					if (gameType == 0 && tutePassed != 3) {  
						int aiMove = ge.callAi();	
						player = ge.getPlayer();
						rowNum = ge.validMove(aiMove);
						board.getCol(aiMove).getCircle(rowNum).setValue(player);
						ge.makeMove(aiMove);
						rightPanel.getUndoButton().setEnabled(false);
						if (player == 0) {
							rightPanel.setColor(1);
						} else if (player == 1) {
							rightPanel.setColor(0);
						}
						if (ge.checkWinCond(aiMove, player)) {
							rightPanel.setColor(1);
							if(tutorialOn == false)
								endWin();
						} else if (ge.checkDrawCond()) {
							if(tutorialOn == false)
								endDraw();
						}
						if(flag == true) {
							gameEnd = true;
						}
					}
				}
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {	
	}

	/**
	 * Highlights the column where the mouse is at
	 * @param e
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		Column col = (Column) e.getComponent();	
		col.setBackground(new Color (201, 182, 129));
	}

	/**
	 * Removes hightlight of the column the mouse was on
	 * @param e
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		Column col = (Column) e.getComponent();
		col.setBackground(new Color (222, 206, 162));
	}
	
	/**
	 * Initializes the board and all menu panels
	 */
	private void initCenterPanel() {
		this.centerPanel = new JPanel(new BorderLayout());
		this.board = new Board(this);
		this.menu = new MenuPanel(0);
		this.newGameMenu = new MenuPanel(1);
		initMenu();
		menu.getResume().setEnabled(false);
		initNewGame();
		this.centerPanel.add(this.menu, BorderLayout.CENTER);
		//this.centerPanel.add(this.menu, menuName);
		this.add(centerPanel, BorderLayout.CENTER);	
	}
	
	/**
	 * Initializes the right panel and all the buttons there
	 */
	private void initRightPanel() {
		this.rightPanel = new RightButtonPanel(false);
		rightPanel.setColor(0);
		rightPanel.setPreferredSize(new Dimension(200, 600));
		this.add(rightPanel, BorderLayout.LINE_END);
		rightPanel.getRedoButton().setEnabled(false);
		rightPanel.getUndoButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((ge.undoAvailable())&&(gameEnd==false)) {
					int col = ge.undoMove();
					int row = ge.validMove(col);
					board.getCol(col).getCircle(row).setValue(2);
					if (gameType == 0) {
						col = ge.undoMove();
						row = ge.validMove(col);
						board.getCol(col).getCircle(row).setValue(2);
					}
					rightPanel.getUndoButton().setEnabled(false);
					rightPanel.getRedoButton().setEnabled(true);
				}
			}
        });
		
        rightPanel.getRedoButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				if ((ge.redoAvailable())&&(gameEnd==false)) {
					int player = ge.getPlayer();
					int col = ge.redoMove();
					int row = ge.validMove(col) + 1;
					board.getCol(col).getCircle(row).setValue(player);
					if (gameType == 0) {
						player = ge.getPlayer();
						col = ge.redoMove();
						row = ge.validMove(col) + 1;
						board.getCol(col).getCircle(row).setValue(player);
						
					}
					rightPanel.getRedoButton().setEnabled(false);
					rightPanel.getUndoButton().setEnabled(true);
				}
			}
        });
        
        rightPanel.getNewGameButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				centerPanel.remove(board);
				remove(rightPanel);
				centerPanel.add(menu);
				revalidate();
				repaint();
			}
        });
        
        rightPanel.getHintButton().addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		int aiMove = ge.callHint();							
				board.getCol(aiMove).setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, false));
        	}
        });
	}
	
	/**
	 * Initializes up a pop up window when the game ends in a draw
	 */
	private void endDraw() {
		Object[] options = {"Rematch", "Back to Menu", "Cancel"};
		rightPanel.getRedoButton().setEnabled(false);
		rightPanel.getUndoButton().setEnabled(false);
		rightPanel.getHintButton().setEnabled(false);
		String drawmessage = "No Winner, It's A Draw!";
		System.out.println(drawmessage);
		int draw = JOptionPane.showOptionDialog(null, drawmessage,
				"New Game",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, 
				null,options,null);
		if (draw == JOptionPane.YES_OPTION) {
			createNewGame(gameType, ge.getAi());
		} else if (draw == JOptionPane.NO_OPTION){
			centerPanel.remove(board);
			remove(rightPanel);
			centerPanel.add(menu);
			menu.getResume().setEnabled(false);
			revalidate();
			repaint();
		} else {
			gameEnd = true;
		}
	}
	
	/**
	 * Initializes up a pop up window when someone wins the game
	 */
	private void endWin() {
		int[] winCol = ge.getWinCol();
		int[] winRow = ge.getWinRow();
		for (int i = 0; i < 4; i++) {
			board.getCol(winCol[i]).getCircle(5-winRow[i]).setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
		}
		
		Object[] options = {"Rematch", "Back to Menu", "Cancel"};
		String color;
		if (ge.getPlayer() == 1) {
			color = "Red";
		} else {
			color = "Yellow";
		}
		rightPanel.getRedoButton().setEnabled(false);
		rightPanel.getUndoButton().setEnabled(false);
		rightPanel.getHintButton().setEnabled(false);

		String message = color + " player " + "won!";
		System.out.println(message);
		int end = JOptionPane.showOptionDialog(null, message,
				"New Game",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, 
				null,options,null);
		if (end == JOptionPane.YES_OPTION) {
			createNewGame(this.gameType, this.ge.getAi());
		} else if (end == JOptionPane.NO_OPTION){
			centerPanel.remove(board);
			remove(rightPanel);
			centerPanel.add(menu);
			menu.getResume().setEnabled(false);
			revalidate();
			repaint();
		} else {
			gameEnd = true;
		}
	}
	
	/**
	 * Initializes up all buttons on the main menu
	 */
	private void initMenu() {
		this.menu.getResume().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				centerPanel.remove(menu);
				centerPanel.add(board);
				if (tutorialOn == false) {
					add(rightPanel, BorderLayout.EAST);
					menu.getResume().setText("Resume Game");
				} else {
					add(tutorialPanel, BorderLayout.EAST);
				}
				centerPanel.revalidate();
				centerPanel.repaint();
			}
			
		});

		this.menu.getNewGame().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				centerPanel.remove(menu);
				centerPanel.add(newGameMenu);
				centerPanel.revalidate();
				centerPanel.repaint();
			}
			
		});

		this.menu.getTutorial().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				centerPanel.remove(menu);
				centerPanel.add(board);
				tutorialPanel.setPreferredSize(new Dimension(200, 600));
				add(tutorialPanel, BorderLayout.EAST);
				revalidate();
				repaint();
				
				gameEnd = false;
				createNewGame(0, 2);
				tutorialOn = true;
				tutorialPanel.setColor(ge.getPlayer());
				tutorialPanel.getInstructions().setText("Welcome to Connect Four!\n" + "Your color is red.\n" + "Click on a column to place your move.\n");
				tutorialPanel.getFeedback().setText("Try placing your move on the third column from the left.");
				tutorialPanel.setTutePassed(0);
				tutorialPanel.getNextTut().setEnabled(false);
			}	
		});
		
	}
	
	/**
	 * Initializes up all the buttons on the create new game menu
	 */
	private void initNewGame() {
		this.newGameMenu.getEasy().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("new Easy game");
				createNewGame(0, 1);
				centerPanel.remove(newGameMenu);
				centerPanel.add(board);
				add(rightPanel, BorderLayout.EAST);
				revalidate();
				repaint();
				menu.getResume().setEnabled(true);
				
			}
			
		});
		this.newGameMenu.getMedium().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("new Medium game");
				createNewGame(0, 2);
				centerPanel.remove(newGameMenu);
				centerPanel.add(board);
				add(rightPanel, BorderLayout.EAST);
				revalidate();
				repaint();
				menu.getResume().setEnabled(true);
				
			}
			
		});
		this.newGameMenu.getHard().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("new Hard game");
				createNewGame(0, 3);
				centerPanel.remove(newGameMenu);
				centerPanel.add(board);
				add(rightPanel, BorderLayout.EAST);
				revalidate();
				repaint();
				menu.getResume().setEnabled(true);
				
			}
			
		});

		this.newGameMenu.getMulti().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createNewGame(1, 0);
				centerPanel.remove(newGameMenu);
				centerPanel.add(board);
				add(rightPanel, BorderLayout.EAST);
				revalidate();
				repaint();
				menu.getResume().setEnabled(true);
			}
			
		});
		
		this.newGameMenu.getCancel().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				centerPanel.remove(newGameMenu);
				centerPanel.add(menu);
				revalidate();
				repaint();
				
			}
			
		});
	}
	
	/**
	 * Creates a new game and resets the board
	 * @precond		: type == 0 || tyoe == 1
	 * @param type	: type == 0 := vs AI
	 * 				: type == 1 := vs player	
	 * @param difficulty	:	difficulty == 1 := easy
	 * 							difficulty == 2 := med
	 * 							difficulty == 3 := hard
	 */
	private void createNewGame (int type, int difficulty) {
		for (int i = 0; i < 6; i++) {
			this.board.getCol(i).setBackground(new Color (222, 206, 162));
		}
		this.gameEnd = false;
		this.gameType = type;
		ge = new GameEngine();
		rightPanel.setColor(ge.getPlayer());
		ge.setComputer(difficulty);
		board.clearBoard();
		rightPanel.getHintButton().setEnabled(true);
		menu.getResume().setText("Resume Game");
		this.tutorialOn = false;
	}
	
	/**
	 * Initializes Tutorial Panel
	 * Tutorial Panel contains undo button, next tutorial button, menu button and show player box
	 */
	private void initTutorialPanel() {
		tutorialPanel = new RightButtonPanel(true);
		tutorialPanel.setPreferredSize(new Dimension(200, 600));
		tutorialPanel.getUndoButton().setEnabled(false);
		tutorialPanel.getPrevTut().setEnabled(false);
		
		tutorialPanel.getUndoButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((ge.undoAvailable())) {
					gameEnd = false;
					int col = ge.undoMove();
					int row = ge.validMove(col);
					board.getCol(col).getCircle(row).setValue(2);
					System.out.println("Undo column " + col);
					if(gameType == 0 && tutorialPanel.getTutePassed() != 3) {
						col = ge.undoMove();
						row = ge.validMove(col);
						board.getCol(col).getCircle(row).setValue(2);
					}
					tutorialPanel.getUndoButton().setEnabled(false);
					if(tutorialPanel.getTutePassed() == 0) {
						tutorialPanel.getFeedback().setText("Try placing your move on the third column from the left.");
					} else if (tutorialPanel.getTutePassed() == 1) {
						tutorialPanel.getFeedback().setText("Good! Now, try placing on the fourth column.");
					} else if (tutorialPanel.getTutePassed() == 2 || tutorialPanel.getTutePassed() == 3) {
						tutorialPanel.getFeedback().setText("Win on your next move!");
					} else if (tutorialPanel.getTutePassed() == 4 || tutorialPanel.getTutePassed() == 5) {
						tutorialPanel.getFeedback().setText("Your opponent's winning. Don't let your opponent win!");
					}
				}
			}
        });
		
        tutorialPanel.getNextTut().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameEnd = false;
				if(tutorialPanel.getTutePassed() == 2) {
					tutorialPanel.getNextTut().setEnabled(false);
					createNewGame(0,2);
					tutorialOn = true;
					tutorialPanel.getInstructions().setText("Your goal is to connect 4 in a row either horizontally, vertically or diagonally.");
					tutorialPanel.getFeedback().setText("Win on your next move!");
					board.getCol(0).getCircle(5).setValue(0);
					ge.makeMove(0);
					board.getCol(1).getCircle(5).setValue(1);
					ge.makeMove(1);
					board.getCol(1).getCircle(4).setValue(0);
					ge.makeMove(1);
					board.getCol(2).getCircle(5).setValue(1);
					ge.makeMove(2);
					board.getCol(0).getCircle(4).setValue(0);
					ge.makeMove(0);
					board.getCol(1).getCircle(3).setValue(1);
					ge.makeMove(1);
					board.getCol(0).getCircle(3).setValue(0);
					ge.makeMove(0);
					board.getCol(2).getCircle(4).setValue(1);
					ge.makeMove(2);
				} else if(tutorialPanel.getTutePassed() == 4) {
					tutorialPanel.getNextTut().setEnabled(false);
					tutorialPanel.getInstructions().setText("Blocking your opponent's move is key to winning the game.");
					tutorialPanel.getFeedback().setText("Your opponent's winning. Don't let your opponent win!");
					createNewGame(0,2);
					tutorialOn = true;
					board.getCol(0).getCircle(5).setValue(0);
					ge.makeMove(0);
					board.getCol(1).getCircle(5).setValue(1);
					ge.makeMove(1);
					board.getCol(1).getCircle(4).setValue(0);
					ge.makeMove(1);
					board.getCol(2).getCircle(5).setValue(1);
					ge.makeMove(2);
					board.getCol(0).getCircle(4).setValue(0);
					ge.makeMove(0);
					board.getCol(3).getCircle(5).setValue(1);
					ge.makeMove(3);
				}
			}
        });
       
        tutorialPanel.getNewGameButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				centerPanel.remove(board);
				remove(tutorialPanel);
				centerPanel.add(menu);
				revalidate();
				repaint();
			}
        });
        
	}
}
       
