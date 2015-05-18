import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
	private Board board;
	private GameEngine ge;
	private RightButtonPanel rightPanel;

	public GameFrame(String title){
		super (title);
		setLayout(new BorderLayout());
		this.board = new Board(this);
		this.add(board, BorderLayout.CENTER);
		initRightPanel();
		this.gameType = 0;
		this.gameEnd = false;
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
				board.getCol(colNum).getCircle(rowNum).setValue(player);
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
						board.clearBoard();
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
	
	public void initRightPanel() {
		this.rightPanel = new RightButtonPanel();
		rightPanel.setPreferredSize(new Dimension(150, 600));
		this.add(rightPanel, BorderLayout.LINE_END);
		rightPanel.getUndoButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if ((ge.undoAvailable())&&(gameEnd==false)) {
					int col = ge.undoMove();
					int row = ge.validMove(col);
					board.getCol(col).getCircle(row).setValue(2);
					System.out.println("Undo column " + col);
					if (gameType == 1) {
						int colAI = ge.undoMove();
						int rowAI = ge.validMove(col);
						board.getCol(colAI).getCircle(rowAI).setValue(2);
						System.out.println("Undo column " + colAI);
						
					}
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
			}
        });
	}
}
       
