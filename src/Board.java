import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


public class Board extends JPanel{ //implements MouseListener{
	private Column[] board;
	
	public Board(MouseListener m) {
		this.setLayout(new GridLayout(1,7));
		this.board = new Column[7];
		initBoard(m);
	}
	
	public void initBoard(MouseListener m) {
		for (int i=0; i<7; i++) { 
			board[i] = new Column(i);
			board[i].addMouseListener(m);
			this.add(board[i]);
        }
	}
	
	public void clearBoard() {
		for (int i=0; i<7; i++) { 
			board[i].clearCol();
			board[i].setBorder(null);
        }
	}
	
	public Column getCol(int i) {
		return this.board[i];
	}
}