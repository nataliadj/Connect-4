import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;


public class MenuPanel extends JPanel {
	JPanel menu;
	GridBagConstraints c = new GridBagConstraints();
	
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
		JButton button = new JButton("Resume Game");
		menu.add(button, c);
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0.5;
		c.ipadx = 43;
		button = new JButton("New Game");
		menu.add(button, c);
		c.gridx = 0;
		c.gridy = 4;
		c.weightx = 0.5;
		c.ipadx = 60;
		button = new JButton("Tutorial");
		menu.add(button, c);
		c.gridx = 0;
		c.gridy = 6;
		c.weightx = 0.5;
		c.ipadx = 65;
		button = new JButton("Setting");
		menu.add(button, c);
	}
}
