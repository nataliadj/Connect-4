import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


public class Board extends JPanel{ 
	private Column[] board;
	
	public Board(MouseListener m) {
		this.setLayout(new GridLayout(1,7));
		this.board = new Column[7];
		initBoard(m);
	}
	
	/**
	 * initializing the board which consists of 7 columns
	 * @param MouseListener
	 * precondition: the board must consist of 7 columns
	 */
	public void initBoard(MouseListener m) {
		for (int i=0; i<7; i++) { 
			board[i] = new Column(i);
			board[i].addMouseListener(m);
			this.add(board[i]);
        }
	}
	
	/**
	 * clear the board when starting a new game
	 * precondition: the board must consist of 7 columns
	 */
	public void clearBoard() {
		for (int i=0; i<7; i++) { 
			board[i].clearCol();
			board[i].setBorder(null);
        }
	}
	
	/**
	 * returns the column with index i
	 * @param int
	 * @return Column
	 * precondition: 0 <= i <= 6
	 */
	public Column getCol(int i) {
		return this.board[i];
	}
}