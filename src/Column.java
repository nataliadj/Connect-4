import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


public class Column extends JPanel{
	private Square[] colGUI;
	private int value;
	public Column(int value) {
		this.setBackground(new Color (222, 206, 162));
		this.setLayout(new GridLayout(6,1));
		this.value = value;
		initCol();
	}
	
	public void initCol() {
		this.colGUI = new Square[7];
		for (int i=0; i<6; i++) { 
               colGUI[i] = new Square(this.value, i);
               add(colGUI[i]);
        }
	}
	
	public int getValue() {
		return value;
	}
	public Square getSquare(int i) {
		return colGUI[i];
	}

}
