import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MenuPanel extends JPanel {
	private JPanel menu;
	private GridBagConstraints c = new GridBagConstraints();
	private JButton resume = new JButton("Resume Game");
	private JButton newGame = new JButton("New Game");
	private JButton tutorial = new JButton("Tutorial");
	private JLabel singleLabel = new JLabel("      --------Single Player--------");
	private JButton multi = new JButton("2 Player");
	private JLabel multiLabel = new JLabel("     ----------Multiplayer----------");
	private JButton easy = new JButton("Easy");
	private JButton medium = new JButton("Medium");
	private JButton hard = new JButton("Hard");
	private JButton popout = new JButton("Pop Out");
	private JButton cancel = new JButton("Cancel");
	
	public MenuPanel(int type) {
		this.setLayout(new GridBagLayout());
		//this.setOpaque(false);
		this.setBackground(new Color (222, 206, 162));
		if (type == 0)
			initMenu();
		else if (type == 1)
			initNewGame();

		this.add(menu, c);
	}
	
	private void initMenu() {
		menu = new JPanel();
		menu.setLayout(new GridBagLayout());
		menu.setPreferredSize(new Dimension(320,350));
		menu.setBackground(new Color (222, 206, 162));

		c.fill = GridBagConstraints.CENTER;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.ipadx = 21;
		c.ipady = 20;
		c.insets = new Insets(0, 0, 20, 0);
		this.resume.setFont(new Font("Courier", Font.PLAIN,16));
		menu.add(this.resume, c);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.5;
		c.ipadx = 52;
		this.newGame.setFont(new Font("Courier", Font.PLAIN,16));
		menu.add(this.newGame, c);
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0.5;
		c.ipadx = 53;
		this.tutorial.setFont(new Font("Courier", Font.PLAIN,16));
		menu.add(this.tutorial, c);
	}
	
	private void initNewGame() {
		menu = new JPanel();
		menu.setLayout(new GridBagLayout());
		menu.setPreferredSize(new Dimension(320,350));
		menu.setBackground(new Color (222, 206, 162));

		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.ipadx = 41;
		c.ipady = 20;
		c.insets = new Insets(0, 0, 0, 0);
		singleLabel.setAlignmentX(CENTER_ALIGNMENT);
		singleLabel.setFont(new Font("Courier", Font.BOLD,18));
		menu.add(this.singleLabel, c);

		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.ipadx = 68;
		c.insets = new Insets(0, 10, 20, 5);
		this.easy.setFont(new Font("Courier", Font.PLAIN,16));
		menu.add(this.easy, c);
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 1;
		c.ipadx = 30;
		c.insets = new Insets(0, 5, 20, 5);
		this.medium.setFont(new Font("Courier", Font.PLAIN,16));
		menu.add(this.medium, c);
		c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = 1;
		c.ipadx = 100;
		c.insets = new Insets(0, 5, 20, 1);
		this.hard.setFont(new Font("Courier", Font.PLAIN,16));
		menu.add(this.hard, c);

		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 3;
		c.ipadx = 30;
		c.insets = new Insets(0, 0, 0, 0);
		multiLabel.setAlignmentX(CENTER_ALIGNMENT);
		multiLabel.setFont(new Font("Courier", Font.BOLD,18));
		menu.add(multiLabel, c);
		
		c.anchor = GridBagConstraints.LINE_END;
		c.gridwidth = 2;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 4;
		c.ipadx = 70;
		c.insets = new Insets(0, 10, 20, 0);
		this.multi.setFont(new Font("Courier", Font.PLAIN,16));
		menu.add(this.multi, c);
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 2;
		c.weightx = 0;
		c.gridx = 3;
		c.gridy = 4;
		c.ipadx = 60;
		c.insets = new Insets(0, 0, 20, 0);
		this.popout.setFont(new Font("Courier", Font.PLAIN,16));
		menu.add(this.popout, c);

		c.fill = GridBagConstraints.CENTER;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 5;
		c.ipadx = 98;
		this.cancel.setFont(new Font("Courier", Font.PLAIN,16));
		menu.add(this.cancel, c);
	}
	
	public JButton getResume() {
		return this.resume;
	}
	
	public JButton getNewGame() {
		return this.newGame;
	}
	
	public JButton getTutorial() {
		return this.tutorial;
	}
	
	public JButton getMulti() {
		return this.multi;
	}
	
	public JButton getEasy() {
		return this.easy;
	}
	
	public JButton getMedium() {
		return this.medium;
	}
	
	public JButton getHard() {
		return this.hard;
	}
	
	public JButton getPopOut() {
		return this.popout;
	}
	
	public JButton getCancel() {
		return this.cancel;
	}
}
