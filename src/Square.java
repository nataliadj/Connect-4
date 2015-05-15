
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Square extends JPanel {   
	private int row, column;
	public int value;
	public Square(int r, int c) {
		this.row = r;
		this.column = c;
		this.value = -1;
		this.setOpaque(false);
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
			g.fillOval(5, 5, this.getWidth()-10, this.getHeight()-10);
		} else if (this.value == 1) {
			g.setColor(Color.yellow);
			g.fillOval(5, 5, this.getWidth()-10, this.getHeight()-10);
		} else {
			g.setColor(new Color (240, 226, 189));
			g.fillOval(5, 5, this.getWidth()-10, this.getHeight()-10);
		}
	}
}
