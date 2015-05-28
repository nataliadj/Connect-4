import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;


public class RightButtonPanel extends JPanel {
	private JButton undoButton;
	private JButton redoButton;
	private JButton newGameButton;
	private JButton hintButton;
	private JPanel showPlayer;
	private Circle c;
	private JLabel player;
	
	public RightButtonPanel() {
		//this.setLayout(new GridLayout(10,1));
		this.setBackground(new Color (201, 182, 129));
		initButtons();
	}
	
	private void initButtons() {
		this.undoButton = new JButton("Undo");
		this.undoButton.setFont(new Font("Courier", Font.PLAIN,16));
		this.undoButton.setEnabled(false);
		this.redoButton = new JButton("Redo");
		this.redoButton.setFont(new Font("Courier", Font.PLAIN,16));
		this.redoButton.setEnabled(false);
		this.newGameButton = new JButton("Menu");
		this.newGameButton.setFont(new Font("Courier", Font.PLAIN,16));
		this.hintButton = new JButton("Hint");
		this.hintButton.setFont(new Font("Courier", Font.PLAIN,16));
		this.showPlayer = new JPanel();
		this.c = new Circle(-1,-1);
		this.player = new JLabel();
		this.player.setFont(new Font("Courier", Font.PLAIN,16));
		
		GroupLayout GLsmall = new GroupLayout(showPlayer);
		GLsmall.setAutoCreateGaps(true);
		GLsmall.setAutoCreateContainerGaps(true);
		showPlayer.setLayout(GLsmall);
	
		GroupLayout.SequentialGroup topBottom = GLsmall.createSequentialGroup();
		topBottom.addComponent(this.c, 100, 100, 150);
	    topBottom.addComponent(this.player);
	    GroupLayout.ParallelGroup mid = GLsmall.createParallelGroup(GroupLayout.Alignment.CENTER);
		mid.addComponent(this.c, 100, 100, 160);
		mid.addComponent(this.player);
		GroupLayout.SequentialGroup leftRight = GLsmall.createSequentialGroup();
		leftRight.addGroup(mid);
		
		GLsmall.setHorizontalGroup(leftRight);
	    GLsmall.setVerticalGroup(topBottom);
		showPlayer.setBackground(new Color (201, 182, 129));
		showPlayer.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true),
				"Current Player", 0, 0, new Font("Courier", Font.PLAIN,14), Color.BLACK));
		
		this.player.setHorizontalAlignment(SwingConstants.CENTER);
		c.setPreferredSize(new Dimension (80,80));
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
	    if (player == 0) {
	    	c.setValue(0);
	    	this.player.setText("Red");
	    } else if (player == 1) {
	    	c.setValue(1);
	    	this.player.setText("Yellow");

	    }
	}  

}
