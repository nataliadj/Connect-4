import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class Square extends JPanel {   
	private int row, column;
	public int value;
	public Square(int r, int c) {
		this.row = r;
		this.column = c;
		this.value = -1;
		this.setBackground(Color.blue);	
		//this.setBorder(BorderFactory.createLineBorder(Color.black));
		repaint();
	}
	public int getColumn() {
		return column;
	}
	public int getRow() {
		return row;
	}
	public void setValue(int v) {
		this.value = v;
		repaint();
	}
	@Override
	public void paint(Graphics g){
		super.paint(g);
		if (this.value == 0){
			g.setColor(Color.red);
			g.fillOval(0, 0, this.getWidth()-5, this.getHeight()-5);
		} else if (this.value == 1) {
			g.setColor(Color.yellow);
			g.fillOval(0, 0, this.getWidth()-5, this.getHeight()-5);
		} else {
			g.setColor(Color.white);
			g.fillOval(0, 0, this.getWidth()-5, this.getHeight()-5);
		}
	}
}
