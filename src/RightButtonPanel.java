import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class RightButtonPanel extends JPanel{
	private JButton undoButton;
	private JButton redoButton;
	private JButton newGameButton;
	private JButton hintButton;
	
	public RightButtonPanel() {
		//this.setLayout(new GridLayout(10,1));
		this.setBackground(new Color (201, 182, 129));
		initButtons();
	}
	
	private void initButtons() {
		this.undoButton = new JButton("Undo");
		//this.undoButton.setPreferredSize(new Dimension(200, 100));
		this.undoButton.setEnabled(false);
		this.redoButton = new JButton("Redo");
		this.redoButton.setEnabled(false);
		this.newGameButton = new JButton("New Game");
		this.hintButton = new JButton("Hint");
		GroupLayout GL = new GroupLayout(this);
		GL.setAutoCreateGaps(true);
		this.setLayout(GL);
		GroupLayout.SequentialGroup leftToRight = GL.createSequentialGroup();

		GroupLayout.ParallelGroup columnMiddle = GL.createParallelGroup(GroupLayout.Alignment.TRAILING);
		columnMiddle.addComponent(undoButton, 100, 100, 200);
		columnMiddle.addComponent(redoButton, 100, 100, 200);
	    columnMiddle.addComponent(newGameButton, 100, 100, 200);
	    columnMiddle.addComponent(hintButton, 100, 100, 200);
	    leftToRight.addGroup(columnMiddle);

		GroupLayout.SequentialGroup topToBottom = GL.createSequentialGroup();
	    topToBottom.addComponent(undoButton, 30, 50, 50);
	    topToBottom.addComponent(redoButton, 30, 50, 50);
	    topToBottom.addComponent(newGameButton, 30, 50, 50);
	    topToBottom.addComponent(hintButton, 30, 50, 50);

	    GL.setHorizontalGroup(leftToRight);
	    GL.setVerticalGroup(topToBottom);
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
	
	public JButton getHintButton() {
		return this.hintButton;
	}
}
