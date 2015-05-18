import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;


public class RightButtonPanel extends JPanel{
	private JButton undoButton;
	private JButton redoButton;
	private JButton newGameButton;
	
	public RightButtonPanel() {
		this.setLayout(new GridLayout(10,1));
		this.setBackground(new Color (201, 182, 129));
		initButtons();
	}
	
	private void initButtons() {
		this.undoButton = new JButton("Undo");
		this.undoButton.setEnabled(false);
		this.redoButton = new JButton("Redo");
		this.redoButton.setEnabled(false);
		this.newGameButton = new JButton("New Game");
		this.add(undoButton);
		this.add(redoButton);
		this.add(newGameButton);
	}
	
	public JButton getUndoButton() {
		return this.undoButton;
	}
	
	public JButton getRedoButton() {
		return this.redoButton;
	}
	
	public JButton getNewGameButton() {
		return this.newGameButton;
	}
}
