import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;


public class MenuPanel extends JPanel {
	private JPanel menu;
	private GridBagConstraints c = new GridBagConstraints();
	private JButton resume;
	private JButton newGame;
	private JButton tutorial;
	private JButton setting;
	
	public MenuPanel() {
		this.setLayout(new GridBagLayout());
		this.setOpaque(false);
		initMenu();
		this.add(menu, c);

	}
	
	private void initMenu() {
		menu = new JPanel();
		//menu.setLayout(new BorderLayout());
		//menu.setBorder(new LineBorder(Color.BLACK, 5 , true));
		menu.setLayout(new GridBagLayout());
		menu.setPreferredSize(new Dimension(220,250));
		menu.setBackground(new Color(143, 222, 252));
		c.fill = GridBagConstraints.CENTER;

		c.fill = GridBagConstraints.CENTER;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.ipadx = 21;
		c.ipady = 20;
		this.resume = new JButton("Resume Game");
		menu.add(this.resume, c);
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0.5;
		c.ipadx = 43;
		this.newGame = new JButton("New Game");
		menu.add(this.newGame, c);
		c.gridx = 0;
		c.gridy = 4;
		c.weightx = 0.5;
		c.ipadx = 60;
		this.tutorial = new JButton("Tutorial");
		menu.add(this.tutorial, c);
		c.gridx = 0;
		c.gridy = 6;
		c.weightx = 0.5;
		c.ipadx = 65;
		this.setting = new JButton("Setting");
		menu.add(this.setting, c);
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
	
	public JButton getSetting() {
		return this.setting;
	}
}
