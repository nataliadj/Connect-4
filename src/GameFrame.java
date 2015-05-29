import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GameFrame extends JFrame implements MouseListener{
	private int gameType;
	private boolean gameEnd;
	private MenuPanel menu;
	private MenuPanel newGameMenu;
	private GameEngine ge;
	private Board board;
	private RightButtonPanel rightPanel;
	private JPanel centerPanel;
	private JPanel pops;
	
	public GameFrame(String title){
		super (title);
		setLayout(new BorderLayout());
		initCenterPanel();
		initRightPanel();
		remove(rightPanel);
		this.gameType = 0;
		this.gameEnd = false;
		this.ge = new GameEngine();
		this.pops = null;
		ge.setComputer(3);
		this.pack();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
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
				rightPanel.getUndoButton().setEnabled(true);
				rightPanel.getRedoButton().setEnabled(false);
				if (player == 0) {
					rightPanel.setColor(1);
				} else if (player == 1) {
					rightPanel.setColor(0);
				}
				if (ge.checkWinCond(colNum, player)) {
					rightPanel.setColor(player);
					endWin();
				} else if (ge.checkDrawCond()) {
					endDraw();
				} else {
					if (gameType == 0) {  
						int aiMove = ge.callAi();	
						player = ge.getPlayer();
						rowNum = ge.validMove(aiMove);	
						System.out.println(aiMove);
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
							endWin();
						} else if (ge.checkDrawCond()) {
							endDraw();
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
	
	public void initCenterPanel() {
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
	
	public void initPops() {
		this.pops =  new JPanel(new GridLayout(1,7));
		JButton pop1 = new JButton("Pop");
		JButton pop2 = new JButton("Pop");
		JButton pop3 = new JButton("Pop");
		JButton pop4 = new JButton("Pop");
		JButton pop5 = new JButton("Pop");
		JButton pop6 = new JButton("Pop");
		JButton pop7 = new JButton("Pop");
		pops.add(pop1);
		pops.add(pop2);
		pops.add(pop3);
		pops.add(pop4);
		pops.add(pop5);
		pops.add(pop6);
		pops.add(pop7);
		centerPanel.add(pops, BorderLayout.SOUTH);
	}
	
	public void initRightPanel() {
		this.rightPanel = new RightButtonPanel();
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
					System.out.println("Undo column " + col);
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
					System.out.println("Redo column " + col);
				}
				rightPanel.getRedoButton().setEnabled(false);
				rightPanel.getUndoButton().setEnabled(true);
			}
        });
        
        rightPanel.getNewGameButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				centerPanel.remove(board);
				remove(rightPanel);
				if (pops != null) centerPanel.remove(pops);
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
			int ai = ge.getAi();
			ge = new GameEngine();
			ge.setComputer(ai);
			rightPanel.setColor(0);
			board.clearBoard();
			rightPanel.getHintButton().setEnabled(true);
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
	
	private void endWin() {
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
	
	public void initMenu() {
		this.menu.getResume().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				centerPanel.remove(menu);
				if (pops != null) centerPanel.add(pops, BorderLayout.SOUTH);
				centerPanel.add(board);
				add(rightPanel, BorderLayout.EAST);
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
			}	
		});

		this.menu.getSetting().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
			}
		});
		
	}
	
	private void initNewGame() {
		this.newGameMenu.getEasy().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
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
				createNewGame(0, 3);
				centerPanel.remove(newGameMenu);
				centerPanel.add(board);
				add(rightPanel, BorderLayout.EAST);
				revalidate();
				repaint();
				menu.getResume().setEnabled(true);
				
			}
			
		});
		
		this.newGameMenu.getPopOut1().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				createNewGame(0, 4);
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
		
		this.newGameMenu.getPopOut().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				createNewGame(3, 0);
				centerPanel.remove(newGameMenu);
				centerPanel.add(board);
				initPops();
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
	
	private void createNewGame (int type, int difficulty) {
		this.gameType = type;
		ge = new GameEngine();
		ge.setComputer(difficulty);
		board.clearBoard();
		rightPanel.getHintButton().setEnabled(true);
	}
}
       
