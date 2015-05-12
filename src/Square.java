import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class Square extends JPanel {   
	private int row, column;
	public int value;
	public Square(int r, int c) {
		this.row = r;
		this.column = c;
		this.value = -1;
		this.setBackground(Color.white);	
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	public int getColumn() {
		return column;
	}
	public int getRow() {
		return row;
	}
	public void setValue(int v) {
		this.value = v;
	}
}
