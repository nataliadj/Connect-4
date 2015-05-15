import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


public class Board extends JPanel{ //implements MouseListener{
	private Column[] board;
	
	public Board() {
		this.setLayout(new GridLayout(1,7));
		this.board = new Column[7];
		initBoard();
	}
	
	public void initBoard() {
		for (int i=0; i<7; i++) { 
			board[i] = new Column(i);
			this.add(board[i]);
        }
	}
	public Column getCol(int i) {
		return this.board[i];
	}
/*
	@Override
	public void mouseClicked(MouseEvent e) {
		
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
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.setBackground(new Color (222, 206, 162));
	}
	*/
}
