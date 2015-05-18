import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;


public class RightButtonPanel extends JPanel{
	private JButton undoButton;
	private JButton redoButton;
	private JButton menuButton;
	
	public RightButtonPanel() {
		this.setLayout(new GridLayout(10,1));
		this.setBackground(new Color (201, 182, 129));
		initButtons();
	}
	
	private void initButtons() {
		this.undoButton = new JButton("Undo");
		this.redoButton = new JButton("Redo");
		this.menuButton = new JButton("Menu");
		this.add(undoButton);
		this.add(redoButton);
		this.add(menuButton);
	}
	
	public JButton getUndoButton() {
		return this.undoButton;
	}
	
	public JButton getRedoButton() {
		return this.redoButton;
	}
	
	public JButton getMenuButton() {
		return this.menuButton;
	}
}
