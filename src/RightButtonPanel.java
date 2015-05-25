import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;


public class RightButtonPanel extends JPanel {
	private JButton undoButton;
	private JButton redoButton;
	private JButton newGameButton;
	private JButton hintButton;
	private JPanel showPlayer;
	private int player;
	private Circle circle;
	
	public RightButtonPanel() {
		//this.setLayout(new GridLayout(10,1));
		this.setBackground(new Color (201, 182, 129));
		this.player = -1;
		initButtons();
	}
	
	private void initButtons() {
		this.undoButton = new JButton("Undo");
		this.undoButton.setEnabled(false);
		this.redoButton = new JButton("Redo");
		this.redoButton.setEnabled(false);
		this.newGameButton = new JButton("New Game");
		this.hintButton = new JButton("Hint");
		this.showPlayer = new JPanel();
		showPlayer.setBackground(new Color (201, 182, 129));
		showPlayer.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true), "Current Player:", 0, 0, new JLabel().getFont(), Color.BLACK));
		//showPlayer.add(circle);
		repaint();
		GroupLayout GL = new GroupLayout(this);
		GL.setAutoCreateGaps(true);
		GL.setAutoCreateContainerGaps(true);
		this.setLayout(GL);
		GroupLayout.SequentialGroup leftToRight = GL.createSequentialGroup();

		GroupLayout.ParallelGroup columnMiddle = GL.createParallelGroup(GroupLayout.Alignment.TRAILING);
		columnMiddle.addComponent(undoButton, 100, 100, 200);
		columnMiddle.addComponent(redoButton, 100, 100, 200);
	    columnMiddle.addComponent(newGameButton, 100, 100, 200);
	    columnMiddle.addComponent(hintButton, 100, 100, 200);
	    columnMiddle.addComponent(showPlayer, 100, 100, 200);
	    leftToRight.addGroup(columnMiddle);

		GroupLayout.SequentialGroup topToBottom = GL.createSequentialGroup();
	    topToBottom.addComponent(undoButton, 30, 50, 50);
	    topToBottom.addComponent(redoButton, 30, 50, 50);
	    topToBottom.addComponent(newGameButton, 30, 50, 50);
	    topToBottom.addComponent(hintButton, 30, 50, 50);
	    topToBottom.addPreferredGap(hintButton, showPlayer, LayoutStyle.ComponentPlacement.UNRELATED, 0, 500);
	    topToBottom.addComponent(showPlayer, 100, 150, 200);
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
	
	public void setColor(int player) {
		this.player = player;
		repaint();
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (circle != null) {
            g.fillOval(circle.getX(), circle.getY(), getWidth(), getHeight());
        }
    }
	
}
