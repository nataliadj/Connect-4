import java.awt.BorderLayout;
import java.awt.CardLayout;
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
	private int gameType;
	private boolean gameEnd;
	private MenuPanel newGame;
	private GameEngine ge;
	private Board board;
	private RightButtonPanel rightPanel;
	private JPanel centerPanel;
	
	public GameFrame(String title){
		super (title);
		setLayout(new BorderLayout());
		initCenterPanel();
		initRightPanel();
		this.gameType = 0;
		this.gameEnd = false;
		this.ge = new GameEngine();
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
				if (ge.checkWinCond(colNum, player)) {
					endWin();
				} else if (ge.checkDrawCond()) {
					endDraw();
				} else {
					if (gameType == 0) {  
						int aiMove = ge.callAi();	
						player = ge.getPlayer();
						rowNum = ge.validMove(aiMove);						
						board.getCol(aiMove).getCircle(rowNum).setValue(player);
						ge.makeMove(aiMove);
						rightPanel.getUndoButton().setEnabled(false);
						if (ge.checkWinCond(aiMove, player)) {
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
		this.centerPanel = new JPanel(new CardLayout());
		this.board = new Board(this);
		this.newGame = new MenuPanel();
		centerPanel.add(board);
		centerPanel.add(newGame);
		this.add(centerPanel, BorderLayout.CENTER);
	}
	
	public void initRightPanel() {
		this.rightPanel = new RightButtonPanel();
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
				Object[] options = {"Human vs Human", "Human vs Computer"};
				String end = (String)JOptionPane.showInputDialog(null, "Create new game?",
						"New Game",JOptionPane.QUESTION_MESSAGE,null,options,"Human vs Human");
				if (end != null) {
					if (end.equals("Human vs Human")) {
						gameType = 1;
						ge = new GameEngine();
					} else if (end.equalsIgnoreCase("Human vs Computer")){
						Object[] levels = {"Easy", "Medium", "Hard"};
						String level = (String)JOptionPane.showInputDialog(null, "Choose Difficulty:",
								"Difficulty:",JOptionPane.QUESTION_MESSAGE,null,levels,"Easy");
						if (level!=null) {
							ge = new GameEngine();
							if (level.equals("Easy")) {
								ge.setComputer(1);
							} else if (level.equals("Medium")) {
								ge.setComputer(2);
							} else if (level.equalsIgnoreCase("Hard")) {
								ge.setComputer(3);
							}
						}
						gameType = 0;
					}
					gameEnd = false;
					board.clearBoard();
				}
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
		Object[] options = {"Human vs Human", "Human vs Computer"};
		String end = (String)JOptionPane.showInputDialog(null, "No winner! - It's a draw\nCreate new game?",
				"New Game",JOptionPane.QUESTION_MESSAGE,null,options,"Human vs Human");
		rightPanel.getRedoButton().setEnabled(false);
		rightPanel.getUndoButton().setEnabled(false);
		rightPanel.getHintButton().setEnabled(false);
		if (end != null) {
			if (end.equals("Human vs Human")) {
				gameType = 1;
				ge = new GameEngine();
			} else if (end.equalsIgnoreCase("Human vs Computer")){
				Object[] levels = {"Easy", "Medium", "Hard"};
				String level = (String)JOptionPane.showInputDialog(null, "Choose Difficulty:",
						"Difficulty:",JOptionPane.QUESTION_MESSAGE,null,levels,"Easy");
				if (level!=null) {
					ge = new GameEngine();
					if (level.equals("Easy")) {
						ge.setComputer(1);
					} else if (level.equals("Medium")) {
						ge.setComputer(2);
					} else if (level.equalsIgnoreCase("Hard")) {
						ge.setComputer(3);
					}
				}
				gameType = 0;
			}
			board.clearBoard();
			rightPanel.getHintButton().setEnabled(true);
		} else {
			gameEnd = true;
		}
	}
	private void endWin() {
		Object[] options = {"Human vs Human", "Human vs Computer"};
		String color;
		if (ge.getPlayer() == 1) {
			color = "Red";
		} else {
			color = "Yellow";
		}
		rightPanel.getRedoButton().setEnabled(false);
		rightPanel.getUndoButton().setEnabled(false);
		rightPanel.getHintButton().setEnabled(false);
		String message = color + " player " + "won! \nCreate new game?";
		System.out.println(message);
		String end = (String)JOptionPane.showInputDialog(null, message,
				"New Game",JOptionPane.QUESTION_MESSAGE,null,options,"Human vs Human");
		if (end != null) {
			if (end.equals("Human vs Human")) {
				gameType = 1;
				ge = new GameEngine();
			} else if (end.equalsIgnoreCase("Human vs Computer")){
				Object[] levels = {"Easy", "Medium", "Hard"};
				String level = (String)JOptionPane.showInputDialog(null, "Choose Difficulty:",
						"Difficulty",JOptionPane.QUESTION_MESSAGE,null,levels,"Easy");
				if (level!=null) {
					ge = new GameEngine();
					if (level.equals("Easy")) {
						ge.setComputer(1);
					} else if (level.equals("Medium")) {
						ge.setComputer(2);
					} else if (level.equalsIgnoreCase("Hard")) {
						ge.setComputer(3);
					}
				}
				gameType = 0;
			}
			board.clearBoard();
			
			rightPanel.getHintButton().setEnabled(true);
		} else {
			gameEnd = true;
		}
	}
}
       
