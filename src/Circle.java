
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Circle extends JPanel {   
	private int row, column;
	public int value;
	
	/**
	 * Precondition	: r >= 0 && r <= 5
	 * 				: c >= 0 && c <= 6
	 * @param r
	 * @param c
	 */
	public Circle(int r, int c) {
		this.row = r;
		this.column = c;
		this.value = -1;
		this.setOpaque(false);
		repaint();
	}

	/**
	 * returns the column index of this circle
	 * @return int
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * returns the row index of this circle
	 * @return int
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * changing the value of this circle and adjusting the color accordingly
	 * @param int
	 * precondition: v != null
	 */
	public void setValue(int v) {
		this.value = v;
		repaint();
	}
	
	/**
	 * setting the color of this circle according to the value of this circle
	 * 
	 */
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
